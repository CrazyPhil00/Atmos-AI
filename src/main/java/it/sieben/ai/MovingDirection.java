package it.sieben.ai;

import net.minecraft.world.phys.Vec3;

public enum MovingDirection {
    FORWARDS(new Vec3(0, 0, 1)),
    BACKWARDS(new Vec3(0, 0, -1)),
    LEFT(new Vec3(1, 0, 0)),
    RIGHT(new Vec3(-1, 0, 0));


    private Vec3 position;

    MovingDirection(Vec3 position) {
        this.position = position;
    }

    public Vec3 getPosition() {
        return position;
    }
}