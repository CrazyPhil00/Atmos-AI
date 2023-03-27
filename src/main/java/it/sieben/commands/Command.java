package it.sieben.commands;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Command {

    public static ArrayList<CommandExecutor> commandList = new ArrayList<>();

    public static void registerCommands() {
        commandList.add(new HelpCommand("help"));
    }

    public static boolean performCommand(Player player, String command, String[] args) {
        for (CommandExecutor cmdexec: commandList) if (cmdexec.getCommand().equalsIgnoreCase(command)) return cmdexec.onCommand(player, command, args);

        return true;
    }
}
