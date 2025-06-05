package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Không có lệnh nào được gán");
    }

    @Override
    public void undo() {
        System.out.println("Không có gì để hoàn tác");
    }

    @Override
    public String getDescription() {
        return "Không có lệnh";
    }
}
