package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorGoBildaPinpoint;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaServo;
import org.firstinspires.ftc.teamcode.util.templates.ClawTemplate;

// Import GoBILDA Pinpoint

public class Pivot extends ClawTemplate {
    private static final double TARGET_X = 0;
    private static final double TARGET_Y = 0;

    public Pivot(Telemetry telemetry, HardwareMap hw, boolean isEnabled) {
        super(
                new NebulaServo[]{
                        new NebulaServo(hw, "pivotR",
                                NebulaServo.Direction.Forward, isEnabled),
                        new NebulaServo(hw, "pivotL",
                                NebulaServo.Direction.Reverse, isEnabled)
                },
                telemetry
        );
    }

    public void setPosition(double pos) {
        setSetPoint(pos);
    }

    /**
     * Automatically adjust pivot angle using odometry position.
     * @param pinpoint Pinpoint odometry instance
     */
    public void autoAdjust(PinpointLocalizer pinpoint) {
        Pose2d pose = pinpoint.getPose();
        double x = pose.position.x;// your Pinpoint should give X position
        double y = pose.position.y; // and Y position

        double TARGET_X = 0.0;
        double TARGET_Y = 0.0;
        // Distance to target
        double dx = TARGET_X - x;
        double dy = TARGET_Y - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Simple linear mapping (tune with testing)
        double pos = 0.2 + 0.01 * distance;

        // Clamp to servo range
        pos = Math.max(0.0, Math.min(1.0, pos));

        setPosition(pos);

        telemetry.addData("Pivot AutoAdjust", "x: %.2f, y: %.2f, dist: %.2f, pos: %.2f",
                x, y, distance, pos);
    }
}