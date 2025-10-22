package com.github.username;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageMod implements ModInitializer {
    public static final String MOD_ID = "messagemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Message Mod initialized!");

        // ВРЕМЕННО отключаем инициализацию базы данных
        // try {
        //     DatabaseManager.init();
        //     LOGGER.info("Database initialized successfully");
        // } catch (Exception e) {
        //     LOGGER.error("Failed to initialize database", e);
        // }
    }
}