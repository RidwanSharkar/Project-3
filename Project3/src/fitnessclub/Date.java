package fitnessclub;

import java.util.Calendar;

/**
 * Class meant to store and handle dates.
 * @author Ryan Colling, Ridwan Sharkar
 */
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    private static final int MONTH_INDEX = 0; //Used when parsing string dates
    private static final int DAY_INDEX = 1;
    private static final int YEAR_INDEX = 2;

    private static final int QUADRENNIAL = 4; //Used for checking leap years
    private static final int CENTENNIAL = 100;
    private static final int QUARTER_CENTENNIAL = 400;

    //Calendar class begins months (Jan) at 0 instead of 1 so values are readjusted.
    private static final int [] MONTHS_WITH_THIRTY_ONE_DAYS =
            {Calendar.JANUARY + 1, Calendar.MARCH + 1, Calendar.MAY + 1, Calendar.JULY + 1,
            Calendar.AUGUST + 1, Calendar.OCTOBER + 1, Calendar.DECEMBER + 1};

    private static final int NUMBER_OF_MONTHS = 12;
    private static final int LEAP_DAY = 29;
    private static final int MAX_DAYS_IN_MONTH = 31;
    private static final int EARLIEST_YEAR = 1900;
    private static final int ADULT = 18;

    /**
     * Three parameter constructor for Date.
     * @param month the month associated with the date.
     * @param day the day associated with the date.
     * @param year the year associated with the date.
     */
    public Date (int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * One parameter constructor for Date.
     * Takes in a Calendar object to use for the date.
     * @param cal the calendar used to create date.
     */
    public Date(Calendar cal) {
        this.month = cal.get(Calendar.MONTH) + 1;
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.year = cal.get(Calendar.YEAR);
    }

    /**
     * Determines if given year in date is a leap year.
     * @return true if the year is a leap year, false if otherwise.
     */
    private boolean isLeapYear() {
        //Centennial years are only leap years if they are also quarter centennial.
        if (year % CENTENNIAL == 0)
            return year % QUARTER_CENTENNIAL == 0;

        return year % QUADRENNIAL == 0;
    }

    /**
     * Determines if given month has 31 days.
     * @return true if the month has 31 days, false if otherwise.
     */
    private boolean isMonthWithThirtyOneDays()
    {
        for (int thirtyOneDayMonth : MONTHS_WITH_THIRTY_ONE_DAYS) {
            if (month == thirtyOneDayMonth)
                return true;
        }

        return false;
    }

    /**
     * Determines if given date is a valid calendar date.
     * Dates from the future, today's date or dates before 1900 are rejected.
     * @return true if the date is valid, false if otherwise.
     */
    public boolean isValid() {
        if (year < EARLIEST_YEAR)
            return false;

        if(isTodayOrFutureDate())
            return false;

        if (month < 1 || month > NUMBER_OF_MONTHS)
            return false;

        //Calendar class begins months (Jan) at 0 instead of 1 so values are readjusted.
        if (month == (Calendar.FEBRUARY + 1)) {
            if (day == LEAP_DAY)
                return isLeapYear();
            else return day < LEAP_DAY;
        }

        if (day == MAX_DAYS_IN_MONTH) {
            return isMonthWithThirtyOneDays();
        }

        return day >= 1 && day < MAX_DAYS_IN_MONTH;
    }

    /**
     * Checks if date is today's date or a future date
     * @return true if date is today's or a future date, false if otherwise.
     */
    public boolean isTodayOrFutureDate() {
        Date currentDate = getTodaysDate();
        return this.compareTo(currentDate) >= 0;
    }

    /**
     * Checks if a person with this date as a DOB would be an adult.
     * @return true if DOB would make the person 18 years or older, false if otherwise.
     */
    public boolean isAdult() {
        Date today = Date.getTodaysDate();
        int age = today.getYear() - this.getYear();

        if (age == ADULT) { //Check months if difference of years is 18
            return (this.getMonth() <= today.getMonth());
        }
        return (age > ADULT);
    }

    /**
     * Sets the current date to a given amount of month later.
     * @param numberOfMonths the number of months to be skipped to later.
     */
    public void nextMonth(int numberOfMonths) {
        for (int i = 0; i < numberOfMonths; i++) {
            if (this.month == NUMBER_OF_MONTHS) { //If December, change month to Jan and increase year
                this.month = 1;
                this.year++;
            } else
                this.month++;

            if (this.day == MAX_DAYS_IN_MONTH && !(isMonthWithThirtyOneDays())) {
                this.day--; //Decrease day if original date was xx/31/xxxx and next month doesn't have 31 days.
            }
        }
    }

    /**
     * Returns the day associate with the date
     * @return the day of the date
     */
    public int getDay() { return this.day; }

    /**
     * Returns the month associated with the date.
     * @return the month of the date.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the year associated with the date.
     * @return the year of the date.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Compares a given date to itself.
     * @param o the date that is being compared.
     * @return 0 if dates are equal, -1 if o is greater, otherwise returns 1
     */
    @Override
    public int compareTo(Date o) {
        int lessThan = -1;
        int greaterThan = 1;
        int equal = 0;

        if (this.equals(o))
            return equal;

        if (this.year == o.year) { //Compare years first
            if (this.month == o.month) { //Compare months next
                if (this.day > o.day) //Compare days last
                    return greaterThan;
                else
                    return lessThan;
            }
            else if (this.month > o.month)
                return greaterThan;
            else
                return lessThan;
        }
        else if (this.year > o.year) {
            return greaterThan;
        }

        return lessThan;
    }

    /**
     * Determines if a given object is equal to the date itself.
     * @param obj the object that is being compared.
     * @return true if the dates are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return date.month == this.month && date.day == this.day &&
                    date.year == this.year;
        }
        return false;
    }

    /**
     * Returns a string representing the date.
     * @return a string of the date in the format MM/DD/YYYY.
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Returns a date object that corresponds to today's date.
     * @return a date whose value is today's date.
     */
    public static Date getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        return new Date(cal);
    }

    /**
     * Creates a date given a string in format MM/DD/YYYY.
     * @param date the date formatted as MM/DD/YYYY.
     * @return a date corresponding to the string given, null if string wasn't in proper format.
     */
    public static Date getDate(String date) {
        String [] splitDate = date.split("/");
        int month, day, year;

        try {
            month = Integer.parseInt(splitDate[MONTH_INDEX]);
            day = Integer.parseInt(splitDate[DAY_INDEX]);
            year = Integer.parseInt(splitDate[YEAR_INDEX]);
        }
        catch(NumberFormatException e) {
            return null;
        }

        return new Date(month, day, year);
    }

}
