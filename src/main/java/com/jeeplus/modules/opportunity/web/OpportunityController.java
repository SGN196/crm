/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.opportunity.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.opportunity.entity.Opportunity;
import com.jeeplus.modules.opportunity.service.OpportunityService;

/**
 * 线索商机管理列表Controller
 * @author Commit
 * @version 2019-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/opportunity/opportunity")
public class OpportunityController extends BaseController {

	@Autowired
	private OpportunityService opportunityService;
	
	@ModelAttribute
	public Opportunity get(@RequestParam(required=false) String id) {
		Opportunity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = opportunityService.get(id);
		}
		if (entity == null){
			entity = new Opportunity();
		}
		return entity;
	}
	
	/**
	 * 线索商机列表页面
	 */
	@RequiresPermissions("opportunity:opportunity:list")
	@RequestMapping(value = {"list", ""})
	public String list(Opportunity opportunity, Model model) {
		model.addAttribute("opportunity", opportunity);
		return "modules/opportunity/opportunityList";
	}
	
		/**
	 * 线索商机列表数据
	 */
	@ResponseBody
	@RequiresPermissions("opportunity:opportunity:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Opportunity opportunity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Opportunity> page = opportunityService.findPage(new Page<Opportunity>(request, response), opportunity); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑线索商机表单页面
	 */
	@RequiresPermissions(value={"opportunity:opportunity:view","opportunity:opportunity:add","opportunity:opportunity:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Opportunity opportunity, Model model) {
		model.addAttribute("opportunity", opportunity);
		return "modules/opportunity/opportunityForm";
	}

	/**
	 * 增加，编辑商机跟进记录表单页面
	 */
	//@RequiresPermissions(value={"opportunity:opportunity:view","opportunity:opportunity:add","opportunity:opportunity:edit"},logical=Logical.OR)
	@RequestMapping(value = "followForm")
	public String followForm(Opportunity opportunity, Model model) {
		model.addAttribute("opportunity", opportunity);
		return "modules/opportunity/followForm";
	}

	/**
	 * 商机跟进记录列表页面
	 * @param opportunity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "followList")
	public String followList(Opportunity opportunity, Model model) {
		model.addAttribute("opportunity", opportunity);
		return "modules/opportunity/followRecordList";
	}

	/**
	 * 保存线索商机
	 */
	@ResponseBody
	@RequiresPermissions(value={"opportunity:opportunity:add","opportunity:opportunity:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Opportunity opportunity, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(opportunity);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		opportunityService.save(opportunity);//保存
		j.setSuccess(true);
		j.setMsg("保存线索商机成功");
		return j;
	}
	
	/**
	 * 删除线索商机
	 */
	@ResponseBody
	@RequiresPermissions("opportunity:opportunity:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Opportunity opportunity) {
		AjaxJson j = new AjaxJson();
		opportunityService.delete(opportunity);
		j.setMsg("删除线索商机成功");
		return j;
	}
	
	/**
	 * 批量删除线索商机
	 */
	@ResponseBody
	@RequiresPermissions("opportunity:opportunity:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			opportunityService.delete(opportunityService.get(id));
		}
		j.setMsg("删除线索商机成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("opportunity:opportunity:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Opportunity opportunity, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "线索商机"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Opportunity> page = opportunityService.findPage(new Page<Opportunity>(request, response, -1), opportunity);
    		new ExportExcel("线索商机", Opportunity.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出线索商机记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("opportunity:opportunity:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Opportunity> list = ei.getDataList(Opportunity.class);
			for (Opportunity opportunity : list){
				try{
					opportunityService.save(opportunity);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条线索商机记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条线索商机记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入线索商机失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入线索商机数据模板
	 */
	@ResponseBody
	@RequiresPermissions("opportunity:opportunity:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "线索商机数据导入模板.xlsx";
    		List<Opportunity> list = Lists.newArrayList(); 
    		new ExportExcel("线索商机数据", Opportunity.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}