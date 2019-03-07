# Gongoliers 5112 Robot Code for 2019!
## Destination: Deep Space

[![FIRST Destination: Deep Space Game Animation](https://github.com/Gongoliers/Robot2019/blob/master/deepspaceimage.png?raw=true)](https://www.youtube.com/watch?v=Mew6G_og-PI)

## Our 2019 Robot
This code was written specifically for The Gongoliers 2019 robot, although pieces of it may be reimplementable in other robots.  Our robot utilizes four subsystems:

#### Cargo Manipulator
The cargo manipulator is composed of two pneumatic pistons that make up the cargo "arm".  This arm is connected to a "wrist" which is controlled by a motor.  The manipulator itself attached to the wrist is composed of two horizontal rollers connected and jointly controlled by one motor.  Extending the arm allows the rollers to pick up a cargo ball from the floor, and aiming the wrist allows for deployment into the rocket or cargo bay.

#### Hatch Manipulator
The hatch manipulator is composed of two pneumatic pistons that eject hatches stuck to our manipulator through hook tape.  There is also an arm controlled by a motor that drops down to the floor allowing the robot to pick up hatches from the ground (also using hook tape).

#### HAB Climber
The HAB climber is composed of two small single-use pistons that drop "skids" in front of our bumpers.  One additional "giant piston" pushes down on the floor allowing the robot to climb to level 3 of the HAB.  The giant piston needs to be retracted once the front of the robot has gotten onto the HAB platform.

#### Drivetrain
Our West-Coast style chassis is composed of four Colson wheels in the front and two omni wheels in the rear.  We utilize arcade drive for moving the chassis.

### Autonomous/Sandstorm Capabilities
The robot will operate fully autonomously during the sandstorm period, unless the driver decides to cancel the auto command and take over control.  Our robot will be able to deliver at least one hatch during the 15-second sandstorm period with a goal of delivering two hatches.  There is one camera on the front and one camera on the rear of the robot, which are utilized both for driver vision and for automated vision targeting.  Automated vision targeting can be utilized during the sandstorm and throughout the match, allowing the robot to align itself with the rocket, cargo ship, and in the direction of cargo on the floor.

## [Library of Gongolierium](https://github.com/Gongoliers/Library-of-Gongolierium)
We utilize our in-house Library of Gongolierium to develop a more complex robot program much quicker.  Feel free to check out the library [here](https://github.com/Gongoliers/Library-of-Gongolierium) and take advantage of simple unit testing, higher level joysticks/controllers/sensors, built-in math functions, and easy to use motion profiling.  Check it out!

## Xbox Controller Mapping
The Gongoliers will be using two Xbox One controllers to drive and manipulate the 2019 robot.  One controller will be used for driving the chassis and another will be used for controlling the manipulators.  One joystick is also used for controlling the manipulators as an alternative controller design.

#### Driver Controller (port 0)
![Driver Controller Mapping Diagram](https://raw.githubusercontent.com/Gongoliers/Robot2019/master/Drivetrain%20Controller%20Mapping.png)

#### Manipulator Controller (port 1)
![Manipulator Controller Mapping Diagram](https://raw.githubusercontent.com/Gongoliers/Robot2019/master/Manipulator%20Controller%20Mapping.png)
