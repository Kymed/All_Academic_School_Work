import java.io.Serializable;

public class WoodScrew extends Screw implements Serializable {
	
	private static final long serialVersionUID = 6746660853060819650L;
	private Enum<ScrewDesigns.WoodScrewPoints> point;
	public WoodScrew(double length, Threads thread, Enum<Materials.ThreadedMaterials> material, Enum<Finishes.ScrewFinish> finish, Enum<ScrewDesigns.ScrewHeads> head, Enum<ScrewDesigns.ScrewDrive> drive, Enum<ScrewDesigns.WoodScrewPoints> point, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, length, material, finish, head, drive);
		setPoint(point);
	}
	
	private void setPoint(Enum<ScrewDesigns.WoodScrewPoints> point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "Wood Screw, " + point.toString() + "point, " + super.toString();
	}

}
