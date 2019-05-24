/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.oppactivities.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.opportunity.entity.Opportunity;
import com.jeeplus.modules.sys.entity.User;

/**
 * 商机跟进Entity
 * @author Commit
 * @version 2019-05-11
 */
public class OppActivities extends DataEntity<OppActivities> {
	
	private static final long serialVersionUID = 1L;
	private String oppId;		// 商机编号
	private String actTypeID;		// 活动类型
	private Integer order;		// 排序序号
	private String contactID;		// 联系人编号
	private Date date;		// 跟进时间
	private String detail;		// 跟进内容
	private String empID;		// 跟进人编号
	private Date billDate;		// 记录时间

	private User activitieUser;//跟进人

	private Opportunity opportunity;//商机
	public OppActivities() {
		super();
	}
	public OppActivities(String id){
		super(id);
	}
	@ExcelField(title="商机编号", align=2, sort=1)
	public String getOppId() {
		return oppId;
	}

	public void setOppId(String oppId) {
		this.oppId = oppId;
	}
	
	@ExcelField(title="活动类型", dictType="", align=2, sort=2)
	public String getActTypeID() {
		return actTypeID;
	}

	public void setActTypeID(String actTypeID) {
		this.actTypeID = actTypeID;
	}
	
	@ExcelField(title="排序序号", align=2, sort=3)
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@ExcelField(title="联系人编号", dictType="", align=2, sort=4)
	public String getContactID() {
		return contactID;
	}

	public void setContactID(String contactID) {
		this.contactID = contactID;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="跟进时间不能为空")
	@ExcelField(title="跟进时间", align=2, sort=5)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@ExcelField(title="跟进内容", align=2, sort=6)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@ExcelField(title="跟进人编号", align=2, sort=7)
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="记录时间", align=2, sort=8)
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	@ExcelField(title="跟进人", fieldType=User.class, value="activitieUser.name", align=2, sort=9)
	public User getActivitieUser() {
		return activitieUser;
	}

	public void setActivitieUser(User activitieUser) {
		this.activitieUser = activitieUser;
	}

	@ExcelField(title = "商机",fieldType = Opportunity.class,align = 2,sort = 10)
	public Opportunity getOpportunity(){
		return opportunity;
	}
	public void setOpportunity(Opportunity opportunity){this.opportunity=opportunity;}
}