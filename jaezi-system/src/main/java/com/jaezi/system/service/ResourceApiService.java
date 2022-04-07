package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.system.model.Permission;
import com.jaezi.system.model.ResourceApi;
import com.jaezi.system.vo.PermissionVo;
import com.jaezi.system.vo.ResourceApiVo;
import com.jaezi.system.dao.PermissionDao;
import com.jaezi.system.dao.ResourceApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 15:49
 * @description 菜单服务实现类
 */
@Service
public class ResourceApiService extends BaseService<ResourceApi, ResourceApiVo> {

    private ResourceApiDao resourceApiDao;
    private PermissionDao permissionDao;

    @Autowired
    public void setBaseDao(ResourceApiDao resourceApiDao, PermissionDao permissionDao){
        super.setBaseDao(resourceApiDao);
        this.resourceApiDao = resourceApiDao;
        this.permissionDao = permissionDao;
    }

    /**
     * 获取Api资源树
     * @return
     */
    public List<ResourceApiVo> getTreeApis() {
        List<ResourceApiVo> result = new ArrayList<>();
        List<ResourceApiVo> apis = resourceApiDao.getAllVos(null);
        if(null == apis){
            return null;
        }
        Map<Integer, List<ResourceApiVo>> apiMap = apis.stream().collect(Collectors.groupingBy(ResourceApiVo::getPermissionId));

        Map<String, String> filter = new HashMap<>();
        filter.put("permissionType", "Api");
        List<PermissionVo> permissionList = permissionDao.getAllVos(filter);
        if (permissionList.size()>0){
            Map<Integer, List<PermissionVo>> map = permissionList.stream().collect(Collectors.groupingBy(Permission::getPid));

            for (PermissionVo permissionVo : map.remove(0)) {
                ResourceApiVo resourceApiVo = new ResourceApiVo();
                resourceApiVo.setId(permissionVo.getId());
                resourceApiVo.setPid(0);
                resourceApiVo.setApiName(permissionVo.getPermissionName());
                resourceApiVo.setStatus(0);
                result.add(resourceApiVo);
            }

            extractApi(map, result, apiMap);

            return result;
        }
        return null;
    }

    private void extractApi(Map<Integer, List<PermissionVo>> map, List<ResourceApiVo> result, Map<Integer, List<ResourceApiVo>> apiMap) {
        for (ResourceApiVo resourceApiVo : result) {
            if (!map.containsKey(resourceApiVo.getId())){
                break;
            }
            List<PermissionVo> remove = map.remove(resourceApiVo.getId());
            for (PermissionVo permissionVo : remove) {
                if (permissionVo.getIsLeaf() == 1){
                    if (apiMap.containsKey(permissionVo.getId())) {
                        resourceApiVo.getChildren().addAll(apiMap.remove(permissionVo.getId()));
                        if (apiMap.isEmpty()) {
                            return;
                        }
                    }
                    continue;
                }
                ResourceApiVo resourceApi = new ResourceApiVo();
                resourceApi.setId(permissionVo.getId());
                resourceApi.setPid(resourceApiVo.getId());
                resourceApi.setApiName(permissionVo.getPermissionName());
                resourceApi.setStatus(0);
                resourceApiVo.getChildren().add(resourceApi);
            }
            extractApi(map, resourceApiVo.getChildren(), apiMap);
        }
    }

}
