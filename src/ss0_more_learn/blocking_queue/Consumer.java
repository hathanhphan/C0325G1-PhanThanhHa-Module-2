package ss0_more_learn.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        try {
            while (true) {
                Integer item = queue.take(); // Chặn nếu queue rỗng
                if (item == -1) {
                    System.out.println("Consumer nhận tín hiệu kết thúc.");
                    break;
                }
                System.out.println("Consumer nhận: " + item);
                Thread.sleep(1500); // Giả lập thời gian xử lý
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
