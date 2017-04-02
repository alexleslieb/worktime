public class User
{
    String fname, lname, email, password, profilePic;
    DateOfBirth dateOfBirth;
    Cell cellNumber;
    int reports;
    
    public User()
    {
    }

    public User(String fname, String lname, String email, String pass, DateOfBirth dob, Cell cell, String pic)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        password = pass;
        dateOfBirth = dob;
        cellNumber = cell;
        profilePic = pic;
    }
    
    public User(String fname, String lname, String email, String pass, String pic)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        password = pass;
        dateOfBirth = new DateOfBirth("xx","xx","xxxx");
        cellNumber = new Cell("xxx","xxx","xxxx");
        profilePic = pic;
    }
    
    public String getFirstName()
    {
        return fname;
    }
    
    public String getLastName()
    {
        return lname;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public Cell getCellNumber()
    {
        return cellNumber;
    }
    
    public DateOfBirth getDob()
    {
        return dateOfBirth;
    }
    
    public String getProfilePic()
    {
        return profilePic;
    }

    public void setFirstName(String name)
    {
        fname = name;
    }
    
    public void setLastName(String name)
    {
        lname = name;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void setPassword(String pass)
    {
        password = pass;
    }
    
    public void setCellNumber(Cell cell)
    {
        cellNumber = cell;
    }
    
    public void setDateOfBirth(DateOfBirth date)
    {
        dateOfBirth = date;
    }
    
    public void setProfilePic(String pic)
    {
        profilePic = pic;
    }

    public String toString()
    {
        return "First Name: " + fname + "\nLastName: " + lname + "\nEmail: " + email + "\nCell Number: " + cellNumber + "\nDate of Birth: " + dateOfBirth;
    }
}
