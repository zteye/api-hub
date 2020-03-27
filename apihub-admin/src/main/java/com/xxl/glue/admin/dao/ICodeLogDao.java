package com.xxl.glue.admin.dao;

import com.xxl.glue.admin.core.model.CodeLog;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICodeLogDao {
	
	public int save(CodeLog codeLog);
	
	public List<CodeLog> findByGlueId(int glueId);

	public int removeOldLogs(int glueId);

	public int delete(int glueId);
	
}
