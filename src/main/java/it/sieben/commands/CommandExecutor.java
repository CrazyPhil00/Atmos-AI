package it.sieben.commands;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    String command;
    String[] args;
    Player player;
    
    public CommandExecutor(String command) {
        this.command = command;
    }


    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    public boolean onCommand(Player sender, String command, String[] args) {
        return false;
    }


    public void setCommand(String command) {
        this.command = command;
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



}


