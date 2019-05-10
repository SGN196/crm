/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.contact.web;

import com.google.common.collect.Lists;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.contact.entity.Contact;
import com.jeeplus.modules.contact.service.ContactService;
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
import java.util.List;
import java.util.Map;

/**
 * 客户联系人Controller
 * @author Vigny
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/contact/contact")
public class ContactController extends BaseController {

	@Autowired
	private ContactService contactService;
	
	@ModelAttribute
	public Contact get(@RequestParam(required=false) String id) {
		Contact entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contactService.get(id);
		}
		if (entity == null){
			entity = new Contact();
		}
		return entity;
	}
	
	/**
	 * 客户联系人列表页面
	 */
	@RequiresPermissions("contact:contact:list")
	@RequestMapping(value = {"list", ""})
	public String list(Contact contact, Model model) {
		model.addAttribute("contact", contact);
		return "modules/contact/contactList";
	}
	
		/**
	 * 客户联系人列表数据
	 */
	@ResponseBody
	@RequiresPermissions("contact:contact:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Contact contact, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Contact> page = contactService.findPage(new Page<Contact>(request, response), contact); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑客户联系人表单页面
	 */
	@RequiresPermissions(value={"contact:contact:view","contact:contact:add","contact:contact:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Contact contact, Model model) {
		model.addAttribute("contact", contact);
		return "modules/contact/contactForm";
	}

	/**
	 * 保存客户联系人
	 */
	@ResponseBody
	@RequiresPermissions(value={"contact:contact:add","contact:contact:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Contact contact, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(contact);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		contactService.save(contact);//保存
		j.setSuccess(true);
		j.setMsg("保存客户联系人成功");
		return j;
	}
	
	/**
	 * 删除客户联系人
	 */
	@ResponseBody
	@RequiresPermissions("contact:contact:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Contact contact) {
		AjaxJson j = new AjaxJson();
		contactService.delete(contact);
		j.setMsg("删除客户联系人成功");
		return j;
	}
	
	/**
	 * 批量删除客户联系人
	 */
	@ResponseBody
	@RequiresPermissions("contact:contact:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			contactService.delete(contactService.get(id));
		}
		j.setMsg("删除客户联系人成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("contact:contact:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Contact contact, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户联系人"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Contact> page = contactService.findPage(new Page<Contact>(request, response, -1), contact);
    		new ExportExcel("客户联系人", Contact.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出客户联系人记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("contact:contact:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Contact> list = ei.getDataList(Contact.class);
			for (Contact contact : list){
				try{
					contactService.save(contact);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条客户联系人记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条客户联系人记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入客户联系人失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入客户联系人数据模板
	 */
	@ResponseBody
	@RequiresPermissions("contact:contact:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户联系人数据导入模板.xlsx";
    		List<Contact> list = Lists.newArrayList(); 
    		new ExportExcel("客户联系人数据", Contact.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}