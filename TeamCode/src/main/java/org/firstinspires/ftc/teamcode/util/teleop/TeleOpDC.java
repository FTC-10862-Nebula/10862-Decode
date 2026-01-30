package org.firstinspires.ftc.teamcode.util.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drivetrain.FieldCentric;
import org.firstinspires.ftc.teamcode.subsystems.intake.PowerIntake;
import org.firstinspires.ftc.teamcode.subsystems.outtake.Shooter;

@TeleOp
public class TeleOpDC extends MatchOpMode {
    private GamepadEx driverGamepad, operatorGamepad;
    private PowerIntake powerIntake;
    private Shooter shooter;
    private FieldCentric drive;
    private boolean fieldCentricEnabled = false;
    boolean lastReset = false;


    @Override
    public void robotInit() {
        drive = new FieldCentric(hardwareMap);
        drive.resetYaw();
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);
        shooter = new Shooter(telemetry, hardwareMap, true);
        powerIntake = new PowerIntake(telemetry, hardwareMap, true);
    }

    @Override
    public void configureButtons() {
        operatorGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.INTAKE)))
                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));

        operatorGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> shooter.setValue(Shooter.Value.SHOOT)))
                .whenReleased(new InstantCommand(() -> shooter.setValue(Shooter.Value.STOP)));

        operatorGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(() -> shooter.setValue(Shooter.Value.SHOOTFAR)))
                .whenReleased(new InstantCommand(() -> shooter.setValue(Shooter.Value.STOP)));

        operatorGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() ->shooter.setValue(Shooter.Value.OUTTAKE)))
                .whenPressed(new InstantCommand(()-> powerIntake.setValue(PowerIntake.Value.OUTTAKE)))
                .whenReleased(new InstantCommand(()-> shooter.setValue(Shooter.Value.STOP)))
                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));
        driverGamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(() -> drive.resetYaw()));
    }
    @Override
    public void matchStart() {
        fieldCentricEnabled = true;
        powerIntake.setEnabled(true);
        powerIntake.setValue(PowerIntake.Value.STOP);
    }

    @Override
    public void matchLoop() {

        double leftTrigger = driverGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        if (leftTrigger > 0.5) {
            drive.setSlowMode(true);
        } else { drive.setPowerScale(1.0);
        }




        if (fieldCentricEnabled) {
            drive.drive(
                    -gamepad1.left_stick_y,   // forward
                    gamepad1.left_stick_x,   // strafe
                    -gamepad1.right_stick_x   // rotate
            );
        } else {
            drive.drive(
                    -gamepad1.left_stick_y,   // forward
                    gamepad1.left_stick_x,   // strafe
                    -gamepad1.right_stick_x   // rotate
            );
        }
    }
}
