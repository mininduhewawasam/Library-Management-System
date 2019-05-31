package LibManager;

public class DateTime {

    /*
    DateTime class
     */

    private int day;//day of the date
    private int month;//month of the date
    private int year;//year of the date
    private int hour;//hour data
    private int minute;//minute data

    public String getDateFormated() {//getDateFormatter is used to format the date
        return String.format("%02d-%02d-%04d", month, day, year);

    }

    public String getTimeFormated() {//gettimeformted is used to format the time
        return String.format("%02d:%02d", hour, minute);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DateTime(int month, int day, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DateTime(int month, int day, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

}
