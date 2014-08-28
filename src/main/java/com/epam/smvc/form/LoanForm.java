package com.epam.smvc.form;


public class LoanForm {
	
	private Long id;
	
	private String fromDate;
	
	private String toDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "BorrowForm [id=" + id + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}


	
}
