import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.IOException;
import org.jsoup.select.Elements;

public class Finder {
	private Formatter formatter;

	private Selector selector;
	private String attr;
	private String url;

	public Finder(String selectors, String attr, String url) {
		this.attr = attr;
		this.url = url;
		this.formatter = new Formatter(selectors);
	}

	public String find() {
		Document doc = establishConn();
		Elements elems = doc.select("html");
		String[] selectors = formatter.format().split(" ");
		
		for(int i = 0; i < selectors.length; i++) {
			selector = new Selector(selectors[i]);

			elems = elems.select(selector.getSelector());
			if(selector.getIndex() != null) {

				elems = elems.eq(selector.getIndex());
			}
		}

		System.out.println(elems);
		return elems.toString();
	}

	private Document establishConn() {
		try {
			return Jsoup.connect(url).get();
       	} catch(IOException e) {
            return null;
        }
	}
}