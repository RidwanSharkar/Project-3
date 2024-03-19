package fitnessclub;

/**
 * Subclass of Member meant to represent a family membership plan of fitness club.
 * Family Members are billed quarterly and are allowed only one guest pass.
 * @author Ashley Berlinski
 */
public class Family extends Member {

    private boolean guest;
    private final double FEE_PER_MONTH = 49.99;
    private final int MONTH_PER_CYCLE = 3;

    /**
     * Three parameter constructor for Family, takes in a profile, expiration date and home studio.
     * Sets guest pass to false initially.
     * @param profile the personal profile associated with the family member.
     * @param expire the expiration date of the family member's membership.
     * @param homeStudio the location of the family member's home studio.
     */
    public Family(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
        guest = false;
    }

    /**
     * Two parameter constructor for Family, takes in a profile and home studio.
     * Sets guest pass to false initially and sets expiration for 3 months later.
     * @param profile the personal profile associated with the family member.
     * @param homeStudio the location of the family member's home studio.
     */
    public Family (Profile profile, Location homeStudio) {
        super(profile, null, homeStudio);
        Date expire = Date.getTodaysDate();
        expire.nextMonth(3);
        this.setExpire(expire);
        guest = false;
    }

    /**
     * Adds a guest to the family member's plan, if there isn't one already assigned.
     * @return true if guest was added, false if otherwise.
     */
    @Override
    public boolean addGuest() {
        if (!guest){
            guest = true;
            return true;
        }
        return false;
    }

    /**
     * Removes a guest from the family member's plan, returns false if there wasn't one already assigned.
     * @return true if guest was removed, false if otherwise.
     */
    @Override
    public boolean removeGuest() {
        if(guest){
            guest = false;
            return true;
        }
        return false;
    }

    /**
     * Returns the bill for the family member's next billing cycle.
     * Will be charged a flat fee for each month and be billed the total amount on their billing cycle.
     * @return a double holding the value of the family member's bill that cycle.
     */
    @Override
    public double bill() {
        return FEE_PER_MONTH * MONTH_PER_CYCLE;
    }

    /**
     * Returns a string representation of the family member.
     * @return a string of the family member in the format: "[profile], Membership expires [expire],
     * Location: [homeStudio], (Family) guest-pass remaining: [guestRemaining]"
     */
    @Override
    public String toString() {
        String guestRemaining;
        if(!(getExpire().isTodayOrFutureDate()))
            guestRemaining = "not eligible";
        else
            guestRemaining = (guest) ? "0" : "1";

        return super.toString() + ", (Family) guest-pass remaining: " + guestRemaining;
    }
}
