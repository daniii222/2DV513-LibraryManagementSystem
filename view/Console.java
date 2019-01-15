package view;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import model.Book;
import model.Member;
import model.Loan;

public class Console {
	
	private model.Database db;
	
	public Console(model.Database db) {
		this.db = db;
	}
	
	public MenuOption menuOptionInput() {
		displayMenu();
		Scanner in = new Scanner(System.in);
		int input = 13;
		while(input < 0 || input > 12) {
			System.out.println("Enter a number between 0-12.");
			if(in.hasNextInt()) { input = in.nextInt(); } 
			else { in.next(); }
			System.out.println("");
		}
		if(input == 1) {return MenuOption.ADD_BOOK; }
		else if(input == 2)  { return MenuOption.DELETE_BOOK; }
		else if(input == 3)  { return MenuOption.EDIT_BOOK; }
		else if(input == 4)  { return MenuOption.SEARCH_BOOK; }
		else if(input == 5)  { return MenuOption.ADD_MEMBER; }
		else if(input == 6)  { return MenuOption.DELETE_MEMBER; }
		else if(input == 7)  { return MenuOption.EDIT_MEMBER; }
		else if(input == 8)  { return MenuOption.SEARCH_MEMBER; }
		else if(input == 9)  { return MenuOption.NEW_LOAN; }
		else if(input == 10) { return MenuOption.RETURN_BOOK; }
		else if(input == 11) { return MenuOption.SEARCH_LOAN; }
		else if(input == 12) { return MenuOption.SEARCH; }
		else 				 { return MenuOption.QUIT; }
	}
	
	public SearchOption searchOptionInput() {
		displaySearchMenu();
		Scanner in = new Scanner(System.in);
		int input = 9;
		while(input < 0 || input > 8) {
			System.out.println("Enter a number between 0-8.");
			if(in.hasNextInt()) { input = in.nextInt(); } 
			else { in.next(); }
			System.out.println("");
		}
		if(input == 1) { return SearchOption.LIST_BOOKS; }
		else if(input == 2)  { return SearchOption.LIST_MEMBERS; }
		else if(input == 3)  { return SearchOption.LIST_LOANS; }
		else if(input == 4)  { return SearchOption.BOOKS_AVAILABLE; }
		else if(input == 5)  { return SearchOption.BOOKS_UNAVAILABLE; }
		else if(input == 6)  { return SearchOption.BOOKS_RETURN_TODAY; }
		else if(input == 7)  { return SearchOption.MEMBERS_LOANS_AMOUNT; }
		else if(input == 8)  { return SearchOption.LIBRARY_OVERVIEW; }
		else 				 { return SearchOption.QUIT; }
	}
	
	public SearchOption bookSearchOptionInput() {
		displayBookSearchMenu();
		Scanner in = new Scanner(System.in);
		int input = 9;
		while(input < 0 || input > 6) {
			System.out.println("Enter a number between 0-6.");
			if(in.hasNextInt()) { input = in.nextInt(); } 
			else { in.next(); }
			System.out.println("");
		}
		if(input == 1) { return SearchOption.BOOK_ID; }
		if(input == 2) { return SearchOption.BOOK_TITLE; }
		if(input == 3) { return SearchOption.BOOK_AUTHOR; }
		if(input == 4) { return SearchOption.BOOK_GENRE; }
		if(input == 5) { return SearchOption.BOOK_PUBLISHDATE; }
		if(input == 6) { return SearchOption.BOOK_ISBN; }
		else 		   { return SearchOption.QUIT; }
	}
	
	public SearchOption memberSearchOptionInput() {
		displayMemberSearchMenu();
		Scanner in = new Scanner(System.in);
		int input = 9;
		while(input < 0 || input > 5) {
			System.out.println("Enter a number between 0-5.");
			if(in.hasNextInt()) { input = in.nextInt(); } 
			else { in.next(); }
			System.out.println("");
		}
		if(input == 1) { return SearchOption.MEMBER_ID; }
		if(input == 2) { return SearchOption.MEMBER_NAME; }
		if(input == 3) { return SearchOption.MEMBER_PHONENR; }
		if(input == 4) { return SearchOption.MEMBER_ADDRESS; }
		if(input == 5) { return SearchOption.MEMBER_SSN; }
		else 		   { return SearchOption.QUIT; }
	}

	public SearchOption loanSearchOptionInput() {
		displayLoanSearchMenu();
		Scanner in = new Scanner(System.in);
		int input = 9;
		while(input < 0 || input > 5) {
			System.out.println("Enter a number between 0-5.");
			if(in.hasNextInt()) { input = in.nextInt(); } 
			else { in.next(); }
			System.out.println("");
		}
		if(input == 1) { return SearchOption.LOAN_ID; }
		if(input == 2) { return SearchOption.LOAN_BOOKID; }
		if(input == 3) { return SearchOption.LOAN_MEMBERID; }
		if(input == 4) { return SearchOption.LOAN_STARTDATE; }
		if(input == 5) { return SearchOption.LOAN_ENDDATE; }
		else 		   { return SearchOption.QUIT; }
	}
	
	public Book newBookInput() {
		Book newBook = new Book(db.getAvailableId("book"));
		while(!newBook.setIsbn(getStringInput("ISBN")));
		while(!newBook.setTitle(getStringInput("title")));
		while(!newBook.setAuthor(getStringInput("author")));
		while(!newBook.setGenre(getStringInput("genre")));
		while(!newBook.setPublishDate(getStringInput("publish date")));
		return newBook;
	}
	
	public Member newMemberInput() {
		Member newMember = new Member(db.getAvailableId("member"));
		while(!newMember.setSsn(getStringInput("SSN")));
		while(!newMember.setName(getStringInput("name")));
		while(!newMember.setAddress(getStringInput("address")));
		while(!newMember.setPhoneNr(getStringInput("phone number")));
		return newMember;
	}
	
	public Loan newLoanInput() {
		Loan newLoan = new Loan(db.getAvailableId("loan"));
		newLoan.setBookId(getBookByIdInput().getId());
		newLoan.setMemberId(getMemberByIdInput().getId());
		
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(7);
		
		newLoan.setStartDate(startDate.toString());
		newLoan.setEndDate(endDate.toString());
		
		return newLoan;
	}
	
	public Book editBookInput() {
		Book book = getBookByIdInput();
		System.out.println("Current ISBN: " + book.getIsbn());
		while(!book.setIsbn(getStringInput("ISBN")));
		System.out.println("Current title: " + book.getTitle());
		while(!book.setTitle(getStringInput("title")));
		System.out.println("Current author: " + book.getAuthor());
		while(!book.setAuthor(getStringInput("author")));
		System.out.println("Current genre: " + book.getGenre());
		while(!book.setGenre(getStringInput("genre")));
		System.out.println("Current publish date: " + book.getPublishDate());
		while(!book.setPublishDate(getStringInput("publish date")));
		return book;
	}
	
	public Member editMemberInput() {
		Member member = getMemberByIdInput();
		System.out.println("Current SSN: " + member.getSsn());
		while(!member.setSsn(getStringInput("SSN")));
		System.out.println("Current name: " + member.getName());
		while(!member.setName(getStringInput("name")));
		System.out.println("Current address: " + member.getAddress());
		while(!member.setAddress(getStringInput("address")));
		System.out.println("Current phone number: " + member.getPhoneNr());
		while(!member.setPhoneNr(getStringInput("phone number")));
		return member;
	}
	
	public Book getBookByIdInput() {
		Book book;
		while(true) {
			int id = getIntInput("book ID");
			if(db.getBookById(id) == null) {
				System.out.println("book ID does not exist.");
			} else {
				book = db.getBookById(id);
				break;
			}
		}
		return book;
	}
	
	public Member getMemberByIdInput() {
		Member member;
		while(true) {
			int id = getIntInput("member ID");
			if(db.getMemberById(id) == null) {
				System.out.println("member ID does not exist");
			} else {
				member = db.getMemberById(id);
				break;
			}
		}
		return member;
	}
	
	public Loan getLoanByIdInput() {
		Loan loan;
		while(true) {
			int id = getIntInput("loan ID");
			if(db.getLoanById(id) == null) {
				System.out.println("loan ID does not exist");
			} else {
				loan = db.getLoanById(id);
				break;
			}
		}
		return loan;
	}
	
	public Loan returnBook() {
		Loan loan = getLoanByIdInput();
		System.out.println("----------------------------------------");
		System.out.println("------------ RETURNED BOOK -------------");
		System.out.println("Book ID: " + loan.getBookId());
		System.out.println("Member ID: " + loan.getMemberId());
		LocalDate endDate = LocalDate.parse(loan.getEndDate());
		LocalDate returnDate = LocalDate.now();
		long daysLate = ChronoUnit.DAYS.between(endDate, returnDate);
		if(daysLate > 0) { System.out.println("Days late: " + daysLate + "\n"); } 
		else { System.out.println("The book is returned in time!\n"); }
		return loan;
	}
	
	public String getStringInput(String instruction) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter " + instruction + ": ");
		String input = in.nextLine();
		return input;
	}
	
	private int getIntInput(String instruction) {
		Scanner in = new Scanner(System.in);
		int input;
		while(true) {
			System.out.println("Enter " + instruction + ": ");
			if(in.hasNextInt()) { 
				input = in.nextInt();
				break;
			} else { 
				in.next(); 
			}
		}
		return input;
	}
	
	public void querySearch(String query) {
		ResultSet rs = db.getResultOfSearch(query);
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			    	String columnName = rsmd.getColumnName(i);
			        String columnValue = rs.getString(i);
			        System.out.print(columnName + ": " + columnValue);
			        if(i < columnsNumber) { System.out.print(" | "); }
			    }
			    System.out.println("\n-------");
			}
			System.out.println("");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void displayMenu() { 
		System.out.println("----------------------------------------");
		System.out.println("---------------- BOOKS -----------------");
		System.out.println("1:  Add Book");
		System.out.println("2:  Delete Book");
		System.out.println("3:  Edit Book");
		System.out.println("4:  Search Book");
		System.out.println("----------------------------------------");
		System.out.println("--------------- MEMBERS ----------------");
		System.out.println("5:  Add Member");
		System.out.println("6:  Delete Member");
		System.out.println("7:  Edit Member");
		System.out.println("8:  Search Member");
		System.out.println("----------------------------------------");
		System.out.println("---------------- LOANS -----------------");
		System.out.println("9:  New Loan");
		System.out.println("10: Return Book");
		System.out.println("11: Search Loan");
		System.out.println("----------------------------------------");
		System.out.println("12: Search In Database");
		System.out.println("0:  Quit");
		System.out.println("----------------------------------------\n");
	}
	
	private void displaySearchMenu() {
		System.out.println("----------------------------------------");
		System.out.println("---------------- SEARCH ----------------");
		System.out.println("1:  List All Books");
		System.out.println("2:  List All Members");
		System.out.println("3:  List All Loans");
		System.out.println("4:  Books Available");
		System.out.println("5:  Books Unavailable");
		System.out.println("6:  Books To Be Returned Today");
		System.out.println("7:  Member's Amount Of Current Loans");
		System.out.println("8:  Library Overview");
		System.out.println("----------------------------------------");
		System.out.println("0:  Go Back To Menu");
		System.out.println("----------------------------------------\n");
	}
	
	public void displayBookSearchMenu() {
		System.out.println("----------------------------------------");
		System.out.println("------------- BOOK SEARCH --------------");
		System.out.println("1:  Search By ID");
		System.out.println("2:  Search By Title");
		System.out.println("3:  Search By Author");
		System.out.println("4:  Search By Genre");
		System.out.println("5:  Search By Publish Date");
		System.out.println("6:  Search By ISBN");
		System.out.println("----------------------------------------");
		System.out.println("0:  Go Back To Menu");
		System.out.println("----------------------------------------\n");
	}
	
	public void displayMemberSearchMenu() {
		System.out.println("----------------------------------------");
		System.out.println("------------ MEMBER SEARCH -------------");
		System.out.println("1:  Search By ID");
		System.out.println("2:  Search By Name");
		System.out.println("3:  Search By Phone Number");
		System.out.println("4:  Search By Address");
		System.out.println("5:  Search By SSN");
		System.out.println("----------------------------------------");
		System.out.println("0:  Go Back To Menu");
		System.out.println("----------------------------------------\n");		
	}
	
	public void displayLoanSearchMenu() {
		System.out.println("----------------------------------------");
		System.out.println("------------- LOAN SEARCH --------------");
		System.out.println("1:  Search By ID");
		System.out.println("2:  Search By Book ID");
		System.out.println("3:  Search By Member ID");
		System.out.println("4:  Search By Start Date");
		System.out.println("5:  Search By End Date");
		System.out.println("----------------------------------------");
		System.out.println("0:  Go Back To Menu");
		System.out.println("----------------------------------------\n");
	}
}
