package it.sieben.commands;

import it.sieben.utils.ChatColor;
import it.sieben.utils.CommandExecutor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class TitleCommand extends CommandExecutor {


    public TitleCommand(String command) {
        super(command);

        setHelp_message("Sets the Window name to the given name");
        setUsage(ChatColor.ITALIC + ".title {title name}");
    }


    @Override
    public boolean onCommand(Player sender, String command, String[] args) {

        String new_title = "";

        for (String s : args) {
            new_title += s + " ";
        }
        Minecraft.getInstance().setTitle(new_title);
        sender.sendMessage(ChatColor.WHITE.prefix() + ChatColor.GRAY + "Changed Title to " + ChatColor.BOLD + new_title);
        return false;
    }
}
