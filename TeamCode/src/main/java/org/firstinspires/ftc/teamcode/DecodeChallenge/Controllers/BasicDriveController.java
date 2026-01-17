package org.firstinspires.ftc.teamcode.DecodeChallenge.Controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DecodeChallenge.Systems.RobotMapping;

public class BasicDriveController {
    private RobotMapping _robotMapping;
    public DcMotor _leftFrontDrive;
    public DcMotor _rightFrontDrive;
    public DcMotor _leftBackDrive;
    public DcMotor _rightBackDrive;
    public DcMotor _yLeftEncoder;
    private int TicksPerRev = 2000;
    private double EncoderWheelDiameter = 1.82;
    private double TicksPerInch = TicksPerRev / EncoderWheelDiameter * Math.PI;
    private double _maxPower = -0.25;
    private int _targetTicks;


    public BasicDriveController(RobotMapping rbtMap) {
        _robotMapping = rbtMap;

        _leftFrontDrive = _robotMapping.FrontLeftDrive;
        _rightFrontDrive = _robotMapping.FrontRightDrive;
        _leftBackDrive = _robotMapping.BackLeftDrive;
        _rightBackDrive = _robotMapping.BackRightDrive;

        _yLeftEncoder = _robotMapping.YLeftEncoder;
    }
    public void SetTarget(double inches) {
        _targetTicks = (int)(inches * TicksPerInch);
        _yLeftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setAllMotorPowers(_maxPower);
    }

    public boolean IsDoneMoving(){
        if (_yLeftEncoder.getCurrentPosition() < _targetTicks){
            return false;
        }

        _targetTicks = -1; //resets ticker
        setAllMotorPowers(0);
        return true;
    }

    public void DebugOutput(Telemetry telemetry){
        telemetry.addData("Current Power", _leftBackDrive.getPower());
        telemetry.addData("Current Y Encoder", _yLeftEncoder.getCurrentPosition());
        telemetry.addData("Target Ticks", _targetTicks);
    }
    private void setAllMotorPowers(double power){
        _leftFrontDrive.setPower(power);
        _rightFrontDrive.setPower(power);
        _leftBackDrive.setPower(power);
        _rightBackDrive.setPower(power);
    }
}