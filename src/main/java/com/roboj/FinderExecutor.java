import java.io.*;
import java.util.*;

public class FinderExecutor implements Runnable {

	private List<Finder> finders;
    private File file;

    public FinderExecutor(List<Finder> finders, String file) {
        this.finders = finders;
        this.file = new File(file);
    }

    @Override
    public void run() {
    	System.out.println(Thread.currentThread().getName() + " finding...");

        List<String> ss = new ArrayList<String>();
        int size = 0;
        String json = "[";

        for(int i = 0; i < finders.size(); i++) {
            ss.addAll(finders.get(i).find());
        }

        size = ss.size() / finders.size();
        for(int i = 0; i < size; i++) {
            json += "\n{\n";
            json += ss.get(i);
            for(int j = 1; j < finders.size(); j++) {
                json += ",\n" + ss.get((j * size) + i);
            }
            json += "\n";
            json += "}";
            if(i < size -1) {
                json += ",";
            }
            
        }

        json += "\n]";
        printFile(json);
    }

    private void printFile(String found) {
        try {
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(found);
            out.close();
        } catch(IOException e) {
            System.out.println("io exception");
        }

    }

}
