/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.customer.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.customer.entity.Customer;

import java.util.List;

/**
 * 客户档案MAPPER接口
 * @author Vigny
 * @version 2019-05-09
 */
@MyBatisMapper
public interface CustomerMapper extends BaseMapper<Customer> {

    List<Customer> findHighList(Customer customer);
}