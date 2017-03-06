package util;

public class SLogoException extends RuntimeException {
	
	public static final String ZERO_DENOMINATOR = "The denominator cannot be zero.";
	public static final String WRONG_NUM_PARAMS = "Wrong number of parameters.";
	public static final String MISSING_LIST = "Missing List bracket(s).";
	public static final String NO_VARIABLE_IN_SCOPE = "Variable does not exist in this scope.";
	public static final String NO_ACTIVE_TURTLE = "No turtle is active.";
	public static final String INSTANTIATION_ERROR = "Command is not found/instantiated.";
	public static final String STACK_UNDERFLOW = "Stack is empty. Cannot pop.";
	public static final String STACK_OVERFLOW = "Stack is full. Cannot push.";
	
	public static final String INVALID_COLOR_INDEX = "Color index is invalid.";
	public static final String INVALID_KEY = "Key is invalid.";
	public static final String ILLEGAL_ID = "ID cannot be smaller than 1.";
	public static final String ILLEGAL_INPUT = "Input format is illegal: %s";
	
	
	private static final long serialVersionUID = 1L;
    
    public SLogoException(String message) {
        super(message);
    }
    
    public SLogoException(String message, Object ... values) {
        super(String.format(message, values));
    }
    
    public SLogoException (Throwable cause) {
        super(cause);
    }
    
}
