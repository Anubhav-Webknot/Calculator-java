package org.example;
import java.util.*;

abstract class Operation {
    public abstract int operate(int operand1, int operand2);
}

class Addition extends Operation {
    @java.lang.Override
    public int operate(int operand1, int operand2) {
        return operand1+operand2;
    }
}

class Substraction extends Operation{
    @java.lang.Override
    public int operate(int operand1, int operand2) {
        return operand1-operand2;
    }
}

class Multiplication extends Operation{
    @java.lang.Override
    public int operate(int operand1, int operand2) {
        return operand1*operand2;
    }
}

class Division extends Operation{
    @java.lang.Override
    public int operate(int operand1, int operand2) {
        if (operand2 == 0) {
            throw new ArithmeticException("Division by zero error");
        }
        return operand1 / operand2;
    }
}
public class Calculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("\t\t\t\t\t\t\t\tWelcome to my Calculator");
            System.out.println("\t\t\t\tOur calculator can perform the following operations for you : ");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t1. Addition");
            System.out.println("\t\t\t\t\t\t\t\t2. Substraction");
            System.out.println("\t\t\t\t\t\t\t\t3. Multiplication");
            System.out.println("\t\t\t\t\t\t\t\t4. Division");
            System.out.println();
            System.out.println("Type quit for exit");
            System.out.println();
            System.out.println("\t\t\t\tEnter your expression : ");
            java.lang.String expression = sc.nextLine();
            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("You have exited the calculator.\nBye.");
                break;
            }
            System.out.println("the expression is : " + expression);
            try {
                int answer = calculate(expression);
                System.out.println("Answer = " + answer);
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();

        }
    }
    public static int calculate(String expression) {
        int result = 0;
        char[] input = expression.toCharArray();
        expression = expression.replaceAll("\\s+", "");
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < input.length; i++) {
            if (input[i] >= '0' && input[i] <= '9') {
                StringBuilder sb = new StringBuilder();
                while (i < input.length && (input[i] >= '0' && input[i] <= '9')) {
                    sb.append(input[i++]);
                }
                operands.push(Integer.parseInt(sb.toString()));
                i--;
            } else if (input[i] == '(')
                operators.push(input[i]);
            else if (input[i] == ')') {
                while (operators.peek() != '(') {
                    operands.push(evaluate(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.pop();
            } else if (input[i] == '+' || input[i] == '-' || input[i] == '*' || input[i] == '/') {

                while (!operators.empty() && hasPrecedence(input[i], operators.peek())) {
                    operands.push(evaluate(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.push(input[i]);
            }
        }
        while (!operators.empty()) {
            operands.push(evaluate(operators.pop(), operands.pop(), operands.pop()));
        }
        result = operands.pop();
        return result;
    }
     public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;

        else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }

        else
            return true;

    }

    public static int evaluate(char op,int b,int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");

                return a / b;
        }
        return 0;

    }
}

