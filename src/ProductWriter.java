import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args){
        //Declare variables
        Scanner in = new Scanner(System.in);
        ArrayList<String> myArrList = new ArrayList<>();
        int counter = 0;
        String id = "000000";
        String name = "";
        String description= "";
        double cost;
        Boolean done = false;

        do{
            //Prompt for information (id, first and last name, title, and year of birth)
            counter++;
            id = String.format("%06d", counter);
            name = SafeInput.getNonZeroLenString(in, "Enter in the name of the product");
            description = SafeInput.getNonZeroLenString(in, "Enter in the description of the product");
            cost = SafeInput.getDouble(in, "Enter in the cost of the item");

            //Add in information for specific user
            myArrList.add(id + ", " + name + ", " + description + ", " + cost);

            //Check if all people were added
            done = SafeInput.getYNConfirm(in, "Are you done adding products?");

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
        Path file = Paths.get(workingDirectory.getPath(), "src", "ProductTestData.txt"); //Put in user file name here
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
