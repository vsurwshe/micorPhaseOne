package org.domain.component;

import org.domain.entity.UserDet;

public class UserTokenResponse {

	private UserDet userDetails;
	private String userToken;
	private String userRefreshToken;
	private Long tokenExprieTime;

	

	public UserTokenResponse(UserDet userDetails, String userToken, String userRefreshToken, Long tokenExprieTime) {
		super();
		this.userDetails = userDetails;
		this.userToken = userToken;
		this.userRefreshToken = userRefreshToken;
		this.tokenExprieTime = tokenExprieTime;
	}

	public UserTokenResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Long getTokenExprieTime() {
		return tokenExprieTime;
	}

	public void setTokenExprieTime(Long tokenExprieTime) {
		this.tokenExprieTime = tokenExprieTime;
	}

	public String getUserRefreshToken() {
		return userRefreshToken;
	}

	public void setUserRefreshToken(String userRefreshToken) {
		this.userRefreshToken = userRefreshToken;
	}

	public UserDet getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDet userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "UserTokenResponse [userDetails=" + userDetails + ", userToken=" + userToken + ", userRefreshToken="
				+ userRefreshToken + ", tokenExprieTime=" + tokenExprieTime + "]";
	}
	
	

}
