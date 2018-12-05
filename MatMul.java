package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class MatMul extends BinaryOperator{
	public static final MatMul staticMatMul = new MatMul();
	
	public static Operation f(Node a, Node b) {
		return BinaryOperator.createConnection(a, b, staticMatMul);
	}
	
	private MatMul() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.matmul(feeders.get(0).value, feeders.get(1).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(Matrix.matmul(currentGrad, Matrix.transpose(feeders.get(1).value)), 
				Matrix.matmul(Matrix.transpose(feeders.get(0).value), currentGrad)));
	}
	
}
