package books;

public class ListPrice{
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
			"ListPrice{" + 
			"amount = '" + amount + '\'' + 
			",currencyCode = '" + currencyCode + '\'' + 
			",amountInMicros = '" + amountInMicros + '\'' + 
			"}";
		}
}
