package it.sieben.ai;

import com.mojang.datafixers.optics.Lens;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.util.Mth;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;


import java.util.*;

public class PlayerController {

    private static boolean isMoving;

    public static void move(Player player, Vec3 direction) {

        if (isMoving) return; //maybe punish

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingSteps = 3;

            public void run() {
                if (remainingSteps > 0) {
                    player.travel(direction);
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
        // -90 = UP | 0 = STRAIGHT | 90 = DOWN

        float currentYaw = player.getYRot();
        float currentPitch = player.getXRot();

        // Define the number of steps and the duration of the interpolation
        int numSteps = 10;
        long durationMillis = 500;

        // Calculate the step size for each interpolation step
        float yawStep = (yaw - currentYaw) / numSteps;
        float pitchStep = (pitch - currentPitch) / numSteps;

        // Define the TimerTask that performs the interpolation
        TimerTask timerTask = new TimerTask() {
            private int step = 0;
            private final long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                // Calculate the elapsed time since the start of the interpolation
                long elapsedTime = System.currentTimeMillis() - startTime;

                if (elapsedTime > durationMillis) {
                    // Stop the TimerTask when the duration has elapsed
                    this.cancel();
                } else {
                    // Calculate the new yaw and pitch values for this step
                    float newYaw = currentYaw + yawStep * step;
                    float newPitch = currentPitch + pitchStep * step;
                    step++;

                    // Update the player's yaw and pitch values
                    player.setYRot(newYaw);
                    player.setXRot(newPitch);
                }
            }
        };

        // Schedule the TimerTask to run periodically
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, durationMillis / numSteps);
    }

    public static List<BlockPos> getVisibleBlocks(Player player, int range) {
        List<BlockPos> chunkBlocks = new ArrayList<>();
        int player_x = player.getBlockX();
        int player_y = player.getBlockY();
        int player_z = player.getBlockZ();


        for (int x = player_x; x < player_x + 16; x++) {
            for (int y = player_y; y < player_y + 20; y++) {
                for (int z = player_z; z < player_z + 16; z++) {
                    BlockState blockState = player.level.getChunkAt(new BlockPos(player_x, player_y, player_z)).getBlockState(new BlockPos(x, y, z));
                    if (blockState.isAir()) {
                        continue;
                    }
                    chunkBlocks.add(new BlockPos(x, y, z));
                }
            }
        }
        return chunkBlocks;
    }


}
