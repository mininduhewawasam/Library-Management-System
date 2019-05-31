package controllers;

import LibManager.*;
import com.fasterxml.jackson.databind.JsonNode;
import connection.DBconnection;
import play.mvc.*;
import play.libs.Json;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class AppSummary {

    private String content;

    AppSummary(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

/*
libraryController class includes all the methods to communicate with front end and
All the validations to validate date entered from the fontend.
With validation methods inside this class pass them to objects via constructors.

 */

public class LibraryController extends Controller {


    /*
    method add book is used to pass data to book table in the
    database with correct validations.
     */
    public Result addBook() throws SQLException {

        String borrow = request().body().asText();
        JsonNode json = Json.parse(borrow);

        String bookISBN = json.get("Bisbn").textValue();
        String title = json.get("title").textValue();
        String publisher = json.get("pname").textValue();
        String publishedDate = json.get("pdate").textValue();
        String author = json.get("author").textValue();
        String NoofPages = json.get("pages").textValue();
        String sector = json.get("sector").textValue();
        int noOfPages = Integer.parseInt(NoofPages);


        boolean addbookISBN = false;
        boolean addDVDISBN = false;
        String message = null;

        String[] dateSplit = publishedDate.split("-");

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);


        westminsterLibryManager library = new westminsterLibryManager();
        DateTime dateBook = new DateTime(month, day, year);
        Book book = new Book(bookISBN, title, sector, dateBook, null, 0, author, publisher, noOfPages);

        DBconnection connectClass = new DBconnection(); //get the database connection
        Connection connection = connectClass.getConnection();

        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT isbn FROM book");
        ResultSet rs1 = statement1.executeQuery("SELECT isbn FROM DVD");
        ResultSet rs2 = statement2.executeQuery("SELECT isbn FROM book");

        int Bcount = 0;


        while (rs.next()) {
            if (bookISBN.equals(rs.getString("isbn"))) {
                System.out.println("Book ISBN is Already in use");
                message = "Book ISBN is Already in use";
                addbookISBN = true;
                break;
            }
        }

        while (rs1.next()) {
            if (bookISBN.equals(rs1.getString("isbn"))) {
                System.out.println("DVD ISBN is Already in use");
                message = "DVD ISBN is Already in use";
                addDVDISBN = true;
                break;
            }
        }

        while (rs2.next()) {
            Bcount++;
        }


        System.out.println("used space " + Bcount);

        int bookSpace = 100;
        int availableSpace = bookSpace - Bcount - 1;

        if ((!addbookISBN && !addDVDISBN) && Bcount < 100) {
            System.out.println("Book added successfully");
            library.addBook(book);
            System.out.println("Available space = " + availableSpace);
            message = "Book added successfully \nAvailable space = " + availableSpace;
        }


        JsonNode jsonNode = Json.toJson(new AppSummary(message));
        return ok(jsonNode).as("application/json");
    }


    /*
method add DVD is used to pass data to DVD table in the
database with correct validations.
 */

    public Result addDVD() throws SQLException {

        String borrow = request().body().asText();
        JsonNode json = Json.parse(borrow);

        String dvdISBN = json.get("DIsbn").textValue();
        String title = json.get("DVDname").textValue();
        String publishedDate = json.get("pubdate").textValue();
        String producer = json.get("producer").textValue();
        String actors = json.get("actors").textValue();
        String Languages = json.get("language").textValue();
        String subtitlesAvailable = json.get("subAvailable").textValue();
        String sector = json.get("dsector").textValue();

        boolean addbookISBN = false;
        boolean addDVDISBN = false;
        String message = null;

        String[] dateSplit = publishedDate.split("-");

        System.out.println(publishedDate);

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);

        westminsterLibryManager westminsterLibryManager = new westminsterLibryManager();
        DateTime dvdDate = new DateTime(month, day, year);
        DVD dvd = new DVD(dvdISBN, title, sector, dvdDate, null, 0, Languages, subtitlesAvailable, actors, producer);


        DBconnection connectClass = new DBconnection(); //get the database connection
        Connection connection = connectClass.getConnection();

        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT isbn FROM book");
        ResultSet rs1 = statement1.executeQuery("SELECT isbn FROM DVD");
        ResultSet rs2 = statement2.executeQuery("SELECT isbn FROM DVD");

        int Dcount = 0;


        while (rs.next()) {
            if (dvdISBN.equals(rs.getString("isbn"))) {
                System.out.println("Book ISBN is Already in use");
                addbookISBN = true;
                message = "Book ISBN is Already in use";
                break;
            }
        }

        while (rs1.next()) {
            if (dvdISBN.equals(rs1.getString("isbn"))) {
                System.out.println("DVD ISBN is Already in use");
                addDVDISBN = true;
                message = "DVD ISBN is Already in use";
                break;
            }
        }

        while (rs2.next()) {
            Dcount++;
        }


        System.out.println("used space " + Dcount);

        int dvdSpace = 50;

        int availableSpace = dvdSpace - Dcount - 1;

        if ((!addbookISBN && !addDVDISBN) && Dcount < 50) {
            System.out.println("DVD added successfully");

            westminsterLibryManager.addDVD(dvd);
            System.out.println("Available space = " + availableSpace);
            message = "DVD added successfully Available space = " + availableSpace;
        }

        JsonNode jsonNode = Json.toJson(new AppSummary(message));
        return ok(jsonNode).as("application/json");
    }

    /*
method borrowItem is used to pass data to borrowitem table in the
database with correct validations.
 */

    public Result borrowItem() throws SQLException, ParseException {


        String borrow = request().body().asText();
        JsonNode json = Json.parse(borrow);

        String readerID = json.get("readerID").textValue();
        String libreryItemISBN = json.get("IsbnNo").textValue();
        String borrowedDate = json.get("DateofBorrowed").textValue();
        String borrowedTime = json.get("TimeofBorrowed").textValue();

        System.out.println(borrowedTime);

        String[] dateSplit = borrowedDate.split("-");
        String[] timeSplit = borrowedTime.split(":");

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        int hour = Integer.parseInt(timeSplit[0]);
        int minute = Integer.parseInt(timeSplit[1]);
        System.out.println(hour);
        System.out.println(minute);


        DateTime borrowdateTime = new DateTime(month, day, year, hour, minute);

        System.out.println(borrowdateTime.getTimeFormated());

        westminsterLibryManager westminsterLibryManager = new westminsterLibryManager();
        LibraryItem libraryItem = new LibraryItem(libreryItemISBN, borrowdateTime) {
            @Override
            public void setIsbn(String isbn) {
                super.setIsbn(isbn);
            }
        };
        Reader reader = new Reader(readerID);

        System.out.println(libraryItem.getIsbn());
        System.out.println(reader.getReaderID());
        System.out.println(borrowedDate);

        String message = null;


        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();

        Statement statement = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement statement3 = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT isbn FROM book");
        ResultSet rs1 = statement1.executeQuery("SELECT isbn FROM dvd");
        ResultSet rs2 = statement2.executeQuery("SELECT readerID FROM reader");
        ResultSet rs3 = statement3.executeQuery("SELECT isbnBorrowed,borowedDate FROM borroweditems");


        boolean bookISBN = false;
        boolean DVDisbn = false;
        boolean readersIDno = false;
        boolean unAvailability = false;

        String borowedDate = null;


        while (rs.next()) {
            if (libreryItemISBN.equals(rs.getString("isbn"))) {
                System.out.println("book isbn is valid");
                bookISBN = true;
                break;
            }
        }

        while (rs1.next()) {
            if (libreryItemISBN.equals(rs1.getString("isbn"))) {
                System.out.println("DVD isbn is valid");
                DVDisbn = true;
                break;
            }
        }

        while (rs2.next()) {
            if (readerID.equals(rs2.getString("readerID"))) {
                System.out.println("valid reader ID");
                readersIDno = true;
                break;
            }
        }

        while (rs3.next()) {
            if (libreryItemISBN.equals(rs3.getString("isbnBorrowed"))) {
                unAvailability = true;
                borowedDate = rs3.getString("borowedDate");
                System.out.println(borowedDate);

                break;
            }
        }


        if ((bookISBN || DVDisbn) & readersIDno)
            if (!unAvailability) {
                System.out.println("Book is available borrowed successfully");
                message = "Book is available borrowed successfully";

                westminsterLibryManager.borrowItem(reader, libraryItem);

            } else if (unAvailability & bookISBN) {
                String dateEnterd = borowedDate;//checking if the item is borrowed of
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dateEnterd));
                c.add(Calendar.DATE, 7);  // number of days to add
                dateEnterd = sdf.format(c.getTime());  // dt is now the new date

                System.out.println(dateEnterd);
                System.out.println("book is not available now it will be available after this date");
                message = "book is not available now it will be available after " + dateEnterd;
            }
            /*
            THese both conditions are used to find out the next availabe date for both DVD or
            Book if it is currently brought by a another user
             */

            else if (unAvailability & DVDisbn) {
                String dateEnterd = borowedDate;//checking if the item is borrowed of
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dateEnterd));
                c.add(Calendar.DATE, 3);  // number of days to add
                dateEnterd = sdf.format(c.getTime());  // dt is now the new date

                System.out.println(dateEnterd);
                System.out.println("DVD is not available now it will be available after this date");
                message = "DVD is not available now it will be available after " + dateEnterd;
            } else if (!bookISBN || !DVDisbn) {
                System.out.println("invalid ISBN");
                message = "Ivalid ISBN";
            } else if (!readersIDno) {
                System.out.println("Invalid reader ID");
                message = "Invalid Reader ID";
            }


        JsonNode jsonNode = Json.toJson(new AppSummary(message));
        return ok(jsonNode).as("application/json");
    }


    public Result returnItem() throws SQLException, ParseException {

        /*
        this method is used to validate if the item is alredy borrowed or
        if the isbn entered is valid one or if the due date has been passed
        it calculates the over due according to the library item
         */

        String returnitem = request().body().asText();
        JsonNode json = Json.parse(returnitem);

        String ReaderID = json.get("ReaderIDreturn").textValue();
        String ISBNreturnItem = json.get("IsbnNoReturn").textValue();
        String returnedDate = json.get("DateofBorrowed").textValue();
        String retuurnedTime = json.get("TimeofBorrowed").textValue();
        String borowedDate = null;
        String borrowedTime = null;

        String[] dateSplit = returnedDate.split("-");
        String[] timeSplit = retuurnedTime.split(":");

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        int hour = Integer.parseInt(timeSplit[0]);
        int minute = Integer.parseInt(timeSplit[1]);

        DateTime returnDateTime = new DateTime(month, day, year, hour, minute);
        System.out.println(returnDateTime.getTimeFormated());

        westminsterLibryManager westminsterLibryManager = new westminsterLibryManager();
        Reader reader = new Reader(ReaderID);

        LibraryItem libraryItem = new LibraryItem(ISBNreturnItem) {
            @Override
            public void setIsbn(String isbn) {
                super.setIsbn(isbn);
            }
        };

        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();

        Statement statement3 = connection.createStatement();
        ResultSet rs3 = statement3.executeQuery("SELECT borowedDate,borrowedTime FROM borroweditems where isbnBorrowed= '" + ISBNreturnItem + "'");


        while (rs3.next()) {
            borowedDate = rs3.getString("borowedDate");
            borrowedTime = rs3.getString("borrowedTime");
            System.out.println(borowedDate);
            System.out.println(borrowedTime);
        }


        System.out.println(returnDateTime.getDateFormated());
        System.out.println(returnDateTime.getTimeFormated());

        String format = "MM-dd-yyyy hh:mm ";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date dateObj1 = sdf.parse(returnDateTime.getDateFormated() + " " + returnDateTime.getTimeFormated() + " ");
        Date dateObj2 = sdf.parse(borowedDate + " " + borrowedTime + " ");
        System.out.println(dateObj1);
        System.out.println(dateObj2 + "\n");

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        System.out.println(dateObj1.getTime());
        System.out.println(dateObj2.getTime());

        long diff = dateObj1.getTime() - dateObj2.getTime();
/*this calculation is used to calc the date diffenece between
borrwed date and returnd date.
*/

        int differenceOfDays = (int) (diff / (24 * 60 * 60 * 1000));
        System.out.println("difference between days: " + differenceOfDays);

        int differenceOfHours = (int) (diff / (60 * 60 * 1000));
        System.out.println("difference between hours: " + decimalFormat.format(differenceOfHours));


        Statement statement = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement1 = connection.createStatement();


        ResultSet rs = statement.executeQuery("SELECT isbn FROM book");
        ResultSet rs1 = statement1.executeQuery("SELECT isbn FROM dvd");
        ResultSet rs2 = statement2.executeQuery("SELECT readerID FROM reader");


        boolean bookISBN = false;
        boolean DVDisbn = false;
        boolean readersIDno = false;
        boolean unAvailability = false;

        String message = null;


        while (rs.next()) {
            if (ISBNreturnItem.equals(rs.getString("isbn"))) {
                System.out.println("book isbn is valid");
                bookISBN = true;
                break;
            }
        }

        while (rs1.next()) {
            if (ISBNreturnItem.equals(rs1.getString("isbn"))) {
                System.out.println("DVD isbn is valid");
                DVDisbn = true;
                break;
            }
        }

        while (rs2.next()) {
            if (ReaderID.equals(rs2.getString("readerID"))) {
                System.out.println("valid reader ID");
                readersIDno = true;
                break;
            }
        }

        while (rs3.next()) {
            if (ISBNreturnItem.equals(rs3.getString("isbnBorrowed"))) {
                unAvailability = true;
                break;
            }
        }
        double overDue = 0;
        int exceededHours;
        double dueforFirstThree = 0;
        double dueforRest = 0;

        /*
        these conditions are used to
        calculate the over due for
        relevant item
         */

        if (DVDisbn & (differenceOfDays > 2)) {
            exceededHours = differenceOfHours - 24 * 3;
            if (exceededHours <= 24 * 3) {
                dueforFirstThree = (exceededHours) * 0.2;

            }
            if (exceededHours > 24 * 3) {
                dueforFirstThree = 24 * 3 * 0.2;
                dueforRest = (exceededHours - 24 * 3) * 0.5;
            }
            westminsterLibryManager.retrunItem(reader, libraryItem);

            System.out.println(dueforFirstThree);
            System.out.println(dueforRest);
            overDue = dueforFirstThree + dueforRest;
            System.out.println("ovwedue " + overDue);
            message = "DVD returned suscessfully. Overdue fee = " + overDue;

            System.out.println("exceeded hours " + exceededHours);
        }

        if (bookISBN & (differenceOfDays > 6)) {
            exceededHours = differenceOfHours - 24 * 7;
            if (exceededHours <= 24 * 7) {
                dueforFirstThree = (exceededHours) * 0.2;

            }
            if (exceededHours > 24 * 7) {
                dueforFirstThree = 24 * 3 * 0.2;
                dueforRest = (exceededHours - 24 * 3) * 0.5;
            }
            westminsterLibryManager.retrunItem(reader, libraryItem);

            System.out.println(dueforFirstThree);
            System.out.println(dueforRest);
            overDue = dueforFirstThree + dueforRest;
            System.out.println("ovwedue " + overDue);
            message = "Book returned suscessfully. Overdue fee = " + overDue;
            System.out.println("exceeded hours " + exceededHours);
        }

        if ((bookISBN & (differenceOfDays < 6)) || (DVDisbn & (differenceOfDays < 2))) {
            System.out.println("Item returned Successfully");
            westminsterLibryManager.retrunItem(reader, libraryItem);
            message = "Library Item returned Successfully, No OverDue";

        }


        JsonNode jsonNode = Json.toJson(new AppSummary(message));
        return ok(jsonNode).as("application/json");
    }


    public Result viewLibryItems() throws SQLException {

        /*
        this method is used to view all the library items
        available in the libraryy including DVD and books
         */

        String itemName = request().body().asText();
        JsonNode json = Json.parse(itemName);

        String libItemName = json.get("ItemName").textValue();
        System.out.println(libItemName);

        westminsterLibryManager westminsterLibryManager = new westminsterLibryManager();
        LibraryItem libraryItem = new LibraryItem(libItemName) {
            @Override
            public String getTitle() {
                return super.getTitle();
            }
        };

        westminsterLibryManager.viewItems();


        JsonNode jsonNode = Json.toJson(new AppSummary("Success"));
        return ok(jsonNode).as("application/json");
    }


    public Result removeLibItem() throws SQLException {

        /*
        this method is used to remove library items from the
        database;
         */

        String deleteitem = request().body().asText();
        JsonNode json = Json.parse(deleteitem);

        String libItemISBN = json.get("IsbnremoveItem").textValue();

        westminsterLibryManager westminsterLibryManager = new westminsterLibryManager();
        LibraryItem libraryItem = new LibraryItem(libItemISBN) {
            @Override
            public void setIsbn(String isbn) {
                super.setIsbn(isbn);
            }
        };

        String message = null;

        DBconnection connectClass = new DBconnection();
        Connection connection = connectClass.getConnection();

        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement statement3 = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT isbn FROM book");
        ResultSet rs1 = statement1.executeQuery("SELECT isbn FROM dvd");
        ResultSet rs3 = statement3.executeQuery("SELECT isbnBorrowed FROM borroweditems");

        boolean bookISBN = false;
        boolean DVDisbn = false;
        boolean unAvailability = false;

        while (rs.next()) {
            if (libItemISBN.equals(rs.getString("isbn"))) {
                System.out.println("book isbn is valid");
                bookISBN = true;
                break;
            }
        }

        while (rs1.next()) {
            if (libItemISBN.equals(rs1.getString("isbn"))) {
                System.out.println("DVD isbn is valid");
                DVDisbn = true;
                break;
            }
        }

        while (rs3.next()) {
            if (libItemISBN.equals(rs3.getString("isbnBorrowed"))) {
                unAvailability = true;
                break;
            }
        }

        System.out.println(bookISBN);
        System.out.println(DVDisbn);
        System.out.println(unAvailability);

        System.out.println(libraryItem.getIsbn());

        if ((bookISBN) & !unAvailability) {
            System.out.println("book deleted");
            message = "book deleted";
            westminsterLibryManager.deleteItem(libraryItem);
        } else if ((DVDisbn) & !unAvailability) {
            System.out.println("DVD deleted");
            message = "DVD deleted";
            westminsterLibryManager.deleteItem(libraryItem);
        } else if (unAvailability) {
            System.out.println("book is browwed");
            message = "Library item is currently burrowed by a reader";
        } else if (!bookISBN || !DVDisbn) {
            System.out.println("invalid isbn");
            message = "ISBN is incorrect";
        }


        JsonNode jsonNode = Json.toJson(new AppSummary(message));
        return ok(jsonNode).as("application/json");
    }



    public Result addReader() throws SQLException {

        /*
        this method is used to add a new reader to the system;
         */

        String returnitem = request().body().asText();
        JsonNode json = Json.parse(returnitem);

        String readerId = json.get("Id").textValue();
        String readerName = json.get("rname").textValue();
        String readerPhnNo = json.get("mobileNo").textValue();
        String readerEmail = json.get("Email").textValue();
        System.out.println(readerId);
        System.out.println(readerName);
        System.out.println(readerPhnNo);
        System.out.println(readerEmail);

        boolean readersIDno = false;
        String message = null;

        DBconnection connectClass = new DBconnection(); //get the database connection
        Connection connection = connectClass.getConnection();

        Statement statement2 = connection.createStatement();
        ResultSet rs2 = statement2.executeQuery("SELECT readerID FROM reader");

        while (rs2.next()) {
            if (readerId.equals(rs2.getString("readerID"))) {
                readersIDno = true;
                message = "Ready ID is already in use";
                break;
            }
        }

        Reader reader = new Reader(readerId, readerName, readerPhnNo, readerEmail);

        String sql = "INSERT INTO reader (readerID,readerName,readerEmail,readerPhnNo) VALUES('" + reader.getReaderID() + "','" + reader.getReaderName() + "','" + reader.getReaderEmail() + "','" + reader.getReaderMobileNo() + "')";

        Statement stat = connection.createStatement();
        if (!readersIDno) {
            stat.executeUpdate(sql);
            System.out.println("reader added successfully");
            message = "reader added successfully";
        }


        JsonNode jsonNode = Json.toJson(new AppSummary(message));
        return ok(jsonNode).as("application/json");
    }

    public Result appSummary() {
        JsonNode jsonNode = Json.toJson(new AppSummary("Post Request Test => Data Sending Success"));
        return ok(jsonNode).as("application/json");
    }
}
