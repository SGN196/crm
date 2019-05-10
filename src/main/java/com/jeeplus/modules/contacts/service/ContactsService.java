/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.contacts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.contacts.entity.Contacts;
import com.jeeplus.modules.contacts.mapper.ContactsMapper;

/**
 * 客户联系人Service
 * @author Vigny
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly = true)
public class ContactsService extends CrudService<ContactsMapper, Contacts> {

	public Contacts get(String id) {
		return super.get(id);
	}
	
	public List<Contacts> findList(Contacts contacts) {
		return super.findList(contacts);
	}
	
	public Page<Contacts> findPage(Page<Contacts> page, Contacts contacts) {
		return super.findPage(page, contacts);
	}
	
	@Transactional(readOnly = false)
	public void save(Contacts contacts) {
		super.save(contacts);
	}
	
	@Transactional(readOnly = false)
	public void delete(Contacts contacts) {
		super.delete(contacts);
	}
	
}