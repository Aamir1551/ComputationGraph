package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Add extends BinaryOperator{
	public static final Add staticAdd = new Add();
	
	public static Operation f(Node a, Node b) {
		return BinaryOperator.createConnection(a, b, staticAdd);
	}
	
	private Add() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.add(feeders.get(0).value, feeders.get(1).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(currentGrad, currentGrad));
	}
	
}
