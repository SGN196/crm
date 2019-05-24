/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.opportunity.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.customer.entity.Customer;
import com.jeeplus.modules.oppactivities.entity.OppActivities;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.User;

/**
 * 线索商机管理列表Entity
 * @author Commit
 * @version 2019-05-09
 */
public class Opportunity extends DataEntity<Opportunity> {
	
	private static final long serialVersionUID = 1L;
	private String oppNo;		 // 商机编号
	private String oppName;		 // 商机名称
	private String sourceId;	 // 商机来源编号
	private String custId;		 // 客户编号
	private String status;		 // 商机状态
	private String orgId;		 // 所属公司编号
	private String deptId;		 // 所属部门编号
	private String empId;		 // 跟进人员编号
	private String oppStageId;	 // 商机阶段编号
	private Double budget;		 // 资金预算
	private Integer commit;		 // 是否立项
	private String demand;		 // 需求描述
	private String decisionmaker;// 决策人
	private Date planDate;		 // 预计成交日期
	private String process;		 // 决策流程
	private String factors;		 // 决策因素
	private String competitor;	 // 竞争对手
	private Date lastTime;		 // 上次跟进时间
	private Integer deayTimes;	 // 累计延时申请次数
	private Customer customer;//客户
	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private User oppUser;//跟进人

	
	public Opportunity() {
		super();
	}

	public Opportunity(String id){
		super(id);
	}

	@ExcelField(title="商机编号", align=2, sort=1)
	public String getOppNo() {
		return oppNo;
	}

	public void setOppNo(String oppNo) {
		this.oppNo = oppNo;
	}
	
	@ExcelField(title="商机名称", align=2, sort=2)
	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}
	
	@ExcelField(title="商机来源编号", align=2, sort=3)
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@ExcelField(title="客户编号", align=2, sort=4)
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	@ExcelField(title="商机状态", align=2, sort=5)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@ExcelField(title="所属公司编号", align=2, sort=6)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@ExcelField(title="所属部门编号", align=2, sort=7)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@ExcelField(title="跟进人员编号", align=2, sort=8)
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@ExcelField(title="商机阶段编号", align=2, sort=9)
	public String getOppStageId() {
		return oppStageId;
	}

	public void setOppStageId(String oppStageId) {
		this.oppStageId = oppStageId;
	}
	
	@NotNull(message="资金预算不能为空")
	@ExcelField(title="资金预算", align=2, sort=10)
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}
	
	@NotNull(message="是否立项不能为空")
	@ExcelField(title="是否立项", align=2, sort=11)
	public Integer getCommit() {
		return commit;
	}

	public void setCommit(Integer commit) {
		this.commit = commit;
	}
	
	@ExcelField(title="需求描述", align=2, sort=12)
	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}
	
	@ExcelField(title="决策人", align=2, sort=13)
	public String getDecisionmaker() {
		return decisionmaker;
	}

	public void setDecisionmaker(String decisionmaker) {
		this.decisionmaker = decisionmaker;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="预计成交日期", align=2, sort=14)
	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	
	@ExcelField(title="决策流程", align=2, sort=15)
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	@ExcelField(title="决策因素", align=2, sort=16)
	public String getFactors() {
		return factors;
	}

	public void setFactors(String factors) {
		this.factors = factors;
	}
	
	@ExcelField(title="竞争对手", align=2, sort=17)
	public String getCompetitor() {
		return competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="上次跟进时间", align=2, sort=18)
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	@NotNull(message="累计延时申请次数不能为空")
	@ExcelField(title="累计延时申请次数", align=2, sort=19)
	public Integer getDeayTimes() {
		return deayTimes;
	}

	public void setDeayTimes(Integer deayTimes) {
		this.deayTimes = deayTimes;
	}

	@ExcelField(title="所属客户", fieldType=Customer.class, value="customer.name", align=2, sort=20)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@NotNull(message="归属公司不能为空")
	@ExcelField(title="归属公司", align=2, sort=21)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=22)
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}

	@ExcelField(title="跟进人", fieldType=User.class, align=2, sort=23)
	public User getOppUser() { return oppUser; }
	public void setOppUser(User oppUser) {
		this.oppUser = oppUser;
	}

}