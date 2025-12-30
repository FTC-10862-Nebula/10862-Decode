package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.MecDrive;

public class DefaultDriveCommand extends CommandBase {
    private final MecDrive drive;
    private final GamepadEx driverGamepad;

    protected double multiplier;
    boolean isFieldCentric;

    public DefaultDriveCommand(MecDrive drive, GamepadEx driverGamepad, boolean isFieldCentric) {

        this.drive = drive;
        this.driverGamepad = driverGamepad;
        this.multiplier = 1.0;
        addRequirements(this.drive);

        this.isFieldCentric = isFieldCentric;
    }

    @Override
    public void execute() {
        if(driverGamepad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            multiplier = 0.55;
        } else {
            multiplier = 10;//5.5
        }
        if(driverGamepad.getButton(GamepadKeys.Button.A)) {
            drive.resetYaw();
        }
        if(isFieldCentric) {
            drive.driveFieldRelative(
                Math.sqrt(driverGamepad.getLeftY())* multiplier,
                Math.sqrt(driverGamepad.getLeftX())* multiplier,
                Math.sqrt(-driverGamepad.getRightX())* multiplier
            );
        } else{
            drive.driveRobotCentric(
                Math.sqrt(driverGamepad.getLeftY())* multiplier,
                Math.sqrt(driverGamepad.getLeftX())* multiplier,
                Math.sqrt(-driverGamepad.getRightX())* multiplier
            );
        }
    }
}