package com.xxl.glue.admin.controller;

import com.xxl.glue.admin.core.model.CodeLog;
import com.xxl.glue.admin.core.model.GlueInfo;
import com.xxl.glue.admin.core.model.IoMapping;
import com.xxl.glue.admin.core.model.Project;
import com.xxl.glue.admin.core.result.ReturnT;
import com.xxl.glue.admin.dao.IProjectDao;
import com.xxl.glue.admin.service.IGlueInfoService;
import com.xxl.glue.admin.service.IioMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/iomapping")
public class IoMappingController {
	
	@Resource
	private IioMappingService ioMappingService;
	@Resource
	private IProjectDao projectDao;

	@RequestMapping
	public String index(Model model){

		List<Project> projectList = projectDao.loadAll();
		model.addAttribute("projectList", projectList);

		return "iomapping/iomapping.list";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, int projectId, String name){
		return ioMappingService.pageList(start, length, projectId, name);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id){
		return ioMappingService.delete(id);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(IoMapping ioMapping){
		return ioMappingService.add(ioMapping);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(IoMapping ioMapping){
		return ioMappingService.update(ioMapping);
	}


	@RequestMapping("/{type}/webide")
	public String codeSourceEditor(@PathVariable String type, Model model, int id){
		IoMapping ioMapping = ioMappingService.load(id);
		model.addAttribute("codeInfo", ioMapping);
		
		if (ioMapping!=null) {
			model.addAttribute("codeLogList", null);
		}
		if("input".equals(type)){
			return "iomapping/input.webide";
		}else{
			return "iomapping/output.webide";
		}
	}


}
