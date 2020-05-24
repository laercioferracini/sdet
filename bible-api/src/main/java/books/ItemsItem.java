package books;

public class ItemsItem{
	private SaleInfo saleInfo;
	private SearchInfo searchInfo;
	private String kind;
	private VolumeInfo volumeInfo;
	private String etag;
	private String id;
	private AccessInfo accessInfo;
	private String selfLink;

	public SaleInfo getSaleInfo(){
		return saleInfo;
	}

	public SearchInfo getSearchInfo(){
		return searchInfo;
	}

	public String getKind(){
		return kind;
	}

	public VolumeInfo getVolumeInfo(){
		return volumeInfo;
	}

	public String getEtag(){
		return etag;
	}

	public String getId(){
		return id;
	}

	public AccessInfo getAccessInfo(){
		return accessInfo;
	}

	public String getSelfLink(){
		return selfLink;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"saleInfo = '" + saleInfo + '\'' + 
			",searchInfo = '" + searchInfo + '\'' + 
			",kind = '" + kind + '\'' + 
			",volumeInfo = '" + volumeInfo + '\'' + 
			",etag = '" + etag + '\'' + 
			",id = '" + id + '\'' + 
			",accessInfo = '" + accessInfo + '\'' + 
			",selfLink = '" + selfLink + '\'' + 
			"}";
		}
}
