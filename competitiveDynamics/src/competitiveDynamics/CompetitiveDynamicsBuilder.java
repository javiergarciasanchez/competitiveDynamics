package competitiveDynamics;

import java.awt.Component;

import javax.swing.JOptionPane;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import static repast.simphony.essentials.RepastEssentials.*;

public class CompetitiveDynamicsBuilder extends DefaultContext<Object>
		implements ContextBuilder<Object> {

	@Override
	public Context<Object> build(Context<Object> context) {

		if(checkParam()){
			
			new SupplyManager(context);
			
			RunEnvironment.getInstance().endAt((Double) GetParameter("stopAt"));
			
			return context;
		} else {
			EndSimulationRun();
			return context;
		}
	}
	
	private static boolean checkParam() {
		/*
		 * Check consistency of Learning rate Mean
		 */
		double lRMean = (Double) GetParameter("learningRateMean");

		if (lRMean >= 1.0 || lRMean <= 0.5) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame,
					"The Learning Rate Mean should be < 1 and > 0.5",
					"Inconsistent Learning Rate Parameter",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}
