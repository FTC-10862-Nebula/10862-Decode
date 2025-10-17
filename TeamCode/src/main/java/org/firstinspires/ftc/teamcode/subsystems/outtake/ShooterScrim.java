package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.intake.PowerIntake;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.templates.ActiveIntakeTemplate;

public class ShooterScrim extends ActiveIntakeTemplate {
    private final Telemetry telemetry;
    private Value currentValue = Value.STOP; // default

    public enum Value {
        SHOOT(1.0),
        OUTTAKE(-1.0),
        STOP(0.0);

        private final double power;
        Value(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }

    public ShooterScrim(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
                new NebulaMotor[]{ new NebulaMotor(
                        hw, "shooter",
                        DcMotorSimple.Direction.FORWARD,
                        DcMotor.ZeroPowerBehavior.BRAKE,
                        isEnabled)},
                telemetry);
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        super.periodic();
        // actually apply the value each cycle
        setPower(currentValue.getPower());
        telemetry.addData("Shooter Mode", currentValue);
    }

    // instead of raw double, use your enum
    public void setValue(Value value) {
        this.currentValue = value;
    }

    public void stop() {
        setValue(Value.STOP);
    }
}
