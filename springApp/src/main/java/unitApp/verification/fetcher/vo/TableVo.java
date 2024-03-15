package unitApp.verification.fetcher.vo;

import config.vo.DefaultVo;

public class TableVo extends DefaultVo {
	private String tableName;
	private String createTable;

	public TableVo(String tableName) {
		this.tableName = tableName;
	}

	public TableVo(String tableName, String createTable) {
		super();
		this.tableName = tableName;
		this.createTable = createTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCreateTable() {
		return createTable;
	}

	public void setCreateTable(String createTable) {
		this.createTable = createTable;
	}
}