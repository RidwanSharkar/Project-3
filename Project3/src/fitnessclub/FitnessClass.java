package fitnessclub;

/**
 * Class meant to represent a fitness class and its information.
 * Contains information about the instructor, start time, class type, studio location and members and guests attending.
 * @author Ryan Colling, Ridwan Sharkar
 */
public class FitnessClass {

    private Offer classInfo;
    private Instructor instructor;
    private Location studio;
    private Time time;
    private MemberList members;
    private MemberList guests;

    /**
     * Four parameter constructor for FitnessClass, creates empty Memberlists for members and guests.
     * @param classInfo the type of class.
     * @param instructor the instructor teaching the fitness class.
     * @param studio the location the fitness class is being held.
     * @param time the start time for the fitness class.
     */
    public FitnessClass(Offer classInfo, Instructor instructor,
                        Location studio, Time time) {
        this.classInfo = classInfo;
        this.instructor = instructor;
        this.studio = studio;
        this.time = time;
        members = new MemberList();
        guests = new MemberList();
    }

    /**
     * Determines if guest list is empty
     * @return true if the guest list is empty, false if otherwise.
     */
    private boolean isGuestEmpty() {
        return (guests.isEmpty());
    }

    /**
     * Determines if member list is empty
     * @return true if the member list is empty, false if otherwise.
     */
    private boolean isMembersEmpty() {
        return (members.isEmpty());
    }

    /**
     * Returns the type of fitness class.
     * @return an offer enum corresponding to the type of class.
     */
    public Offer getClassInfo() {
        return classInfo;
    }

    /**
     * Returns the instructor teaching the fitness class.
     * @return an instructor enum corresponding to the person teaching the class.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Returns the studio where the class is being held.
     * @return a location enum corresponding to the studio where the class is being held.
     */
    public Location getStudio() {
        return studio;
    }

    /**
     * Returns the time when the class is being held.
     * @return a time enum corresponding to the start time of the class.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Return the list of members attending the fitness class
     * @return the list of members attending the class.
     */
    public MemberList getMembers(){
        return this.members;
    }

    /**
     * Return the list of guests attending the fitness class
     * @return the list of guests attending the class.
     */
    public MemberList getGuests(){
        return this.guests;
    }

    /**
     * Determines if a given object is equal to itself.
     * Fitness classes are equal if the instructor, class type and studio location match.
     * @param obj the object that is being compared to.
     * @return true if the fitness classes are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FitnessClass) {
            FitnessClass fitnessClass = (FitnessClass) obj;
            return this.instructor.equals(fitnessClass.instructor) &&
                    this.classInfo.equals(fitnessClass.classInfo) &&
                    this.studio.equals(fitnessClass.studio);
        }
        return false;
    }

    /**
     * Returns a string representation of the fitness class and its information.
     * @return a string of the fitness class in the format: "[classInfo] - [instructor], [time], [studio]"
     */
    @Override
    public String toString() {
        String header = classInfo + " - " + instructor + ", " + time + ", " + studio.name() + "\n";
        String attendees = "", guests = "";
        if (!(isMembersEmpty()))
            attendees = "[Attendees]\n" + members.toSpaceString();

        if(!(isGuestEmpty()))
            guests = "[Guests]\n" + this.guests.toSpaceString();

        return header + attendees + guests;
    }
}
