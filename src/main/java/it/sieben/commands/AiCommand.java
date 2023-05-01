package it.sieben.commands;

import it.sieben.ai.AtmosAgent;
import it.sieben.ai.MovingDirection;
import it.sieben.ai.PlayerController;
import it.sieben.utils.CommandExecutor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AiCommand extends CommandExecutor {
    public AiCommand(String command) {
        super(command);

        setHelp_message("");
        setUsage("");
    }

    @Override
    public boolean onCommand(Player sender, String command, String[] args) {
        float yaw = 0,  pitch = 0;
        int steps = 1;
        boolean error = false;

        if (args.length > 2) {
            yaw = Float.parseFloat(args[1]);
            pitch = Float.parseFloat(args[2]);
        }else if (args.length > 1) {
            steps = Integer.parseInt(args[1]);
        }

        int finalSteps = steps;
        switch (args[0]) {

            case "forwards" ->
            {
                PlayerController.move(sender, MovingDirection.FORWARDS.getPosition());
            }

            case "backwards" ->
            {
                PlayerController.move(sender, MovingDirection.BACKWARDS.getPosition());
            }

            case "left" ->
            {
                PlayerController.move(sender, MovingDirection.LEFT.getPosition());
            }

            case "right" ->
            {
                PlayerController.move(sender, MovingDirection.RIGHT.getPosition());
            }

            case "jump" ->
            {
                PlayerController.jump(sender);
            }

            case "face" ->
            {
                PlayerController.faceDirection(sender, yaw, pitch);
            }

            case "blocks" ->
            {
                for (BlockPos blockPos : PlayerController.getVisibleBlocks(sender, 2)) {
                    sender.sendMessage(sender.level.getBlockState(blockPos).getBlock().getDescriptionId());
                }
            }

            case "start" ->
            {
                AtmosAgent agent = new AtmosAgent();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int i = finalSteps;
                    @Override
                    public void run() {
                        agent.updateState(sender, PlayerController.getVisibleBlocks(sender, 1));

                        if (i == 0) this.cancel();
                        i--;
                    }
                }, 0, 50);
            }

            default ->
            {
                error = true;
                throw new IllegalStateException("Unexpected value: " + args[0]);
            }
        }

        return error;
    }
}
