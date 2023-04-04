package com.example.demo;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class Movie {
    String movie, genre;
    LocalDate release;
    Integer likes;

    public Movie(){

    }
    public Movie(String movie, String genre, LocalDate release, Integer likes){
        this.movie = movie;
        this.genre = genre;
        this.release = release;
        this.likes = likes;
    }

    public void setMovie(String movie){
        this.movie = movie;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setRelease(LocalDate release){
        this.release = release;
    }

    public void setLikes(Integer likes){
        this.likes = likes;
    }

    public String getMovie(){ return movie; }

    public String getGenre(){ return genre; }

    public LocalDate getRelease(){ return release; }

    public Integer getLikes(){ return likes; }


    public Stream<Movie> getAllMovies(){
        Stream<Movie> movies = Stream.of();
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\MovieFile.txt";
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            String newMovie = "", newGenre = "";
            LocalDate newReleaseDate = LocalDate.now();
            Integer newLikes = 0;

            while ((line = bufferedReader.readLine()) != null) {
                int counter = 1;
                String[] wordsOfLine = line.split(",");
                for(String word : wordsOfLine) {
                    if(counter == 1){ newMovie = word; }
                    else if(counter == 2){ newGenre = word; }
                    else if(counter == 3){ newReleaseDate = LocalDate.parse(word); }
                    else if(counter == 4){ newLikes = Integer.parseInt(word); }
                    counter++;
                }
                Movie m = new Movie(newMovie, newGenre, newReleaseDate, newLikes);
                movies = Stream.concat(Stream.of(m), movies);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public boolean searchMovie (String movie) throws FileNotFoundException{
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\MovieFile.txt";
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsOfLine = line.split(",");
                for(String word : wordsOfLine) {
                    if (movie.equals(word)){
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

    public void addNewMovie(String movie, String genre, LocalDate release, Integer likes) throws IOException {
        String filePath = "D:\\.FACULTATE\\Semestrul 1\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\MovieFile.txt";
        Writer output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(movie + "," + genre + "," + release + "," + likes);
        output.close();
    }
}
