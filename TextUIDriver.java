import java.io.*; 
import java.util.*;
import java.lang.*;

public class TextUIDriver
{/*
    public static void main (String arg[])
    {
        FileSystem work = new FileSystem();
        
        int i;
        
        ArrayList <Job> jList;

        Scanner oScan = new Scanner(System.in);
        
        String op;
        double option = 10;
        
        String name;
        String location;
        String loc;
        double rate;
        double hours;

        while (option != 0)
        {
            System.out.println("___________________________________________________________________________________________________________________________________");
            System.out.println("\t\tSelect an option:\n[1]Add Job\n[2]Edit Job Data\n[3]Add Job Hours\n[4]View Job Details\n[5]Delete Job\n[6]Job List\n[0]Quit");
            op = oScan.nextLine();
            option = Double.parseDouble(op);

            if (option == 1)
            {
                Scanner jScan = new Scanner(System.in);
                System.out.print("\nEnter job name: ");
                name = jScan.nextLine();
                System.out.print("Enter job location: ");
                location = jScan.nextLine();
                System.out.print("Enter job rate: ");
                rate = jScan.nextDouble();
                System.out.print("Enter job hours: ");
                hours = jScan.nextDouble();

                boolean stored = work.storeJobs(name, location, rate, hours);
                System.out.println("\nJob successfully added.");
            }
            else
            {
                if (option == 2)
                {
                    Scanner jScan = new Scanner(System.in);
                    System.out.println("\nWhich job will you update?");
                    jList = work.jobList();
                    for (i = 0; i < jList.size();i++)
                    {
                        System.out.println(jList.get(i).getName());
                    }
                    
                    System.out.print("\nName: ");
                    name = jScan.nextLine();
                    System.out.print("Location: ");
                    loc = jScan.nextLine();
                    
                    if (work.findJob(name,loc) > 0)
                    {
                        System.out.println("\nWhat will you change?\nName\nLocation\nRate\nHours");
                        String change = jScan.nextLine();
                        if (change.toLowerCase().equals("name") || change.toLowerCase().equals("location") || change.toLowerCase().equals("rate") || change.toLowerCase().equals("hours"))
                        {
                            System.out.println("\nWhat will you change the " + change + " to?");
                            String update = jScan.nextLine();
                            work.changeData(name, loc, change, update);
                            System.out.println("\nUpdate successful.\n");
                        }
                        else
                        {
                            System.out.println("\nNot a valid option.\n");
                        }
                    }
                    else
                    {
                        System.out.println("\nJob not in file.\n");
                    }
                }
                else
                {
                    if (option == 3)
                    {
                        Scanner jScan = new Scanner(System.in);
                        System.out.println("\nWhich job will you update?");
                        jList = work.jobList();
                        for (i = 0; i < jList.size();i++)
                        {
                            System.out.println(jList.get(i).getName());
                        }
                        
                        System.out.print("\nName: ");
                        name = jScan.nextLine();
                        System.out.print("Location: ");
                        loc = jScan.nextLine();
                        
                        if (work.findJob(name, loc) > 0)
                        {
                            System.out.println("\nHow many hours will you add?");
                            double newHours = jScan.nextDouble();
                            work.updateHours(name,loc,newHours);
                            System.out.println("\nHours successfully added.\n");
                        }
                        else
                        {
                            System.out.println("\nJob not in file.\n");
                        }
                    }
                    else
                    {
                        if (option == 4)
                        {
                            Scanner jScan2 = new Scanner(System.in);
                            System.out.println("\nWhich job will you view?");
                            jList = work.jobList();
                            for (i = 0; i < jList.size();i++)
                            {
                                System.out.println(jList.get(i).getName());
                            }
                            
                            System.out.print("\nName: ");
                            name = jScan2.nextLine();
                            System.out.print("Location: ");
                            loc = jScan2.nextLine();
                            
                            System.out.println("");
                            String details = work.viewJob(name,loc).toString();
                            if (details.length() != 0)
                            {
                                System.out.println(details);
                            }
                            else
                            {
                                System.out.println("\nJob not found.");
                            }
                        }
                        else
                        {
                            if (option == 5)
                            {
                                Scanner jScan = new Scanner(System.in);
                                System.out.println("\nWhich job would you like to delete?");
                                jList = work.jobList();
                                for (i = 0; i < jList.size();i++)
                                {
                                    System.out.println(jList.get(i).getName());
                                }
                                
                                System.out.print("\nName: ");
                                name = jScan.nextLine();
                                System.out.print("Location: ");
                                loc = jScan.nextLine();
                                
                                int k = work.deleteJob(name,loc);
                                
                                
                                if (k > 0)
                                {
                                    System.out.println("\nJob successfully deleted.\n");
                                }
                                else
                                {
                                    System.out.println("\nJob not in file.\n");
                                }
                            }
                            else
                            {
                                if (option == 6)
                                {
                                    jList = work.jobList();
                                    System.out.println("\nJob List:");
                                    for (i = 0; i < jList.size();i++)
                                    {
                                        System.out.println(jList.get(i).getName());
                                    }
                                }
                                else
                                {
                                    if (option != 0)
                                    {
                                        System.out.println("Not a valid option.\n");
                                    }
                                }
                            }
                        }
                    }
                }
            } 
        }
        System.out.println("\nYou may now close the program...");
    }*/
}