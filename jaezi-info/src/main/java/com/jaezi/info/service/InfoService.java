package com.jaezi.info.service;

import com.jaezi.common.base.BaseException;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.info.dao.InfoDao;
import com.jaezi.info.dao.InfoStaffDao;
import com.jaezi.info.model.Info;
import com.jaezi.info.model.InfoStaff;
import com.jaezi.info.vo.InfoVo;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 15:49
 * @description 消息信息服务接口实现类
 */
@Service
public class InfoService extends BaseService<Info, InfoVo> {

    public InfoDao infoDao;
    private InfoStaffDao infoStaffDao;
    private final MinioClient minioClient;

    public InfoService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public void setBaseDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }

    @Autowired
    public void setBaseDao(InfoStaffDao infoStaffDao) {
        this.infoStaffDao = infoStaffDao;
    }

    /**
     * 添加消息信息
     *
     * @param info 消息数据
     * @return 添加是否成功
     */
    public int addInfo(InfoVo info) throws BaseException {
        final int issueStatus = 1;
        //如果状态是发布
        if (issueStatus == info.getStatus()) {
            info.setSenderStatus(issueStatus);
            info.setRecipientStatus(issueStatus);
            //如果是发件人,直接设置已读
            info.setSenderReadStatus(1);
            info.setRecipientReadStatus(2);
        }

        info.setCreateTime(System.currentTimeMillis());
        info.setCreateUser(JwtUtil.getRealName());

        if (null != info.getInfoId()) {
            info.setReplyId(info.getInfoId());
        }

        int rest = infoDao.add(info);
        if (rest <= 0) {
            throw new BaseException("消息添加失败");
        }

        if (null == info.getInfoId()) {
            infoDao.updateClaim(info.getClaimId(), info.getId());
        }

        //添加抄送人列表
        List<InfoStaff> infoStaffList = info.getInfoStaffList();
        if (!ObjectUtils.isEmpty(infoStaffList)) {
            infoStaffList.parallelStream().forEach(infoStaff -> {
                if (issueStatus == info.getStatus()) {
                    infoStaff.setStatus(1);
                }
                infoStaff.setInfoId(info.getId());
            });

            int rests = infoStaffDao.addBath(infoStaffList);
            if (rests <= 0) {
                throw new BaseException("消息抄送人添加失败");
            }
        }

        return rest;
    }

    /**
     * 更新未读状态
     *
     * @param id
     * @return int
     */
    public int updateReadStatus(Integer id, String realName) {
        return infoDao.updateReadStatus(id, realName);
    }

    /**
     * 更新删除状态
     *
     * @param id
     * @return int
     */
    public int remove(Integer id, String realName) {
        Info info = infoDao.getOneById(id);

        return infoDao.updateStatus(info, realName);
    }

    /**
     * 信息交流二级界面文件上传
     *
     * @param file 上传的文件
     * @param id   信息交流ID
     * @return 文件路径
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    public String documentUpload(MultipartFile file, Integer id) {
        String dir = INFO + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            Info oneById = infoDao.getOneById(id);
            oneById.setDocumentName(file.getOriginalFilename());
            oneById.setDocumentUrl(result.get(URL));
            infoDao.update(oneById);
            return result.get(URL);
        }
        return null;
    }
}
