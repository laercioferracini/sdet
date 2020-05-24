package books;

public class RetailPrice{
	private double amount;
	private String currencyCode;
	private int amountInMicros;

	public double getAmount(){
		return amount;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public int getAmountInMicros(){
		return amountInMicros;
	}

	@Override
 	public String toString(){
		return 
			"RetailPrice{" + 
			"amount = '" + amount + '\'' + 
			",currencyCode = '" + currencyCode + '\'' + 
			",amountInMicros = '" + amountInMicros + '\'' + 
			"}";
		}
}
