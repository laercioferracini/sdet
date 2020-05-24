package books;

public class ReadingModes{
	private boolean image;
	private boolean text;

	public boolean isImage(){
		return image;
	}

	public boolean isText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"ReadingModes{" + 
			"image = '" + image + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
