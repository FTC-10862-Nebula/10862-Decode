package org.firstinspires.ftc.teamcode.util.templates;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;

public class ActiveIntakeTemplate extends SubsystemBase {
    private final Telemetry telemetry;
    private final NebulaMotor[] motors;
    private double currentPower = 0.0;

    public ActiveIntakeTemplate(
            NebulaMotor[] motors,
            Telemetry telemetry
    ) {
        this.motors = motors;
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("Intake Power", currentPower);
    }

    public void setPower(double power) {
        currentPower = power;
        for (NebulaMotor motor : motors) {
            motor.setPower(power);
        }
    }
        public NebulaMotor[] getMotors(){
            return motors;
    }

}