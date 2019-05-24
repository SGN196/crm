/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.contacts.web;

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
import com.jeeplus.modules.contacts.utils.PropertyConfigUtil;
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
import java.lang.reflect.Field;


/**
 * 客户联系人Controller
 * @author Vigny
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/contacts/contacts")
public class ContactsController extends BaseController {

	@Autowired
	private ContactsService contactsService;

	private static PropertyConfigUtil property = PropertyConfigUtil.getInstance("properties/config.properties");
	
	@ModelAttribute
	public Contacts get(@RequestParam(required=false) String id) {
		Contacts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contactsService.get(id);
		}
		if (entity == null){
			entity = new Contacts();
		}
		return entity;
	}


	/**
	 * 客户联系人列表页面
	 */
	@RequiresPermissions("contacts:contacts:list")
	@RequestMapping(value = {"list", ""})
	public String list(Contacts contacts, Model model) {
		model.addAttribute("contacts", contacts);
		return "modules/contacts/contactsList";
	}
	
		/**
	 * 客户联系人列表数据
	 */
	@ResponseBody
	@RequiresPermissions("contacts:contacts:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Contacts contacts, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Contacts> page = contactsService.findPage(new Page<Contacts>(request, response), contacts); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑客户联系人表单页面
	 */
	@RequiresPermissions(value={"contacts:contacts:view","contacts:contacts:add","contacts:contacts:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Contacts contacts, Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id == null || id == "") {
			model.addAttribute("checked", true);
		}
		model.addAttribute("id", id);
		model.addAttribute("contacts", contacts);
		return "modules/contacts/contactsForm";
	}

	/**
	 * 保存客户联系人
	 */
	@ResponseBody
	@RequiresPermissions(value={"contacts:contacts:add","contacts:contacts:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Contacts contacts, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(contacts);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		int sum = 0;
		if (contacts!=null){
			Class cls =contacts.getClass();
			Field[] fields = cls.getDeclaredFields();
			List<String> list=property.printAllProperty();
			System.out.println(list.size());
			for(int i=0; i<fields.length; i++){
				Field f = fields[i];
				f.setAccessible(true);
				if (f.get(contacts)!=null){
					for (int k =0;k<list.size();k++) {
							if (list.get(k).equals(f.getName())) {
								int degree = Integer.parseInt(property.getValue(f.getName()));
								sum += degree;
							}

					}
				}
			}
			System.out.println(sum+"%");
		}
		//新增或编辑表单保存
		contactsService.save(contacts);//保存
		j.setSuccess(true);
		j.setMsg("保存客户联系人成功");
		return j;
	}

	
	/**
	 * 删除客户联系人
	 */
	@ResponseBody
	@RequiresPermissions("contacts:contacts:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Contacts contacts) {
		AjaxJson j = new AjaxJson();
		contactsService.delete(contacts);
		j.setMsg("删除客户联系人成功");
		return j;
	}
	
	/**
	 * 批量删除客户联系人
	 */
	@ResponseBody
	@RequiresPermissions("contacts:contacts:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			contactsService.delete(contactsService.get(id));
		}
		j.setMsg("删除客户联系人成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("contacts:contacts:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Contacts contacts, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户联系人"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Contacts> page = contactsService.findPage(new Page<Contacts>(request, response, -1), contacts);
    		new ExportExcel("客户联系人", Contacts.class).setDataList(page.getList()).write(response, fileName).dispose();
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
	@RequiresPermissions("contacts:contacts:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Contacts> list = ei.getDataList(Contacts.class);
			for (Contacts contacts : list){
				try{
					contactsService.save(contacts);
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
	@RequiresPermissions("contacts:contacts:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户联系人数据导入模板.xlsx";
    		List<Contacts> list = Lists.newArrayList(); 
    		new ExportExcel("客户联系人数据", Contacts.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}