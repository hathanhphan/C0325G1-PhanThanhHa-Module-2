package ss9_automated_testing_and_tdd.bai_tap.next_day_calculator;

public class NextDayCalculator {
    public static int[] calculateNextDay(int day, int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
                if (day == 31) {
                    day = 1;
                    month++;
                    return new int[] {day, month, year};
                }
            case 4:
            case 6:
            case 11:
                if (day == 30) {
                    day = 1;
                    month++;
                    return new int[] {day, month, year};
                }
            case 12:
                if (day == 31) {
                    day = 1;
                    month = 1;
                    year++;
                    return new int[] {day, month, year};
                }
            case 2:
                if ((day == 29 && isLeapYear(year)) || (day == 28 && !isLeapYear(year))) {
                    day = 1;
                    month++;
                    return new int[] {day, month, year};
                }
        }
        return new int[] {day + 1, month, year};
    }

    public static boolean isLeapYear(int year) {
        boolean isDivisibleBy4 = year % 4 == 0;
        if (isDivisibleBy4) {
            boolean isDivisibleBy100 = year % 100 == 0;
            if (isDivisibleBy100) {
                boolean isDivisibleBy400 = year % 400 == 0;
                if (isDivisibleBy400) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }
}
