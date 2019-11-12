import java.io.Serializable;

public abstract class Bolt extends ExternalThreaded implements Serializable{
	
	private static final long serialVersionUID = 821042847710334954L;

	public Bolt(double unitPrice, int numPerUnit, Threads thread, double length, Enum<Materials.ThreadedMaterials> material, Enum<Finishes.BoltFinish> finish) throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, length, material, finish);
	}
	
	@Override
	public String toString() {
		return "" + super.toString();
	}

}
