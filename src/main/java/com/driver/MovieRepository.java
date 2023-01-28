package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    // map movie name by movie
    private Map<String, Movie> movieMap;

    // map director name by director
    private Map<String, Director> directorMap;


    // map director name by all HIS movies
    private Map<String, List<String>> directorMovieMap;

    public MovieRepository() {
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMap = new HashMap<String, List<String>>();
    }

    public String addMovie(Movie movie) {
        movieMap.put(movie.getName(),movie);
        return "Movie added Successfully";
    }


    public String addDirector(Director director) {
        directorMap.put(director.getName(),director);
        return "Director added Successfully";
    }



    public String addMovieDirectorPair(String mName, String dName) {
        if(movieMap.containsKey(mName) && directorMap.containsKey(dName)) {

//            movieMap.put(mName, movieMap.get(mName));
//            directorMap.put(dName, directorMap.get(dName));

            // curr director ki jo movie list hai usme ye movie bhi add kar do

            List<String> currMovies = new ArrayList<>();

            if(directorMovieMap.containsKey(dName))
                currMovies = directorMovieMap.get(dName);   // us director ki movie list nikal liya


            currMovies.add(mName);
            directorMovieMap.put(dName, currMovies); // update in HashMap also
        }

        return "Paired new Movie to Director";
    }


    // get movie by name
    public Movie getMovieByName(String name) {
        return movieMap.get(name);
    }

    // get director by name
    public Director getDirectorByName(String name) {
        return directorMap.get(name);
    }


    // get list of movies name by given director name
    public List<String> getMoviesByDirectorName(String name) {
        List<String> movieList = new ArrayList<>();
        if(directorMovieMap.containsKey(name))
            movieList = directorMovieMap.get(name);

        return movieList;
    }



    // get list of all movies added
    public List<String> findAllMovies(){
//        List<String> movieList = new ArrayList<>();
//        for (String mName : movieMap.keySet())
//            movieList.add(mName);
//        return movieList;

        return new ArrayList<>(movieMap.keySet());
    }


    // delete a director and his movies from records
    public String deleteDirectorByName(String name) {
        List<String> movieList = new ArrayList<>();
        if(directorMovieMap.containsKey(name)) {
            movieList = directorMovieMap.get(name);

            for (String mName : movieList) {
                if(movieMap.containsKey(mName))
                    movieMap.remove(mName);
            }

            directorMovieMap.remove(name);
        }

        if(directorMap.containsKey(name))
            directorMap.remove(name);

        return name + " and Movies of " + name + " Deleted Successfully";
    }


    // delete all directors and all movies by them form records
    public String deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<>();

        for (String dName : directorMovieMap.keySet()) {
            for (String mName : directorMovieMap.get(dName)) {
                movieSet.add(mName);
            }
//            directorMovieMap.remove(dName);
        }

        for (String mName : movieSet) {
            if(movieMap.containsKey(mName))
                movieMap.remove(mName);
        }
        return "Directors and their movies deleted";
    }



}