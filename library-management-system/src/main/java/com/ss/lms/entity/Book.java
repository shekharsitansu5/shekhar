package com.ss.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private boolean borrowed;
    private String msg;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy;
    
    
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the borrowedBy
	 */
	public User getBorrowedBy() {
		return borrowedBy;
	}
	/**
	 * @param borrowedBy the borrowedBy to set
	 */
	public void setBorrowedBy(User borrowedBy) {
		this.borrowedBy = borrowedBy;
	}
	
	
	/**
	 * @return the borrowed
	 */
	public boolean isBorrowed() {
		return borrowed;
	}
	/**
	 * @param borrowed the borrowed to set
	 */
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the errMsg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param errMsg the errMsg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
