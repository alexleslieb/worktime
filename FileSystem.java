import java.io.*; 
import java.util.*;
import java.lang.*;

public class FileSystem
{
    public FileSystem()
    {
    }
    
    //Stores data on user (creates user data and job file if new user); Also used to update existing user info
    public boolean storeUserData(User user)
    {   
        File userFile = new File (user.getEmail() + "_data.dat");
        File jobFile = new File (user.getEmail() + "_jobs.dat");
        File reportFile = new File (user.getEmail() + "_reports.dat");
            
        try
        {
            FileWriter fw = new FileWriter (userFile.getName());
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            out.println(user.getFirstName()); 
            out.println(user.getLastName());
            out.println(user.getEmail());
            out.println(user.getPassword());
            out.println(user.getDob());
            out.println(user.getCellNumber());
            out.println(user.getProfilePic());

            out.close();
            
            if(!jobFile.exists())
            {
                jobFile.createNewFile();
            }
            
            if(!reportFile.exists())
            {
                reportFile.createNewFile();
            }
            
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    //Update user password
    public boolean changePassword(User user)
    {
        File userFile = new File (user.getEmail() + "_data.dat");
        
        try
        {
            if(userFile.exists())
            {
                FileWriter fw = new FileWriter (user.getEmail() + "_data.dat");
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter out = new PrintWriter (bw);
                
                out.println(user.getFirstName()); 
                out.println(user.getLastName());
                out.println(user.getEmail());
                out.println(user.getPassword());
                out.println(user.getDob());
                out.println(user.getCellNumber());
                out.println(user.getProfilePic());

                out.close();
                
                return true;
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }
    
    //Delete user job file and data file
    public boolean deleteUser(User user)
    {       
        int found = 0;
        
        try
        {
            File userFile = new File (user.getEmail() + "_data.dat");
            File jobFile = new File (user.getEmail() + "_jobs.dat");
            File reportFile = new File (user.getEmail() + "_reports.dat");
            
            if (userFile.exists())
            {
                userFile.delete();
                found = found + 1;
            }
            if (jobFile.exists())
            {
                jobFile.delete();
                found = found + 1;
            }
            if (reportFile.exists())
            {
                reportFile.delete();
                found = found + 1;
            }
            
            if(found > 0)
            {   
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    //Validates user data entered in form to that of file (if it exists)
    public boolean loginUser(String email, String password)
    {
        File searchFile = new File(email + "_data.dat");
        
        String fname = "", lname = "", mail = "", pass = "", dob = "", cell = "", profilePic = "";
        
        try
        {
            if (searchFile.exists())
            {
                Scanner fileScan = new Scanner(searchFile);
                
                while (fileScan.hasNext())
                {
                    fname = fileScan.nextLine();
                    lname = fileScan.nextLine();
                    mail = fileScan.nextLine();
                    pass = fileScan.nextLine();
                    dob = fileScan.nextLine();
                    cell = fileScan.nextLine();
                    profilePic = fileScan.nextLine();
                }
                
                if (pass.equals(password))
                {
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    //Returns a user object with desired user data from file
    public User getUserData(String email, User user)
    {
        DateOfBirth dateOfBirth;
        Cell cellPhone;
        String fname = "", lname = "", mail = "", pass = "", cellTemp = "", dob = "", profilePic = "";
        
        try
        {
            Scanner fileScan = new Scanner(new File(email + "_data.dat"));
            
            while (fileScan.hasNext())
            {
                fname = fileScan.nextLine();
                lname = fileScan.nextLine();
                mail = fileScan.nextLine();
                pass = fileScan.nextLine();
                dob = fileScan.nextLine();
                cellTemp = fileScan.nextLine();
                profilePic = fileScan.nextLine();
            }
            String cell[] = cellTemp.split("-");
            cellPhone = new Cell(cell[0],cell[1],cell[2]);
            
            String date[] = dob.split("/");
            dateOfBirth = new DateOfBirth(date[0],date[1],date[2]);
            user = new User(fname,lname,mail,pass,dateOfBirth,cellPhone,profilePic);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return user;
    }
    
    //Changes profile pic stored in file
    public void changeProfilePic(User user)
    {
        File userFile = new File (user.getEmail() + "_data.dat");
        
        try
        {
            if(userFile.exists())
            {
                FileWriter fw = new FileWriter (user.getEmail() + "_data.dat");
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter out = new PrintWriter (bw);
                
                out.println(user.getFirstName()); 
                out.println(user.getLastName());
                out.println(user.getEmail());
                out.println(user.getPassword());
                out.println(user.getDob());
                out.println(user.getCellNumber());
                out.println(user.getProfilePic());
    
                out.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    //Retrieves current jobs, appends list of jobs with new job, then rewrites job list to file
    public void storeJobs(String name, String location, double rate, double hours, User user)
    {        
        try
        {
            File jobFile = new File(user.getEmail() + "_jobs.dat");
            
            if(jobFile.createNewFile())
            {
                FileWriter fw = new FileWriter (jobFile.getName());      
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter out = new PrintWriter (bw);
                
                out.println(name);
                out.println(location);
                out.println(rate);
                out.println(hours);
                out.close();  
            }
            else
            {
                FileWriter fw = new FileWriter (jobFile.getName(),true);
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter out = new PrintWriter (bw);
                
                out.println(name);
                out.println(location);
                out.println(rate);
                out.println(hours);
                out.close();  
            }                        
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    //Deletes selected job from file
    public boolean deleteJob(ArrayList<Job> jobs, User user)
    {   
        int i;        
        
        try
        {
            FileWriter fw = new FileWriter (user.getEmail() + "_jobs.dat");
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            for(i = 0; i < jobs.size(); i++)
            {
                out.println(jobs.get(i).getName());
                out.println(jobs.get(i).getLocation());
                out.println(jobs.get(i).getRate());
                out.println(jobs.get(i).getHours());
            }
            
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        
        return true;
    }
    
    //Changes specified data on specified job in file
    public void changeData(String cname, String clocation, String change, String update, User user)
    {        
        Scanner scan = new Scanner(System.in);

        ArrayList<Job> jobs = new ArrayList<Job>();
        
        try
        {
            int i = 0;

            Scanner fileScan = new Scanner(new File(user.getEmail() + "_jobs.dat"));
             
            while (fileScan.hasNext())
            {               
                String name = fileScan.nextLine();
                String location = fileScan.nextLine();
                double rate = Double.parseDouble(fileScan.nextLine());
                double hours = Double.parseDouble(fileScan.nextLine());
                
                Job j = new Job (name, location, rate, hours);
                jobs.add(j);
            }

            for (i = 0; i < jobs.size(); i++)
            {
                if (cname.toLowerCase().equals(jobs.get(i).getName().toLowerCase()) && clocation.toLowerCase().equals(jobs.get(i).getLocation().toLowerCase()))
                {
                    if (change.toLowerCase().equals("name"))
                    {
                        jobs.get(i).setName(update);
                    }
                    else
                    {
                        if (change.toLowerCase().equals("location"))
                        {
                            jobs.get(i).setLocation(update);
                        }
                        else
                        {
                            if (change.toLowerCase().equals("rate"))
                            {
                                jobs.get(i).setRate(Double.parseDouble(update));
                            }
                            else
                            {
                                if (change.toLowerCase().equals("hours"))
                                {
                                    jobs.get(i).setHours(Double.parseDouble(update));
                                }
                                else
                                {
                                    System.out.println("Not a valid option.\n");
                                }
                            }
                        }
                    }
                }
            }
            
            FileWriter fw = new FileWriter (user.getEmail() + "_jobs.dat");
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            for (Job jj: jobs)
            {
                out.println(jj.getName());
                out.println(jj.getLocation());
                out.println(jj.getRate());
                out.println(jj.getHours());
            }
            
            out.close();
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }  
    }
    
    //Returns number greater than 0 if job is found in file, returns 0 if job does not exist
    public int findJob(String job, String loc, User user)
    {
        Scanner scan = new Scanner(System.in);

        int found = 0;
        int i;
        
        ArrayList<Job> jobs = new ArrayList<Job>();

        try
        {
            Scanner fileScan = new Scanner(new File(user.getEmail() + "_jobs.dat"));
             
            while (fileScan.hasNext())
            {               
                String name = fileScan.nextLine();
                String location = fileScan.nextLine();
                double rate = Double.parseDouble(fileScan.nextLine());
                double hours = Double.parseDouble(fileScan.nextLine());
                
                Job j = new Job (name, location, rate, hours);
                jobs.add(j);
            }
            
            for (i = 0; i < jobs.size(); i++)
            {
                if (jobs.get(i).getName().toLowerCase().equals(job.toLowerCase()) && jobs.get(i).getLocation().toLowerCase().equals(loc.toLowerCase()))
                {
                   found++;
                }
            }     
            
            FileWriter fw = new FileWriter (user.getEmail() + "_jobs.dat");
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            for (Job jj: jobs)
            {
                out.println(jj.getName());
                out.println(jj.getLocation());
                out.println(jj.getRate());
                out.println(jj.getHours());
            }
            
            out.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }  

        return found;
    }
    
    //Updates current hours acquired for a job
    public void updateHours(String jobName, String jobLocation, double addHours, User user)
    {
        Scanner scan = new Scanner(System.in);

        ArrayList<Job> jobs = new ArrayList<Job>();
        
        try
        {
            int i = 0;
            
            Scanner fileScan = new Scanner(new File(user.getEmail() + "_jobs.dat"));
            
            while (fileScan.hasNext())
            {
                String name = fileScan.nextLine();
                String location = fileScan.nextLine();
                double rate = Double.parseDouble(fileScan.nextLine());
                double hours = Double.parseDouble(fileScan.nextLine());
                
                Job j = new Job (name, location, rate, hours);
                jobs.add(j);
            }

            for (i = 0; i < jobs.size(); i++)
            {
                if (jobs.get(i).getName().toLowerCase().equals(jobName.toLowerCase()) && jobs.get(i).getLocation().toLowerCase().equals(jobLocation.toLowerCase()))
                {
                    jobs.get(i).setHours((jobs.get(i).getHours() + addHours));
                }                
            }
            
            FileWriter fw = new FileWriter (user.getEmail() + "_jobs.dat");
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            for (Job jj: jobs)
            {
                out.println(jj.getName());
                out.println(jj.getLocation());
                out.println(jj.getRate());
                out.println(jj.getHours());
            }
            
            out.close();         
        }
        catch (Exception e)
        {
            System.out.println(e);
        }  
    }
    
    //Returns details on specified job
    public Job viewJob(String jobName, String jobLocation, User user)
    {
        Scanner scan = new Scanner(System.in);

        ArrayList<Job> jobs = new ArrayList<Job>();
        
        Job j2 = new Job();
        
        try
        {
            int i = 0;
            
            Scanner fileScan = new Scanner(new File(user.getEmail() + "_jobs.dat"));
            
            while (fileScan.hasNext())
            {
                String name = fileScan.nextLine();
                String location = fileScan.nextLine();
                double rate = Double.parseDouble(fileScan.nextLine());
                double hours = Double.parseDouble(fileScan.nextLine());
                
                Job j = new Job (name, location, rate, hours);
                jobs.add(j);
            }

            for (i = 0; i < jobs.size(); i++)
            {
                if (jobs.get(i).getName().toLowerCase().equals(jobName.toLowerCase()) && jobs.get(i).getLocation().toLowerCase().equals(jobLocation.toLowerCase()))
                {
                    j2 = jobs.get(i);
                }
            }

            FileWriter fw = new FileWriter (user.getEmail() + "_jobs.dat");
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            for (Job jj: jobs)
            {
                out.println(jj.getName());
                out.println(jj.getLocation());
                out.println(jj.getRate());
                out.println(jj.getHours());
            }
            
            out.close();            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }  
        return j2;
    }
  
    //Returns array list of jobs beloning to user
     public ArrayList<Job> jobList(User user)
    {
        Scanner scan = new Scanner(System.in);
        
        ArrayList<Job> jobs = new ArrayList<Job>();

        try
        {
            Scanner fileScan = new Scanner(new File(user.getEmail() + "_jobs.dat"));
            
            while (fileScan.hasNext())
            {
                String name = fileScan.nextLine();
                String location = fileScan.nextLine();
                double rate = Double.parseDouble(fileScan.nextLine());
                double hours = Double.parseDouble(fileScan.nextLine());
                
                Job j = new Job (name, location, rate, hours);
                jobs.add(j);
            }

            FileWriter fw = new FileWriter (user.getEmail() + "_jobs.dat");
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
            
            for (Job jj: jobs)
            {
                out.println(jj.getName());
                out.println(jj.getLocation());
                out.println(jj.getRate());
                out.println(jj.getHours());
            }
            
            out.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }  
        
        return jobs;
    }
    
    //Writes a user's report to a file
    public boolean storeReport(User user, Report report)
    {
        try
        {
            File reportFile = new File (user.getEmail() + "_reports.dat");

            if (reportFile.createNewFile())
            {
                FileWriter fw = new FileWriter (reportFile.getName());
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter out = new PrintWriter (bw);

                for (Job job: report.getJobs())
                {
                    out.println(report.getDate());
                    out.println(job.getName());
                    out.println(job.getLocation());
                    out.println(job.getRate());
                    out.println(job.getHours());
                }
    
                out.close();
                
                return true;
            }
            else
            {
                FileWriter fw = new FileWriter (reportFile.getName(), true);
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter out = new PrintWriter (bw);

                for (Job job: report.getJobs())
                {
                    out.println(report.getDate());
                    out.println(job.getName());
                    out.println(job.getLocation());
                    out.println(job.getRate());
                    out.println(job.getHours());
                }
    
                out.close();
                
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    //Collects report data from file
    public ArrayList<Report> readReports(User user)
    {   
        ArrayList<Report> reports = new ArrayList<Report>();
        ArrayList<Job> jobs = new ArrayList<Job>();
        String date = "", tempDate;

        try
        {
            Scanner fileScan = new Scanner(new File(user.getEmail() + "_reports.dat"));
            int i = 0, j = 1;
            
            if(fileScan.hasNext())
            {
                date = fileScan.nextLine();
            }
            
            while (fileScan.hasNext())
            {                       
                String name = fileScan.nextLine();
                String location = fileScan.nextLine();
                double rate = Double.parseDouble(fileScan.nextLine());
                double hours = Double.parseDouble(fileScan.nextLine());

                jobs.add(new Job (name, location, rate, hours));
                
                if(!fileScan.hasNext())
                {
                    reports.add(new Report(new ArrayList<Job>(jobs.subList(i,j)), date));
                }
                else
                {
                    tempDate = fileScan.nextLine();
                    
                    if(!tempDate.equals(date))
                    {
                        reports.add(new Report(new ArrayList<Job>(jobs.subList(i,j)),date));                       
                        date = tempDate;
                        i = j;
                    }
                }
                j++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nThere were issues reading the file.");
        }  
        
        return reports;
    }
    
    //Delete report from file by rewriting file
    public boolean rewriteReports(User user, ArrayList<Report> reports)
    {
        File reportFile = new File (user.getEmail() + "_reports.dat");
        
        try
        {
            boolean bool = reportFile.createNewFile();
            
            FileWriter fw = new FileWriter (reportFile.getName());
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter out = new PrintWriter (bw);
    
            for (Report report: reports)
            {
                for(Job job: report.getJobs())
                {
                    out.println(report.getDate());
                    out.println(job.getName());
                    out.println(job.getLocation());
                    out.println(job.getRate());
                    out.println(job.getHours());
                }
            }
    
            out.close();
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e + "\nError writing to file");
        }
        
        return false;
    }
}