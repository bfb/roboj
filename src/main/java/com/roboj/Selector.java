public class Selector {
	
	private String text;
	private Integer index;

	public Selector(String text) {
		this.text = text;
		this.index = findIndex();
		removeIndex();
	}

	public String getText() {
		return text;
	}

	public Integer getIndex() {
		return index;
	}

	public void removeIndex() {
		if(text.endsWith("]")) {
			int i = text.lastIndexOf("[");
			text = text.substring(0, i);
		}
	}

	public Integer findIndex() {
		if(text.endsWith("]")) {
			int i = text.lastIndexOf("[");
			String index = text.substring(i+1, text.length() - 1);
			return Integer.valueOf(index);
		}
		return null;
	}

	public String toString() {
		return this.text + " - " + this.index;
	}
	
}