package ogp.balls;

import java.awt.Color;

import ogp.BreakoutState;
import ogp.math.Circle;
import ogp.math.Point;
import ogp.math.Rectangle;
import ogp.math.Vector;
import ogp.ui.Canvas;
import ogp.util.MPOOPLegitGenerated;

/**
 * This class represents a ball.
 * A ball has a geometry (represents the shape and position of the ball),
 * a velocity and a behavior.
 *  
 * 
 * @invar | getAllowedArea().contains(getGeometry().getCenter())
 */
public class Ball
{


    /**
     * Determines shape and position of the ball.
     * 
     */
    private Circle geometry;

    /**
     * Expressed in distance per milliseconds.
     * 
     */
    private Vector velocity;

    /**
     * Determines how the ball behaves.
     * See BallBehavior.
     * 
     */
    private BallBehavior behavior;

    /**
     * The ball center must at all times fit inside this rectangle.
     * 
     * @representationObject
     * @invar | allowedArea != null
     */
    private final Rectangle allowedArea;
    
    

    public Ball(Rectangle allowedArea, Circle geometry, Vector velocity, BallBehavior behavior)
    {

        this.allowedArea = allowedArea;
        this.geometry = null;
        this.velocity = null;
        this.behavior = null;
    }


    public Circle getGeometry()
    {
        return null;
    }


    public Vector getVelocity()
    {
        return null;
    }

    /**
     * Returns the ball's allowed area.
     * 
     * @creates | result
     * @post | result != null
     */
    public Rectangle getAllowedArea()
    {
        return allowedArea;
    }

    /**
     * Returns this ball's behavior.
     * 
     */
    public BallBehavior getBehavior()
    {
        return null;
    }

    /**
     * @post | result != null
     */
    public Color getColor()
    {
        return Color.WHITE;
    }

    /**
     * Returns this ball's center.
     */
    public Point getCenter()
    {
        return null;
    }

    /**
     * Updates the ball's state.
     * 
     * LEGIT
     * 
     * @pre | state != null
     * @pre | elapsedMilliseconds >= 0
     * @mutates | this
     * @mutates | state
     */
    @MPOOPLegitGenerated
    public void tick(BreakoutState state, long elapsedMilliseconds)
    {
        this.behavior.update(state, this, elapsedMilliseconds);
    }

    /**
     * Moves the ball elapsedMilliseconds into the future.
     * This method does not take into account collisions with other elements:
     * it simply moves the ball in a straight line.
     * 
     * @mutates_property | getGeometry()  
     */
    public void move(long elapsedMilliseconds)
    {
        
    }

    /**
     * Computes the position the ball would be after elapsedMilleconds time passes.
     * Does not take into account collisions with other elements. 
     * 
     */
    public Circle computeDestination(long elapsedMilliseconds)
    {
        return null;
    }

    /**
     * Updates the ball's geometry.
     * 
     * @pre | geometry != null
     * @pre | getAllowedArea().contains(geometry)
     * @post | getGeometry() == geometry
     * @mutates_properties | getGeometry()
     */
    public void setGeometry(Circle geometry)
    {
        this.geometry = geometry;
    }

    /**
     * Updates the ball's velocity.
     * 
     * @pre | velocity != null
     * @post | getVelocity().equals(velocity)
     * @mutates_properties | getVelocity()
     */
    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }



    /**
     * Paints this ball onto the canvas.
     * 
     * LEGIT
     * 
     * @pre | canvas != null
     * @mutates | canvas
     */
    @MPOOPLegitGenerated
    public void paint(Canvas canvas)
    {
        this.behavior.paint(canvas, this);
    }

    /**
     * Sets the ball's behavior.
     */
    public void setBehavior(BallBehavior behavior)
    {
        this.behavior = null;
    }

    
    
    
    
}
