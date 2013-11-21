import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Robot {
	private static List<Finder> finders = new ArrayList<Finder>();
	private static List<String> urls = new ArrayList<String>();
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

	public static void addFinder(Finder finder) {
        finders.add(finder);
	}

    public static void addUrl(String url) {
        urls.add(url);
    }

    public static void setConcurrency(int threads) {
        pool = Executors.newFixedThreadPool(threads);
    }

	public static void start() {
        for (int i = 0; i < urls.size(); i++) {
        	List<Finder> findersCopy = new ArrayList<Finder>();
        	for(Finder finder : finders) {
        		findersCopy.add(finder.getClone());
        	}
           
        	for(Finder finder : findersCopy) {
        		finder.setUrl(urls.get(i));
            }
        	
            Runnable worker = new FinderExecutor(findersCopy, "data/url" + i + ".json");
        	pool.execute(worker);
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        
        }

        Logger.done();
	}	
}