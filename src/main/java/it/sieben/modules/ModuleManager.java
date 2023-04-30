package it.sieben.modules;

import it.sieben.modules.pvp.Killaura;
import it.sieben.utils.Category;

import java.util.ArrayList;

public class ModuleManager {

    ArrayList<Module> module_list = new ArrayList<>();

    public void LoadModules() {

        module_list.add(new Killaura("Killaura", Category.PVP));

    }
}
