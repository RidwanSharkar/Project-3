package fitnessclub;

/**
 * Subclass of Member meant to represent a basic membership plan of fitness club.
 * Basic Members are billed monthly and are only allowed to attend a certain amount of classes per billing cycle.
 * @author Ryan Colling, Ridwan Sharkar
 */
public class Basic extends Member {

    private int numClasses;
    private final double FEE_PER_MONTH = 39.99;
    private final int ADDITIONAL_CHARGE = 10;
    private final int MAX_CLASSES_PER_MONTH = 4;

    /**
     * Three parameter constructor for Basic, takes in a profile, expiration date and home studio.
     * Sets number of classes taken to zero initially.
     * @param profile the personal profile associated with the basic member
     * @param expire the expiration date of the basic member's membership.
     * @param homeStudio the location of the basic member's home studio.
     */
    public Basic(Profile profile, Date expire, Location homeStudio) {

        super(profile, expire, homeStudio);
        numClasses = 0;
    }

    /**
     * Two parameter constructor for Basic, takes in a profile and home studio.
     * Sets number of classes taken to zero initially and sets expiration for 1 month later.
     * @param profile the personal profile associated with the basic member.
     * @param homeStudio the location of the basic member's home studio.
     */
    public Basic (Profile profile, Location homeStudio) {
        super(profile, null, homeStudio);
        Date expire = Date.getTodaysDate();
        expire.nextMonth(1);
        this.setExpire(expire);
        numClasses = 0;
    }

    /**
     * Adds an additional count to the number of classes taken per billing cycle.
     */
    @Override
    public void attendClass() {
        numClasses++;
    }

    /**
     * Returns the bill for the basic member's next billing cycle.
     * Will be charged flat fee along with additional costs if member takes more classes than allotted to them.
     * @return a double holding the value of the basic member's bill that cycle.
     */
    @Override
    public double bill() {
        double bill = FEE_PER_MONTH;

        for (int i = MAX_CLASSES_PER_MONTH; i < numClasses; i++) { //Add charges for extra classes taken.
            bill += ADDITIONAL_CHARGE;
        }

        return bill;
    }

    /**
     * Returns a string representation of the basic member.
     * @return a string of the basic member in the format: "[profile], Membership expires [expire],
     * Location: [homeStudio], (Basic) number of classes attended: [numClasses]"
     */
    @Override
    public String toString() {
        return super.toString() + ", (Basic) number of classes attended: " + numClasses;
    }

}
