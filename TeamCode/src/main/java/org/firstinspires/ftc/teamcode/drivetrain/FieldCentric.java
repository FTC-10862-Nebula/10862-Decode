package org.firstinspires.ftc.teamcode.drivetrain;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FieldCentric extends SubsystemBase {
    private final DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    private final IMU imu;
    private double powerScale = 1.0;

    public FieldCentric(HardwareMap hardwareMap) {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fL");
        frontRightDrive = hardwareMap.get(DcMotor.class, "fR");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bL");
        backRightDrive = hardwareMap.get(DcMotor.class, "bR");

        // Motor directions
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    public void resetYaw() {
        imu.resetYaw();
    }

    public void setSlowMode(boolean slow) {
        powerScale = slow ? 0.5 : 1.0;
    }

    public void setPowerScale(double powerScale) {
        this.powerScale = powerScale;
    }

    // Field-centric control
    public void centricField(double forward, double right, double rotate) {
        double theta = Math.atan2(forward, right);
        double r = Math.hypot(right, forward);

        // Rotate based on the robot's current heading
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        theta = AngleUnit.normalizeRadians(theta - botHeading);

        double newForward = r * Math.sin(theta);
        double newRight = r * Math.cos(theta);

        drive(newForward, newRight, rotate);
       // newForward = -newForward;
    }


    // Robot-relative control
    public void drive(double forward, double right, double rotate) {
        double frontLeftPower = forward - right + rotate;
        double frontRightPower = forward + right - rotate;
        double backLeftPower = forward + right + rotate;
        double backRightPower = forward - right - rotate;

        double max = Math.max(1.0, Math.max(Math.abs(frontLeftPower),
                Math.max(Math.abs(frontRightPower),
                        Math.max(Math.abs(backLeftPower), Math.abs(backRightPower)))));

        frontLeftDrive.setPower((frontLeftPower / max) * powerScale);
        frontRightDrive.setPower((frontRightPower / max) * powerScale);
        backLeftDrive.setPower((backLeftPower / max) * powerScale);
        backRightDrive.setPower((backRightPower / max) * powerScale);
    }
}
