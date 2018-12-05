package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Reshape implements Operator{  //needs to be done
	
	public static final Reshape staticReshape = new Reshape();
	private static Integer nxtReshapeOpID = 0;
			
	public static Operation f(Node a, Constant b, Constant c) {
		Operation e = createConnection(a, b, c);
		e.op = staticReshape;
		return e;
	}
	
	public static Operation createConnection(Node a, Constant b, Constant c) {
		Operation e = new Operation("Reshape" + nxtReshapeOpID.toString(), null, staticReshape);
		Operator.connect(a, e);
		Operator.connect(b,e);
		Operator.connect(c, e);
		return e;
	}
	
	private Reshape() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.reshape(feeders.get(0).value, feeders.get(1).value, feeders.get(2).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(Matrix.reshape(currentGrad, new Matrix(1, 1, feeders.get(0).value.getDim1()), 
				new Matrix(1, 1, feeders.get(0).value.getDim2()))));
	}
	
}
