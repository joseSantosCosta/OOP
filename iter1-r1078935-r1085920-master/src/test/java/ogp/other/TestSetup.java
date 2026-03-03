package ogp.other;

import ogp.BreakoutState;
import ogp.GameMapParser;
import ogp.balls.BallBehavior;
import ogp.math.Circle;
import ogp.math.Point;
import ogp.math.Vector;
import ogp.util.MPOOPLegitGenerated;

/**
 * LEGIT
 */
@MPOOPLegitGenerated
public class TestSetup {
	
	private TestSetup() {};
	
	

	
	private static final int FACTOR = 100;
	
	/**
	 * Returns 7 lines of size 7.
	 * post: the first line is "2  1   " where 2/1 must be replaced by the corr. input strings.
	 * 
	 * throws if either character string has length != 1
	 * either character can be blank " ".
	 */
	public static String[] mkHitMap(String character, String character2) {
		if (character.length() != 1) { throw new IllegalArgumentException(); }
		if (character2.length() != 1) { throw new IllegalArgumentException(); }
		String line0 = character2 + "  " + character + "   ";
		String line1 = "       ";
		return new String[] { line0, line1, line1, line1, line1, line1, line1};
	}
	
	/**
	 * Returns a BreakoutState where a ball (see behavior) is about to hit a block (see character).
	 * Ticking the state 120ms should suffice for this
	 * 
	 * @param character | string code for the type of brick. may be blank " ". The ball is about to hit that one.
	 * @param character2 | string code for an additional brick. Useful in order
	 *   | to not be in a losing state after having hit the other brick.
	 * @param behavior | the ball type about to hit the block
	 */
	public static BreakoutState mkHitState(String character, String character2, BallBehavior behavior) {
		var state = GameMapParser.parseNoBalls(mkHitMap(character, character2),100*FACTOR,30*FACTOR);
		Point pos = state.getBrickGrid().getBrickAt(new Point(3,0)).getGeometry().getBottomCenter().add(
				new Vector(0, 3000));
		state.addBall(new Circle(pos,500), new Vector(0,-33), behavior);
		return state;
	}
	

}
