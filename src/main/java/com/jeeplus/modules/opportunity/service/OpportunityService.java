/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.opportunity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.opportunity.entity.Opportunity;
import com.jeeplus.modules.opportunity.mapper.OpportunityMapper;

/**
 * 线索商机管理列表Service
 * @author Commit
 * @version 2019-05-09
 */
@Service
@Transactional(readOnly = true)
public class OpportunityService extends CrudService<OpportunityMapper, Opportunity> {

	public Opportunity get(String id) {
		return super.get(id);
	}
	
	public List<Opportunity> findList(Opportunity opportunity) {
		return super.findList(opportunity);
	}
	
	public Page<Opportunity> findPage(Page<Opportunity> page, Opportunity opportunity) {
		return super.findPage(page, opportunity);
	}
	
	@Transactional(readOnly = false)
	public void save(Opportunity opportunity) {
		super.save(opportunity);
	}
	
	@Transactional(readOnly = false)
	public void delete(Opportunity opportunity) {
		super.delete(opportunity);
	}
	
}