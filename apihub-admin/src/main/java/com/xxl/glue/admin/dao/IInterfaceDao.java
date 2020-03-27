package com.xxl.glue.admin.dao;

import com.xxl.glue.admin.core.model.GlueInfo;
import com.xxl.glue.admin.core.model.Interface;
import com.xxl.glue.admin.core.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IInterfaceDao {

	public List<Interface> pageList(Map<String, Object> params);
	public int pageListCount(Map<String, Object> params);
	
	public int save(Interface interface1);

	public int update(Interface interface1);

	public int delete(int id);

	public List<Project> loadAll();

	public Interface findByAppname(String appname);

    public Interface load(int id);

}
