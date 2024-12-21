package com.amigoscode.dao;

import com.amigoscode.model.Booking;
import com.amigoscode.model.Car;
import com.amigoscode.model.User;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.UUID;

public class IBookDaoImpl implements IBookDao {
    @Override
    public boolean bookCar(User user, Car car) {
        // variables used
        Car[] cars = null;
        boolean isBook = false;
        int carLines = 0;
        int index = 0;

        // get file path
        File file = new File("src/com/amigoscode/file/booked-cars-data.csv");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
                // create a booking object
                Booking booking = new Booking(user, car);

                // write booking information to the file
                pw.write(booking.getUuidRefNumber().toString());
                pw.write(",");
                pw.write(booking.getUser().getUuid().toString());
                pw.write(",");
                pw.write(booking.getUser().getName());
                pw.write(",");
                pw.write(booking.getCar().getBrand());
                pw.write(",");
                pw.write(booking.getCar().getModel());
                pw.write(",");
                pw.write(String.valueOf(booking.getCar().getRegister()));
                pw.write(",");
                pw.write(String.valueOf(booking.getCar().isElectric()));
                pw.write(",");
                pw.write(booking.getDate().toString());
                pw.write("\n");

                // change the boolean value -> true
                isBook = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // if added, update the current car-data file
        if (isBook) {
            // get car file location
            file = new File("src/com/amigoscode/file/car-data.csv");

            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    // get each line
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        carLines++;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // initialized the array
                cars = new Car[carLines - 1];

                // populate the array with the new updated values
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    // get each line
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        String[] strings = line.split(",");
                        if (Integer.parseInt(strings[2]) == car.getRegister())
                            continue;
                        cars[index++] = new Car(
                            strings[0],
                            strings[1],
                            Integer.parseInt(strings[2]),
                            Boolean.parseBoolean(strings[3])
                        );
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // update the car file with new array of available cars
                try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, false)))) {
                    for (Car car1 : cars) {
                        pw.write(car1.getBrand());
                        pw.write(",");
                        pw.write(car1.getModel());
                        pw.write(",");
                        pw.write(String.valueOf(car1.getRegister()));
                        pw.write(",");
                        pw.write(String.valueOf(car1.isElectric()));
                        pw.write("\n");
                    }

                    // return true
                    return true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

    @Override
    public Booking[] getAllBookings() {
        // resources used
        Booking[] bookings = null;
        int noOfLines = 0;
        int index = 0;

        // get the file path
        File file = new File("src/com/amigoscode/file/booked-cars-data.csv");

        if (!file.exists()) {
            return null;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // get each line
                String line = null;
                while ((line = br.readLine()) != null) {
                    noOfLines++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (noOfLines > 0) {
                // initialized the array
                bookings = new Booking[noOfLines];

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    // get each line
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        String[] strings = line.split(",");
                        bookings[index++] = new Booking(
                            UUID.fromString(strings[0]),
                            new User(
                                UUID.fromString(strings[1]),
                                strings[2]),
                            new Car(
                                strings[3],
                                strings[4],
                                Integer.parseInt(strings[5]),
                                Boolean.parseBoolean(strings[6])),
                            LocalDateTime.parse(strings[7])
                        );
                    }

                    // returns the number of bookings
                    return bookings;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public Booking getUserBook(User user) {
        // get the file
        File file = new File("src/com/amigoscode/file/booked-cars-data.csv");

        if (!file.exists()) {
            return null;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // get a line
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(",");
                    if (user.getUuid().toString().equalsIgnoreCase(strings[1])) {
                        return new Booking(
                            UUID.fromString(strings[0]),
                            new User(
                                UUID.fromString(strings[1]),
                                strings[2]
                            ),
                            new Car(
                                strings[3],
                                strings[4],
                                Integer.parseInt(strings[5]),
                                Boolean.parseBoolean(strings[6])
                            ),
                            LocalDateTime.parse(strings[7])
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
