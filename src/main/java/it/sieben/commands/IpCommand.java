package it.sieben.commands;

import it.sieben.utils.ChatColor;
import it.sieben.utils.CommandExecutor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class IpCommand extends CommandExecutor {
    public IpCommand(String command) {
        super(command);

        setHelp_message("Shows the Server Ip and copies it to the clipboard");
        setUsage(ChatColor.ITALIC + ".ip");
    }


    @Override
    public boolean onCommand(Player sender, String command, String[] args) {

        String ip = "";

        try {
            ip = Minecraft.getInstance().getCurrentServer().ip;
        } catch (NullPointerException e) {
            sender.sendMessage(ChatColor.WHITE.prefix() + ChatColor.RED + "You are in a Singleplayer World, the .ip command doesn't work!");
            return true;
        }







        sender.sendMessage(ChatColor.WHITE.prefix() + ChatColor.DARK_GRAY + "Server Ip is " + ChatColor.BLUE + ip);
        copyToClipboard(ip);

        return false;
    }
}
