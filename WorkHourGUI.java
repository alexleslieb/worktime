import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import javax.swing.filechooser.*;

public class WorkHourGUI extends JPanel 
{
    private FileSystem work;
    private JLabel headermsg, opHeader, jobNameLabel, locationLabel, rateLabel, hoursLabel, response, confirm, hrsLabel, minLabel, newPasswordLabel, newPasswordLabel2;
    private JLabel firstNameLabel, lastNameLabel, emailLabel, passwordLabel, dateLabel, cellLabel, profilePicLabel, tempPicLabel, oldPasswordLabel, mainPagePic;
    private JLabel tPayLabel, tHoursLabel, nameLabel, jobsLabel, infoLabel[];
    private JPanel header, accountPanel, accountPanel2, menu, external, operation, signUpForm, editUserForm, editAccountForm, logInForm, jobForm, changePasswordForm, totalsPanel;
    private JPanel jobButtons, jobInfoPanel, newHours, dateOfBirth, cellPanel, window, lowerSignUpPanel, passwordPanel, userInfoTable, picPanel, jobPanel[], changePicPanel;
    private JPanel reportsPanel, reportButtonsPanel, formBoxPanel;
    private JButton accountOps[], mainOps[], submit, clear, jobs[] = new JButton[1], cancel = new JButton("Cancel"), reportArray[] = new JButton[1];
    private JButton browse, personalInfoEdit, passwordEdit, save, deleteAccount, createReport, viewReports, deleteReport;
    private JButton pictureEdit = new JButton("Change Profile Picture");
    private JTextArea descriptions[], jnameOP, reportDescription = new JTextArea();; 
    private JTextField name, location, rate, hours, name2, location2, rate2, hours2, firstName, lastName, email, cell1, cell2, cell3;
    private JTextField newHours1 = new JTextField(""), newHours2 = new JTextField(""), day, month, year;
    private JPasswordField password, newPassword, newPassword2;
    private String jname = "", jlocation = "", passwordRules, defaultProfilePic = "defaultprofileicon.png", tempList = "";
    private Report report;
    private ArrayList <Job> jobs3;
    private ArrayList <Report> reports;
    private User loggedUser;
    private JScrollPane windowPane, jobDescriptionsPane, reportListPane, reportDescPane;
    private Verification verification;
    private int n = 0;
    private float totalPay, totalHours;
    private DecimalFormat df;
    private Color buttonColor = new Color(204, 217, 255), fadedButtonColor = new Color(230, 230, 255);
    private JRadioButton showPasswordButton;
    private ImageIcon imageIcon;
    private JFileChooser fileChooser;
    private File selectedFile;
    private Random r;
    private ButtonListener blisten;
    private RadioListener rlisten;
    private SubmitOnEnter klisten;
    private GridLayout horLayout = new GridLayout(1,0), vertLayout = new GridLayout(0,1), totalsLayout = new GridLayout(1,1);

    public WorkHourGUI()
    {           
        r = new Random();

        work = new FileSystem(); // Create instance of file system
        
        verification = new Verify(); //Create instance of verification class
        
        passwordRules = "Password must:\n*Be at least 8 characters long\n*Contain an uppercase letter\n*Contain a lowercase letter\n*Conatain at least one numerical digit\n*Contain no spaces";
        
        setBackground(Color.BLACK);
        
        window = new JPanel();
        window.setPreferredSize(new Dimension(840,565));
        window.setBackground(new Color(0, 57, 230));

        //Creates scroll pane for window panel
        windowPane = new JScrollPane(window,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        windowPane.setPreferredSize(new Dimension(850,575));
        windowPane.setBorder(BorderFactory.createRaisedBevelBorder());               
        
        UIManager.put("Button.disabledText", new Color(0,0, 0));//Makes all disabled buttons have a black text
        repaint();

        blisten = new ButtonListener();//Instantiates button listener
        
        ClickListener clisten = new ClickListener();//Instantiates click listener
        
        rlisten = new RadioListener();//Instantiates radio button listener
        
        klisten = new SubmitOnEnter();//Instantiates key listener
        
        //Header Panel
        JLabel headermsg = new JLabel ("WORK TIME");
        JPanel header = new JPanel();
        header.setBackground(new Color(204, 217, 255));
        header.setPreferredSize(new Dimension(840,25));
        header.add(headermsg);
        
        //Panels for Account buttons
        accountPanel = new JPanel(new GridLayout(1,0));
        accountPanel.setPreferredSize(new Dimension(850,20));
        accountPanel2 = new JPanel(new GridLayout(1,0));
        accountPanel2.setPreferredSize(new Dimension(850,20));
        
        //Account buttons
        accountOps = new JButton[4];
        accountOps[0] = new JButton("Sign Up");
        accountOps[1] = new JButton("Log In");
        accountOps[2] = new JButton("My Account");
        accountOps[3] = new JButton("Log Out");
        
        for(n = 0; n < accountOps.length;n++)
        {
            accountOps[n].addActionListener(blisten);
        }
        accountPanel.add(accountOps[0]);
        accountPanel.add(accountOps[1]);
        accountPanel2.add(accountOps[2]);
        accountPanel2.add(accountOps[3]);
        
        //Panel for menu buttons
        menu = new JPanel(new GridLayout(1,0));
        menu.setPreferredSize(new Dimension(855,20));
        
        //Menu Buttons
        mainOps = new JButton[6];
        mainOps[0] = new JButton("Add Job");
        mainOps[1] = new JButton("Edit Job Data");
        mainOps[2] = new JButton("Add Job Hours");
        mainOps[3] = new JButton("Reset Job Hours");
        mainOps[4] = new JButton("Delete Job");
        mainOps[5] = new JButton("View Job Details");       

        for(n = 0; n < mainOps.length;n++)
        {
            mainOps[n].addActionListener(blisten);
            menu.add(mainOps[n]);
        }
        
        //External Panel
        external = new JPanel(new GridLayout(0,1));
        external.add(header);
        external.add(accountPanel);
        
        //Operation Panel
        operation = new JPanel(); 
        operation.setBackground(Color.white);
        operation.setPreferredSize(new Dimension(850,505));

        //First Page Exposition
        jnameOP = new JTextArea("\t\t\tWELCOME TO THE WORK TIME PROGRAM.\n\t\tHERE YOU CAN MANAGE ALL THE HOURS WORKED FOR YOUR JOB(S).\n\t\t\tSELECT AN OPTION ABOVE TO CONTINUE.\n\n\t\t\t\tBy: Alex Leslie");
        jnameOP.setEditable(false);
        jnameOP.setPreferredSize(new Dimension(800,100));
        
        mainPagePic = new JLabel();//Pic label displayed on main page
        mainPagePic.setPreferredSize(new Dimension(275,275));
        mainPagePic.setHorizontalAlignment(JLabel.CENTER);
        
        mainPage();
                
        //Sign Up Form-------------------------------------------------------------------------------------------------------------------------------------
        formBoxPanel = new JPanel();
        formBoxPanel.setLayout(new BoxLayout(formBoxPanel,BoxLayout.PAGE_AXIS));
        
        signUpForm = new JPanel(new GridLayout(0,2));
        signUpForm.setPreferredSize(new Dimension(400,150));
        signUpForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        
        firstNameLabel = new JLabel("First Name:",SwingConstants.CENTER);
        firstNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        firstNameLabel.setOpaque(true);
        firstNameLabel.setBackground(fadedButtonColor);
        
        lastNameLabel = new JLabel("Last Name:",SwingConstants.CENTER);
        lastNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lastNameLabel.setOpaque(true);
        lastNameLabel.setBackground(Color.WHITE);
        
        emailLabel = new JLabel("Email:",SwingConstants.CENTER);
        emailLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emailLabel.setOpaque(true);
        emailLabel.setBackground(fadedButtonColor);
        
        passwordLabel = new JLabel("Password:",SwingConstants.CENTER);
        passwordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(Color.WHITE);
        
        dateLabel = new JLabel("Date of Birth:",SwingConstants.CENTER);
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dateLabel.setOpaque(true);
        dateLabel.setBackground(fadedButtonColor);
        
        cellLabel = new JLabel("Cell Number:",SwingConstants.CENTER);
        cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cellLabel.setOpaque(true);
        cellLabel.setBackground(Color.WHITE);
        
        firstName = new JTextField("");
        firstName.setPreferredSize(new Dimension(200,20));
        firstName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        firstName.addKeyListener(klisten);
        
        lastName = new JTextField("");
        lastName.setPreferredSize(new Dimension(200,20));
        lastName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lastName.addKeyListener(klisten);
        
        email = new JTextField("");
        email.setPreferredSize(new Dimension(200,20));
        email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        email.addKeyListener(klisten);
        
        cellPanel = new JPanel();
        cellPanel.setPreferredSize(new Dimension(200,18));
        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cellPanel.setOpaque(true);
        cellPanel.setBackground(Color.WHITE);
        
        cell1 = new JTextField();
        cell1.setPreferredSize(new Dimension(30,30));
        cell1.setDocument(new LengthRestrictedDocument(3));
        cell1.addFocusListener(clisten);
        cell1.addKeyListener(klisten);

        cell2 = new JTextField();
        cell2.setPreferredSize(new Dimension(30,30));
        cell2.setDocument(new LengthRestrictedDocument(3));
        cell2.addFocusListener(clisten);
        cell2.addKeyListener(klisten);

        cell3 = new JTextField();
        cell3.setPreferredSize(new Dimension(40,30));
        cell3.setDocument(new LengthRestrictedDocument(4));
        cell3.addFocusListener(clisten);
        cell3.addKeyListener(klisten);

        cellPanel.add(new JLabel("("));
        cellPanel.add(cell1);
        cellPanel.add(new JLabel(")"));
        cellPanel.add(new JLabel("-"));
        cellPanel.add(cell2);
        cellPanel.add(new JLabel("-"));
        cellPanel.add(cell3);
        
        password = new JPasswordField("");
        password.setPreferredSize(new Dimension(200,20));
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        password.addKeyListener(klisten);

        showPasswordButton = new JRadioButton("Show Password");
        showPasswordButton.setBackground(Color.WHITE);
        showPasswordButton.addActionListener(rlisten);
        
        dateOfBirth = new JPanel();
        dateOfBirth.setPreferredSize(new Dimension(200,18));
        dateOfBirth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dateOfBirth.setOpaque(true);
        dateOfBirth.setBackground(Color.WHITE);

        day = new JTextField();
        day.setPreferredSize(new Dimension(22,30));
        day.setDocument(new LengthRestrictedDocument(2));
        day.addFocusListener(clisten);
        day.addKeyListener(klisten);
        
        month = new JTextField();
        month.setPreferredSize(new Dimension(22,30));
        month.setDocument(new LengthRestrictedDocument(2));
        month.addFocusListener(clisten);
        month.addKeyListener(klisten);

        year = new JTextField();
        year.setPreferredSize(new Dimension(40,30));
        year.setDocument(new LengthRestrictedDocument(4));
        year.addFocusListener(clisten);
        year.addKeyListener(klisten);
        
        dateOfBirth.add(month);
        dateOfBirth.add(new JLabel("/"));
        dateOfBirth.add(day);  
        dateOfBirth.add(new JLabel("/"));
        dateOfBirth.add(year);         
          
        submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(100,25));
        submit.addActionListener(blisten);
        
        clear = new JButton("Clear");
        clear.setPreferredSize(new Dimension(100,25));
        clear.addActionListener(blisten);
        
        lowerSignUpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Creates panel for submit and cancel below sign up form
        lowerSignUpPanel.setPreferredSize(new Dimension(337,30));
        lowerSignUpPanel.setOpaque(true);
        lowerSignUpPanel.setBackground(Color.WHITE);
        //--------------------------------------------------------------------------------------------------------------------------------------------------
        
        //Log In Form---------------------------------------------------------------------------------------------------------------------------------------
        logInForm = new JPanel(new GridLayout(0,2));
        logInForm.setPreferredSize(new Dimension(400,70));
        logInForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        
        //---------------------------------------------------------------------------------------------------------------------------------------------------
               
        //New Job Form---------------------------------------------------------------------------------------------------------------------------------------
        jobForm = new JPanel(new GridLayout(0,2));
        jobForm.setPreferredSize(new Dimension(800,200));
        jobForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        
        jobNameLabel = new JLabel("Name:",SwingConstants.CENTER);
        jobNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jobNameLabel.setOpaque(true);
        jobNameLabel.setBackground(fadedButtonColor);
        
        locationLabel = new JLabel("Location:",SwingConstants.CENTER);
        locationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        locationLabel.setOpaque(true);
        locationLabel.setBackground(Color.WHITE);
        
        rateLabel = new JLabel("Rate:",SwingConstants.CENTER);
        rateLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rateLabel.setOpaque(true);
        rateLabel.setBackground(fadedButtonColor);
        
        hoursLabel = new JLabel("Hours:",SwingConstants.CENTER);
        hoursLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hoursLabel.setOpaque(true);
        hoursLabel.setBackground(Color.WHITE);
        
        name = new JTextField("");
        name.setPreferredSize(new Dimension(200,20));
        name.addKeyListener(klisten);
        
        location = new JTextField("");
        location.setPreferredSize(new Dimension(200,20));
        location.addKeyListener(klisten);
        
        rate = new JTextField("");
        rate.setPreferredSize(new Dimension(200,20));
        rate.addKeyListener(klisten);
        
        hours = new JTextField("");
        hours.setPreferredSize(new Dimension(200,20));
        hours.addKeyListener(klisten);
        //--------------------------------------------------------------------------------------------------------------------------------------------------
        
        //Create response label that keeps user informed of current operations and responses
        response = new JLabel("");
        response.setPreferredSize(new Dimension(800,20));
        operation.add(response);

        //newHours = new JPanel(new GridLayout(1,0));
        newHours = new JPanel();
        
        //Set up job buttons-----------------------------------------------------------------------------------------------------------------------------------
        vertLayout.setVgap(20);
        jobButtons = new JPanel(vertLayout);
        jobButtons.setOpaque(false);
        jobButtons.setBackground(Color.WHITE);
        operation.add(jobButtons);

        cancel.addActionListener(blisten);
        cancel.setPreferredSize(new Dimension(100,25));
        //-----------------------------------------------------------------------------------------------------------------------------------------------------
        
        confirm = new JLabel();
        confirm.setPreferredSize(new Dimension(800,30));       

        totalsLayout.setHgap(20);
        totalsPanel = new JPanel(totalsLayout);
        totalsPanel.setPreferredSize(new Dimension(750,30));
        totalsPanel.setOpaque(false);
        totalsPanel.setBackground(Color.WHITE);        

        tPayLabel = new JLabel();
        tPayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tPayLabel.setOpaque(true);
        tPayLabel.setBackground(fadedButtonColor);
        tPayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        tHoursLabel = new JLabel();
        tHoursLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tHoursLabel.setOpaque(true);
        tHoursLabel.setBackground(fadedButtonColor);
        tHoursLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //Add Job Hours components---------------------------------------------------------
        hrsLabel = new JLabel("Hours:");
        minLabel = new JLabel("Minutes:");
        
        newHours1 = new JTextField("");
        newHours1.setPreferredSize(new Dimension(30,30));
        newHours1.setDocument(new LengthRestrictedDocument(2));
        newHours1.addKeyListener(klisten);
        
        newHours2 = new JTextField("");
        newHours2.setPreferredSize(new Dimension(30,30));
        newHours2.setDocument(new LengthRestrictedDocument(2));
        newHours2.addKeyListener(klisten);
        
        //Account management------------------------------------------------------------------------------        
        editAccountForm = new JPanel(new GridLayout(0,2));
        editAccountForm.setPreferredSize(new Dimension(800,250));
        editAccountForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        
        browse = new JButton("Browse...");
        browse.addActionListener(blisten);//Browse button for changing profile pic
        
        save = new JButton("Save");
        save.addActionListener(blisten);//Save button for changing profile pic
        
        personalInfoEdit = new JButton("Edit Personal Information");
        personalInfoEdit.addActionListener(blisten);//Edit button for changing account data
        
        passwordEdit = new JButton("Change Password");
        passwordEdit.addActionListener(blisten);//Edit button for changing password
        
        pictureEdit.addActionListener(blisten);//Edit button for changing picture
        
        deleteAccount = new JButton("Delete Account");
        deleteAccount.addActionListener(blisten);//Edit button for deleting user account
        
        profilePicLabel = new JLabel();
        profilePicLabel.setPreferredSize(new Dimension(800,200));
        profilePicLabel.setHorizontalAlignment(JLabel.CENTER);

        //Change password form-----------------------------------------------------------------------------
        changePasswordForm = new JPanel(new GridLayout(0,2));
        changePasswordForm.setPreferredSize(new Dimension(800,120));
        changePasswordForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        
        oldPasswordLabel = new JLabel("Old Password:",SwingConstants.CENTER);
        oldPasswordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        oldPasswordLabel.setOpaque(true);
        oldPasswordLabel.setBackground(fadedButtonColor);
        
        newPasswordLabel = new JLabel("New Password:",SwingConstants.CENTER);
        newPasswordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPasswordLabel.setOpaque(true);
        newPasswordLabel.setBackground(Color.WHITE);
        
        newPasswordLabel2 = new JLabel("Confirm Password:",SwingConstants.CENTER);
        newPasswordLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPasswordLabel2.setOpaque(true);
        newPasswordLabel2.setBackground(fadedButtonColor);
        
        newPassword = new JPasswordField("");
        newPassword.setPreferredSize(new Dimension(200,20));
        newPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPassword.addKeyListener(klisten);
        
        newPassword2 = new JPasswordField("");
        newPassword2.setPreferredSize(new Dimension(200,20));
        newPassword2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPassword2.addKeyListener(klisten);
        //-------------------------------------------------------------------------------------------------
        
        passwordPanel = new JPanel();//Panel for changing password
        passwordPanel.setPreferredSize(new Dimension(800,30));
        passwordPanel.setOpaque(true);
        passwordPanel.setBackground(Color.WHITE);
        
        //Account page contents--------------------------------------------------------------------------------
        picPanel = new JPanel();
        picPanel.setPreferredSize(new Dimension(800,230));
        picPanel.setOpaque(true);
        picPanel.setBackground(Color.WHITE);
        
        userInfoTable = new JPanel(new GridLayout(0,2));
        userInfoTable.setPreferredSize(new Dimension(600,100));
        
        nameLabel = new JLabel("",SwingConstants.CENTER);      
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setPreferredSize(new Dimension(700,20));
        
        jobsLabel = new JLabel("Jobs:",SwingConstants.CENTER);      
        jobsLabel.setOpaque(true);
        jobsLabel.setBackground(Color.WHITE);
        jobsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));        
        
        infoLabel = new JLabel[4];
        for(n = 0; n < infoLabel.length; n++)
        {
            infoLabel[n] = new JLabel("",SwingConstants.CENTER);
            infoLabel[n].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            infoLabel[n].setOpaque(true);
            infoLabel[n].setBackground(Color.WHITE);
        }
        //-------------------------------------------------------------------------------------------------------------------------------------------------
        //Profile Pic Panel--------------------------------------------------------------------------------------------------------------------------------
        changePicPanel = new JPanel();
        changePicPanel.setLayout(new BoxLayout(changePicPanel,BoxLayout.PAGE_AXIS));
        changePicPanel.setPreferredSize(new Dimension(230,230));
        //-------------------------------------------------------------------------------------------------------------------------------------------------
        //Report Components--------------------------------------------------------------------------------------------------------------------------------
        createReport = new JButton("Create Report");
        createReport.addActionListener(blisten);
        
        viewReports = new JButton("View Reports");
        viewReports.addActionListener(blisten);
        
        deleteReport = new JButton("Delete Report");
        deleteReport.addActionListener(blisten);        
        
        horLayout.setHgap(20);        
        reportsPanel = new JPanel(horLayout);
        reportsPanel.setOpaque(false);
        reportsPanel.setBackground(Color.WHITE);      

        reportListPane = new JScrollPane(reportsPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reportListPane.setPreferredSize(new Dimension(800,120));
        
        reportDescription.setEditable(false);

        reportDescPane = new JScrollPane(reportDescription,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reportDescPane.setPreferredSize(new Dimension(600,265));    

        reportButtonsPanel = new JPanel(vertLayout);
        reportButtonsPanel.setOpaque(false);
        reportButtonsPanel.setBackground(Color.WHITE);
        
        //------------------------------------------------------------------------------------------------------------------------------------------------

        window.add(header);
        window.add(external);
        window.add(operation);
        
        add(windowPane);

        setVisible(true);
    }
    
    //Yes or no dialog box
    public int confirmationBox(String message)
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        
        return JOptionPane.showConfirmDialog(null, message, "Confirm", dialogButton);
    }
    
    //User input dialog box
    public int inputBox(String message, JPanel panel)
    {
        return JOptionPane.showConfirmDialog(null, panel, message, JOptionPane.OK_CANCEL_OPTION);
    }

    //Creates sign up box
    public void signUpBox(JPanel signUpForm)
    {
        if(inputBox("Sign Up", signUpForm) == JOptionPane.OK_OPTION)
        {
            submitSignUpForm();
        }   
        else
        {
            clearSignUpForm();
        }
    }
    
    //Creates log in box
    public void logInBox(JPanel logInForm)
    {
        if(inputBox("Log In", logInForm) == JOptionPane.OK_OPTION)
        {
            submitLogInForm();
        }
        else
        {
            clearLogInForm();
        }
    }
    
    //Creates box for changing picture
    public void changePicBox(JPanel changePicPanel)
    {
        if(inputBox("Change Profile Picture", changePicPanel) == JOptionPane.OK_OPTION)
        {
            loggedUser.setProfilePic(selectedFile.getName());
            
            work.changeProfilePic(loggedUser);
            
            JOptionPane.showMessageDialog(null, "Profile picture has been updated");
            
            setImage(loggedUser.getProfilePic(),profilePicLabel,200,200);
            
            operation.repaint();
            operation.revalidate();
        }
    }
    
    //Check if new password form is empty
    public boolean isPasswordFormEmpty()
    {
        if(String.valueOf(password.getPassword()).length() == 0 || String.valueOf(newPassword.getPassword()).length() == 0 || String.valueOf(newPassword2.getPassword()).length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Default Page
    public void mainPage()
    {
        opHeader = new JLabel("OPERATION SCREEN");
        
        //setImage("Pictures\\\\icon.png",mainPagePic, 250, 250);        

        setImage("icon.png",mainPagePic, 275, 275);

        operation.add(opHeader);
        operation.add(jnameOP);
        //operation.add(mainPagePic);
        
        operation.setPreferredSize(new Dimension(850,505));
        
        external.remove(accountPanel2);
        external.remove(menu);
        external.add(accountPanel);
        external.repaint();
        external.revalidate();
    }
    
    //Clears fields for add job form
    public void clearJobFormFields()
    {
        name.setText(""); 
        location.setText(""); 
        rate.setText(""); 
        hours.setText("");
    }    
    
    //Concatenates a job name and location
    private String concatenate(String name, String location)
    {
        return name + " - " + location;
    }
    
    //Separates the name from the concatenated form of a jobs name and location from a selected button's text value
    private String separateName(String button)
    {
        String sep[] = button.split(" - ");
        return sep[0];
    }
    
    //Separates the location from the concatenated form of a jobs name and location from a selected button's text value
    private String separateLocation(String button)
    {
        String sep[] = button.split(" - ");
        return sep[1];
    }
    
    //Enables all menu buttons
    private void enableAllMenuButtons()
    {
        int i;
        
        for (i = 0; i < mainOps.length;i++)
        {
            mainOps[i].setEnabled(true);
        }
    }
    
    //Enables all account header buttons
    private void enableAllAccountButtons()
    {
        int i;
        
        for (i = 0; i < accountOps.length;i++)
        {
            accountOps[i].setEnabled(true);
        }
    }
    
    //Enables all account manager buttons
    private void enableAllManagerButtons()
    {
        viewReports.setEnabled(true);
        personalInfoEdit.setEnabled(true); 
        pictureEdit.setEnabled(true); 
        passwordEdit.setEnabled(true);
        deleteAccount.setEnabled(true);        
    }
    
    //Creates buttons for job selection
    private void createJobButtons(JButton jobs[], ArrayList <Job> jobs3)
    {     
        int i;

        jobButtons.removeAll();
        
        blisten = new ButtonListener();
        for (i = 0; i < jobs.length;i++)
        {
            jobs[i] = new JButton(concatenate(jobs3.get(i).getName(),jobs3.get(i).getLocation()));
            jobs[i].setBackground(buttonColor);
            jobButtons.add(jobs[i]);
            jobs[i].addActionListener(blisten);
            jobs[i].setPreferredSize(new Dimension(800,50));
            
            operation.revalidate();
            operation.repaint();
        }
    }
    
    //Creates buttons for report selection
    private void createReportButtons(JButton reportArray[], ArrayList <Report> reports)
    {
        int i;
        
        reportsPanel.removeAll();
        for(i = 0; i < reportArray.length; i++)
        {
            reportArray[i] = new JButton(reports.get(i).getDate());
            reportArray[i].setBackground(buttonColor);
            reportsPanel.add(reportArray[i]);
            reportArray[i].addActionListener(blisten);
            reportArray[i].setPreferredSize(new Dimension(140,115));

            operation.revalidate();
            operation.repaint();
        }
    }
    
    //Creates buttons for jobs with selected button changing color
    private void greyJobButton(JButton jobs[], ArrayList <Job> jobs3, String jname, String jlocation)
    {
        int i;

        for (i = 0; i < jobs.length;i++)
        {
            if (jobs[i].getText().equals(concatenate(jname,jlocation)))
            {
                jobs[i].setBackground(fadedButtonColor);
                jobs[i].setEnabled(false);
            }
            else
            {
                jobs[i].setBackground(buttonColor);
                jobs[i].setEnabled(true);
            }
        }
    }
    
    //Disables selected report button
    private void greyReportButton(JButton reportArray[], String selected)
    {
        int i;
        
        for(i = 0; i < reportArray.length; i++)
        {
            if(reportArray[i].getText().equals(selected))
            {
                reportArray[i].setEnabled(false);
                reportArray[i].setBackground(Color.WHITE);
            }
            else
            {
                reportArray[i].setEnabled(true);
                reportArray[i].setBackground(fadedButtonColor);
            }
        }
    }
    
    //Removes all components from the screen excluding account and menu buttons
    private void clearScreen()
    {
        password.setText("");
        newPassword.setText("");
        newPassword2.setText("");
        
        resetDateFields();
        resetCellField();
        jobForm.removeAll();
        operation.removeAll();
        jobButtons.removeAll();
    }
    
    //Resets the date fields to default indicator values
    private void resetDateFields()
    {
        day.setText("DD");
        day.setForeground(Color.LIGHT_GRAY);
        month.setText("MM");
        month.setForeground(Color.LIGHT_GRAY);
        year.setText("YYYY");
        year.setForeground(Color.LIGHT_GRAY);
    }
    
    //Resets the cell fields to default indicator values
    private void resetCellField()
    {
        cell1.setText("XXX");
        cell1.setForeground(Color.LIGHT_GRAY);
        cell2.setText("XXX");
        cell2.setForeground(Color.LIGHT_GRAY);
        cell3.setText("XXXX");
        cell3.setForeground(Color.LIGHT_GRAY);
    }
    
    //Clears fields in sign up form
    public void clearSignUpForm()
    {               
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        resetCellField();
        resetDateFields();
        password.setText("");
        
        password.setEchoChar((char)0);
        showPasswordButton.setSelected(false);
    }
    
    //Clears fields in log in form
    public void clearLogInForm()
    {
        email.setText("");
        password.setText("");
    }
    
    //Sets state of account operation button corresponding to the key to disabled
    private void setAccountOpsStates(int key)
    {
        int i;
        
        for(i = 0; i < accountOps.length;i++)
        {
            if (i == key)
            {
                accountOps[i].setEnabled(false);
            }
            else
            {
                accountOps[i].setEnabled(true);
            }
        }
    }
    
    //Sets state of main operation button corresponding to the key to disabled
    private void setMainOpsStates(int key)
    {
        int i;
        
        for(i = 0; i < mainOps.length;i++)
        {
            if (i == key)
            {
                mainOps[i].setEnabled(false);
            }
            else
            {
                mainOps[i].setEnabled(true);
            }
        }
    }
    
    //Updates the list of job buttons
    private void updateJobs()
    {
        jobs3 = work.jobList(loggedUser);              
        jobs = new JButton[jobs3.size()];
    }
    
    //Updates the list of report buttons
    private void updateReports()
    {
        reports = work.readReports(loggedUser);
        reportArray = new JButton[reports.size()];
    }

    //Displays account page
    private void accountPage()
    {
        enableAllManagerButtons();
        enableAllMenuButtons();                
        
        operation.add(opHeader);
        operation.add(response);
        
        opHeader.setText("ACCOUNT INFORMATION");
        response.setText("View or edit account data below");
        
        accountOps[2].setEnabled(false);

        nameLabel.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
        
        //setImage("Pictures\\" + loggedUser.getProfilePic(), profilePicLabel, 200, 200);
        setImage(loggedUser.getProfilePic(), profilePicLabel, 200, 200);
        
        picPanel.add(profilePicLabel);
        picPanel.add(nameLabel);
        
        infoLabel[0].setText(loggedUser.getDob().toString());
        infoLabel[1].setText(loggedUser.getEmail());
        infoLabel[2].setText(loggedUser.getCellNumber().toString());
        
        
        if(jobs3.size() != 0)
        {
            for(n = 0; n < jobs3.size(); n++)
            {
                tempList = tempList + jobs3.get(n).getName();
                if (n != jobs3.size() - 1)
                {
                    tempList = tempList + ", ";
                }
            }
            
            infoLabel[3].setText(tempList);
            tempList = "";
        }
        else
        {
            infoLabel[3].setText("None");
        }
        
        userInfoTable.add(dateLabel);
        userInfoTable.add(infoLabel[0]);
        userInfoTable.add(cellLabel);
        userInfoTable.add(infoLabel[2]);
        userInfoTable.add(emailLabel);
        userInfoTable.add(infoLabel[1]);
        userInfoTable.add(jobsLabel);
        userInfoTable.add(infoLabel[3]);

        operation.add(createReport);
        operation.add(viewReports);
        operation.add(personalInfoEdit);
        operation.add(passwordEdit);
        //operation.add(pictureEdit);
        operation.add(deleteAccount);
        //operation.add(picPanel);
        operation.add(userInfoTable);
        operation.repaint();
        operation.revalidate();
    }
    
    //Creates page for reports
    public void reportScreen()
    {
        accountOps[2].setEnabled(true);
        
        viewReports.setEnabled(false);
        
        deleteReport.setEnabled(false);
        
        reportButtonsPanel.add(deleteReport);
        reportButtonsPanel.add(cancel);
        
        operation.add(opHeader);
        operation.add(response);
        operation.add(viewReports);
        operation.add(reportListPane);
        operation.add(reportDescPane);
        operation.add(reportButtonsPanel);
        
        response.setText("Select a report to view.");
       
        reportDescription.setText("");
        
        createReportButtons(reportArray,reports);
    }
    
    //Checks if sign up form is empty and returns boolean value
    public boolean signUpFormEmpty()
    {
        if(firstName.getText().trim().length() == 0 || lastName.getText().trim().length() == 0 || email.getText().trim().length() == 0 
            || String.valueOf(password.getPassword()).trim().length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Checks if account update form is empty
    public boolean editAccountFormEmpty()
    {
        if(firstName.getText().trim().length() == 0 || lastName.getText().trim().length() == 0 || email.getText().trim().length() == 0 
            || cell1.getText().trim().length() == 0 || cell2.getText().trim().length() == 0 
            || cell3.getText().trim().length() == 0 || day.getText().trim().length() == 0 
            || month.getText().trim().length() == 0 || year.getText().trim().length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Changes image of designated image label
    public void setImage(String imgName, JLabel imageLabel, int hor, int ver)
    {   
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        if((new File(imgName)).exists())
        {
            //imageIcon = new ImageIcon(getClass().getResource(imgName.replace("\\","\\\\")));   
            imageIcon = new ImageIcon(getClass().getResource(imgName));      
            Image img = imageIcon.getImage().getScaledInstance(hor,ver,java.awt.Image.SCALE_SMOOTH);
            imageLabel.setText("");
            //imageLabel.setPreferredSize(new Dimension(800,ver));
            imageIcon = new ImageIcon(img);
            imageLabel.setIcon(imageIcon);
        }
        else
        {
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found");
            imageLabel.setPreferredSize(new Dimension(200,200));
            operation.revalidate();
            operation.repaint();
        }

        if(!pictureEdit.isEnabled())
        {
            operation.add(save);
            operation.revalidate();
            operation.repaint();
        }
    }
    
    //Submit Sign Up Form
    public void submitSignUpForm()
    {
        if(signUpFormEmpty())
        {
            JOptionPane.showMessageDialog(null,"Fill out all fields.");  
            signUpBox(signUpForm);
        }
        else
        {
            if(!(verification.isValidName(firstName.getText()) && verification.isValidName(lastName.getText())))
            {
                JOptionPane.showMessageDialog(null,"Name is invalid.");  
                signUpBox(signUpForm);
            }
            else
            {
                if(!verification.isValidEmail(email.getText()))
                {
                    JOptionPane.showMessageDialog(null,"Email is invalid.");  
                    signUpBox(signUpForm);
                }
                else
                {
                    if(!verification.isValidPassword(String.valueOf(password.getPassword())))
                    {
                        JOptionPane.showMessageDialog(null,passwordRules); 
                        signUpBox(signUpForm);
                    }
                    else
                    {
                        User newUser = new User (firstName.getText(),lastName.getText(),email.getText(),String.valueOf(password.getPassword()),defaultProfilePic);
                        
                        if(work.storeUserData(newUser))
                        {
                            JOptionPane.showMessageDialog(null, "New User: " + newUser.getFirstName() + " " + newUser.getLastName() + " has been added.");
                            
                            clearSignUpForm();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "This email is already registered");
                            signUpBox(signUpForm);
                        }
                    }                       
                }
            }
        }
    }
    
    //Submit Login Form
    public void submitLogInForm()
    {
        String mail = email.getText();
        String pass = String.valueOf(password.getPassword());
        
        if(email.getText().trim().length() == 0 || String.valueOf(password.getPassword()).trim().length() == 0)
        {
            //response.setText("You must ensure all fields are filled.");
            JOptionPane.showMessageDialog(null,"Fill out all fields.");  
            logInBox(logInForm);
        }
        else
        {
            if(!verification.isValidEmail(email.getText()))
            {
                //response.setText("Invalid Email.");
                JOptionPane.showMessageDialog(null,"Email is invalid."); 
                logInBox(logInForm);
            }
            else
            {
                if(work.loginUser(mail,pass))
                {
                    JOptionPane.showMessageDialog(null, "Login Successful"); 
                    
                    loggedUser = work.getUserData(mail, loggedUser);
                    
                    //Array List of jobs in file
                    updateJobs();
                    
                    //Initialize user reports from file
                    updateReports();
                    
                    email.setText("");

                    clearScreen();
                    
                    clearLogInForm();
                    
                    accountOps[1].setEnabled(true);
                    
                    external.remove(accountPanel);
                    external.add(accountPanel2);
                    external.add(menu);
                    external.repaint();
                    external.revalidate();
                    
                    operation.setPreferredSize(new Dimension(850,485));
                    
                    accountPage();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Email or password is incorrect"); 
                    logInBox(logInForm);
                }
            }
        }
    }
    
    //Submit Add Job
    public void submitAddJobForm()
    {
        if (name.getText().trim().length() == 0 || location.getText().trim().length() == 0 || 
        rate.getText().trim().length() == 0 || hours.getText().trim().length() == 0)
        {
            response.setText("You must ensure all fields are filled.");
            JOptionPane.showMessageDialog(null,"Fill out all fields.");              
        }
        else
        {           
            try
            {
                if (work.findJob(name.getText(),location.getText(),loggedUser) == 0)
                {
                    if (Double.parseDouble(rate.getText()) >= 0 && Double.parseDouble(hours.getText()) >= 0)
                    {
                        work.storeJobs(name.getText(),location.getText(),Double.parseDouble(rate.getText()),Double.parseDouble(hours.getText()),loggedUser);
                        updateJobs();
                        
                        JOptionPane.showMessageDialog(null, concatenate(name.getText(),location.getText()) + " has been added.");
                        name.setText("");
                        location.setText("");
                        rate.setText("");
                        hours.setText("");
                        response.setText("New Job has been added. Will you add another?");   
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null, "*Rate and hours must be greater than or equal to zero.");  
                    }
                }
                else
                {
                    response.setText("That job already exists");   
                    JOptionPane.showMessageDialog(null, "*The job " + name.getText() + " already exists");
                }
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "*Rate and hours must be numeric values.");              
            }
        }
    }
    
    //Submit Add Hours
    public void submitAddJobHours()
    {
        if(newHours1.getText().trim().length() == 0 || newHours2.getText().trim().length() == 0)
        {
            response.setText("You must fill the text fields before you submit.");
            JOptionPane.showMessageDialog(null, "Fill all fields.");
        }
        else
        {
            try
            {
                if (Double.parseDouble(newHours1.getText()) >= 0 && Double.parseDouble(newHours2.getText()) >= 0 && Double.parseDouble(newHours2.getText()) < 60)
                {
                    DecimalFormat df = new DecimalFormat("##.##");
                    double h = Double.parseDouble(newHours1.getText()) + ((Double.parseDouble(newHours2.getText())/60));
                    
                    work.updateHours(jname,jlocation,h,loggedUser);
                    
                    JOptionPane.showMessageDialog(null, df.format(h) + " hour(s) added to " + concatenate(jname,jlocation) + ".");                   

                    clearScreen();
                    
                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
                    response.setText("Hours have been added. Will you add more?");

                    updateJobs();
                    
                    createJobButtons(jobs,jobs3);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "*Hours/Minutes must be greater than zero.\n*Minutes must be in range 0 - 59.");
                }
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "*Hours and minutes must be numerical values.");
            }
        }
    }
    
    //Submit Edit Jobs
    public void submitEditJobForm()
    {
        if(name.getText().trim().length() == 0 || location.getText().trim().length() == 0 || 
        rate.getText().trim().length() == 0 || hours.getText().trim().length() == 0)
        {
            response.setText("Ensure all fields are completed.");
            JOptionPane.showMessageDialog(null, "Fill out all fields.");
        }
        else
        {
            try
            {
                if (Double.parseDouble(rate.getText()) >= 0 && Double.parseDouble(hours.getText()) >= 0)
                {
                    JOptionPane.showMessageDialog(null, name.getText() + " data updated successfully.");
                    work.changeData(jname, jlocation,"Name",name.getText(),loggedUser);
                    work.changeData(jname, jlocation,"Location",location.getText(),loggedUser);
                    work.changeData(jname, jlocation,"Rate",rate.getText(),loggedUser);
                    work.changeData(jname, jlocation,"Hours",hours.getText(),loggedUser);

                    updateJobs();
                    
                    clearScreen();

                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
                    response.setText("Job data updated successfully. Will you edit another?");

                    createJobButtons(jobs,jobs3);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "*Rate and hours must be greater than or equal to zero.");
                }
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "*Rate and hours must be numeric values.");
            }
        }
    }
    
    //Submit Change Password
    public void submitChangePasswordForm()
    {
        if(isPasswordFormEmpty())
        {
            JOptionPane.showMessageDialog(null, "Fill out all fields.");
            response.setText("Fill out all fields.");
        }
        else
        {
            if(!String.valueOf(password.getPassword()).equals(loggedUser.getPassword()))
            {
                JOptionPane.showMessageDialog(null, "Incorrect password entered.");
                response.setText("Incorrect password.");
            }
            else
            {
                if(!String.valueOf(newPassword.getPassword()).equals(String.valueOf(newPassword2.getPassword())))
                {
                    JOptionPane.showMessageDialog(null, "New passwords do not match.");
                    response.setText("New passwords do not match");
                }
                else
                {
                    if(!verification.isValidPassword(String.valueOf(newPassword.getPassword())))
                    {
                        JOptionPane.showMessageDialog(null, passwordRules);
                        response.setText("Invalid password.");
                    }
                    else
                    {
                        loggedUser.setPassword(String.valueOf(newPassword.getPassword()));
                        work.changePassword(loggedUser);

                        JOptionPane.showMessageDialog(null, "Password has been changed.");
                        
                        clearScreen();
                        
                        enableAllManagerButtons();
                        
                        accountPage();
                    }
                }
            }
        }
    }
    
    //Submit Edit User Data
    public void submitEditUserData()
    {
        if(editAccountFormEmpty())
        {
            response.setText("You must ensure all fields are filled.");
            JOptionPane.showMessageDialog(null,"Fill out all fields.");  
        }
        else
        {
            if(!(verification.isValidName(firstName.getText()) && verification.isValidName(lastName.getText())))
            {
                response.setText("Invalid Name.");
                JOptionPane.showMessageDialog(null,"Name is invalid.");  
            }
            else
            {
                if(!verification.isValidEmail(email.getText()))
                {
                    response.setText("Invalid Email.");
                    JOptionPane.showMessageDialog(null,"Email is invalid.");  
                }
                else
                {
                    try
                    {
                        String cellular = "+1" + cell1.getText() + cell2.getText() + cell3.getText(); //Used for cell phone validity testing
                        Cell cellPhone = new Cell(cell1.getText(),cell2.getText(),cell3.getText()); //Actual cell number stored as user data
                        
                        if (!verification.isValidCellNumber(cellular))
                        {
                            response.setText("Invalid Cell Number.");
                            JOptionPane.showMessageDialog(null,"Cell number is invalid.");  
                        }
                        else
                        {
                            DateOfBirth dob = new DateOfBirth(day.getText(),month.getText(),year.getText());
                            if(!verification.isValidDOB(dob))
                            {
                                response.setText("Invalid Date of Birth.");
                                JOptionPane.showMessageDialog(null,"Date of birth is invalid.");  
                            }
                            else
                            {
                                loggedUser.setFirstName(firstName.getText());
                                loggedUser.setLastName(lastName.getText());
                                loggedUser.setEmail(email.getText());
                                loggedUser.setDateOfBirth(dob);
                                loggedUser.setCellNumber(cellPhone);
                                
                                if(work.storeUserData(loggedUser))
                                {
                                    JOptionPane.showMessageDialog(null, "User data has been updated.");
                                    response.setText("New user has been added.");                                            
                                    
                                    clearScreen();
                                    
                                    enableAllManagerButtons();

                                    accountPage();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Failed to update user data");
                                    response.setText("Failed to update user data.");  
                                }
                            }
                        }                    
                    }
                    catch(NumberFormatException e)
                    {
                        response.setText("Invalid Date of Birth");
                        JOptionPane.showMessageDialog(null,"Date of birth is invalid.");  
                    }          
                }
            }
        }
    }
    
    //Submit Delete Account
    public void submitDeleteAccount()
    {
        if(String.valueOf(password.getPassword()).trim().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please enter your password before submitting.");
        }
        else
        {
            if(String.valueOf(password.getPassword()).trim().equals(loggedUser.getPassword()))
            {
                work.deleteUser(loggedUser);
                
                JOptionPane.showMessageDialog(null, "Your account has been deleted.");
                
                deleteAccount.setEnabled(true);
                
                clearScreen();
                
                mainPage();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect password.");
                password.setText("");
            }
        }
    }
    
    //Key listener to implement submit on pressing enter in forms
    private class SubmitOnEnter implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent event)
        {
            if(event.getKeyCode() == KeyEvent.VK_ENTER) //If Enter key is pressed
            {
                if((email.isFocusOwner() || password.isFocusOwner()) && !accountOps[1].isEnabled())//In login form
                {
                    submitLogInForm();
                }
                
                if(firstName.isFocusOwner() || lastName.isFocusOwner() || email.isFocusOwner() || cell1.isFocusOwner() || cell2.isFocusOwner() || cell3.isFocusOwner()
                || day.isFocusOwner() || month.isFocusOwner() || year.isFocusOwner() || password.isFocusOwner())
                {
                    if(!accountOps[0].isEnabled())//In signup form
                    {
                        submitSignUpForm();
                    }
                    else
                    {
                        if(!personalInfoEdit.isEnabled())//In edit user account form
                        {
                            submitEditUserData();
                        }
                    }
                }                
                
                if((password.isFocusOwner() || newPassword.isFocusOwner() || newPassword2.isFocusOwner()) && !passwordEdit.isEnabled())//In change password form
                {
                    submitChangePasswordForm();
                }
                
                if(name.isFocusOwner() || location.isFocusOwner() || rate.isFocusOwner() || hours.isFocusOwner())
                {
                    if(!mainOps[0].isEnabled())//In add job form
                    {
                        submitAddJobForm();
                    }
                    else
                    {
                        if(!mainOps[1].isEnabled())//In edit job form
                        {
                            submitEditJobForm();
                        }
                    }
                }
                
                if(password.isFocusOwner() && !deleteAccount.isEnabled())//In delete account password validation
                {
                    submitDeleteAccount();
                }
                
                if(newHours1.isFocusOwner() || newHours2.isFocusOwner())//In add job hours form
                {
                    submitAddJobHours();
                }
            }
        }
        
        @Override
        public void keyReleased(KeyEvent arg0) {

        }
    
        @Override
        public void keyTyped(KeyEvent arg0) {
    
        }
    }
    
    //Radio Listener Actions
    private class RadioListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (showPasswordButton.isSelected())//Allows password to be visible
            {
                password.setEchoChar((char)0);
            }
            
            if (!showPasswordButton.isSelected())//Masks password with given echo character
            {
                password.setEchoChar((char)'*');
            }
        }
    }
    
    //Clicl listener actions for text fields
    private class ClickListener implements FocusListener
    {
        @Override
        public void focusGained(FocusEvent fe)
        {
            if(day.isFocusOwner() && day.getText().equals("DD"))
            {
                day.setText("");
                day.setForeground(Color.BLACK);
            }
            
            if(month.isFocusOwner() && month.getText().equals("MM"))
            {
                month.setText("");
                month.setForeground(Color.BLACK);
            }
            
            if(year.isFocusOwner() && year.getText().equals("YYYY"))
            {
                year.setText("");
                year.setForeground(Color.BLACK);
            }
            
            if(cell1.isFocusOwner() && cell1.getText().equals("XXX"))
            {
                cell1.setText("");
                cell1.setForeground(Color.BLACK);
            }
            
            if(cell2.isFocusOwner() && cell2.getText().equals("XXX"))
            {
                cell2.setText("");
                cell2.setForeground(Color.BLACK);
            }
            
            if(cell3.isFocusOwner() && cell3.getText().equals("XXXX"))
            {
                cell3.setText("");
                cell3.setForeground(Color.BLACK);
            }
        }
    
        @Override
        public void focusLost(FocusEvent fe)
        {
            if(cell1.getText().equals(""))
            {
                cell1.setForeground(Color.LIGHT_GRAY);
                cell1.setText("XXX");
            }
            
            if(cell2.getText().equals(""))
            {
                cell2.setForeground(Color.LIGHT_GRAY);
                cell2.setText("XXX");
            }
            
            if(cell3.getText().equals(""))
            {
                cell3.setForeground(Color.LIGHT_GRAY);
                cell3.setText("XXXX");
            }
            
            if(day.getText().equals(""))
            {
                day.setForeground(Color.LIGHT_GRAY);
                day.setText("DD");
            }
            
            if(month.getText().equals(""))
            {
                month.setForeground(Color.LIGHT_GRAY);
                month.setText("MM");
            }
            
            if(year.getText().equals(""))
            {
                year.setForeground(Color.LIGHT_GRAY);
                year.setText("YYYY");
            }
        }
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent event)
        {
            ButtonListener blisten = new ButtonListener();
            int i, j;
            
            //Sign Up User Form
            if (event.getSource() == accountOps[0])
            {
                signUpForm.add(firstNameLabel);
                signUpForm.add(firstName);
                signUpForm.add(lastNameLabel);
                signUpForm.add(lastName);
                signUpForm.add(emailLabel);
                signUpForm.add(email);
                signUpForm.add(passwordLabel);
                signUpForm.add(password);
                
                password.setEchoChar((char)'*');
                
                formBoxPanel.removeAll();
                formBoxPanel.add(signUpForm);
                formBoxPanel.add(showPasswordButton);
                
                signUpBox(formBoxPanel);
            }
            
            //Log in user form
            if(event.getSource() == accountOps[1])
            {
                password.setEchoChar((char)'*');
                
                logInForm.add(emailLabel);
                logInForm.add(email);
                logInForm.add(passwordLabel);
                logInForm.add(password);
                               
                logInBox(logInForm);
            }
            
            //Account Page
            if(event.getSource() == accountOps[2])
            {
                clearScreen();
                
                accountPage();
            }                 

            //Logout Button
            if (event.getSource() == accountOps[3])
            {
                int PromptResult = JOptionPane.showConfirmDialog(null,"Are you sure you want log out?","Log Out?", JOptionPane.YES_NO_OPTION);
                if(PromptResult==JOptionPane.YES_OPTION)
                 {
                     JOptionPane.showMessageDialog(null, "Logout Successful"); 
                     
                     clearScreen();
                     
                     clearSignUpForm();
                     
                     enableAllManagerButtons();
                     
                     accountOps[1].setEnabled(true);

                     mainPage();                     
                 }
            }
            
            //Add Job
            if (event.getSource() == mainOps[0])
            {
                enableAllAccountButtons();
                
                clearScreen();

                operation.add(opHeader);
                operation.add(response);
                opHeader.setText("ADD JOB");
                response.setText("Enter job information below.");
                
                clearJobFormFields();
      
                jobForm.add(jobNameLabel);
                jobForm.add(name);
                jobForm.add(locationLabel);
                jobForm.add(location);
                jobForm.add(rateLabel);
                jobForm.add(rate);
                jobForm.add(hoursLabel);
                jobForm.add(hours);

                operation.add(jobForm);
                operation.add(submit);
                operation.add(clear);
                
                setMainOpsStates(0);
                
                operation.revalidate();
                operation.repaint();
            }
            
            //Submit Add Job Form
            if (event.getSource() == submit && !mainOps[0].isEnabled())
            {
                submitAddJobForm();
            }
            
            //Clear Sign Up Form Entries
            if (event.getSource() == clear && !accountOps[0].isEnabled())
            {
                clearSignUpForm();
            }
            
            //Clear Log In Form Entries
            if (event.getSource() == clear && !accountOps[1].isEnabled())
            {
                email.setText("");
                password.setText("");
            }
            
            //Clear Job Form Entries
            if (event.getSource() == clear && !mainOps[0].isEnabled())
            {
                name.setText("");
                location.setText("");
                rate.setText("");
                hours.setText("");
                operation.revalidate();
                operation.repaint();
            }
            
            //Edit Job Data
            if (event.getSource() == mainOps[1] || (event.getSource() == cancel && !mainOps[1].isEnabled()))
            {
                if (jobs3.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You don't have any jobs at the moment.");
                }
                else
                {
                    enableAllAccountButtons();
                    
                    clearScreen();
                    
                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
                    opHeader.setText("EDIT JOB DATA");
                    response.setText("Which job will you edit?");
                    
                    setMainOpsStates(1);
                    
                    createJobButtons(jobs,jobs3);
                }
            }
            
            //Add Job Hours
            if (event.getSource() == mainOps[2] || (event.getSource() == cancel && !mainOps[2].isEnabled()))
            {
                if (jobs3.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You don't have any jobs at the moment.");
                }
                else
                {
                    enableAllAccountButtons();
                    
                    clearScreen();
    
                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
                    opHeader.setText("ADD JOB HOURS");
                    response.setText("Which job will you add hours to?");
                    
                    setMainOpsStates(2);
                    
                    createJobButtons(jobs,jobs3);
                }
            }
            
            //View Job Details
            if (event.getSource() == mainOps[5])
            {
                if (jobs3.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You don't have any jobs at the moment.");
                }
                else
                {
                    enableAllAccountButtons();
                    
                    jobInfoPanel = new JPanel(new GridLayout(0,2));
                    
                    jobPanel = new JPanel[jobs.length];
                                                                              
                    clearScreen();
                    
                    operation.add(opHeader);
    
                    opHeader.setText("VIEW JOB DETAILS");
    
                    setMainOpsStates(5);
    
                    df = new DecimalFormat("##.##");
                    
                    totalPay = 0;
                    totalHours = 0;
                    
                    descriptions = new JTextArea[jobs3.size()];

                    for (i = 0; i < jobs.length;i++)
                    {
                        jobs[i] = new JButton(concatenate(jobs3.get(i).getName(),jobs3.get(i).getLocation()));
                        jobs[i].setBackground(buttonColor);  
                        jobs[i].setBorderPainted(false);
                        descriptions[i] = new JTextArea(work.viewJob(separateName(jobs[i].getText()), separateLocation(jobs[i].getText()),loggedUser).toString());
                                           
                        jobPanel[i] = new JPanel(new GridLayout(0,1));
                        jobPanel[i].add(jobs[i]);
                        jobPanel[i].add(descriptions[i]);
                        jobPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
                        jobInfoPanel.add(jobPanel[i]); 
                        jobButtons.add(jobInfoPanel);
    
                        jobs[i].setPreferredSize(new Dimension(400,30));
                        jobs[i].setEnabled(false);
                        descriptions[i].setEditable(false);   
                        
                        totalPay += jobs3.get(i).getHours() * jobs3.get(i).getRate();                    
                        totalHours += jobs3.get(i).getHours();
                    }
                
                    tPayLabel.setText("Total Pay: $" + df.format(totalPay));
                    tHoursLabel.setText("Total Hours: " + df.format(totalHours));
                    totalsPanel.add(tPayLabel);
                    totalsPanel.add(tHoursLabel);
                    operation.add(totalsPanel);
    
                    jobButtons.revalidate();
                    jobButtons.repaint();
                    
                    jobDescriptionsPane = new JScrollPane(jobButtons,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jobDescriptionsPane.setPreferredSize(new Dimension(830,425));
    
                    operation.add(jobDescriptionsPane);
                    operation.revalidate();
                    operation.repaint();
                }
            }          
            
            //Delete Job
            if (event.getSource() == mainOps[4] || (event.getSource() == cancel && !mainOps[4].isEnabled()))
            {            
                if (jobs3.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You don't have any jobs at the moment.");
                }
                else
                {
                    enableAllAccountButtons();
                    
                    clearScreen();
                    
                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
                    opHeader.setText("DELETE JOB");
                    response.setText("Which job will you delete?");
     
                    setMainOpsStates(4);
                    
                    createJobButtons(jobs,jobs3);
                }
            }    
            
            //Reset job hours
            if (event.getSource() == mainOps[3] || (event.getSource() == cancel && !mainOps[3].isEnabled()))
            {
                if (jobs3.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You don't have any jobs at the moment.");
                }
                else
                {
                    enableAllAccountButtons();
                    
                    clearScreen();
                    
                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
                    opHeader.setText("RESET JOB HOURS");
                    response.setText("Which job's hours will you reset?");
    
                    setMainOpsStates(3);
         
                    createJobButtons(jobs,jobs3);
                }
            }
            
            //Job buttons for editing jobs
            for (i = 0; i < jobs.length; i++)
            {
                if (event.getSource() == jobs[i] && !mainOps[1].isEnabled())
                {     
                    jname = separateName(jobs[i].getText());
                    jlocation = separateLocation(jobs[i].getText());
                    
                    clearScreen();

                    operation.add(opHeader);
                    operation.add(response);
                    response.setText("Data sheet for " + jname);
                    
                    for(Job q: jobs3)
                    {
                        if (jname.toLowerCase().equals(q.getName().toLowerCase()) && jlocation.toLowerCase().equals(q.getLocation().toLowerCase()))
                        {
                            name.setText(q.getName());
                            location.setText(q.getLocation());
                            rate.setText("" + q.getRate());
                            hours.setText("" + q.getHours());
                        }
                    }
                    
                    jobForm.add(jobNameLabel);
                    jobForm.add(name);
                    jobForm.add(locationLabel);
                    jobForm.add(location);
                    jobForm.add(rateLabel);
                    jobForm.add(rate);
                    jobForm.add(hoursLabel);
                    jobForm.add(hours);
    
                    operation.add(jobForm);
                    operation.add(submit);
                    operation.add(cancel);                   

                    operation.revalidate();
                    operation.repaint();
                }
            }
            
            //Job buttons for add hours
            for (i = 0; i < jobs.length; i++)
            {
                if (event.getSource() == jobs[i] && !mainOps[2].isEnabled())
                {                
                    jname = separateName(jobs[i].getText());
                    jlocation = separateLocation(jobs[i].getText());                    
                    
                    jobButtons.removeAll();

                    newHours.add(hrsLabel);
                    newHours.add(newHours1);
                    newHours.add(minLabel);
                    newHours.add(newHours2);
                    newHours1.setText("");
                    newHours2.setText("");
                    
                    for (i = 0; i < jobs.length;i++)
                    {
                        jobs[i] = new JButton(concatenate(jobs3.get(i).getName(),jobs3.get(i).getLocation()));
                        jobs[i].setBackground(buttonColor);
                        jobButtons.add(jobs[i]);
                        jobs[i].addActionListener(blisten);
                        jobs[i].setPreferredSize(new Dimension(800,50));
                        
                        if (jobs[i].getText().equals(concatenate(jname,jlocation)))
                        {
                            response.setText("Enter number of hours to add.");
                            jobs[i].setEnabled(false);
                            jobs[i].setBackground(fadedButtonColor);
                            jobButtons.add(newHours);
                        }
                    }
                    
                    operation.add(submit);
                    operation.add(cancel);
                    operation.revalidate();
                    operation.repaint();
                }
            }
            
            //Submit button for editing jobs
            if (event.getSource() == submit && !mainOps[1].isEnabled())
            {
                submitEditJobForm();
            }

            //Submit button for adding hours
            if (event.getSource() == submit && !mainOps[2].isEnabled())
            {
                submitAddJobHours();
            }
            
            //Job Buttons for Delete Job
            for (i = 0; i < jobs.length; i++)
            {
                if (event.getSource() == jobs[i] && !mainOps[4].isEnabled())
                {
                    jname = separateName(jobs[i].getText());
                    jlocation = separateLocation(jobs[i].getText());

                    greyJobButton(jobs,jobs3,jname,jlocation);
                    
                    confirm.setText("Are you sure you want to delete this job?");
                    
                    operation.add(confirm);
                    operation.add(submit);
                    operation.add(cancel);
                    
                    operation.revalidate();
                    operation.repaint();
                }
            }
        
            //When job is getting deleted
            if (event.getSource() == submit && !mainOps[4].isEnabled())
            {
                for (i = 0; i < jobs3.size(); i++)
                {
                    if (jobs3.get(i).getName().toLowerCase().equals(jname.toLowerCase()) && jobs3.get(i).getLocation().toLowerCase().equals(jlocation.toLowerCase()))
                    {
                        jobs3.remove(i);
                        break;
                    }
                }            
                
                if(work.deleteJob(jobs3, loggedUser))
                {
                    updateJobs();
                    JOptionPane.showMessageDialog(null, concatenate(jname,jlocation) + " has been deleted.");
                    if(jobs3.size() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "You have no more jobs left.");
                    }
                    response.setText("Job has been deleted. Will you delete another?");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Operation failed.");
                    response.setText("Operation failed.");
                }
                
                clearScreen();
                
                if(jobs3.size() == 0)
                {
                    accountPage();
                }
                else
                {
                    operation.add(opHeader);
                    operation.add(response);
                    operation.add(jobButtons);
    
                    createJobButtons(jobs,jobs3);
                }
            }    
                                 
            //When job to be reset is selected
            for (i = 0; i < jobs.length; i++)
            {
                if (event.getSource() == jobs[i] && !mainOps[3].isEnabled())
                {
                    jname = separateName(jobs[i].getText());
                    jlocation = separateLocation(jobs[i].getText());
                    
                    greyJobButton(jobs,jobs3,jname,jlocation);
                   
                    confirm.setText("Are you sure you want to reset this job's hours?");
                    
                    operation.add(confirm);
                    operation.add(submit);
                    operation.add(cancel);
                    
                    operation.revalidate();
                    operation.repaint();
                }
            }
            
            //Designated job's hours are reset below
             if (event.getSource() == submit && !mainOps[3].isEnabled())
            {
                JOptionPane.showMessageDialog(null, "Hours for " + concatenate(jname,jlocation) + " have been set to zero.");
                
                clearScreen();
                
                operation.add(opHeader);
                operation.add(response);
                operation.add(jobButtons);
    
                work.changeData(jname,jlocation,"Hours","0",loggedUser);
                response.setText("Hours have been reset, will you reset another?");
                
                updateJobs();
                
                createJobButtons(jobs,jobs3);
            }                

            //Edit profile information
            if (event.getSource() == personalInfoEdit)
            {
                clearScreen();
                
                personalInfoEdit.setEnabled(false);
                
                operation.add(opHeader);
                operation.add(response);
                operation.add(personalInfoEdit);
                
                opHeader.setText("EDIT ACCOUNT");
                response.setText("Change account data below");
                
                firstName.setText(loggedUser.getFirstName());
                lastName.setText(loggedUser.getLastName());
                email.setText(loggedUser.getEmail());
                cell1.setText(loggedUser.getCellNumber().getCell1());
                cell1.setForeground(Color.BLACK);
                cell2.setText(loggedUser.getCellNumber().getCell2());
                cell2.setForeground(Color.BLACK);
                cell3.setText(loggedUser.getCellNumber().getCell3());
                cell3.setForeground(Color.BLACK);
                day.setText(loggedUser.getDob().getDay());
                day.setForeground(Color.BLACK);
                month.setText(loggedUser.getDob().getMonth());
                month.setForeground(Color.BLACK);
                year.setText(loggedUser.getDob().getYear());
                year.setForeground(Color.BLACK);
                
                editAccountForm.add(firstNameLabel);
                editAccountForm.add(firstName);
                editAccountForm.add(lastNameLabel);
                editAccountForm.add(lastName);
                editAccountForm.add(emailLabel);
                editAccountForm.add(email);
                editAccountForm.add(cellLabel);
                editAccountForm.add(cellPanel);
                editAccountForm.add(dateLabel);
                editAccountForm.add(dateOfBirth);
                
                operation.add(editAccountForm);
                operation.add(submit);
                operation.add(cancel);
                
                operation.revalidate();
                operation.repaint();
            }
          
            //Submit of updated account info
            if(event.getSource() == submit && !accountOps[2].isEnabled() && !personalInfoEdit.isEnabled())
            {
                submitEditUserData();
            }
            
            //Cancel update of account info
            if(event.getSource() == cancel && !accountOps[2].isEnabled() && !personalInfoEdit.isEnabled())
            {
                clearScreen();
                
                clearSignUpForm();
                
                personalInfoEdit.setEnabled(true);
                
                accountPage();
            }
            
            //Change password
            if (event.getSource() == passwordEdit)
            {
                clearScreen();
                
                passwordEdit.setEnabled(false);
                
                operation.add(opHeader);
                operation.add(response);
                operation.add(passwordEdit);
                
                changePasswordForm.add(oldPasswordLabel);
                changePasswordForm.add(password);
                changePasswordForm.add(newPasswordLabel);
                changePasswordForm.add(newPassword);
                changePasswordForm.add(newPasswordLabel2);
                changePasswordForm.add(newPassword2);
                
                operation.add(changePasswordForm);
                operation.add(submit);
                operation.add(cancel);
                
                operation.revalidate();
                operation.repaint();
            }
            
            //Changing the user's password
            if (event.getSource() == submit && !accountOps[2].isEnabled() && !passwordEdit.isEnabled())
            {
                submitChangePasswordForm();
            }
            
            //Cancel changing of password
            if(event.getSource() == cancel && !accountOps[2].isEnabled() && !passwordEdit.isEnabled())
            {
                clearScreen();
                
                accountOps[2].setEnabled(true);
                
                accountPage();
            }
                        
            //Change profile picture
            if (event.getSource() == pictureEdit)
            {
                changePicPanel.removeAll();
                
                tempPicLabel = new JLabel();
                tempPicLabel.setPreferredSize(new Dimension(800,200));
                tempPicLabel.setHorizontalAlignment(JLabel.CENTER);  
                setImage(loggedUser.getProfilePic(),tempPicLabel,200,200);
                
                changePicPanel.add(tempPicLabel);
                changePicPanel.add(browse);
                
                changePicBox(changePicPanel);
            }
            
            //Browse button to change profile picture
            if (event.getSource() == browse)
            {
                try
                {
                    fileChooser = new JFileChooser(new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
                    //fileChooser = new JFileChooser("Pictures");
                    //defaults to designated file path
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }   
                
                //Allows only files with jpg and png extensions shown
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files (^.jpg, ^.png)","jpg","png"));
                
                int returnValue = fileChooser.showOpenDialog(null);
                
                if (returnValue == JFileChooser.APPROVE_OPTION) 
                {
                    selectedFile = fileChooser.getSelectedFile();
                    //setImage("Pictures\\\\" + selectedFile.getName(), profilePicLabel, 200, 200);
                    setImage(selectedFile.getName(), tempPicLabel, 200, 200);
                    //setImage(selectedFile.getAbsolutePath(), profilePicLabel);
                }
            }

            //Delete logged user account
            if(event.getSource() == deleteAccount)
            {
                deleteAccount.setEnabled(false);
                
                response.setText("Enter your password to confirm deletion.");
                
                operation.remove(personalInfoEdit);
                operation.remove(passwordEdit);
                operation.remove(pictureEdit);
                operation.remove(createReport);
                operation.remove(viewReports);
                
                passwordPanel.add(passwordLabel);
                passwordPanel.add(password);
                
                operation.add(passwordPanel);
                operation.add(submit);
                operation.add(cancel);
                
                operation.revalidate();
                operation.repaint();
            }
            
            //User account gets deleted
            if(event.getSource() == submit && !deleteAccount.isEnabled())
            {
                submitDeleteAccount();
            }
            
            //Cancel account deletion
            if(event.getSource() == cancel && !deleteAccount.isEnabled())
            {
                clearScreen();
                
                deleteAccount.setEnabled(true);
                
                accountPage();
            }
            
            //Create Report
            if(event.getSource() == createReport)
            {
                if(jobs3.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You have no jobs to create reports on.");
                }
                else
                {
                    if(confirmationBox("Create report for current jobs?") == JOptionPane.YES_OPTION)
                    {
                        Report report = new Report(jobs3);
                        if(work.storeReport(loggedUser,report))
                        {
                            JOptionPane.showMessageDialog(null, "New report has been created.");
                            updateReports();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Failed to create report.");
                        }
                    }
                }
            }
            
            //Display Reports
            if(event.getSource() == viewReports)
            {
                if(reports.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You don't have any reports at the moment.");
                }
                else
                {
                    clearScreen();
                    
                    reportScreen();
                }
            }
            
            //When report to view is selected
            for (i = 0; i < reportArray.length; i++)
            {
                if(event.getSource() == reportArray[i])
                {
                    for(j = 0; j < reports.size(); j++)
                    {
                        if(reportArray[i].getText().equals(reports.get(j).getDate()))
                        {
                            deleteReport.setEnabled(true);
                            
                            greyReportButton(reportArray,reportArray[i].getText());
                            reportDescription.setText(reports.get(j).toString());
                            operation.revalidate();
                            operation.repaint();
                        }
                    }
                }
            }
            
            //Delete selected report
            if(event.getSource() == deleteReport)
            {
                if(confirmationBox("Delete selected report?") == JOptionPane.YES_OPTION)
                {
                    for(i = 0; i < reportArray.length; i++)
                    {
                        if(!reportArray[i].isEnabled())
                        {
                             for(j = 0; j < reports.size(); j++)
                            {
                                if(reports.get(j).getDate().equals(reportArray[i].getText()))
                                {
                                    reports.remove(j);
                                    break;
                                }
                            }
                            if(work.rewriteReports(loggedUser,reports))
                            {
                                JOptionPane.showMessageDialog(null, "Report has been deleted.");
                            }
                            updateReports();
                            break;
                        }
                    }
                }
                clearScreen();
                
                reportScreen();
            }
            
            //Cancel view reports
            if((event.getSource() == cancel) && !viewReports.isEnabled())
            {
                clearScreen();
                
                viewReports.setEnabled(true);
                
                accountPage();
            }
        }
    }
}