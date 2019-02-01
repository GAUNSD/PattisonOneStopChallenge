package ca.murcia.dev.pattison;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Book Json Reader
 *
 * Read the raw book data from a given (or default) URL resource.
 * The output of this class conforms to the json.org package
 */
public class BookJsonReader {

    private static final String DATA_URL = "https://gist.githubusercontent.com/dfraser/28528a6d1b93c032f4a2577d2c3f5056/raw/45d3af9f32a2dbf3c48d019b8a50fd12674a7a8a/books.json";
    private static final String GET_REQUEST = "GET";

    /**
     * Read from the default URL resource for the book data
     *
     * @return the {@link JSONArray} representing the raw data
     * @throws BookException
     */
    public static JSONArray readJSONFromResource() throws BookException {
        return readJSONFromResource( DATA_URL );
    }

    /**
     * Read from the supplier URL resource for the book data
     *
     * @param dataUrl a url pointing to the location of some raw JSON book data
     * @return the {@link JSONArray} representing the raw data
     * @throws BookException
     */
    public static JSONArray readJSONFromResource( String dataUrl ) throws BookException {
        String rawJsonString;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(dataUrl).openConnection();
            connection.setRequestMethod( GET_REQUEST );

            BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                sb.append( inputLine );
            }

            rawJsonString = sb.toString();
        } catch ( IOException e ) {
            throw new BookException( "Book Reading Exception", "Could Not Read From Resource", e );
        }

        return new JSONObject( rawJsonString ).getJSONArray( "books" );
    }
}
