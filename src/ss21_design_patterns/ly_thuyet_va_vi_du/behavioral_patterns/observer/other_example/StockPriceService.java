package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer.other_example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*   3. Observer Pattern - ⭐⭐⭐⭐⭐
    Tần suất sử dụng: Rất cao (80% dự án có GUI/Event)

    Observer là nền tảng của hầu hết các hệ thống event-driven:

    Java Swing/AWT: ActionListener, MouseListener
    Android: OnClickListener, BroadcastReceiver
    Spring: ApplicationEventPublisher, @EventListener
    Model-View architectures: MVC, MVP, MVVM
*
* */

public class StockPriceService {
    private List<StockPriceObserver> observers = new ArrayList<>();
    private Map<String, Double> stockPrices = new HashMap<>();

    public void addObserver(StockPriceObserver observer) {
        observers.add(observer);
    }

    public void updateStockPrice(String symbol, double price) {
        stockPrices.put(symbol, price);
        notifyObservers(symbol, price);
    }

    private void notifyObservers(String symbol, double price) {
        observers.forEach(observer -> observer.onPriceUpdate(symbol, price));
    }
}
