/**
 * Extra Credit Project
 * CSE174
 * D
 * Mohamed Lemin Ebbahenne
 */
import java.util.Random;
import java.util.Scanner;

public class bank
{
    static void signUp(String[][] users, int[] numUsers)
    {
        Scanner sc = new Scanner(System.in);
        if (numUsers[0] >= 10)
        {
            System.out.println("Sorry, the maximum number of users has been reached.");
            return;
        }
        System.out.print("\nEnter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email address:");
        String email = sc.nextLine();
        System.out.print("Enter your username:");
        String username = sc.nextLine();
        System.out.print("Enter your password:");
        String password = sc.nextLine();
        String routingNumber = generateRoutingNumber();
        System.out.print("Enter you initial deposit amount: $" );
        double initialDeposit = sc.nextDouble();
        users[numUsers[0]] = new String[]{name, email, username, password, routingNumber, Double.toString(initialDeposit)};
        numUsers[0]++;
        System.out.println("\nYour account has been created successfully!");
        System.out.println("********************************************************************");
    }

    static void login(String[][] users, int[] numUsers)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        int index = findUser(users, numUsers[0], username, password);
        if (index == -1)
        {
            System.out.println("Invalid username or password. Would you like to create a new account? (Y)es or (N)o");
            String choice = sc.nextLine().toUpperCase();
            if (choice.equals("Y"))
                signUp(users, numUsers);
        }
        else
        {
            System.out.println("************Main Menu*************");
            System.out.println("\nWelcome, " + users[index][0] + "!");
            String input ;
            while (true)
            {
                System.out.println("\nSelect an option:");
                System.out.println("H. Home");
                System.out.println("D. Deposit");
                System.out.println("W. Withdraw");
                System.out.println("E. Exit");
                input = sc.nextLine().toUpperCase();
                switch (input)
                {
                    case "H":
                        displayUserInfo(users, index);
                        break;
                    case "D":
                        makeDeposit(users, index);
                        break;
                    case "W":
                        makeWithdrawal(users, index);
                        break;
                    case "E":
                        System.out.println("\nThank you for using the MLB Bank!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
        System.out.println("********************************************************************");
    }

    static int findUser(String[][] users, int numUsers, String username, String password)
    {
        for (int i = 0; i < numUsers; i++)
        {
            if (users[i][2].equals(username) && users[i][3].equals(password))
                return i;
        }
        return -1;
    }

    static void displayUserInfo(String[][] users, int index)
    {
        System.out.println("\nName: " + users[index][0]);
        System.out.println("Email: " + users[index][1]);
        System.out.println("Username: " + users[index][2]);
        System.out.println("Routing Number: " + users[index][4]);
        System.out.println("You current balance: $" + users[index][5]);
        System.out.println("********************************************************************");
    }

    static void makeDeposit(String[][] users, int index)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the amount you would like to deposit: $");
        double amount = sc.nextDouble();
        double balance = Double.parseDouble(users[index][5]);
        balance += amount;
        users[index][5] = Double.toString(balance);
        System.out.println("\nDeposit successful!");
        System.out.println("Your current balance: $" + users[index][5]);
        System.out.println("********************************************************************");
    }

    static String generateRoutingNumber()
    {
        Random rand = new Random();
        String routingNumber = "";
        int digit;
        for (int i = 0; i < 9; i++)
        {
            digit = rand.nextInt(10);
            routingNumber += Integer.toString(digit);
        }
        return routingNumber;
    }

    static void makeWithdrawal(String[][] users, int index)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the amount you would like to withdraw: $");
        double amount = sc.nextDouble();
        double balance = Double.parseDouble(users[index][5]);
        if (balance < amount)
        {
            System.out.println("\nInsufficient balance!");
            return;
        }
        balance -= amount;
        users[index][5] = Double.toString(balance);
        System.out.println("\nWithdrawal successful!");
        System.out.println("Your current balance: $" + users[index][5]);
        System.out.println("********************************************************************");
    }

    public static void main(String[] args)
    {
        String[][] users = new String[10][6];
        int[] numUsers = {0};

        Scanner sc = new Scanner(System.in);
        System.out.println("******** Welcome to the " + "\uFDF4" + " MLB Bank Page!*********");
        System.out.println("           Mohamed Lemin Bank          ");

        String input;
        boolean exit = false;
        while (!exit)
        {
            System.out.println("\nSelect an option:");
            System.out.println("(S)ignUp");
            System.out.println("(L)ogin");
            System.out.println("(E)xit");
            System.out.println("**********************************");
            input = sc.nextLine().toUpperCase();
            switch (input)
            {
                case "S":
                    signUp(users, numUsers);
                    break;
                case "L":
                    login(users, numUsers);
                    break;
                case "E":
                    System.out.println("Thank you for using the MLB Bank App!");
                    System.out.println("********************************************************************");
                    exit = true;
                    break;
                default: System.out.println("Invalid option. Please try again.");
            }
        }
    }
}