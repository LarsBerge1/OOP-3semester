/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;


/**
 *  Description of class Book.
 *  The book Class is responsible for holding the information associated with a book.
 *
 * @author Lars Berge
 * @version 2018.02.12
 */
public class Book
{
    private String title;           // Name of the book.
    private String seriesTitle;     //The title of the series.
    private String author;          //Author of the book.
    private String publisher;       //Publisher of the book.
    private String publicationDate; //The publication year of the book.
    private int edition;            //The edition of the book.

    /**
     * Constructor for single books.
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param publisher The publisher of the book
     * @param publicationDate The publication day of the book
     * @param edition The edition of the book
     */
    public Book(String title, String author, String publisher, String publicationDate, int edition)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.edition = edition;
        this.seriesTitle = null;
    }

    /**
     * Constructor for books in a series.
     * @param title
     * @param seriesTitle
     * @param author
     * @param publisher
     * @param publicationDate
     */
    public Book(String title, String seriesTitle, String author, String publisher, String publicationDate) {
        this.title = title;
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }


    /**
     * Set the title of the book
     * @param title the title of the book.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * Get the title of the book
     * @return the title of the book
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Set the title of the book serie
     * @param seriesTitle the series title
     */
    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    /**
     * Get the title of the book serie
     * @return the series title
     */
    public String getSeriesTitle() {
        return this.seriesTitle;
    }

    /**
     * Set the author of the book
     * @param author the author of the book.
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    /**
     * Get the author of the book
     * @return the author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Set the edition of the book.
     * @param edition of the book.
     */
    public void setEdition(int edition) {
        this.edition = edition;
    }
    /**
     * Get the edition of the book.
     * @return the edition of the book.
     */
    public int getEdition() {
        return this.edition;
    }

    /**
     * Set the date of publication of the book.
     * @param publicationDate the publication date of the book.
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Get the publication date of the book
     * @return the date of publication
     */
    public String getPublicationYear() {
        return this.publicationDate;
    }

    /**
     * Set the publisher of the book.
     * @param publisher of the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get the publisher of the book
     * @return the publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }
}

