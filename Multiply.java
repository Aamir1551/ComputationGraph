package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Multiply extends BinaryOperator{
	public static final Multiply staticMultiply = new Multiply();
	
	public static Operation f(Node a, Node b) {
		return BinaryOperator.createConnection(a, b, staticMultiply);
	}
	
	private Multiply() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.multiply(feeders.get(0).value, feeders.get(1).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(Matrix.multiply(currentGrad, feeders.get(1).value), 
				Matrix.multiply(currentGrad, feeders.get(0).value)));
	}
	
}
