package controller;

import java.time.LocalDate;

import model.Book;
import model.Member;

public class LibraryController {
	
	private model.ManagementSystem managementSystem;
	private view.Console console;
	
	public LibraryController(model.ManagementSystem managementSystem, view.Console console) {
		this.managementSystem = managementSystem;
		this.console = console;
	}
	
	public boolean menu() {
		
		view.MenuOption option = console.menuOptionInput();
		switch(option) {
		case ADD_BOOK :
			managementSystem.addBook(console.newBookInput());
			break;
		case DELETE_BOOK :
			managementSystem.deleteBook(console.getBookByIdInput());
			break;
		case EDIT_BOOK :
			Book book = console.editBookInput();
			managementSystem.deleteBook(book);
			managementSystem.addBook(book);
			break;
		case SEARCH_BOOK :
			while(searchBookMenu());
			break;
		case ADD_MEMBER :
			managementSystem.addMember(console.newMemberInput());
			break;
		case DELETE_MEMBER :
			managementSystem.deleteMember(console.getMemberByIdInput());
			break;
		case EDIT_MEMBER :
			Member member = console.editMemberInput();
			managementSystem.deleteMember(member);
			managementSystem.addMember(member);
			break;
		case SEARCH_MEMBER :
			while(searchMemberMenu());
			break;
		case NEW_LOAN :
			managementSystem.addLoan(console.newLoanInput());
			break;
		case RETURN_BOOK :
			managementSystem.deleteLoan(console.returnBook());
			break;
		case SEARCH_LOAN :
			while(searchLoanMenu());
			break;
		case SEARCH :
			while(searchMenu());
			break;
		default:
			break;
		}
	
		return option != view.MenuOption.QUIT;
	}
	
	public boolean searchMenu() {
		view.SearchOption option = console.searchOptionInput();
		switch(option) {
		case LIST_BOOKS :
			console.querySearch("select * from books");
			break;
		case LIST_MEMBERS :
			console.querySearch("select * from members");
			break;
		case LIST_LOANS :
			console.querySearch("select * from loans");
			break;
		case BOOKS_AVAILABLE :
			console.querySearch("select books.id, books.title, books.author from books where not exists(select loans.bookId FROM loans WHERE books.id = loans.bookId)");
			break;
		case BOOKS_UNAVAILABLE :
			console.querySearch("select books.id, books.title, members.id, members.name, members.phoneNr, loans.endDate from books join loans on books.id = loans.bookId join members on members.id = loans.memberId");
			break;
		case BOOKS_RETURN_TODAY :
			console.querySearch("select books.id, books.title, members.id, members.name, members.phoneNr from books join loans on books.id = loans.bookId join members on members.id = loans.memberId where loans.endDate = '" + LocalDate.now().toString() + "'");
			break;
		case MEMBERS_LOANS_AMOUNT :
			console.querySearch("select memberId, count(memberId) as nrOfLoans from loans group by memberId");
			break;
		case LIBRARY_OVERVIEW :
			 //console.querySearch("select count(*) from books union select count(*) from members union select count(*) from loans");
			 console.querySearch("select count(*) as nrOfBooks from books");
			 console.querySearch("select count(*) as nrOfMembers from members");
			 console.querySearch("select count(*) as nrOfLoans from loans");
		default:
			break;
		}
		return option != view.SearchOption.QUIT;
	}
	
	public boolean searchBookMenu() {
		view.SearchOption option = console.bookSearchOptionInput();
		switch(option) {
		case BOOK_ID :
			console.querySearch("select * from books where id = " + console.getBookByIdInput().getId());
			break;
		case BOOK_AUTHOR :
			console.querySearch("select * from books where author = '" + console.getStringInput("author") + "'");
			break;
		case BOOK_TITLE :
			console.querySearch("select * from books where title = '" + console.getStringInput("title") + "'");
			break;
		case BOOK_GENRE :
			console.querySearch("select * from books where genre = '" + console.getStringInput("genre") + "'");
			break;
		case BOOK_PUBLISHDATE :
			console.querySearch("select * from books where publishDate = '" + console.getStringInput("publish date [YYYY-MM-DD]") + "'");
			break;
		case BOOK_ISBN :
			console.querySearch("select * from books where isbn = '" + console.getStringInput("ISBN") + "'");
			break;
		default :
			break;
		}
		return option != view.SearchOption.QUIT;
	}
	
	public boolean searchMemberMenu() {
		view.SearchOption option = console.memberSearchOptionInput();
		switch(option) {
		case MEMBER_ID :
			console.querySearch("select * from members where id = " + console.getMemberByIdInput().getId());
			break;
		case MEMBER_NAME :
			console.querySearch("select * from members where name = '" + console.getStringInput("name") + "'");
			break;
		case MEMBER_PHONENR :
			console.querySearch("select * from members where phoneNr = '" + console.getStringInput("phone number") + "'");
			break;
		case MEMBER_ADDRESS :
			console.querySearch("select * from members where address = '" + console.getStringInput("address") + "'");
			break;
		case MEMBER_SSN :
			console.querySearch("select * from members where ssn = '" + console.getStringInput("ssn") + "'");
			break;
		default :
			break;
		}
		return option != view.SearchOption.QUIT;
	}
	
	public boolean searchLoanMenu() {
		view.SearchOption option = console.loanSearchOptionInput();
		switch(option) {
		case LOAN_ID :
			console.querySearch("select * from loans where id = " + console.getLoanByIdInput().getId());
			break;
		case LOAN_BOOKID :
			console.querySearch("select * from loans where bookId = '" + console.getStringInput("book id") + "'");
			break;
		case LOAN_MEMBERID :
			console.querySearch("select * from loans where memberId = '" + console.getStringInput("member id") + "'");
			break;
		case LOAN_STARTDATE :
			console.querySearch("select * from loans where startDate = '" + console.getStringInput("start date") + "'");
			break;
		case LOAN_ENDDATE :
			console.querySearch("select * from loans where endDate = '" + console.getStringInput("end date") + "'");
			break;
		default :
			break;
		}
		return option != view.SearchOption.QUIT;
	}

}
