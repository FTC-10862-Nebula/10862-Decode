package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.templates.ElevatorTemplate;

public class Lift extends ElevatorTemplate {
    private final Telemetry telemetry;
    public enum Value {
        REST(1.0),
        EXTEND(1.0);
        public final double pos;
        Value(double pos)
        { this.pos = pos; } }
    public Lift(HardwareMap hw, Telemetry telemetry, boolean isEnabled) {
        super(
                new NebulaMotor[] {
                        new NebulaMotor(hw, "motorRight",
                                DcMotorSimple.Direction.FORWARD,
                                DcMotor.ZeroPowerBehavior.BRAKE, isEnabled),

                        new NebulaMotor(hw, "motorLeft",
                                DcMotorSimple.Direction.REVERSE,
                                DcMotor.ZeroPowerBehavior.BRAKE, isEnabled)
                },
                telemetry,
                new PIDFController(0, 0, 0, 0)
        );
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        super.periodic();
        telemetry.addData("Lift", getSetPoint());
    }

    public void setPosition(Value value) { setSetPoint(value.pos); }
}
