import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Robot {
	private static List<Finder> finders = new ArrayList<Finder>();
	private static List<String> urls = new ArrayList<String>();
    private static ExecutorService pool = Executors.newFixedThreadPool(20);

	public static void addFinder(Finder finder) {
        finders.add(finder);
	}

	/*public static void printFinders() {
        for(Finder finder : finders) {
            System.out.println(finder.getSelector().getSelector());
        }
	}*/

	public static void start() {
        urls.add("http://www.netshoes.com.br/departamento/casual/tenis");

		Long initalTime = System.currentTimeMillis();
        
        for (int i = 0; i < urls.size(); i++) {
        	List<Finder> findersCopy = new ArrayList<Finder>();
        	for(Finder finder : finders) {
        		findersCopy.add(finder.getClone());
        	}

        	for (Finder finder : findersCopy) {
        		finder.setUrl(urls.get(i));
	        }

        	Runnable worker = new FinderExecutor(findersCopy, "url" + i);
        	pool.execute(worker);
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        
        }
        Long finalTime = System.currentTimeMillis();
        System.out.println("time => " + (finalTime - initalTime));
        System.out.println("finished");
	}	
}