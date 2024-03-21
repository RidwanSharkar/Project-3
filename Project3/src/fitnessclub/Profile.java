package fitnessclub;

/**
 * Class meant to hold the personal information of a person's profile.
 * Contains their first and last name as well as their date of birth.
 * @author Ryan Colling, Ridwan Sharkar
 */
public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Three parameter constructor, takes in a first name, a last and a DOB.
     * @param fname the first name of the profile.
     * @param lname the last name of the profile.
     * @param dob the date of birth of the profile.
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Returns the first name of the profile.
     * @return the first name of the profile.
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * Returns the last name of the profile.
     * @return the last name of the profile.
     */
    public String getLname(){
        return this.lname;
    }

    /**
     * Returns the DOB of the profile.
     * @return the DOB of the profile.
     */
    public Date getDOB(){
        return this.dob;
    }

    /**
     * Returns first and last name of a profile as a single string.
     * @return a string of the profile's first and last name.
     */
    public String getName() {
        return fname + " " + lname;
    }

    /**
     * Determines if a given object is equal to itself. Profiles are equal if names and DOB match.
     * @param obj the object that is being compared to.
     * @return true if the profiles are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return this.lname.equalsIgnoreCase(profile.lname) &&
                    this.fname.equalsIgnoreCase(profile.fname) &&
                    this.dob.equals(profile.dob);
        }
        return false;
    }

    /**
     * Compares another profile to itself. Will compare last names, then first names and finally DOBs.
     * @param o the profile to be compared.
     * @return 1 if profile comes after the given one, -1 if the profile comes after, 0 if they are equal.
     */
    @Override
    public int compareTo(Profile o) {
        int lessThan = -1;
        int greaterThan = 1;

        if (this.lname.equalsIgnoreCase(o.lname)) { //Compare last names
            if (this.fname.equalsIgnoreCase(o.fname)) { //Compare first names
                return this.dob.compareTo(o.dob); //Compare date of birth
            }
            return (this.fname.compareTo(o.fname) >= greaterThan) ? greaterThan : lessThan;
        }
        return (this.lname.compareTo(o.lname) >= greaterThan) ? greaterThan : lessThan;
    }

    /**
     * Returns a string representation of the profile.
     * @return a string of the format "first name:last name:DOB"
     */
    @Override
    public String toString(){
        return fname + ":" + lname + ":" + dob;
    }

}
