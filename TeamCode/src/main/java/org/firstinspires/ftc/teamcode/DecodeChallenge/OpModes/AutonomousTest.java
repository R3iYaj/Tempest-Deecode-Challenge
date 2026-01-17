package org.firstinspires.ftc.teamcode.DecodeChallenge.OpModes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.DecodeChallenge.Controllers.BasicDriveController;
import org.firstinspires.ftc.teamcode.DecodeChallenge.Systems.RobotMapping;
import org.firstinspires.ftc.teamcode.DecodeChallenge.PedroPathing.Constants;
import org.firstinspires.ftc.teamcode.DecodeChallenge.Systems.DecodePathing;
import org.firstinspires.ftc.teamcode.DecodeChallenge.Systems.FireSequence;

@Autonomous(name="Autonomous Comp Test", group="Test")
public class AutonomousTest extends LinearOpMode {
    private enum RobotState { Start, Preloaded, MoveOffWall, MoveOffLaunch, Idle }

    private RobotMapping _robotMapping;
    private FireSequence _fireSequence;
    private Follower _follower;
    private RobotState _currentAutoState = RobotState.Start;
    private BasicDriveController _driveController;

    @Override
    public void runOpMode() {

        _robotMapping = new RobotMapping(hardwareMap);
        _follower = Constants.createFollower(hardwareMap);
        _fireSequence = new FireSequence(telemetry, _robotMapping);
        _driveController = new BasicDriveController(_robotMapping);

        telemetry.addData("OpMode", "Autonomous Comp Test");
        telemetry.addData("Robot State: ", _currentAutoState);
        telemetry.update();

        waitForStart();

        // NOTE: initialize launch variable and spin up the launcher.
        FireSequence.LaunchState launchState = FireSequence.LaunchState.Off;

        while (opModeIsActive()) {

            _follower.update();

            launchState = _fireSequence.GetStatus();

            switch (_currentAutoState){

                case Start:
                    _driveController.SetTarget(0.15);
                    _currentAutoState = RobotState.MoveOffWall;
                    break;

                case MoveOffWall:
                    if (_driveController.IsDoneMoving()){
                        _fireSequence.InitFireMode();
                        _currentAutoState = RobotState.Preloaded;
                    }
                    break;

                case Preloaded:

                    if (launchState == FireSequence.LaunchState.ReadyToFire){
                        _fireSequence.Fire();
                    }

                    if (launchState == FireSequence.LaunchState.Off){
                        _driveController.SetTarget(3);
                        _currentAutoState = RobotState.MoveOffLaunch;
                    }
                    break;

                case MoveOffLaunch:
                    if (_driveController.IsDoneMoving()){
                        _currentAutoState = RobotState.Idle;
                    }
                    break;

                case Idle:
                    // Do nothing
                    break;
            }

            telemetry.addData("Auto State", _currentAutoState);
            telemetry.addData("Fire State", launchState);
            telemetry.addData("Pos X", _follower.getPose().getX());
            telemetry.addData("Pos Y", _follower.getPose().getY());
            _driveController.DebugOutput(telemetry);
            telemetry.update();
        }
    }
}