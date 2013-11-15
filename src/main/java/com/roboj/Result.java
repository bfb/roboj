import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Result {
	private static List<Finder> finders = new ArrayList<Finder>();
	private static List<String> urls = new ArrayList<String>();

	public static void addFinder(Finder finder) {
		finders.add(finder);
	}

	public static void printFinders() {
		for(Finder finder : finders) {
			System.out.println(finder.getSelector().getSelector());
		}
	}

	public static void running() {
		urls.add("http://www.livrariacultura.com.br/scripts/cultura/maisv/maisv.asp?titem=1&nassunto=1&nveiculomv=1&cidioma=por");

		Long initalTime = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < urls.size(); i++) {

        	List<Finder> finders2 = new ArrayList<Finder>();
        	for(Finder finder : finders) {
        		finders2.add(finder.getClone());
        	}

        	for (int j = 0; j < finders2.size(); j++) {
        		finders2.get(j).setUrl(urls.get(i));
	        }

        	Runnable worker = new FinderExecutor(finders2, "url" + i);
        	executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        Long finalTime = System.currentTimeMillis();
        System.out.println("time => " + (finalTime - initalTime));
        System.out.println("finished");
	}
}
