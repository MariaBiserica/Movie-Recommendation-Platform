package com.example.demo.controllers;

import com.example.demo.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserController implements Initializable {
    String searchedMovie = "";
    String searchedMovieGenre = "";
    Movie m = new Movie();
    List<String> likedGenres = new ArrayList<>();
    List<Movie> recommendations = new ArrayList<>();
    ObservableList<Movie> list = FXCollections.observableArrayList(m.getAllMovies().collect(Collectors.toList()));
    @FXML
    public Label usernameLabel;
    public Label passwordLabel;
    public Label searchOutputLabel;
    public TextField searchMovieField;
    public Button likeButton;

    @FXML public TableView<Movie> table = new TableView<>();
    @FXML  public TableColumn<Movie, String> movie = new TableColumn<>();
    @FXML public TableColumn<Movie, String> genre = new TableColumn<>();
    @FXML public TableColumn<Movie, LocalDate> release = new TableColumn<>();
    @FXML public TableColumn<Movie, Integer> likes = new TableColumn<>();


    @Override
    public void initialize(URL url, ResourceBundle rb){
        likeButton.setDisable(true);

        //initializing user data
        String filePath = "D:\\Medii si Instrumente de Programare\\movie_rec\\demo\\src\\main\\java\\CurrentUserFile.txt";
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                int counter = 1;
                String[] wordsOfLine = line.split(",");
                for(String word : wordsOfLine) {
                    if(counter == 1){
                        usernameLabel.setText("Welcome user: " + word);
                    }
                    else{
                        passwordLabel.setText("Your password is: " + word);
                    }
                    counter++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //intializing user feed
        //ObservableList<Movie> list = FXCollections.observableArrayList(
                //new Movie("Witcher", "Fantasy", LocalDate.now(), 100),
                //new Movie("Furthermore", "Fantasy", LocalDate.now(), 180)
        //);
        movie.setCellValueFactory(new PropertyValueFactory<Movie, String>("movie"));
        genre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        release.setCellValueFactory(new PropertyValueFactory<Movie, LocalDate>("release"));
        likes.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("likes"));

        table.setItems(list);
    }

    public List<Movie> getMoviesByGenre(String genre, List<Movie> list){
        List<Movie> filteredList = new ArrayList<>();
        for (Movie m: list) {
            if (m.getGenre().equals(genre)) {
                System.out.print('\n');
                System.out.print(m.getMovie());
                filteredList.add(m);
            }
        }
        return filteredList;
    }

    @FXML
    public void onSearchMovieTextFieldChanged(ActionEvent actionEvent) throws FileNotFoundException {
        searchedMovie = this.searchMovieField.getText();
        if(m.searchMovie(searchedMovie)){
            searchOutputLabel.setText("Found movie!");
            likeButton.setDisable(false);
        }
        else{
            searchOutputLabel.setText("There's no such movie recorded!");
            likeButton.setDisable(true);
        }
    }

    @FXML
    public void onLikeButtonClicked(ActionEvent actionEvent) {
        System.out.print('\n');
        System.out.print("The searched movie is: " + searchedMovie);
        List<Movie> newList = list.stream().map(m -> {
            Movie newMovie = new Movie(m.getMovie(), m.getGenre(), m.getRelease(), m.getLikes());
            System.out.print('\n');
            System.out.print(newMovie.getMovie());
            if(newMovie.getMovie().equals(searchedMovie)){
                searchedMovieGenre = newMovie.getGenre();
                newMovie.setLikes(newMovie.getLikes() + 1);
            }
            return newMovie;
        }).collect(Collectors.toList());

        list = FXCollections.observableArrayList(newList);

        //update recommendations
        if(!likedGenres.contains(searchedMovieGenre)){
            likedGenres.add(searchedMovieGenre);
        }
        Collections.sort(likedGenres);

        recommendations = new ArrayList<>();
        for(String g: likedGenres){
            List<Movie> recs = getMoviesByGenre(g, newList);
            Collections.sort(recs, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    //return m1.getLikes() - m2.getLikes();
                    if (m1.getLikes() == m2.getLikes())
                        return 0;
                    else if (m1.getLikes() < m2.getLikes())
                        return 1;
                    else
                        return -1;
                }
            });
            recommendations.addAll(recs);
        }

        movie.setCellValueFactory(new PropertyValueFactory<Movie, String>("movie"));
        genre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        release.setCellValueFactory(new PropertyValueFactory<Movie, LocalDate>("release"));
        likes.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("likes"));

        //List<Movie> l = new ArrayList<>();
        //l.addAll(recommendations);
        //l.addAll(list);
        table.setItems(FXCollections.observableArrayList(recommendations));

        likeButton.setDisable(true);
    }
}
