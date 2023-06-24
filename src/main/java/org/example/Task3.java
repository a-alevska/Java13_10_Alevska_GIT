package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {
        try (FileReader reader = new FileReader("words.txt")) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
            }

            StringBuilder words = new StringBuilder();
            for (char character : buf) {
                words.append(character);
            }
            String[] word = words.toString().split("\\s\\n|\\s");
            int count = 0;
            List<Result> results = new ArrayList<>();


            for (String w : word) {
                for (String s : word) {
                    if (w.equals(s)) {
                        count++;
                    }
                }
                Result result = new Result(w, count);
                if(!results.contains(result)){
                    results.add(result);
                }
                count = 0;
            }
            Collections.sort(results, (r1, r2) -> Integer.compare(r2.getFrequency(), r1.getFrequency()));
            System.out.println(results.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Result{
    private String word;
    private int frequency;

    Result(String word, int frequency){
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        Result result = (Result) o;
        return frequency == result.frequency && Objects.equals(word, result.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency);
    }

    @Override
    public String toString() {
        return word+" "+frequency+"\n";
    }
}
