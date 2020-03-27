package com.xxl.glue.admin.service;

import com.xxl.glue.admin.core.model.IoMapping;
import com.xxl.glue.admin.core.result.ReturnT;
import java.util.Map;

public interface IioMappingService {

	public Map<String, Object> pageList(int offset, int pagesize, int projectId, String name);
	
	public ReturnT<String> delete(int id);
	
	public ReturnT<String> add(IoMapping ioMapping);

	ReturnT<String> update(IoMapping ioMapping);

	public IoMapping load(int id);


}
