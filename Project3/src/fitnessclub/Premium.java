package fitnessclub;

/**
 * Subclass of Member meant to represent a premium membership plan of fitness club.
 * Premium Members are billed yearly with one month free and are allowed three guest passes.
 * @author Ryan Colling, Ridwan Sharkar
 */
public class Premium extends Member {

    private int guestPass;
    private final double FEE_PER_MONTH = 59.99;
    private final int MONTH_PER_CYCLE = 11;
    private final int MAX_NUM_GUEST_PASSES = 3;

    /**
     * Three parameter constructor for Premium, takes in a profile, expiration date and home studio.
     * Sets guest pass to 0 initially.
     * @param profile the personal profile associated with the premium member.
     * @param expire the expiration date of the premium member's membership.
     * @param homeStudio the location of the premium member's home studio.
     */
    public Premium(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
        guestPass = 0;
    }

    /**
     * Two parameter constructor for Premium, takes in a profile and home studio.
     * Sets guest pass to 0 initially and sets expiration for 12 months later.
     * @param profile the personal profile associated with the premium member.
     * @param homeStudio the location of the premium member's home studio.
     */
    public Premium (Profile profile, Location homeStudio) {
        super(profile, null, homeStudio);
        Date expire = Date.getTodaysDate();
        expire.nextMonth(12);
        this.setExpire(expire);
        guestPass = 0;
    }

    /**
     * Adds a guest to the premium member's plan, if they are at their maximum guest passes.
     * @return true if guest was added, false if otherwise.
     */
    @Override
    public boolean addGuest() {
        if (guestPass < MAX_NUM_GUEST_PASSES){
            guestPass++;
            return true;
        }
        return false;
    }

    /**
     * Removes a guest from the premium member's plan, returns false if there wasn't one already assigned.
     * @return true if guest was removed, false if otherwise.
     */
    @Override
    public boolean removeGuest() {
        if(guestPass > 0){
            guestPass--;
            return true;
        }
        return false;
    }

    /**
     * Returns the bill for the premium member's next billing cycle.
     * Will be charged a flat fee for each month and be billed the total amount on their billing cycle.
     * @return a double holding the value of the premium member's bill that cycle.
     */
    @Override
    public double bill() {
        return FEE_PER_MONTH * MONTH_PER_CYCLE;
    }

    /**
     * Returns a string representation of the family member.
     * @return a string of the family member in the format: "[profile], Membership expires [expire],
     * Location: [homeStudio] (Premium) guest-pass remaining: [guestRemaining]"
     */
    @Override
    public String toString() {
        String guestRemaining;
        if(!(getExpire().isTodayOrFutureDate()))
            guestRemaining = "not eligible";
        else
            guestRemaining = Integer.toString(MAX_NUM_GUEST_PASSES - guestPass);

        return super.toString() + " (Premium) guest-pass remaining: " + guestRemaining;
    }

}
