import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);

        RobojLexer lexer = new RobojLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RobojParser parser = new RobojParser(tokens);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTree tree = parser.t();
        
        RobojListenerImpl listener = new RobojListenerImpl(parser);
        walker.walk(listener, tree);
    }
}
