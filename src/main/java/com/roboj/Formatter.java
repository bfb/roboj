public class Formatter {
	private String selectors;

	public Formatter(String selectors) {
		this.selectors = selectors;
	}

	public String format() {
		String formatted = selectors;
		formatted = formatted.replaceAll("=>", " ");
		formatted = formatted.replaceAll("[()]", "");
		return formatted;
	}

	
}