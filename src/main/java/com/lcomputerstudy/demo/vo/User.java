package com.lcomputerstudy.demo.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private Timestamp userDatetime;
	private String userFormatDatetime;
	
	public Timestamp getUserDatetime() {
		return userDatetime;
	}
	public void setUserDatetime(Timestamp userDatetime) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		userFormatDatetime = f.format(userDatetime);
		
		this.userDatetime = userDatetime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserFormatDatetime() {
		return userFormatDatetime;
	}
	public void setUserFormatDatetime(String userFormatDatetime) {
		this.userFormatDatetime = userFormatDatetime;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userAge="
				+ userAge + ", userDatetime=" + userDatetime + ", userFormatDatetime=" + userFormatDatetime + "]";
	}
	
}
