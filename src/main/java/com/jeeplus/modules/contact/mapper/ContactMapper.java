/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.contact.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.contact.entity.Contact;

/**
 * 客户联系人MAPPER接口
 * @author Vigny
 * @version 2019-05-10
 */
@MyBatisMapper
public interface ContactMapper extends BaseMapper<Contact> {
	
}