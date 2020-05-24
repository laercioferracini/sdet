package books;

public class Pdf{
	private boolean isAvailable;
	private String acsTokenLink;

	public boolean isIsAvailable(){
		return isAvailable;
	}

	public String getAcsTokenLink(){
		return acsTokenLink;
	}

	@Override
 	public String toString(){
		return 
			"Pdf{" + 
			"isAvailable = '" + isAvailable + '\'' + 
			",acsTokenLink = '" + acsTokenLink + '\'' + 
			"}";
		}
}
