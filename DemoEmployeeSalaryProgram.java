/**
 * Filename : LabTestSect2
 * Program Purpose : to process data from an input file containing employee information, calculate their annual salaries based on certain criteria, and then output this processed data into another file
 * Programmer : Najihah Husna
 * Date : 14 March 2024
 */
//import the respective packages
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

//Driver class
public class DemoEmployeeSalaryProgram
{
   //Driver method
   public static void main(String[]args) throws IOException
    {
        //Istantiate the object of DecimalFormat
        DecimalFormat dF = new DecimalFormat("0.00");
        
        //try block
        try
        {
            //Set the input/output file
            //input file
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));
            
            
            //2 output files
            PrintWriter fileoutput1 = new PrintWriter(new FileWriter("employeeData.txt"));
            
            
            //Declare the variables
            String inputData = null;
            String employName = "";
            double employSalary = 0.000;
            int emploWorkingYear = 0;
            
            //variable for top performing employee
            String top_employName = "";
            double top_employSalary = 0.000;
            int top_emploWorkingYear = 0;
            
            //variable for newest employee
            String latest_employName = "";
            double latest_employSalary = 0.00;
            int latest_emploWorkingYear = 0;
            
            //Write the title header list of the employees
            fileoutput1.println("----------------List of the Employees-----------------------");
            
            while((inputData = inputFile.readLine()) != null)
            {
                //Instantiate the object reference of the StringTokenizer class
                //to read the string line (input data)
                StringTokenizer sT = new StringTokenizer(inputData,"|");
                
                //Break into tokens and assign to the appropriate variables
                employName= sT.nextToken();
                employSalary = Double.parseDouble(sT.nextToken());
                emploWorkingYear = Integer.parseInt(sT.nextToken());
                
                //to calculate the salary
                double annualSalary = employSalary + (employSalary * 0.05);
                
                //to test for the negative number
                if(employSalary < 0 || emploWorkingYear <0)
                  throw new IllegalArgumentException();
                
                //find top performing employee
                if(annualSalary > top_employSalary){
                    top_employName = employName;
                    top_employSalary = annualSalary;
                    top_emploWorkingYear = emploWorkingYear;
                }
                //find the employee with the least years of service
                if(latest_emploWorkingYear == 0 || emploWorkingYear < latest_emploWorkingYear){
                    latest_employName = employName;
                    latest_employSalary = annualSalary;
                    latest_emploWorkingYear = emploWorkingYear;
                    
                }
                
                //store list of employees
                String employData = employName+"\t\t RM "+annualSalary+"\t\t "+emploWorkingYear+" years";
                fileoutput1.println(employData);
    
            }
            //top performing employee
            fileoutput1.println("\n\n -------- Top Performing Employee Details -------");
            String top_employData = top_employName+"\t\t RM "+top_employSalary+"\t\t "+top_emploWorkingYear+" years";
            fileoutput1.println(top_employData);
            //display top performing employee
            JOptionPane.showMessageDialog(null,"--------Top performing Employee Details ---------\n"+top_employData);
            
            //latest employee
            fileoutput1.println("\n\n -------- Details of Employee with the least years of service -------");
            String latest_employData = latest_employName+"\t\t RM "+latest_employSalary+"\t\t "+latest_emploWorkingYear+" years";
            fileoutput1.println(latest_employData);
            JOptionPane.showMessageDialog(null,"--------Details of Employee with the least years of service ---------\n"+latest_employData);

            //close all files
            inputFile.close();
            fileoutput1.close();
            
        }//end of try block
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input!";
            JOptionPane.showMessageDialog(null, output);
        }
    }
}