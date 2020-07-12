package oop_assignment3;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee{
	String firstName;
	String lastName;
	String sSN;          // Social Security Number
	
	public Employee(String firstName, String lastName, String sSN) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sSN = sSN;
	}
	
//	GET METHODS
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getsSN() {
		return sSN;
	}
	
//	ABSTRACT METHOD
	public abstract double earnings();
	
	public String toString() {
		return "Employee-Name: " + getFirstName() + " " + getLastName() + "\nEmployee-SSN " + getsSN();
	}
}

class SalariedEmployee extends Employee{ 
	
	public SalariedEmployee(String firstName, String lastName, String sSN) {
		super(firstName, lastName, sSN);
		
	}
	@Override
	public double earnings() {
		return weeklySalary();
	}
	
	public double weeklySalary() {
		return 800.0;
	}
	
	@Override
	public String toString() {
		return "Salaried_Employee-Name: " + super.getFirstName() + " " + super.getLastName() + 
				"\nSalaried_Employee-SSN: " + super.getsSN() +
				"\nSalaried_Employee-Salary: " + earnings();
	}
}

class HourlyEmployee extends Employee{
	double hourlyWage;
	int hoursWorked;
	
	public HourlyEmployee(String firstName, String lastName, String sSN, double hourlyWage, int hoursWorked) {
		super(firstName, lastName, sSN);
		this.hourlyWage = hourlyWage;
		this.hoursWorked = hoursWorked;
	}
	
//	GET METHODS
	public double getHourlyWage() {
		return hourlyWage;
	}
	public int getHoursWorked() {
		return hoursWorked;
	}
	
	@Override
	public double earnings() {
		if (hoursWorked<=40) {
			return hourlyWage * Double.valueOf(hoursWorked);
		}
		else if (hoursWorked>40) {
			return (40 * hourlyWage ) + (hoursWorked - 40) * hourlyWage * 1.5;
		}
		
		return 0.0;
	}
	
	@Override
	public String toString() {
		return "HourlyEmployee-Name: " + super.getFirstName() + " " + super.getLastName() + 
				"\nHourlyEmployee-SSN: " + super.getsSN() + 
				"\nHourlyEmployee-HourlyWage: " + getHourlyWage() +
				"\nHourlyEmployee-HoursWorked: " + getHoursWorked() +
				"\nHourlyEmployee-Salary: " + earnings();
	}
}

class CommissionEmployee extends Employee{
	double grossSales;
	int commissionRate;
	
	public CommissionEmployee(String firstName, String lastName, String sSN, double grossSales, int commissionRate) {
		super(firstName, lastName, sSN);
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}
	
//	Getters
	public int getCommissionRate() {
		return commissionRate;
	}
	public double getGrossSales() {
		return grossSales;
	}
	
	@Override
	public double earnings() {
		return (commissionRate * grossSales)/100;
	}
	
	@Override
	public String toString() {
		return "CommissionEmployee-Name: " + super.getFirstName() + " " + super.getLastName() + 
				"\nCommissionEmployee-SSN: " + super.getsSN() + 
				"\nCommissionEmployee-CommissionRate: " + getCommissionRate() +
				"\nCommissionEmployee-GrossSales: " + getGrossSales() +
				"\nCommissionEmployee-Salary: " + earnings();
	}
}

class BasePlusCE extends CommissionEmployee{
	double basicSalary;
	
	public BasePlusCE(String firstName, String lastName, String sSN, 
			double grossSales, int commissionRate, double basicSalary) {
		
		super(firstName, lastName, sSN, grossSales, commissionRate);
		this.basicSalary = basicSalary;
	}
	
//	Getters
	public double getBasicSalary() {
		return basicSalary;
	}
	
	@Override
	public double earnings() {
		return (super.getCommissionRate() * super.getGrossSales())/100 + newBaseSalary(basicSalary) ;
	}
	
	public double newBaseSalary(double basicSalary) {
		double newBaseSalary;
		newBaseSalary = basicSalary + (basicSalary * 0.1);
		return newBaseSalary;
	}
	@Override
	public String toString() {
		return  "BasePlusCE-Name: " + super.getFirstName() + " " + super.getLastName() + 
				"\nBasePlusCE-SSN: " + super.getsSN() + 
				"\nBasePlusCE-CommissionRate: " + super.getCommissionRate() +
				"\nBasePlusCE-GrossSales: " + super.getGrossSales() +
				"\nBasePlusCE-BaseSalary: " + basicSalary +
				"\nNew Base Salary with 10% increase: " + newBaseSalary(basicSalary) +
				"\nBasePlusCE-Salary: " + earnings();
	}
	
}
public class Main {
	
	public static void main(String[] args) {
		String firstNameStr;
		String lastNameStr;
		String sSNStr;
		double hourlyWageDbl;
		int hoursWorkedInt;
		double grossSalesDbl;
		int commissionRateInt;
		double basicSalaryDbl;
		
		ArrayList<Employee> list = new ArrayList<>();
        Scanner in  = new Scanner(System.in);
       boolean flag=true;
       
       System.out.println("Hello!" +
       					"\nTo get details of Employees " +
    		   			"\nPress 1 -> Salaried Employee " +
       					"\nPress 2 -> Commission Employee " +
    		   			"\nPress 3 -> Hourly Employee " + 
       					"\nPress 4 -> Base Plus Commission Employee ");
       do
        {
            int i=in.nextInt();
            if(i==1) {
            	System.out.println("Greeting Salaried Employee!" +
            	"\nPlease Enter your FirstName: ");
            	firstNameStr = in.next();
            	System.out.println("Please Enter your LastName: ");
            	lastNameStr = in.next();
            	System.out.println("Please Enter your Social Security Number: ");
            	sSNStr = in.next();
            	
                list.add(new SalariedEmployee(firstNameStr, lastNameStr, sSNStr));
            }
            else if (i==2) {
            	System.out.println("Greeting Commission Employee!" +
                    	"\nPlease Enter your FirstName: ");
                    	firstNameStr = in.next();
                    	System.out.println("Please Enter your LastName: ");
                    	lastNameStr = in.next();
                    	System.out.println("Please Enter your Social Security Number: ");
                    	sSNStr = in.next();
                    	System.out.println("Please Enter your Gross Sales: ");
                        grossSalesDbl = in.nextDouble();
                        System.out.println("Please Enter your Commission Rate: ");
                        commissionRateInt = in.nextInt();
                        
                list.add(new CommissionEmployee(firstNameStr, lastNameStr, sSNStr, grossSalesDbl, commissionRateInt));
            }
            else if (i==3) {
            	System.out.println("Greeting Hourly Employee!" +
                    	"\nPlease Enter your FirstName: ");
                    	firstNameStr = in.next();
                    	System.out.println("Please Enter your LastName: ");
                    	lastNameStr = in.next();
                    	System.out.println("Please Enter your Social Security Number: ");
                    	sSNStr = in.next();
                    	System.out.println("Please Enter your Hourly Wage: ");
                        hourlyWageDbl = in.nextDouble();
                        System.out.println("Please Enter your Hours Worked: ");
                        hoursWorkedInt = in.nextInt();
                        
                list.add(new HourlyEmployee(firstNameStr, lastNameStr, sSNStr, hourlyWageDbl, hoursWorkedInt));
            }
            else if (i==4) {
            	System.out.println("Greeting Base Plus Commission Employee!" +
                    	"\nPlease Enter your FirstName: ");
                    	firstNameStr = in.next();
                    	System.out.println("Please Enter your LastName: ");
                    	lastNameStr = in.next();
                    	System.out.println("Please Enter your Social Security Number: ");
                    	sSNStr = in.next();
                    	System.out.println("Please Enter your Gross Sales: ");
                        grossSalesDbl = in.nextDouble();
                        System.out.println("Please Enter your Commission Rate: ");
                        commissionRateInt = in.nextInt();
                        System.out.println("Please Enter your Basic Salary: ");
                        basicSalaryDbl = in.nextDouble();
                        
                    list.add(new BasePlusCE(firstNameStr, lastNameStr, sSNStr, grossSalesDbl, commissionRateInt, basicSalaryDbl));
            }
            else
                System.out.println("Wrong option");
                    
            System.out.println("Do u want to continue ...");
            char c = in.next().charAt(0);
            if (c=='y') flag=true;
            else  flag=false;
            
            
        }
       
       while(flag);
       
       for(Employee temp: list)
            System.out.println(temp.toString()+"\n");
        
    }
		
}


