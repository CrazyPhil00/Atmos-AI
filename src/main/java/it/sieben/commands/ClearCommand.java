package it.sieben.commands;

import it.sieben.utils.ChatColor;
import it.sieben.utils.CommandExecutor;
import net.minecraft.world.entity.player.Player;

public class ClearCommand extends CommandExecutor {
    public ClearCommand(String command) {
        super(command);

        setHelp_message("Clears the Chat");
        setUsage(ChatColor.ITALIC + ".clear");
    }

    @Override
    public boolean onCommand(Player sender, String command, String[] args) {

        for (int i = 0; i < 100; i++) {
            sender.sendMessage("");
        }

        return false;
    }
}
