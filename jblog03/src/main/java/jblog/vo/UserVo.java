package jblog.vo;

import jakarta.validation.constraints.NotEmpty;

public class UserVo {
	@NotEmpty(message="ID는 필수입니다!")
	private String id;

	@NotEmpty(message="이름은 필수입니다!")
	private	String name;
	
	@NotEmpty(message="비밀번호는 필수입니다!")
	private String password;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
