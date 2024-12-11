// Student Name
// Period X
// Fraction Calculator Project

import java.util.*;

// TODO: Description of what this program does goes here.
public class FracCalc {

   // It is best if we have only one console object for input
   public static Scanner console = new Scanner(System.in);

   // String strInput = "help";
   // public static Scanner con = new Scanner(System.in);
   // strInput = console.next();

   // This main method will loop through user input and then call the
   // correct method to execute the user's request for help, test, or
   // the mathematical operation on fractions. or, quit.
   // DO NOT CHANGE THIS METHOD!!
   public static void main(String[] args) {

      // initialize to false so that we start our loop
      boolean done = false;

      // When the user types in "quit", we are done.
      while (!done) {
         // prompt the user for input
         String input = getInput();

         // special case the "quit" command
         if (input.equalsIgnoreCase("quit")) {
            done = true;
         } else if (!UnitTestRunner.processCommand(input, FracCalc::processCommand)) {
            // We allowed the UnitTestRunner to handle the command first.
            // If the UnitTestRunner didn't handled the command, process normally.
            String result = processCommand(input);

            // print the result of processing the command
            System.out.println(result);
         }
      }

      System.out.println("Goodbye!");
      console.close();
   }

   // Prompt the user with a simple, "Enter: " and get the line of input.
   // Return the full line that the user typed in.
   public static String getInput() {
      // Prompt the user with a simple, "Enter: " and get the line of input.
      // Return the full line that the user typed in.
      System.out.print("Enter : ");
      String command = console.nextLine();
      // System.in

      return command;

   }

   // processCommand will process every user command except for "quit".
   // It will return the String that should be printed to the console.
   // This method won't print anything.
   // DO NOT CHANGE THIS METHOD!!!
   public static String processCommand(String input) {

      if (input.equalsIgnoreCase("help")) {
         return provideHelp();
      }

      // if the command is not "help", it should be an expression.
      // Of course, this is only if the user is being nice.
      return processExpression(input);
   }

   // Lots work for this project is handled in here.
   // Of course, this method will call LOTS of helper methods
   // so that this method can be shorter.
   // This will calculate the expression and RETURN the answer.
   // This will NOT print anything!
   // Input: an expression to be evaluated
   // Examples:
   // 1/2 + 1/2
   // 2_1/4 - 0_1/8
   // 1_1/8 * 2
   // Return: the fully reduced mathematical result of the expression
   // Value is returned as a String. Results using above examples:
   // 1
   // 2 1/8
   // 2 1/4
   public static String processExpression(String input) {
      // This method parses the input string, separates the operands and operator, and returns the result of the operation.
      // The input string is in the form of "operand1 operator operand2".
      // The operands can be whole numbers, fractions, or mixed numbers.
      // The operator can be +, -, *, or /.
      
      int operatorIndex = input.indexOf(' ') + 1;
      char operator = input.charAt(operatorIndex);

      String secondPart = input.substring(operatorIndex + 2);

      int whole = 0;
      int numerator = 0;
      int denominator = 1;

      if (secondPart.contains("_")) {
         int underscoreIndex = secondPart.indexOf('_');
         whole = Integer.parseInt(secondPart.substring(0, underscoreIndex));
         String fractionPart = secondPart.substring(underscoreIndex + 1);
         int slashIndex = fractionPart.indexOf('/');
         numerator = Integer.parseInt(fractionPart.substring(0, slashIndex));
         denominator = Integer.parseInt(fractionPart.substring(slashIndex + 1));
      } else if (secondPart.contains("/")) {
         int slashIndex = secondPart.indexOf('/');
         numerator = Integer.parseInt(secondPart.substring(0, slashIndex));
         denominator = Integer.parseInt(secondPart.substring(slashIndex + 1));
      } else {
         whole = Integer.parseInt(secondPart);
      }

      if (numerator < 0 && denominator < 0) {
         numerator = -numerator;
         denominator = -denominator;
      } else if (denominator < 0) {
         numerator = -numerator;
         denominator = -denominator;
      }

      return String.format("Op:%c Whole:%d Num:%d Den:%d", operator, whole, numerator, denominator);

   }

   // Returns a string that is helpful to the user about how
   // to use the program. These are instructions to the user.
   public static String provideHelp() {
      // TODO: Update this help text!

      String help = "This fraction calculator performs fraction arithmetic.\n";
      help += "the user can enter whole number or fraction along with arithmetic operator and the second whole number or fraction.";
      help += "For eg. 1 + 1/2 or 2_1/2 * 3_3/4 and so on.\n";
      help += "Type 'quit' to exit the program.\n";
      help += "Type 'test' and test_case number to run the test cases.\n";
      return help;
   }
}
