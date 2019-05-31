package LibManager;

public class Reader {
    private String readerID;//ID of the reader
    private String readerName;//NAmeOF the reader
    private String readerMobileNo;//MobileNum Of the reader
    private String readerEmail;//Email of the reader

    public Reader(String readerID, String readerName, String readerMobileNo, String readerEmail) {
        this.readerID = readerID;
        this.readerName = readerName;
        this.readerMobileNo = readerMobileNo;
        this.readerEmail = readerEmail;

    }

    public Reader(String readerID) {
        this.readerID = readerID;
    }

    public String getReaderID() {
        return readerID;
    }


    public String getReaderName() {
        return readerName;
    }


    public String getReaderMobileNo() {
        return readerMobileNo;
    }


    public String getReaderEmail() {
        return readerEmail;
    }

}
