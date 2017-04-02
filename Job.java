import java.io.*; 
import java.util.*;
import java.lang.*;
import java.text.*;

public class Job
{
    protected String name;
    protected String location;
    protected double rate;
    protected double hours;
    
    public Job()
    {
        this.name = "";
        this.location = "";
        this.rate = 0;
        this.hours = 0;
    }
    
    public Job(String name, String location, double rate, double hours)
    {
        this.name = name;
        this.location = location;
        this.rate = rate;
        this.hours = hours;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public double getRate()
    {
        return rate;
    }
    
    public double getHours()
    {
        return hours;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public void setRate(double rate)
    {
        this.rate = rate;
    }
    
    public void setHours(double hours)
    {
        this.hours = hours;
    }
    
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("##.##");
        return "Name: " + name + "\nLocation: " + location + "\nRate: $" + df.format(rate) + " per hour\nAccumulated Hours for Pay: " + df.format(hours) + "\nEstimated pay: $" + df.format(rate *hours) + "\n";
    }
}