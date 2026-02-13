package RetirementCalculator;

import java.util.Scanner;

public class RetirementCalculator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Retirement Growth Simulator");
		System.out.println("----------------------------");
		
		boolean runAgain;
		
		do {
			int currentAge = readIntInRange(sc, "Current Age (18-100): ", 18, 100);
			int retirementAge = readIntInRange(sc, "Retirement Age: ", currentAge + 1, 100);
			
			double currentBalance = readDoubleInRange(sc, "Current Balance: ", 0, Double.MAX_VALUE);
			double annualContribution = readDoubleInRange(sc, "Annual Contribution: ", 0, Double.MAX_VALUE);
			double annualRate = readDoubleInRange(sc, "Annual Interest Rate (0-30%): ", 0, 30);
			double contributionIncrease = readDoubleInRange(sc, "Annual Contribution Increase (0-20%): ", 0, 20);
			
			int compoundingChoice = readCompoundingChoice(sc);
			
			runSimulation(currentAge, retirementAge, currentBalance,
					annualContribution, annualRate,
					compoundingChoice, contributionIncrease);
			
			runAgain = askRunAgain(sc);
			
		} while (runAgain);
		
		sc.close();
		System.out.println("Goodbye!");
	}
public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
	while (true) {
		System.out.print(prompt);
		try {
			int value = Integer.parseInt(sc.nextLine());
			
			if (value >= min && value <= max) {
				return value;
			} else {
				System.out.println("Value must be between " + min + " and " + max + ".");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a whole number.");
		
		}
	}
}
public static double readDoubleInRange(Scanner sc, String prompt, double min, double max) {
	while (true) {
		System.out.print(prompt);
		try {
			double value = Double.parseDouble(sc.nextLine());
			
			if (value >= min && value <= max) {
				return value;
			} else {
				System.out.println("Value must be between " + min + " and " + max + ".");
			}
		}catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			
			}
			}
		}
public static int readCompoundingChoice(Scanner sc) {
	while (true) {
		System.out.println("Compounding Frequency: ");
		System.out.println("1 = Annually");
		System.out.println("2 = Monthly");
		System.out.println("3 = Daily");
		System.out.println("Choose (1-3): ");
		
		try {
			int choice = Integer.parseInt(sc.nextLine());
			
			if (choice >= 1 && choice <= 3) {
				return choice;
			} else {
				System.out.println("Please enter 1, 2, or 3.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
			}
		}
	}

public static void runSimulation(int currentAge, int retirementAge,
        double balance, double annualContribution,
        double annualRate, int compoundingChoice,
        double contributionIncrease) {

int periodsPerYear;

if (compoundingChoice == 1)
periodsPerYear = 1;
else if (compoundingChoice == 2)
periodsPerYear = 12;
else
periodsPerYear = 365;

double r_period = (annualRate / 100) / periodsPerYear;

double totalContributions = 0;
double totalInterest = 0;

System.out.println("\nYear-by-Year Projection");
System.out.println("--------------------------------------------------------------------------");
System.out.printf("%-5s %-15s %-15s %-15s %-15s%n",
"Age", "Start Balance", "Contributions", "Interest", "End Balance");
System.out.println("--------------------------------------------------------------------------");

int years = retirementAge - currentAge;

for (int year = 1; year <= years; year++) {

double startBalance = balance;
double yearlyContributionTotal = 0;

double periodContribution = annualContribution / periodsPerYear;

for (int period = 1; period <= periodsPerYear; period++) {

balance += periodContribution;
yearlyContributionTotal += periodContribution;

balance = balance * (1 + r_period);
}

double interestEarned = balance - startBalance - yearlyContributionTotal;

totalContributions += yearlyContributionTotal;
totalInterest += interestEarned;

int ageAtEnd = currentAge + year;

System.out.printf("%-5d $%-14.2f $%-14.2f $%-14.2f $%-14.2f%n",
ageAtEnd, startBalance, yearlyContributionTotal,
interestEarned, balance);

// Increase contribution for next year
annualContribution = annualContribution * (1 + contributionIncrease / 100);
}

System.out.println("--------------------------------------------------------------------------");

System.out.println("\nSummary");
System.out.printf("Total Contributions: $%.2f%n", totalContributions);
System.out.printf("Total Interest Earned: $%.2f%n", totalInterest);
System.out.printf("Ending Balance at Age %d: $%.2f%n", retirementAge, balance);
}
public static boolean askRunAgain(Scanner sc) {
    while (true) {
        System.out.print("\nRun another simulation? (Y/N): ");
        String input = sc.nextLine().trim().toUpperCase();

        if (input.equals("Y"))
            return true;
        else if (input.equals("N"))
            return false;
        else
            System.out.println("Please enter Y or N.");
    }
}

}



	



