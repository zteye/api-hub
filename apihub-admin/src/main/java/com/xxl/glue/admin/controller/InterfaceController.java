package com.xxl.glue.admin.controller;

import com.xxl.glue.admin.core.model.CodeLog;
import com.xxl.glue.admin.core.model.GlueInfo;
import com.xxl.glue.admin.core.model.Interface;
import com.xxl.glue.admin.core.model.Project;
import com.xxl.glue.admin.core.result.ReturnT;
import com.xxl.glue.admin.dao.IInterfaceDao;
import com.xxl.glue.admin.dao.IProjectDao;
import com.xxl.glue.admin.service.IGlueInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/interface")
public class InterfaceController {
	
	@Resource
	private IInterfaceDao iInterfaceDao;
	@Resource
	private IProjectDao projectDao;

	@RequestMapping
	public String index(Model model){

		List<Project> projectList = projectDao.loadAll();
		model.addAttribute("projectList", projectList);

		return "interface/interface.list";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, int projectId, String name){

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", start);
		params.put("pagesize", length);
		params.put("projectId", projectId);
		params.put("name", name);

		List<Interface> list = iInterfaceDao.pageList(params);
		int list_count = iInterfaceDao.pageListCount(params);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordsTotal", list_count);		// 总记录数
		map.put("recordsFiltered", list_count);		// 过滤后的总记录数
		map.put("data", list);  					// 数据


		return map;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id){
		// valid
		Interface interface1 = iInterfaceDao.load(id);
		if (interface1==null) {
			return new ReturnT<String>(500, "“删除失败,interface”不存在");
		}
		// delete
		int ret = iInterfaceDao.delete(id);
		if (ret < 1) {
			return new ReturnT<String>(500, "删除失败");
		}
		iInterfaceDao.delete(id);

		return ReturnT.SUCCESS;


	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(Interface interface1){
		// valid
		if (StringUtils.isBlank(interface1.getCode())) {
			return new ReturnT<String>(500, "“Glue名称”不可为空");
		}
		if (StringUtils.isBlank(interface1.getName())) {
			return new ReturnT<String>(500, "“Glue描述”不可为空");
		}

		// project
		Project project = projectDao.load(interface1.getProjectId());
		if (project == null){
			return new ReturnT<String>(500, "所属项目ID非法");
		}



		// parse final name
		String finalName = project.getAppname().concat(".").concat(interface1.getName());
		interface1.setName(finalName);

		// save
		int ret = iInterfaceDao.save(interface1);
		if (ret < 1) {
			return new ReturnT<String>(500, "新增失败");
		}
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(Interface interface1){
		Interface oldInterface = iInterfaceDao.load(interface1.getId());
		if (oldInterface == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "更新失败，接口记录不存在");
		}

		// update base info
		int ret = iInterfaceDao.update(interface1);

		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}


}
