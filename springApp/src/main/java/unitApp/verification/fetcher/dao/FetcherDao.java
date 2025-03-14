package unitApp.verification.fetcher.dao;

import config.component.Utils;
import config.database.ConnectionLocator;
import config.database.DatabaseLocator;
import config.database.reflection.ReflectionConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unitApp.verification.fetcher.vo.TableVo;
import unitApp.verification.fetcher.vo.ValidateVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetcherDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ConnectionLocator validate;

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	private FetcherDao() {
		this.validate = new DatabaseLocator("validate");
	}

	public static FetcherDao getInstance() {
		return FetcherDaoHelper.INSTANCE;
	}

	private static class FetcherDaoHelper {
		private static final FetcherDao INSTANCE = new FetcherDao();
	}

	// 데이터베이스의 모든 테이블 조회
	public List<TableVo> getTableSchemaList(String tableSchema) {
		logger.info("FetcherDao getTableSchemaList: {}", tableSchema);

		List<TableVo> list = new ArrayList<>();

		StringBuilder query = new StringBuilder();
		query.append("SELECT TABLE_NAME");
		query.append("  FROM INFORMATION_SCHEMA.TABLES");
		query.append(" WHERE TABLE_SCHEMA = ?");

		try {
			connection = ReflectionConnector.getConnection(tableSchema);
			ps = connection.prepareStatement(query.toString());
			ps.setString(1, tableSchema);
			rs = ps.executeQuery();

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");

				list.add(new TableVo(tableName));
			}
		} catch (SQLException e) {
			logger.error("Error getAllTableList: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}

		return list;
	}

	// 테이블 생성 스크립트 조회
	public String getCreateTable(String tableName, String targetSchema) {
		logger.info("FetcherDao getCreateTable: {}, {}", tableName, targetSchema);

		String createQuery = null;
		StringBuilder query = new StringBuilder();
		query.append("SHOW CREATE TABLE ");
		query.append(tableName);

		try {
			connection = ReflectionConnector.getConnection(targetSchema);
			ps = connection.prepareStatement(query.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				createQuery = rs.getString("CREATE TABLE");
			}
		} catch (SQLException e) {
			logger.error("Error getCreateTable: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}

		return createQuery;
	}

	// executeQuery
	public void executeQuery(String executeQuery, String targetSchema) {
		logger.info("FetcherDao void executeQuery /");

		try {
			connection = ReflectionConnector.getConnection(targetSchema);
			ps = connection.prepareStatement(executeQuery);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error String String executeQuery: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}
	}

	// executeQuery
	public String executeQuery(String executeQuery) {
		logger.info("FetcherDao String executeQuery /");
		String message = "";

		try {
			connection = validate.getConnection();
			ps = connection.prepareStatement(executeQuery);
			ps.executeQuery();
		} catch (SQLException e) {
			message = e.getMessage();
			logger.error("Error String executeQuery: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}

		return message;
	}

	// executeQuery
	public void executeQuery(ValidateVo vo) {
		logger.info("FetcherDao String executeQuery /");

		try {
			connection = validate.getConnection();
			ps = connection.prepareStatement(vo.getRegexpContent());
			ps.executeQuery();

			vo.setVerify("true");
		} catch (SQLException e) {
			logger.error("Error ValidateVo executeQuery: {}", e.getMessage());
			vo.setVerify("false");
			vo.setErrorMessage(e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}
	}

	// 모든 테이블 Drop
	public void allTableDrop(String tableSchema) {
		logger.info("FetcherDao allTableDrop: {}", tableSchema);

		StringBuilder query = new StringBuilder();
		query.append("SHOW TABLES");

		try {
			connection = ReflectionConnector.getConnection(tableSchema);
			ps = connection.prepareStatement(query.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				String tableName = rs.getString(1);

				query.setLength(0);
				query.append("DROP TABLE IF EXISTS ");
				query.append(tableName);

				ps = connection.prepareStatement(query.toString());
				ps.executeQuery();
			}
		} catch (SQLException e) {
			logger.error("Error executeQuery: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}
	}

	// 모든 Function Drop
	public void allFunctionDrop(String routineSchema) {
		logger.info("FetcherDao allFunctionDrop: {}", routineSchema);

		StringBuilder query = new StringBuilder();
		query.append("SELECT SPECIFIC_NAME");
		query.append("  FROM INFORMATION_SCHEMA.ROUTINES");
		query.append(" WHERE ROUTINE_TYPE = 'FUNCTION'");
		query.append("   AND ROUTINE_SCHEMA = ?");

		try {
			connection = ReflectionConnector.getConnection(routineSchema);
			ps = connection.prepareStatement(query.toString());
			ps.setString(1, routineSchema);
			rs = ps.executeQuery();

			while (rs.next()) {
				String functionName = rs.getString(1);

				query.setLength(0);
				query.append("DROP FUNCTION IF EXISTS ");
				query.append(functionName);

				ps = connection.prepareStatement(query.toString());
				ps.executeQuery();
			}
		} catch (SQLException e) {
			logger.error("Error executeQuery: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}
	}

	// VALIDATE 테이블 clear
	public void validateClear() {
		logger.info("FetcherDao validateClear /");

		StringBuilder query = new StringBuilder();

		query.append("DELETE");
		query.append("  FROM VALIDATE");

		try {
			connection = validate.getConnection();
			ps = connection.prepareStatement(query.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error validateClear: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}
	}

	// parsing db 저장
	public void insertPatternizerList(ValidateVo vo) {
		logger.info("FetcherDao insertPatternizerList /");

		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO VALIDATE (FILE_PATH, ID, ORIGINAL_CONTENT, REGEXP_CONTENT, ERROR_MESSAGE, VERIFY)");
		query.append(" VALUES ");
		query.append("(?, ?, ?, ?, ?, ?)");

		try {
			connection = validate.getConnection();
			ps = connection.prepareStatement(query.toString());
			ps.setString(1, vo.getFilePath());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getOriginalContent());
			ps.setString(4, vo.getRegexpContent());
			ps.setString(5, vo.getErrorMessage());
			ps.setString(6, vo.getVerify());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error insertPatternizerList: {}", e.getMessage());
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}
	}

	// 데이터베이스의 모든 테이블 조회
	/*
	public List<TableVo> getAllTableList(String tableSchema) {
		logger.info("FetcherDao getAllTableList: {}", tableSchema);

		List<TableVo> list = new ArrayList<>();

		StringBuilder query = new StringBuilder();
		query.append("SELECT TABLE_NAME");
		query.append("  FROM INFORMATION_SCHEMA.TABLES");
		query.append(" WHERE TABLE_SCHEMA = ?");

		try {
			connection = connectionLocator.getConnection();
			ps = connection.prepareStatement(query.toString());
			ps.setString(1, tableSchema);
			rs = ps.executeQuery();

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");

				list.add(new TableVo(tableName));
			}
		} catch (SQLException | ClassNotFoundException | IOException e) {
			logger.error("Error getAllTableList: {}", e.getMessage());
		} finally {
			Util.databaseClose(rs, ps, connection);
		}

		return list;
	}
	*/
}