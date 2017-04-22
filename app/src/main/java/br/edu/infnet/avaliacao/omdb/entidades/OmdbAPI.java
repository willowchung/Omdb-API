package br.edu.infnet.avaliacao.omdb.entidades;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class OmdbAPI {

    private static final String FIELD_SEARCH = "Search";
    private static final String FIELD_RESPONSE = "Response";
    private static final String FIELD_TOTAL_RESULTS = "totalResults";

    @SerializedName(FIELD_SEARCH)
    private List<Movie> movieList;
    @SerializedName(FIELD_RESPONSE)
    private String mResponse;
    @SerializedName(FIELD_TOTAL_RESULTS)
    private int mTotalResult;

    public OmdbAPI() {
    }

    public static String getFieldSearch() {
        return FIELD_SEARCH;
    }

    public static String getFieldResponse() {
        return FIELD_RESPONSE;
    }

    public static String getFieldTotalResults() {
        return FIELD_TOTAL_RESULTS;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public String getmResponse() {
        return mResponse;
    }

    public void setmResponse(String mResponse) {
        this.mResponse = mResponse;
    }

    public int getmTotalResult() {
        return mTotalResult;
    }

    public void setmTotalResult(int mTotalResult) {
        this.mTotalResult = mTotalResult;
    }

    /*public OmdbAPI(Parcel in) {
        movieList = new ArrayList<Movie>();
        in.readTypedList(movieList, Movie.CREATOR);
        mResponse = in.readString();
        mTotalResult = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<OmdbAPI> CREATOR = new Parcelable.Creator<OmdbAPI>() {
        public OmdbAPI createFromParcel(Parcel in) {
            return new OmdbAPI(in);
        }

        public OmdbAPI[] newArray(int size) {
        return new OmdbAPI[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(movieList);
        dest.writeString(mResponse);
        dest.writeInt(mTotalResult);
    }*/


}