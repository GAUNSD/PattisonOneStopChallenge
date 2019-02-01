package ca.murcia.dev.pattison;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;

/**
 * Book Reader
 *
 * Read a List of Books and return them.
 * Uses the {@link BookJsonReader} to retrieve the data.
 *
 * Could utilize further interfaces to add further "Readers" in the future.
 */
public class BookReader {

    private List< Book > mapFromJson( JSONArray jsonBooks ) {
        return IntStream.range( 0, jsonBooks.length() )
                .boxed()
                .map( jsonBooks::getJSONObject )
                .map( Book::new )
                .collect( Collectors.toList() );
    }

    public List< Book > read() throws BookException {
        return mapFromJson(
                BookJsonReader.readJSONFromResource()
        );
    }
}
