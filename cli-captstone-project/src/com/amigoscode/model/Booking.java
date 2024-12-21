package com.amigoscode.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private final UUID uuidRefNumber;
    private final User user;
    private final Car car;
    private final LocalDateTime date;

    public Booking(User user, Car car) {
        this.uuidRefNumber = UUID.randomUUID();
        this.user = user;
        this.car = car;
        this.date = LocalDateTime.now();
    }

    public Booking(UUID uuidRefNumber, User user, Car car, LocalDateTime date) {
        this.uuidRefNumber = uuidRefNumber;
        this.user = user;
        this.car = car;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public UUID getUuidRefNumber() {
        return uuidRefNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Booking{" +
            "uuidRefNumber=" + uuidRefNumber +
            ", user=" + user +
            ", car=" + car +
            ", date=" + date +
            '}';
    }
}
