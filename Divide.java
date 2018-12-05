package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Divide extends BinaryOperator{
	public static final Divide staticDivide = new Divide();
	
	public static Operation f(Node a, Node b) {
		return BinaryOperator.createConnection(a, b, staticDivide);
	}
	
	private Divide() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.divide(feeders.get(0).value, feeders.get(1).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(Matrix.multiply(currentGrad ,Matrix.divide(1, feeders.get(1).value)),
				Matrix.multiply(currentGrad, Matrix.divide(-1, Matrix.multiply(feeders.get(1).value, feeders.get(1).value)))));
	}

}
