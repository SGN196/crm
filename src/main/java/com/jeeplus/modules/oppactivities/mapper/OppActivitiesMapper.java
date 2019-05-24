/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.oppactivities.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.oppactivities.entity.OppActivities;

import java.util.List;

/**
 * 商机跟进MAPPER接口
 * @author Commit
 * @version 2019-05-11
 */
@MyBatisMapper
public interface OppActivitiesMapper extends BaseMapper<OppActivities> {
    OppActivities getByOppId(String oppId);

    List<OppActivities> findListByDate(OppActivities entity);
}