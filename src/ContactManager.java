import java.sql.*;
import java.util.Scanner;

public class ContactManager 
{
    public static void main(String[] args)
    {
        System.out.println("Project Title: Contact Manager\t"+"Name: MANAN HAKANI");
        MainPage();
    }
    
    static String cont_num;
    static String cont_name;
    static String cont_email;
    static String num;
    
    static final String jdbc_calss = "org.apache.derby.jdbc.ClientDriver";
    static final String driver = "jdbc:derby://localhost:1527/contacts";
    static Connection con = null;
    public static void MainPage() 
    {

        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~    WELCOME TO CONTACT MANAGER!!!    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose your action:");
        System.out.println("1 : View Contacts");
        System.out.println("2 : Add Contact");
        System.out.println("3 : Update Contact");
        System.out.println("4 : Delete Contact");
        System.out.println("0 : Exit");
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\n"+"Enter you choice:\n");
        int num = sc.nextInt();
        
        switch(num)
        {
            case 0:
                System.out.println("Thank you for using Contact Mananger!!");
                break;
                
            case 1:
                View();
                break;
                
            case 2:
                Add();
                break;
                
            case 3:
                update();
                break;

            case 4:
                delete();
                break;
        } 
    }
    
    // Method to view contacts
    public static void View()
    {
        try
        {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/contacts");
        System.out.println("Following are the Contact Details:");
        Statement st = con.createStatement();
                
        String str = "select * from ContactInfo";                
        ResultSet rs = st.executeQuery(str);
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println(" Printing All Contacts: \n");
 
        System.out.println(rsmd.getColumnName(1)+ "\t\t" + rsmd.getColumnName(2) + "\t\t\t"+ rsmd.getColumnName(3));
        System.out.println("------------------------------------------------------------------------");
    
        while(rs.next())
        {
            System.out.println(rs.getString("NAME") + "\t\t"+ rs.getString("NUMBER") + "\t\t"+ rs.getString("EMAIL"));
        }
        System.out.println("\n");
        con.close();
        MainPage();
                
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            MainPage(); //call main page
        }     
    }
    
    // Method to add contacts
    public static void Add()   
    {
        try
        {
        // making string constants null to store data.
        String cont_num = null; 
        String cont_name = null; 
        String cont_email = null;
        String num = null;
                
        Scanner sc1 = new Scanner(System.in);
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/contacts");
        Statement st = con.createStatement();
        
        System.out.println("TO ADD CONTACT:");
        System.out.println("-------------------------------");
        System.out.println("Enter Name:");
        cont_name = sc1.next();
                
        System.out.println("Enter Contact Number:");
        cont_num = sc1.next();

        System.out.println("Enter Email id:");
        cont_email = sc1.next();
                
        String str1 = "insert into contactinfo values('"+cont_name+"','"+cont_num+"','"+cont_email+"')";
        // TO get input in string into database the format to get data is : ' " + (name) + " '.
        st.executeUpdate(str1);
        System.out.println("\n"+"~~~~~~~~~~~~~~~~~~~~~    Record is inserted successfully !!!    ~~~~~~~~~~~~~~~~");
        con.close();
        MainPage();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            MainPage();
        }        
    }
    
    // Method to update contacts
    public static void update()
    {
        try
        {
        // making string constants null to store data.
        String cont_num = null; 
        String cont_name = null; 
        String cont_email = null;
        String num = null;
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/contacts");
        Statement st = con.createStatement();
               
        System.out.println("TO UPDATE RECORD:");
        System.out.println("-------------------------------");
        Scanner sc1 = new Scanner(System.in);
        
        try
        {
            System.out.println("Enter your number to veiw your contact:");
            num = sc1.next();

            String strupd = "select * from CONTACTINFO where NUMBER ='"+num+"'";
            st.execute(strupd);
            ResultSet rs = st.executeQuery(strupd);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("\nPrinting your contact: \n");
            System.out.println(rsmd.getColumnName(1)+ "\t\t" + rsmd.getColumnName(2) + "\t\t\t"+ rsmd.getColumnName(3));
            System.out.println("------------------------------------------------------------------------");
    
            while(rs.next())
            {
                System.out.println(rs.getString("NAME") + "\t\t"+ rs.getString("NUMBER") + "\t\t"+ rs.getString("EMAIL"));
            }
            System.out.println("\n");
        }
            
        catch(Exception e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            MainPage();
        }
        
        System.out.println("Enter your new details:");
        
        System.out.println("Enter Name:");
        cont_name = sc1.next();
                
        System.out.println("Enter Contact Number:");
        cont_num = sc1.next();

        System.out.println("Enter Email id:");
        cont_email = sc1.next();
                
        String str1 = "UPDATE contactinfo set NAME='"+cont_name+"', NUMBER='"+cont_num+"',EMAIL='"+cont_email+"' WHERE NUMBER='"+num+"' ";
        // TO get input in string into database the format to get data is : ' " + (name) + " '.
        st.executeUpdate(str1);
        System.out.println("\n"+"~~~~~~~~~~~~~~~~~~~~~    Record Updated successfully !!!    ~~~~~~~~~~~~~~~~");
        con.close();
        MainPage();
        }

        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            MainPage();
        }    
    }
    
    // Method to update contacts
    public static void delete()
    {
        try
        {
        // making string constants null to store data.
        String cont_num = null;
                
        Scanner sc1 = new Scanner(System.in);
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/contacts");
        Statement st = con.createStatement();
        
        System.out.println("To DELETE CONTACT:");
        System.out.println("-------------------------------");
        System.out.println("\nEnter your number to Delete your contact:");
        cont_num = sc1.next();
                
        String str1 = "delete from contactinfo where NUMBER='"+cont_num+"'";
        // TO get input in string into database the format to get data is : ' " + (name) + " '.
        st.executeUpdate(str1);
        System.out.println("\n"+"~~~~~~~~~~~~~~~~~~~~~    Record Deleted successfully !!!    ~~~~~~~~~~~~~~~~");
        con.commit();
        con.close();
        MainPage();
        }
        
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println("Invalid input!, Please try again!!!");
            MainPage();
        }    
    }
}