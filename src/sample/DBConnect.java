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
                    String city = "   ";



                    NodeList listOfAddresses = doc.getElementsByTagName("address");
                    for(int t=0; t<listOfAddresses.getLength(); t++){
                        Node firstAddressNode = listOfAddresses.item(s);
                        if(firstAddressNode.getNodeType() == Node.ELEMENT_NODE){
                            Element firstAddressElement = (Element)firstAddressNode;
                            NodeList addressList = firstAddressElement.getElementsByTagName("city");
                            Element addressElement = (Element)addressList.item(0);

                            NodeList cityList = addressElement.getChildNodes();
                            city = ((Node)cityList.item(0)).getNodeValue().trim();

                        }

                    }

                    int i = st.executeUpdate("insert into Technicien(Numero, Nom, Prenom, Mot_de_Pass, Numero_Telephone, Adresse_City, Adresse_Cp, Adresse_Street, Adresse_Number, Date_Embauche, Bank_Account) values('"+ID+"','"+city+"','"+ " "+"')");




                }
            }
            System.out.println("Data is successfully inserted!");
        }
        catch (Exception err) {
            System.out.println(" " + err.getMessage());
        }

    }
}
