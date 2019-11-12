import java.io.Serializable;

public abstract class Screw extends ExternalThreaded implements Serializable {
	
	private static final long serialVersionUID = -3286290894596555314L;
	private Enum<ScrewDesigns.ScrewHeads> head;
	private Enum<ScrewDesigns.ScrewDrive> drive;
	public Screw(double unitPrice, int numPerUnit, Threads thread, double length, Enum<Materials.ThreadedMaterials> material, Enum<?> finish, Enum<ScrewDesigns.ScrewHeads> head, Enum<ScrewDesigns.ScrewDrive> drive)
			throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, length, material, finish);
		setScrew(head, drive);
	}
	
	private void setScrew(Enum<ScrewDesigns.ScrewHeads> head, Enum<ScrewDesigns.ScrewDrive> drive) throws IllegalFastener {
		this.head = head;
		this.drive = drive;
	}
	
	@Override
	public String toString() {
		return "" + head.toString() + " head, " + drive.toString() + " drive, " + super.toString();
	}
	
}
