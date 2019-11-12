import java.io.Serializable;

public class WingNut extends InnerThreaded implements Serializable{
	
	private static final long serialVersionUID = 605932837325341333L;

	public WingNut(Threads thread, Enum<Materials.ThreadedMaterials> material, Enum<Finishes.WingNutFinish> finish, double unitPrice, int numPerUnit)
			throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, material, finish);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Wing Nut " + super.toString();
	}

}
