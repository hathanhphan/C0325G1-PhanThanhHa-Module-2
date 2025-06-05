package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

/*
* Command Pattern đóng gói một yêu cầu thành một đối tượng,
* cho phép bạn tham số hóa client với các yêu cầu khác nhau,
* xếp hàng đợi các yêu cầu, và hỗ trợ các hoạt động có thể hoàn tác.
* */

public class Main {
    public static void main(String[] args) {
        // Tạo các thiết bị
        SmartLight livingRoomLight = new SmartLight("phòng khách");
        SmartLight bedroomLight = new SmartLight("phòng ngủ");
        AirConditioner livingRoomAC = new AirConditioner("phòng khách");

        // Tạo remote control
        SmartHomeRemote remote = new SmartHomeRemote();

        // Tạo các lệnh
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        LightOnCommand bedroomLightOn = new LightOnCommand(bedroomLight);
        LightOffCommand bedroomLightOff = new LightOffCommand(bedroomLight);

        AirConditionerOnCommand acOn = new AirConditionerOnCommand(livingRoomAC);
        Command acOff = new Command() {
            private boolean wasOn;
            private int previousTemp;

            @Override
            public void execute() {
                wasOn = livingRoomAC.isOn();
                previousTemp = livingRoomAC.getTemperature();
                livingRoomAC.turnOff();
            }

            @Override
            public void undo() {
                if (wasOn) {
                    livingRoomAC.turnOn();
                    livingRoomAC.setTemperature(previousTemp);
                }
            }

            @Override
            public String getDescription() {
                return "Tắt điều hòa phòng khách";
            }
        };

        // Cài đặt lệnh cho remote
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, bedroomLightOn, bedroomLightOff);
        remote.setCommand(2, acOn, acOff);

        // Tạo macro command - "Về nhà"
        Command[] comeHomeCommands = {
                livingRoomLightOn,
                bedroomLightOn,
                acOn
        };
        MacroCommand comeHomeCommand = new MacroCommand(comeHomeCommands, "Về nhà");

        Command[] leaveHomeCommands = {
                livingRoomLightOff,
                bedroomLightOff,
                acOff
        };
        MacroCommand leaveHomeCommand = new MacroCommand(leaveHomeCommands, "Rời khỏi nhà");

        remote.setCommand(6, comeHomeCommand, leaveHomeCommand);

        // Hiển thị remote
        remote.displayRemote();

        // Test các lệnh
        System.out.println("\n--- Test điều khiển từng thiết bị ---");
        remote.onButtonPressed(0);  // Bật đèn phòng khách
        remote.onButtonPressed(1);  // Bật đèn phòng ngủ
        remote.onButtonPressed(2);  // Bật điều hòa

        System.out.println("\n--- Test undo ---");
        remote.undoButtonPressed(); // Hoàn tác lệnh cuối (tắt điều hòa)

        System.out.println("\n--- Test macro command ---");
        remote.offButtonPressed(6); // Rời khỏi nhà (tắt tất cả)

        System.out.println("\n--- Test undo macro ---");
        remote.undoButtonPressed(); // Hoàn tác macro (bật lại tất cả)

        System.out.println("\n--- Test macro về nhà ---");
        remote.onButtonPressed(6);  // Về nhà (bật tất cả)
    }
}
