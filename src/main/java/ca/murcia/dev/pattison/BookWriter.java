package ca.murcia.dev.pattison;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookWriter {

    private MySQLConnection mySQLConnection;

    public BookWriter() throws BookException {
        this.mySQLConnection = new MySQLConnection();
    }

    private PreparedStatement prepareBookStatement( Connection connection, Book book) throws BookException {
        String insertBookStatement = "INSERT INTO pattisonbooks.books " +
                "(isbn, title, subtitle, author, published, publisher, pages, description, website) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedBookStatement = connection.prepareStatement( insertBookStatement );
            preparedBookStatement.setString( 1, book.getIsbn() );
            preparedBookStatement.setString( 1, book.getTitle() );
            preparedBookStatement.setString( 1, book.getSubtitle() );
            preparedBookStatement.setString( 1, book.getAuthor() );
            preparedBookStatement.setDate( 1, Date.valueOf( book.getPublished() ) );
            preparedBookStatement.setString( 1, book.getPublisher() );
            preparedBookStatement.setLong( 1, book.getPages() );
            preparedBookStatement.setString( 1, book.getDescription() );
            preparedBookStatement.setString( 1, book.getWebsite() );
            return preparedBookStatement;
        } catch ( SQLException e ) {
            throw new BookException( "Book Database Statement Exception", "Could Not Prepare Statement", e );
        }
    }

    public void write( List< Book > books ) throws BookException {
        try {
            List< PreparedStatement > bookStatements;
            Connection connection = mySQLConnection.connect();
            connection.setAutoCommit( false );

            bookStatements = books.stream()
                    .map( book -> prepareBookStatement( connection, book ) )
                    .collect( Collectors.toList() );

            for(PreparedStatement ps : bookStatements ) {
                ps.executeUpdate();
            }

            connection.commit();
            for(PreparedStatement ps : bookStatements ) {
                if(ps != null) {
                    ps.close();
                }
            }
            connection.setAutoCommit( true );
        } catch ( SQLException e ) {
            throw new BookException( "Book Database Writing Exception", "Could Not Write Book To Database", e );
        } finally {
            mySQLConnection.disconnect();
        }
    }
}
