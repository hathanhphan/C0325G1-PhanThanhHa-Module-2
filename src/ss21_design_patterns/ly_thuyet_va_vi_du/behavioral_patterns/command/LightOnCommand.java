package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class LightOnCommand implements Command {
    private SmartLight light;
    private boolean wasOn;
    private int previousBrightness;

    public LightOnCommand(SmartLight light) {
        this.light = light;
    }

    @Override
    public void execute() {
        wasOn = light.isOn();
        previousBrightness = light.getBrightness();
        light.turnOn();
    }

    @Override
    public void undo() {
        if (wasOn) {
            light.turnOn();
            light.setBrightness(previousBrightness);
        } else {
            light.turnOff();
        }
    }

    @Override
    public String getDescription() {
        return "Bật đèn " + light.getLocation();
    }
}
