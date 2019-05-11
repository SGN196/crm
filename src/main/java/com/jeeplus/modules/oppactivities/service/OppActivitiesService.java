/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.oppactivities.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.oppactivities.entity.OppActivities;
import com.jeeplus.modules.oppactivities.mapper.OppActivitiesMapper;

/**
 * 商机跟进Service
 * @author Commit
 * @version 2019-05-11
 */
@Service
@Transactional(readOnly = true)
public class OppActivitiesService extends CrudService<OppActivitiesMapper, OppActivities> {

	public OppActivities get(String id) {
		return super.get(id);
	}
	
	public List<OppActivities> findList(OppActivities oppActivities) {
		return super.findList(oppActivities);
	}
	
	public Page<OppActivities> findPage(Page<OppActivities> page, OppActivities oppActivities) {
		return super.findPage(page, oppActivities);
	}
	
	@Transactional(readOnly = false)
	public void save(OppActivities oppActivities) {
		super.save(oppActivities);
	}
	
	@Transactional(readOnly = false)
	public void delete(OppActivities oppActivities) {
		super.delete(oppActivities);
	}
	
}