package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;
import org.firstinspires.ftc.teamcode.util.templates.ClawTemplate;
@Deprecated
public class LockServo extends ClawTemplate {
    public Telemetry telemetry;
    public enum Value {
        LOCK(0.67),
        REST(0.34);

        public final double pos;
        Value(double pos) {
            this.pos = pos;
        }
    }

    public LockServo(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
        new NebulaServo[]{
                new NebulaServo(hw, "lock",
                        NebulaServo.Direction.Forward, isEnabled),
    },
                telemetry
        );
}

    public void setPosition(Value value) {
        setSetPoint(value);
    }

}
