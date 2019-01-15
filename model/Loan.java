package model;

public class Loan {
	
	private int id;
	private int bookId;
	private int memberId;
	private String startDate;
	private String endDate;
	
	public Loan(int id) {
		this.id = id;
	}
	
	public Loan(int id, int bookId, int memberId, String startDate, String endDate) {
		this.id = id;
		this.bookId = bookId;
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getId() { return this.id; }

	public int getBookId() { return bookId; }

	public void setBookId(int bookId) { this.bookId = bookId; }

	public int getMemberId() { return memberId; }

	public void setMemberId(int memberId) { this.memberId = memberId; }

	public String getStartDate() { return startDate; }

	public void setStartDate(String startDate) { this.startDate = startDate; }

	public String getEndDate() { return endDate; }

	public void setEndDate(String endDate) { this.endDate = endDate; }
	
}
