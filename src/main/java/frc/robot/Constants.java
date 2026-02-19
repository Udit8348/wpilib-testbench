package frc.robot;

public class Constants {
    public static class ConfigConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
    }

    public static class CANConstants {
      public static final int kIndexerID = 6;
      public static final int kFlywheelID = 5;
    }

    public static class DrivetrainConstants {
      //OG CAN IDs for motor controllers
      public static final int kRightBackID = 4;
      public static final int kRightFrontID = 3; // use  as the leader since it has a working motor
      public static final int kLeftBackID = 1;
      public static final int kLeftFrontID = 2;
    }
}
