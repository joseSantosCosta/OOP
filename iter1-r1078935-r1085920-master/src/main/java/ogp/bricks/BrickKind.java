package ogp.bricks;

/**
 * @immutable
 */
public enum BrickKind {
	STANDARD,
	SPAWNBALL,
	SPIKEY,
	SHRINKPADDLE,
	INVERTPADDLE;
	
	/**
	 * An example of how to do a case analysis on a BrickKind value.
	 * The switch expression uses arrows, which prevent falling through other cases
	 * (contrary to a standard switch statement where a break statement is accidentally missing).
	 */
	@SuppressWarnings("unused")
	private boolean isPaddleRelated(BrickKind bkind) {
		boolean out = switch (bkind) {
			case STANDARD -> false;
			case SPAWNBALL -> false;
			case SPIKEY -> false;
			case SHRINKPADDLE -> true;
			case INVERTPADDLE -> true;
		};
		return out;
	}
}


