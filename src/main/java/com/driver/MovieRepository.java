package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieMap;
    private HashMap<String,Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository() {
        this.movieMap = new HashMap<String,Movie>();
        this.directorMap = new HashMap<String,Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void addMovie(Movie movie){
        String movieName = movie.getName();
        movieMap.put(movieName,movie);
    }

    public void addDirector(Director director){
        String directorName = director.getName();
        directorMap.put(directorName,director);
    }

    public void addMovieDirectorPair(String MovieName,String DirectorName) {
        if (directorMap.containsKey(DirectorName) && movieMap.containsKey(MovieName)) {
            movieMap.put(MovieName, movieMap.get(MovieName));
            directorMap.put(DirectorName, directorMap.get(DirectorName));
            List<String> currMovies = new ArrayList<String>();
            if (directorMovieMapping.containsKey(DirectorName))
                currMovies = directorMovieMapping.get(DirectorName);

            currMovies.add(MovieName);
            directorMovieMapping.put(DirectorName, currMovies);
        }
    }

    public Movie getMovieByName(String movieName){
        return movieMap.get(movieName);
    }
    public Director getDirectorByName(String DirectorName){
        return directorMap.get(DirectorName);
    }
    public List<String> getMoviesByDirectorName(String DirectorName){
        List<String> ListofMovies = new ArrayList<>();
        if(directorMovieMapping.containsKey(DirectorName))
            ListofMovies =  directorMovieMapping.get(DirectorName);
        return ListofMovies;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }
    public void deleteDirectorByName(String directorName){
        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(directorName)){
            movies =  directorMovieMapping.get(directorName);
            for(String movie : movies){
                movieMap.remove(movie);
            }
            directorMovieMapping.remove(directorName);
        }


        if(directorMap.containsKey(directorName)) {
            directorMap.remove(directorName);
        }
    }
    public void deleteAllDirectors(){
        HashSet<String> moviesSet = new HashSet<String>();

        for(String director: directorMovieMapping.keySet()){
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }

    }
}