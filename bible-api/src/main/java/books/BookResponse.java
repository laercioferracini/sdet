package books;

import java.util.List;

public class BookResponse{
	private int totalItems;
	private String kind;
	private List<ItemsItem> items;

	public int getTotalItems(){
		return totalItems;
	}

	public String getKind(){
		return kind;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"BookResponse{" + 
			"totalItems = '" + totalItems + '\'' + 
			",kind = '" + kind + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}