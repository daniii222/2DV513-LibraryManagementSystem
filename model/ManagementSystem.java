package model;

import java.util.ArrayList;

public class ManagementSystem {
	
	private Database db;
	
	public ManagementSystem(Database db) {
		this.db = db;
	}
	
	public void addBook(Book book) {
		this.db.saveBook(book);
	}
	
	public void deleteBook(Book book) {
		this.db.deleteBookById(book.getId());
	}
	
	public void addMember(Member member) {
		this.db.saveMember(member);
	}
	
	public void deleteMember(Member member) {
		this.db.deleteMemberById(member.getId());
	}
	
	public void addLoan(Loan loan) {
		this.db.saveLoan(loan);
	}
	
	public void deleteLoan(Loan loan) {
		this.db.deleteLoanById(loan.getId());
	}

}
