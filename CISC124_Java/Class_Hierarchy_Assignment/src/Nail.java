import java.io.Serializable;

public abstract class Nail extends Fastener implements Serializable {
	
	private static final long serialVersionUID = 4415585953503017114L;
	private NailDesigns.CommonNailSizes size;
	private NailDesigns.CommonNailGauges gauge;
	private NailDesigns.CommonNailLengths length;
	public Nail(double unitPrice, int numPerUnit, Enum<?> finish, NailDesigns.CommonNailSizes size, NailDesigns.CommonNailGauges gauge, NailDesigns.CommonNailLengths length) throws IllegalFastener {
		super(unitPrice, numPerUnit, Materials.NailMaterials.Steel, finish);
		setNail(size, gauge, length);
	}
	
	private void setNail(NailDesigns.CommonNailSizes size, NailDesigns.CommonNailGauges gauge, NailDesigns.CommonNailLengths length) {
		this.size = size;
		this.gauge = gauge;
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "" + size.toString() + " size, " + length.toString() + "\" long, " + gauge.toString() + " gauge, " + super.toString();
	}

}
