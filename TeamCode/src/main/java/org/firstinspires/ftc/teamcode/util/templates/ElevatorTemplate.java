package org.firstinspires.ftc.teamcode.util.templates;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;

public class ElevatorTemplate extends SubsystemBase {
    private final Telemetry telemetry;
    private final NebulaMotor[] motors;
    private double setPoint = 0.0;
    private final PIDFController pidf;

    public ElevatorTemplate(
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

        double output = pidf.calculate(getPosition(), setPoint);

        for (NebulaMotor motor : motors) {
            motor.setPower(output);
        }

        telemetry.addData("Elevator Encoder", setPoint);
        telemetry.addData("Elevator Position", getPosition());
    }

    public double getPosition() {
        return motors[0].getPosition();
    }
    public void resetEncoder() {
        for (NebulaMotor motor : motors) {
            motor.resetEncoder();
        }
    }
    public void setSetPoint(double target) {
        setPoint = target;
    }
    public double getSetPoint() {
        return setPoint;
    }

    public NebulaMotor[] getMotors() {
        return motors;
    }
}
