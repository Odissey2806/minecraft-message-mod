package com.github.username;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.UUID;

public class DatabaseManager {
    private static SessionFactory sessionFactory;

    public static void init() {
        try {
            // Конфигурация Hibernate
            Configuration configuration = new Configuration();

            // Настройки подключения к PostgreSQL
            Properties settings = new Properties();
            settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/minecraft_db");
            settings.put("hibernate.connection.username", "minecraft_user");
            settings.put("hibernate.connection.password", "password123");
            settings.put("hibernate.connection.pool_size", "5");

            // Настройки Hibernate
            settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "update");
            settings.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(MessageEntity.class);

            // Создание SessionFactory
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            MessageMod.LOGGER.info("Database connection established successfully");

        } catch (Exception e) {
            MessageMod.LOGGER.error("Failed to initialize database connection", e);
        }
    }

    public static void saveMessage(UUID playerUuid, String text) {
        if (sessionFactory == null) {
            MessageMod.LOGGER.error("Database not initialized");
            return;
        }

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            // Создание и сохранение entity
            MessageEntity message = new MessageEntity();
            message.setUuid(playerUuid);
            message.setText(text.length() > 255 ? text.substring(0, 255) : text);

            session.persist(message);
            session.getTransaction().commit();

            MessageMod.LOGGER.info("Message saved to database: UUID={}, Text={}", playerUuid, text);

        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            MessageMod.LOGGER.error("Failed to save message to database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void close() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            MessageMod.LOGGER.info("Database connection closed");
        }
    }
}