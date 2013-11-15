import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.IOException;
import org.jsoup.select.Elements;

public class Finder {
	private Formatter formatter;
	private Selector selector;
	private String attr;
	private String url;
	private Processor processor;
	private String id;

	public Finder(String selectors, String attr, String id) {
	    this.attr = attr;
		this.formatter = new Formatter(selectors);
		this.selector = new Selector(formatter.format());
		this.id = id;
	}

	public Finder() {}

	public String find() {
		Document doc = establishConn();
		Elements elems = doc.select("html");

		String[] selectors = this.selector.getSelector().split(" ");

		for(int i = 0; i < selectors.length; i++) {
			System.out.println(selectors[i]);
			Selector selectorPart = new Selector(selectors[i]);
			elems = elems.select(selectorPart.getSelector());
			if(selectorPart.getIndex() != null) {
				elems = elems.eq(selectorPart.getIndex());
			}
		}

		for(int i = 0; i < elems.size(); i++) {
			System.out.println(i + " >>>>> " + elems.get(i).attr(attr));

		}

		return elems.toString();
	}

	private Document establishConn() {
		try {
			return Jsoup.connect(url).get();
       	} catch(IOException e) {
            return null;
        }
	}

	public Selector getSelector() {
		return selector;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Finder getClone() {
		Finder finder = new Finder();
		finder.setSelector(selector);
		finder.setFormatter(formatter);
		finder.setAttr(attr);
		finder.setId(this.id);
		return finder;
	}
}
