package ss4_class_and_object_in_java.bai_tap.phuong_trinh_bac_hai;

public class LinearEquation {
    private double a;
    private double b;

    public LinearEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private double getRoot() {
        if (a != 0) {
            return -b / a;
        } else {
            return 0;
        }
    }
    public void solve(){
        if (a == 0) {
            if (b != 0) {
                System.out.println("Phương trình vô nghiệm.");
            } else {
                System.out.println("Phương trình vô số nghiệm.");
            }
        } else {
            System.out.printf("Phương trình có nghiệm duy nhất là %.2f\n", getRoot());
        }
    }
}
