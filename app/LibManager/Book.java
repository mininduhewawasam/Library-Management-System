package LibManager;

/*
Book class is used to create book objects to manipulate book data
 */

public class Book extends LibraryItem {
    private String authorName;//Name of the book Author
    private String publisherName;//NAme of the publisher Of the book
    private int noofPages;//Num Of Page

//this parameter contains all the attributes realate to book objects
    public Book(String isbn, String title, String sector, DateTime publicationDateTime, String currentReaderID, int overDue, String authorName, String publisherName, int noofPages) {
        super(isbn, title, sector, publicationDateTime, currentReaderID, overDue);
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.noofPages = noofPages;
    }


    public String getAuthorName() {
        return authorName;
    }


    public String getPublisherName() {
        return publisherName;
    }


    public int getNoofPages() {
        return noofPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authorName='" + authorName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", noofPages=" + noofPages +
                '}';
    }
}
