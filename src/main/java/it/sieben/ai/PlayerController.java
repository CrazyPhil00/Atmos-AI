package it.sieben.ai;

import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerController {

    public static void moveForwards(Player player, int steps) {
        Vec3 movement = new Vec3(0, 0, 1);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingSteps = steps;

            public void run() {
                if (remainingSteps > 0) {
                    player.travel(movement);
                    remainingSteps--;
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(task, 0, 50); // execute every millisecond
    }

    public static void moveBackwards(Player player, int steps) {
        Vec3 movement = new Vec3(0, 0, -1);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingSteps = steps;

            public void run() {
                if (remainingSteps > 0) {
                    player.travel(movement);
                    remainingSteps--;
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(task, 0, 50); // execute every millisecond
    }

    public static void moveRight(Player player, int steps) {
        Vec3 movement = new Vec3(1, 0, 0);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingSteps = steps;

            public void run() {
                if (remainingSteps > 0) {
                    player.travel(movement);
                    remainingSteps--;
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(task, 0, 50); // execute every millisecond
    }

    public static void moveLeft(Player player, int steps) {
        Vec3 movement = new Vec3(-1, 0, 0);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingSteps = steps;

            public void run() {
                if (remainingSteps > 0) {
                    player.travel(movement);
                    remainingSteps--;
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(task, 0, 50); // execute every millisecond
    }

    public static void jump(Player player){
        if (player.isOnGround()) {
            player.jumpFromGround();
        }else {
            //TODO Punish
        }
    }

    public static void faceDirection(Player player, float yaw, float pitch) {
        float currentYaw = player.getYRot();
        float currentPitch = player.getXRot();
        float deltaYaw = normalizeAngle(yaw - currentYaw);
        float deltaPitch = normalizeAngle(pitch - currentPitch);
        float yawSpeed = 0.5f;
        float pitchSpeed = 0.3f;
        while (deltaYaw > 180) deltaYaw -= 360;
        while (deltaYaw < -180) deltaYaw += 360;
        while (deltaPitch > 180) deltaPitch -= 360;
        while (deltaPitch < -180) deltaPitch += 360;
        if (deltaYaw > 0) {
            player.setYRot(currentYaw + Math.min(deltaYaw, yawSpeed));
        } else if (deltaYaw < 0) {
            player.setYRot(currentYaw + Math.max(deltaYaw, -yawSpeed));
        }
        if (deltaPitch > 0) {
            player.setXRot(currentPitch + Math.min(deltaPitch, pitchSpeed));
        } else if (deltaPitch < 0) {
            player.setXRot(currentPitch + Math.max(deltaPitch, -pitchSpeed));
        }
    }

    private static float normalizeAngle(float angle) {
        return (angle % 360 + 360) % 360;
    }

}
