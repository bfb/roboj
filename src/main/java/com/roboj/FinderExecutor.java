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
    	String threadName = Thread.currentThread().getName();
        Logger.urlAccessed(threadName, finders.get(0).getUrl());
        List<String> finded = new ArrayList<String>();
        
        for(int i = 0; i < finders.size(); i++) {
            finded.addAll(finders.get(i).find());
        }

        int size = 0;
        size = finded.size() / finders.size();

        String json = "[";
        for(int i = 0; i < size; i++) {
            json += "\n{\n";
            json += finded.get(i);
            for(int j = 1; j < finders.size(); j++) {
                json += ",\n" + finded.get((j * size) + i);
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
        String threadName = Thread.currentThread().getName();
        try {
            new File("data").mkdirs();
            file.createNewFile();
            Logger.fileSaved(threadName, file.getAbsolutePath());
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(found);
            out.close();
        } catch(IOException e) {
            System.out.println("io exception");
        }

    }

}
