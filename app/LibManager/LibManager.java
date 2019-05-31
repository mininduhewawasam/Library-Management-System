package LibManager;

import java.sql.SQLException;

public interface LibManager {

    void addBook(Book book) throws SQLException;

    void addDVD(DVD dvd) throws SQLException;

    void retrunItem(Reader reader, LibraryItem libraryItem) throws SQLException;

    void borrowItem(Reader reader, LibraryItem libraryItem) throws SQLException;

    void deleteItem(LibraryItem libraryItem) throws SQLException;

    void generateReport();

    void viewItems() throws SQLException;
}
