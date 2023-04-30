package it.sieben.utils;

import net.minecraft.world.entity.player.Player;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private String command, help_message, usage;
    private String[] args;
    private Player player;
    
    public CommandExecutor(String command) {
        this.command = command;
    }

    public boolean onCommand(Player sender, String command, String[] args) {
        return false;
    }


    public void setCommand(String command) {
        this.command = command;
    }

    public void setHelp_message(String help_message) {
        this.help_message = help_message;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }


    public Player getPlayer() {
        return player;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public String getHelp_message() {
        return help_message;
    }
    public String getUsage() {
        return usage;
    }



    public static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }







}


