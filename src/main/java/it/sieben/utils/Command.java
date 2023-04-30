package it.sieben.utils;

import it.sieben.commands.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class Command {

    public static ArrayList<CommandExecutor> commandList = new ArrayList<>();

    public static void registerCommands() {
        commandList.add(new HelpCommand("help"));

        commandList.add(new IpCommand("ip"));
        commandList.add(new TitleCommand("title"));
        commandList.add(new ClearCommand("clear"));
        commandList.add(new AiCommand("ai"));
    }

    public static boolean performCommand(Player player, String command, String[] args) {

        for (CommandExecutor cmdexec: commandList) if (cmdexec.getCommand().equalsIgnoreCase(command)) return cmdexec.onCommand(player, command, args);

        //No command was found
        player.sendMessage(ChatColor.WHITE.prefix() + ChatColor.RED + "No command was found! For help use the .help command.");
        return true;
    }
}
