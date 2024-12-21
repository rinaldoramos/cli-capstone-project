package com.amigoscode.model;

public class Car {

    private final String brand;
    private final String model;
    private final int register;
    private final boolean isElectric;

    public Car(String brand, String model, int register, boolean isElectric) {
        this.brand = brand;
        this.model = model;
        this.register = register;
        this.isElectric = isElectric;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public int getRegister() {
        return register;
    }

    @Override
    public String toString() {
        return "Car{" +
            "brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", isElectric=" + isElectric +
            ", register=" + register +
            '}';
    }
}
