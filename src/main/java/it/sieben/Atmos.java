package it.sieben;

import it.sieben.modules.ModuleManager;
import it.sieben.utils.Command;
import it.sieben.render.RenderTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class Atmos {
    public static Atmos instance;

    public static Minecraft minecraft;

    public Atmos() {
        instance = this;
    }

    public void preLoad() {
        System.out.println("--Preloading Atmos--");
        Command.registerCommands();
    }

    public void load() {
        System.out.println("--Loading Atmos--");

        minecraft = Minecraft.getInstance();

        new ModuleManager().LoadModules();
        RenderTitleScreen fpsHud = new RenderTitleScreen(minecraft);
        minecraft.gui.getChat().addMessage((Component) fpsHud);
    }

    public void postLoad() {
        System.out.println("--Postloading Atmos--");

    }


    public static Atmos getInstance() {
        return instance;
    }
}
