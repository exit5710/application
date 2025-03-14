package unitApp.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unitApp.verification.fetcher.service.FetcherService;
import unitApp.verification.member.service.MemberService;

import java.lang.invoke.MethodHandles;

public class VerificationApp {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);
	private static final String DIRECTORY_PATH = "C:/Documents/sql_validate/sql_kdn_test";

	private final FetcherService fetcherService;
	private final MemberService memberService;

	public VerificationApp() {
		this.fetcherService = FetcherService.getInstance();
		this.memberService = MemberService.getInstance();
	}

	// initializeVerification
	private void initializeVerification() {
		logger.info("VerificationApp initializeVerification /");

		// validate 초기화
		fetcherService.initialize("validate");

		// 스키마 동기화 (원본 데이터베이스, 대상 데이터베이스)
		fetcherService.schemaSync("proponest", "validate"); // proponest

		// VALIDATE 테이블 clear
		fetcherService.validateClear();

		// function 생성
		fetcherService.functionCreate(DIRECTORY_PATH);

		// MyBatis XML 쿼리 유효성 검사
		fetcherService.validateQueryXml(DIRECTORY_PATH);

		// fin
		memberService.fin();

		// --------------------------------------------------------------------------------- //
		// vaf member 테이블 조회
		// memberService.selectMember();

		// 모든 테이블 drop
		// fetcherService.allTableDrop("validate");

		// 스키마 동기화 (원본 데이터 베이스)
		/*
		fetcherService.schemaSync("model2");
		*/
	}

	public static void main(String[] args) {
		logger.info(String.valueOf(thisClass));

		VerificationApp verificationApp = new VerificationApp();
		verificationApp.initializeVerification();
	}
}