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
        String id = tokens.getText(ctx.id());
        // validate if null
        String process = tokens.getText(ctx.process().code());

        System.out.println(process);


        Finder finder = new Finder(selectors, attr.substring(1, attr.length()), id);

        Robot.addFinder(finder);

        //finder.find();
    }

    @Override
    public void exitStart(RobojParser.StartContext ctx) {
        Robot.start();
    }
}
