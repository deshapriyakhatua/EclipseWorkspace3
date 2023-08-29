package objects;

public class User {

	String id;
	String name;
	String adderess;
	String pin;
	String phone;
	String gender;
	String latitude;
	String longitude;
	String profession;
	
	public User() {
		
	}

	public User(String id, String name, String adderess, String pin, String phone, String gender, String latitude,
			String longitude, String profession) {
		super();
		this.id = id;
		this.name = name;
		this.adderess = adderess;
		this.pin = pin;
		this.phone = phone;
		this.gender = gender;
		this.latitude = latitude;
		this.longitude = longitude;
		this.profession = profession;
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

	public String getAdderess() {
		return adderess;
	}

	public void setAdderess(String adderess) {
		this.adderess = adderess;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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
	
	
}
