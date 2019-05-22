/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.customer.web;

import com.google.common.collect.Lists;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.contacts.entity.Contacts;
import com.jeeplus.modules.contacts.service.ContactsService;
import com.jeeplus.modules.customer.entity.Customer;
import com.jeeplus.modules.customer.service.CustomerService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 客户档案Controller
 * @author Vigny
 * @version 2019-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/customer/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ContactsService contactsService;
	@ModelAttribute
	public Customer get(@RequestParam(required=false) String id) {
		Customer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = customerService.get(id);
		}
		if (entity == null){
			entity = new Customer();
		}
		return entity;
	}
	
	/**
	 * 客户档案列表页面
	 */
	@RequiresPermissions("customer:customer:list")
	@RequestMapping(value = {"list", ""})
	public String list(Customer customer, Model model) {
		model.addAttribute("customer", customer);
		return "modules/customer/customerList";
	}
	
		/**
	 * 客户档案列表数据
	 */
	@ResponseBody
	@RequiresPermissions("customer:customer:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Customer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Customer> page = customerService.findPage(new Page<Customer>(request, response), customer); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑客户档案表单页面
	 */
	@RequiresPermissions(value={"customer:customer:view","customer:customer:add","customer:customer:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Customer customer, Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id == null || id == "") {
			model.addAttribute("checked", true);
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			int day = cale.get(Calendar.DAY_OF_MONTH);
			Random random = new Random();
			int twoPlace = random.nextInt(99);
			String two = String.format("%02d", twoPlace);
			int threePlace = random.nextInt(999);
			String three = String.format("%02d", threePlace);
			customer.setNumber("CUS" + year + "-" + month + "-" + day + "-" + two + "-" + three);
		}
		Contacts contacts =new Contacts();
		contacts.setCustomer(customer);
		List<Contacts>  findList = contactsService.findList(contacts);
		System.out.println(findList);
		model.addAttribute("findList", findList);
		model.addAttribute("customer", customer);
		return "modules/customer/customerForm";
	}

	/**
	 * 保存客户档案
	 */
	@ResponseBody
	@RequiresPermissions(value={"customer:customer:add","customer:customer:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Customer customer, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(customer);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		customerService.save(customer);//保存
		j.setSuccess(true);
		j.setMsg("保存客户档案成功");
		return j;
	}
	
	/**
	 * 删除客户档案
	 */
	@ResponseBody
	@RequiresPermissions("customer:customer:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Customer customer) {
		AjaxJson j = new AjaxJson();
		customerService.delete(customer);
		j.setMsg("删除客户档案成功");
		return j;
	}
	
	/**
	 * 批量删除客户档案
	 */
	@ResponseBody
	@RequiresPermissions("customer:customer:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			customerService.delete(customerService.get(id));
		}
		j.setMsg("删除客户档案成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("customer:customer:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Customer customer, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户档案"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Customer> page = customerService.findPage(new Page<Customer>(request, response, -1), customer);
    		new ExportExcel("客户档案", Customer.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出客户档案记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("customer:customer:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Customer> list = ei.getDataList(Customer.class);
			for (Customer customer : list){
				try{
					customerService.save(customer);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条客户档案记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条客户档案记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入客户档案失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入客户档案数据模板
	 */
	@ResponseBody
	@RequiresPermissions("customer:customer:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户档案数据导入模板.xlsx";
    		List<Customer> list = Lists.newArrayList(); 
    		new ExportExcel("客户档案数据", Customer.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}