import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class PfFinalProject{
    /*
    Bank management system variables:
  
    Customer information: account numbers, names, ages, CNICs, father's names, PINs, bank names, branch codes, and account balances.
    Transaction information: dates, types, and amounts for each customer, with a maximum of 100 transactions per customer.
    Manager information: salary and number of accounts opened.
  
    These variables are used to store and manage customer and transaction data in the bank management system.
    These are kept static so that there scope remains throughout the whole class
 */
    
    static final int MAX_CUSTOMERS = 100;
    static int customerCount = 0;
    static int[] accountNumbers = new int[MAX_CUSTOMERS];
    static String[] names = new String[MAX_CUSTOMERS];
    static int[] ages = new int[MAX_CUSTOMERS];
    static String[] cnics = new String[MAX_CUSTOMERS];
    static String[] fatherNames = new String[MAX_CUSTOMERS];
    static int[] pins = new int[MAX_CUSTOMERS];
    static String[] bankNames = new String[MAX_CUSTOMERS];
    static int[] branchCodes = new int[MAX_CUSTOMERS];
    static double [] accountBalances = new double[MAX_CUSTOMERS];
    static final int MAX_TRANSACTIONS = 100;
    static LocalDate[][] transactionDates = new LocalDate[MAX_CUSTOMERS][MAX_TRANSACTIONS];
    static String[][] transactionTypes = new String[MAX_CUSTOMERS][MAX_TRANSACTIONS];
    static double[][] transactionAmounts = new double[MAX_CUSTOMERS][MAX_TRANSACTIONS];
    static int[] transactionCounts = new int[MAX_CUSTOMERS];
    static int managerSalary = 200000;
    static int numAccountsOpenedByManager = 0;
	
    public static void main(String[] args) {
        //Main entry point
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
		
        //Loading transactions and customers so that there data is saved respectively
        loadCustomerData();
		loadTransactions();
       
        //Using try catch block so that in case of any wrong input there is no crashing
        /*
        * This block of code is the main entry point of the bank management system.
        * It prompts the user to select their bank and branch, and then their role (CEO, Manager, or Customer).
        * 
        * The user is first asked to select their bank (HBL or MCB) and then their branch.
        * The branch code is stored in the 'branchCode' variable.
        * 
        * Once the bank and branch are selected, the user is asked to select their role.
        * Based on the user's role, the program calls the corresponding menu function:
        *   - ceoMenu() for the CEO
        *   - managerMenu() for the Manager
        *   - customerMenu() for the Customer
        * 
        * The program uses try-catch blocks to handle invalid input and exceptions.
        * It also uses a finally block to close the scanner object.
        */
        try {
            System.out.println("Welcome to Our Banks!");
            String bankName = "";
            int branchCode = 0;
            while (true) {
                System.out.println("Select your bank:");
                System.out.println("1. HBL");
                System.out.println("2. MCB");
                int bankChoice = 0;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        bankChoice = scanner.nextInt();
                        
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number (1 or 2).");
                        scanner.next(); // Clear the invalid input
                    }
                }
                scanner.nextLine(); // Consume newline
                switch (bankChoice) {
                    case 1:
                        bankName = "HBL";
                        System.out.println("Which branch of HBL would you like to open your account in?");
                        System.out.println("1. Branch 1 (Code: 1267)");
                        System.out.println("2. Branch 2 (Code: 2345)");
                        System.out.println("3. Branch 3 (Code: 4567)");
                        int hblChoice = 0;
                        validInput = false;
                        while (!validInput) {
                            try {
                                hblChoice = scanner.nextInt();
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                                scanner.next(); // Clear the invalid input
                            }
                        }
                        scanner.nextLine(); // Consume newline
                        switch (hblChoice) {
                            case 1:
                                branchCode = 1267;
                                break;
                            case 2:
                                branchCode = 2345;
                                break;
                            case 3:
                                branchCode = 4567;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                continue;
                        }
                        break;
                    case 2:
                        bankName = "MCB";
                        System.out.println("Which branch of MCB would you like to open your account in?");
                        System.out.println("1. Branch 1 (Code: 7890)");
                        System.out.println("2. Branch 2 (Code: 8901)");
                        System.out.println("3. Branch 3 (Code: 9012)");
                        int mcbChoice = 0;
                        validInput = false;
                        while (!validInput) {
                            try {
                                mcbChoice = scanner.nextInt();
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                                scanner.next(); // Clear the invalid input
                            }
                        }
                        scanner.nextLine(); // Consume newline
                        switch (mcbChoice) {
                            case 1:
                                branchCode = 7890;
                                break;
                            case 2:
                                branchCode = 8901;
                                break;
                            case 3:
                                branchCode = 9012;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                continue;
                        }
                        break;
                    default:
                        System.out.println("Invalid bank choice. Try again.");
                        continue;
                }
                break;
            }
            System.out.println("Select your role:");
            System.out.println("1. CEO");
            System.out.println("2. Manager");
            System.out.println("3. Customer");
            int userType = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    userType = scanner.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                    scanner.next(); // Clear the invalid input
                }
            }
            scanner.nextLine(); // Consume newline
            switch (userType) {
                case 1:
                    ceoMenu(scanner);
                    break;
                case 2:
                    managerMenu(scanner, rand, bankName, branchCode);
                    break;
                case 3:
                    customerMenu(scanner, rand, bankName, branchCode);
                    break;
                default:
                    System.out.println("Invalid user type. Exiting program.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /*
    * The 'ceoMenu' method displays the CEO menu and handles user input.
    * It contains a while loop that continues until the user selects option 3 (Exit).
    * 
    * The method uses a try-catch block to handle invalid input and exceptions.
    * If the user enters invalid input, the program catches the 'InputMismatchException' and prompts the user to enter a number.
    * 
    * If an unexpected error occurs, the program catches the 'Exception' and prints an error message.
    * 
    * Based on the user's choice, the method calls the corresponding function:
    *   - return for option 1 (CEO views all the accounts)
    *   - return for option 2 (Exit)
    */
    public static void ceoMenu(Scanner scanner) {
        while (true) {
            try {
                System.out.println("\nCEO Menu:");
                System.out.println("1. View All Data");			
                System.out.println("2. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:

                         viewAllAccounts();
                         viewAllTransactions();
                        
                        break;
                    case 2:
                       
                        System.out.println("Exiting...");
                        return;
                        
                       
                   
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume newline
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } 
        
        }
    }
    /*
    * The 'managerMenu' method displays the Manager menu and handles user input.
    * It contains a while loop that continues until the user selects option 6 (Exit).
    * 
    * The method uses a try-catch block to handle invalid input and exceptions.
    * If the user enters invalid input, the program catches the 'InputMismatchException' and prompts the user to enter a number.
    * 
    * If an unexpected error occurs, the program catches the 'Exception' and prints an error message.
    * 
    * Based on the user's choice, the method calls the corresponding function:
    *   - openAccount() for option 1 (Open Account)
    *   - viewAllAccounts() for option 2 (View All Accounts)
    *   - viewTransactions() and viewAllTransactions() and viewManagerTransactions() for option 3 (View Transactions)
    *   - loadManagerData() for option 4 (Load Data)
    *   - saveManagerData() for option 5 (Save Data)
    *   - return for option 6 (Exit)
    */

    public static void managerMenu(Scanner scanner, Random rand, String bankName, int branchCode) {
        while (true) {
            try{
			 System.out.println("\nManager Menu:");
                System.out.println("1. Open Account");
                System.out.println("2. View All Accounts");
                System.out.println("3. View Transactions");
				System.out.println("4. Load Data");
				System.out.println("5. Save Data");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        openAccount(scanner, rand, bankName, branchCode);
                        break;
                    case 2:
                        
                        viewAllAccounts();
                        break;
                    case 3:
                        viewTransactions(scanner);
                        viewAllTransactions();
                        viewManagerTransactions();
                        break;
                    case 4:
						loadManagerData();
						break;
					case 5:
						saveManagerData();
						break;
					case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                    
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } 
        }
	}
       /*
    * The 'customerMenu' method displays the Customer menu and handles user input.
    * It contains a while loop that continues until the user selects option 6 (Exit).
    * 
    * The method uses a try-catch block to handle invalid input and exceptions.
    * If the user enters invalid input, the program catches the 'InputMismatchException' and prompts the user to enter a number.
    * 
    * If an unexpected error occurs, the program catches the 'Exception' and prints an error message.
    * 
    * Based on the user's choice, the method calls the corresponding function:
    *   - openAccount() for option 1 (Open Account)
    *   - viewAccount() for option 2 (View Account)
    *   - addTransaction() and saveTransactions() for option 3 (Add Transactions)
    *   - viewTransactions() for option 4 (View Transactions)
    *   - transferFunds() and saveTransactions() for option 5 (Transfer Funds)
    *   - return for option 6 (Exit)
    * 
    * The method also saves the transactions after adding or transferring funds.
    */
	
    public static void customerMenu(Scanner scanner, Random rand, String bankName, int branchCode) {
        while (true) {
            try {
                System.out.println("\nCustomer Menu:");
                System.out.println("1. Open Account");
                System.out.println("2. View Account");
                System.out.println("3. Add Transactions");
                System.out.println("4. View Transactions");
                System.out.println("5. Transfer Funds"); // Added transfer funds option
                System.out.println("6. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        openAccount(scanner, rand, bankName, branchCode);
                        break;
                    case 2:
                        viewAccount(scanner);
                        break;
                    case 3:
                        addTransaction(scanner);
						saveTransactions();
                        break;
                    case 4:
                        viewTransactions(scanner);
                        break;
                    case 5:
                        transferFunds(scanner);
						saveTransactions();
                        break;
                    case 6:
					     saveTransactions();
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } 
        }
    }
  
    /*
    * The 'displayCEOFile' method reads and displays the contents of the "ceo.txt" file.
    * 
    * It uses a try-with-resources statement to create a BufferedReader object, which is used to read the file line by line.
    * 
    * The method catches and handles two types of exceptions:
    *   - IOException: This exception is thrown if there is an error reading the file, such as if the file does not exist.
    *   - Exception: This is a general exception that catches any other unexpected errors that may occur.
    * 
    * If an exception is caught, the method prints an error message to the console.
    * 
    * If no exceptions are thrown, the method prints each line of the file to the console.
    */
    public static void displayCEOFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ceo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading CEO data: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    /*
    * The 'displaytransactionsFile' method reads and displays the contents of the "all_transactions.txt" file.
    * 
    * This file is assumed to contain a record of all transactions that have taken place in the system.
    * 
    * It uses a try-with-resources statement to create a BufferedReader object, which is used to read the file line by line.
    * 
    * The method catches and handles two types of exceptions:
    *   - IOException: This exception is thrown if there is an error reading the file, such as if the file does not exist.
    *   - Exception: This is a general exception that catches any other unexpected errors that may occur.
    * 
    * If an exception is caught, the method prints an error message to the console.
    * 
    * If no exceptions are thrown, the method prints each line of the file to the console, effectively displaying all transactions.
    */
    public static void displaytransactionsFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("all_transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions data: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    /*
    * The 'saveManagerActivities' method writes the manager's activities to a file named "manager_activities.txt".
    * 
    * It creates a FileWriter object to write to the file.
    * 
    * The method writes the following information to the file:
    *   - A header indicating that the file contains manager activities
    *   - The number of accounts opened by the manager
    *   - The account numbers of the accounts opened by the manager
    * 
    * The method then closes the FileWriter object to ensure that the data is written to the file.
    * 
    * If an IOException occurs during the writing process, the method catches the exception and prints an error message to the console.
    * 
    * If the writing process is successful, the method prints a success message to the console.
    * 
   
    */
    public static void saveManagerActivities() {
        try {
            FileWriter writer = new FileWriter("manager_activities.txt");
            writer.write("Manager Activities:\n");
            writer.write("Number of accounts opened: " + customerCount + "\n");
            writer.write("Account numbers opened: \n");
            for (int i = 0; i < customerCount; i++) {
                if(accountNumbers[i]!=0){
                writer.write(accountNumbers[i] + "\n ");
                }

            }
            
            writer.write("\n");
            
            writer.close();
            System.out.println("Manager activities data written to manager_activities.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    /*
    * The 'displayManagerActivities' method reads and displays the contents of the "manager_activities.txt" file.
    * 
    * This file is assumed to contain a record of the manager's activities, such as the number of accounts opened and the account numbers.
    * 
    * The method uses a try-with-resources statement to create a BufferedReader object, which is used to read the file line by line.
    * 
    * The method then prints each line of the file to the console, effectively displaying the manager's activities.
    * 
    * The method catches and handles two types of exceptions:
    *   - IOException: This exception is thrown if there is an error reading the file, such as if the file does not exist.
    *   - Exception: This is a general exception that catches any other unexpected errors that may occur.
    * 
    * If an exception is caught, the method prints an error message to the console.
    * 
    * If no exceptions are thrown, the method successfully displays the manager's activities to the console.
    */
public static void displayManagerActivities() {
        try (BufferedReader reader = new BufferedReader(new FileReader("manager_activities.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading manager data: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }
  
    /*
    * The openAccount method is used to open a new account for a customer.
    * 
    * It takes four parameters: a Scanner object, a Random object, a String representing the bank name, and an int representing the branch code.
    * 
    * The method first checks if the maximum number of customers has been reached.
    * If so, it prints a message and returns. This is done to prevent the system from creating more accounts than it is designed to handle.
    * 
    * The method then prompts the user to enter their CNIC (Computerized National Identity Card) number.
    * The CNIC number is a unique identifier for Pakistani citizens, and is used to verify their identity.
    * The method validates the input to ensure that it is in the correct format (5 digits, hyphen, 7 digits, hyphen, 1 digit).
    * If the input is invalid, it prints an error message and prompts the user to enter it again.
    * 
    * The method then checks if the customer already has an account in the same bank.
    * If so, it prints a message and returns. This is done to prevent the system from creating duplicate accounts for the same customer.
    * 
    * The method then prompts the user to enter their name, father's name, and age.
    * It validates the input to ensure that it is in the correct format.
    * If the input is invalid, it prints an error message and prompts the user to enter it again.
    * 
    * The method then generates a random 6-digit account number and prompts the user to set a 4-digit PIN.
    * It validates the input to ensure that it is a 4-digit integer.
    * If the input is invalid, it prints an error message and prompts the user to enter it again.
    * 
    * Once all the information has been collected, the method creates a new account with the account number, PIN, and customer information.
    * It then saves the customer data to a file using the saveCustomerData method.
    * 
    * If there is an exception during the input or file writing process, the method catches it and prints an error message.
    * This is done to ensure that the program does not crash if there is an unexpected error.
    * 
    * Overall, the openAccount method is a crucial part of the banking system, as it allows new customers to create accounts and start using the bank's services.
    * By validating the input and generating random account numbers, the method helps to ensure the security and integrity of the banking system.
    */    
        
    public static void openAccount(Scanner scanner, Random rand, String bankName, int branchCode) {
        if (customerCount >= MAX_CUSTOMERS) {
            System.out.println("Maximum number of customers reached. Cannot open more accounts.");
            return;
        }

        try {
            String cnic;
            while (true) {
                System.out.print("Enter your CNIC (Format: 12345-1234567-1): ");
                cnic = scanner.nextLine();
                if (cnic.matches("\\d{5}-\\d{7}-\\d{1}")) break;
                else System.out.println("Invalid CNIC format. Please enter in the correct format (12345-1234567-1).");
            }
            for (int i = 0; i < customerCount; i++) {
                 if (cnics[i] != null && bankNames[i] != null && cnics[i].equals(cnic) && bankNames[i].equals(bankName)) {
                    System.out.println("You already have an account in this bank.");
                    return;
                }
            }
            String fatherName = "";
            String name = "";
            int age = -1;   
            while (true) {
                try {
                    System.out.print("Enter your Name: ");
                    name = scanner.nextLine();
                    if (!name.matches("[a-zA-Z ]+")) { // Check if name contains only alphabets and spaces
                        throw new IllegalArgumentException("Name should only contain alphabets and spaces.");
                    }
                    if(name.isBlank()){
                        throw new IllegalArgumentException("Name can not be empty");
                    } else {
                        break; // Exit the loop if name is valid
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }   
            while (true) {
                try {
                    System.out.print("Enter your Age: ");
                    age = Integer.parseInt(scanner.nextLine());
                    if (age < 18 || age > 99) { // Check if age is within the valid range
                        throw new IllegalArgumentException("Please enter a valid age (18-99).");
                    } else {
                        break; // Exit the loop if age is valid
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Age should be a valid integer.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }   
            while (true) {
                try {
                    System.out.print("Enter your Father's Name: ");
                    fatherName = scanner.nextLine();
                    if (!fatherName.matches("[a-zA-Z ]+")) { // Check if father's name contains only alphabets and spaces
                        throw new IllegalArgumentException("Father's Name should only contain alphabets and spaces.");
                    }
                    if(fatherName.isBlank()){
                        throw new IllegalArgumentException("Father name can not be empty");
                    } else {
                        break; // Exit the loop if father's name is valid
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }   
        
        
            int pin;

            while (true) {
                try {
                    System.out.print("Set your PIN (4 digits): ");
                    String input = scanner.nextLine();
    
                    // Check if the input is exactly 4 digits long
                    if (input.length() != 4) {
                        throw new IllegalArgumentException("Invalid PIN length. Please enter a 4-digit PIN.");
                    }
    
                    // Convert the input to an integer
                    pin = Integer.parseInt(input);
                    break; // Exit the loop if PIN is valid
                } catch (NumberFormatException e) {
                    System.out.println("Error: PIN should be a 4-digit integer.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                } 
            }
    
            System.out.println("Your PIN is set to: " + String.format("%04d", pin));
            System.out.println("Account created successfully.");
            int accountNumber = rand.nextInt(900000) + 100000; // Generate random 6-digit account number
            System.out.println("Your account has been created successfully.");
            System.out.println("Bank Name: " + bankName);
            System.out.println("Branch Code: " + branchCode);
            System.out.println("Account Number: " + accountNumber);
            accountNumbers[customerCount] = accountNumber;
            names[customerCount] = name;
            ages[customerCount] = age;
            cnics[customerCount] = cnic;
            fatherNames[customerCount] = fatherName;
            pins[customerCount] = pin;
            bankNames[customerCount] = bankName;
            branchCodes[customerCount] = branchCode;
            customerCount++;
            saveCustomerData();
            numAccountsOpenedByManager++;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct information.");
            scanner.nextLine(); // Consume newline
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    /*

    * This method is used to view the account information of a customer.
   
    * It takes a Scanner object as a parameter, which is used to get the account number and PIN from the user.
   
    * The method first prompts the user to enter their account number and PIN.
    
    * It then searches the accountNumbers and pins arrays for a match.
    * If a match is found, it prints the account information, including the name, age, CNIC, father's name, PIN, bank name, and branch code.
    * If no match is found, it prints a message indicating that the account was not found.
    
    *The method also handles InputMismatchException, which occurs when the user enters an invalid input (e.g., a string instead of an integer).
    *In this case, it prints an error message and prompts the user to enter a valid account number.
    *Finally, the method catches any other exceptions that may occur and prints an error message with the exception message.
     */

    public static void viewAccount(Scanner scanner) {
        try {
            System.out.print("Enter your Account Number: ");
            int accountNumber = scanner.nextInt();
			System.out.println("Enter your pin : ");
			int pin = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < customerCount; i++) {
                if (accountNumbers[i] == accountNumber && pins[i] == pin) {
                    System.out.println("Account Information:");
                    System.out.println("Name: " + names[i]);
                    System.out.println("Age: " + ages[i]);
                    System.out.println("CNIC: " + cnics[i]);
                    System.out.println("Father's Name: " + fatherNames[i]);
                    System.out.println("Your PIN is: " + String.format("%04d", pins[i]));
                    System.out.println("Bank Name: " + bankNames[i]);
                    System.out.println("Branch Code: " + branchCodes[i]);
                    return;
                }
            }
            System.out.println("Account not found. Please enter a valid account number.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.nextLine(); // Consume newline
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }
    
    /*

    * This method is used to view the transaction history of a customer.

    * It takes a Scanner object as a parameter, which is used to get the account number from the user.

    * The method first loads the transaction data using the loadTransactions method.

    * It then prompts the user to enter their account number.

    * It searches the accountNumbers array for a match.
    * If a match is found, it prints the transaction history for the account, including the date, type, and amount of each transaction.
    * If no match is found, it prints a message indicating that the account was not found.

    * The method also handles InputMismatchException, which occurs when the user enters an invalid input (e.g., a string instead of an integer).
    * In this case, it prints an error message and prompts the user to enter a valid account number.
    * Finally, the method catches any other exceptions that may occur and prints an error message with the exception message. 
    */
        public static void viewTransactions(Scanner scanner) {
    loadTransactions();
    try {
        System.out.print("Enter your Account Number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < customerCount; i++) {
            if (accountNumbers[i] == accountNumber) {
                System.out.println("Transactions for Account Number: " + accountNumber);
                for (int j = 0; j < transactionCounts[i]; j++) {
                    System.out.println(transactionDates[i][j] + " - " + transactionTypes[i][j] + ": $" + transactionAmounts[i][j]);
                }
                return;
            }
        }

        System.out.println("Account not found. Please enter a valid account number.");
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid account number.");
        scanner.nextLine(); // Consume newline
    }
    catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    } 
}

    /*

    * This method is used to add a new transaction to a customer's account.
    
    * It takes a Scanner object as a parameter, which is used to get the account number, transaction type, and transaction amount from the user.
    
    * The method first prompts the user to enter their account number.
    
    * It searches the accountNumbers array for a match.
    * If a match is found, it checks if the maximum number of transactions has been reached for the account.
    * If not, it prompts the user to enter the transaction type and amount.

    * It then checks if the account has sufficient funds for the withdrawal, if applicable.
    * If so, it records the transaction and updates the account balance.
    
    * The method also handles NumberFormatException, which occurs when the user enters an invalid input (e.g., a string instead of a number).
    * In this case, it prints an error message and prompts the user to enter a valid amount.
    * Finally, the method catches any other exceptions that may occur and prints an error message with the exception message. */
public static void addTransaction(Scanner scanner) {
        System.out.print("Enter your Account Number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());

        int index = findAccountIndex(accountNumber);
        if (index == -1) {
            System.out.println("Account not found.");
            return;
        }

        if (transactionCounts[index] >= MAX_TRANSACTIONS) {
            System.out.println("Maximum number of transactions reached for this account.");
            return;
        }

        try {
            System.out.print("Enter transaction type (Deposit/Withdrawal): ");
            String type = scanner.nextLine();
            System.out.print("Enter transaction amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (type.equalsIgnoreCase("Withdrawal") && accountBalances[index] < amount) {
                System.out.println("Insufficient funds for withdrawal.");
                return;
            }

            if (type.equalsIgnoreCase("Withdrawal")) {
                accountBalances[index] -= amount;
            } else if (type.equalsIgnoreCase("Deposit")) {
                accountBalances[index] += amount;
            } else {
                System.out.println("Invalid transaction type.");
                return;
            }

            recordTransaction(index, LocalDate.now(), type, amount);
			saveTransactions();
            System.out.println("Transaction successful.");
			 if (type.equalsIgnoreCase("Withdrawal")) {
                accountBalances[index] -= amount;
				System.out.print("your remaing balance is: " + accountBalances[index]);
            } else if (type.equalsIgnoreCase("Deposit")) {
                accountBalances[index] += amount;
			System.out.print("your remaing balance is: " + accountBalances[index]);
			}
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    /*

    * This method is used to transfer funds between two customer accounts.

    * It takes a Scanner object as a parameter, which is used to get the account numbers and transfer amount from the user.

    * The method first prompts the user to enter the sender's account number, recipient's account number, and transfer amount.

    *  It searches the accountNumbers array for a match for both the sender and recipient accounts.
    *  If a match is found for either account, it checks if the sender account has sufficient funds for the transfer.
    *  If so, it transfers the funds, records the transaction, and updates the account balances.

    *  The method also handles NumberFormatException, which occurs when the user enters an invalid input (e.g., a string instead of a number).
    *  In this case, it prints an error message and prompts the user to enter valid account numbers and amount.
    *  Finally, the method catches any other exceptions that may occur and prints an error message with the exception message. */
    public static void transferFunds(Scanner scanner) {
        try {
            System.out.print("Enter your Account Number: ");
            int senderAccountNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter recipient's Account Number: ");
            int recipientAccountNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(scanner.nextLine());

            int senderIndex = findAccountIndex(senderAccountNumber);
            int recipientIndex = findAccountIndex(recipientAccountNumber);

            if (senderIndex == -1) {
                System.out.println("Sender account not found.");
                return;
            }
            if (recipientIndex == -1) {
                System.out.println("Recipient account not found.");
                return;
            }

            if (accountBalances[senderIndex] < amount) {
                System.out.println("Insufficient funds.");
            } else {
                accountBalances[senderIndex] -= amount;
                accountBalances[recipientIndex] += amount;

                // Record the transaction for the sender
                recordTransaction(senderIndex, LocalDate.now(), "Transfer Out", amount);
                // Record the transaction for the recipient
                recordTransaction(recipientIndex, LocalDate.now(), "Transfer In", amount);

                System.out.println("Transfer successful.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid account numbers and amount.");
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }
/*

    * This method is used to record a transaction for a customer's account.

    * It takes four parameters: the account index, the transaction date, the transaction type, and the transaction amount.

    * The method records the transaction by storing the date, type, and amount in the corresponding arrays (transactionDates, transactionTypes, and transactionAmounts) at the current transaction index for the account.
    
    * It then increments the transaction count for the account to keep track of the number of transactions.
    * This method is called from the addTransaction and transferFunds methods to record transactions and transfers. */
    private static void recordTransaction(int accountIndex, LocalDate date, String type, double amount) {
        int transactionIndex = transactionCounts[accountIndex];
        transactionDates[accountIndex][transactionIndex] = date;
        transactionTypes[accountIndex][transactionIndex] = type;
        transactionAmounts[accountIndex][transactionIndex] = amount;
        transactionCounts[accountIndex]++;
    }

    /*
    * This method is used to print the transaction records for a customer's account.
    
    * It takes the account index as a parameter and prints out the transaction records for that account.
    
    * The method iterates through the transaction arrays (transactionDates, transactionTypes, and transactionAmounts) up to the current transaction count for the account.
    * For each transaction, it prints out the date, type, amount, and the remaining balance after the transaction.
    * This method is called from the main menu to display the transaction history for a customer. */
        public static void printTransactions(int index) {
        System.out.println("\nTransaction Records:");
        for (int i = 0; i < transactionCounts[index]; i++) {
            System.out.println("Date: " + transactionDates[index][i] + ", Type: " + transactionTypes[index][i] + ", Amount: " + transactionAmounts[index][i] +", Remaining Balance "+accountBalances[index]);
        }
		
    }

    /*

    * This method is used to view all customer accounts.

    * It checks if there are any accounts in the system. If not, it displays a message indicating that no accounts were found.
    * If there are accounts, it iterates through the arrays containing customer information (names, ages, cnics, fatherNames, pins, bankNames, branchCodes) and checks if all necessary fields are present for each account.
    * If all fields are present, it displays the account information in a formatted string, including the account number, name, age, CNIC, father's name, PIN, bank name, and branch code.
   
    *This method is called from the main menu to display all customer accounts. */
        public static void viewAllAccounts() {
    if (customerCount == 0) {
        System.out.println("No accounts found.");
        return;
    }

    System.out.println("All Accounts:");
    for (int i = 0; i < customerCount; i++) {
        // Ensure all necessary fields are present
        if (names[i] != null && !names[i].isEmpty() &&
            ages[i] != 0 &&
            cnics[i] != null && !cnics[i].isEmpty() &&
            fatherNames[i] != null && !fatherNames[i].isEmpty() &&
            String.valueOf(pins[i]).length() == 4 &&
            bankNames[i] != null && !bankNames[i].isEmpty() &&
            branchCodes[i] != 0) {

            // Display account information
            String accountData = String.format(
                "Account Number: %d, Name: %s, Age: %d, CNIC: %s, Father's Name: %s, PIN: %d, Bank Name: %s, Branch Code: %d",
                accountNumbers[i], names[i], ages[i], cnics[i], fatherNames[i], pins[i], bankNames[i], branchCodes[i]
            );
            System.out.println(accountData);

            
        }
    }
}
   
    public static void viewAllTransactions() {
        System.out.println("All Transactions:");
        for (int i = 0; i < customerCount; i++) {
            for (int j = 0; j < transactionCounts[i]; j++) {
               
                System.out.println("Account Number: " + accountNumbers[i] +
                ", Date: " + transactionDates[i][j] +
                ", Type: " + transactionTypes[i][j] +
                ", Amount: " + transactionAmounts[i][j]);
                
            }
        }

    }
    /*

    * This method is used to view all transactions for all customer accounts.

    * It iterates through the arrays containing customer transaction information (accountNumbers, transactionDates, transactionTypes, transactionAmounts) and checks if there are any transactions for each account.
    * If there are transactions, it displays the transaction information in a formatted string, including the account number, date, type, and amount.
    
    * This method is called from the main menu to display all customer transactions. */
    public static void viewManagerActivities() {
        // CEO view of manager activities
        try (BufferedReader managerReader = new BufferedReader(new FileReader("manager.txt"))) {
            String line;
            while ((line = managerReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading manager.txt: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
        
    }

     /*

    * This method is used to view all manager accounts.

    * It reads the "manager.txt" file line by line and checks if the line starts with "Account opened".
    * If the line starts with "Account opened", it displays the line, which contains information about the account.
    
    * This method is called from the main menu to display all manager accounts. */

    public static void viewManagerTransactions() {
        // Manager view of all transactions
        try (BufferedReader managerReader = new BufferedReader(new FileReader("manager.txt"))) {
            String line;
            while ((line = managerReader.readLine()) != null) {
                if (line.startsWith("Transaction")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading manager.txt: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }
    
    /*
    * This method loads customer data from a file "customers.txt" into arrays.
    * It reads the file line by line and stores the data in arrays.
    * The data includes account numbers, names, ages, CNICs, father names, PINs, and bank names and branch codes.
    * This method is called from the main menu to load customer data.
     */

    public static void loadCustomerData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                accountNumbers[customerCount] = Integer.parseInt(parts[0]);
                names[customerCount] = parts[1];
                ages[customerCount] = Integer.parseInt(parts[2]);
                cnics[customerCount] = parts[3];
                fatherNames[customerCount] = parts[4];
                pins[customerCount] = Integer.parseInt(parts[5]);
                bankNames[customerCount] = parts[6];
                branchCodes[customerCount] = Integer.parseInt(parts[7]);
                customerCount++;
            }
        } catch (IOException e) {
            System.out.println("Error loading customer data: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }
    /*
    * This method saves customer data to a file "customers.txt".

    * It creates a PrintWriter object to write to the file.

    * It then loops through the arrays that contain the customer data and writes each piece of data to the file, separated by commas.
   
    * The data is written to the file in the following order: account number, name, age, CNIC, father name, PIN, bank name, and branch code.
    
    * This method is called from the main menu to save customer data.
    * If an IOException occurs, an error message is printed to the console.
    * If any other exception occurs, an unexpected error message is printed to the console.
     */
    
    public static void saveCustomerData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customers.txt"))) {
            for (int i = 0; i < customerCount; i++) {
                writer.printf("%d,%s,%d,%s,%s,%d,%s,%d%n", accountNumbers[i], names[i], ages[i], cnics[i], fatherNames[i], pins[i], bankNames[i], branchCodes[i]);
            }
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    /*
    * This method saves manager data to a file "BM.txt".
    
    * It first checks if the file exists, and if not, it creates a new file.
    * If the file cannot be created, an error message is printed and the method returns.
    
    * The method then writes data to the file using a PrintWriter.
    * It starts by writing a header line that contains the column names for the data.
    
    * Then, it loops through the arrays that contain the customer data and writes each piece of data to the file, separated by commas.
    * For each customer, it writes the customer information (account number, name, age, CNIC, father name, PIN, bank name, and branch code) followed by the transaction data.
    * The transaction data includes the transaction date, transaction type, transaction amount, and transaction count.
    * If a customer has multiple transactions, the method writes each transaction on a new line, with the customer information repeated for each transaction.
    * If a customer has no transactions, the method writes "N/A" for the transaction date and type, and 0.0 for the transaction amount.
    
    * After writing all the data, the method prints a success message to the console.
    * If an IOException occurs during the writing process, an error message is printed to the console.
   
     */
	//file handling for manager 
	public static void saveManagerData() {
    File f = new File("BM.txt");

    try {
        if (!f.exists()) {
            f.createNewFile();
        }
    } catch (IOException e) {
        System.out.println("Error creating BM.txt: " + e.getMessage());
        return;
    }

    try (PrintWriter pw = new PrintWriter(f)) {
        // Write header line
        pw.println("AccountNumber,Name,Age,CNIC,FatherName,Pin,BankName,BranchCode,TransactionDate,TransactionType,TransactionAmount,TransactionCount");

        // Write data for each customer
        for (int i = 0; i < customerCount; i++) {
            // Write customer information
            pw.printf("%d,%s,%d,%s,%s,%d,%s,%d,%d",
                    accountNumbers[i],
                    names[i],
                    ages[i],
                    cnics[i],
                    fatherNames[i],
                    pins[i],
                    bankNames[i],
                    branchCodes[i],
                    transactionCounts[i]);

            // Write all transaction data
            for (int j = 0; j < transactionCounts[i]; j++) {
                if (transactionDates[i][j] != null) {
                    pw.printf(",%s,%s,%.2f",
                            transactionDates[i][j].toString(),
                            transactionTypes[i][j],
                            transactionAmounts[i][j]);
                } else {
                    pw.print(",N/A,N/A,0.0");
                }
            }

            pw.println(); // New line for the next customer
        }

        System.out.println("Data saved successfully.");
    } catch (IOException e) {
        System.out.println("Error saving data: " + e.getMessage());
    }
}


    /*
    * This method loads manager data from a file "BM.txt".

    *It first checks if the file exists, and if not, it prints a message and returns.
    * If the file exists, it creates a Scanner object to read the file.
    * It skips the header line and then reads the file line by line.
    
    * For each line, it splits the line into parts using the comma as a delimiter.
    * It then loads the customer information (account number, name, age, CNIC, father name, PIN, bank name, and branch code) from the parts.
    
    * It also loads the transaction data for each customer, including the transaction date, transaction type, transaction amount, and transaction count.
    * If a customer has multiple transactions, the method loads each transaction from the subsequent parts of the line.
    * If a customer has no transactions, the method sets the transaction date and type to null, and the transaction amount to 0.0.
    
    * After loading all the data, the method prints a success message to the console.
    
    * If a FileNotFoundException occurs, the method prints a message and returns.
    * If any other exception occurs, the method prints an error message to the console.

     */
	
        
   public static void loadManagerData() {
    File f = new File("BM.txt");

    if (!f.exists()) {
        System.out.println("Customer data file not found. Starting with empty data.");
        return;
    }

    try (Scanner scanner = new Scanner(f)) {
        scanner.nextLine(); // Skip header line
        customerCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            accountNumbers[customerCount] = Integer.parseInt(parts[0]);
            names[customerCount] = parts[1];
            ages[customerCount] = Integer.parseInt(parts[2]);
            cnics[customerCount] = parts[3];
            fatherNames[customerCount] = parts[4];
            pins[customerCount] = Integer.parseInt(parts[5]);
            bankNames[customerCount] = parts[6];
            branchCodes[customerCount] = Integer.parseInt(parts[7]);
            transactionCounts[customerCount] = Integer.parseInt(parts[8]);

            // Load transaction data
            int offset = 9;
            for (int j = 0; j < transactionCounts[customerCount]; j++) {
                if (offset + 2 < parts.length) {
                    transactionDates[customerCount][j] = parts[offset].equals("N/A") ? null : LocalDate.parse(parts[offset]);
                    transactionTypes[customerCount][j] = parts[offset + 1];
                    transactionAmounts[customerCount][j] = Double.parseDouble(parts[offset + 2]);
                    offset += 3;
                }
            }

            customerCount++;
        }

        System.out.println("Data loaded successfully.");
    } catch (FileNotFoundException e) {
        System.out.println("Customer data file not found. Starting with empty data.");
    } catch (Exception e) {
        System.out.println("Error loading customer data: " + e.getMessage());
    }
}

          



    /* 
    * This method saves transaction data to a file "transactions.txt".

    * It creates a BufferedWriter object to write to the file.
    
    * It first writes a header line that contains the column names for the data.
    * Then, it loops through the arrays that contain the customer data and writes each piece of data to the file, separated by commas.
    * 
    * For each customer, it writes the account number, account balance, and transaction count on a single line.
    * Then, it writes the transaction data for each customer on separate lines.
    * If a customer has multiple transactions, the method writes each transaction on a new line.
    * If a customer has no transactions, the method does not write any transaction data for that customer.
    
    * After writing all the data, the method closes the BufferedWriter.
    
    * If an IOException occurs during the writing process, an error message is printed to the console.
    * If any other exception occurs, an unexpected error message is printed to the console.
    * The method does not return any value. 
        */


 public static void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"))) {
            writer.write("accountNumber,accountBalance,transactionCount,transactionDate,transactionType,transactionAmount\n");
            for (int i = 0; i < customerCount; i++) {
                writer.write(accountNumbers[i] + "," + accountBalances[i] + "," + transactionCounts[i] + "\n");
                for (int j = 0; j < transactionCounts[i]; j++) {
                    writer.write(transactionDates[i][j] + "\n");
                    writer.write(transactionTypes[i][j] + "\n");
                    writer.write(transactionAmounts[i][j] + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }

    /*
    * This method loads transaction data from a file "transactions.txt".
    
    * It creates a Scanner object to read the file.
    * If the file is empty, it returns immediately.
    
    * It skips the header line and then reads the file line by line.
    * For each customer, it reads the account number, account balance, and transaction count from a single line, and parses them into integers and doubles.
    * Then, it reads the transaction data for each customer.
    
    * For each transaction, it reads the transaction date, transaction type, and transaction amount from separate lines, and parses them into a LocalDate, a string, and a double, respectively.
   
    * It stores the loaded data in the corresponding arrays (accountNumbers, accountBalances, transactionCounts, transactionDates, transactionTypes, and transactionAmounts).
    * It also increments the customerCount variable to keep track of the number of customers.
    
    * If an IOException occurs during the reading process, an error message is printed to the console.
    
    * If a DateTimeParseException or NumberFormatException occurs during the parsing process, an error message is printed to the console.
    
    * If any other exception occurs, an unexpected error message is printed to the console.
    
    * The method does not return any value.
        */


 

 public static void loadTransactions() {
    try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("transactions.txt")))) {
        if (!scanner.hasNextLine()) {
            return; // No transactions to load
        }
        scanner.nextLine(); // Skip the header line
        for (int i = 0; i < MAX_CUSTOMERS && scanner.hasNextLine(); i++) {
            String[] accountData = scanner.nextLine().split(",");
            accountNumbers[i] = Integer.parseInt(accountData[0]);
            accountBalances[i] = Double.parseDouble(accountData[1]);
            transactionCounts[i] = Integer.parseInt(accountData[2]);
            for (int j = 0; j < transactionCounts[i]; j++) {
                transactionDates[i][j] = LocalDate.parse(scanner.nextLine());
                transactionTypes[i][j] = scanner.nextLine();
                transactionAmounts[i][j] = Double.parseDouble(scanner.nextLine());
            }
            customerCount++;
        }
    } catch (IOException e) {
        System.out.println("Error loading transactions: " + e.getMessage());
    } catch (DateTimeParseException | NumberFormatException e) {
        System.out.println("Error parsing transactions: " + e.getMessage());
    }catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    } 
}

    /*
    * This method finds the index of a specific account number in the accountNumbers array.
   
    * It takes an accountNumber as a parameter.
   
    * It loops through the accountNumbers array from index 0 to customerCount - 1.
    * For each index, it checks if the account number at that index matches the given accountNumber.
   
    * If a match is found, it returns the index.
    * If no match is found after checking all indices, it returns -1 to indicate that the account number was not found.
    
    * This method is used to quickly locate a specific account in the system.
     */
	
private static int findAccountIndex(int accountNumber) {
        for (int i = 0; i < customerCount; i++) {
            if (accountNumbers[i] == accountNumber) {
                return i;
            }
        }
        return -1;
    }
}