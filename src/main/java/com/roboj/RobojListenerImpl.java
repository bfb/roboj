import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import java.util.*;
import java.util.Collections;
import java.io.File;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class RobojListenerImpl extends RobojBaseListener {
	RobojParser parser;
    String root;

    public RobojListenerImpl(RobojParser parser) {
        this.parser = parser;
        this.root = "";
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
        //if(tokens.getText().endsWith("end")) {
            String method = tokens.getText(ctx.process().code().method());
            String[] params = tokens.getText(ctx.process().code().params()).split(",");
            processor = new Processor(method, params);
        //} else {
          // processor = new Processor("", new String[0]);
        //}

        //String attr = " ";
        //if(tokens.getText().contains(":[a-z]' 'as")) {
           String attr = tokens.getText(ctx.property());
        //}
        
        String id = tokens.getText(ctx.id());

        Finder finder = new Finder(selectors, attr.substring(1, attr.length()), id, processor);

        System.out.println(finder);
        Robot.addFinder(finder);

        //finder.find();
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
