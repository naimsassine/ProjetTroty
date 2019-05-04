package sample;

import java.io.*;
import java.sql.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
/*
public class InsertXMLData{
    public static void main(String[] args) {
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetDbb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","03331655");
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("./Ressources/anonyme_users.xml"));
            doc.getDocumentElement().normalize();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfPersons = doc.getElementsByTagName("user");
            for(int s=0; s<listOfPersons.getLength(); s++){
                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstPersonElement = (Element)firstPersonNode;
                    NodeList nameList = firstPersonElement.getElementsByTagName("ID");
                    Element nameElement =(Element)nameList.item(0);

                    NodeList textFNList = nameElement.getChildNodes();
                    String name=((Node)textFNList.item(0)).getNodeValue().trim();

                    NodeList addressList = firstPersonElement.getElementsByTagName("password");
                    Element addressElement =(Element)addressList.item(0);

                    NodeList textLNList = addressElement.getChildNodes();
                    String address= ((Node)textLNList.item(0)).getNodeValue().trim();



                    int i=st.executeUpdate("insert into utilisateur(U_ID,Mot_De_Passe) values('"+name+"','"+address+"')");
                }
            }
            System.out.println("Data is successfully inserted!");
        }catch (Exception err) {
            System.out.println(" " + err.getMessage ());
        }/*
    }
}}*/