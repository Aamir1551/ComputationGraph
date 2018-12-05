package ComputationalGraph;

public abstract class Optimiser {
	
	Session sess = null;
	
	public Optimiser(Session sess) {
		sess.run();
		this.sess = sess;
	}
	
	public void run() {
		this.initialiseRun();
		sess.computeGradients();
		for(Node i : this.sess.sessGraph.graphVariables) {
			i.value.subtract(getGradient(i));
		}	
	}
	
	private void initialiseRun() {
		this.sess.sessGraph.graphNodes.get(sess.sessGraph.graphNodes.size() -1).gradients.add(new Matrix(1, 1, 1));
		for(int i = this.sess.sessGraph.graphNodes.size() - 2;i>=0;i--) {
			this.sess.sessGraph.graphNodes.get(i).gradients.add(new Matrix(1, 1, 0));
		}
	}
	
	public abstract Matrix getGradient(Node i);

}
