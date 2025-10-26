package org.firstinspires.ftc.teamcode.subsystems.intake;
import static com.arcrobotics.ftclib.util.Direction.FORWARD;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaCRServo;
import org.firstinspires.ftc.teamcode.util.templates.IndexerTemplate;

public class Indexer extends IndexerTemplate {
    private NebulaCRServo[] CRservos;
    public Telemetry telemetry;
public enum Value {
    SHOOT(1.0),
    REST(0.0);

    public final double power;
    Value(double power) {
        this.power = power;
    }
}

    public Indexer(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
                new NebulaCRServo[]{
                        new NebulaCRServo(hw,
                                "indexer1",
                                FORWARD,
                                isEnabled),
                        new NebulaCRServo(hw,
                                "indexer2",
                                FORWARD,
                                isEnabled),
                        new NebulaCRServo(hw,
                                "indexer3",
                                FORWARD,
                                isEnabled)
                },


                telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("IndexerSpeed:", getPower());
    }

    public void setPower(double power, NebulaCRServo servo) {
        for (NebulaCRServo nebulaCRServo : CRservos) {
            servo.setPower(power);
        }
    }

    public double getPower() {
        return 0.0;
    }
}