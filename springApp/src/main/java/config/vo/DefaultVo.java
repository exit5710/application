package config.vo;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DefaultVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rowStatus;
	private String gridId;
	private int resultCnt;
	private String message;
	private String registerId;
	private String registerDate;
	private String modifyId;
	private String modifyDate;

	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public int getResultCnt() {
		return resultCnt;
	}

	public void setResultCnt(int resultCnt) {
		this.resultCnt = resultCnt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}