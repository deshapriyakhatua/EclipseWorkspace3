package model;

public class User {

	String userid;
	String email;
	String name;
	String phone;
	String gender;
	String latitude;
	String longitude;
	String profession;
	String address;
	String profile_pic;
	String cover_pic;
	
	public User() {
		
	}

	public User(String userid, String email, String name, String phone, String gender, String latitude,
			String longitude, String profession, String address, String profile_pic, String cover_pic) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.latitude = latitude;
		this.longitude = longitude;
		this.profession = profession;
		this.address = address;
		this.profile_pic = profile_pic;
		this.cover_pic = cover_pic;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	public String getCover_pic() {
		return cover_pic;
	}

	public void setCover_pic(String cover_pic) {
		this.cover_pic = cover_pic;
	}

		
}
