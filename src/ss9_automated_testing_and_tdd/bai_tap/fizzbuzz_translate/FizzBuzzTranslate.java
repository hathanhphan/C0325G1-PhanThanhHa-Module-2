package ss9_automated_testing_and_tdd.bai_tap.fizzbuzz_translate;

public class FizzBuzzTranslate {
    public static String translate(int number) {
        boolean isFizz = number % 3 == 0;
        boolean isBuzz = number % 5 == 0;
        if(isFizz && isBuzz)
            return "FizzBuzz";
        if(isFizz)
            return "Fizz";
        if(isBuzz)
            return "Buzz";
        return number + "";
    }
}
