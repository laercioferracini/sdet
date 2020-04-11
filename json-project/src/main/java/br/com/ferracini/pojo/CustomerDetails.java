package br.com.ferracini.pojo;

public class CustomerDetails {
    private String courseName;
    private String purchasedDate;
    private String amount;
    private String location;

    public CustomerDetails() {
    }

    public CustomerDetails(String courseName, String purchasedDate, String amount, String location) {
        this.courseName = courseName;
        this.purchasedDate = purchasedDate;
        this.amount = amount;
        this.location = location;
    }

	public String getCourseName() {
		return courseName;
	}

	public String getPurchasedDate() {
		return purchasedDate;
	}

	public String getAmount() {
		return amount;
	}

	public String getLocation() {
		return location;
	}

	@Override
    public String toString() {
        return
                "CustomerDetails{" +
                        "courseName = '" + courseName + '\'' +
                        ",purchasedDate = '" + purchasedDate + '\'' +
                        ",amount = '" + amount + '\'' +
                        ",location = '" + location + '\'' +
                        "}";
    }
}
