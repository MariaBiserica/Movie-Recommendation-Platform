package com.example.demo.roles;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User {
    private String userName;
    private String userPassword;

    public User() {
        userName = "";
        userPassword = "";
    }

    public String getUsername() { return userName; }

    public String getPassword() { return userPassword; }

    public boolean searchUsername(String username) throws FileNotFoundException {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\UserFile.txt";
        //Scanner scan = new Scanner(new File(filePath));
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsOfLine = line.split(",");
                for(String word : wordsOfLine) {
                    if (username.equals(word)){
                        userName = username;
                        return true;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean searchPassword(String password) throws FileNotFoundException {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\UserFile.txt";
        //Scanner scan = new Scanner(new File(filePath));
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if(line.equals(userName + "," + password)){
                    userPassword = password;
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addNewUser(String username, String password) throws IOException {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\UserFile.txt";
        Writer output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(username + "," + password);
        output.close();
    }

    public void addCurrentUser(String username, String password) throws IOException {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\CurrentUserFile.txt";
        Path fileName = Path.of(filePath);
        Files.writeString(fileName, username + "," + password);
    }
}
