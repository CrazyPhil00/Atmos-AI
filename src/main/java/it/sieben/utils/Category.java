package it.sieben.utils;

public enum Category {
    PVP("Pvp"),
    CLIENT("Client"),
    RENDER("Render"),
    WORLD("World"),
    MOVEMENT("Movement"),
    EXPLOITS("Exploits"),
    PLAYER("Player");
    String name;
    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
