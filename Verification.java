import java.time.LocalDateTime;

public interface Verification
{
    public boolean isValidName(String name);
    public boolean isValidEmail(String email);
    public boolean isValidCellNumber(String cell);
    public boolean isValidDOB(DateOfBirth date);
    public boolean isValidPassword(String password);
}

class Verify implements Verification
{
    public boolean isValidName(String name)
    {
        return name.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
    }
    
    public boolean isValidEmail(String email)
    {
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    
    public boolean isValidCellNumber(String cell)
    {
        return cell.matches("^\\+(?:[0-9] ?){6,14}[0-9]$");
    }
    
    public boolean isValidDOB(DateOfBirth date)
    {
        LocalDateTime now = LocalDateTime.now();
        int day, month, year;
        
        day = Integer.parseInt(date.getDay());
        month = Integer.parseInt(date.getMonth());
        year = Integer.parseInt(date.getYear());
        
        if(now.getYear() < year)
        {
            return false;
        }
        else
        {
            if(now.getMonthValue() < month && now.getYear() < year)
            {
                return false;
            }
            else
            {
                if(now.getDayOfMonth() < day && now.getMonthValue() < month && now.getYear() < year)
                {
                    return false;
                }
            }
        }
        
        if (year % 4 == 0) //Leap year
        {
            if(month == 2) //February leap year
            {
                if(day >= 1 && day <= 29)
                {
                    return true;
                }
            }
            else
            {
                if((month == 4) || (month == 6) || (month == 9) || (month == 11))//April, June, September, November
                {
                    if(day >= 1 && day <= 30)
                    {
                        return true;
                    }
                }
                else
                {
                    if(day >= 1 && day <= 31)//Remaining months
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            if(month >= 1 && month <= 12)//Check if month is valid
            {
                if(month == 2)//February
                {
                    if(day >= 1 && day <= 28)
                    {
                        return true;
                    }
                }
                else
                {
                    if((month == 4) || (month == 6) || (month == 9) || (month == 11))//April, June, September, November
                    {
                        if(day >= 1 && day <= 30)
                        {
                            return true;
                        }
                    }
                    else
                    {
                        if(day >= 1 && day <= 31)//Remaining months
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean isValidPassword(String password)
    {
        //return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,10}");
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}");
    }
}
