package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class LightOffCommand implements Command {
    private SmartLight light;
    private boolean wasOn;
    private int previousBrightness;

    public LightOffCommand(SmartLight light) {
        this.light = light;
    }

    @Override
    public void execute() {
        wasOn = light.isOn();
        previousBrightness = light.getBrightness();
        light.turnOff();
    }

    @Override
    public void undo() {
        if (wasOn) {
            light.turnOn();
            light.setBrightness(previousBrightness);
        }
    }

    @Override
    public String getDescription() {
        return "Tắt đèn " + light.getLocation();
    }
}
