package ComputationalGraph;
import java.util.ArrayList;
import java.util.Arrays;

public class Subtract extends BinaryOperator{
	public static final Subtract staticSubtract = new Subtract();
	
	public static Operation f(Node a, Node b) {
		return BinaryOperator.createConnection(a, b, staticSubtract);
	}
	
	private Subtract() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.subtract(feeders.get(0).value, feeders.get(1).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(currentGrad, Matrix.multiply(-1, currentGrad)));
	}
}
