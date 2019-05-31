package LibManager;

import connection.DBconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class westminsterLibryManager implements LibManager {

    /*
    westminsterlibrymaager Class include implementations of all the
    method declared in the interface of LibryManager. Methods in this
    class are used to manipulate data between the back end and database
     */

    public static ArrayList<LibraryItem> itmList = new ArrayList<>();

    @Override
    public void addBook(Book book) throws SQLException {
        //System.out.println(book);
        System.out.println(book.getIsbn());

        DBconnection connectClass = new DBconnection(); //get the database connection
        Connection connection = connectClass.getConnection();
        String sql = "INSERT INTO book (isbn,title,sector,pubDate,author,publisher,NoofPages,CurrentReader,borowwedDate,overDue) VALUES('" + book.getIsbn() + "','" + book.getTitle() + "','" + book.getSector() + "','" + book.getPublicationDateTime().getDateFormated() + "','" + book.getAuthorName() + "','" + book.getPublisherName() + "' ,'" + book.getNoofPages() + "','','','')";
        //then insert all values to database table


        Statement stat = connection.createStatement();
        stat.executeUpdate(sql);


    }

    @Override
    public void addDVD(DVD dvd) throws SQLException {

        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();


        String sql = "INSERT INTO DVD (isbn,title,sector,pubDate,producer,actors,languages,subsAvailable,currentReader,borowwedDate,overDue) VALUES('" + dvd.getIsbn() + "','" + dvd.getTitle() + "','" + dvd.getSector() + "','" + dvd.getPublicationDateTime().getDateFormated() + "','" + dvd.getProducer() + "','" + dvd.getActors() + "' ,'" + dvd.getLanguagesAvailale() + "',' " + dvd.getSubtitlesAvailable() + "','','','')";
        Statement stat = connection.createStatement();
        stat.executeUpdate(sql);

    }

    @Override
    public void borrowItem(Reader reader, LibraryItem libraryItem) throws SQLException {

        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();


        String sql = "INSERT INTO borroweditems (isbnBorrowed,readerIDborrwed,borowedDate,borrowedTime,REturnDate,overdue) VALUES('" + libraryItem.getIsbn() + "','" + reader.getReaderID() + "','" + libraryItem.getBorrowedDateTime().getDateFormated() + "','" + libraryItem.getBorrowedDateTime().getTimeFormated() + "','','" + 0 + "')";
        Statement stat = connection.createStatement();
        stat.executeUpdate(sql);

    }


    @Override
    public void retrunItem(Reader reader, LibraryItem libraryItem) throws SQLException {

        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();


        String sql = "DELETE FROM borroweditems WHERE isbnBorrowed= '" + libraryItem.getIsbn() + "'";
        Statement statmnt = connection.createStatement();
        statmnt.executeUpdate(sql);
    }


    @Override
    public void deleteItem(LibraryItem libraryItem) throws SQLException {

        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();


        String sql1 = "DELETE FROM book WHERE isbn=" + libraryItem.getIsbn();
        String sql2 = "DELETE FROM DVD WHERE isbn= " + libraryItem.getIsbn();
        Statement statmnt1 = connection.createStatement();
        Statement statmnt2 = connection.createStatement();
        statmnt1.executeUpdate(sql1);
        statmnt2.executeUpdate(sql2);
    }


    @Override
    public void generateReport() {

    }

    @Override
    public void viewItems() throws SQLException {

        LibryItemList();

        for (LibraryItem item : itmList) {
            System.out.println(item.getIsbn() + "        " + item.getTitle() + "       " + item.getSector());
        }

    }

    public void LibryItemList() throws SQLException {

        DBconnection dBconnection = new DBconnection();

        Connection conn = dBconnection.getConnection();
        Statement stm;
        Statement stm2;
        stm = conn.createStatement();
        stm2 = conn.createStatement();
        String sql = "Select * From book";
        String sql2 = "Select * From DVD";
        ResultSet rst2;
        ResultSet rst;
        rst = stm.executeQuery(sql);
        rst2 = stm2.executeQuery(sql2);

        while (rst.next()) {
            LibraryItem lbItem1 = new LibraryItem(rst.getString("isbn"), rst.getString("title"), rst.getString("sector")) {
            };
            itmList.add(lbItem1);
        }

        while (rst2.next()) {
            LibraryItem lbItem2 = new LibraryItem(rst2.getString("isbn"), rst2.getString("title"), rst2.getString("sector")) {
            };
            itmList.add(lbItem2);
        }

    }

}
