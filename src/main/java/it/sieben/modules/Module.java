package it.sieben.modules;

import it.sieben.utils.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class Module {

    public Player player = Minecraft.getInstance().player;

    boolean toggled;
    String name;
    Category type;

    public Module(String name, Category type) {
        this.name = name;
        this.type = type;
    }

    public void onPlayerTick() {}

    public void onServerTick() {}

    public boolean isToggled() {
        return toggled;
    }

    public void toggle() {
        this.toggled = !toggled;
    }

    public String getName() {
        return name;
    }

    public Category getType() {
        return type;
    }
}