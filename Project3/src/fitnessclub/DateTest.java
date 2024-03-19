package fitnessclub;

import org.junit.Test;
import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void testFutureDate_OutOfRange() {
        Date date = Date.getDate("2/2/2025");
        assertFalse(date.isValid());
    }

    @Test
    public void testDateBeforeNineteenHundred_OutOfRange() {
        Date date = Date.getDate("2/2/1899");
        assertFalse(date.isValid());
    }

    @Test
    public void testInvalidMonths_OutOfRange() {
        Date date = Date.getDate("13/2/2001");
        assertFalse(date.isValid());
    }

    @Test
    public void testMonthNotWithThirtyOneDays_OutOfRange() {
        Date date = Date.getDate("6/31/2012");
        assertFalse(date.isValid());
    }

    @Test
    public void testInvalidDay_OutOfRange() {
        Date date = Date.getDate("8/32/2012");
        assertFalse(date.isValid());
    }

    @Test
    public void testMonthWithThirtyOneDays_InRange() {
        Date date = Date.getDate("12/31/1998");
        assertTrue(date.isValid());
    }

    @Test
    public void testValidLeapDay_LeapYear() {
        Date date = Date.getDate("2/29/2000");
        assertTrue(date.isValid());
    }

    @Test
    public void testInvalidLeapDay_LeapYear() {
        Date date = Date.getDate("2/29/1900");
        assertFalse(date.isValid());
    }
}