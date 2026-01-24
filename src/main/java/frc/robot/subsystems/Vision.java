package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    private final PhotonCamera camera;
    private final PhotonPoseEstimator poseEstimator;
    public static final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);
    public static final Transform3d kRobotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0, 0, 0));
    photonEstimator = new PhotonPoseEstimator(kTagLayout, kRobotToCam);

    // camera name, field layout (april tag json), robot to camera transform (camera position relative to robot)
    public Vision(String cameraName, AprilTagFieldLayout aprilTagFieldLayout, Transform3d robotToCam) {
        this.camera = new PhotonCamera(cameraName);
        this.poseEstimator = new PhotonPoseEstimator(
            aprilTagFieldLayout,
            PoseStrategy.LOWEST_AMBIGUITY,
            robotToCam
        );
    }

    PoseEstimate poseEstimate = null;
    double timestamp = 0;

    public PhotonCamera getCamera() {
        return camera;
    }

    public PhotonPipelineResult getLatestResult() {
        return camera.getLatestResult();
    }

    public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d priorPose) {
        poseEstimator.setReferencePose(priorPose);
        return poseEstimator.update();
    }
}
