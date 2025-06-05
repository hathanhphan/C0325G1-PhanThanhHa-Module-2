package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class SmartHomeRemote {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command lastCommand;
    private static final int SLOT_COUNT = 7;

    public SmartHomeRemote() {
        onCommands = new Command[SLOT_COUNT];
        offCommands = new Command[SLOT_COUNT];

        Command noCommand = new NoCommand();
        for (int i = 0; i < SLOT_COUNT; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        lastCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        if (slot >= 0 && slot < SLOT_COUNT) {
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
            System.out.println("Đã cài đặt lệnh cho slot " + slot + ": " + onCommand.getDescription());
        }
    }

    public void onButtonPressed(int slot) {
        if (slot >= 0 && slot < SLOT_COUNT) {
            System.out.println("Nhấn nút ON slot " + slot);
            onCommands[slot].execute();
            lastCommand = onCommands[slot];
        }
    }

    public void offButtonPressed(int slot) {
        if (slot >= 0 && slot < SLOT_COUNT) {
            System.out.println("Nhấn nút OFF slot " + slot);
            offCommands[slot].execute();
            lastCommand = offCommands[slot];
        }
    }

    public void undoButtonPressed() {
        System.out.println("Nhấn nút UNDO");
        lastCommand.undo();
    }

    public void displayRemote() {
        System.out.println("\n=== REMOTE CONTROL ===");
        for (int i = 0; i < SLOT_COUNT; i++) {
            System.out.println("Slot " + i + ": " + onCommands[i].getDescription() +
                    " | " + offCommands[i].getDescription());
        }
        System.out.println("=====================");
    }
}
