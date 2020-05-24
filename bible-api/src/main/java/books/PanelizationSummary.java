package books;

public class PanelizationSummary{
	private boolean containsImageBubbles;
	private boolean containsEpubBubbles;

	public boolean isContainsImageBubbles(){
		return containsImageBubbles;
	}

	public boolean isContainsEpubBubbles(){
		return containsEpubBubbles;
	}

	@Override
 	public String toString(){
		return 
			"PanelizationSummary{" + 
			"containsImageBubbles = '" + containsImageBubbles + '\'' + 
			",containsEpubBubbles = '" + containsEpubBubbles + '\'' + 
			"}";
		}
}
