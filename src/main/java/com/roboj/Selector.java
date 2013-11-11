public class Selector {
	
	private String selector;
	private Integer index;

	public Selector(String selector) {
		this.selector = selector;
		this.index = findIndex();
		removeIndex();
	}

	public String getSelector() {
		return selector;
	}

	public Integer getIndex() {
		return index;
	}

	public void removeIndex() {
		if(selector.endsWith("]")) {
			int i = selector.lastIndexOf("[");
			selector = selector.substring(0, i);
		}
	}

	public Integer findIndex() {
		if(selector.endsWith("]")) {
			int i = selector.lastIndexOf("[");
			String index = selector.substring(i+1, selector.length() - 1);
			return Integer.valueOf(index);
		}
		return null;
	}

}