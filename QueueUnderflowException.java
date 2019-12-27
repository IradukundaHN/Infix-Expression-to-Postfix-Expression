
@SuppressWarnings("serial")
public class QueueUnderflowException extends RuntimeException {

	public QueueUnderflowException ()  {}
	
	public QueueUnderflowException (String message)
	{
		super(message);
	}

}