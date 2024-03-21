package fitnessclub;

/**
 * Enum meant to represent the instructors teaching classes at the fitness club.
 * The 5 instructors are: Jennifer, Kim, Denise, Davis, and Emma.
 * @author Ryan Colling, Ridwan Sharkar
 */
public enum Instructor {

    JENNIFER(), KIM(), DENISE(), DAVIS(), EMMA();

    /**
     * Returns an instructor corresponding to the given string, not case-sensitive.
     * @param instructor the string of the instructor being requested
     * @return an instructor enum corresponding to the given string, null if there were no matches.
     */
    public static Instructor getInstructor(String instructor) {
        for (Instructor instructEnum: Instructor.values()) {
            if (instructEnum.name().equalsIgnoreCase(instructor))
                return instructEnum;
        }
        return null;
    }

}
