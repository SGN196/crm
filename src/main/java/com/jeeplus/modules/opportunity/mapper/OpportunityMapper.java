/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.opportunity.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.opportunity.entity.Opportunity;

/**
 * 线索商机管理列表MAPPER接口
 * @author Commit
 * @version 2019-05-09
 */
@MyBatisMapper
public interface OpportunityMapper extends BaseMapper<Opportunity> {
	
}