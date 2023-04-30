package it.sieben.commands;

import it.sieben.utils.ChatColor;
import it.sieben.utils.Command;
import it.sieben.utils.CommandExecutor;
import net.minecraft.world.entity.player.Player;

public class HelpCommand extends CommandExecutor {


    public HelpCommand(String command) {
        super(command);

        setHelp_message("Shows the Help message");
        setUsage(ChatColor.ITALIC + ".help {command}");
    }

    @Override
    public boolean onCommand(Player sender, String command, String[] args) {


        if (args.length > 0) command_help(args[0], command, sender);

        else  help_list(sender, args);



        return false;
    }


    public void command_help(String arg, String command, Player sender) {

        if (Command.commandList.contains(arg)) {
            sender.sendMessage(ChatColor.WHITE.prefix() + ChatColor.RED + "Command " + ChatColor.ITALIC + arg + ChatColor.RED + "doesn't, use .help to see all commands.");
            return;
        }

        for (CommandExecutor cmd : Command.commandList) {
            if (arg.equalsIgnoreCase(cmd.getCommand())) {

                sender.sendMessage(ChatColor.DARK_GRAY + "-----------------------");
                sender.sendMessage("");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.WHITE + "       [" + ChatColor.BLUE + ChatColor.BOLD + "Atmos" + ChatColor.RESET + ChatColor.WHITE + "-" + ChatColor.DARK_BLUE + ChatColor.BOLD + "Help" + ChatColor.WHITE + "]");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.WHITE + "          [" + ChatColor.GRAY + ChatColor.BOLD + cmd.getCommand() + ChatColor.WHITE + "]");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GRAY + ChatColor.BOLD + "Description" + ChatColor.WHITE + "] " + ChatColor.GRAY + cmd.getHelp_message());
                sender.sendMessage("[" + ChatColor.GRAY + ChatColor.BOLD + "Usage" + ChatColor.WHITE + "] " + ChatColor.DARK_GRAY + cmd.getUsage());
                sender.sendMessage("");
                sender.sendMessage(ChatColor.DARK_GRAY + "-----------------------");
            }
        }

    }

    public void help_list(Player sender, String[] args) {
        sender.sendMessage(ChatColor.DARK_GRAY + "-----------------------");
        sender.sendMessage("");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.WHITE + "       [" + ChatColor.BLUE + ChatColor.BOLD + "Atmos" + ChatColor.RESET + ChatColor.WHITE + "-" + ChatColor.DARK_BLUE + ChatColor.BOLD + "Help" + ChatColor.WHITE + "]");
        sender.sendMessage("");

        for (CommandExecutor cmds : Command.commandList) {
            sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GRAY + ChatColor.BOLD + cmds.getCommand() + ChatColor.WHITE + "] : " + ChatColor.DARK_GRAY + cmds.getUsage());
        }
        sender.sendMessage("");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.DARK_GRAY + "-----------------------");
    }
}
