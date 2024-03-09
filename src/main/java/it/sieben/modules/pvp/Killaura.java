package it.sieben.modules.pvp;

import it.sieben.modules.Module;
import it.sieben.utils.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class Killaura extends Module {
    public Killaura(String name, Category type) {
        super(name, type);
    }
    int i = 0;
    int everyTick = 4;

    @Override
    public void onPlayerTick() {
        super.onPlayerTick();

        if (!isToggled()) return;


        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        AABB searchArea = new AABB(x - 16, y - 16, z - 16, x + 16, y + 16, z + 16);

        TargetingConditions conditions = TargetingConditions.forCombat();

        LivingEntity nearestEntity = player.level.getNearestEntityW(
                player.level,
                LivingEntity.class,
                conditions,
                player, x, y, z,
                searchArea);

        if (i % 4 == 0) {
            if (nearestEntity.isAlive() && nearestEntity != null) {
                player.attack(nearestEntity);
                player.swing(InteractionHand.MAIN_HAND);
            }
        }

        i++;
    }
}
