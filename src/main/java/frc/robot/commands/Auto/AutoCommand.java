// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FunelCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.Shootercommand;
import frc.robot.commands.TurretBangBangController;
import frc.robot.subsystems.Drivesubsysteme;
import frc.robot.subsystems.Funelsubsytem;
import frc.robot.subsystems.Intakesubsystem;
import frc.robot.subsystems.Shootersubsystem;
import frc.robot.subsystems.Turetsubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCommand extends SequentialCommandGroup {
  /** Creates a new AutoCommand. */
  
  public AutoCommand(VisionSubsystem m_vision, Turetsubsystem m_turret, Funelsubsytem m_funel, Shootersubsystem m_shooter, Intakesubsystem m_intake, Drivesubsysteme m_drive) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new TurretBangBangController(m_vision,m_turret),
    new Shootercommand(m_shooter, 1).withTimeout(3)
    .raceWith(new FunelCommand(m_funel, 1),new Shootercommand(m_shooter, 1)).withTimeout(3)
    .andThen(new IntakeCommand(m_intake, 1))
    
    );
  }
}
