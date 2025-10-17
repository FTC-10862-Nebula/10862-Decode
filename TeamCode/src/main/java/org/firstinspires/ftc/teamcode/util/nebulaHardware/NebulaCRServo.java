package org.firstinspires.ftc.teamcode.util.nebulaHardware;
import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo.Direction.Forward;
import static org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo.Direction.Reverse;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.util.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class NebulaCRServo {
    private final CRServo servo;
    private final boolean isEnabled;

    /**
     * @param hM HardwareMap
     * @param deviceId The Name in the Config File
     * @param direction Direction of Servo Turning
     * @param isEnabled On or Off
     */
    public NebulaCRServo(HardwareMap hM, String deviceId,
                         Direction direction,
                         boolean isEnabled) {
        servo = new CRServo(hM, deviceId);
        this.isEnabled = isEnabled;
        switch (direction) {
            case FORWARD:
                setInverted(false);
                break;
//            case Reverse:
//                setInverted(true);
//                break;
        }
    }

    public void setPower(double power) {
        if (!isEnabled) servo.stopMotor();
        else servo.set(power);
    }

    public void stop() {
        servo.stopMotor();
    }

    public void setInverted(boolean isInverted) {
        servo.setInverted(isInverted);
    }

    public void close() {
        servo.disable();
    }
}
