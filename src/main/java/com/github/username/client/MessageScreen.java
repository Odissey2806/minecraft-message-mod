package com.github.username.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import com.github.username.MessageMod;
import com.github.username.MessageProto;

public class MessageScreen extends Screen {
    private TextFieldWidget textField;

    public MessageScreen() {
        super(Text.literal("Message Sender"));
    }

    @Override
    protected void init() {
        super.init();

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Текстовое поле для ввода сообщения
        this.textField = new TextFieldWidget(
                this.textRenderer,
                centerX - 150, centerY - 30, 300, 20,
                Text.literal("Enter your message here...")
        );
        this.textField.setMaxLength(255);
        this.textField.setPlaceholder(Text.literal("Type your message..."));
        this.addSelectableChild(this.textField);

        // Кнопка отправки
        ButtonWidget sendButton = ButtonWidget.builder(
                Text.literal("Send Message"),
                button -> sendMessage()
        ).dimensions(centerX - 50, centerY + 10, 100, 20).build();

        this.addDrawableChild(sendButton);

        this.setInitialFocus(this.textField);
    }

    private void sendMessage() {
        String messageText = this.textField.getText().trim();
        if (!messageText.isEmpty()) {
            // Просто выводим сообщение в чат и логи
            MessageMod.LOGGER.info("Message sent: {}", messageText);

            if (this.client != null && this.client.player != null) {
                this.client.player.sendMessage(Text.literal("§a[Message Mod]§r " + messageText), false);
            }

            // Закрытие экрана
            if (this.client != null) {
                this.client.setScreen(null);
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title,
                this.width / 2, 40, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC
            this.close();
            return true;
        } else if (keyCode == 257) { // Enter
            this.sendMessage();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}