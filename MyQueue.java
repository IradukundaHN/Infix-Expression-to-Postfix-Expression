import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{

	private int size;
	private int front;
	private int rear;
	private int num;
	private ArrayList<T> queue;
	
	/** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue
	 * 
	 */
	public MyQueue(int sizeQueue)
	{
		this.size = sizeQueue;
		front = 0;
		rear = 0;
		queue = new ArrayList<T>(size);
	}
	public MyQueue()
	{
		size = 20;
		front = 0;
		rear = 0;
		queue = new ArrayList<T>(size);
	}
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(num==0)
			return true;
		else
			return false;
	}

	/**
	 * Determines of the Queue is empty
	 * @return true is full, and false otherwise
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if (num == size)
			return true ;
		else
			return false;
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		int frontPoint;
		
		if(num == 0)
		{
			throw new QueueUnderflowException("Dequeue on an empty queue.");
		}
		else 
		{
			frontPoint = front;
			front = (front+1)%size;
			num--;
			
			return (T) queue.get(frontPoint);
		}
	}
	
	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return num;
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(num==size)
		{
			throw new  QueueOverflowException("Queue overflow");
		}
		else 
		{
			//queue[rear++] = (T) e;
			num++;
			queue.add(rear, (T) e);
			rear =(rear+1)%size;
			return true;
		}
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString()
	{
		String s ="";
		for(int i=0; i<queue.size(); i++)
		{
			s+=queue.get(i);
		}
		return s;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String s ="";
		for(int i=0; i<queue.size(); i++)
		{
			s+=queue.get(i);
			if(i+1<queue.size()&&!queue.get(i+1).equals(null))
				s+= delimiter;
		}
		return s;
	}

	/**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyList = new ArrayList<T> (list.size());
		for(int i=0; i<list.size(); i++)
		{
			copyList.add(i,list.get(i));
		}
		for(int i=0; i<copyList.size();i++)
		{
			queue.add(i, copyList.get(i));
			num++;
		}
	}

}
