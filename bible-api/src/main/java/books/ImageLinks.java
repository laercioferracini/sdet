package books;

public class ImageLinks{
	private String thumbnail;
	private String smallThumbnail;

	public String getThumbnail(){
		return thumbnail;
	}

	public String getSmallThumbnail(){
		return smallThumbnail;
	}

	@Override
 	public String toString(){
		return 
			"ImageLinks{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",smallThumbnail = '" + smallThumbnail + '\'' + 
			"}";
		}
}
