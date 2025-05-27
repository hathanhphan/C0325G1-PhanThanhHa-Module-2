package ss0_more_learn.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Producer tạo: " + i);
                queue.put(i); // Chặn nếu queue đầy
                Thread.sleep(1000); // Giả lập thời gian sản xuất
            }
            queue.put(-1); // Tín hiệu kết thúc
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
