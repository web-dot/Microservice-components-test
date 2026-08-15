package com.project.rating_service;

public class Rating {

	private Long Id;
	private Long bookId;
	private int stars;
	
	public Rating(Long id, Long bookId, int stars) {
		this.Id = id;
		this.bookId = bookId;
		this.stars = stars;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	
}
