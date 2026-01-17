package org.firstinspires.ftc.teamcode.DecodeChallenge.Controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DecodeChallenge.Systems.RobotMapping;

public class BasicDriveController {
    private RobotMapping _robotMapping;
    public DcMotor _leftFrontDrive;
    public DcMotor _rightFrontDrive;
    public DcMotor _leftBackDrive;
    public DcMotor _rightBackDrive;
    public DcMotor _yLeftEncoder;
    public DcMotor _yRightEncoder;
    public DcMotor _xEncoder;
    public Telemetry _telemetry;
    private double _maxPower = 0.25;

    public BasicDriveController(RobotMapping rbtMap, Telemetry telemetry) {
        _robotMapping = rbtMap;
        _telemetry = telemetry;

        _leftFrontDrive = _robotMapping.FrontLeftDrive;
        _rightFrontDrive = _robotMapping.FrontRightDrive;
        _leftBackDrive = _robotMapping.BackLeftDrive;
        _rightBackDrive = _robotMapping.BackRightDrive;

        _yLeftEncoder = _robotMapping.YLeftEncoder;
        _yRightEncoder = _robotMapping.YRightEncoder;
        _xEncoder = _robotMapping.XEncoder;
    }

    private double _currentPos;
    public void DriveInchForward(double targetPos) {

        _telemetry.addData("YLeftEncoder Reading", _yLeftEncoder.getCurrentPosition());
        _telemetry.addData("YRightEncoder Reading", _yRightEncoder.getCurrentPosition());
        _telemetry.addData("XEncoder Reading", _xEncoder.getCurrentPosition());

        if (_currentPos != targetPos) {
            _leftFrontDrive.setPower(_maxPower);
            _rightFrontDrive.setPower(_maxPower);
            _leftBackDrive.setPower(_maxPower);
            _rightBackDrive.setPower(_maxPower);

        }
    }

    public void DriveCmForward(double targetPos){

        _telemetry.addData("YLeftEncoder Reading", _yLeftEncoder.getCurrentPosition());
        _telemetry.addData("YRightEncoder Reading", _yRightEncoder.getCurrentPosition());
        _telemetry.addData("XEncoder Reading", _xEncoder.getCurrentPosition());

        if (_currentPos != targetPos){
            _leftFrontDrive.setPower(_maxPower);
            _rightFrontDrive.setPower(_maxPower);
            _leftBackDrive.setPower(_maxPower);
            _rightBackDrive.setPower(_maxPower);
        }
    }
}