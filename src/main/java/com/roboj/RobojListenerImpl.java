import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class RobojListenerImpl extends RobojBaseListener {
	RobojParser parser;

    public RobojListenerImpl(RobojParser parser) {
        this.parser = parser;
    }

    @Override
    public void exitFinder(RobojParser.FinderContext ctx) {
        TokenStream tokens = parser.getTokenStream();

        String selectors = tokens.getText(ctx.selectors());
        String attr = tokens.getText(ctx.property());
        String url = "https://pt.wikipedia.org/wiki/Barack_Obama";
        String id = tokens.getText(ctx.id());

        Finder finder = new Finder(selectors, attr, url);
        finder.find();
    }
}