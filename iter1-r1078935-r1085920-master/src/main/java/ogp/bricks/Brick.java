package ogp.bricks;

import java.awt.Color;

import ogp.BreakoutState;
import ogp.balls.Ball;
import ogp.balls.BallBehavior;
import ogp.math.Circle;
import ogp.math.Point;
import ogp.math.Rectangle;
import ogp.math.Vector;
import ogp.ui.Canvas;

/**
 * 
 * All bricks have two properties:
 * - a rectangle (geometry), representing their position in the game world.
 * - a grid position, representing the position in the block grid
 * 
 * Also any brick is of some "kind", see BrickKind and how to do a case analysis on BrickKind values.
 */
public class Brick
{
	
    /**
     * @invar | geometry != null
     * @representationObject
     */
    private final Rectangle geometry;

    /**
     * @invar | gridPosition != null
     */
    private final Point gridPosition;
    
    /**
     * @invar | brickKind != null
     */
    private final BrickKind brickKind;
    

    public Brick(Rectangle geometry, Point gridPosition, BrickKind brickKind)
    {


        this.brickKind = null;
        this.geometry = null;
        this.gridPosition = null;
        
    }
    
    
    public BrickKind getBrickKind() { return this.brickKind; }



    /**
     * Returns the grid position.
     * 
     * @post | result != null
     */
    public Point getGridPosition()
    {
        return this.gridPosition;
    }

    /**
     * Returns the rectangle occupied by this brick in the game world.
     * 
     * @creates | result
     * @post | result != null
     */
    public Rectangle getGeometry()
    {
        return null;
    }
    
    public String getLabel() {
    	return null;
    }
    
    public Color getLabelColor() {
    	return getColorPriv();
    }

    public boolean isIndestructible() {
		return false;
	}

	/**
     * Paints the brick using the canvas, including its label.
     * 
     * 
     * @pre | canvas != null
     * @mutates | canvas
     */
    public void paint(Canvas canvas)
    {
    	
    }

    /**
     * Used in the paint method to determine the color of the rectangle on screen.
     * See ColorSet as well.
     * 
     * @post | result != null
     */
    private Color getColorPriv()
    {
        return null;
    }
    
    public Color getColor() {
    	return getColorPriv();
    }

    /**
     * Called when this brick has been hit by a ball.
     * It is given the full BreakoutState and the Ball which has hit the brick.
     * This method should update the state and/or ball,
     * e.g., remove the brick from the state, change the paddle's size, etc.
     * 
     * Speed reflection of the ball is not handled here.
     * 
     * 
     */
    public void hit(BreakoutState state, Ball ball) {
    	
    	switch (this.brickKind) {
    	
    		case STANDARD -> {
    			state.getBrickGrid().removeBrick(this);
    		}
    		
    		case SPAWNBALL -> {
    			
    			Point c = state.getBrickGrid().getBoundingRectangle().getBottomCenter();
    			Vector up = new Vector( 0, -35);
    			BallBehavior behavior = new BallBehavior();
    			state.addBall(
    					new Circle(c, 500),
    					up,
    					behavior);
    			state.addBall(
    					new Circle(c, 500),
    					up,
    					behavior);
    		}
    		
    		case SPIKEY -> {
    			for (int i = 0 ; i < 5 ; i++) { state.lose1Life(); }
    		}
    		
    		case SHRINKPADDLE -> {
    			
    		}
    		
    		case INVERTPADDLE -> {
    			
    		}
    		
    	}
    	
    }


}
