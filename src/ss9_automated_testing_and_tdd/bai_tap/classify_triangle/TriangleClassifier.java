package ss9_automated_testing_and_tdd.bai_tap.classify_triangle;

public class TriangleClassifier {
    public static String classifyTriangle(double sideA, double sideB, double sideC) {
        boolean is3sideOfTriangle = sideA > 0 && sideB > 0 && sideC > 0 && (sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA);
        if (is3sideOfTriangle) {
            boolean isEquilateralTriangle = sideA == sideB && sideA == sideC;
            boolean isIsoscelesTriangle = sideA == sideB || sideA == sideC || sideB == sideC;
            if (isEquilateralTriangle) {
                return "tam giác đều";
            } else if (isIsoscelesTriangle) {
                return "tam giác cân";
            } else {
                return "tam giác thường";
            }
        } else {
            return "không phải là tam giác";
        }
    }
}
