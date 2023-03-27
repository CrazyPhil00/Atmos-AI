package it.sieben;

import it.sieben.commands.Command;

public class Atmos {
    public static Atmos instance;

    public Atmos() {
        instance = this;
    }

    public void preLoad() {
        System.out.println("--Preloading Atmos--");
    }

    public void load() {
        System.out.println("--Loading Atmos--");

        Command.registerCommands();
    }

    public void postLoad() {
        System.out.println("--Postloading Atmos--");

    }


    public static Atmos getInstance() {
        return instance;
    }
}
