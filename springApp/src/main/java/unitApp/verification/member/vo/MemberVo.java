package unitApp.verification.member.vo;

import config.vo.DefaultVo;

public class MemberVo extends DefaultVo {
	private String id;
	private String name;
	private String gender;
	private String birthday;
	private String etc;
	private String bloodType;

	public MemberVo(String id, String name, String gender, String birthday, String bloodType, String etc, String registerDate) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.bloodType = bloodType;
		this.etc = etc;
		setRegisterDate(registerDate);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
}