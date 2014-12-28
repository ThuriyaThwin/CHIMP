package fluentSolver;

import htn.HTNMethod;

import java.util.logging.Logger;

import org.metacsp.framework.ConstraintNetwork;
import org.metacsp.utility.logging.MetaCSPLogging;

public class TestFluentNetworkSolver {

	public static void main(String[] args) {
		Logger logger = MetaCSPLogging.getLogger(TestFluentNetworkSolver.class);
		String[] symbolsPredicates = {"on", "robotat", "get_mug"};
		String[] symbolsMugs = {"mug1", "mug2", "mug3", "mug4", "mug5", "mug6", "mug7", "mug8", "mug9", "mug10", "none"};
		String[] symbolsPlAreas = {"pl1", "pl2", "pl3", "pl4", "pl5", "pl6", "pl7", "pl8", "pl9", "pl10", "none"};
		String[] symbolsManAreas = {"ma1", "ma2", "ma3", "ma4", "ma5", "ma6", "ma7", "ma8", "ma9", "ma10", "none"};
		String[] symbolsPreAreas = {"pma1", "pma2", "pma3", "pma4", "pma5", "pma6", "pma7", "pma8", "pma9", "pma10", "none"};
		String[][] symbols = new String[5][];
		symbols[0] = symbolsPredicates;
		symbols[1] = symbolsMugs;
		symbols[2] = symbolsPlAreas;
		symbols[3] = symbolsManAreas;
		symbols[4] = symbolsPreAreas;
		logger.info("Test1");
		FluentNetworkSolver solver = new FluentNetworkSolver(0, 500, symbols, new int[] {1,1,1,1,1});
		logger.info("Test2");
		Fluent[] fluents = (Fluent[]) solver.createVariables(3);
		logger.info("Test3");
		ConstraintNetwork.draw(solver.getConstraintNetwork());
		
		fluents[0].setName("get_mug(mug1 pl1 none none)");
		fluents[1].setName("get_mug(mug1 pl1 none none)");
		fluents[2].setName("robotat(none ?pl none none)");
		

//		SimpleBooleanValueConstraint con2 = new SimpleBooleanValueConstraint(Type.UNARYTRUE);
//		con2.setFrom(fluents[1]);
//		con2.setTo(fluents[1]);
//		logger.info("Added con2? " + bsolver.addConstraint(con2));
		
//		try { Thread.sleep(5000); }
//		catch (InterruptedException e) { e.printStackTrace(); }
		
//		SimpleBooleanValueConstraint con1 = new SimpleBooleanValueConstraint(Type.EQUALS);
//		con1.setFrom(fluents[0]);
//		con1.setTo(fluents[1]);
//		logger.info("Added con1? " + bsolver.addConstraint(con1));
		
//		CompoundNameMatchingConstraint ncon0 = 
//				new CompoundNameMatchingConstraint(CompoundNameMatchingConstraint.Type.MATCHES);
//		ncon0.setFrom(fluents[0].getCompoundNameVariable());
//		ncon0.setTo(fluents[1].getCompoundNameVariable());
//		CompoundNameMatchingConstraintSolver nsolver = 
//				(CompoundNameMatchingConstraintSolver) solver.getConstraintSolvers()[0];
//		logger.info("Added ncon0? " + nsolver.addConstraint(ncon0));
		
		FluentConstraint fcon01 = new FluentConstraint(FluentConstraint.Type.MATCHES);
		fcon01.setFrom(fluents[0]);
		fcon01.setTo(fluents[1]);
		logger.info("Added fcon01? " + solver.addConstraint(fcon01));
		
		FluentConstraint fcon21 = new FluentConstraint(FluentConstraint.Type.PRE, new int[] {1,1, 2, 2});
		fcon21.setFrom(fluents[2]);
		fcon21.setTo(fluents[1]);
		logger.info("Added fcon21? " + solver.addConstraint(fcon21));
		
		
		FluentConstraint fcon11 = new FluentConstraint(FluentConstraint.Type.UNARYAPPLIED, new HTNMethod(null, null, null, null, null));
		fcon11.setFrom(fluents[1]);
		fcon11.setTo(fluents[1]);
		logger.info("Added fcon11? " + solver.addConstraint(fcon11));
		
		logger.info("Removing fcon11");
		solver.removeConstraint(fcon11);
		
		logger.info("Removing fcon21");
		solver.removeConstraint(fcon21);
		
	}

}
