package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ActualUser {

    public void SaveUser(String UserId) throws FileNotFoundException {
        PrintWriter bw = new PrintWriter("src/ActualUser.txt");
        bw.write(UserId);
        bw.close();
    }

    public String GetUser() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("src/ActualUser.txt"));
            String str = reader.nextLine();
            return  str;
    }
}
