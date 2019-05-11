package sample;

import java.io.*;
import java.util.Scanner;

public class ActualUser {

    public void saveUser(String UserId) throws FileNotFoundException {
        PrintWriter bw = new PrintWriter("src/ActualUser.txt");
        bw.write(UserId);
        bw.close();
    }

    public String getUser() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("src/ActualUser.txt"));
            String str = reader.nextLine();
            return  str;
    }
    public void saveTech(String Numero) throws FileNotFoundException {
        PrintWriter bw = new PrintWriter("src/Tech.txt");
        bw.write(Numero);
        bw.close();
    }
    public String getTech() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("src/Tech.txt"));
        String str = reader.nextLine();
        return  str;
    }


}
