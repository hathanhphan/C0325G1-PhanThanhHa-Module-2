package ss18_threading.bai_tap.display_number;

public class OddThread extends Thread {

    public OddThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i += 2) {
                System.out.println(this.getName() + ": " + i);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " is interrupted.");
        }
    }
}
