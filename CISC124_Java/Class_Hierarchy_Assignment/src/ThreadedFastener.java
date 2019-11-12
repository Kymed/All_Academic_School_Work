import java.io.Serializable;

public abstract class ThreadedFastener extends Fastener implements Serializable {
	
	private static final long serialVersionUID = -3120500555877331301L;
	private Threads diameter;
	
	public ThreadedFastener(double unitPrice, int numPerUnit, Threads thread, Enum<?> material, Enum<?> finish) throws IllegalFastener {
		super(unitPrice, numPerUnit, material, finish);
		setThread(thread);
	}
	
	private void setThread(Threads thread) throws IllegalFastener {
		diameter = thread;
 	}
	
	@Override
	public String toString() {
		return "" + diameter.toString() + "thread, " + super.toString();
	}
	
}
