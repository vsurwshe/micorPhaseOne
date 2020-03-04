package org.domain.component;

public class UserTokenResponse {

	private String userToken;
	private Long tokenExprieTime;
	private String userRefreshToken;
	
	
	
   public UserTokenResponse(String userToken, Long tokenExprieTime, String userRefreshToken) {
		super();
		this.userToken = userToken;
		this.tokenExprieTime = tokenExprieTime;
		this.userRefreshToken = userRefreshToken;
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

}
