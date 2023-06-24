package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task1 {

        public static void main(String[] args) throws IOException {
            try (FileReader reader = new FileReader("file.txt")) {
                char[] buf = new char[256];
                int c;
                while ((c = reader.read(buf)) > 0) {
                    if (c < 256) {
                        buf = Arrays.copyOf(buf, c);
                    }
                }

                StringBuilder numbers = new StringBuilder();
                for (char character : buf) {
                    numbers.append(character);
                }
                String[] number = numbers.toString().split("\\s\\n");


                for (String s : number) {
                    if (s.matches("^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$") || s.matches("^\\d{3}-\\d{3}-\\d{4}$")) {
                        System.out.println(s);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
}
