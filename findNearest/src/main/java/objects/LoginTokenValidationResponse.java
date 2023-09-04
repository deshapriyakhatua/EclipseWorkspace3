package objects;

public class LoginTokenValidationResponse {

	String message;
	String userid;
	String destinationURL;
	
	public LoginTokenValidationResponse(String message, String userid, String destinationURL) {
		this.message = message;
		this.userid = userid;
		this.destinationURL = destinationURL;
	}

	public String getDestinationURL() {
		return destinationURL;
	}

	public void setDestinationURL(String destinationURL) {
		this.destinationURL = destinationURL;
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
