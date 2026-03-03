package ogp.math;

import java.util.Objects;

import ogp.util.MPOOPLegitGenerated;

/**
 * This class represents a point on a 2-dimensional integer grid.
 *
 * @immutable
 */
public class Point
{
	
	public static Point O() {
		return new Point(0,0);
	}
	
    private final long x;
    private final long y;

    /**
     * Return a new Point with given x and y coordinates.
     *
     * @post | x() == x
     * @post | y() == y
     */
    public Point(long x, long y)
    {
        this.x = x;
        this.y = y;
    }

    /** Return this point's x coordinate. */
    public long x()
    {
        return x;
    }

    /** Return this point's y coordinate. */
    public long y()
    {
        return y;
    }

    /**
     * Return the point obtained by adding vector `v` to this point.
     *
     */
    public Point add(Vector v)
    {
        return null;
    }

    /**
     * Return the point obtained by adding vector `- v` to this point.
     *
     */
    public Point subtract(Vector v)
    {
        return null;
    }

    /**
     * @creates | result
     * @post | result != null
     * @post | result.x() == x()
     * @post | result.y() == y() + dy
     */
    public Point moveDown(int dy)
    {
        return null;
    }


    public Point moveUp(int dy)
    {
        return new Point(x, y - dy);
    }


    public Point moveLeft(int dx)
    {
        return null;
    }


    public Point moveRight(int dx)
    {
        return null;
    }

    /**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    /**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public boolean equals(Object obj)
    {
        if ( obj instanceof Point that )
        {
            return this.x == that.x && this.y == that.y;
        }
        else
        {
            return false;
        }
    }

    /**
     * Return a string representation of this point.
     *
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
    
    
}
