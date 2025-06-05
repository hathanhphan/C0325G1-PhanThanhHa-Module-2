package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class MacroCommand implements Command {
    private Command[] commands;
    private String description;

    public MacroCommand(Command[] commands, String description) {
        this.commands = commands;
        this.description = description;
    }

    @Override
    public void execute() {
        System.out.println("=== Thực hiện: " + description + " ===");
        for (Command command : commands) {
            command.execute();
        }
        System.out.println("=== Hoàn thành: " + description + " ===");
    }

    @Override
    public void undo() {
        System.out.println("=== Hoàn tác: " + description + " ===");
        // Thực hiện undo theo thứ tự ngược lại
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
        System.out.println("=== Hoàn tác xong: " + description + " ===");
    }

    @Override
    public String getDescription() {
        return description;
    }
}
