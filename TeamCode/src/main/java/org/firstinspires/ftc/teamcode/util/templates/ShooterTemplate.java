package org.firstinspires.ftc.teamcode.util.templates;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;

public class ShooterTemplate extends SubsystemBase {
    private final Telemetry telemetry;
    private final NebulaMotor[] motors;
    private final PIDFController pidf;
    private double targetVelocity = 0.0; // desired shooter velocity

    public ShooterTemplate(
            NebulaMotor[] motors,
            Telemetry telemetry,
            PIDFController pidf
    ) {
        this.motors = motors;
        this.telemetry = telemetry;
        this.pidf = pidf;
    }

    @Override
    public void periodic() {
        double currentVelocity = motors[0].getVelocity(); // get feedback from motor
        double output = pidf.calculate(currentVelocity, targetVelocity);

        // Clamp output to motor power range (-1 to 1)
        output = Math.max(-1, Math.min(1, output));

        // Prevent small oscillations when target velocity is low
        if (Math.abs(targetVelocity) < 10) {
            output = 0;
        }
        // Apply PIDF output to all shooter motors
        for (NebulaMotor motor : motors) {
            motor.setPower(output);
        }

        telemetry.addData("Target Velocity", targetVelocity);
        telemetry.addData("Current Velocity", currentVelocity);
        telemetry.addData("Motor Output", output);
        telemetry.update();
    }

    public void setVelocity(double velocity) {
        this.targetVelocity = velocity;
    }

    public double getVelocity() {
        return targetVelocity;
    }

    public void resetEncoders() {
        for (NebulaMotor motor : motors) {
            motor.resetEncoder();
        }
    }
}

