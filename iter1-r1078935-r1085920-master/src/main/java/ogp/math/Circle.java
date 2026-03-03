package ogp.math;

import java.util.Objects;

import ogp.util.MPOOPLegitGenerated;

/**
 * LEGIT
 * 
 * Represents a circle in a 2-dimensional integer coordinate system.
 *
 * @immutable
 */
@MPOOPLegitGenerated
public class Circle
{
    /**
     * @invar | center != null
     */
    private final Point center;

    /**
     * @invar | radius >= 0
     */
    private final long radius;

    /**
     * Construct a circle with a given center point and diameter.
     *
     * @post | getCenter().equals(center)
     * @post | getRadius() == radius
     * @throws IllegalArgumentException
     *   | center == null
     * @throws IllegalArgumentException
     *   | radius < 0
     */
    public Circle(Point center, long radius)
    {
        if ( center == null )
        {
            throw new IllegalArgumentException();
        }

        if ( radius < 0 )
        {
            throw new IllegalArgumentException();
        }

        this.center = center;
        this.radius = radius;
    }

    /**
     * Return the center point of this circle.
     *
     * @post | result != null
     */
    public Point getCenter()
    {
        return center;
    }

    /**
     * @post | result >= 0
     */
    public long getRadius()
    {
        return radius;
    }

    /**
     * @creates | result
     * @post | result != null
     * @post | result.equals(getCenter().add(Vector.RIGHT.multiply(getRadius())))
     */
    public Point getRightmostPoint()
    {
        var x = getRight();
        var y = center.y();

        return new Point(x, y);
    }

    /**
     * @creates | result
     * @post | result != null
     * @post | result.equals(getCenter().add(Vector.LEFT.multiply(getRadius())))
     */
    public Point getLeftmostPoint()
    {
        var x = getLeft();
        var y = center.y();

        return new Point(x, y);
    }

    /**
     * @creates | result
     * @post | result != null
     * @post | result.equals(getCenter().add(Vector.UP.multiply(getRadius())))
     */
    public Point getTopmostPoint()
    {
        var x = center.x();
        var y = getTop();

        return new Point(x, y);
    }

    /**
     * @creates | result
     * @post | result != null
     * @post | result.equals(getCenter().add(Vector.DOWN.multiply(getRadius())))
     */
    public Point getBottommostPoint()
    {
        var x = center.x();
        var y = getBottom();

        return new Point(x, y);
    }

    /**
     * Return the outermost point of this circle in the given direction `dir`.
     *
     * LEGIT
     *
     * @inspects | direction
     * @creates | result
     * @pre | direction != null
     * @post | result != null
     */
    @MPOOPLegitGenerated
    public Point getPointInDirection(Vector direction)
    {
        return getCenter().add(direction.multiply(getRadius()).divide(direction.getLength()));
    }

    /**
     * @post | result == this.getCenter().x() - getRadius()
     */
    public long getLeft()
    {
        return this.center.x() - radius;
    }

    /**
     * @post | result == this.getCenter().y() - getRadius()
     */
    public long getTop()
    {
        return this.center.y() - radius;
    }

    /**
     * @post | result == this.getCenter().x() + getRadius()
     */
    public long getRight()
    {
        return this.center.x() + radius;
    }

    /**
     * @post | result == this.getCenter().y() + getRadius()
     */
    public long getBottom()
    {
        return this.center.y() + radius;
    }

    /**
     * @creates | result
     * @post | result != null
     * @post | result.getLeft() == getLeft()
     * @post | result.getTop() == getTop()
     * @post | result.getWidth() == 2 * getRadius()
     * @post | result.getHeight() == 2 * getRadius()
     */
    public Rectangle getBoundingRectangle()
    {
        return new Rectangle(getLeft(), getTop(), 2 * getRadius(), 2 * getRadius());
    }

    /**
     * Moves the circle in the direction given by {@code vector}.
     *
     * @inspects vector
     * @creates | result
     * @pre | vector != null
     * @post | result != null
     * @post | result.getRadius() == getRadius()
     * @post | result.getCenter().equals(this.getCenter().add(vector))
     */
    public Circle move(Vector vector)
    {
        var newCenter = this.center.add(vector);

        return moveTo(newCenter);
    }

    /**
     * Sets the circle's center to {@code newCenter}.
     *
     * @inspects | newCenter
     * @creates | result
     * @pre | newCenter != null
     * @post | result != null
     * @post | result.getRadius() == getRadius()
     * @post | result.getCenter().equals(newCenter)
     */
    public Circle moveTo(Point newCenter)
    {
        return new Circle(newCenter, this.radius);
    }

    /**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public boolean equals(Object other)
    {
        if ( other instanceof Circle that )
        {
            return this.center.equals(that.center) && this.radius == that.radius;
        }
        else
        {
            return false;
        }
    }

    /**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public int hashCode()
    {
        return Objects.hash(this.center, this.radius);
    }
    
    public Circle scaleUp() {
    	return new Circle(center, 5*radius/4);
    }
    
    public Circle scaleDown() {
    	return new Circle(center, 4*radius/5);
    }
}
