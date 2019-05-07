package sample;
import com.opencsv.CSVReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class DBConnect {

    private Connection con;
    private static Connection conn;
    private Statement st;
    private ResultSet rs;


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

    public static String checkDateCorrect(String s){
        String t = "2017-03-26T03";
        if (s.contains("2017-03-26T02"))
        {
            t = s.replace("2017-03-26T02","2017-03-26T03"); }
        else {
            t = s;
        }
        return t;
    }

    public DBConnect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetDbb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","root","03331655");
            st = con.createStatement();

        }
        catch (Exception e){
            System.out.print("Error " + e.getMessage());}

    }

    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetDbb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","root","03331655");
        return conn;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }


    public void getData(){
        try{

            String query = "Select * from Utilisateur WHERE Utilisateur.U_ID=1";
            rs = st.executeQuery(query);
            System.out.print("Les Utilisateurs obtenus sont: ");
            while(rs.next()){
                String name = rs.getString("Numero_carte");
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
            Document doc = docBuilder.parse (new File("Ressources/anonyme_users.xml"));
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
            Document doc = docBuilder.parse (new File("src/Ressources/mecaniciens.xml"));
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
            Document doc = docBuilder.parse (new File("Ressources/registeredUsers.xml"));
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

    public void insertDataReloads(){
        try (CSVReader reader = new CSVReader(new FileReader("Ressources/reloads.csv"), ','))
        {
            String insertQuery = "Insert into Recharge values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            String[] rowData = null;
            int i = 0;
            int iteration = 0;
            while(((rowData = reader.readNext())) != null){
                if(iteration == 0) {
                    iteration++;
                    continue;
                }

                for (String data : rowData)
                {

                    pstmt.setString((i % 10) + 1, data);

                    if (++i % 10 == 0)
                        pstmt.addBatch();// add batch

                    if (i % 10 == 0)// insert when the batch size is 10
                        pstmt.executeBatch();
                }}
            System.out.println("Data Successfully Uploaded");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    } // Check en principe


    // fonctionne pas a caause d une couille dans la bdd
    public void insertDataReparation(){
        try (CSVReader reader = new CSVReader(new FileReader("src/Ressources/reparations.csv"), ','))
        {
            String insertQuery = "Insert into Reparation values (?,?,?,?,?,null) ";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            String[] rowData = null;
            int i = 0;
            int iteration = 0;
            while(((rowData = reader.readNext())) != null){
                if(iteration == 0) {
                    iteration++;
                    continue;
                }

                for (String data : rowData)
                {
                    System.out.print(data + "LOOl");
                    pstmt.setString((i % 5) + 1, data);

                    if (++i % 5 == 0)
                        pstmt.addBatch();// add batch

                    if (i % 5 == 0)// insert when the batch size is 10
                        pstmt.executeBatch();
                }}
            System.out.println("Data Successfully Uploaded");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    } // Check en principe

    public void insertDataScooters(){
        try (CSVReader reader = new CSVReader(new FileReader("Ressources/scooters.csv"), ';'))
        {
            String insertQuery = "Insert into Trotinette values (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            String[] rowData = null;
            int i = 0;
            int iteration = 0;
            while(((rowData = reader.readNext())) != null){
                if(iteration == 0) {
                    iteration++;
                    continue;
                }

                for (String data : rowData)
                {
                    pstmt.setString((i % 5) + 1, data);

                    if (++i % 5 == 0)
                        pstmt.addBatch();// add batch

                    if (i % 5 == 0)// insert when the batch size is 10
                        pstmt.executeBatch();
                }}
            System.out.println("Data Successfully Uploaded");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    } // Check en principe

    public void insertDataTrips(){
        try (CSVReader reader = new CSVReader(new FileReader("Ressources/trips.csv"), ','))
        {
            String insertQuery = "Insert into Voyage values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            String[] rowData = null;
            int i = 0;
            int iteration = 0;
            while(((rowData = reader.readNext())) != null){
                if(iteration == 0) {
                    iteration++;
                    continue;
                }

                for (String data : rowData)
                {
                    pstmt.setString((i % 8) + 1, data);

                    if (++i % 8 == 0)
                        pstmt.addBatch();// add batch

                    if (i % 8 == 0)// insert when the batch size is 10
                        pstmt.executeBatch();
                }}
            System.out.println("Data Successfully Uploaded");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    } // Check en principe


    public String Login(String U_ID) throws SQLException {
        String selectSQL = "SELECT Mot_De_Passe FROM Utilisateur WHERE U_ID = ?";
        String password = "No password found";


        PreparedStatement pstmt = con.prepareStatement(selectSQL);
        pstmt.setString(1, U_ID);

        ResultSet rs = pstmt.executeQuery();

        try {
            while (rs.next()) {
                password = rs.getString("Mot_De_Passe");
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            pstmt.close();
            con.close();
        }

        return password;
    }

    public Boolean signupAnonymeUser(String ID, String Password, String CC){
        try
        {
            // the mysql insert statement
            String query = " insert into Utilisateur"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, ID);
            preparedStmt.setString (2,Password);
            preparedStmt.setString (3,CC);

            // execute the preparedstatement
            preparedStmt.execute();

            con.close();
            return  true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public Boolean signupChargerUser(String ID, String FN, String LN, String TN, String City, String PC,
                                     String Street, String Number){

        try
        {
            // the mysql insert statement
            String query = " insert into Utilisateur_Recharge"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, ID);
            preparedStmt.setString (2,FN);
            preparedStmt.setString (3,LN);
            preparedStmt.setString (4,TN);
            preparedStmt.setString (5,City);
            preparedStmt.setString (6,PC);
            preparedStmt.setString (7,Street);
            preparedStmt.setString (8,Number);



            // execute the preparedstatement
            preparedStmt.execute();

            con.close();
            return  true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }


    }
    public Boolean insertComplaint(String T_ID) throws SQLException {
        // Test si la trotinette existe
        String selectSQL = "SELECT T_ID FROM Trotinette WHERE T_ID = ?";
        String trottfound = "No Scoots found";


        PreparedStatement pstmt = con.prepareStatement(selectSQL);
        pstmt.setString(1, T_ID);

        ResultSet rs = pstmt.executeQuery();

        try {
            while (rs.next()) {
                trottfound = rs.getString("T_ID");
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        // SI la trott existe il va faire un update

        if (T_ID.equals(trottfound)){
            try
            {
                // the mysql insert statement
                String query = " UPDATE Trotinette SET État = 'True'  WHERE T_ID = ?";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, T_ID);

                // execute the preparedstatement
                preparedStmt.execute();

                con.close();
                return  true;
            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
                return false;
            }
        }
        else {
            return false;
        }

    }

    public Boolean checkTechnicien(String MID) throws SQLException {
        String selectSQL = "SELECT Numero FROM Technicien WHERE Numero = ?";
        String mechfound = "No Scoots found";


        PreparedStatement pstmt = con.prepareStatement(selectSQL);
        pstmt.setString(1, MID);

        ResultSet rs = pstmt.executeQuery();

        try {
            while (rs.next()) {
                mechfound = rs.getString("Numero");
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        // SI la trott existe il va faire un update

        if (MID.equals(mechfound)){
                return  true;
        }
        else {
            return false;
        }
    }

    public Boolean removeTrotinette(String TID) {
        try
        {
            String query = "Delete from Reparation Where T_ID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, TID);
            preparedStmt.execute();


            String query1 = "Delete from Recharge Where T_ID = ?";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setString (1, TID);
            preparedStmt1.execute();

            String query2 = "Delete from Voyage Where T_ID = ?";
            PreparedStatement preparedStmt2 = con.prepareStatement(query2);
            preparedStmt2.setString (1, TID);
            preparedStmt2.execute();

            String query3 = "Delete from Trotinette Where T_ID = ?";
            PreparedStatement preparedStmt3 = con.prepareStatement(query3);
            preparedStmt3.setString (1, TID);
            preparedStmt3.execute();


            con.close();
            return  true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public Boolean addTrotinette(String TID, String Modele, String Date, String Battery, String Complaint,
                                 String Dispo, String posX, String posY){

        try
        {
            // the mysql insert statement
            String query = " insert into Trotinette"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1,TID);
            preparedStmt.setString (2,Date);
            preparedStmt.setString (3,Modele);
            preparedStmt.setString (4,Complaint);
            preparedStmt.setString (5,Battery);



            // execute the preparedstatement
            preparedStmt.execute();

            con.close();
            return  true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public Boolean modifyTrotinette(String TID, String VaraModifier, String NouvelleVar){
        try
        {
            // the mysql insert statement
            String query = "UPDATE Trotinette SET ? = ? WHERE Trotinette.T_ID=?;";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1,VaraModifier);
            preparedStmt.setString (2,NouvelleVar);
            preparedStmt.setString (3,TID);

            // execute the preparedstatement
            preparedStmt.execute();

            con.close();
            return  true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }
}



