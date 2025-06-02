package ss18_threading.bai_tap.simple_thread;

public class NumberGenerator implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Number: " + i + " | Hashcode: " + hashCode());
                    Thread.sleep(500);
                }
            }
        catch (InterruptedException e) {
            System.out.println("Thread is interrupted.");
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
