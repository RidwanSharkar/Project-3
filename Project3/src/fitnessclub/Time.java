package fitnessclub;

import java.text.DecimalFormat;

/**
 * Enum meant to represent the start time associated with the periods of the day.
 * Morning begins at 9:30, Afternoon begins at 14:00, and Evenings begins at 18:30.
 * @author Ashley Berlinski
 */
public enum Time {
    MORNING (9, 30),
    AFTERNOON (14, 0),
    EVENING (18, 30);

    private int hour;
    private int minute;

    /**
     * Constructor for the time enum, takes in the hour and minute in which the time period starts
     * @param hour the hour when the time period starts.
     * @param minute the minute when the time period starts.
     */
    Time (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Returns a time corresponding to the given string, not case-sensitive.
     * @param time the string of the time being requested
     * @return a time enum corresponding to the given string, null if there were no matches.
     */
    public static Time getTime(String time) {
        for (Time timeEnum: Time.values()) {
            if (timeEnum.name().equalsIgnoreCase(time))
                return timeEnum;
        }
        return null;
    }

    /**
     * Returns a string representation of the start time of day in military hours.
     * @return the time in military hours 0:00
     */
    @Override
    public String toString() {
        String minute = new DecimalFormat("00").format(this.minute);
        return this.hour + ":" + minute;
    }
}
