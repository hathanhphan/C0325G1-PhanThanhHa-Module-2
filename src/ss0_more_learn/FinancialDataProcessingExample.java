package ss0_more_learn;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    private String id;
    private LocalDate date;
    private double amount;
    private String category;
    private String type; // "INCOME" or "EXPENSE"

    public Transaction(String id, LocalDate date, double amount, String category, String type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
    }

    public String getId() { return id; }
    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

public class FinancialDataProcessingExample {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T1", LocalDate.of(2023, 1, 5), 1200.0, "Salary", "INCOME"),
                new Transaction("T2", LocalDate.of(2023, 1, 10), 45.5, "Groceries", "EXPENSE"),
                new Transaction("T3", LocalDate.of(2023, 1, 15), 300.0, "Rent", "EXPENSE"),
                new Transaction("T4", LocalDate.of(2023, 1, 20), 60.0, "Utilities", "EXPENSE"),
                new Transaction("T5", LocalDate.of(2023, 2, 5), 1200.0, "Salary", "INCOME"),
                new Transaction("T6", LocalDate.of(2023, 2, 10), 40.0, "Groceries", "EXPENSE"),
                new Transaction("T7", LocalDate.of(2023, 2, 15), 300.0, "Rent", "EXPENSE"),
                new Transaction("T8", LocalDate.of(2023, 2, 25), 200.0, "Bonus", "INCOME"),
                new Transaction("T9", LocalDate.of(2023, 3, 5), 1200.0, "Salary", "INCOME"),
                new Transaction("T10", LocalDate.of(2023, 3, 10), 50.0, "Groceries", "EXPENSE"),
                new Transaction("T11", LocalDate.of(2023, 3, 15), 300.0, "Rent", "EXPENSE"),
                new Transaction("T12", LocalDate.of(2023, 3, 20), 70.0, "Utilities", "EXPENSE")
        );

        // 1. Tính tổng thu nhập và chi phí
        Map<String, Double> totalByType = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getType,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
        System.out.println("Total by type: " + totalByType);

        // 2. Tính số dư cuối cùng
        double balance = transactions.stream()
                .mapToDouble(transaction ->
                        "INCOME".equals(transaction.getType()) ? transaction.getAmount() : -transaction.getAmount()
                )
                .sum();
        System.out.println("Final balance: $" + balance);

        // 3. Tìm tháng có thu nhập cao nhất
        Map<Month, Double> incomeByMonth = transactions.stream()
                .filter(t -> "INCOME".equals(t.getType()))
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        Optional<Map.Entry<Month, Double>> highestIncomeMonth = incomeByMonth.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        highestIncomeMonth.ifPresent(entry ->
                System.out.println("Month with highest income: " + entry.getKey() + " ($" + entry.getValue() + ")")
        );

        // 4. Tính chi tiêu trung bình hàng tháng theo danh mục
        Map<String, Double> avgMonthlyExpenseByCategory = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.averagingDouble(Transaction::getAmount)
                ));

        System.out.println("Average monthly expense by category:");
        avgMonthlyExpenseByCategory.forEach((category, avg) ->
                System.out.println(category + ": $" + String.format("%.2f", avg))
        );

        // 5. Tìm các giao dịch có giá trị lớn nhất mỗi tháng
        Map<Month, Optional<Transaction>> largestTransactionByMonth = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth(),
                        Collectors.maxBy(Comparator.comparing(Transaction::getAmount))
                ));

        System.out.println("Largest transaction each month:");
        largestTransactionByMonth.forEach((month, transaction) ->
                transaction.ifPresent(t ->
                        System.out.println(month + ": " + t.getCategory() + " - $" + t.getAmount())
                )
        );

        // 6. Tính tỷ lệ chi tiêu theo danh mục
        double totalExpense = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        Map<String, Double> expenseRatioByCategory = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(t -> t.getAmount() / totalExpense * 100)
                ));

        System.out.println("Expense ratio by category:");
        expenseRatioByCategory.forEach((category, ratio) ->
                System.out.println(category + ": " + String.format("%.2f", ratio) + "%")
        );

        // 7. Tính số dư qua từng tháng
        Map<Month, Double> balanceByMonth = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth(),
                        Collectors.summingDouble(t ->
                                "INCOME".equals(t.getType()) ? t.getAmount() : -t.getAmount()
                        )
                ));

        System.out.println("Monthly balance:");
        balanceByMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": $" + String.format("%.2f", entry.getValue()))
                );

        // 8. Tính tổng thu nhập và chi tiêu luỹ kế
        System.out.println("Cumulative income and expense:");

        double cumulativeIncome = 0;
        double cumulativeExpense = 0;

        List<Month> sortedMonths = Arrays.asList(Month.JANUARY, Month.FEBRUARY, Month.MARCH);
        for (Month month : sortedMonths) {
            double monthlyIncome = transactions.stream()
                    .filter(t -> t.getDate().getMonth() == month && "INCOME".equals(t.getType()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            double monthlyExpense = transactions.stream()
                    .filter(t -> t.getDate().getMonth() == month && "EXPENSE".equals(t.getType()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            cumulativeIncome += monthlyIncome;
            cumulativeExpense += monthlyExpense;

            System.out.println(month + " - Income: $" + monthlyIncome +
                    ", Expense: $" + monthlyExpense +
                    ", Cumulative Income: $" + cumulativeIncome +
                    ", Cumulative Expense: $" + cumulativeExpense);
        }

        // 9. Tìm các giao dịch bất thường (outliers) - giả sử là các giao dịch có giá trị > 2 lần giá trị trung bình của loại giao dịch đó
        Map<String, Double> avgAmountByType = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getType,
                        Collectors.averagingDouble(Transaction::getAmount)
                ));

        List<Transaction> outlierTransactions = transactions.stream()
                .filter(t -> t.getAmount() > 2 * avgAmountByType.get(t.getType()))
                .collect(Collectors.toList());

        System.out.println("Outlier transactions:");
        outlierTransactions.forEach(System.out::println);

        // 10. Dự đoán chi tiêu tháng tiếp theo dựa trên trung bình 3 tháng trước
        Map<String, Double> predictedExpenseByCategory = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.averagingDouble(Transaction::getAmount)
                ));

        System.out.println("Predicted expenses for next month:");
        predictedExpenseByCategory.forEach((category, amount) ->
                System.out.println(category + ": $" + String.format("%.2f", amount))
        );

        double totalPredictedExpense = predictedExpenseByCategory.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println("Total predicted expense: $" + String.format("%.2f", totalPredictedExpense));
    }
}

