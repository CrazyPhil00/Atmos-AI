package it.sieben.ai;

import com.github.chen0040.rl.learning.qlearn.QAgent;
import com.github.chen0040.rl.utils.IndexValue;
import net.minecraft.client.Minecraft;

import java.math.BigInteger;
import java.util.Random;

import java.util.*;
import com.github.chen0040.rl.learning.qlearn.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AtmosAgent {

    private static final int STATE_COUNT = 65536; // 16 x 16 x 16 blocks
    private static final int ACTION_COUNT = 4; // move forward, move backward, move left, move right, jump, and look around
    private static final double ALPHA = 0.1;
    private static final double GAMMA = 0.9;
    private static final double INITIAL_Q = 0.5;
    private static final double EPSILON = 0.1;

    private static double immediateReward;

    private QAgent agent;
    private Random random;

    private int currentState;
    private int prevAction;

    private Map<Vec3, Boolean> blockedDirections;

    public AtmosAgent() {
        agent = new QAgent(STATE_COUNT, ACTION_COUNT, ALPHA, GAMMA, INITIAL_Q);
        random = new Random();
        blockedDirections = new HashMap<>();
    }

    public void updateState(Player player, List<BlockPos> visibleBlocks) {
        immediateReward = 0.0;
        // Calculate the current state based on the visible blocks around the player
        int state = 0;
        for (BlockPos block : visibleBlocks) {
            int x = block.getX() - player.getBlockX() + 8;
            int y = block.getY() - player.getBlockY() + 8;
            int z = block.getZ() - player.getBlockZ() + 8;
            if (x < 0 || x > 15 || y < 0 || y > 15 || z < 0 || z > 15) {
                continue;
            }
            state |= 1 << (x * 16 + y * 4 + z);
        }
        currentState = state;

        if (prevAction != -1) {
            BlockPos blockInFront = BlockPos.containing(player.getEyePosition(1.0f).relative(player.getDirection(), 1.0));
            BlockState blockState = player.level.getBlockState(blockInFront);
            if (!blockedDirections.getOrDefault(blockInFront.getCenter(), false) && !blockState.isAir()) {
                // Player moved towards a block
                addReward(-1.0); // penalize player for moving towards a block
            } else {
                // Player did not move towards a block
                addReward(1.0); // reward player for not moving towards a block
            }
        }


        // Update the Q-learning agent with the new state and reward
        if (prevAction != -1) {
            agent.update(prevAction, currentState, null, immediateReward);
        }

        // Select the next action based on the Q-learning agent's policy
        Set<Integer> availableActions = getAvailableActions(player);
        IndexValue action = agent.selectAction();
        prevAction = action.getIndex();
        performAction(player);
    }

    public void performAction(Player player) {
        switch (prevAction) {
            case 0:
                PlayerController.move(player, MovingDirection.FORWARDS.getPosition());
                break;
            case 1:
                PlayerController.move(player, MovingDirection.BACKWARDS.getPosition());
                break;
            case 2:
                PlayerController.move(player, MovingDirection.LEFT.getPosition());
                break;
            case 3:
                PlayerController.move(player, MovingDirection.RIGHT.getPosition());
                break;
            case 4:
                PlayerController.jump(player);
                break;
            default:
                break;
        }
    }
    public Set<Integer> getAvailableActions(Player player) {
        Set<Integer> actions = new HashSet<Integer>();

        // Check if the player can move forward
        actions.add(0);

        // Check if the player can move backward
        actions.add(1);


        // Check if the player can strafe left
        actions.add(2);


        // Check if the player can strafe right
            actions.add(3);


        // Check if the player can jump
        if (player.isOnGround()) {
            actions.add(4);
        }

        // Return the list of available actions
        return actions;
    }

    public static void addReward(double amount) {
        immediateReward += amount;
    }

}
