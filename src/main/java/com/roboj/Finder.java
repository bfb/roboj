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

	public Finder(List<Selector> selectors, String attr, String id, Processor processor) {

		this.attr = attr;
		this.selectors = selectors;
		this.id = id;
		this.processor = processor;

		System.out.println("PR => " + this.processor);
	}

	public Finder() {}

	public String find() {
		Document doc = establishConn();
		Elements elements = doc.select("html");

		for(Selector selector : selectors) {
			elements = elements.select(selector.getText());
			if(selector.getIndex() != null) {
				elements = elements.eq(selector.getIndex());
			}
		}

		String result = "";
		for(Element element : elements) {
			System.out.println(" >>>>> " + this.processor.process(element.attr(attr)));

			result += this.processor.process(element.attr(attr)) + ",\n";
		}

		return result;
	}

	private Document establishConn() {
			try {
					return Jsoup.connect(url).get();
      } catch(IOException e) {
          return null;
      }
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

	public Processor getProcessor() {
		return this.processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public Finder getClone() {
		Finder finder = new Finder();
		finder.setSelectors(selectors);
		finder.setAttr(attr);
		finder.setId(this.id);
		finder.setProcessor(this.processor);
		return finder;
	}

	public String toString() {
		return this.attr + " - " +
		this.selectors + " - " +
		this.id + " - " +
		this.processor;
	}
}
