
@SuppressWarnings("serial")
public class QueueOverflowException extends RuntimeException {

	public QueueOverflowException ()  {}
	
	public QueueOverflowException (String message)
	{
		super(message);
	}

}