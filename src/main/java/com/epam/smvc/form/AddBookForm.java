package com.epam.smvc.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class AddBookForm {
	
	private Long id;
	
	@NotEmpty(message="Please enter the ISBN number")
	@Size(min=0, max=20)
	private String isbn;
	
	@NotEmpty(message="Please enter the author")
	@Size(min=0, max=50)
	private String author;
	
	@NotNull(message="Please enter the publication year")
	@Range(min=1000, max=2014, message="Year must between 1000 and 2014")
	private Long year;
	
	@NotEmpty(message="Please enter the title")
	private String title;
	
	@Size(min=0, max=50, message="The description must between 0 and 50 characters")
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
