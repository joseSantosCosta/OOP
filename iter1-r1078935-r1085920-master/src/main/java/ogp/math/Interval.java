package ogp.math;

import ogp.util.MPOOPLegitGenerated;

/**
 * LEGIT
 *
 * @invar | getLowerBound() <= getUpperBound()
 */
@MPOOPLegitGenerated
public class Interval
{
    public static final long PRECISION_FACTOR = 1000;

    /**
     * @invar | lowerBound <= upperBound
     */
    private final long lowerBound;
    private final long upperBound;

    /**
     * @creates | result
     * @post | result != null
     * @post | result.getLowerBound() == Long.MIN_VALUE
     * @post | result.getUpperBound() == Long.MAX_VALUE
     */
    public static Interval createMaximalInterval()
    {
        var lowerBound = Long.MIN_VALUE;
        var upperBound = Long.MAX_VALUE;

        return new Interval(lowerBound, upperBound);
    }

    /**
     * @post | getLowerBound() == lowerBound
     * @post | getUpperBound() == upperBound
     * @throws IllegalArgumentException
     *   | lowerBound > upperBound
     */
    public Interval(long lowerBound, long upperBound)
    {
        if ( lowerBound > upperBound )
        {
            throw new IllegalArgumentException();
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public long getLowerBound()
    {
        return this.lowerBound;
    }

    public long getUpperBound()
    {
        return this.upperBound;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public long toRelative(long x)
    {
        return (x - this.lowerBound) * PRECISION_FACTOR / getWidth();
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public long fromRelative(long x)
    {
        return getWidth() * x / PRECISION_FACTOR + lowerBound;
    }

    /**
     * @post | result == getUpperBound() - getLowerBound()
     */
    public long getWidth()
    {
        return upperBound - lowerBound;
    }

    /**
     * @post | result == (getLowerBound() <= x && x <= getUpperBound())
     */
    public boolean isInside(long x)
    {
        return lowerBound <= x && x <= upperBound;
    }
}
