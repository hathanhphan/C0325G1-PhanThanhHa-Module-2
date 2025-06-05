package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class AirConditionerOnCommand implements Command {
    private AirConditioner ac;
    private boolean wasOn;
    private int previousTemp;

    public AirConditionerOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        wasOn = ac.isOn();
        previousTemp = ac.getTemperature();
        ac.turnOn();
    }

    @Override
    public void undo() {
        if (wasOn) {
            ac.turnOn();
            ac.setTemperature(previousTemp);
        } else {
            ac.turnOff();
        }
    }

    @Override
    public String getDescription() {
        return "Bật điều hòa " + ac.getLocation();
    }
}
