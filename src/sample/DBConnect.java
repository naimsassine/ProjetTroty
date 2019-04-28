package sample;
import java.io.*;
import java.sql.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.sql.*;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetDbb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","03331655");
            st = con.createStatement();

        }
        catch (Exception e){
            System.out.print("Error " + e.getMessage());}

    }


    public void getData(){
        try{

            String query = "Select * from Trotinette";
            rs = st.executeQuery(query);
            System.out.print("Trotinette from Trotinette");
            while(rs.next()){
                String name = rs.getString("Model");
                System.out.print(name);
                }

            }

        catch(Exception e){
            System.out.print("Error " + e.getMessage());
            }
        }




    public void insertDataUtilisateur() {
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("./data2019/anonyme_users.xml"));
            doc.getDocumentElement().normalize();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfUsers = doc.getElementsByTagName("user");
            for(int s=0; s<listOfUsers.getLength(); s++){
                Node firstUserNode = listOfUsers.item(s);
                if(firstUserNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstUserElement = (Element)firstUserNode;
                    NodeList nameList = firstUserElement.getElementsByTagName("ID");
                    Element nameElement = (Element)nameList.item(0);

                    NodeList IDList = nameElement.getChildNodes();
                    String ID = ((Node)IDList.item(0)).getNodeValue().trim();

                    NodeList PasswordList = firstUserElement.getElementsByTagName("password");
                    Element PasswordElement = (Element)PasswordList.item(0);

                    NodeList textLNList = PasswordElement.getChildNodes();
                    String password = ((Node)textLNList.item(0)).getNodeValue().trim();

                    NodeList CreditCartList = firstUserElement.getElementsByTagName("password");
                    Element CreditCardElement = (Element)CreditCartList.item(0);

                    NodeList CCList = CreditCardElement.getChildNodes();
                    String CC_number = ((Node)CCList.item(0)).getNodeValue().trim();


                    int i = st.executeUpdate("insert into utilisateur(U_ID,Mot_De_Passe,Numero_Carte) values('"+ID+"','"+password+"','"+CC_number+"')");
                }}
            System.out.println("Data is successfully inserted!");
        }
        catch (Exception err) {
            System.out.println(" " + err.getMessage());
        }

    }




    public void insertDataMecaniciens() {
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("./data2019/mecaniciens.xml"));
            doc.getDocumentElement().normalize();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfMecanics = doc.getElementsByTagName("mechanic");
            for(int s=0; s<listOfMecanics.getLength(); s++){
                Node firstMecanicNode = listOfMecanics.item(s);
                if(firstMecanicNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstMecanicElement = (Element)firstMecanicNode;
                    NodeList nameList = firstMecanicElement.getElementsByTagName("mechanicID");
                    Element nameElement = (Element)nameList.item(0);

                    NodeList IDList = nameElement.getChildNodes();
                    String ID = ((Node)IDList.item(0)).getNodeValue().trim();


                    NodeList nomList = firstMecanicElement.getElementsByTagName("lastname");
                    Element nomElement = (Element)nomList.item(0);

                    NodeList namesList = nomElement.getChildNodes();
                    String name = ((Node)namesList.item(0)).getNodeValue().trim();



                    NodeList prenomList = firstMecanicElement.getElementsByTagName("firstname");
                    Element prenomElement = (Element)prenomList.item(0);

                    NodeList prenomsList = prenomElement.getChildNodes();
                    String prenom = ((Node)prenomsList.item(0)).getNodeValue().trim();

                    NodeList PasswordList = firstMecanicElement.getElementsByTagName("password");
                    Element PasswordElement = (Element)PasswordList.item(0);

                    NodeList textLNList = PasswordElement.getChildNodes();
                    String password = ((Node)textLNList.item(0)).getNodeValue().trim();

                    NodeList PhoneList = firstMecanicElement.getElementsByTagName("phone");
                    Element PhoneElement = (Element)PhoneList.item(0);

                    NodeList PhonesList = PhoneElement.getChildNodes();
                    String phone = ((Node)PhonesList.item(0)).getNodeValue().trim();



                    NodeList addressList = firstMecanicElement.getElementsByTagName("city");
                    Element addressElement = (Element)addressList.item(0);

                    NodeList cityList = addressElement.getChildNodes();
                    String city = ((Node)cityList.item(0)).getNodeValue().trim();


                    NodeList addresscpList = firstMecanicElement.getElementsByTagName("cp");
                    Element addresscpElement = (Element)addresscpList.item(0);

                    NodeList cpList = addresscpElement.getChildNodes();
                    String cp = ((Node)cpList.item(0)).getNodeValue().trim();


                    NodeList adresseStreetList = firstMecanicElement.getElementsByTagName("street");
                    Element streetElement = (Element)adresseStreetList.item(0);

                    NodeList streetList = streetElement.getChildNodes();
                    String street = ((Node)streetList.item(0)).getNodeValue().trim();


                    NodeList addressNumberList = firstMecanicElement.getElementsByTagName("number");
                    Element addressNumberElement = (Element)addressNumberList.item(0);

                    NodeList numberList = addressNumberElement.getChildNodes();
                    String number = ((Node)numberList.item(0)).getNodeValue().trim();



                    NodeList hireDateList = firstMecanicElement.getElementsByTagName("hireDate");
                    Element hireDateElement = (Element)hireDateList.item(0);

                    NodeList HDList = hireDateElement.getChildNodes();
                    String HireDate = ((Node)HDList.item(0)).getNodeValue().trim();



                    NodeList bankAccountList = firstMecanicElement.getElementsByTagName("bankaccount");
                    Element BAElement = (Element)bankAccountList.item(0);

                    NodeList BAList = BAElement.getChildNodes();
                    String bankAccount = ((Node)BAList.item(0)).getNodeValue().trim();

                    int i = st.executeUpdate("insert into Technicien values('"+ID+"','"+name+"','"+prenom+"','"+password+"','"+phone+"','"+city+"','"+cp+"','"+street+"','"+number+"','"+HireDate+"','"+bankAccount+"')");

                }
            }
            System.out.println("Data is successfully inserted!");
        }
        catch (Exception err) {
            System.out.println(" " + err.getMessage());
        }

    }




    public void insertDataUtilisateurRecharge(){

        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("./data2019/registeredUsers.xml"));
            doc.getDocumentElement().normalize();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfUsers = doc.getElementsByTagName("user");
            for(int s=0; s<listOfUsers.getLength(); s++){
                Node firstUserNode = listOfUsers.item(s);
                if(firstUserNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstUserElement = (Element)firstUserNode;
                    NodeList nameList = firstUserElement.getElementsByTagName("ID");
                    Element nameElement = (Element)nameList.item(0);

                    NodeList IDList = nameElement.getChildNodes();
                    String ID = ((Node)IDList.item(0)).getNodeValue().trim();


                    NodeList nomList = firstUserElement.getElementsByTagName("lastname");
                    Element nomElement = (Element)nomList.item(0);

                    NodeList namesList = nomElement.getChildNodes();
                    String name = ((Node)namesList.item(0)).getNodeValue().trim();

                    name = checkApostrophe(name);



                    NodeList prenomList = firstUserElement.getElementsByTagName("firstname");
                    Element prenomElement = (Element)prenomList.item(0);

                    NodeList prenomsList = prenomElement.getChildNodes();
                    String prenom = ((Node)prenomsList.item(0)).getNodeValue().trim();

                    prenom = checkApostrophe(prenom);

                    NodeList PasswordList = firstUserElement.getElementsByTagName("password");
                    Element PasswordElement = (Element)PasswordList.item(0);

                    NodeList textLNList = PasswordElement.getChildNodes();
                    String password = ((Node)textLNList.item(0)).getNodeValue().trim();

                    password = checkApostrophe(password);

                    NodeList PhoneList = firstUserElement.getElementsByTagName("phone");
                    Element PhoneElement = (Element)PhoneList.item(0);

                    NodeList PhonesList = PhoneElement.getChildNodes();
                    String phone = ((Node)PhonesList.item(0)).getNodeValue().trim();

                    phone = checkApostrophe(phone);



                    NodeList addressList = firstUserElement.getElementsByTagName("city");
                    Element addressElement = (Element)addressList.item(0);

                    NodeList cityList = addressElement.getChildNodes();
                    String city = ((Node)cityList.item(0)).getNodeValue().trim();

                    city = checkApostrophe(city);


                    NodeList addresscpList = firstUserElement.getElementsByTagName("cp");
                    Element addresscpElement = (Element)addresscpList.item(0);

                    NodeList cpList = addresscpElement.getChildNodes();
                    String cp = ((Node)cpList.item(0)).getNodeValue().trim();


                    NodeList adresseStreetList = firstUserElement.getElementsByTagName("street");
                    Element streetElement = (Element)adresseStreetList.item(0);

                    NodeList streetList = streetElement.getChildNodes();
                    String street = ((Node)streetList.item(0)).getNodeValue().trim();

                    street = checkApostrophe(street);


                    NodeList addressNumberList = firstUserElement.getElementsByTagName("number");
                    Element addressNumberElement = (Element)addressNumberList.item(0);

                    NodeList numberList = addressNumberElement.getChildNodes();
                    String number = ((Node)numberList.item(0)).getNodeValue().trim();


                    NodeList bankAccountList = firstUserElement.getElementsByTagName("bankaccount");
                    Element BAElement = (Element)bankAccountList.item(0);

                    NodeList BAList = BAElement.getChildNodes();
                    String bankAccount = ((Node)BAList.item(0)).getNodeValue().trim();

                    int i = st.executeUpdate("insert into Utilisateur values('"+ID+"','"+password+"','"+bankAccount+"')");


                    int j = st.executeUpdate("insert into Utilisateur_Recharge values('"+ID+"','"+name+"','"+prenom+"','"+phone+"','"+city+"','"+cp+"','"+street+"','"+number+"')");

                }
            }
            System.out.println("Data is successfully inserted!");
        }
        catch (Exception err) {
            System.out.println(" " + err.getMessage());
        }

    }




    public static String checkApostrophe(String s){
        String t = "";
        if (s.contains("'"))
    {
             t = s.replace("'","''"); }
        else {
             t = s;
        }
        return t;

    }

}


