import java.io.Serializable;

public abstract class Nut extends ThreadedFastener implements Serializable {
	
	private static final long serialVersionUID = -7704059657296660683L;

	public Nut(double unitPrice, int numPerUnit, Threads thread, Enum<?> material, Enum<?> finish)
			throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, material, finish);
	}
	
	@Override
	public String toString() {
		return "" + super.toString();
	}

}
