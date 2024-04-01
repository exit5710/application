package config.database.reflection;

import unitApp.verification.fetcher.vo.ValidateVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public interface ReflectionLoop {
	// loop
	void loop(ResultSet rs, List<ValidateVo> list) throws SQLException;

	// loopMap
	void loopMap(ResultSet rs, List<Map<String, Object>> list) throws SQLException;

	// memberList
	void memberList(ResultSet rs, List<Map<String, Object>> list) throws  SQLException;
}