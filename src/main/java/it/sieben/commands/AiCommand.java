package it.sieben.commands;

import it.sieben.ai.PlayerController;
import it.sieben.utils.CommandExecutor;
import net.minecraft.world.entity.player.Player;

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

        if (args.length > 2) {
            yaw = Float.parseFloat(args[1]);
            pitch = Float.parseFloat(args[2]);
        }else if (args.length > 1) {
            steps = Integer.parseInt(args[1]);
        }

        switch (args[0]) {

            case "forwards" ->
            {
                PlayerController.moveForwards(sender, steps);
            }

            case "backwards" ->
            {
                PlayerController.moveBackwards(sender, steps);
            }

            case "left" ->
            {
                PlayerController.moveLeft(sender, steps);
            }

            case "right" ->
            {
                PlayerController.moveRight(sender, steps);
            }

            case "jump" ->
            {
                PlayerController.jump(sender);
            }

            case "face" ->
            {
                PlayerController.faceDirection(sender, yaw, pitch);
            }
        }

        return false;
    }
}
