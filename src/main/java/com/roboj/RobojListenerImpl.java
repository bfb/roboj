import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import java.util.*;
import java.util.Collections;
import java.io.File;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class RobojListenerImpl extends RobojBaseListener {
	RobojParser parser;
    private String root;
    private String property;

    public RobojListenerImpl(RobojParser parser) {
        this.parser = parser;
        this.root = "";
        this.property = "";
    }

    @Override
    public void exitFinder(RobojParser.FinderContext ctx) {
        TokenStream tokens = parser.getTokenStream();

        String selector = this.root + tokens.getText(ctx.selectors());
        Formatter formatter = new Formatter(selector);
        selector = formatter.format();

        List<Selector> selectors = new ArrayList<Selector>();
        String selectorParts[] = selector.split(" ");
        for(int i = 0; i < selectorParts.length; i++) {
            selectors.add(new Selector(selectorParts[i]));
        }

        Processor processor;
        String method;
        String[] params;
        try {    
            method = tokens.getText(ctx.process().code().method());
            params = tokens.getText(ctx.process().code().params()).split(",");
        } catch(NullPointerException e) {
            method = "";
            params = new String[0];
        }
        processor = new Processor(method, params);
        
        String attr;
        try {
            attr = tokens.getText(ctx.property());
            attr = attr.substring(1, attr.length());
        } catch(NullPointerException e) {
            attr = "";
        }

        String id = tokens.getText(ctx.id());
        Finder finder = new Finder(selectors, attr, id, processor);
        Robot.addFinder(finder);
    }

    @Override
    public void exitStart(RobojParser.StartContext ctx) {
        Robot.start();
    }

    @Override
    public void exitRoot(RobojParser.RootContext ctx) {
        TokenStream tokens = parser.getTokenStream();

        if(tokens.getText().contains("root"))
            this.root = tokens.getText(ctx.selectors()) + "=>";
    }
}