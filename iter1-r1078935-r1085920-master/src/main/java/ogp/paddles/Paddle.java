package ogp.paddles;

import java.awt.Color;

import ogp.BreakoutState;
import ogp.Collision;
import ogp.balls.Ball;
import ogp.math.Interval;
import ogp.math.Point;
import ogp.math.Rectangle;
import ogp.math.Vector;
import ogp.ui.Canvas;
import ogp.util.MPOOPLegitGenerated;

/**
 * Represents the paddle in the breakout game.
 *
 * @invar | getTopCenter() != null
 * @invar | getAllowedInterval().isInside(getTopCenter().x() - getHalfWidth())
 * @invar | getAllowedInterval().isInside(getTopCenter().x() + getHalfWidth())
 */
public class Paddle
{
    public static final int HEIGHT = 1000;

    public static final int GROW_FACTOR = 1100;

    public static final int SHRINK_FACTOR = 900;


    private Point topCenter;

    private long halfWidth;

    private PaddleMotionDirection motionDirection;
    
    /**
     * @invar | invertedFuel >= 0
     */
    private int invertedFuel;

    /**
     * Speed at which paddle moves.
     * Whether the paddle actually moves is determined by motionDirection,
     * but if the paddle moves, it is at this speed.
     *
     * @invar | speed > 0
     */
    private final long speed;

    /**
     * The entire width of the paddle must fit inside this interval.
     */
    private final Interval allowedInterval;
    

    /**
     * Construct a paddle located around a given center in the field.
     * Note that we specify its half size instead of its full size.
     * This is to avoid the rounding errors that would occur if its size were odd.
     *
     */
    public Paddle(Interval allowedInterval, Point topCenter, long halfWidth, long speed)
    {
        

        this.topCenter = null;
        this.halfWidth = 0;
        this.motionDirection = null;
        this.speed = 0;
        this.allowedInterval = null;
        
        invertedFuel = 0; //not inverted by default
    }
    
    public boolean isInverted() {
    	return invertedFuel > 0;
    }


    public PaddleMotionDirection getMotionDirection()
    {
        return null;
    }


    public void setMotionDirection(PaddleMotionDirection direction)
    {
        
    }

    /**
     * Return the center point of this paddle.
     *
     * @post | result != null
     */
    public Point getTopCenter()
    {
        return topCenter;
    }


    public Interval getAllowedInterval()
    {
        return null;
    }

    /**
     * @post | result > 0
     */
    public long getHalfWidth()
    {
        return this.halfWidth;
    }

    /**
     * @post | result == 2 * getHalfWidth()
     */
    public long getWidth()
    {
        return this.halfWidth * 2;
    }

    /**
     * Returns the paddle's height.
     *
     * @post | result == Paddle.HEIGHT
     */
    public long getHeight()
    {
        return HEIGHT;
    }

    /**
     * Returns the paddle's speed.
     *
     */
    public long getSpeed()
    {
        return speed;
    }

    /**
     * Returns a rectangle representing the paddle's shape.
     *
     */
    public Rectangle getGeometry()
    {
    	return null;
    }

    /**
     * Moves the paddle so that its top center x coordinate equals the given x.
     * Ensures that the paddle does not go outside the allowed area.
     *
     * @creates | getTopCenter()
     * @mutates_properties | getTopCenter()
     * @post | getTopCenter().equals(clamp(new Point(x, old(getTopCenter().y()))))
     */
    public void setTopCenterX(long x)
    {
        this.topCenter = clamp(new Point(x, topCenter.y()));
    }

    /**
     * Moves this Paddle a certain distance.
     * Ensures that the paddle remains within the allowed area.
     *
     * @creates | getTopCenter()
     * @mutates_properties | getTopCenter()
     * @post | getTopCenter().equals(clamp(new Point(old(getTopCenter().x()) + distance, old(getTopCenter().y()))))
     */
    public void move(long distance)
    {
        setTopCenterX(topCenter.x() + distance);
    }

    /**
     * Computes the new state of the paddle in elapsedMilliseconds, i.e.,
     * moves the paddle a certain distance, determined by its motion direction
     * and speed.

     * @mutates_properties | getTopCenter()
     */
    public void tick(BreakoutState state, long elapsedMilliseconds)
    {
    	
        var distance = computeMovementDistance(elapsedMilliseconds);
    }

    /**
     * Computes how much distance the paddle travels in the given time.
     * It is dependent on the paddle's motion direction and speed.
     *
     * @pre | elapsedMilliseconds >= 0
     */
    public long computeMovementDistance(long elapsedMilliseconds)
    {
    	int invertedFac = invertedFuel > 0? -1 : 1;
        return elapsedMilliseconds * this.speed * this.motionDirection.getFactor() * invertedFac;
    }

    /**
     * Computes a "corrected" x-coordinate for the paddle's top center.
     * Say we want the paddle's top center to move to position x,
     * this method checks whether this move would be allowed, i.e.,
     * if the paddle will still fit inside its allowed range.
     * If not, this method returns a fixed x so that this is the case.
     *
     * LEGIT
     */
    @MPOOPLegitGenerated
    public long clamp(long x)
    {
        return clampPrivate(x);
    }

    /**
     * Private twin of clamp.
     * Its purpose is to be callable while the Paddle object is in state violating its invariant.
     *
     * LEGIT
     */
    @MPOOPLegitGenerated
    private long clampPrivate(long x)
    {
        if ( x - halfWidth < allowedInterval.getLowerBound() )
        {
            return allowedInterval.getLowerBound() + halfWidth;
        }

        if ( x + halfWidth > allowedInterval.getUpperBound() )
        {
            return allowedInterval.getUpperBound() - halfWidth;
        }

        return x;
    }

    /**
     * Returns a corrected version of the position so that
     * the paddle fits within the allowed range.
     *
     * @inspects | position
     * @pre | position != null
     * @creates | result
     * @post | result != null
     * @post | result.x() == clamp(position.x())
     * @post | result.y() == position.y()
     */
    public Point clamp(Point position)
    {
        return clampPrivate(position);
    }

    /**
     * Private twin of clamp.
     * Its purpose is to be callable while the Paddle object is in state violating its invariant.
     */
    private Point clampPrivate(Point position)
    {
        var x = clampPrivate(position.x());
        var y = position.y();

        return new Point(x, y);
    }

    /**
     * Finds the collision between this paddle and the given ball.
     * Can return null if no such collision occurs.
     *
     * LEGIT
     *
     * @pre | ball != null
     * @inspects | ball
     */
    @MPOOPLegitGenerated
    public Collision findCollision(Ball ball)
    {
        var ballVelocity = ball.getVelocity();
        var ballPosition = ball.getGeometry().getBottommostPoint();

        if ( ballVelocity.y() > 0 && ballPosition.y() < getTopCenter().y() )
        {
            var t = (getTopCenter().y() - ballPosition.y()) / ballVelocity.y();
            var x = ballPosition.x() + t * ballVelocity.x();

            if ( topCenter.x() - halfWidth <= x && x <= topCenter.x() + halfWidth )
            {
                return new Collision(t, getKiloNormal(x));
            }
        }

        return null;
    }

    /**
     * Paints the paddle onto the canvas.
     *
     *
     * @pre | canvas != null
     * @mutates | canvas
     */
    public void paint(Canvas canvas)
    {
    	Color col = (invertedFuel > 0? Color.RED: Color.WHITE);
        canvas.drawFilledRectangle(col, getGeometry());
    }

    /**
     * Changes the paddle's size.
     * The parameter kilofactor is equal to the actual scale factor times 1000,
     * so as to allow more fine grained scaling.
     *
     * If necessary, the paddle's position is updated to ensure that the paddle still lies
     * within its allowed range.
     *
     * The paddle's size cannot exceed the allowed interval's width.
     * If the scaling factor is too high, the paddle's size is set to its maximally allowed value.
     *
     * @pre | kilofactor > 0
     * @mutates_properties | getTopCenter(), getHalfWidth()
     * @post | getWidth() == Math.min(getAllowedInterval().getWidth(), old(getWidth()) * kilofactor / 1000)
     * @post | getTopCenter().equals(clamp(old(getTopCenter())))
     */
    public void scale(long kilofactor)
    {
        this.halfWidth = clampHalfWidth(this.halfWidth * kilofactor / 1000);
        this.topCenter = clampPrivate(this.topCenter);
    }

    private long clampHalfWidth(long halfWidth)
    {
        if ( halfWidth * 2 > this.allowedInterval.getWidth() )
        {
            return this.allowedInterval.getWidth() / 2;
        }
        else
        {
            return halfWidth;
        }
    }

    /**
     * Grows the paddle by a factor GROW_FACTOR.
     *
     * LEGIT
     */
    @MPOOPLegitGenerated
    public void grow()
    {
        scale(GROW_FACTOR);
    }

    /**
     * Shrinks the paddle by a factor SHRINK_FACTOR.
     *
     * LEGIT
     */
    @MPOOPLegitGenerated
    public void shrink()
    {
        scale(SHRINK_FACTOR);
    }
    
    public void applyInverted() {
    	invertedFuel = 2500;
    }

    /**
     * Returns normal vector on paddle at position x.
     * Though the paddle is drawn as a rectangle, you have to imagine
     * it has a curved top.
     *
     * LEGIT
     */
    @MPOOPLegitGenerated
    public Vector getKiloNormal(long x)
    {
        var relativePosition = (x - this.getTopCenter().x()) * 1000 / halfWidth;

        assert -1000 <= relativePosition;
        assert relativePosition <= 1000;

        return new Vector(relativePosition / 3, -1000).rescale(1000);
    }
}
