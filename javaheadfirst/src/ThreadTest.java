public class ThreadTest {
	public void go() {
		Thread t = new Thread(new ThreadJob());
		t.start();
		System.out.println("before job");
		System.out.println("after job");
	}
	
	public static void main(String[] args) {
		ThreadTest tt = new ThreadTest();
		tt.go();
	}

	public class ThreadJob implements Runnable {
		public void run() {
			System.out.println("job");
		}
	}
}
