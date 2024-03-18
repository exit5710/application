package unitApp.verification.member.dao;

import config.component.Utils;
import config.database.ConnectionLocator;
import config.database.DatabaseLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unitApp.verification.member.vo.MemberVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ConnectionLocator connectionLocator;

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public MemberDao() {
		this.connectionLocator = new DatabaseLocator();
	}

	public static MemberDao getInstance() {
		return MemberDaoHelper.INSTANCE;
	}

	private static  class MemberDaoHelper {
		private static final MemberDao INSTANCE = new MemberDao();
	}

	// member 테이블 조회
	public List<MemberVo> selectMember() {
		logger.info("MemberDao selectMember /");

		List<MemberVo> list = new ArrayList<>();

		StringBuilder query = new StringBuilder();
		query.append("SELECT ID");
		query.append("       , NAME");
		query.append("       , GENDER");
		query.append("       , BIRTHDAY");
		query.append("       , BLOOD_TYPE");
		query.append("       , ETC");
		query.append("       , REGISTER_DATE");
		query.append("  FROM MEMBER");

		try {
			connection = connectionLocator.getConnection();
			ps = connection.prepareStatement(query.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String gender = rs.getString("GENDER");
				String birthday = rs.getString("BIRTHDAY");
				String bloodType = rs.getString("BLOOD_TYPE");
				String etc = rs.getString("ETC");
				String registerDate = rs.getString("REGISTER_DATE");

				list.add(new MemberVo(id, name, gender, birthday, bloodType, etc, registerDate));
			}
		} catch (SQLException e) {
			logger.error("Error selectMember: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}

		return list;
	}
}