package com.xxl.glue.admin.service.impl;

import com.xxl.glue.admin.core.model.CodeLog;
import com.xxl.glue.admin.core.model.GlueInfo;
import com.xxl.glue.admin.core.model.IoMapping;
import com.xxl.glue.admin.core.model.Project;
import com.xxl.glue.admin.core.result.ReturnT;
import com.xxl.glue.admin.dao.ICodeLogDao;
import com.xxl.glue.admin.dao.IGlueInfoDao;
import com.xxl.glue.admin.dao.IProjectDao;
import com.xxl.glue.admin.dao.IioMappingDao;
import com.xxl.glue.admin.service.IGlueInfoService;
import com.xxl.glue.admin.service.IioMappingService;
import com.xxl.glue.core.broadcast.XxlGlueBroadcaster;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class IoMappingServiceImpl implements IioMappingService {
	@Resource
	private IProjectDao projectDao;
	@Resource
	private IioMappingDao ioMappingDao;

	@Override
	public Map<String, Object> pageList(int offset, int pagesize, int projectId, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("projectId", projectId);
		params.put("name", name);

		List<IoMapping> list = ioMappingDao.pageList(params);
		int list_count = ioMappingDao.pageListCount(params);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordsTotal", list_count);		// 总记录数
		map.put("recordsFiltered", list_count);		// 过滤后的总记录数
	    map.put("data", list);  					// 数据
	    
		return map;
	}

	@Override
	public ReturnT<String> delete(int id) {
		// valid
		IoMapping ioMapping = ioMappingDao.load(id);
		if (ioMapping==null) {
			return new ReturnT<String>(500, "“删除失败,ioMapping”不存在");
		}
		// delete
		int ret = ioMappingDao.delete(id);
		if (ret < 1) {
			return new ReturnT<String>(500, "删除失败");
		}
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> add(IoMapping ioMapping) {
		// valid


		// project
		Project project = projectDao.load(ioMapping.getProjectId());
		if (project == null){
			return new ReturnT<String>(500, "所属项目ID非法");
		}

		// save
		int ret = ioMappingDao.save(ioMapping);
		if (ret < 1) {
			return new ReturnT<String>(500, "新增失败");
		}
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> update(IoMapping ioMapping) {

		IoMapping oldIoMapping = ioMappingDao.load(ioMapping.getId());
		if (oldIoMapping == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "更新失败，GLUE记录不存在");
		}

		// update base info
		int ret = ioMappingDao.update(ioMapping);

		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@Override
	public IoMapping load(int id) {
		return ioMappingDao.load(id);
	}




}
