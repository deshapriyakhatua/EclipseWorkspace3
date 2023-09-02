package objects;

public class LoginTokenValidationResponse {

	String message;
	String userid;
	
	public LoginTokenValidationResponse(String message, String userid) {
		this.message = message;
		this.userid = userid;
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
	
	
}
