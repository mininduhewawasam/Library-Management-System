package LibManager;

public class DVD extends LibraryItem {
    private String languagesAvailale;//languages availabe for the DVD
    private String subtitlesAvailable;//subs available for the dvd
    private String actors;//actos of the dvd
    private String producer;//producers of the dvd



    public DVD(String isbn, String title, String sector, DateTime publicationDateTime, String currentReaderID, int overDue, String languagesAvailale, String subtitlesAvailable, String actors, String producer) {
        super(isbn, title, sector, publicationDateTime, currentReaderID, overDue);
        this.languagesAvailale = languagesAvailale;
        this.subtitlesAvailable = subtitlesAvailable;
        this.actors = actors;
        this.producer = producer;

    }

    public String getLanguagesAvailale() {
        return languagesAvailale;
    }


    public String getSubtitlesAvailable() {
        return subtitlesAvailable;
    }

    public String getActors() {
        return actors;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
