# Minecraft Message Mod

Fabric мод для Minecraft 1.21.1 с GUI интерфейсом для отправки сообщений.

##  Функциональность
- GUI интерфейс для отправки сообщений (клавиша M)
- Подготовка к интеграции с PostgreSQL
- Современный стек технологий

##  Технологический стек
- Java 21
- Fabric Mod Loader
- Gradle 8.14 (Kotlin DSL)
- Fabric API

##  Установка
1. Скачайте `message-mod-1.0.0.jar` из релизов
2. Поместите в папку `mods` Minecraft
3. Запустите Minecraft с Fabric Loader

##  Использование
- Нажмите `M` для открытия GUI
- Введите сообщение и нажмите "Send Message"
- Сообщение появится в чате

##  Разработка
```bash
./gradlew build          # Сборка
./gradlew runClient      # Запуск клиента
```

```
src/main/java/com/example/messagemod/
├── MessageMod.java          # Главный класс
├── MessageProto.java        # Protobuf сообщения
└── client/
├── MessageModClient.java # Клиентская часть
└── MessageScreen.java    # GUI экран
```

