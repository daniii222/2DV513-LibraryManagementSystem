package model;

public class Book {
	
	private int id;
	private String isbn;
	private String title;
	private String author;
	private String genre;
	private String publishDate;
	
	public Book(int id) {
		this.id = id;
	}
	
	public Book(int id, String isbn, String title, String author, String genre, String publishDate) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publishDate = publishDate;
	}
	
	public int getId() { return this.id; }

	public String getIsbn() { return isbn; }

	public boolean setIsbn(String isbn) { 
		if(isbn != null && !isbn.isEmpty()) {
			this.isbn = isbn;
			return true;
		} else {
			return false;
		}
	}

	public String getTitle() { return title; }

	public boolean setTitle(String title) { 
		if(title != null && !title.isEmpty()) {
			this.title = title;
			return true;
		} else {
			return false;
		}
 
	}

	public String getAuthor() { return author; }

	public boolean setAuthor(String author) { 
		if(author != null && !author.isEmpty()) {
			this.author = author;
			return true;
		} else {
			return false;
		}
	}

	public String getGenre() { return genre; }

	public boolean setGenre(String genre) { 
		if(genre != null && !genre.isEmpty()) {
			this.genre = genre;
			return true;
		} else {
			return false;
		}
	}

	public String getPublishDate() { return publishDate; }

	public boolean setPublishDate(String publishDate) { 
		if(publishDate != null && !publishDate.isEmpty()) {
			this.publishDate = publishDate;
			return true;
		} else {
			return false;
		} 
	}
}
