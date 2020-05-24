package books;

import java.util.List;

public class SaleInfo{
	private List<OffersItem> offers;
	private String country;
	private boolean isEbook;
	private String saleability;
	private String buyLink;
	private RetailPrice retailPrice;
	private ListPrice listPrice;

	public List<OffersItem> getOffers(){
		return offers;
	}

	public String getCountry(){
		return country;
	}

	public boolean isIsEbook(){
		return isEbook;
	}

	public String getSaleability(){
		return saleability;
	}

	public String getBuyLink(){
		return buyLink;
	}

	public RetailPrice getRetailPrice(){
		return retailPrice;
	}

	public ListPrice getListPrice(){
		return listPrice;
	}

	@Override
 	public String toString(){
		return 
			"SaleInfo{" + 
			"offers = '" + offers + '\'' + 
			",country = '" + country + '\'' + 
			",isEbook = '" + isEbook + '\'' + 
			",saleability = '" + saleability + '\'' + 
			",buyLink = '" + buyLink + '\'' + 
			",retailPrice = '" + retailPrice + '\'' + 
			",listPrice = '" + listPrice + '\'' + 
			"}";
		}
}