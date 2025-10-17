package org.firstinspires.ftc.teamcode.util.templates;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaCRServo;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;

public class IndexerTemplate {
    private final Telemetry telemetry;
    private final NebulaCRServo[] CRservos;

    public IndexerTemplate(
            NebulaCRServo[] CRservos,
            Telemetry telemetry
    ) {
        this.CRservos = CRservos;
        this.telemetry = telemetry;
    }

    public void periodic() {
    }
    public void setPower(double power) {
        for (NebulaCRServo servo : CRservos) {
            servo.setPower(power);
        }
    }
    public NebulaCRServo[] getServos() {
        return CRservos;
    }
}