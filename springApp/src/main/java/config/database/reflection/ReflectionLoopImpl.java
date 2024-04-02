package config.database.reflection;

import unitApp.verification.fetcher.vo.ValidateVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ReflectionLoopImpl implements ReflectionLoop {
	@Override
	public void loop(ResultSet rs, List<ValidateVo> list) throws SQLException {
		while (rs.next()) {
			String originalContent = rs.getString("ORIGINAL_CONTENT");
			String regexpContent = rs.getString("REGEXP_CONTENT");

			list.add(new ValidateVo(originalContent, regexpContent));
		}
	}

	@Override
	public void loopMap(ResultSet rs, List<Map<String, Object>> list) throws SQLException {
		while (rs.next()) {
			String originalContent = rs.getString("ORIGINAL_CONTENT");
			String regexpContent = rs.getString("REGEXP_CONTENT");

			Map<String, Object> map = new HashMap<>();
			map.put("originalContent", originalContent);
			map.put("regexpContent", regexpContent);

			list.add(map);
		}
	}

	@Override
	public void memberList(ResultSet rs, List<Map<String, Object>> list) throws SQLException {
		while (rs.next()) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", rs.getString("ID"));
			map.put("name", rs.getString("NAME"));

			list.add(map);
		}
	}
}