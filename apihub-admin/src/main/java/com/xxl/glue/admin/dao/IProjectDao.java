package com.xxl.glue.admin.dao;

import com.xxl.glue.admin.core.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProjectDao {
	
	public int save(Project project);

	public int update(Project project);

	public int delete(int id);

	public List<Project> loadAll();

	public Project findByAppname(String appname);

    public Project load(int id);

}
