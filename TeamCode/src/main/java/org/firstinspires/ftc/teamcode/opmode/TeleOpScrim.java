package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DefaultDriveCommand;

import org.firstinspires.ftc.teamcode.subsystems.MecDrive;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

@TeleOp
public class TeleOpScrim extends MatchOpMode {
    private GamepadEx driverGamepad, operatorGamepad;
    private MecDrive drive;

    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);
        drive = new MecDrive();

    }

    @Override
    public void configureButtons() {
        drive.setDefaultCommand(new DefaultDriveCommand(drive, driverGamepad, true));
//        driverGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
//                .whenPressed(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.INTAKE)))
//                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));

//        driverGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
//                .whenPressed(new InstantCommand(() -> shooterScrim.setValue(ShooterScrim.Value.SHOOT)))
//                .whenReleased(new InstantCommand(() -> shooterScrim.setValue(ShooterScrim.Value.STOP)));
//
//        driverGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
//                .whenPressed(new InstantCommand(() ->shooterScrim.setValue(ShooterScrim.Value.OUTTAKE)))
//                .whenPressed(new InstantCommand(()-> powerIntake.setValue(PowerIntake.Value.OUTTAKE)))
//                .whenReleased(new InstantCommand(()-> shooterScrim.setValue(ShooterScrim.Value.STOP)))
//                .whenReleased(new InstantCommand(() -> powerIntake.setValue(PowerIntake.Value.STOP)));
    }
    
    @Override
    public void matchStart() {
    
    }

    @Override
    public void matchLoop() {

    }
}
