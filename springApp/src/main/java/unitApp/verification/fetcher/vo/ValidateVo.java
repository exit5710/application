package unitApp.verification.fetcher.vo;

import config.vo.DefaultVo;

@SuppressWarnings("unused")
public class ValidateVo extends DefaultVo {
	private static final long serialVersionUID = 1L;

	private String filePath;
	private String id;
	private String originalContent;
	private String regexpContent;
	private String errorMessage;
	private String verify;

	public ValidateVo(String regexpContent) {
		this.regexpContent = regexpContent;
	}

	public ValidateVo(String filePath, String id, String originalContent) {
		this.filePath = filePath;
		this.id = id;
		this.originalContent = originalContent;
	}

	public ValidateVo(String filePath, String originalContent, String errorMessage, String verify) {
		this.filePath = filePath;
		this.originalContent = originalContent;
		this.errorMessage = errorMessage;
		this.verify = verify;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getRegexpContent() {
		return regexpContent;
	}

	public void setRegexpContent(String regexpContent) {
		this.regexpContent = regexpContent;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}
}