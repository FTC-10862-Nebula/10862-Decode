package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.drivetrain.Drive;

public class DriveTeleop extends CommandBase {
    private final Drive drive;
    private final GamepadEx driver;

    public DriveTeleop(Drive drive, GamepadEx driver) {
        this.drive=drive;
        this.driver=driver;

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        drive.driveFieldRelative(-driver.getLeftY(),
                driver.getLeftX(),
                driver.getRightX());
    }
}
