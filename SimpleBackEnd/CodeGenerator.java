import java.util.*;
import java.io.*;

/*
	Note when using this class
	This class depends on java files created by Javacc. That is, you have to use JavaCC first to have a Parser.java 
	before compiling this java file.
	Usage: java CodeGenerator <file input>
	The output is the assembly code printed out to STD
	Reminder: 
	This class now only works with very simple input, such as an integer assignment to variables.
	Future improvement is needed.
*/
public class CodeGenerator {
	private SymbolTable table;
	private Stack<String> freeRegister;
	//Assume initially we have 5 free registers
	public CodeGenerator(SymbolTable dict) {
		table = dict;
		StringBuilder reg = new StringBuilder(2);
		reg.append("R");
		freeRegister = new Stack<>();
		for (int count = 4; count >= 0; count--) {
			if (count == 4) {
				reg.append(count);
				freeRegister.push(reg.toString());
			} else {
				reg = reg.deleteCharAt(1);
				reg.append(count);
				freeRegister.push(reg.toString());
			}
		}
	}

	public void generateCode(BinaryExpression root, boolean goLeft) {
		if (root == null) {
			return;
		}
		//if node is left leaf
		if (root.getLeft() == null && root.getRight() == null && goLeft) {
			if (isNumeric(root.getOp())) {
				//means that this node is part of an expression
				//maybe need to load to the register
			} 
		} 
		//if node has a right child leaf
		else if (root.getRight() != null && root.getRight().getLeft() == null && root.getRight().getRight() == null) { 
			generateCode(root.getLeft(), true);
			if (root.getOp().equals("=")) {
				String rightOp = root.getRight().getOp();
				String regUsed = freeRegister.pop();
				if (isNumeric(rightOp)) { //means that assigning a numeric value
					System.out.println("li " + regUsed + " " + rightOp);
				} else {
					System.out.println("lw " + regUsed + " " + rightOp);
				}
				System.out.println("sw " + regUsed + " " + root.getLeft().getOp());
				freeRegister.push(regUsed);
			}
		} else {
			generateCode(root.getLeft(), true);
			generateCode(root.getRight(), false);
			String operator = root.getOp();
			if (operator.equals("*") || operator.equals("+")) {
				System.out.println(root.getOp() + " R1 R2 R3");	
			}
 			
		}

	}
	//to check if a string is just a number
	private boolean isNumeric(String a) {
		return a.matches("[0-9]+");
	}

	public static void main(String[] args) throws ParseException, FileNotFoundException {
		Parser parser = new Parser(new FileInputStream (args[0]));
	    HashSet<String> st = new HashSet<String>();
	    SymbolTable table = new SymbolTable(st);
	    BinaryExpression parsedTree = parser.Input(table);
	    System.out.println(parsedTree.toString());
	    table.Print();
	    CodeGenerator codeGen = new CodeGenerator(table);
	    codeGen.generateCode(parsedTree.getRight(), false); //as the left subtree only contains declarations
	}
}