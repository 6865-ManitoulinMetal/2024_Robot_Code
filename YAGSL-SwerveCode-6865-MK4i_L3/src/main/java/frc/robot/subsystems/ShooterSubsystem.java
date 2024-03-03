package frc.robot.subsystems;

public class ShooterSubsystem {
    
}

public class shooter
    {
public static void main(string[args])

{
Talon shooter = new Talon(4);
Talon launcher = new Talon(5);
XboxController xBox1 = new XboxController(0);
shooter.set(0)//set(Controlmode.velocity, 0)


if(xBox1.buttonApressed)//this is just so we have something in the if statment
{
    shooter.set(1); //set(Controlmode.velocity, 500);
    StopWatch(2000);


    launcher.set(0.1); //set(Controlmode.velocity, 50);
    StopWatch(500);


    shooter.set(0); //set(Controlmode.velocity, 0);
    launcher.set(0); //set(Controlmode.velocity, 0);
}
    }

}
