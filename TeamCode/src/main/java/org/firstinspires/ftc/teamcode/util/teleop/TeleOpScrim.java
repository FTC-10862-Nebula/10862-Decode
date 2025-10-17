package org.firstinspires.ftc.teamcode.util.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drivetrain.FieldCentric;
import org.firstinspires.ftc.teamcode.subsystems.intake.PowerIntake;
import org.firstinspires.ftc.teamcode.subsystems.outtake.ShooterScrim;

@TeleOp
public class TeleOpScrim extends MatchOpMode {
    private GamepadEx driverGamepad, operatorGamepad;
    private PowerIntake powerIntake;
    private ShooterScrim shooterScrim;
    private FieldCentric fieldCentric;
    private Limelight3A limelight3A;
    private boolean drivetrainEnabled = true;

    @Override
    public void robotInit() {
        fieldCentric = new FieldCentric(hardwareMap);
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);
    //    limelight3A = new Limelight3A(172.29.0.28, "Limelight3A", 172.29.0.1);

        shooterScrim = new ShooterScrim(telemetry, hardwareMap, true);
        powerIntake = new PowerIntake(telemetry, hardwareMap, true);
    }

    @Override
    public void configureButtons() {
        driverGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.INTAKE)))
                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));

        driverGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
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

    }

    @Override
    public void matchLoop() {
        if (drivetrainEnabled) {
            fieldCentric.setSlowMode(isStarted());
            // Reset IMU yaw
            if (driverGamepad.getButton(GamepadKeys.Button.A)) {
                fieldCentric.resetYaw();
            }

            // Switch between robot-relative and field-centric drive
            if (driverGamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                fieldCentric.drive(
                        -driverGamepad.getLeftY(),
                        driverGamepad.getLeftX(),
                        driverGamepad.getRightX()
                );
            } else {
                fieldCentric.driveFieldRelative(
                        -driverGamepad.getLeftY(),
                        driverGamepad.getLeftX(),
                        driverGamepad.getRightX()
                );
            }
        }

        telemetry.addLine("Press A to reset Yaw");
        telemetry.addLine("Hold left bumper for robot-relative drive");
        telemetry.update();
    }
}