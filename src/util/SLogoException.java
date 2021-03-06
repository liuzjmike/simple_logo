package util;

public class SLogoException extends RuntimeException {
	
	public static final String ZERO_DENOMINATOR = "The denominator cannot be zero.";
	public static final String WRONG_NUM_PARAMS = "Wrong number of parameters.";
	public static final String NO_PARAMS = "No parameters provided.";
	public static final String MISSING_LIST = "Missing List bracket(s).";
	public static final String NO_ACTIVE_TURTLE = "No turtle is active.";
	
	public static final String INSTANTIATION_ERROR = "Command is not found/instantiated: %s";
	
	public static final String STACK_UNDERFLOW = "Stack is empty. Cannot pop.";
	public static final String STACK_OVERFLOW = "Stack is full. Cannot push.";
	
	public static final String INVALID_COLOR_INDEX = "Color index is invalid.";
	public static final String COLOR_NOT_IN_PALETTE = "Color is not in palette";
	public static final String INVALID_KEY = "Key is invalid.";
	public static final String INVALID_COMMAND = "Command is not valid.";
	public static final String ILLEGAL_ID = "ID cannot be smaller than 1.";
	public static final String ILLEGAL_INPUT = "Input format is illegal: %s";
	
	public static final String INVALID_FILE = "File is invalid";
	
	public static final String HELP_VIEWER_FAILED = "HelpViewer failed to open.";
	public static final String INVALID_COLOR_BUTTON = "Color button is invalid.";
	
	
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
