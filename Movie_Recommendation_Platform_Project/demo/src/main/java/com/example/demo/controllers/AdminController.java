package com.example.demo.controllers;

import com.example.demo.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class AdminController {
    String movie = "", genre = "";
    LocalDate release;

    @FXML
    public Label movieOutputLabel;
    public Label genreOutputLabel;
    public TextField movieField;
    public TextField genreField;
    public DatePicker releasePicker;
    public Button addMovieButton;

    @FXML
    public void onMovieTextFieldChanged(ActionEvent actionEvent) {
        movie = this.movieField.getText();
        addMovieButton.setDisable(false);
    }

    public void onGenreTextFieldChanged(ActionEvent actionEvent) {
        genre = this.genreField.getText();
        addMovieButton.setDisable(false);
    }

    public void onReleaseSelectButtonClicked(ActionEvent actionEvent) {
        release = releasePicker.getValue();
    }

    public void onAddMovieButtonClicked(ActionEvent actionEvent) throws IOException {
        movieOutputLabel.setText("");
        genreOutputLabel.setText("");
        Movie m = new Movie();

        if(movie.equals("")){
            movieOutputLabel.setText("Please enter a movie name!");
            addMovieButton.setDisable(true);
        }
        else if(genre.equals("")){
            genreOutputLabel.setText("Please enter a movie genre!");
            addMovieButton.setDisable(true);
        }
        else if(!m.searchMovie(movie)){
            m.addNewMovie(movie, genre, release, 50);
            movieOutputLabel.setText("Movie was successfully added!");
            addMovieButton.setDisable(true);
        }
        else{
            movieOutputLabel.setText("This movie already exists!");
            addMovieButton.setDisable(true);
        }
    }
}
