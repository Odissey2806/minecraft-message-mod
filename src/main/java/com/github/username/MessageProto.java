package com.github.username;

// Временная реализация, пока не работает generateProto
public class MessageProto {
    public static class Message {
        private String text;

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public static Message parseFrom(byte[] data) {
            Message message = new Message();
            message.setText(new String(data));
            return message;
        }

        public byte[] toByteArray() {
            return text != null ? text.getBytes() : new byte[0];
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            private String text;

            public Builder setText(String text) {
                this.text = text;
                return this;
            }

            public Message build() {
                Message message = new Message();
                message.setText(text);
                return message;
            }
        }
    }
}