package fitnessclub;

/**
 * Enum meant to represent the locations of the studios, contains city, county and zip code of said location.
 * The 5 locations are: Bridgewater, Edison, Franklin, Piscataway, Somerville.
 * @author Ashley Berlinski
 */
public enum Location {

    BRIDGEWATER("SOMERSET", "08807"),
    EDISON("MIDDLESEX", "08837"),
    FRANKLIN("SOMERSET", "08873"),
    PISCATAWAY("MIDDLESEX", "08854"),
    SOMERVILLE( "SOMERSET","08876");

    private final String county;
    private final String zipCode;

    /**
     * Constructor for the location enum, stores the names of the city, county and its zipcode.
     * @param county the county associated with the location.
     * @param zipCode the zip code associated with the location.
     */
    Location(String county, String zipCode) {
        this.county = county;
        this.zipCode = zipCode;
    }

    /**
     * Returns the county associated with the location.
     * @return the county of the location.
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * Returns the zip code associated with the location.
     * @return the zip code of the location.
     */
    public String getZipCode(){
        return this.zipCode;
    }

    /**
     * Returns a string representation of the location.
     * @return a string of the location in the format: "CITY, ZIPCODE, COUNTY"
     */
    @Override
    public String toString(){
        return this.name() + ", " + zipCode + ", " + county;
    }

    /**
     * Returns a location corresponding to the given string, not case-sensitive.
     * @param location the string of the location being requested
     * @return a location enum corresponding to the given string, null if there were no matches.
     */
    public static Location getLocation(String location) {
        for (Location locEnum: Location.values()) {
            if (locEnum.name().equalsIgnoreCase(location))
                return locEnum;
        }
        return null;
    }
}
