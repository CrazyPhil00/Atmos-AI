package it.sieben.render;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.SubtitleOverlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.Mth;
import org.apache.logging.log4j.core.pattern.TextRenderer;

public class RenderTitleScreen extends SubtitleOverlay {
    private long lastNanoTime = -1L;
    Minecraft minecraft;
    private int fps;

    public RenderTitleScreen(Minecraft minecraft) {
        super(minecraft);
        this.minecraft = minecraft;
    }

    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        if (this.lastNanoTime < 0L) {
            this.lastNanoTime = System.nanoTime();
            return;
        }

        long currentTime = System.nanoTime();
        long elapsedNanoTime = currentTime - this.lastNanoTime;

        if (elapsedNanoTime > 0L) {
            int elapsedMs = (int)(elapsedNanoTime / 1_000_000L);
            this.fps = Mth.floor(1000.0F / (float)elapsedMs);
        }

        this.lastNanoTime = currentTime;

        String fpsText = "FPS: " + fps;

        int textWidth = this.minecraft.font.width(fpsText);
        int textHeight = this.minecraft.font.lineHeight;

        int xPos = this.minecraft.getWindow().getGuiScaledWidth() - textWidth - 5;
        int yPos = this.minecraft.getWindow().getGuiScaledHeight() - textHeight - 5;

        RenderSystem.enableBlend();
        this.minecraft.font.drawShadow(poseStack, fpsText, xPos, yPos, 0xFFFFFFFF);
        RenderSystem.disableBlend();
    }

    public void tick() {
        // do nothing
    }
}