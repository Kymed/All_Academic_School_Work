import java.io.Serializable;

public abstract class ExternalThreaded extends InnerThreaded implements Serializable {
	
	private static final long serialVersionUID = 5801942167162967899L;
	private double length;
	public ExternalThreaded(double unitPrice, int numPerUnit, Threads thread, double length, Enum<Materials.ThreadedMaterials> material, Enum<?> finish) throws IllegalFastener {
		super(unitPrice, numPerUnit, thread, material, finish);
		setLength(length);
	}
	
	private void setLength(double length) throws IllegalFastener {
		if (length < (1d/2d) || length > 20d) {
			throw new IllegalFastener("Incorrect length in inches");
		}
		if (length <= 6d && length % (1d/4d) != 0d) {
			throw new IllegalFastener("Lengths between 1/2\" to 6\" must be in units of 1/4\"");
		}
		if (length >= 6d && length <= 11d && length % (1d/2d) != 0d) {
			throw new IllegalFastener("Lengths between 6\" to 11\" must be in units of 1/2\"");
		}
		if (length >= 11d && length % 1d != 0) {
			throw new IllegalFastener("Lengths between 11\" and 20\" must be in units of 1\"");
		}
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "" + Double.toString(length) + "\" long, " + super.toString();
	}
	
}