package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.observer;

public class NewsAgency implements Observer {
    private String agencyName;

    public NewsAgency(String agencyName) {
        this.agencyName = agencyName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("Báo " + agencyName + " đưa tin: Cổ phiếu "
                + stockSymbol + " giao dịch ở mức $" + price);

        if (price > 120) {
            System.out.println("  -> Tin tức: " + stockSymbol + " đạt mức cao kỷ lục!");
        } else if (price < 30) {
            System.out.println("  -> Tin tức: " + stockSymbol + " giảm mạnh, cần thận trọng!");
        }
    }
}
