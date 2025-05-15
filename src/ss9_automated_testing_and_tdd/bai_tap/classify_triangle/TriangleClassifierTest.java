package ss9_automated_testing_and_tdd.bai_tap.classify_triangle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleClassifierTest {

    @Test
    public void testEquilateralTriangle() {
        // Test case: All sides equal (2, 2, 2)
        String result = TriangleClassifier.classifyTriangle(2, 2, 2);
        assertEquals("tam giác đều", result, "Triangle with sides 2, 2, 2 should be equilateral");
    }

    @Test
    public void testIsoscelesTriangle() {
        // Test case: Two sides equal (2, 2, 3)
        String result = TriangleClassifier.classifyTriangle(2, 2, 3);
        assertEquals("tam giác cân", result, "Triangle with sides 2, 2, 3 should be isosceles");

        // Additional test cases for isosceles triangles with different sides equal
        String result2 = TriangleClassifier.classifyTriangle(2, 3, 2);
        assertEquals("tam giác cân", result2, "Triangle with sides 2, 3, 2 should be isosceles");

        String result3 = TriangleClassifier.classifyTriangle(3, 2, 2);
        assertEquals("tam giác cân", result3, "Triangle with sides 3, 2, 2 should be isosceles");
    }

    @Test
    public void testScaleneTriangle() {
        // Test case: No sides equal (3, 4, 5)
        String result = TriangleClassifier.classifyTriangle(3, 4, 5);
        assertEquals("tam giác thường", result, "Triangle with sides 3, 4, 5 should be scalene");
    }

    @Test
    public void testNotATriangle_ViolatesTriangleInequality() {
        // Test case: One side is too long (8, 2, 3)
        String result = TriangleClassifier.classifyTriangle(8, 2, 3);
        assertEquals("không phải là tam giác", result,
                "Sides 8, 2, 3 violate triangle inequality and should not form a triangle");
    }

    @Test
    public void testNotATriangle_NegativeSide() {
        // Test case: Negative side (-1, 2, 1)
        String result = TriangleClassifier.classifyTriangle(-1, 2, 1);
        assertEquals("không phải là tam giác", result,
                "Triangle with negative side -1 should not be a valid triangle");
    }

    @Test
    public void testNotATriangle_ZeroSide() {
        // Test case: Zero side (0, 1, 1)
        String result = TriangleClassifier.classifyTriangle(0, 1, 1);
        assertEquals("không phải là tam giác", result,
                "Triangle with side 0 should not be a valid triangle");
    }

    @Test
    public void testAdditionalTriangleInequality() {
        // Additional test cases for triangle inequality
        String result1 = TriangleClassifier.classifyTriangle(1, 2, 3);
        assertEquals("không phải là tam giác", result1,
                "Sides 1, 2, 3 violate triangle inequality (1+2=3) and should not form a triangle");

        String result2 = TriangleClassifier.classifyTriangle(7, 2, 3);
        assertEquals("không phải là tam giác", result2,
                "Sides 7, 2, 3 violate triangle inequality (2+3<7) and should not form a triangle");
    }

    @Test
    public void testBoundaryValues() {
        // Testing with very small valid values
        String result = TriangleClassifier.classifyTriangle(0.1, 0.1, 0.1);
        assertEquals("tam giác đều", result,
                "Triangle with very small equal sides should be equilateral");

        // Testing with very large values
        String result2 = TriangleClassifier.classifyTriangle(1000000, 1000000, 1000000);
        assertEquals("tam giác đều", result2,
                "Triangle with very large equal sides should be equilateral");
    }
}

