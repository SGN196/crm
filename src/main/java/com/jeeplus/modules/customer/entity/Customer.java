/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.customer.entity;

import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.modules.contacts.entity.Contacts;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.User;

import javax.validation.constraints.NotNull;

/**
 * 客户档案Entity
 * @author Vigny
 * @version 2019-05-09
 */
public class Customer extends DataEntity<Customer> {
	
	private static final long serialVersionUID = 1L;
	private String erpcusId;		// ERP系统客户Id
	private String orgId;		// 所属公司Id
	private String deptId;		// 所属部门Id
	private String empId;		// 业务员Id
	private String number;		// 客户代码
	private String name;		// 客户名称
	private Integer state;		// 使用状态
	private Integer isPublic;		// 是否公海
	private String sourceId;		// 客户来源Id
	private String statusId;		// 跟进状态Id
	private String areaId;		// 区域Id
	private String address;		// 客户地址
	private String contactId;		// 主联系人Id
	private String provincelId;		// 省份Id
	private String cityId;		// 城市Id
	private String countryId;		// 区县Id
	private String streetId;		// 镇街Id
	private String detailAddress;		// 详细地址
	private String gps;		// GPS定位
	private String shortName;		// 简称
	private String industoryId;		// 行业Id
	private Integer cusLevel;		// 客户等级
	private String fax;		// 传真
	private String webSite;		// 官网地址
	private String email;		// 官方邮箱
	private String bank;		// 开户行
	private String account;		// 银行账号
	private String propertyId;		// 企业性质Id
	private String creditCode;		// 统一信用代码
	private String business;		// 经营范围
	private String recent;		// 经营近况
	private String legalPerson;		// 客户法人
	private String usedName;		// 曾用名
	private String competitor;		// 竞争对手
	private String coreAdvantage;		// 核心优势
	private String industryPosition;		// 行业地位
	private String industryStandard;		// 行业标准
	private Double turnoverYearly;		// 年营业额
	private Double taxYearly;		// 年纳税额
	private Integer workerNum;		// 职工总人数
	private Integer researcherNum;		// 科研人员人数
	private Double rateA;		// 科研人员占比
	private Integer juniorNum;		// 大专科研人数
	private Integer undergraduateNum;		// 本科科研人数
	private Integer masterNum;		// 硕士科研人数
	private Integer primaryNum;		// 初职科研人数
	private Integer intermediateNum;		// 中职科研人数
	private Double intellectualFunds;		// 知识产权经费
	private Double rateB;		// 经费占比
	private Integer inventionPatentNum;		// 发明专利数量
	private Integer appearancePatentNum;		// 外观专利数量
	private Integer trademarkNum;		// 商标版权数量
	private Integer practicalPatentNum;		// 实用型专利数量
	private Contacts contacts;//联系人
	private Office office;//所属部门
	private User tuser;//销售人员

	
	public Customer() {
		super();
	}

	public Customer(String id){
		super(id);
	}

	@ExcelField(title="ERP系统客户Id", align=2, sort=1)
	public String getErpcusId() {
		return erpcusId;
	}

	public void setErpcusId(String erpcusId) {
		this.erpcusId = erpcusId;
	}
	
	@ExcelField(title="所属公司Id", align=2, sort=2)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@ExcelField(title="所属部门Id", align=2, sort=3)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@ExcelField(title="业务员Id", align=2, sort=4)
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@ExcelField(title="客户代码", align=2, sort=5)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@ExcelField(title="客户名称", align=2, sort=6)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="使用状态", align=2, sort=7)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@NotNull(message="是否公海不能为空")
	@ExcelField(title="是否公海", align=2, sort=8)
	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
	@ExcelField(title="客户来源Id", align=2, sort=9)
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@ExcelField(title="跟进状态Id", dictType="", align=2, sort=10)
	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	@ExcelField(title="区域Id", align=2, sort=11)
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	@ExcelField(title="客户地址", align=2, sort=12)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@ExcelField(title="主联系人Id", align=2, sort=13)
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	@ExcelField(title="省份Id", align=2, sort=14)
	public String getProvincelId() {
		return provincelId;
	}

	public void setProvincelId(String provincelId) {
		this.provincelId = provincelId;
	}
	
	@ExcelField(title="城市Id", align=2, sort=15)
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@ExcelField(title="区县Id", align=2, sort=16)
	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
	@ExcelField(title="镇街Id", align=2, sort=17)
	public String getStreetId() {
		return streetId;
	}

	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	
	@ExcelField(title="详细地址", align=2, sort=18)
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	@ExcelField(title="GPS定位", align=2, sort=19)
	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}
	
	@ExcelField(title="简称", align=2, sort=20)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@ExcelField(title="行业Id", dictType="", align=2, sort=21)
	public String getIndustoryId() {
		return industoryId;
	}

	public void setIndustoryId(String industoryId) {
		this.industoryId = industoryId;
	}
	
	@ExcelField(title="客户等级", dictType="", align=2, sort=22)
	public Integer getCusLevel() {
		return cusLevel;
	}

	public void setCusLevel(Integer cusLevel) {
		this.cusLevel = cusLevel;
	}
	
	@ExcelField(title="传真", align=2, sort=23)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@ExcelField(title="官网地址", align=2, sort=24)
	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
	@ExcelField(title="官方邮箱", align=2, sort=25)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ExcelField(title="开户行", align=2, sort=26)
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@ExcelField(title="银行账号", align=2, sort=27)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@ExcelField(title="企业性质Id", align=2, sort=28)
	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	
	@ExcelField(title="统一信用代码", align=2, sort=29)
	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	
	@ExcelField(title="经营范围", align=2, sort=30)
	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	
	@ExcelField(title="经营近况", align=2, sort=31)
	public String getRecent() {
		return recent;
	}

	public void setRecent(String recent) {
		this.recent = recent;
	}
	
	@ExcelField(title="客户法人", align=2, sort=32)
	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	@ExcelField(title="曾用名", align=2, sort=33)
	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}
	
	@ExcelField(title="竞争对手", align=2, sort=34)
	public String getCompetitor() {
		return competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	
	@ExcelField(title="核心优势", align=2, sort=35)
	public String getCoreAdvantage() {
		return coreAdvantage;
	}

	public void setCoreAdvantage(String coreAdvantage) {
		this.coreAdvantage = coreAdvantage;
	}
	
	@ExcelField(title="行业地位", align=2, sort=36)
	public String getIndustryPosition() {
		return industryPosition;
	}

	public void setIndustryPosition(String industryPosition) {
		this.industryPosition = industryPosition;
	}
	
	@ExcelField(title="行业标准", align=2, sort=37)
	public String getIndustryStandard() {
		return industryStandard;
	}

	public void setIndustryStandard(String industryStandard) {
		this.industryStandard = industryStandard;
	}
	
	@ExcelField(title="年营业额", align=2, sort=38)
	public Double getTurnoverYearly() {
		return turnoverYearly;
	}

	public void setTurnoverYearly(Double turnoverYearly) {
		this.turnoverYearly = turnoverYearly;
	}
	
	@ExcelField(title="年纳税额", align=2, sort=39)
	public Double getTaxYearly() {
		return taxYearly;
	}

	public void setTaxYearly(Double taxYearly) {
		this.taxYearly = taxYearly;
	}
	
	@ExcelField(title="职工总人数", align=2, sort=40)
	public Integer getWorkerNum() {
		return workerNum;
	}

	public void setWorkerNum(Integer workerNum) {
		this.workerNum = workerNum;
	}
	
	@ExcelField(title="科研人员人数", align=2, sort=41)
	public Integer getResearcherNum() {
		return researcherNum;
	}

	public void setResearcherNum(Integer researcherNum) {
		this.researcherNum = researcherNum;
	}
	
	@ExcelField(title="科研人员占比", align=2, sort=42)
	public Double getRateA() {
		return rateA;
	}

	public void setRateA(Double rateA) {
		this.rateA = rateA;
	}
	
	@ExcelField(title="大专科研人数", align=2, sort=43)
	public Integer getJuniorNum() {
		return juniorNum;
	}

	public void setJuniorNum(Integer juniorNum) {
		this.juniorNum = juniorNum;
	}
	
	@ExcelField(title="本科科研人数", align=2, sort=44)
	public Integer getUndergraduateNum() {
		return undergraduateNum;
	}

	public void setUndergraduateNum(Integer undergraduateNum) {
		this.undergraduateNum = undergraduateNum;
	}
	
	@ExcelField(title="硕士科研人数", align=2, sort=45)
	public Integer getMasterNum() {
		return masterNum;
	}

	public void setMasterNum(Integer masterNum) {
		this.masterNum = masterNum;
	}
	
	@ExcelField(title="初职科研人数", align=2, sort=46)
	public Integer getPrimaryNum() {
		return primaryNum;
	}

	public void setPrimaryNum(Integer primaryNum) {
		this.primaryNum = primaryNum;
	}
	
	@ExcelField(title="中职科研人数", align=2, sort=47)
	public Integer getIntermediateNum() {
		return intermediateNum;
	}

	public void setIntermediateNum(Integer intermediateNum) {
		this.intermediateNum = intermediateNum;
	}
	
	@ExcelField(title="知识产权经费", align=2, sort=48)
	public Double getIntellectualFunds() {
		return intellectualFunds;
	}

	public void setIntellectualFunds(Double intellectualFunds) {
		this.intellectualFunds = intellectualFunds;
	}
	
	@ExcelField(title="经费占比", align=2, sort=49)
	public Double getRateB() {
		return rateB;
	}

	public void setRateB(Double rateB) {
		this.rateB = rateB;
	}
	
	@ExcelField(title="发明专利数量", align=2, sort=50)
	public Integer getInventionPatentNum() {
		return inventionPatentNum;
	}

	public void setInventionPatentNum(Integer inventionPatentNum) {
		this.inventionPatentNum = inventionPatentNum;
	}
	
	@ExcelField(title="外观专利数量", align=2, sort=51)
	public Integer getAppearancePatentNum() {
		return appearancePatentNum;
	}

	public void setAppearancePatentNum(Integer appearancePatentNum) {
		this.appearancePatentNum = appearancePatentNum;
	}
	
	@ExcelField(title="商标版权数量", align=2, sort=52)
	public Integer getTrademarkNum() {
		return trademarkNum;
	}

	public void setTrademarkNum(Integer trademarkNum) {
		this.trademarkNum = trademarkNum;
	}
	
	@ExcelField(title="实用型专利数量", align=2, sort=53)
	public Integer getPracticalPatentNum() {
		return practicalPatentNum;
	}

	public void setPracticalPatentNum(Integer practicalPatentNum) {
		this.practicalPatentNum = practicalPatentNum;
	}

	@ExcelField(title="联系人", fieldType=Contacts.class, value="contacts.name", align=2, sort=54)
	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	/*@NotNull(message="归属部门不能为空")*/
	@ExcelField(title="归属部门", fieldType=Office.class, value="office.name", align=2, sort=55)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@ExcelField(title="销售人员", fieldType=User.class, value="tusers.name", align=2, sort=56)
	public User getTuser() {
		return tuser;
	}

	public void setTuser(User tuser) {
		this.tuser = tuser;
	}
	
}