import java.io.Serializable;

public class CarriageBolt extends Bolt implements Serializable {
	
	private static final long serialVersionUID = 6629003053612281848L;

	public CarriageBolt(double length, Threads thread, Enum<Materials.ThreadedMaterials> material, Enum<Finishes.BoltFinish> finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, length, material, finish);
	}
	
	@Override
	public String toString() {
		return "Carriage Bolt " + super.toString();
	}

}
