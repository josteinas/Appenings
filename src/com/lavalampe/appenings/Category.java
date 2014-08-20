package com.lavalampe.appenings;

public class Category {
	
	private String name;
	private float latitude;
	private float longitude;
	private User creator;
	private long id;
	private boolean privateCategory;
	
	public Category(String name, float latitude, float longitude, User creator, boolean privateCategory) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.creator = creator;
		this.setPrivate(privateCategory);
	}
	public Category(String name, float latitude, float longitude, User creator, boolean privateCategory, long id) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.creator = creator;
		this.setId(id);
		this.setPrivate(privateCategory);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isPrivate() {
		return privateCategory;
	}
	public void setPrivate(boolean privateCategory) {
		this.privateCategory = privateCategory;
	}

}
