package sample;

import java.io.*;
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
    public void SaveTech(String Numero) throws FileNotFoundException {
        PrintWriter bw = new PrintWriter("src/Tech.txt");
        bw.write(Numero);
        bw.close();
    }
    public String GetTech() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("src/Tech.txt"));
        String str = reader.nextLine();
        return  str;
    }


}
