import java.io.Serializable;

public abstract class Fastener implements Serializable {

	private static final long serialVersionUID = -2088928842456670824L;
	
	private Enum<?> material;
	private Enum<?> finish;
	private double unitPrice;
	private int numPerUnit;
	
	public Fastener(double unitPrice, int numPerUnit, Enum<?> material, Enum<?> finish) throws IllegalFastener {
		setUnitPrice(unitPrice);
		setNumPerUnit(numPerUnit);
		setMaterial(material);
		setFinish(finish);
	}
	
	private void setMaterial(Enum<?> material) {
		this.material = material;
	}
	
	private void setFinish(Enum<?> finish) {
		this.finish = finish;
	}
	
	private void setUnitPrice(double unitPrice) throws IllegalFastener {
		if (unitPrice < 0f) {
			throw new IllegalFastener("Unit price must be more than 0");
		}
		
		this.unitPrice = unitPrice;
	}
	
	private void setNumPerUnit(int numPerUnit) throws IllegalFastener {
		if (numPerUnit < 0 ||  numPerUnit > 10000) {
			throw new IllegalFastener("Number per unit must be between 1 and 10000 inclusive");
		}
		
		if (numPerUnit != 1 && numPerUnit % 5 != 0) {
			throw new IllegalFastener("Number per unit must be 1 or divisible by 5");
		}
		
		this.numPerUnit = numPerUnit;
	}
	
	public Double getOrderCost(int units) {
		return unitPrice * units;
	}
	
	public String toString() {
		return "" + material.toString() + ", with a " + finish.toString() + " finish. " + Integer.toString(numPerUnit) + "in a unit $" + Double.toString(unitPrice) + " per unit.";
	}
	
	
}
