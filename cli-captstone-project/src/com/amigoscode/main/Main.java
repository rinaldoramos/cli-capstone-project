package com.amigoscode.main;

/*
 * Welcome to the CLI - Capstone Project
 * Here we are creating a booking system with simple functionality
 * such as view users, view cars, view booking, etc
 * */

import com.amigoscode.model.Booking;
import com.amigoscode.model.Car;
import com.amigoscode.model.User;
import com.amigoscode.service.IBookService;
import com.amigoscode.service.ICarService;
import com.amigoscode.service.IUserService;
import com.amigoscode.servicefactory.BookServiceFactory;
import com.amigoscode.servicefactory.CarServiceFactory;
import com.amigoscode.servicefactory.UserServiceFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // resources used
        IUserService userService = UserServiceFactory.getUserService();
        ICarService carService = CarServiceFactory.getCarService();
        IBookService bookService = BookServiceFactory.getBookService();

        Scanner scanner = new Scanner(System.in);

        // variables used
        int userChoice = 0;

        do {
            displayMenu();
            System.out.println();
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    // add user
                    System.out.println("➡️ Please enter the name of the new user");
                    String userName = scanner.next();
                    String message = userService.addUser(userName);
                    if (message.equalsIgnoreCase("success"))
                        System.out.println("User created successfully");
                    else
                        System.out.println("User was not created correctly");
                    break;
                case 2:
                    // view all user booking
                    User[] users2 = userService.gettAllUsers();

                    // display users
                    for (User user : users2) {
                        System.out.println(user);
                    }

                    // get user id from keyboard
                    System.out.println();
                    System.out.println("➡️ Insert user id");
                    String userID2 = scanner.next();

                    if (userID2.length() != 36) {
                        System.out.println("❌ Invalid USER ID ❌");
                    } else {
                        // get user data
                        User user2 = userService.getUserByID(userID2);

                        // check if that user has book a car
                        Booking userBook = bookService.getUserBook(user2);
                        if (userBook != null) {
                            System.out.println("Booking Information");
                            System.out.println("===================");
                            System.out.println(userBook);
                        } else {
                            System.out.println("❌ Sorry! There are no booking for user : " + user2);
                        }
                    }
                    break;
                case 3:
                    // view all booking
                    Booking[] allBookings = bookService.getAllBookings();
                    if (allBookings != null) {
                        System.out.printf("%-40S %-40S %-10S %-15S %-20S %-12S %-10S %-12S\n",
                            "Booking Universal Unique Identifier",
                            "User Universal Unique Identifier", "Name",
                            "Brand", "Model", "Registration", "isElectric",
                            "Booking Date");
                        System.out.printf("%-40S %-40S %-10S %-15S %-20S %-12S %-10S %-12S\n",
                            "====================================",
                            "====================================", "====",
                            "=====", "=====", "============", "==========",
                            "=============================");
                        for (Booking booking : allBookings) {
                            System.out.printf("%-40s %-40s %-10s %-15s %-20s %-12d %-10s %-12s\n",
                                booking.getUuidRefNumber(),
                                booking.getUser().getUuid(),
                                booking.getUser().getName(),
                                booking.getCar().getBrand(),
                                booking.getCar().getModel(),
                                booking.getCar().getRegister(),
                                booking.getCar().isElectric(),
                                booking.getDate()
                            );
                        }
                        System.out.printf("%-40S %-40S %-10S %-15S %-20S %-12S %-10S %-12S\n",
                            "====================================",
                            "====================================", "====",
                            "=====", "=====", "============", "==========",
                            "=============================");
                    } else {
                        System.out.println("There are no booking at the moment 👀");
                    }
                    break;
                case 4:
                    // view all cars
                    Car[] allCars = carService.getAllCars();
                    if (allCars != null) {
                        System.out.printf("%-15S %-20S %-12S %-10S\n", "Brand", "Model", "Registration", "isElectric");
                        System.out.printf("%-15S %-20S %-12S %-10S\n", "=====", "=====", "============", "==========");
                        for (int i = 0; i < allCars.length; i++) {
                            System.out.printf("%-15s %-20s %-12d %-10s\n",
                                allCars[i].getBrand(),
                                allCars[i].getModel(),
                                allCars[i].getRegister(),
                                allCars[i].isElectric());
                        }
                        System.out.printf("%-15S %-20S %-12S %-10S\n", "=====", "=====", "============", "==========");
                    } else {
                        System.out.println("No car available ❌");
                    }
                    break;
                case 5:
                    // view all electric cars
                    Car[] allElectricCars = carService.getAllElectricCars();
                    if (allElectricCars != null) {
                        System.out.printf("%-15S %-20S %-12S %-10S\n", "Brand", "Model", "Registration", "isElectric");
                        System.out.printf("%-15S %-20S %-12S %-10S\n", "=====", "=====", "============", "==========");
                        for (int i = 0; i < allElectricCars.length; i++) {
                            System.out.printf("%-15s %-20s %-12d %-10s\n",
                                allElectricCars[i].getBrand(),
                                allElectricCars[i].getModel(),
                                allElectricCars[i].getRegister(),
                                allElectricCars[i].isElectric());
                        }
                        System.out.printf("%-15S %-20S %-12S %-10S\n", "=====", "=====", "============", "==========");
                    } else {
                        System.out.println("No electric car available ❌");
                    }
                    break;
                case 6:
                    // view all users
                    User[] users = userService.gettAllUsers();
                    if (users != null) {
                        System.out.printf("%-40S %-10S \n", "Universal Unique Identifier", "Name");
                        System.out.printf("%-40S %-10S \n", "====================================", "====");
                        for (User user : users) {
                            System.out.printf("%-40s %-10s \n", user.getUuid(), user.getName());
                        }
                        System.out.printf("%-40S %-10S \n", "====================================", "====");
                    } else {
                        System.out.println("There are no users added at the moment");
                    }
                    break;
                case 7:
                    // book a car
                    User[] users1 = userService.gettAllUsers();

                    // display users
                    for (User user : users1) {
                        System.out.println(user);
                    }

                    // get user id from keyboard
                    System.out.println();
                    System.out.println("➡️ Insert user id");
                    String userID = scanner.next();

                    // get user with the id given
                    User user1 = userService.getUserByID(userID);

                    // get all car available
                    Car[] allCars1 = carService.getAllCars();

                    // display all cars
                    for (Car car : allCars1) {
                        System.out.println(car);
                    }

                    // get car id from keyboard
                    System.out.println();
                    System.out.println("➡️ Insert car registration");
                    String carId1 = scanner.next();

                    // get car with given register number
                    Car car1 = carService.getCarByRegistration(Integer.parseInt(carId1));

                    // get all booking information
                    boolean isBooked = bookService.bookCar(user1, car1);
                    if (isBooked) {
                        System.out.println("🎉Hurray! The car was book for given user🎉");
                    } else {
                        System.out.println("❌ Sorry, the car was not booked 😩");
                    }
                    break;
                case 8:
                    // exit the program
                    break;
                default:
                    System.out.println("❌ Invalid Input. Please type a number between [1-8] ❌");
                    System.out.println();
            }
        } while (userChoice != 8);

        System.out.println("🥂Thank you for choosing us. Come back soon!!🥂");
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("""
                1️⃣ - Add a User
                2️⃣ - View All User Booked Cars
                3️⃣ - View All Bookings
                4️⃣ - View Available Cars
                5️⃣ - View Available Electric Cars
                6️⃣ - View all users
                7️⃣ - Book Car
                8️⃣ - Exit
            """);
    }
}