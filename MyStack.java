
import java.util.ArrayList;


/** Interface for a generic Stack data structure
 * @param <T> data type
 */

public class MyStack <T> implements StackInterface<T> {
 
	private int size;
	private int top;
	private ArrayList<T> stack;
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	public MyStack(int s)
	{
		this.size = s;
		top = -1;
		stack = new ArrayList<T>(size);
	}
	public MyStack()
	{
		size = 25;
		top = -1 ;
		stack = new ArrayList<T>(size);
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty()
	{
		if(top == -1)
			return true;
		else
			return false;
		
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull()
	{
		if (top == size -1)
			return true ;
		else
			return false;
	}
	

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	public T pop() throws StackUnderflowException
	{
		int item;
		
		if(top==-1)
		{
			throw new StackUnderflowException("The Stack is empty");
		}
		else
		{
			item = top;
			top--;
			T popped = stack.get(item);
			stack.remove(item);
			return popped;
			
	     }
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	public T top() throws StackUnderflowException
	{
		
		if(top == -1)
		{
			throw new StackUnderflowException("The Stack is empty");
		}
		else
		{
			return stack.get(top);
	    }
	}
	

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size()
	{
		int num=0;
		for(int i=0;i<stack.size()&&!stack.get(i).equals(null);i++)
			num++;
		return num;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	public boolean push(T e) throws StackOverflowException
	{
		if (top == size - 1) 
		{
            throw new StackOverflowException("Push on an empty stack.");
        }else{
        	top++;
        	stack.add(top,e);
        	
           // stack[++top] = (T) e;
            return true;
        }
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString()
	{
		String s ="";
		for(int i=0; i<stack.size(); i++)
		{
			s+=stack.get(i);
		}
		return s;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter)
	{
		String s ="";
		for(int i=0; i<stack.size(); i++)
		{
			s+=stack.get(i);
			if(i+1<stack.size()&&!stack.get(i+1).equals(null))
				s+= delimiter;
		}
		return s;
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  */
	public void fill(ArrayList<T> list)
	{
		ArrayList<T> copyList = new ArrayList<T> (list.size());
		for(int i=0; i<list.size(); i++)
		{
			copyList.add(list.get(i));
		}
		for(int i=0; i<copyList.size();i++)
		{
			stack.add(i, copyList.get(i));
			top++;
		}
	}
 
}
