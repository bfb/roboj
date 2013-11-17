import java.util.*;

public class JsonFormatter {
	
	public static String format(String key, List<String> results) {

		String formatted = "\"" + key + "\"" + " : ";
        formatted += "\"" + results.get(0) + "\"";

        for(int i = 1; i < results.size(); i++) {
        	formatted += ", " + "\"" + key + "\"" + " : ";
            formatted += "\"" + results.get(i) + "\"";
        }
		return formatted;
	}


}