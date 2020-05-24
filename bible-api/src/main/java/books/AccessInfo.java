package books;

public class AccessInfo{
	private String accessViewStatus;
	private String country;
	private String viewability;
	private Pdf pdf;
	private String webReaderLink;
	private Epub epub;
	private boolean publicDomain;
	private boolean quoteSharingAllowed;
	private boolean embeddable;
	private String textToSpeechPermission;

	public String getAccessViewStatus(){
		return accessViewStatus;
	}

	public String getCountry(){
		return country;
	}

	public String getViewability(){
		return viewability;
	}

	public Pdf getPdf(){
		return pdf;
	}

	public String getWebReaderLink(){
		return webReaderLink;
	}

	public Epub getEpub(){
		return epub;
	}

	public boolean isPublicDomain(){
		return publicDomain;
	}

	public boolean isQuoteSharingAllowed(){
		return quoteSharingAllowed;
	}

	public boolean isEmbeddable(){
		return embeddable;
	}

	public String getTextToSpeechPermission(){
		return textToSpeechPermission;
	}

	@Override
 	public String toString(){
		return 
			"AccessInfo{" + 
			"accessViewStatus = '" + accessViewStatus + '\'' + 
			",country = '" + country + '\'' + 
			",viewability = '" + viewability + '\'' + 
			",pdf = '" + pdf + '\'' + 
			",webReaderLink = '" + webReaderLink + '\'' + 
			",epub = '" + epub + '\'' + 
			",publicDomain = '" + publicDomain + '\'' + 
			",quoteSharingAllowed = '" + quoteSharingAllowed + '\'' + 
			",embeddable = '" + embeddable + '\'' + 
			",textToSpeechPermission = '" + textToSpeechPermission + '\'' + 
			"}";
		}
}
