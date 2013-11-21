import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        //set concurrency
        int c = Integer.valueOf(args[2]);
        Robot.setConcurrency(c);

        // add urls
        String urls = null;
        urls = args[1];
        FileReader fr = new FileReader(urls);
        BufferedReader reader = new BufferedReader(fr);
        String url = reader.readLine();
        while(url != null) {
            Robot.addUrl(url);
            url = reader.readLine();
        }

        // start parsing
        String inputFile = null;
        if (args.length>0) inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }

        ANTLRInputStream input = new ANTLRInputStream(is);
        RobojLexer lexer = new RobojLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RobojParser parser = new RobojParser(tokens);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTree tree = parser.start();
        
        RobojListenerImpl listener = new RobojListenerImpl(parser);
        walker.walk(listener, tree);
    }
}