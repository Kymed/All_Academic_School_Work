
public class IllegalFastener extends Exception {
	
	private static final long serialVersionUID = -5762001556201372193L;
	
	public IllegalFastener() {
		super("Illegal parameter value supplied to Fastener object");
	}
	
	public IllegalFastener(String message) {
		super(message);
	}

}
