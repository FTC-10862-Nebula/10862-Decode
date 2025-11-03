package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.templates.ActiveIntakeTemplate;

public class PowerIntake extends ActiveIntakeTemplate {
    private final Telemetry telemetry;
    private Value currentValue = Value.STOP; // default
    private boolean isEnabled;

    public enum Value {
        INTAKE(1.0),
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

    public PowerIntake(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
                new NebulaMotor[]{ new NebulaMotor(
                        hw, "intake",
                        DcMotorSimple.Direction.REVERSE,
                        DcMotor.ZeroPowerBehavior.BRAKE,
                        true)},
                telemetry);
        this.telemetry = telemetry;
        new PIDFController(0.0045,0,0,0);
        this.isEnabled = isEnabled;
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isEnabled) setPower(currentValue.getPower());
        else setPower(0);
        telemetry.addData("Intake Mode", currentValue);
        telemetry.addData("Intake Enabled", isEnabled);
    }

    // instead of raw double, use your enum
    public void setValue(Value value) {
        this.currentValue = value;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}