package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.templates.ActiveIntakeTemplate;
public class Shooter extends ActiveIntakeTemplate {
    private final Telemetry telemetry;
    private Value currentValue = Value.STOP;
    private boolean isEnabled;

    public enum Value {
        SHOOT(0.6),
        SHOOTFAR(0.95),
        SHOOTAUTO(0.4),
        OUTTAKE(-1),
        STOP(0.0);

        private final double power;
        Value(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }

    public Shooter(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
                new NebulaMotor[]{ new NebulaMotor(
                        hw, "shooter",
                        DcMotorSimple.Direction.FORWARD,
                        DcMotor.ZeroPowerBehavior.BRAKE,
                        true),
                new NebulaMotor(
                        hw, "shooter2",
                        DcMotorSimple.Direction.REVERSE,
                        DcMotor.ZeroPowerBehavior.BRAKE,
                        true)
                },
                telemetry
        );

        this.telemetry = telemetry;
        this.isEnabled = isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
    @Override
    public void periodic()
    {
        super.periodic();
        if (isEnabled) setPower(currentValue.getPower());
        else setPower(0);

        telemetry.addData("Shooter Mode", currentValue);
        telemetry.addData("Shooter Enabled", isEnabled);
    }

    // instead of raw double, use your enum
    public void setValue(Value value) {
        this.currentValue = value;
    }
    public void stop() {
        setValue(Value.STOP);
    }
}
