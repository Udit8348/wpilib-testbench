package frc.robot;

public class Constants {
    public static class ConfigConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
    }

    public static class CANConstants {
      public static final int kMotorID = 5;
    }

    public static class DrivetrainConstants {
      // CAN IDs for motor controllers
      public static final int kRightBackID = 1;
      public static final int kRightFrontID = 2;
      public static final int kLeftBackID = 3;
      public static final int kLeftFrontID = 4;
    }
}
