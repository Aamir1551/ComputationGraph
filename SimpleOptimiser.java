package ComputationalGraph;

public class SimpleOptimiser extends Optimiser{
	
	double l_r = 0.1;
	
	public SimpleOptimiser(Session sess) {
		super(sess);
	}

	@Override
	public Matrix getGradient(Node i) {
		return Matrix.multiply(i.gradients.get(i.gradients.size() - 1), l_r) ;
	}
	
	public void setLr(double learning_rate) {
		l_r = learning_rate;
	}
	
}
