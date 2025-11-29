package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake.PowerIntake;
import org.firstinspires.ftc.teamcode.subsystems.outtake.ShooterScrim;

@Autonomous(name = "Test", group = "Auto")
public class Test extends LinearOpMode {
    private final Pose2d beginPose = new Pose2d(-50, -46, Math.toRadians(45));
    private final Pose2d parkPose = new Pose2d(-60,-20,Math.toRadians(45));
    MecanumDrive drive;
    ShooterScrim shooterScrim;
    PowerIntake powerIntake;
    @Override
    public void runOpMode() throws InterruptedException {
        robotInit();
        while (!opModeIsActive() && !isStopRequested()) {
        }

        waitForStart();
        // Enable subsystems after start
        shooterScrim.setEnabled(true);
        powerIntake.setEnabled(true);

        if (opModeIsActive()) {
            // Drive forward to target
            Action toGoal = drive.actionBuilder(beginPose)
                    .lineToX(-20)
                    .strafeTo(new Vector2d(-60,-20))
                    .build();
            Actions.runBlocking(toGoal);

            shooterScrim.setValue(ShooterScrim.Value.SHOOTAUTO);
                long start = System.currentTimeMillis();
                while (opModeIsActive() && System.currentTimeMillis() - start < 3000) {
                    shooterScrim.periodic();
                    telemetry.update(); }
                    powerIntake.setValue(PowerIntake.Value.AUTOINTAKE);
                    start = System.currentTimeMillis();
                    while (opModeIsActive() && System.currentTimeMillis() - start < 1500) {
                        powerIntake.periodic();
                        telemetry.update();
                    }

// Intake 4 seconds

// Stop both
            shooterScrim.setValue(ShooterScrim.Value.STOP);
            powerIntake.setValue(PowerIntake.Value.STOP);
            shooterScrim.periodic();
            powerIntake.periodic();
        }
    }
    void robotInit() {
        // Initialize the drive using the starting pose
        drive = new MecanumDrive(hardwareMap, beginPose);
        powerIntake = new PowerIntake(telemetry, hardwareMap, true);
        shooterScrim = new ShooterScrim(telemetry, hardwareMap,true);
    }
}
