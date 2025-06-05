package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class StockPrice implements Subject {
    private List<Observer> observers;
    private String stockSymbol;
    private double price;

    public StockPrice(String stockSymbol) {
        this.stockSymbol = stockSymbol;
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Đã thêm observer mới cho cổ phiếu " + stockSymbol);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Đã xóa observer khỏi cổ phiếu " + stockSymbol);
    }

    @Override
    public void notifyObservers() {
        System.out.println("Thông báo thay đổi giá cổ phiếu " + stockSymbol + ": $" + price);
        for (Observer observer : observers) {
            observer.update(stockSymbol, price);
        }
        System.out.println("---");
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public double getPrice() {
        return price;
    }
}
