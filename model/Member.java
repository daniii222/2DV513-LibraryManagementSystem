package model;

public class Member {
	
	private int id;
	private String ssn;
	private String name;
	private String address;
	private String phoneNr;
	
	public Member(int id) {
		this.id = id;
	}
	
	public Member(int id, String ssn, String name, String address, String phoneNr) {
		this.id = id;
		this.ssn = ssn;
		this.name = name;
		this.address = address;
		this.phoneNr = phoneNr;
	}
	
	public int getId() { return this.id; }
	
	public String getSsn() { return ssn; }

	public boolean setSsn(String ssn) { 
		if(ssn != null && !ssn.isEmpty()) {
			this.ssn = ssn;
			return true;
		} else {
			return false;
		}
	}

	public String getName() { return name; }

	public boolean setName(String name) { 
		if(name != null && !name.isEmpty()) {
			this.name = name;
			return true;
		} else {
			return false;
		}
	}

	public String getAddress() { return address; }

	public boolean setAddress(String address) { 
		if(address != null && !address.isEmpty()) {
			this.address = address; 
			return true;
		} else {
			return false;
		}
	}

	public String getPhoneNr() { return phoneNr; }

	public boolean setPhoneNr(String phoneNr) { 
		if(phoneNr != null && !phoneNr.isEmpty()) {
			this.phoneNr = phoneNr; 
			return true;
		} else {
			return false;
		}
	}
}
