import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.IOException;
import org.jsoup.select.Elements;
import java.util.*;

public class Finder {

	private List<Selector> selectors;
	private String attr;
	private String url;
	private Processor processor;
	private String id;
	private List<String> results; 

	public Finder(List<Selector> selectors, String attr, String id, Processor processor) {
		this.attr = attr;
		this.selectors = selectors;
		this.id = id;
		this.processor = processor;
	}

	public Finder() {}

	public List<String> find() {
		Document doc = establishConn();
		Elements elements = doc.select("html");

		for(Selector selector : selectors) {
			elements = elements.select(selector.getText());
			
			if(selector.getIndex() != null) {
				elements = elements.eq(selector.getIndex());
			}
		}

		for(Element element : elements) {
			if(attr.equals("")) {
				results.add("\"" + id +"\"" + ":" + "\"" + this.processor.process(element.html()) + "\"");
			} else {
				results.add("\"" + id +"\"" + ":" + "\"" + this.processor.process(element.attr(attr)) + "\"");	
			}
		}

		return results;
		//return JsonFormatter.format(id, results);
	}

	private Document establishConn() {
		try {
			return Jsoup.connect(url).get();
		} catch(IOException e) {
          return null;
        }
	}

	public Finder getClone() {
		Finder finder = new Finder();
		finder.setSelectors(selectors);
		finder.setAttr(attr);
		finder.setId(this.id);
		finder.setProcessor(this.processor);
		finder.setResults(new ArrayList<String>());
		return finder;
	}


	// getters and setters

	public String getUrl() {
		return url;
	}

	public List<Selector> getSelectors() {
		return selectors;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public void setSelectors(List<Selector> selectors) {
		this.selectors = selectors;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}

	public Processor getProcessor() {
		return this.processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public String toString() {
		return this.attr + " - " +
		this.selectors + " - " +
		this.id + " - " +
		this.processor;
	}
}
