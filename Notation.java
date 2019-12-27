import java.io.IOException;
import java.util.Stack;
/**
 * Notation class will have a method infixToPostfix to convert infix notation to postfix notation that will take in a string and return a string, a method postfixToInfix to convert postfix notation to infix notation that will take in a string and return a string, and a method to evaluatePostfix to evaluate the postfix expression. It will take in a string and return a double.
 * @author Hugues Nelson Iradukunda
 *
 */
public class Notation {

	
	public static MyStack<Character> stack = new MyStack<Character>();
	public static MyQueue<Character> queue = new MyQueue<Character>();
	
	/**
	 * Method to convert Infix to postfix expression
	 * @param expression
	 * @return
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 * @throws QueueUnderflowException
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String expression) throws QueueOverflowException, StackOverflowException, StackUnderflowException, QueueUnderflowException, InvalidNotationFormatException
	{
		String result = "";
		queue = new MyQueue<Character>();
		for(int i=0; i<expression.length(); i++)
		{
			char c = expression.charAt(i);
		
			if(c==' ')
				continue;
			else if(Character.isDigit(c))
			{
				queue.enqueue(c);
			}
			else if(c=='(')
			{
				stack.push(c);
			}
			else if(c=='+'||c=='-'||c=='/'||c=='*')
			{
				while (!stack.isEmpty()&&(precedenceCheck(c))<=precedenceCheck(stack.top()))
				{
					queue.enqueue(stack.pop());			        	
				}
				stack.push(c);
			}
			else if(c==')')
			{
				while (!stack.isEmpty()&&stack.top()!='('&&(stack.top()=='-'||stack.top()=='+'||stack.top()=='*'||stack.top()=='/'))
				{
					queue.enqueue(stack.pop());
				}
				if(!stack.isEmpty()&&stack.top()=='(')
				{
					stack.pop();
				}
				else 
					throw new InvalidNotationFormatException("The notation format is incorrect");
			}
		}
		while(!stack.isEmpty())
		{
			queue.enqueue(stack.pop());
		}
		while(!queue.isEmpty())
			result+=queue.dequeue();
			
		return result;
	}
	
	/**
	 * Method to convert Postfix to Infix expression
	 * @param expression
	 * @return result
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String expression) throws StackOverflowException, StackUnderflowException, InvalidNotationFormatException
	{
		String result = "";
		MyStack<String> s = new MyStack<String>();
		for(int i=0; i<expression.length(); i++)
		{
			char c = expression.charAt(i);
			if(c==' ')
				continue;
			else if(Character.isDigit(c))
				s.push(""+c);
			else if(c=='+'||c=='*'||c=='/'||c=='-')
			{
				if(s.size()<2)
					throw new InvalidNotationFormatException("The notation format is incorrect");
				else
				{
					String leftOperator;
					String rightOperator;
					String op ="";
					leftOperator = s.pop();
					rightOperator = s.pop();
					op = "("+rightOperator+c+leftOperator+")";
					s.push(op);
				}
			}		
		}
		result += s.pop();
		
		return result;
	}
	
	/**
	 * Method to evaluate Postfix Expression
	 * @param expression
	 * @return result 
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String expression) throws StackOverflowException, StackUnderflowException, InvalidNotationFormatException
	{
		double result=0.0;
		MyStack<String> s = new MyStack<String>();
		for(int i=0; i<expression.length(); i++)
		{
			char c = expression.charAt(i);
			if(c==' ')
				continue;
			else if(Character.isDigit(c))
				s.push(""+c);
			else if(c=='+'||c=='*'||c=='/'||c=='-')
			{
				if(s.size()<2)
					throw new InvalidNotationFormatException("The notation format is incorrect");
				else
				{
					String leftOperator;
					String rightOperator;
					leftOperator = s.pop();
					double op1 = Double.parseDouble(leftOperator);
					rightOperator = s.pop();
					double op2 = Double.parseDouble(rightOperator);
					
					switch(c)
					{
						case '+':
							result = op1 + op2;
							break;
						case '-':
							result = op2 - op1;
							break;
						case '*':
							result = op1 * op2;
							break;
						case '/':
							result = op2 / op1;
							break;
					}
					s.push(""+result);
				}
			}
		}
		if(stack.size()>1)
			throw new InvalidNotationFormatException("The notation is incorrect");
		else
			return result;
	}
	
	/**
	 * Method to check the precedence of the operators
	 * @param op
	 * @return value based on order
	 */
	public static int precedenceCheck(char op) {
		
			switch (op) 
			{
				case '+':
				case '-':
					return 1;
				case '*':
				case '/':
					return 2;
				default:
					return 0;
			}
		}
	}
    

        	
       