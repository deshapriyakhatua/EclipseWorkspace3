package objects;

public class LoginResponse {
	
	String message;
	String userid;
	String accessToken;
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(String message, String userid, String accessToken) {
		this.message = message;
		this.userid = userid;
		this.accessToken = accessToken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
