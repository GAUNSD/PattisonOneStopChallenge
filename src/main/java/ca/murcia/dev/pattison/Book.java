package ca.murcia.dev.pattison;

import org.json.JSONObject;

/**
 * Book
 *
 * A representation of an individual book.
 * Modelled from the sample JSON file that was supplied.
 */
public class Book {

    private String isbn;
    private String title;
    private String subtitle;
    private String author;
    private String published;
    private String publisher;
    private Long pages;
    private String description;
    private String website;

    /**
     * Create a Book from it's JSON Representation
     *
     * @param json the raw data read from the resource
     */
    public Book( JSONObject json ) {
        this.isbn = json.getString( "isbn" );
        this.title = json.getString( "title" );
        this.subtitle = json.getString( "subtitle" );
        this.author = json.getString( "author" );
        this.published = json.getString( "published" );
        this.publisher = json.getString( "publisher" );
        this.pages = json.getLong( "pages" );
        this.description = json.getString( "description" );
        this.website = json.getString( "website" );
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn( String isbn ) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle( String subtitle ) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished( String published ) {
        this.published = published;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher( String publisher ) {
        this.publisher = publisher;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages( Long pages ) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite( String website ) {
        this.website = website;
    }
}
