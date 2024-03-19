package fitnessclub;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Instance stores a Schedule object that represents an array of FitnessClass objects.
 * Contains methods to add and expand the classes array and to load class information from a txt file.
 * @author Ridwan Sharkar, Ashley Berlinski
 */
public class Schedule
{

    private FitnessClass[] classes;
    private int numClasses;
    private static final int CAPACITY_INCREMENTS = 4;
    private static final int OFFER_INDEX = 0; //Parsing Tokens
    private static final int INSTRUCT_INDEX = 1;
    private static final int TIME_INDEX = 2;
    private static final int LOCATION_INDEX = 3;
    private static final int NOT_FOUND = -1;


    /**
     * Constructor that initializes the classes array and sets its initial number of classes to 0.
     */
    public Schedule() {
        classes = new FitnessClass[CAPACITY_INCREMENTS];
        numClasses = 0;
    }

    /**
     * Adds a FitnessClass to the schedule. Calls to expandArray() if necessary.
     * @param fitnessClass the object to add to the schedule.
     */
    private void addFitnessClass(FitnessClass fitnessClass) {
        if (numClasses == classes.length) {
            expandArray();
        }
        classes[numClasses++] = fitnessClass;
    }

    /**
     * Expands, copies, and updates existing classes array to accommodate more fitness classes
     */
    private void expandArray() {
        FitnessClass[] newClasses = new FitnessClass[classes.length + CAPACITY_INCREMENTS];
        for (int i = 0; i < numClasses; i++) {
            newClasses[i] = classes[i];
        }
        classes = newClasses;
    }

    /**
     * Helper method used to find the index of a fitness class in the schedule.
     * @param fitnessClass the fitness class that is being searched for in the schedule.
     * @return the index of the fitness class in the list, returns -1 if fitness is not in list.
     */
    private int find(FitnessClass fitnessClass) {
        if(fitnessClass == null)
            return NOT_FOUND;

        for (int i = 0; i < numClasses; i++) {
            if(this.classes[i] == null) //Hitting a null means hit the end of the list.
                break;

            if(this.classes[i].equals(fitnessClass))
                return i;
        }

        return NOT_FOUND;
    }

    /**
     * Returns a fitness class in the schedule given a fitness class of equal value.
     * @param fitnessClass the fitness class being requested.
     * @return the corresponding fitness class in the schedule, null otherwise.
     */
    public FitnessClass getFitnessClass(FitnessClass fitnessClass) {
        int index = find(fitnessClass);
        if(index == NOT_FOUND)
            return null;
        return classes[index];
    }

    /**
     * Returns an array of fitness class of the same time of day given.
     * @param time the time of day that is given.
     * @return an array of fitness classes of the time of day given.
     */
    public FitnessClass [] getClassesAtSameTime(Time time) {
        FitnessClass [] classSameTime = new FitnessClass[numClasses];
        int index = 0;
        for(FitnessClass fitnessClass: classes) {
            if (fitnessClass == null)
                break;

            if(fitnessClass.getTime().equals(time))
                classSameTime[index++] = fitnessClass;
        }
        return classSameTime;
    }

    /**
     * Loads fitness class information from a file and adds classes to the schedule.
     * @param file the file that includes the class schedule to be loaded
     * @throws IOException if there is an error reading from the file.
     */
    public void load(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");      // Input Format: "Pilates Jennifer morning Bridgewater"

            if (tokens.length > LOCATION_INDEX) {
                Offer classInfo = Offer.getOffer(tokens[OFFER_INDEX]);
                Instructor instructor = Instructor.getInstructor(tokens[INSTRUCT_INDEX]);
                Time time = Time.getTime(tokens[TIME_INDEX]);
                Location studio = Location.getLocation(tokens[LOCATION_INDEX]);

                if (classInfo != null || instructor != null || studio != null || time != null) {
                    FitnessClass fitnessClass = new FitnessClass(classInfo, instructor, studio, time);
                    addFitnessClass(fitnessClass);
                }
            }
        }
        scanner.close();
    }

    /**
     * Prints schedule of classes in their current positions.
     */
    public void printSchedule() {
        for (int i = 0; i < numClasses; i++) {
            if (classes[i] == null)
                break;

            System.out.print(classes[i]);
        }
    }
}
