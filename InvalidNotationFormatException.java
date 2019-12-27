
@SuppressWarnings("serial")
public class InvalidNotationFormatException extends RuntimeException {

	public InvalidNotationFormatException () 
	{
		super("Notation Format is incorrect");
	}
	
	public InvalidNotationFormatException(String message)
	{
		super(message);
	}

}

