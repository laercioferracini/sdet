package books;

import java.util.List;

public class VolumeInfo{
	private List<IndustryIdentifiersItem> industryIdentifiers;
	private int pageCount;
	private String printType;
	private ReadingModes readingModes;
	private String previewLink;
	private String canonicalVolumeLink;
	private String description;
	private String language;
	private String title;
	private ImageLinks imageLinks;
	private String subtitle;
	private PanelizationSummary panelizationSummary;
	private String publisher;
	private String publishedDate;
	private List<String> categories;
	private String maturityRating;
	private boolean allowAnonLogging;
	private String contentVersion;
	private List<String> authors;
	private String infoLink;

	public List<IndustryIdentifiersItem> getIndustryIdentifiers(){
		return industryIdentifiers;
	}

	public int getPageCount(){
		return pageCount;
	}

	public String getPrintType(){
		return printType;
	}

	public ReadingModes getReadingModes(){
		return readingModes;
	}

	public String getPreviewLink(){
		return previewLink;
	}

	public String getCanonicalVolumeLink(){
		return canonicalVolumeLink;
	}

	public String getDescription(){
		return description;
	}

	public String getLanguage(){
		return language;
	}

	public String getTitle(){
		return title;
	}

	public ImageLinks getImageLinks(){
		return imageLinks;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public PanelizationSummary getPanelizationSummary(){
		return panelizationSummary;
	}

	public String getPublisher(){
		return publisher;
	}

	public String getPublishedDate(){
		return publishedDate;
	}

	public List<String> getCategories(){
		return categories;
	}

	public String getMaturityRating(){
		return maturityRating;
	}

	public boolean isAllowAnonLogging(){
		return allowAnonLogging;
	}

	public String getContentVersion(){
		return contentVersion;
	}

	public List<String> getAuthors(){
		return authors;
	}

	public String getInfoLink(){
		return infoLink;
	}

	@Override
 	public String toString(){
		return 
			"VolumeInfo{" + 
			"industryIdentifiers = '" + industryIdentifiers + '\'' + 
			",pageCount = '" + pageCount + '\'' + 
			",printType = '" + printType + '\'' + 
			",readingModes = '" + readingModes + '\'' + 
			",previewLink = '" + previewLink + '\'' + 
			",canonicalVolumeLink = '" + canonicalVolumeLink + '\'' + 
			",description = '" + description + '\'' + 
			",language = '" + language + '\'' + 
			",title = '" + title + '\'' + 
			",imageLinks = '" + imageLinks + '\'' + 
			",subtitle = '" + subtitle + '\'' + 
			",panelizationSummary = '" + panelizationSummary + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",publishedDate = '" + publishedDate + '\'' + 
			",categories = '" + categories + '\'' + 
			",maturityRating = '" + maturityRating + '\'' + 
			",allowAnonLogging = '" + allowAnonLogging + '\'' + 
			",contentVersion = '" + contentVersion + '\'' + 
			",authors = '" + authors + '\'' + 
			",infoLink = '" + infoLink + '\'' + 
			"}";
		}
}