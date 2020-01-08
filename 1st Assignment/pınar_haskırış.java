/**
 * Main Class
 *
 * Program Explanation:
 * This program takes a txt file and checks whether the java code inside that file has matching parentheses.
 * To do this, the program takes the file, goes through it line by line, 
 * goes through the lines character by character and when it sees an open parentheses ( '(' or '{') it adds it to the stack.
 * When a closing parentheses comes ( ')' or '}' ) the last element of the stack is being  popped and the code checks whether
 * the opening parentheses is matching with the popped parentheses.
 * If they match, no problem. If they don't match, there is a problem.
 * In the end, the stack should be empty. If its not, there is problem.
 * The stack is being printed every time a new character gets in.
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pınar_haskırış {
	public static void main(String[] args) {

		//creating a stack to store the opening parentheses
		myStack stack = new myStack();

		String filename = " ";

		//creating a boolean variable to check whether the given code has matching parentheses later
		boolean isCorrect = true;

		//creating a char to use when go through the given file
		char c = ' ';

		//creating a scanner to determine which file to read
		Scanner reader = new Scanner(System.in);

		//giving instructions to the user
		System.out.println("Chose data file to read: ");
		System.out.println("Enter 1 to select data1.txt");
		System.out.println("Enter 2 to select data2.txt");
		System.out.println("Enter 3 to select data3.txt");
		System.out.println("Enter 4 to select data4.txt");

		//asking for the input to chose which file to read
		int input = reader.nextInt();
		if (input == 1) {
			System.out.println("data1.txt is selected");
			filename = "data1.txt";
		}

		if (input == 2) {
			System.out.println("data2.txt is selected");
			filename = "data2.txt";
		}

		if (input == 3) {
			System.out.println("data3.txt is selected");
			filename = "data3.txt";
		}

		if (input == 4) {
			System.out.println("data4.txt is selected");
			filename = "data4.txt";
		}

		//reading the input file
		File myFile = new File(filename);
		Scanner myInput = null;

		try { //using try-catch to catch possible exceptions
			myInput = new Scanner(myFile); //reading the file
		} catch (FileNotFoundException e) { //catching possible exceptions
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}

		
		while (myInput.hasNext()) { //as long as we have a line in the file
			String line = myInput.nextLine(); //take that line
			for (int i = 0; i < line.length(); i++) {
				c = line.charAt(i); //go through it

				if (c == '{' || c == '(') { //search for open parentheses
					stack.push((Character) c);  //add it to the stack if there is any

					System.out.println();
					stack.printStack(); //calling the printStack function from myStack class to print the stack contents
				}


				if (c == '}' || c == ')') { //if we find a closed parentheses
					if (!stack.isEmpty()) { //if the stack is not empty
						char popped = (char) stack.pop(); //pop the last element of the stack


						if (c == '}' && popped == '{') { //if we have a set of {}
							isCorrect = true;
							continue; //no problems, continue

						} else if (c == '}' && popped != '{') { // if we don't have a set of {}
							System.out.println("\nParantheses do not match!");
							isCorrect = false; // the code is incorrect in terms of parentheses
						}

						if (c == ')' && popped == '(') { //if we have a set of ()
							isCorrect = true;
							continue; // no problems, continue

						} else if (c == ')' && popped != '(') { //if we don't have a set of ()
							System.out.println("\nParantheses do not match!");
							isCorrect = false; // the code is incorrect in terms of parentheses
						}

					}


					if ((c == '}' || c == ')') && stack.isEmpty()) { //if there is a closing parentheses while there is nothing to match
						System.out.println("\nParenthesis do not match!");
						isCorrect = false; // the code is incorrect in terms of parentheses
						break;
					}

				}

			}


		}
		if(!myInput.hasNext() && !stack.isEmpty()) { //if the stack is not empty after we finished reading the file
			System.out.println("\nParenthesis do not match."); //means there is an open parentheses left in stack
			isCorrect = false; // the code is incorrect in terms of parentheses
		}
		if(isCorrect)
			System.out.println("\nParenthesis do match.");		

	}

}
