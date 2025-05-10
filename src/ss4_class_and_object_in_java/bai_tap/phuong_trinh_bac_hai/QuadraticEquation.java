package ss4_class_and_object_in_java.bai_tap.phuong_trinh_bac_hai;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;
    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private double getDiscriminant() {
        return Math.pow(b, 2) - 4 * a * c;
    }

    private double getRoot1() {
        if (this.getDiscriminant() >= 0) {
            return (-b + Math.pow(this.getDiscriminant(), 0.5)) / (2 * a);
        } else {
            return 0;
        }
    }
    private double getRoot2() {
        if (this.getDiscriminant() >= 0) {
            return (-b - Math.pow(this.getDiscriminant(), 0.5)) / (2 * a);
        } else {
            return 0;
        }
    }
    public void solve() {
        if (a == 0) {
            LinearEquation linearEquation = new LinearEquation(b, c);
            linearEquation.solve();
        } else {
            double delta = this.getDiscriminant();
            if (delta < 0) {
                System.out.println("Phương trình vô nghiệm");
            } else if (delta == 0) {
                System.out.printf("Phương trình có nghiệm duy nhất là %.2f", this.getRoot1());
            } else {
                System.out.printf("Phương trình có 2 nghiệm là %.2f và %.2f", this.getRoot1(), this.getRoot2());
            }
        }
    }
}
