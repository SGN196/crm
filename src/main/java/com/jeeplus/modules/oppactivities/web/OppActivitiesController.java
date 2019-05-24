/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.oppactivities.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.jeeplus.modules.opportunity.entity.Opportunity;
import com.jeeplus.modules.opportunity.service.OpportunityService;
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
import com.jeeplus.modules.oppactivities.entity.OppActivities;
import com.jeeplus.modules.oppactivities.service.OppActivitiesService;

/**
 * 商机跟进Controller
 * @author Commit
 * @version 2019-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/oppactivities/oppActivities")
public class OppActivitiesController extends BaseController {

	@Autowired
	private OppActivitiesService oppActivitiesService;

	@Autowired
	private OpportunityService opportunityService;
	
	@ModelAttribute
	public OppActivities get(@RequestParam(required=false) String id) {
		OppActivities entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oppActivitiesService.get(id);
		}
		if (entity == null){
			entity = new OppActivities();
		}
		return entity;
	}
	
	/**
	 * 商机跟进列表页面
	 */
	@RequiresPermissions("oppactivities:oppActivities:list")
	@RequestMapping(value = {"list", ""})
	public String list(OppActivities oppActivities, Model model) {
		model.addAttribute("oppActivities", oppActivities);
		return "modules/oppactivities/oppActivitiesList";
	}
	
		/**
	 * 商机跟进列表数据
	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(OppActivities oppActivities, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OppActivities> page = oppActivitiesService.findPage(new Page<OppActivities>(request, response), oppActivities); 
		return getBootstrapData(page);
	}
	/**
	 * 商机跟进列表数据按时间倒序
	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:list")
	@RequestMapping(value = "orderByDate")
	public Map<String, Object> orderByDate(OppActivities oppActivities, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OppActivities> page = oppActivitiesService.findPageByDate(new Page<OppActivities>(request, response), oppActivities);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑商机跟进表单页面
	 */
	@RequiresPermissions(value={"oppactivities:oppActivities:view","oppactivities:oppActivities:add","oppactivities:oppActivities:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(OppActivities oppActivities, Model model) {
		Opportunity opportunity=opportunityService.get(oppActivities.getOppId());
		model.addAttribute("oppActivities", oppActivities);
		model.addAttribute("opportunity", opportunity);
		return "modules/oppactivities/oppActivitiesForm";
	}



	/**
	 * 保存商机跟进
	 */
	@ResponseBody
	@RequiresPermissions(value={"oppactivities:oppActivities:add","oppactivities:oppActivities:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(OppActivities oppActivities, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(oppActivities);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		oppActivitiesService.save(oppActivities);//保存
		j.setSuccess(true);
		j.setMsg("保存商机跟进成功");
		return j;
	}
	
	/**
	 * 删除商机跟进
	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(OppActivities oppActivities) {
		AjaxJson j = new AjaxJson();
		oppActivitiesService.delete(oppActivities);
		j.setMsg("删除商机跟进成功");
		return j;
	}
	
	/**
	 * 批量删除商机跟进
	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			oppActivitiesService.delete(oppActivitiesService.get(id));
		}
		j.setMsg("删除商机跟进成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(OppActivities oppActivities, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "商机跟进"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OppActivities> page = oppActivitiesService.findPage(new Page<OppActivities>(request, response, -1), oppActivities);
    		new ExportExcel("商机跟进", OppActivities.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出商机跟进记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<OppActivities> list = ei.getDataList(OppActivities.class);
			for (OppActivities oppActivities : list){
				try{
					oppActivitiesService.save(oppActivities);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条商机跟进记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条商机跟进记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入商机跟进失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入商机跟进数据模板
	 */
	@ResponseBody
	@RequiresPermissions("oppactivities:oppActivities:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "商机跟进数据导入模板.xlsx";
    		List<OppActivities> list = Lists.newArrayList(); 
    		new ExportExcel("商机跟进数据", OppActivities.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}