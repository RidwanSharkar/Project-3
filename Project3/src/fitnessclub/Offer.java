package fitnessclub;

/**
 * Enum meant to represent the classes offered at the fitness club.
 * The 3 classes offered are: Pilates, Spinning and Cardio.
 * @author Ashley Berlinski
 */
public enum Offer {

    PILATES(), SPINNING(), CARDIO();

    /**
     * Returns a class offer corresponding to the given string, not case-sensitive.
     * @param offer the string of the class offer being requested
     * @return an offer enum corresponding to the given string, null if there were no matches.
     */
    public static Offer getOffer(String offer) {
        for (Offer offEnum: Offer.values()) {
            if (offEnum.name().equalsIgnoreCase(offer))
                return offEnum;
        }
        return null;
    }

}
