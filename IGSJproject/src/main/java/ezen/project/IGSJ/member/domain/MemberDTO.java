package ezen.project.IGSJ.member.domain;

import java.sql.Date;

public class MemberDTO {
	/*
	 * userId	varchar(20)	NO
		userPwd	varchar(500)	NO
		userName	varchar(15)	NO
		userPhoneNumber	varchar(20)	NO
		userEmail	varchar(40)	NO
		userJoinDate	datetime	NO
		userVerify	int	NO
		userBirth	varchar(20)	NO
	 * 
	 */

	private String userId;
	private String userPwd;
	private String userName;
	private String userPhoneNumber;
	private String userEmail;
	private Date userJoinDate;
	private int userVerify;
	private String userBirth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getUserJoinDate() {
		return userJoinDate;
	}

	public void setUserJoinDate(Date userJoinDate) {
		this.userJoinDate = userJoinDate;
	}

	public int getUserVerify() {
		return userVerify;
	}

	public void setUserVerify(int userVerify) {
		this.userVerify = userVerify;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userPhoneNumber=" + userPhoneNumber
				+ ", userEmail=" + userEmail + ", userJoinDate=" + userJoinDate + ", userVerify=" + userVerify + ", userBirth=" + userBirth + "]";
	}

}
