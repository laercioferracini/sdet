package books;

public class IndustryIdentifiersItem{
	private String identifier;
	private String type;

	public String getIdentifier(){
		return identifier;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"IndustryIdentifiersItem{" + 
			"identifier = '" + identifier + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}
