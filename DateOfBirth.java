import java.io.*; 
import java.util.*;
import java.lang.*;

public class DateOfBirth
{
    String day, month, year;

    public DateOfBirth(String m, String d, String y)
    {
        day = d;
        month = m;
        year = y;
    }
    
    public String getDay()
    {
        return day;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public String getYear()
    {
        return year;
    }
    
    public void setDay(String d)
    {
        day = d;
    }
    
    public void setMonth(String m)
    {
        month = m;
    }
    
    public void setYear(String y)
    {
        year = y;
    }
    
    public String toString()
    {
        return  month + "/" + day + "/" + year;
    }
}