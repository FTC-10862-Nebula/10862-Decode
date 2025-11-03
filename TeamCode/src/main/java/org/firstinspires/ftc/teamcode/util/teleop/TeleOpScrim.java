package org.firstinspires.ftc.teamcode.util.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drivetrain.Drive;
import org.firstinspires.ftc.teamcode.drivetrain.FieldCentric;
import org.firstinspires.ftc.teamcode.subsystems.intake.PowerIntake;
import org.firstinspires.ftc.teamcode.subsystems.outtake.ShooterScrim;

@TeleOp
public class TeleOpScrim extends MatchOpMode {
    private GamepadEx driverGamepad, operatorGamepad;
    private PowerIntake powerIntake;
    private ShooterScrim shooterScrim;
    private FieldCentric drive;
    private boolean fieldCentricEnabled = true;

    @Override
    public void robotInit() {
        drive = new FieldCentric(hardwareMap);
        drive.resetYaw();
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);
        shooterScrim = new ShooterScrim(telemetry, hardwareMap, true);
        powerIntake = new PowerIntake(telemetry, hardwareMap, true);

    }

    @Override
    public void configureButtons() {
        driverGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.INTAKE)))
                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));

        driverGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> shooterScrim.setValue(ShooterScrim.Value.SHOOT)))
                .whenReleased(new InstantCommand(() -> shooterScrim.setValue(ShooterScrim.Value.STOP)));

        driverGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() ->shooterScrim.setValue(ShooterScrim.Value.OUTTAKE)))
                .whenPressed(new InstantCommand(()-> powerIntake.setValue(PowerIntake.Value.OUTTAKE)))
                .whenReleased(new InstantCommand(()-> shooterScrim.setValue(ShooterScrim.Value.STOP)))
                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));
    }

    @Override
    public void matchStart() {
        fieldCentricEnabled = true;
    }

    @Override
    public void matchLoop() {

//        if (driverGamepad.getButton(GamepadKeys.Button.Y)) {
//            powerIntake.setPower(1);
//        } else {
//            powerIntake.setPower(1);
 //       }
        double rightTrigger = driverGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        if (rightTrigger > 0.5) {
            drive.setSlowMode(true);

        } else { drive.setPowerScale(1);
        }


        if (fieldCentricEnabled) {
            drive.drive(
                    -gamepad1.left_stick_y,  // forward
                    -gamepad1.left_stick_x,   // strafe
                    gamepad1.right_stick_x   // rotate
            );
        } else {
            drive.centricField(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    gamepad1.right_stick_x
            );
        }
    }
}
