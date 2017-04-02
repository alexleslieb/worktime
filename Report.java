import java.util.*;
import java.text.*;

public class Report
{   
    private String date;
    private DateFormat df = new SimpleDateFormat("E, dd/MM/yy @ HH:mm:ss");
    private ArrayList <Job> jobs;
    
    public Report()
    {
    }
    
    public Report(ArrayList<Job> jobs)
    {
        //this.user = user;
        this.jobs = jobs;
        date = df.format(new Date());
    }

    public Report(ArrayList<Job> jobs, String date)
    {
        //this.user = user;
        this.jobs = jobs;
        this.date = date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public void setJobs(ArrayList<Job> jobs)
    {
        this.jobs = jobs;
    }

    public String getDate()
    {
        return date;
    }
    
    public ArrayList<Job> getJobs()
    {
        return jobs;
    }
    
    public String toString()
    {
        String desc = "Created on: " + this.date + "\n";

        for(Job tmpJob:jobs)
        {
            desc += "\n" + tmpJob.toString();
        }
        
        return desc;
        //return date + "\n" + jobs;
    }
}