import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {
    public static double addition(double... num) {
        if (num.length == 0) {
            return 0;
        }
        double total = 0.0;
        for (int i = 0; i < num.length; i++) {
            total += num[i];
        }
        return total;
    } 

    public static int addition(int... num) {
        if (num.length == 0) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < num.length; i++) {
            total += num[i];
        }
        return total;
    }

    public static double subtraction(double... num) {
        if (num.length == 0) {
            return 0;
        }
        double total = num[0];
        for (int i = 1; i < num.length; i++) {
            total -= num[i];
        }
        return total;
    }

    public static int subtraction(int... num) {
        if (num.length == 0) {
            return 0;
        }
        int total = num[0];
        for (int i = 1; i < num.length; i++) {
            total -= num[i];
        }
        return total;

    } 

    public static double multiplication(double... num) {
        if (num.length == 0) {
            return 0;
        }
        double total = 1.0;
        for (int i = 0; i < num.length; i++) {
            total *= num[i];
        }
        return total;
    }

    public static int multiplication(int... num) {
        if (num.length == 0) {
            return 0;
        }
        int total = 1;
        for (int i = 0; i < num.length; i++) {
            total = total * num[i];
        }
        return total;

    } 

    public static double division(double... num) {
        if (num.length == 0) {
            return 0.0;
        }
        double total = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] == 0) {
                return 0;
            }
            total /= num[i];
        }
        return total;
    }

    public static int division(int... num) {
        if (num.length == 0) {
            return 0;
        }
        int total = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] == 0) {
                return 0;
            }
            total /= num[i];
        }
        return total;

    } 

    public static double tip(double total, double tipPercentage) {
        if (total == 0) {
            return 0;
        } else if (tipPercentage == 0) {
            return 0;
        }
        double percent = division((double)tipPercentage, 100);
        double tipAmount = multiplication(total, percent);
        tipAmount = Math.round(tipAmount * 100.00) / 100.0;
        return tipAmount;
        
    }

    public static String salesTax(String state, double subtotal) {
        if (subtotal == 0) {
            return "0";
        }
        boolean found = false;
        DecimalFormat moneyRounding = new DecimalFormat("0.00");
        File salesTaxFile = new File("salestax.txt");
        String stateName = "XX";
        double stateTax = 0.0;
        try (Scanner readData = new Scanner(salesTaxFile)) {
            while (readData.hasNextLine()) {
                String stateInfo = readData.nextLine();
                if (stateInfo.substring(0, 2).toLowerCase().equals(state.toLowerCase())) {
                    found = true;
                    stateName = stateInfo.substring(0, 2);
                    stateTax = Double.parseDouble(stateInfo.substring(3));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
        }
        if (found == false) {
            return "Not a valid U.S. state.";
        }
        String total = moneyRounding.format(addition(subtotal, multiplication(subtotal, division(stateTax, 100))));
        return total;
    }

    public static void tryAgain(double value) {
        System.out.println();
        System.out.println("Would you like to try another operation?");
        System.out.println();
        Scanner useValue = new Scanner(System.in);
        System.out.print("> ");
        String choice = useValue.nextLine();
        if (choice.toLowerCase().equals("y")) {
            mainMenu(value);    
        } else if (choice.toLowerCase().equals("n")) {
            System.out.println();
            System.out.println("End.");
        } else {
            System.out.println();
            System.out.println("Program aborted.");
        }
    }

    public static void additionMenu(double value) {
        System.out.println("Please write your equation, with each value to be operated separated with a space and plus (+) sign.");
        System.out.println();
        Scanner equationScanner = new Scanner(System.in);
        if (value != 0.0) {
            System.out.print("> " + value + " + ");
        } else if (value == 0.0) {
            System.out.print("> ");
        }
        String equation = equationScanner.nextLine();
        System.out.println();
        String[] numbers = equation.trim().split("\\s*\\+\\s*");
        double total = value;
        for (int i = 0; i < numbers.length; i++) {
             double num = Double.parseDouble(numbers[i]);
            total += addition(num, 0.0);
        }
        System.out.println("= " + total);
        tryAgain(total);
    }

    public static void subtractionMenu(double value) {
        System.out.println("Please write your equation, with each value to be operated separated with a space and minus (-) sign.");
        System.out.println();
        Scanner equationScanner = new Scanner(System.in);
        if (value != 0.0) {
            System.out.print("> " + value + " - ");
        } else if (value == 0.0) {
            System.out.print("> ");
        }
        String equation = equationScanner.nextLine();
        System.out.println();
        String[] numbers = equation.trim().split("\\s*\\-\\s*");
        double total = value;
        if (value == 0.0) {
            total = Double.parseDouble(numbers[0]);
            for (int i = 1; i < numbers.length; i++) {
                double num = Double.parseDouble(numbers[i]);
                total = subtraction(total, num);
            }
        } else {
            total = value;
            for (int i = 0; i < numbers.length; i++) {
                double num = Double.parseDouble(numbers[i]);
                total = subtraction(total, num);
            }
        }
        System.out.println("= " + total);
        tryAgain(total);
    }

    public static void multiplicationMenu(double value) {
        System.out.println("Please write your equation, with each value to be operated separated with a space and multiplication (*) sign/asterisks.");
        System.out.println();
        Scanner equationScanner = new Scanner(System.in);
        if (value != 0.0) {
            System.out.print("> " + value + " * ");
        } else if (value == 0.0) {
            System.out.print("> ");
        }
        String equation = equationScanner.nextLine();
        System.out.println();
        String[] numbers = equation.trim().split("\\s*\\*\\s*");
        double total = 0.0;
        if (value != 0.0) {
            total = value;
        } else {
            total = 1;
        }
        for (int i = 0; i < numbers.length; i++) {
             double num = Double.parseDouble(numbers[i]);
            total = multiplication(num, total);
        }
        System.out.println("= " + total);
        tryAgain(total);
    }

    public static void divisionMenu(double value) {
        System.out.println("Please write your equation, with each value to be operated separated with a space and forward-slash (/) sign.");
        System.out.println();
        Scanner equationScanner = new Scanner(System.in);
        if (value != 0.0) {
            System.out.print("> " + value + " / ");
        } else if (value == 0.0) {
            System.out.print("> ");
        }
        String equation = equationScanner.nextLine();
        System.out.println();
        String[] numbers = equation.trim().split("\\s*\\/\\s*");
        double total;
        if (value == 0.0) {
            total = Double.parseDouble(numbers[0]);
            for (int i = 1; i < numbers.length; i++) {
                double num = Double.parseDouble(numbers[i]);
                total = division(total, num);
            }
        } else {
            total = value;
            for (int i = 0; i < numbers.length; i++) {
                double num = Double.parseDouble(numbers[i]);
                total = division(total, num);
            }
        }
        System.out.println("= " + total);
        tryAgain(total);
    }

    public static void tipMenu(double value) {
        System.out.println();
        System.out.println("Please write the percent tip you'd like to give. Do not include the percent symbol.");
        System.out.println();
        Scanner percentScanner = new Scanner(System.in);
        System.out.print("> ");
        String percentStr = percentScanner.nextLine();
        System.out.println();
        double percent = Double.parseDouble(percentStr);
        double tipAmount = 0.0;
        if (value != 0.0) {
            System.out.println("Would you like to use the saved value (" + value + ") as the subtotal to calculate a tip amount?");
            System.out.println();
            Scanner ask = new Scanner(System.in);
            System.out.print("> ");
            String answer = ask.nextLine();
            if (answer.toLowerCase().equals("y")) {
                tipAmount = tip(value, percent);
                System.out.println("Tip Amount (" + percent + "%): $" + tipAmount + "\nTotal: $" + addition(tipAmount, value));
                System.out.println();
            } else {
                System.out.println("What is the subtotal?");
                System.out.println();
                Scanner subtotalScanner = new Scanner(System.in);
                System.out.print("> ");
                String subtotalStr = subtotalScanner.nextLine();
                double subtotal = Double.parseDouble(subtotalStr);
                tipAmount = tip(subtotal, percent);
                System.out.println("Tip Amount (" + percent + "): $" + tipAmount + "\nTotal: $" + (addition(tipAmount, subtotal)));
                System.out.println();
            }
        } else {
                System.out.println("What is the subtotal?");
                System.out.println();
                Scanner subtotalScanner = new Scanner(System.in);
                System.out.print("> ");
                String subtotalStr = subtotalScanner.nextLine();
                double subtotal = Double.parseDouble(subtotalStr);
                tipAmount = tip(subtotal, percent);
                System.out.println();
                System.out.println("Tip Amount (" + percent + "%): $" + tipAmount + "\nTotal: $" + (addition(tipAmount, subtotal)));
                System.out.println();
        }
        tryAgain(tipAmount);
    }

    public static void salesTaxMenu(double value) {
        System.out.println();
        System.out.println("Please enter the two-letter acronym of the state you reside in.");
        System.out.println();
        Scanner stateScanner = new Scanner(System.in);
        System.out.print("> ");
        String state = stateScanner.nextLine();
        System.out.println();
        String totalAmount = "";
        if (value != 0.0) {
            System.out.println("Would you like to use the saved value (" + value + ") as the subtotal to calculate a total with the sales tax amount?");
            System.out.println();
            Scanner ask = new Scanner(System.in);
            System.out.print("> ");
            String answer = ask.nextLine();
            if (answer.toLowerCase().equals("y")) {
                System.out.println();
                totalAmount = salesTax(state, value);
                System.out.println("Total with tax included: $" + totalAmount);
                System.out.println();
                value = Double.parseDouble(totalAmount);
            } else {
                System.out.println();
                System.out.println("What is the subtotal?");
                System.out.println();
                Scanner subtotalScanner = new Scanner(System.in);
                System.out.print("> ");
                String subtotalStr = subtotalScanner.nextLine();
                double subtotal = Double.parseDouble(subtotalStr);
                totalAmount = salesTax(state, subtotal);
                System.out.println();
                System.out.println("Total with tax included: $" + totalAmount);
                System.out.println();
                value = Double.parseDouble(totalAmount);
            }
        } else {
                System.out.println();
                System.out.println("What is the subtotal?");
                System.out.println();
                Scanner subtotalScanner = new Scanner(System.in);
                System.out.print("> ");
                String subtotalStr = subtotalScanner.nextLine();
                double subtotal = Double.parseDouble(subtotalStr);
                totalAmount = salesTax(state, subtotal);
                System.out.println();
                System.out.println("Total with tax included: $" + totalAmount);
                System.out.println();
                value = Double.parseDouble(totalAmount);
        }
        tryAgain(value);
    }

    public static void opMenu(double value, String operation) {
        System.out.println();
        System.out.println("Current Operation: " + operation);
        System.out.println();
        if (value != 0.0) {
            System.out.println("You have a saved value from a previous operation (" + value + "). Would you like to use it? (Y/N)");
            System.out.println();
            Scanner useValue = new Scanner(System.in);
            System.out.print("> ");
            String choice = useValue.nextLine();
            if (choice.toLowerCase().equals("y")) {
                if (operation.equals("Addition")) {
                    System.out.println();
                    System.out.println("Saved. The number (" + value + ") will be used in this next addition operation.");
                    System.out.println();
                    additionMenu(value);
                } else if (operation.equals("Subtraction")) {
                    System.out.println();
                    System.out.println("Saved. The number (" + value + ") will be used in this next subtraction operation.");
                    System.out.println();  
                    subtractionMenu(value);
                } else if (operation.equals("Multiplication")) {
                    System.out.println();
                    System.out.println("Saved. The number (" + value + ") will be used in this next multiplication operation.");
                    System.out.println();
                    multiplicationMenu(value);
                } else if (operation.equals("Division")) {
                    System.out.println();
                    System.out.println("Saved. The number (" + value + ") will be used in this next division operation.");
                    System.out.println();
                    divisionMenu(value);
                } else if (operation.equals("Tip")) {
                    System.out.println();
                    System.out.println("Saved. The number (" + value + ") will be used in this next tip operation.");
                    System.out.println();
                    tipMenu(value);
                } else if (operation.equals("Sales Tax")) {
                    System.out.println();
                    System.out.println("Saved. The number (" + value + ") will be used in this next sales tax operation.");
                    System.out.println();
                    salesTaxMenu(value);
                }
            } else if (choice.toLowerCase().equals("n")) {
                System.out.println();
                if (operation.equals("Addition")) {
                    additionMenu(0.0);
                } else if (operation.equals("Subtraction")) {
                    subtractionMenu(0.0);
                } else if (operation.equals("Multiplication")) {
                    multiplicationMenu(0.0);
                } else if (operation.equals("Division")) {
                    divisionMenu(0.0);
                } else if (operation.equals("Tip")) {
                    tipMenu(0.0);
                } else if (operation.equals("Sales Tax")) {
                    salesTaxMenu(0.0);
                }
            } else {
                System.out.println();
                System.out.println("Program aborted.");
            }
        } else {
            if (operation.equals("Addition")) {
                additionMenu(0.0);
            } else if (operation.equals("Subtraction")) {
                subtractionMenu(0.0);
            } else if (operation.equals("Multiplication")) {
                multiplicationMenu(0.0);
            } else if (operation.equals("Division")) {
                divisionMenu(0.0);
            } else if (operation.equals("Tip")) {
                tipMenu(0.0);
            } else if (operation.equals("Sales Tax")) {
                salesTaxMenu(0.0);
            }
        }
        System.out.println();
    }

    public static void mainMenu(double value) {
        System.out.println();
        System.out.println("Please choose from the list of operations.");
        System.out.println();
        System.out.println("[ 1 ] Addition");
        System.out.println("[ 2 ] Subtraction");
        System.out.println("[ 3 ] Multiplication");
        System.out.println("[ 4 ] Division");
        System.out.println("[ 5 ] Tip");
        System.out.println("[ 6 ] Sales Tax");
        System.out.println();
        Scanner operationSelection = new Scanner(System.in);
        System.out.print("> ");
        String selection = operationSelection.nextLine();
        if (selection.equals("1")) {
            opMenu(value, "Addition");
        } else if (selection.equals("2")) {
            opMenu(value, "Subtraction");
        } else if (selection.equals("3")) {
            opMenu(value, "Multiplication");
        } else if (selection.equals("4")) {
            opMenu(value, "Division");
        } else if (selection.equals("5")) {
            opMenu(value, "Tip");
        } else if (selection.equals("6")) {
            opMenu(value, "Sales Tax");
        }

    }

    public static void main(String[] args) {
        mainMenu(0.0);
    }
}