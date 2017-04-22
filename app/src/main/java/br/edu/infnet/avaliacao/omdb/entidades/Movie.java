package br.edu.infnet.avaliacao.omdb.entidades;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Willow on 21/04/2017.
 */

public class Movie {

    private static final String FIELD_PLOT = "Plot";
    private static final String FIELD_YEAR = "Year";
    private static final String FIELD_LANGUAGE = "Language";
    private static final String FIELD_METASCORE = "Metascore";
    private static final String FIELD_TOTAL_SEASONS = "totalSeasons";
    private static final String FIELD_RATED = "Rated";
    private static final String FIELD_IMDB_VOTES = "imdbVotes";
    private static final String FIELD_WRITER = "Writer";
    private static final String FIELD_IMDB_ID = "imdbID";
    private static final String FIELD_TYPE = "Type";
    private static final String FIELD_COUNTRY = "Country";
    private static final String FIELD_IMDB_RATING = "imdbRating";
    private static final String FIELD_RESPONSE = "Response";
    private static final String FIELD_TITLE = "Title";
    private static final String FIELD_ACTORS = "Actors";
    private static final String FIELD_RUNTIME = "Runtime";
    private static final String FIELD_GENRE = "Genre";
    private static final String FIELD_AWARDS = "Awards";
    private static final String FIELD_RELEASED = "Released";
    private static final String FIELD_POSTER = "Poster";
    private static final String FIELD_DIRECTOR = "Director";


    @SerializedName(FIELD_PLOT)
    private String mPlot;
    @SerializedName(FIELD_YEAR)
    private String mYear;
    @SerializedName(FIELD_LANGUAGE)
    private String mLanguage;
    @SerializedName(FIELD_METASCORE)
    private String mMetascore;
    @SerializedName(FIELD_TOTAL_SEASONS)
    private int mTotalSeason;
    @SerializedName(FIELD_RATED)
    private String mRated;
    @SerializedName(FIELD_IMDB_VOTES)
    private int mImdbVote;
    @SerializedName(FIELD_WRITER)
    private String mWriter;
    @SerializedName(FIELD_IMDB_ID)
    private String mImdbID;
    @SerializedName(FIELD_TYPE)
    private String mType;
    @SerializedName(FIELD_COUNTRY)
    private String mCountry;
    @SerializedName(FIELD_IMDB_RATING)
    private double mImdbRating;
    @SerializedName(FIELD_RESPONSE)
    private String mResponse;
    @SerializedName(FIELD_TITLE)
    private String mTitle;
    @SerializedName(FIELD_ACTORS)
    private String mActor;
    @SerializedName(FIELD_RUNTIME)
    private String mRuntime;
    @SerializedName(FIELD_GENRE)
    private String mGenre;
    @SerializedName(FIELD_AWARDS)
    private String mAward;
    @SerializedName(FIELD_RELEASED)
    private String mReleased;
    @SerializedName(FIELD_POSTER)
    private String mPoster;
    @SerializedName(FIELD_DIRECTOR)
    private String mDirector;


    public Movie(){

    }

    public void setPlot(String plot) {
        mPlot = plot;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getYear() {
        return mYear;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setMetascore(String metascore) {
        mMetascore = metascore;
    }

    public String getMetascore() {
        return mMetascore;
    }

    public void setTotalSeason(int totalSeason) {
        mTotalSeason = totalSeason;
    }

    public int getTotalSeason() {
        return mTotalSeason;
    }

    public void setRated(String rated) {
        mRated = rated;
    }

    public String getRated() {
        return mRated;
    }

    public void setImdbVote(int imdbVote) {
        mImdbVote = imdbVote;
    }

    public int getImdbVote() {
        return mImdbVote;
    }

    public void setWriter(String writer) {
        mWriter = writer;
    }

    public String getWriter() {
        return mWriter;
    }

    public void setImdbID(String imdbID) {
        mImdbID = imdbID;
    }

    public String getImdbID() {
        return mImdbID;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setImdbRating(double imdbRating) {
        mImdbRating = imdbRating;
    }

    public double getImdbRating() {
        return mImdbRating;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    public String getResponse() {
        return mResponse;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setActor(String actor) {
        mActor = actor;
    }

    public String getActor() {
        return mActor;
    }

    public void setRuntime(String runtime) {
        mRuntime = runtime;
    }

    public String getRuntime() {
        return mRuntime;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setAward(String award) {
        mAward = award;
    }

    public String getAward() {
        return mAward;
    }

    public void setReleased(String released) {
        mReleased = released;
    }

    public String getReleased() {
        return mReleased;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getDirector() {
        return mDirector;
    }


}