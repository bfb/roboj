public class Logger {

	public static void init() {
		System.out.println("starting boj\n");
	}

	public static void done() {
		System.out.println("\ndone");
	}

	public static void urlAccessed(String thread, String url) {
		System.out.println("(" + thread + ") accessing " + url);
	}

	public static void fileSaved(String thread, String file) {
		System.out.println("(" + thread + ") saving " + file);
	}

}