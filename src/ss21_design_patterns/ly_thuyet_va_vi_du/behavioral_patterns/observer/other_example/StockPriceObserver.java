package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer.other_example;

public interface StockPriceObserver {
    void onPriceUpdate(String symbol, double price);
}
