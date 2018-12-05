package ComputationalGraph;

public class Main {

	public static void main(String[] args) {
		//so basically backprop was working, bt then u did somat(u tried refactoring) and now backprop dnt work -  and then u just left it at that :)
		
		
		Variable a = new Variable("a", new Matrix(2,2, -4));
		Variable b = new Variable("b", new Matrix(2,2,10));
		Constant c = new Constant("c", new Matrix(2, 2, 5));
		Variable d = new Variable("d", new Matrix(2, 2, 6));
		Placeholder e = new Placeholder("e", new Matrix(2,2,6));

		
		Operation z1 = Add.f(a, b); //6
		Operation z2 = Subtract.f(c, d); //-1
		Operation z3 = Multiply.f(z1, z2); //-6
		Operation z4 = Divide.f(z2, e); //-1/6
		Operation z5 = Subtract.f(z3, z4); //5.83
		
		Session mysess = new Session("mysess", z5);
		
		
		mysess.run();
		mysess.getValues(z1, z2, z3, z4, z5, e, d);
		System.out.println("asd");
		
/*		Variable a = new Variable("a", new Matrix(3, 3, 4));
		Variable b = new Variable("b", new Matrix(3, 3, 2));sess
		Operation c = Multiply.f(a, b);
		
		Session mysess = new Session("mysess");
		mysess.run(null, c).get(0).print();
		System.out.print("s");*/
		
		SimpleOptimiser trainer = new SimpleOptimiser(mysess);
		trainer.l_r =  0.1;
		trainer.run();
		System.out.println("grads: ");
		trainer.sess.sessGraph.graphNodes.forEach(arg0 -> arg0.value.print());
		System.out.println("seppp");
		trainer.sess.sessGraph.graphNodes.forEach(arg0 -> System.out.println(arg0.name));
		trainer.sess.sessGraph.graphNodes.forEach(arg0 -> arg0.gradients.get(arg0.gradients.size() -1).print());
		
/*		for(Node i : trainer.graphNodes) {
			System.out.println(i.name);
			for(Node j : i.feedsGradParam.keySet()) {
				for(Node k : i.feedsGradParam.get(j)) {
					k.value.print();;
				}
			}
		}*/
		
		
		System.out.println("done");
		
		
	}

}
