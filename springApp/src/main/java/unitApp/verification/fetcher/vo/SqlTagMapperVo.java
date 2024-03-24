package unitApp.verification.fetcher.vo;

@SuppressWarnings("unused")
public class SqlTagMapperVo {
	private String filePath;
	private String namespace;
	private String id;
	private String refid;
	private String content;

	public SqlTagMapperVo(String filePath, String namespace, String id, String refid, String content) {
		this.filePath = filePath;
		this.namespace = namespace;
		this.id = id;
		this.refid = refid;
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}