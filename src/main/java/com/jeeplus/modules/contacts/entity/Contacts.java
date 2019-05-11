/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.contacts.entity;

import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.core.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 客户联系人Entity
 * @author Vigny
 * @version 2019-05-10
 */
public class Contacts extends DataEntity<Contacts> {
	
	private static final long serialVersionUID = 1L;
	private String cusId;		// 客户Id
	private String name;		// 联系人姓名
	private Integer gender;		// 性别
	private Integer state;		// 状态
	private String cardId;		// 身份证号码
	private String department;		// 部门
	private String title;		// 职务
	private String officePhone;		// 办公室电话
	private String mobile;		// 手机号码
	private String email;		// 邮件地址
	private String qqNumber;		// QQ号码
	private String wxId;		// 微信Id
	private String influence;		// 决策影响力
	private String description;		// 性格描述
	
	public Contacts() {
		super();
	}

	public Contacts(String id){
		super(id);
	}

	@ExcelField(title="客户Id", align=2, sort=1)
	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	
	@ExcelField(title="联系人姓名", align=2, sort=2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="性别不能为空")
	@ExcelField(title="性别", dictType="", align=2, sort=3)
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@NotNull(message="状态不能为空")
	@ExcelField(title="状态", align=2, sort=4)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@ExcelField(title="身份证号码", align=2, sort=5)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	@ExcelField(title="部门", dictType="", align=2, sort=6)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@ExcelField(title="职务", dictType="", align=2, sort=7)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ExcelField(title="办公室电话", align=2, sort=8)
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	
	@ExcelField(title="手机号码", align=2, sort=9)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@ExcelField(title="邮件地址", align=2, sort=10)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ExcelField(title="QQ号码", align=2, sort=11)
	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	
	@ExcelField(title="微信Id", align=2, sort=13)
	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
	
	@ExcelField(title="决策影响力", align=2, sort=14)
	public String getInfluence() {
		return influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}
	
	@ExcelField(title="性格描述", align=2, sort=15)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}