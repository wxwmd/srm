package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.Template;
import com.jaezi.system.vo.TemplateVo;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateDao extends BaseDao<Template, TemplateVo> {
}
