package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String MovieName,String DirectorName){
        movieRepository.addMovieDirectorPair(MovieName,DirectorName);
    }

    public Movie getMovieByName(String movieName){
        return movieRepository.getMovieByName(movieName);
    }
    public Director getDirectorByName(String DirectorName){
        return movieRepository.getDirectorByName(DirectorName);
    }
    public List<String> getMoviesByDirectorName(String DirectorName){
        return movieRepository.getMoviesByDirectorName(DirectorName);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public void deleteDirectorByName(String directorName){
        movieRepository.deleteDirectorByName(directorName);
    }
    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }
}