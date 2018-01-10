
public class AnotherThreadTest implements Runnable {
	private int count = 100;
	
	public static void main(String[] args) {
		AnotherThreadTest test = new AnotherThreadTest();
		Thread thread1 = new Thread(test);
		Thread thread2 = new Thread(test);
		Thread thread3 = new Thread(test);
		thread1.setName("Butters");
		thread2.setName("Cartman");
		thread3.setName("Stan");
		thread1.start();
		thread2.start();
		thread3.start();
		while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
			try { 
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("final count " + test.count);
	}

	public  void go() {
		System.out.println(Thread.currentThread().getName() + " inside (" + count + ")");
		if (count > 0) {
			System.out.println(Thread.currentThread().getName() + " started (" + count + ")");
			count -= 20;
			System.out.println(Thread.currentThread().getName() + " ended (" + count + ")");
		}
	}
	
	public void run() {
		while (count > 0)
			go();
	}
}
