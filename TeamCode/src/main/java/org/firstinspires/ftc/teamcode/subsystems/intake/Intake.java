package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.templates.ActiveIntakeTemplate;

public class Intake extends ActiveIntakeTemplate {
    private final Telemetry telemetry;
    private final boolean isEnabled;

    public enum IntakeValue {
        INTAKE(1.0),

        AUTOINTAKE(1.0),
        OUTTAKE(-1.0),
        STOP(0.0);

        private final double power;
        IntakeValue(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }

    public Intake(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
            new NebulaMotor[]{ new NebulaMotor(
                hw, "intake",
                DcMotorSimple.Direction.REVERSE,
                DcMotor.ZeroPowerBehavior.FLOAT,
                true)},
            telemetry);
        this.telemetry = telemetry;
        this.isEnabled = isEnabled;
        setValue(IntakeValue.STOP);
    }

    @Override
    public void periodic() {
        super.periodic();
        telemetry.addData("Intake Enabled", isEnabled);
    }

    public void setValue(IntakeValue intakeValue) {
        setPower(intakeValue.power);
    }
}