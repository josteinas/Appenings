package com.lavalampe.appenings;


public class Happening {
	
	private long id;
	private Category category;
	private long starttime, endtime;
	private User creator;
	private boolean privateHappening;
	
	public Happening(Category category, long starttime, long endtime, User creator, boolean privateHappening) {
		super();
		this.setCategory(category);
		this.setStarttime(starttime);
		this.setEndtime(endtime);
		this.setCreator(creator);
		this.setPrivate(privateHappening);
		
	}
	public Happening(Category category, long starttime, long endtime, User creator, boolean privateHappening, long id) {
		super();
		this.setCategory(category);
		this.setStarttime(starttime);
		this.setEndtime(endtime);
		this.setCreator(creator);
		this.setId(id);
		this.setPrivate(privateHappening);
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
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
		return privateHappening;
	}
	public void setPrivate(boolean privateHappening) {
		this.privateHappening = privateHappening;
	}
	
	
}
