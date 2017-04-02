import java.io.*; 
import java.util.*;
import java.lang.*;

public class Cell
{
    String cell1, cell2, cell3;

    public Cell(String c1, String c2, String c3)
    {
        cell1 = c1;
        cell2 = c2;
        cell3 = c3;
    }
    
    public String getCell1()
    {
        return cell1;
    }
    
    public String getCell2()
    {
        return cell2;
    }
    
    public String getCell3()
    {
        return cell3;
    }
    
    public void setCell1(String c1)
    {
        cell1 = c1;
    }
    
    public void setCell2(String c2)
    {
        cell2 = c2;
    }
    
    public void setCell3(String c3)
    {
        cell3 = c3;
    }
    
    public String toString()
    {
        return  cell1 + "-" + cell2 + "-" + cell3;
    }
}