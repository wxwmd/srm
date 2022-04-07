package com.jaezi.bus.supplierQuality.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.supplierQuality.dao.MidQmFeedbackTablePersonDao;
import com.jaezi.bus.supplierQuality.dao.QmFeedbackTableDao;
import com.jaezi.bus.supplierQuality.dto.QmFeedbackTableDto;
import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.model.QmFeedbackTable;
import com.jaezi.bus.supplierQuality.vo.QmFeedbackTableVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;

import io.minio.MinioClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/14 14:29
 * @description 未审批和已审批QM反馈单业务层
 */
@Service
public class QmFeedbackTableService extends BaseService<QmFeedbackTable, QmFeedbackTableVo> {

    private QmFeedbackTableDao qmFeedbackTableDao;

    private final MinioClient minioClient;

    private MidQmFeedbackTablePersonDao midQmFeedbackTablePersonDao;

    @Autowired
    public void setBaseDao(QmFeedbackTableDao qmFeedbackTableDao, MidQmFeedbackTablePersonDao midQmFeedbackTablePersonDao) {
        super.setBaseDao(qmFeedbackTableDao);
        this.qmFeedbackTableDao = qmFeedbackTableDao;
        this.midQmFeedbackTablePersonDao = midQmFeedbackTablePersonDao;
    }

    public QmFeedbackTableService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id) {
        QmFeedbackTable qmFeedbackTable = qmFeedbackTableDao.getOneById(id);
        if (qmFeedbackTable != null) {
            String url = qmFeedbackTable.getUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(qmFeedbackTable.getUrl(), qmFeedbackTable.getFeedbackTheme() + "." + suffix, response, minioClient, QM_FEEDBACK_TABLE_DIR);
        }
    }

    /**
     * 查询所有QM反馈单
     *
     * @param filter
     * @return QmFeedbackTable>
     */
    public DataGrid<QmFeedbackTable> findAll(Map<String, String> filter) {
        DataGrid<QmFeedbackTable> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<QmFeedbackTable> all = getListByVisible(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<QmFeedbackTable> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<QmFeedbackTable> list = getListByVisible(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 查询所有QM反馈单
     *
     * @param filter
     * @return QmFeedbackTable>
     */
    public List<QmFeedbackTable> getListByVisible(Map<String, String> filter) {
        Integer userType = Integer.valueOf(filter.get("userType"));
        if (userType == 1) {
            return qmFeedbackTableDao.findByVisible(filter);
        }
        return qmFeedbackTableDao.findAll(filter);
    }

    /**
     * 添加QM反馈单
     *
     * @param qmFeedbackTableDto QM反馈单对象数据和可见人员数据
     * @return int     添加成功数
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    public int addQmFeedbackTable(QmFeedbackTableDto qmFeedbackTableDto) {
        QmFeedbackTable qmFeedbackTable = new QmFeedbackTable();
        BeanUtils.copyProperties(qmFeedbackTableDto, qmFeedbackTable);
        List<Integer> visiblePersonList = qmFeedbackTableDto.getVisiblePersonList();
        Integer visibleType = qmFeedbackTableDto.getVisible();
        int addResult = qmFeedbackTableDao.add(qmFeedbackTable);
        if (addResult > 0) {
            if (visibleType == 0) {
                for (Integer personId : visiblePersonList) {
                    MidQmFeedbackTablePerson midQmFeedbackTablePerson = new MidQmFeedbackTablePerson();
                    midQmFeedbackTablePerson.setPersonId(personId);
                    midQmFeedbackTablePerson.setQmFeedbackTableId(qmFeedbackTable.getId());
                    midQmFeedbackTablePersonDao.add(midQmFeedbackTablePerson);
                }
            }
        }
        return addResult;
    }

    /**
     * 文件上传
     *
     * @param file        文件对象
     * @param minioClient minio客户端对象
     * @author whj
     * @date 2021/8/14
     * @since 1.0
     */
    public String upload(MultipartFile file, MinioClient minioClient) {
        // 文件夹名称
        String dir = QM_FEEDBACK_TABLE_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 删除之前先判断，是否有关联的人员，有，就先删除中间表信息，再删除qm反馈单信息
     *
     * @param id qm反馈单id
     * @return int  删除成功数
     * @author whj
     * @date 2021/8/24
     * @since 1.0
     */
    public int deleteQmFeedback(Integer id) {
        List<MidQmFeedbackTablePerson> list = midQmFeedbackTablePersonDao.getListByQmFeedbackTableId(id);
        if (list.size() > 0) {
            midQmFeedbackTablePersonDao.deleteListByQmFeedbackTableId(id);
        }
        return qmFeedbackTableDao.delete(id);
    }

    /**
     * 修改QM反馈单信息，先查看对象中是否有可见性的修改，有的话就先删除之前的可见性，重新添加新的可见性
     *
     * @param qmFeedbackTableDto QM反馈单对象数据和可见人员数据
     * @return int  修改数
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    public int updateQmFeedbackTable(QmFeedbackTableDto qmFeedbackTableDto) {
        QmFeedbackTable qmFeedbackTable = new QmFeedbackTable();
        BeanUtils.copyProperties(qmFeedbackTableDto, qmFeedbackTable);
        Integer visible = qmFeedbackTableDto.getVisible();
        List<Integer> visiblePersonList = qmFeedbackTableDto.getVisiblePersonList();
        Integer id = qmFeedbackTableDto.getId();
        int result = midQmFeedbackTablePersonDao.deleteListByQmFeedbackTableId(id);
        if (result >= 0) {
            if (visible == 0) {
                for (Integer personId : visiblePersonList) {
                    MidQmFeedbackTablePerson midQmFeedbackTablePerson = new MidQmFeedbackTablePerson();
                    midQmFeedbackTablePerson.setPersonId(personId);
                    midQmFeedbackTablePerson.setQmFeedbackTableId(id);
                    midQmFeedbackTablePersonDao.add(midQmFeedbackTablePerson);
                }
            }
        }
        return qmFeedbackTableDao.update(qmFeedbackTable);
    }
}
