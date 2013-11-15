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

        for(Finder finder : finders) {
            String found = finder.find();
            this.printFile(found);
        }
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
