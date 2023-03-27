package it.sieben.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class HelpCommand extends CommandExecutor{


    public HelpCommand(String command) {
        super(command);

        setCommand("help");

    }

    @Override
    public boolean onCommand(Player sender, String command, String[] args) {

        String helpMenu = "§9-----------------------\n" +
                "       §1Atmos-Help     §8\n";

        int i = 0;
        for (String arg : args) {
            helpMenu += "[" + i + "] : " + arg + "\n";
            i ++;
        }
        helpMenu += "§9-----------------------";

        sender.sendSystemMessage(Component.translatable(helpMenu));

        return false;
    }
}
