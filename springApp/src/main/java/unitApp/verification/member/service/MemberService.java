package unitApp.verification.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unitApp.verification.member.dao.MemberDao;
import unitApp.verification.member.vo.MemberVo;

import java.util.List;

public class MemberService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = MemberDao.getInstance();
	}

	public static  MemberService getInstance() {
		return MemberServiceHelper.INSTANCE;
	}

	private static  class MemberServiceHelper {
		private static final MemberService INSTANCE = new MemberService();
	}

	// member 테이블 조회
	public void selectMember() {
		logger.info("MemberService selectMember /");

		List<MemberVo> list = memberDao.selectMember();
		for (MemberVo vo : list) {
			System.out.println(vo.getId() + " " + vo.getName());
		}
	}

	public void fin() {
		System.err.println("\n--- FIN -----");
	}
}
