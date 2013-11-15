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

        String json = "{";
        json += finders.get(0).find();
        for(int i = 1; i < finders.size(); i++) {
            json += ", " + finders.get(i).find();
        }
        json += "}";

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
