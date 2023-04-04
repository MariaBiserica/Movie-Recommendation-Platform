package com.example.demo.roles;

import java.io.*;

public class Admin {
    private String adminName;
    private String adminPassword;

    public Admin() {
        adminName = "";
        adminPassword = "";
    }

    //public String getUsername() { return adminName; }

    //public String getPassword() { return adminPassword; }

    public boolean searchUsername(String username) {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\AdminFile.txt";
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsOfLine = line.split(",");
                for(String word : wordsOfLine) {
                    if (username.equals(word)){
                        adminName = username;
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println(filePath + " does not exist");
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean searchPassword(String password) {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\AdminFile.txt";
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                if(line.equals(adminName + "," + password)){
                    adminPassword = password;
                    return true;
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println(filePath + " does not exist");
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addNewAdmin(String username, String password) {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\AdminFile.txt";
        String newLine = username + "," + password;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.newLine();
            writer.append(newLine);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
