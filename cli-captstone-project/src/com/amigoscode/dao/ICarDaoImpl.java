package com.amigoscode.dao;

import com.amigoscode.model.Car;

import java.io.*;

public class ICarDaoImpl implements ICarDao {
    @Override
    public Car[] getAllElectricCars() {
        // variables used
        Car[] cars = null;
        String line = null;
        int noOfElectricCars = 0;
        int index = 0;

        // locate the file
        File file = new File("src/com/amigoscode/file/car-data.csv");

        if (file.exists()) {
            // traverse the file to get number of lines
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // read each line to determine how many lines
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(",");
                    if (strings[3].equalsIgnoreCase("true")) {
                        noOfElectricCars++;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (noOfElectricCars > 0) {
                // initialized the array
                cars = new Car[noOfElectricCars];

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    // read each line to determine how many lines
                    while ((line = br.readLine()) != null) {
                        String[] strings = line.split(",");
                        if (strings[3].equalsIgnoreCase("true")) {
                            cars[index++] = new Car(
                                strings[0],
                                strings[1],
                                Integer.parseInt(strings[2]),
                                Boolean.parseBoolean(strings[3])
                            );
                        }
                    }

                    // return the array of electric cars
                    return cars;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println("No file found in directory");
        }
        return null;
    }

    @Override
    public Car[] getAllCars() {
        // variables used
        Car[] cars = null;
        int noOfLines = 0;
        int index = 0;

        // get the file path
        File file = new File("src/com/amigoscode/file/car-data.csv");

        if (!file.exists()) {
            return null;
        } else {
            // check how many line the file has
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while (br.readLine() != null) {
                    noOfLines++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // initialized the array of cars
            cars = new Car[noOfLines];

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // get each line and then process
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(",");
                    String brand = strings[0];
                    String model = strings[1];
                    int register = Integer.parseInt(strings[2]);
                    boolean isElectric = Boolean.parseBoolean(strings[3]);

                    // populate the array
                    cars[index++] = new Car(brand, model, register, isElectric);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // return the total cars
            return cars;
        }
    }

    @Override
    public Car getCarByRegistration(int register) {
        // get the file
        File file = new File("src/com/amigoscode/file/car-data.csv");

        if (!file.exists()) {
            return null;
        } else {
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                // read each line
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(",");
                    if (Integer.parseInt(strings[2]) == register) {
                        return new Car(
                            strings[0],
                            strings[1],
                            Integer.parseInt(strings[2]),
                            Boolean.parseBoolean(strings[3])
                        );
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
