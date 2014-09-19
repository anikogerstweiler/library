package com.epam.smvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "author")
	@NotEmpty(message="Please enter the author")
	@Size(min=0, max=50)
	private String author;

	@Column(name = "title")
	@NotEmpty(message="Please enter the title")
	private String title;

	@Column(name = "year")
	@NotNull(message="Please enter the publication year")
	@Range(min=1000, max=2014, message="Year must between 1000 and 2014")
	private Long year;

	@Column(name = "isbn")
	@NotEmpty(message="Please enter the ISBN number")
	@Size(min=0, max=20)
	private String isbn;
	
	@Column(name = "description")
	private String description;
	
	@Transient
	private String shortTitle;
	
	@Transient
	private String shortAuthor;
	
	public String getShortAuthor() {
		return shortAuthor;
	}

	public void setShortAuthor(String shortAuthor) {
		this.shortAuthor = shortAuthor;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title
				+ ", year=" + year + ", isbn=" + isbn
				+ ", description=" + description + "]";
	}
}
