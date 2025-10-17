package org.firstinspires.ftc.teamcode.util.templates;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

public class ArmTemplate {
    private final Telemetry telemetry;
    private final NebulaServo[] servos;

    public ArmTemplate(
            NebulaServo[] servos,
            Telemetry telemetry
    ) {
        this.servos = servos;
        this.telemetry = telemetry;
    }

    public void periodic() {
        for (NebulaServo servo : servos) {
            telemetry.addData("ArmPos", servo.getPosition());
        }
    }
    public void setSetPoint(double position) {
        for (NebulaServo servo : servos) {
            servo.setPosition(position);
        }
    }
    public NebulaServo[] getServos() {
        return servos;
    }
}