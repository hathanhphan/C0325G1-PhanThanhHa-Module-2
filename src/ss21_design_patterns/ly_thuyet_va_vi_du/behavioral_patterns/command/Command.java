package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public interface Command {
    void execute();
    void undo();
    String getDescription();
}
