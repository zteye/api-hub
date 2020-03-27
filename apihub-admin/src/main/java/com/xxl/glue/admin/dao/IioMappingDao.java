package com.xxl.glue.admin.dao;

import com.xxl.glue.admin.core.model.GlueInfo;
import com.xxl.glue.admin.core.model.IoMapping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IioMappingDao {
	
	public List<IoMapping> pageList(Map<String, Object> params);
	public int pageListCount(Map<String, Object> params);
	
	public int delete(int id);
	
	public int save(IoMapping ioMapping);
	
	public int update(IoMapping ioMapping);
	
	public IoMapping load(int id);

}
