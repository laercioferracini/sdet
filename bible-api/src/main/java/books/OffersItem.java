package books;

public class OffersItem{
	private int finskyOfferType;
	private RetailPrice retailPrice;
	private ListPrice listPrice;
	private boolean giftable;

	public int getFinskyOfferType(){
		return finskyOfferType;
	}

	public RetailPrice getRetailPrice(){
		return retailPrice;
	}

	public ListPrice getListPrice(){
		return listPrice;
	}

	public boolean isGiftable(){
		return giftable;
	}

	@Override
 	public String toString(){
		return 
			"OffersItem{" + 
			"finskyOfferType = '" + finskyOfferType + '\'' + 
			",retailPrice = '" + retailPrice + '\'' + 
			",listPrice = '" + listPrice + '\'' + 
			",giftable = '" + giftable + '\'' + 
			"}";
		}
}
