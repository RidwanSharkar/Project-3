package fitnessclub;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Class meant to hold and manage a list of different members (Basic, Family and Premium).
 * @author Ryan Colling, Ridwan Sharkar
 */
public class MemberList {
    private Member [] members;
    private int size;

    private static final int NOT_FOUND = -1;
    private static final int CAPACITY_INCREMENTS = 4;
    private static final int LESS_THAN = 0;
    private static final int EQUAL = 0;

    private static final int COMPARE_MEMBER = 1; //Used for sorting
    private static final int COMPARE_COUNTY = 2;
    private static final int COMPARE_ZIPCODE = 3;
    private static final int NO_SECONDARY_MODE = -1;

    private static final int MEMBER_TYPE_INDEX = 0; //Parsing Tokens
    private static final int FNAME_INDEX = 1;
    private static final int LNAME_INDEX = 2;
    private static final int BDATE_INDEX = 3;
    private static final int EXPDATE_INDEX = 4;
    private static final int LOCATION_INDEX = 5;

    /**
     * Default constructor for MemberList, sets the initial capacity to four.
     */
    public MemberList() {
        this.size = CAPACITY_INCREMENTS;
        this.members = new Member[size];
    }

    /**
     * Helper method used to find the index of a member in the members list.
     * @param member the member that is being searched for in the members list.
     * @return the index of the member in the list, returns -1 if member is not in list.
     */
    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if(this.members[i] == null) //Hitting a null means hit the end of the list.
                break;

            if(this.members[i].equals(member))
                return i;
        }

        return NOT_FOUND;
    }

    /**
     * Helper Method used to increase the capacity of the member array.
     */
    private void grow() {
        Member [] increasedMembers = new Member [size + CAPACITY_INCREMENTS];

        for (int i = 0; i < this.size; i++) {
            increasedMembers[i] = members[i];
        }

        this.size += CAPACITY_INCREMENTS;
        members = increasedMembers;
    }

    /**
     * Helper method used to sort collection, will sort by the primary attribute first and
     * then secondary if they are equal.
     * @param primaryMode used to indicate which primary attribute the members are being compared by.
     * @param secondaryMode used to indicate which secondary attribute the members are being compared by.
     */
    private void insertionSort (int primaryMode, int secondaryMode) {
        int smallestIndex;

        for (int i = 0; i < (size - 1); i++) {
            if (members[i] == null)
                break;

            smallestIndex = i;

            for (int j = (i + 1); j < size; j++) {
                if (members[j] == null)
                    break;

                int comparison = compare(members[j], members[smallestIndex], primaryMode);
                if (comparison < LESS_THAN){ //Sort with primary attribute first
                    smallestIndex = j;
                }
                if (comparison == EQUAL) { //Sort with secondary attribute
                    comparison = compare(members[j], members[smallestIndex], secondaryMode);
                    if(comparison < LESS_THAN) {
                        smallestIndex = j;
                    }
                }
            }
            swap(i, smallestIndex);
        }
    }

    /**
     * Helper method for insertion sort, compares two members given a mode of comparison.
     * @param member1 the first member being compared
     * @param member2 the second member being compared
     * @param mode an int which determines by which attribute the members will be compared.
     * @return returns a negative value if 1st attribute comes before, positive if it comes after, 0 if equal.
     */
    private int compare(Member member1, Member member2, int mode) {
        switch (mode) {
            case COMPARE_MEMBER:
                return member1.compareTo(member2);
            case COMPARE_COUNTY:
                String county1 = member1.getHomeStudio().getCounty();
                String county2 = member2.getHomeStudio().getCounty();
                return county1.compareTo(county2);
            case COMPARE_ZIPCODE:
                String zip1 = member1.getHomeStudio().getZipCode();
                String zip2 = member2.getHomeStudio().getZipCode();
                return zip1.compareTo(zip2);
            default: return NOT_FOUND; //Invalid mode entered.
        }
    }



    /**
     * Helper method used to swap members in the collection given their indexes
     * @param index1 the index of the first member to be swapped.
     * @param index2 the index of the second member to be swapped.
     */
    private void swap(int index1, int index2) {
        Member swapped = members[index1];
        members[index1] = members[index2];
        members[index2] = swapped;
    }

    /**
     * Returns a new Member object given an array of tokens.
     * Member type - First name - Last name - Birth date - Expiration date - Home studio location
     * @param tokens an array of string tokens used to create member object.
     * @return a member corresponding to the tokens given, null if tokens were invalid or there wasn't enough.
     */
    private Member createNewMember(String [] tokens) {

        if (tokens.length <= LOCATION_INDEX) //Check if there is enough tokens
            return null;

        Date birthDate = Date.getDate(tokens[BDATE_INDEX]);
        Date expDate = Date.getDate(tokens[EXPDATE_INDEX]);
        Location location = Location.getLocation(tokens[LOCATION_INDEX]);

        if (birthDate == null || expDate == null || location == null)
            return null;

        Profile profile = new Profile(tokens[FNAME_INDEX], tokens[LNAME_INDEX],
                birthDate);

        return switch (tokens[MEMBER_TYPE_INDEX]) {
            case "B" -> new Basic(profile, expDate, location);
            case "F" -> new Family(profile, expDate, location);
            case "P" -> new Premium(profile, expDate, location);
            default -> null;
        };
    }

    /**
     * Determines if the member list is empty
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (members[0] == null);
    }

    /**
     * Determines if a member is already in the list.
     * @param member the member that is being searched for in the list.
     * @return true if member is in member list, false otherwise.
     */
    public boolean contains(Member member) {
        return find(member) != NOT_FOUND;
    }

    /**
     * Checks if a profile matches a profile of a member in the list
     * @param profile the profile being compared to the members
     * @return the member that matches the profile
     */
    public Member containsProfile (Profile profile) {

        Member rmember = new Member();

        for(int i = 0; i < members.length; i++) {
            if (members[i].getProfile().compareTo(profile) == 0) {
                rmember = members[i];
            }
        }

        return rmember;
    }

    /**
     * Adds a member to the member list if it is not already in there.
     * @param member the member that is to be added.
     * @return true if member was successfully added, false if otherwise.
     */
    public boolean add(Member member) {
        if (contains(member))
            return false;

        int emptyIndex = 0; //Next available space for member in list

        for (int i = 0; i < size; i++) {
            if (this.members[i] == null) {
                emptyIndex = i;
                break;
            }
        }

        members[emptyIndex] = member;

        if (emptyIndex + 1 == size) //Increase size of member list last available slot is assigned
            grow();

        return true;
    }

    /**
     * Removes an existing member from the list.
     * @param member the member that is to be removed.
     * @return true if member was successfully removed, false if otherwise.
     */
    public boolean remove(Member member) {
        int index = find(member);

        if (index == NOT_FOUND)
            return false;

        for (int i = index; i < (size - 1); i++) { //Replace current album in list with one ahead of it.
            if (members[i + 1] == null) { //Set extra index to null
                members[i] = null;
                break;
            }
            members[i] = members[i + 1];
        }

        return true;
    }

    /**
     * Loads member data from a text file into the member list, skips over invalid input.
     * @param file the file that is being read and providing memebership entries.
     * @throws IOException if there were issues opening or reading the provided file.
     */
    public void load(File file) throws IOException {

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String [] line = scanner.nextLine().split("\\s");
            Member member = createNewMember(line);

            if (member != null) {
                add(member);
            }
        }
    }

    /**
     * Sorts members in list by county and then zipcode, finally prints to console.
     */
    public void printByCounty() { //sort by county then zip code
        insertionSort(COMPARE_COUNTY, COMPARE_ZIPCODE);
        System.out.print(this);
    }

    /**
     * Sorts members in list by their profile, finally prints to console.
     */
    public void printByMember() {
        insertionSort(COMPARE_MEMBER, NO_SECONDARY_MODE);
        System.out.print(this);
    }

    /**
     * Prints list of members with their current positions along with the amount due for their next billing cycle.
     */
    public void printFees() {
        for (int i = 0; i < size; i++) {
            if (members[i] == null)
                break;

            String bill = new DecimalFormat("0.00").format(members[i].bill());
            System.out.println(members[i] + " [next due: $" + bill + "]");
        }
    }

    /**
     * Given a member object, returns an equivalent member in the list.
     * @param member the member that is being searched for.
     * @return a member corresponding to the one given, null if not in list.
     */
    public Member getMember(Member member) {
        int index = find(member);
        return (index != NOT_FOUND) ? members[index] : null;
    }

    /**
     * Returns a string representation of the member list with spaces in the beginning.
     * @return a list of the members all separated by a new line and starting with spaces.
     */
    public String toSpaceString() {
        String listOfMembers = "";
        for (int i = 0; i < size; i++) {
            if (members[i] == null)
                break;

            listOfMembers += ("   " + members[i]) + "\n";
        }
        return listOfMembers;
    }

    /**
     * Returns a string representation of the member list.
     * @return a list of the members all separated by a new line.
     */
    @Override
    public String toString() {
        String listOfMembers = "";
        for (int i = 0; i < size; i++) {
            if (members[i] == null)
                break;

            listOfMembers += (members[i]) + "\n";
        }
        return listOfMembers;
    }
}
