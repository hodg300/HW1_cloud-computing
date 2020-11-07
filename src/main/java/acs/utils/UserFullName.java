package acs.utils;

public class UserFullName {
	private String first;
	private String last;
	
	public UserFullName() {
		// TODO Auto-generated constructor stub
	}
	
	public UserFullName(String first,String last) {
		// TODO Auto-generated constructor stub
		this.first = first;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void validate() {
		
		if(first == null) {
			throw new RuntimeException("first name was not instantiate");
		}
		
		if(first.isEmpty()) {
			throw new RuntimeException("first name can not be empty");
		}
		
		if(last == null) {
			throw new RuntimeException("last name was not instantiate");
		}
		
		if(last.isEmpty()) {
			throw new RuntimeException("last name can not be empty");
		}
		
	}
	
	
}
