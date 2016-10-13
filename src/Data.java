public class Data {
	private int CID, SocialSecurityNumber;
	private String FirstName, LastName, City,AddressLine;

	public Data(int cid, String firstName, String lastName, String city, String addressLine, int socialSecurityNumber)
	{
		setCID(cid);
		setFirstName(firstName);
		setLastName(lastName);
		setCity(city);
		setAddressLine(addressLine);
		setSocialSecurityNumber(socialSecurityNumber);
	}

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getSocialSecurityNumber() {
		return SocialSecurityNumber;
	}

	public void setSocialSecurityNumber(int socialSecurityNumber) {
		SocialSecurityNumber = socialSecurityNumber;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getAddressLine() {
		return AddressLine;
	}

	public void setAddressLine(String addressLine) {
		AddressLine = addressLine;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

}
