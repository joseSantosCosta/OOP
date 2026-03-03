package ogp.walls;

import ogp.Collision;
import ogp.balls.Ball;
import ogp.math.Vector;
import ogp.util.MPOOPLegitGenerated;

/**
 * LEGIT
 * 
 * A wall is an invisible boundary to the game world.
 * The game world is bounded by three walls: to the left, to the right and at the top.
 * Balls bounce off these walls.
 * 
 * Walls can be seen as infinite long lines.
 * 
 * @immutable
 */
@MPOOPLegitGenerated
public class Wall
{
	/**
	 * @invar | wallKind != null
	 */
	private WallKind wallKind;
	

	private final long coordinate; //x coord. for left and right wall. Y coord for top wall.
	

	public Wall(WallKind wallKind, long coordinate) {
		if ( wallKind == null) { throw new IllegalArgumentException(); }
		
		this.coordinate = coordinate;
		this.wallKind = wallKind;
	}
	
	
	public WallKind getWallKind() {
		return wallKind;
	}
	
    /**
     * Computes the collision between this wall and the given ball.
     * Returns null if there is no collision. 
     * 
     * @inspects | ball
     * @pre | ball != null
     */
    public Collision findCollision(Ball ball) {
    	switch (this.wallKind) {
    	
    		case LEFT -> {
    	        var ballPosition = ball.getGeometry().getLeftmostPoint();
    	        var ballVelocity = ball.getVelocity();

    	        if ( ballVelocity.x() < 0 && ballPosition.x() >= this.coordinate )
    	        {
    	            var t = (ballPosition.x() - this.coordinate) / -ballVelocity.x();
    	            
    	            return new Collision(t, Vector.KILO_LEFT);
    	        }

    	        return null;
    		}
    		
    		case RIGHT -> {
    	        var ballPosition = ball.getGeometry().getRightmostPoint();
    	        var ballVelocity = ball.getVelocity();

    	        if ( ballVelocity.x() > 0 && ballPosition.x() <= this.coordinate )
    	        {
    	            var t = (this.coordinate - ballPosition.x()) / ballVelocity.x();

    	            return new Collision(t, Vector.KILO_LEFT);
    	        }

    	        return null;
    		}
    		
    		case TOP -> {
    	        var ballPosition = ball.getGeometry().getTopmostPoint();
    	        var ballVelocity = ball.getVelocity();

    	        if ( ballVelocity.y() < 0 && ballPosition.y() > this.coordinate )
    	        {
    	            var t = (ballPosition.y() - this.coordinate) / -ballVelocity.y();

    	            return new Collision(t, Vector.KILO_DOWN);
    	        }
    	        return null;
    		}
    		
    	}
    	return null; //default
    }
    
    /**
     * Normal unit vector.
     * It is perpendicular to the wall.
     * It must have length 1.
     * It must point in the direction the ball is coming from.
     * 
     * 
     * @post | result != null
     * @post | result.isUnitVector()
     */
    public Vector getNormal() {
    	return switch (this.wallKind) {
    		case LEFT -> Vector.RIGHT;
    		case RIGHT -> Vector.LEFT;
    		case TOP -> Vector.DOWN;
    	};
    }
}
