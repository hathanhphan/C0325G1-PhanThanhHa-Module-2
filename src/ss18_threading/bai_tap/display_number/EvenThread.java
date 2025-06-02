package ss18_threading.bai_tap.display_number;

public class EvenThread extends Thread {

    public EvenThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println(this.getName() + ": " + i);
                Thread.sleep(15);
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " is interrupted.");
        }
    }
}
