package unitApp.verification.fetcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unitApp.verification.fetcher.dao.FetcherDao;
import unitApp.verification.fetcher.vo.SqlTagMapperVo;
import unitApp.verification.fetcher.vo.TableVo;
import unitApp.verification.fetcher.vo.ValidateVo;

public class FetcherService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final FetcherDao fetcherDao;
	private final List<SqlTagMapperVo> sqlTagMapperList;

	private FetcherService() {
		this.fetcherDao = FetcherDao.getInstance();
		this.sqlTagMapperList = new ArrayList<>();
	}

	public static FetcherService getInstance() {
		return FetcherServiceHelper.INSTANCE;
	}

	private static class FetcherServiceHelper {
		private static final FetcherService INSTANCE = new FetcherService();
	}

	// validate 초기화
	public void initialize(String tableSchema) {
		logger.info("FetcherService initialize /");

		// 모든 테이블 drop
		allTableDrop(tableSchema);

		// 모든 function drop
		allFunctionDrop(tableSchema);

		// VALIDATE 생성 스크립트
		StringBuilder query = new StringBuilder();
		query.append("CREATE TABLE VALIDATE (");
		query.append("  FILE_PATH varchar(200) DEFAULT NULL,");
		query.append("  ID varchar(50) DEFAULT NULL,");
		query.append("  ORIGINAL_CONTENT varchar(4000) DEFAULT NULL,");
		query.append("  REGEXP_CONTENT varchar(4000) DEFAULT NULL,");
		query.append("  ERROR_MESSAGE varchar(4000) DEFAULT NULL,");
		query.append("  VERIFY varchar(5) DEFAULT NULL");
		query.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;");

		// VALIDATE 테이블 생성
		fetcherDao.executeQuery(query.toString(), tableSchema);
	}

	// 스키마 동기화 (원본 데이터 베이스, 대상 데이터 베이스)
	public void schemaSync(String sourceSchema, String targetSchema) {
		logger.info("FetcherService schemaSync: {}, {}", sourceSchema, targetSchema);

		// 원본 데이터 베이스의 모든 테이블 list
		List<TableVo> sourceSchemaList = fetcherDao.getTableSchemaList(sourceSchema);

		// 조회된 테이블의 생성 스크립트 list
		List<String> createScriptList = new ArrayList<>();

		// 조회된 테이블의 drop 쿼리 list
		List<String> dropTableList = new ArrayList<>();

		for (TableVo vo : sourceSchemaList) {
			String tableName = vo.getTableName();

			// 조회된 테이블 생성 스크립트 조회
			String createQuery = fetcherDao.getCreateTable(tableName, sourceSchema);
			createScriptList.add(createQuery);

			// 조회된 테이블의 drop 쿼리 list
			String dropTableQuery = "DROP TABLE IF EXISTS " + tableName;
			dropTableList.add(dropTableQuery);
		}

		// 타겟 데이터베이스에 drop 쿼리 실행
		for (String element : dropTableList) {
			fetcherDao.executeQuery(element, targetSchema);
		}

		// 타겟 데이터베이스에 생성 스크립트 실행
		for (String element : createScriptList) {
			fetcherDao.executeQuery(element, targetSchema);
		}
	}

	// 모든 테이블 drop
	public void allTableDrop(String tableSchema) {
		logger.info("FetcherService allTableDrop: {}", tableSchema);

		fetcherDao.allTableDrop(tableSchema);
	}

	// 모든 Function drop
	public void allFunctionDrop(String routineSchema) {
		logger.info("FetcherService allFunctionDrop: {}", routineSchema);

		fetcherDao.allFunctionDrop(routineSchema);
	}

	// function 생성
	public void functionCreate(String directoryPath) {
		logger.info("FetcherService functionCreate /");

		List<String> fileList = getFileList(directoryPath, ".sql");

		if (!fileList.isEmpty()) {
			for (String xmlFilePath : fileList) {
				try {
					byte[] fileBytes = Files.readAllBytes(Paths.get(xmlFilePath));
					String content = new String(fileBytes, Charset.forName("MS949"));
					// String content = new String(fileBytes, StandardCharsets.UTF_8);

					String errorMessage = fetcherDao.executeQuery(content);

					if (!"".equals(errorMessage)) {
						ValidateVo validateVo = new ValidateVo(xmlFilePath, content, errorMessage, "false");
						fetcherDao.insertPatternizerList(validateVo);
					}
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		} else {
			logger.warn("sql not found.");
		}
	}

	// MyBatis XML 쿼리 유효성 검사
	public void validateQueryXml(String directoryPath) {
		logger.info("FetcherService validateQueryXml /");

		// fileList
		List<String> fileList = getFileList(directoryPath, ".xml");

		// XML 파일을 문자열로 읽기
		List<ValidateVo> xmlStringList = xmlStringRead(fileList);

		// query 정규화 parsing
		List<ValidateVo> patternizerList = patternizer(xmlStringList);

		// include XML parsing
		includeParsing(patternizerList);

		// regexpContent executeQuery 실행
		for (ValidateVo vo : patternizerList) {
			fetcherDao.executeQuery(vo);
		}

		// parsing executeQuery 결과 db 저장
		for (ValidateVo vo : patternizerList) {
			fetcherDao.insertPatternizerList(vo);
		}
	}

	// fileList
	private List<String> getFileList(String directoryPath, String extension) {
		logger.info("FetcherService getFileList: {}", directoryPath);

		List<String> fileList = new ArrayList<>();

		collectFiles(new File(directoryPath), extension, fileList);

		if (".xml".equals(extension) && fileList.isEmpty()) {
			logger.error("XML files not found in directory: {}", directoryPath);
			throw new RuntimeException("XML files not found.");
		}

		return fileList;
	}

	// collectFiles
	private void collectFiles(File directory, String extension, List<String> fileList) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						collectFiles(file, extension, fileList);
					} else {
						if (file.getName().toLowerCase().endsWith(extension)) {
							fileList.add(file.getAbsolutePath().replace(File.separator, "/"));
						}
					}
				}
			}
		} else {
			logger.error("Directory not found: {}", directory.getAbsolutePath());
			throw new RuntimeException("Directory not found: " + directory.getAbsolutePath());
		}
	}

	// XML 파일을 문자열로 읽기
	private List<ValidateVo> xmlStringRead(List<String> fileList) {
		logger.info("FetcherService xmlStringRead /");

		List<ValidateVo> list = new ArrayList<>();

		for (String xmlFilePath : fileList) {
			try {
				String content = new String(Files.readAllBytes(Paths.get(xmlFilePath)));

				List<String> tagList = List.of("select", "insert", "update", "delete", "sql");
				for (String tag : tagList) {
					list.addAll(extractTags(content, tag, xmlFilePath));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

		return list;
	}

	// extractTags
	private List<ValidateVo> extractTags(String content, String tag, String xmlFilePath) {
		logger.info("FetcherService extractTags: {}", tag);

		List<ValidateVo> list = new ArrayList<>();

		Matcher matcher = Pattern.compile("<" + tag + ".*?id=\"(.*?)\".*?>(.*?)</" + tag + ">", Pattern.DOTALL).matcher(content);

		while (matcher.find()) {
			String id = matcher.group(1);
			String originalContent = matcher.group();

			// sql태그가 있는 xml의 sql 정보 가져오기
			if ("sql".equals(tag)) {
				getSqlContent(id, originalContent, content, xmlFilePath);
			} else {
				list.add(new ValidateVo(xmlFilePath, id, originalContent));
			}
		}

		return list;
	}

	// getSqlContent
	private void getSqlContent(String id, String originalContent, String content, String xmlFilePath) {
		Matcher matcher = Pattern.compile("<mapper.*?namespace=\"(.*?)\".*?>(.*?)</mapper>", Pattern.DOTALL).matcher(content);

		while (matcher.find()) {
			String namespace = matcher.group(1);
			String refid = namespace + "." + id;

			sqlTagMapperList.add(new SqlTagMapperVo(xmlFilePath, namespace, id, refid, originalContent));
		}
	}

	// query 정규화 parsing
	private List<ValidateVo> patternizer(List<ValidateVo> xmlParsingList) {
		logger.info("FetcherService patternizer /");

		for (ValidateVo vo : xmlParsingList) {
			String content = vo.getOriginalContent();
			String regexpContent = processRegexp(content);

			vo.setRegexpContent(regexpContent);
		}

		return xmlParsingList;
	}

	// include parsing
	private void includeParsing(List<ValidateVo> patternizerList) {
		for (ValidateVo validateVo : patternizerList) {
			while (validateVo.getRegexpContent().contains("include")) {
				validateVo.setRegexpContent(validateVo.getRegexpContent().replaceAll("<include refid=\"(\\w+)\"></include>", "<include refid=\"$1\"/>"));

				for (SqlTagMapperVo sqlTagMapperVo : sqlTagMapperList) {
					String regexpContent = validateVo.getRegexpContent();

					Matcher matcher = Pattern.compile("<include\\s+refid=\"(.*?)\".*?/\\s*>", Pattern.DOTALL).matcher(regexpContent);

					if (matcher.find()) {
						String refId = matcher.group(1);
						String content = matcher.group();

						if (refId.equals(sqlTagMapperVo.getId()) || refId.equals(sqlTagMapperVo.getRefid())) {
							regexpContent = regexpContent.replaceAll(content, sqlTagMapperVo.getContent());
							validateVo.setRegexpContent(regexpContent);
						}
					}
				}
			}

			validateVo.setRegexpContent(processRegexp(validateVo.getRegexpContent()));
		}
	}

	// processRegexp
	private String processRegexp(String data) {
		logger.info("FetcherService processRegexp /");

		// 주석 패턴 처리
		data = data.replaceAll("//.*", "");
		data = data.replaceAll("\\s+", " ");
		data = data.replaceAll("/\\*.*?\\*/", "");
		data = data.replaceAll("<!--[^>]*-->", "");

		// sql 태그 패턴 처리
		data = data.replaceAll("<select[^>]*>|</select>", "");
		data = data.replaceAll("<insert[^>]*>|</insert>", "");
		data = data.replaceAll("<update[^>]*>|</update>", "");
		data = data.replaceAll("<delete[^>]*>|</delete>", "");
		data = data.replaceAll("<sql[^>]*>|</sql>", "");
		data = data.replaceAll("<if[^>]*>|</if>", "");
		data = data.replaceAll("<foreach[^>]*>|</foreach>", "");

		// #{ } 패턴 처리
		data = data.replaceAll("#\\{\\w+}", "'1'");

		// <![CDATA[ ]]> 패턴 처리
		data = data.replaceAll("<!\\[CDATA\\[|]]>", "");

		// where 태그 패턴 처리
		data = data.replaceAll("<where>", "WHERE 1 = 1");
		data = data.replaceAll("</where>", "");

		// set 태그 패턴 처리
		data = data.replace("<set>", "set").replace("</set>", "");
		data = data.replaceFirst(",\\s*WHERE", " WHERE");

		// ETC
		data = data.replaceAll("'(\\d+)' LIMIT\\s*'\\1'|LIMIT\\s*'\\d+'", "LIMIT 1");
		data = data.replaceAll("0\">", "");

		return data;
	}

	// 스키마 동기화 (원본 데이터 베이스)
	/*
	public void schemaSync(String tableSchema) {
		logger.info("FetcherService schemaSync: {}", tableSchema);

		// 원본 데이터베이스의 모든 테이블을 조회한다.
		List<TableVo> list = fetcherDao.getAllTableList(tableSchema);

		// 조회된 테이블의 생성 스크립트 list
		List<String> scriptList = new ArrayList<>();

		// 조회된 테이블의 drop 쿼리 list
		List<String> dropList = new ArrayList<>();

		for (TableVo vo : list) {
			String tableName = vo.getTableName();

			// 조회된 테이블 생성 스크립트 조회
			String createQuery = fetcherDao.getCreateTable(tableName);
			scriptList.add(createQuery);

			// 조회된 테이블의 drop 쿼리 list
			String dropTable = "DROP TABLE IF EXISTS " + tableName;
			dropList.add(dropTable);
		}

		// 타겟 데이터베이스에 drop 쿼리 실행
		for (String element : dropList) {
			fetcherDao.executeQuery(element);
		}

		// 타겟 데이터베이스에 생성 스크립트 실행
		for (String element : scriptList) {
			fetcherDao.executeQuery(element);
		}
	}
	*/
}