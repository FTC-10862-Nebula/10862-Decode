package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;
import org.firstinspires.ftc.teamcode.util.templates.ArmTemplate;
@Deprecated
public class DropDown extends ArmTemplate {
    private Telemetry telemetry;

    public DropDown(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
                new NebulaServo[]{
                        new NebulaServo(hw,
                                "dropRF",
                                NebulaServo.Direction.Forward,
                                isEnabled),

                        new NebulaServo(hw,
                                "dropLF",
                                NebulaServo.Direction.Reverse,
                                isEnabled)
                },
                telemetry
        );
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        super.periodic();
        telemetry.addData("dropdown", "down");
        telemetry.addData("dropdown", "up");
    }
    public void moveToDown() {
        setSetPoint(0);
    }

    public void moveToUp() {
        setSetPoint(1);
    }
   }