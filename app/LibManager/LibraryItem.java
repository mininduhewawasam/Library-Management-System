package LibManager;

public abstract class LibraryItem {
    private String isbn;
    private String title;
    private String sector;
    private DateTime publicationDateTime;
    private String currentReaderID;
    private int overDue;
    private DateTime borrowedDateTime;


    public LibraryItem(String isbn, String title, String sector, DateTime publicationDateTime, String currentReaderID, int overDue) {
        this.isbn = isbn;
        this.title = title;
        this.sector = sector;
        this.publicationDateTime = publicationDateTime;
        this.currentReaderID = currentReaderID;
        this.overDue = overDue;
    }

    public LibraryItem(String isbn, DateTime borrowedDateTime) {
        this.isbn = isbn;
        this.borrowedDateTime = borrowedDateTime;
    }

    public LibraryItem(String isbn) {
        this.isbn = isbn;
    }

    public LibraryItem(String isbn, String title, String sector) {
        this.isbn = isbn;
        this.title = title;
        this.sector = sector;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public DateTime getPublicationDateTime() {
        return publicationDateTime;
    }

    public DateTime getBorrowedDateTime() {
        return borrowedDateTime;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", sector='" + sector + '\'' +
                ", publicationDateTime=" + publicationDateTime +
                ", currentReaderID='" + currentReaderID + '\'' +
                ", overDue=" + overDue +
                '}';
    }
}
