package com.desh.java;

public class Category {

	String id;
	String name;
	String imageURL;
	
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Category(String id, String name, String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
	}
	
	
}
