package ss18_threading.bai_tap.display_number;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        OddThread oddThread = new OddThread("Odd Thread");
        EvenThread evenThread = new EvenThread("Even Thread");
        oddThread.start();
        oddThread.join();
        evenThread.start();
    }
}
