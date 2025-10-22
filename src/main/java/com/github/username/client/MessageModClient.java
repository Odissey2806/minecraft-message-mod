package com.github.username.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageModClient implements ClientModInitializer {
    private static KeyBinding openScreenKey;
    public static final Logger LOGGER = LoggerFactory.getLogger("messagemod");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Message Mod Client");

        // Регистрация keybinding
        openScreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.messagemod.open_screen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "category.messagemod.general"
        ));

        // Обработка нажатия клавиши
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openScreenKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new MessageScreen());
                    LOGGER.info("Opening Message Screen");
                }
            }
        });

        LOGGER.info("Message Mod Client initialized successfully");
    }
}