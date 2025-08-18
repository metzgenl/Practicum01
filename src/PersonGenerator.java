import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args){
        //Declare variables
        Scanner in = new Scanner(System.in);
        ArrayList<String> myArrList = new ArrayList<>();
        int counter = 0;
        String id = "000000";
        String firstName = "";
        String lastName = "";
        String title= "";
        int yearOfBirth;
        Boolean done = false;

        do{
            //Prompt for information (id, first and last name, title, and year of birth)
            counter++;
            id = String.format("%06d", counter);
            firstName = SafeInput.getNonZeroLenString(in, "Enter in the first name of the person");
            lastName = SafeInput.getNonZeroLenString(in, "Enter in the last name of the person");
            title = SafeInput.getNonZeroLenString(in, "Enter in the title of the person");
            yearOfBirth = SafeInput.getInt(in, "Enter the year of birth of the person");

            //Add in information for specific user
            myArrList.add(id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth);

            //Check if all people were added
            done = SafeInput.getYNConfirm(in, "Are you done adding people?");

        }while(!done);
        //Save information (from Lab13 of Computer Programming I)
        saveFile(myArrList);

    }
    //Save File function
    private static void saveFile(ArrayList<String> myArrList) {
        Scanner scanner = new Scanner(System.in);
        //Ask user for filename if this is a new array and not an opened one
        // Write to file, code from video
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", "PersonTestData.txt"); //Put in user file name here
        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for (String rec : myArrList) {
                writer.write(rec, 0, rec.length());

                writer.newLine();
            }

            writer.close();
            System.out.println("Data saved successfully to PersonTestData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
