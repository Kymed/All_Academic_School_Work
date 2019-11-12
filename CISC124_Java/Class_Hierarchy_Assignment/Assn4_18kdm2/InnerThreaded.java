import java.io.Serializable;

public abstract class InnerThreaded extends Fastener implements Serializable {
	
	private static final long serialVersionUID = -3120500555877331301L;
	private Threads diameter;
	
	public InnerThreaded(double unitPrice, int numPerUnit, Threads thread, Enum<Materials.ThreadedMaterials> material, Enum<?> finish) throws IllegalFastener {
		super(unitPrice, numPerUnit, material, finish);
		verifyMaterial(material, finish);
		setThread(thread);
	}
	
	private void verifyMaterial(Enum<?> material, Enum<?> finish) throws IllegalFastener {
		String finishName = finish.toString();
		if (material == Materials.ThreadedMaterials.Brass || material == Materials.ThreadedMaterials.Stainless_Steel) {
			if (finishName.equals("Chrome") || finishName.equals("Hot_Dipped_Galvanized") || finishName.equals("Yellow_Zinc") || finishName.equals("Zinc")) {
				throw new IllegalFastener("Illegal material for type Brass & Stainless Steel");
			}
		}
	}
	
	private void setThread(Threads thread) throws IllegalFastener {
		diameter = thread;
 	}
	
	@Override
	public String toString() {
		return "" + diameter.toString() + "thread, " + super.toString();
	}
	
}