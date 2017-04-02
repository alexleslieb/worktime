import java.io.*; 
import java.util.*;
import java.lang.*;
import java.time.LocalDateTime;

public class TextUIDriver2
{
    public static void main(String[] arg)
    {
        FileSystem work = new FileSystem();
        /*
        FileSystem work = new FileSystem();
        Scanner scan = new Scanner(System.in);
        User user;
        
        String fname, lname, email, password;
        int cell, day, month, year;
        Date date;
        /*
        System.out.print("Enter first name: ");
        fname = scan.nextLine();
        System.out.print("Enter last name: ");
        lname = scan.nextLine();
        System.out.print("Enter email address: ");
        email = scan.nextLine();
        System.out.print("Enter password: ");
        password = scan.nextLine();
        System.out.println("Enter date of birth: ");
        System.out.print("Date: ");
        day = scan.nextInt();
        System.out.print("Month: ");
        month = scan.nextInt();
        System.out.print("Year: ");
        year = scan.nextInt();
        System.out.print("Enter cell number: ");
        cell = scan.nextInt();
        
        date = new Date(day,month,year);
        user = new User(fname,lname,email,password,date,cell);
        
        work.storeUserData(user);
        
        System.out.println("New user created: " + user.getFirstName() + " " + user.getLastName());
        
        Scanner scan2 = new Scanner(System.in);
        
        System.out.println("Which user will you delete?");
        System.out.print("First name: ");
        fname = scan2.nextLine();
        System.out.print("Last name: ");
        lname = scan2.nextLine();
        
        if (fname.equals(user.getFirstName()) && lname.equals(user.getLastName()))
        {
            work.deleteUser(user);
            System.out.println("User deleted");
        }
        else
        {
            System.out.println("User does not exist");
        }*/
        /*
        Scanner scan3 = new Scanner(System.in);
        
        System.out.print("Email of user login: ");
        email = scan3.nextLine();
        System.out.print("Password: ");
        password = scan3.nextLine();
        
        if(work.loginUser(email,password))
        {
            System.out.println("Login successful");
        }
        else
        {
            System.out.println("Login failed");
        }
        
        String str = " 3 0 ";
        
        System.out.println(Integer.parseInt(str.replaceAll("\\s+","")));
        System.out.println(str);*/
        
        /*
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getMonthValue());
        System.out.println(now.getYear());*/
        
        //String str = "Thirteen!3";
        //System.out.println(str.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,10}"));
        
        ArrayList <Report> reports = new ArrayList<Report>();
        
        reports = work.readReports(new User());
        
        System.out.println(reports);
    }
}
