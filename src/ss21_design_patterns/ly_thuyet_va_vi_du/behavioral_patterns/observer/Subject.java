package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
