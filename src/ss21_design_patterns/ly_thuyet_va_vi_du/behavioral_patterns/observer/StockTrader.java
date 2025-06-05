package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer;

public class StockTrader implements Observer {
    private String traderName;

    public StockTrader(String traderName) {
        this.traderName = traderName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("Trader " + traderName + " nhận thông báo: "
                + stockSymbol + " hiện tại $" + price);

        if (price > 100) {
            System.out.println("  -> " + traderName + " quyết định BÁN!");
        } else if (price < 50) {
            System.out.println("  -> " + traderName + " quyết định MUA!");
        } else {
            System.out.println("  -> " + traderName + " quyết định GIỮ!");
        }
    }
}
