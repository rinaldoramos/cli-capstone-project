package com.amigoscode.model;

import java.util.UUID;

public class User {

    private final UUID uuid;
    private final String name;

    public User(String name) {
        // creates an Universally Unique Identifier
        this.uuid = UUID.randomUUID();
        this.name = name;
    }

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
            "uuid=" + uuid +
            ", name='" + name + '\'' +
            '}';
    }
}
