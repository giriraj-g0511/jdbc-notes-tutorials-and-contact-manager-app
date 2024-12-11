package model;


public class Contact {
	
	
	private String name;
	private String company;
	private String email;
	private String city;
	private long mobileNumber;
	
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	@Override
	public String toString() {
		return "Contact [name=" + name + ", company=" + company + ", email=" + email + ", city=" + city
				+ ", mobileNumber=" + mobileNumber+ "]";
	}
	
	
	
	
}
