package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Task2 {

    public static void writeFileWithOutputStream(String json) {
        try(FileOutputStream fileOutputStream = new FileOutputStream("user.json")) {
            byte[] buffer = json.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        List<User> userList = new ArrayList<>();
        try {
            File file = new File("file2.txt");
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                String name = data[0];
                int age = Integer.parseInt(data[1]);

                User user = new User(name, age);
                userList.add(user);
            }

            scanner.close();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(userList);
            writeFileWithOutputStream(json);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private String name;
    private int age;

    User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
