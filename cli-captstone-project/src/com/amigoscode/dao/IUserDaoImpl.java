package com.amigoscode.dao;

import com.amigoscode.model.User;

import java.io.*;
import java.util.UUID;

public class IUserDaoImpl implements IUserDao {
    @Override
    public String addUser(String name) {
        // variables used
        boolean isCreated = false;

        // locate the path for the file
        File file = new File("src/com/amigoscode/file/user-data.csv");
        try {
            if (!file.exists()) {
                // create the file
                file.createNewFile();
            }

            if (file.exists()) {
                // prepare the PrintWriter object to write to the file
                try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
                    // create the object
                    User user = new User(name);

                    // copy the object in a csv format
                    pw.write(user.getUuid().toString());
                    pw.write(",");
                    pw.write(user.getName());
                    pw.write("\n");

                    return "success";
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return "failure";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByID(String userID) {
        // get file
        File file = new File("src/com/amigoscode/file/user-data.csv");

        if (!file.exists()) {
            return null;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // read each line and compare value
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(",");
                    if (strings[0].equalsIgnoreCase(userID)) {
                        return new User(
                            UUID.fromString(strings[0]),
                            strings[1]
                        );
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public User[] gettAllUsers() {
        // variables used
        String line = null;
        User[] users = null;
        int fileLineCounter = 0;
        int index = 0;

        // locate the path for the file
        File file = new File("src/com/amigoscode/file/user-data.csv");

        if (file.exists()) {
            // determine how many lines the file has
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    fileLineCounter++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (fileLineCounter > 0) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    // initialize the array
                    users = new User[fileLineCounter];

                    // read one line at a time
                    while ((line = br.readLine()) != null) {
                        // get the array of values using split method and allocate the values accordingly
                        String[] strings = line.split(",");
                        UUID uuid = UUID.fromString(strings[0]);
                        String name = strings[1];

                        users[index++] = new User(uuid, name);
                    }

                    // return the array
                    return users;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
