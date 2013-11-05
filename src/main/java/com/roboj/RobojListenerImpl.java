import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class RobojListenerImpl extends RobojBaseListener {
	RobojParser parser;

    public RobojListenerImpl(RobojParser parser) {
        this.parser = parser;
    }

    @Override
    public void exitFinders(RobojParser.FindersContext ctx) {
        TokenStream tokens = parser.getTokenStream();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://pt.wikipedia.org/wiki/Barack_Obama").get();    
        } catch(IOException e) {
            System.out.println("no connection");
        }
		Element elem = doc.select(tokens.getText(ctx.selector())).first();
		System.out.println(elem.html());
    }
}