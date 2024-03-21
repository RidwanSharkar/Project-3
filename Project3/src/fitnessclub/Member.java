package fitnessclub;

/**
 * Superclass meant to represent a member of fitness club.
 * Holds the basic information of a membership the member's profile, membership expiration and chosen home studio.
 * Should be used as a type and not as instance of an actual membership.
 * @author Ryan Colling, Ridwan Sharkar@author Ashley Berlinski
 */
public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;

    protected final double INVALID_BILL = -1.0;

    /**
     * Default constructor for Member sets all values to their defaults.
     */
    public Member() {
        this.profile = null;
        this.expire = null;
        this.homeStudio = null;
    }

    /**
     * Three parameter constructor for Member, takes in a profile, expiration date and home studio.
     * @param profile the personal profile associated with the member
     * @param expire the expiration date of the member's membership.
     * @param homeStudio the location of the member's home studio.
     */
    public Member(Profile profile, Date expire, Location homeStudio) {
        this.profile = profile;
        this.expire = expire;
        this.homeStudio = homeStudio;
    }

    /**
     * Adds a guest to the member's plan, should only be called by Member's subclasses.
     * @return false since Member class should not call this method.
     */
    public boolean addGuest() {
        return false;
    }

    /**
     * Removes a guest from the member's plan, should only be called by Member's subclasses.
     * @return false since Member class should not call this method.
     */
    public boolean removeGuest() {
        return false;
    }

    /**
     * Records attendance of a member, should only be called by Member's subclasses.
     */
    public void attendClass() {
    }

    /**
     * Returns the bill for the member's next billing cycle, should only be called by Member subclasses.
     * @return -1 since Member class should not call this method.
     */
    public double bill() {
        return INVALID_BILL;
    }

    /**
     * Returns the expiration date of the member's membership.
     * @return the expiration date of the membership.
     */
    public Date getExpire() {
        return expire;
    }

    /**
     * Returns the profile of the member.
     * @return the profile of the member.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Returns the home studio of the member.
     * @return the home studio of the member.
     */
    public Location getHomeStudio() {
        return homeStudio;
    }

    /**
     * Sets the expiration date for a member given a new date.
     * @param expire the new expiration date for the member.
     */
    protected void setExpire(Date expire) {
        this.expire = expire;
    }

    /**
     * Returns first and last name of a member as a single string.
     * @return a string of the member's first and last name.
     */
    public String getMemberName() {
        return profile.getName();
    }

    /**
     * Determines if a given object is equal to itself. Members are equal if their profiles are equal.
     * @param obj the object that is being compared to.
     * @return true if the members are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member member = (Member) obj;
            return this.profile.equals(member.profile);
        }
        return false;
    }

    /**
     * Compares another member to itself, uses members' profile to compare itself.
     * @param o the member to be compared.
     * @return 1 if member comes after the given one, -1 if the member comes after, 0 if they are equal.
     */
    @Override
    public int compareTo(Member o) {
            return this.profile.compareTo(o.profile);
    }

    /**
     * Returns a string representation of the member.
     * @return a string of the member in the format: "[profile], Membership expires [expire], Location: [homeStudio]"
     */
    @Override
    public String toString() {
        String expireString = (expire.isTodayOrFutureDate()) ? "expires " : "expired ";
        return profile + ", Membership " + expireString + expire + ", Home Studio: " + homeStudio;
    }
}
