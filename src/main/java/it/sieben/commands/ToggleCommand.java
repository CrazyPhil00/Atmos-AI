package it.sieben.commands;

import it.sieben.modules.ModuleManager;
import it.sieben.utils.ChatColor;
import it.sieben.utils.CommandExecutor;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.player.Player;

public class ToggleCommand extends CommandExecutor {
    public ToggleCommand(String command) {
        super(command);

        setHelp_message("Toggles the given Module");
        setUsage(ChatColor.ITALIC + ".toggle {module name}");
    }

    @Override
    public boolean onCommand(Player sender, String command, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("No arg given");
            return true;
        }

        String module = args[0];
        sender.sendMessage(module);
        moduleManager.getModule(module).toggle();
        sender.sendMessage("Toggled " + moduleManager.getModule(module).getName());

        return false;
    }
}
