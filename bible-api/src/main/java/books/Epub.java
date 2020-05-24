package books;

public class Epub{
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
			"Epub{" + 
			"isAvailable = '" + isAvailable + '\'' + 
			",acsTokenLink = '" + acsTokenLink + '\'' + 
			"}";
		}
}
