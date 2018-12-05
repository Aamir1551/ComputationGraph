package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Exp implements Operator{ //needs to be done
	
	public static final Exp staticExp = new Exp();
	private static Integer nxtExpOpID = 0;
	
	public static Operation f(Node a) {
		Operation c = new Operation("ExpOp" + nxtExpOpID, null, staticExp);
		c.feeders.add(a);
		nxtExpOpID+=1;
		return c;
	}
	
	private Exp() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.exp(feeders.get(0).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(Matrix.multiply(currentGrad, Matrix.exp(feeders.get(0).value))));
	}
	
}
