import java.io.Serializable;

public class CommonNail extends Nail implements Serializable {

	public CommonNail(NailDesigns.CommonNailSizes size, NailDesigns.CommonNailLengths length, NailDesigns.CommonNailGauges gauge, Enum<Finishes.CommonNailFinish> finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(unitPrice, numPerUnit, finish, size, gauge, length);
	}
	
	@Override
	public String toString() {
		return "Common Nail, " + super.toString();
	}

}
