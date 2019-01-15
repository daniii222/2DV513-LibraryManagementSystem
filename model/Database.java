package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
	
	private Connection c = null;
	
	public Database() {
		String url = "jdbc:sqlite:library.db"; // Database
		
		try {
			this.c = DriverManager.getConnection(url);
			Statement s = this.c.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS books (id integer not null unique, isbn string, title string, author string, genre string, publishDate string)");
			s.executeUpdate("CREATE TABLE IF NOT EXISTS members (id integer not null unique, ssn string, name string, address string, phoneNr string)");
			s.executeUpdate("CREATE TABLE IF NOT EXISTS loans (id integer not null unique, bookId integer not null, memberId integer not null, startDate string, endDate string)");
			
		} catch(Exception e) { System.err.println(e.getMessage()); }
	}
	
	public ArrayList<Book> getBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			Statement s = this.c.createStatement();
		    String sql = "select * from books";
		    ResultSet rs;
		    rs = s.executeQuery(sql);
		    while (rs.next()) {
		        Book book = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publishDate"));
		        books.add(book);
		    }
		    return books;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return books;
		}
	}
	
	public ArrayList<Member> getMembers() {
		ArrayList<Member> members = new ArrayList<Member>();
		try {
			Statement s = this.c.createStatement();
		    String sql = "select * from members";
		    ResultSet rs;
		    rs = s.executeQuery(sql);
		    while (rs.next()) {
		        Member member = new Member(rs.getInt("id"), rs.getString("ssn"), rs.getString("name"), rs.getString("address"), rs.getString("phoneNr"));
		        members.add(member);
		    }
		    return members;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return members;
		}
	}
	
	public ArrayList<Loan> getLoans() {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		try {
			Statement s = this.c.createStatement();
		    String sql = "select * from loans";
		    ResultSet rs;
		    rs = s.executeQuery(sql);
		    while (rs.next()) {
		        Loan loan = new Loan(rs.getInt("id"), rs.getInt("bookId"), rs.getInt("memberId"), rs.getString("startDate"), rs.getString("endDate"));
		        loans.add(loan);
		    }
		    return loans;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return loans;
		}
	}
	
	public void saveBook(Book book) {
		String sql = "INSERT INTO books (id, isbn, title, author, genre, publishDate) VALUES(?,?,?,?,?,?)";
		try {
				PreparedStatement ps = this.c.prepareStatement(sql);
				ps.setInt(1, book.getId());
				ps.setString(2, book.getIsbn());
				ps.setString(3, book.getTitle());
				ps.setString(4, book.getAuthor());
				ps.setString(5, book.getGenre());
				ps.setString(6, book.getPublishDate());
				ps.executeUpdate();
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Book getBookById(int id) {
		ArrayList<Book> books = getBooks();
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getId() == (id)) {
					return books.get(i);
			}
		}
		return null;
	}
	
	public void deleteBookById(int id) {
		String sql = "DELETE FROM books WHERE id = ?";
		try {
			PreparedStatement ps = this.c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void saveMember(Member member) {
		String sql = "INSERT INTO members (id, ssn, name, address, phoneNr) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = this.c.prepareStatement(sql);
			ps.setInt(1, member.getId());
			ps.setString(2, member.getSsn());
			ps.setString(3, member.getName());
			ps.setString(4, member.getAddress());
			ps.setString(5, member.getPhoneNr());
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Member getMemberById(int id) {
		ArrayList<Member> members = getMembers();
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getId() == (id)) {
				return members.get(i);
			}
		}
		return null;
	}
	
	public void deleteMemberById(int id) {
		String sql = "DELETE FROM members WHERE id = ?";
		try {
			PreparedStatement ps = this.c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void saveLoan(Loan loan) {
		String sql = "INSERT INTO loans (id, bookId, memberId, startDate, endDate) VALUES(?,?,?,?,?)";
		try {
				PreparedStatement ps = this.c.prepareStatement(sql);
				ps.setInt(1, loan.getId());
				ps.setInt(2, loan.getBookId());
				ps.setInt(3, loan.getMemberId());
				ps.setString(4, loan.getStartDate());
				ps.setString(5, loan.getEndDate());
				ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Loan getLoanById(int id) {
		ArrayList<Loan> loans = getLoans();
		for(int i = 0; i < loans.size(); i++) {
			if(loans.get(i).getId() == (id)) {
				return loans.get(i);
			}
		}
		return null;
	}
	
	public void deleteLoanById(int id) {
		String sql = "DELETE FROM loans WHERE id = ?";
		try {
			PreparedStatement ps = this.c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ResultSet getResultOfSearch(String sql) {
	    try {
			Statement s = this.c.createStatement();
		    ResultSet rs;
		    rs = s.executeQuery(sql);
		    return rs;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public int getAvailableId(String type) {
		if(type.equals("book")) {
			ArrayList<Book> books = getBooks();
			int id = 1;
			boolean availableId = false;
			for(int i = 0; i < books.size(); i++) {
				for(int j = 0; j < books.size(); j++) {
					if(books.get(j).getId() == id) {
						availableId = false;
						break;
					} else {
						availableId = true;
					}
				}
				if(availableId) {
					break;
				}
				id++;
			}
			if(availableId) { return id; } 
			else { return books.size()+1; }
		} else if (type.equals("member")) {
			ArrayList<Member> members = getMembers();
			int id = 1;
			boolean availableId = false;
			for(int i = 0; i < members.size(); i++) {
				for(int j = 0; j < members.size(); j++) {
					if(members.get(j).getId() == id) {
						availableId = false;
						break;
					} else {
						availableId = true;
					}
				}
				if(availableId) {
					break;
				}
				id++;
			}
			if(availableId) { return id; } 
			else { return members.size()+1; }
		} else {
			ArrayList<Loan> loans = getLoans();
			int id = 1;
			boolean availableId = false;
			for(int i = 0; i < loans.size(); i++) {
				for(int j = 0; j < loans.size(); j++) {
					if(loans.get(j).getId() == id) {
						availableId = false;
						break;
					} else {
						availableId = true;
					}
				}
				if(availableId) {
					break;
				}
				id++;
			}
			if(availableId) { return id; } 
			else { return loans.size()+1; }
		}
	}
}
