import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import java.util.*;
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

        String selector = tokens.getText(ctx.selectors());
        Formatter formatter = new Formatter(selector);
        selector = formatter.format();

        
        List<Selector> selectors = new ArrayList<Selector>();
        String selectorParts[] = selector.split(" ");

        for(int i = 0; i < selectorParts.length; i++) {
            selectors.add(new Selector(selectorParts[i]));
        }

        Processor processor;
        if(tokens.getText().contains("process")) {
            String method = tokens.getText(ctx.process().code().method());
            String[] params = tokens.getText(ctx.process().code().params()).split(",");
            processor = new Processor(method, params);
        } else {
           processor = new Processor("", new String[0]);
        }

        String attr = tokens.getText(ctx.property());
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
}
