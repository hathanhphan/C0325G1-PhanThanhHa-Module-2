package ss9_automated_testing_and_tdd.bai_tap.next_day_calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NextDayCalculatorTest {
    @Test
    public void testRegularDayIncrement() {
        // Test case: 1/1/2018 -> 2/1/2018
        int[] expectedResult = {2, 1, 2018};
        int[] actualResult = NextDayCalculator.calculateNextDay(1, 1, 2018);
        assertArrayEquals(expectedResult, actualResult, "Regular day increment from 1/1/2018 should return 2/1/2018");
    }

    @Test
    public void testEndOfJanuary() {
        // Test case: 31/1/2018 -> 1/2/2018
        int[] expectedResult = {1, 2, 2018};
        int[] actualResult = NextDayCalculator.calculateNextDay(31, 1, 2018);
        assertArrayEquals(expectedResult, actualResult, "End of January should transition to February 1st");
    }

    @Test
    public void testEndOfApril() {
        // Test case: 30/4/2018 -> 1/5/2018
        int[] expectedResult = {1, 5, 2018};
        int[] actualResult = NextDayCalculator.calculateNextDay(30, 4, 2018);
        assertArrayEquals(expectedResult, actualResult, "End of April should transition to May 1st");
    }

    @Test
    public void testEndOfFebruaryNonLeapYear() {
        // Test case: 28/2/2018 -> 1/3/2018
        int[] expectedResult = {1, 3, 2018};
        int[] actualResult = NextDayCalculator.calculateNextDay(28, 2, 2018);
        assertArrayEquals(expectedResult, actualResult, "End of February in non-leap year should transition to March 1st");
    }

    @Test
    public void testEndOfFebruaryLeapYear() {
        // Test case: 29/2/2020 -> 1/3/2020
        int[] expectedResult = {1, 3, 2020};
        int[] actualResult = NextDayCalculator.calculateNextDay(29, 2, 2020);
        assertArrayEquals(expectedResult, actualResult, "End of February in leap year should transition to March 1st");
    }

    @Test
    public void testEndOfYear() {
        // Test case: 31/12/2018 -> 1/1/2019
        int[] expectedResult = {1, 1, 2019};
        int[] actualResult = NextDayCalculator.calculateNextDay(31, 12, 2018);
        assertArrayEquals(expectedResult, actualResult, "End of year should transition to January 1st of next year");
    }

    @Test
    public void testLeapYearFebruary28() {
        // Test case: 28/2/2020 -> 29/2/2020 (leap year)
        int[] expectedResult = {29, 2, 2020};
        int[] actualResult = NextDayCalculator.calculateNextDay(28, 2, 2020);
        assertArrayEquals(expectedResult, actualResult, "February 28 in leap year should go to February 29");
    }

    @Test
    public void testEndOfMonths30Days() {
        // Testing months with 30 days (April, June, September, November)
        int[] expectedResult = {1, 7, 2018};
        int[] actualResult = NextDayCalculator.calculateNextDay(30, 6, 2018);
        assertArrayEquals(expectedResult, actualResult, "End of June should transition to July 1st");
    }

    @Test
    public void testEndOfMonths31Days() {
        // Testing months with 31 days (January, March, May, July, August, October, December)
        int[] expectedResult = {1, 9, 2018};
        int[] actualResult = NextDayCalculator.calculateNextDay(31, 8, 2018);
        assertArrayEquals(expectedResult, actualResult, "End of August should transition to September 1st");
    }
}
