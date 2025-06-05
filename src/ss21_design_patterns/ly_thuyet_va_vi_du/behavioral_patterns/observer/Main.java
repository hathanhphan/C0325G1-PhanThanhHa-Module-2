package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer;

/*
* Observer Pattern định nghĩa mối quan hệ phụ thuộc một-nhiều giữa các đối tượng.
* Khi một đối tượng thay đổi trạng thái, tất cả các đối tượng phụ thuộc sẽ được thông báo và cập nhật tự động.
* */

public class Main {
    public static void main(String[] args) {
// Tạo subject
        StockPrice appleStock = new StockPrice("AAPL");

        // Tạo observers
        StockTrader trader1 = new StockTrader("John");
        StockTrader trader2 = new StockTrader("Alice");
        NewsAgency vnExpress = new NewsAgency("VnExpress");
        NewsAgency tuoiTre = new NewsAgency("Tuổi Trẻ");

        // Đăng ký observers
        appleStock.addObserver(trader1);
        appleStock.addObserver(trader2);
        appleStock.addObserver(vnExpress);
        appleStock.addObserver(tuoiTre);

        // Thay đổi giá cổ phiếu
        appleStock.setPrice(45.0);
        appleStock.setPrice(85.0);
        appleStock.setPrice(125.0);

        // Xóa một observer
        appleStock.removeObserver(trader2);
        appleStock.setPrice(25.0);
    }
}
