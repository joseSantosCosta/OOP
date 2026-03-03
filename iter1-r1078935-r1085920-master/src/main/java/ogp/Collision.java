package ogp;

import static ogp.util.SpecUtil.*;

import ogp.bricks.Brick;
import ogp.math.Vector;
import ogp.util.MPOOPLegitGenerated;

/**
 * Objects of this class contain collision-related information.
 * Collisions can happen between balls and walls & paddles & bricks.
 *
 */
public class Collision
{
	/**
	 * not null iff brick collision
	 */
	private final Brick brick;
	
    /**
     * Time until collision.
     *
     * @invar | millisecondsUntilCollision >= 0
     */
    private final long millisecondsUntilCollision;

    /**
     * Normal vector on the surface that was hit.
     * The vector must have size approximately 1000, see {@link Vector#isKiloUnitVector()}.
     *
     * @invar | kiloNormal != null
     * @invar | kiloNormal.isKiloUnitVector()
     */
    private final Vector kiloNormal;
    
    
    
    /**
     * Constructor for a non-brick collision object 
     * 

     */
    public Collision(long millisecondsUntilCollision, Vector kiloNormal)
    {
        if ( millisecondsUntilCollision < 0 )
        {
            throw new IllegalArgumentException();
        }

        if ( kiloNormal == null || !kiloNormal.isKiloUnitVector() )
        {
            throw new IllegalArgumentException();
        }

        this.millisecondsUntilCollision = 0;
        this.kiloNormal = null;
        this.brick = null;
    }

    /**
     * Constructor for any collision.
     * 
     * @param | brick may be null.

     */
    public Collision(long millisecondsUntilCollision, Vector kiloNormal, Brick brick)
    {

        this.millisecondsUntilCollision = 0;
        this.kiloNormal = null;
        this.brick = null; //can be null if current collision is not with a brick
    }


    public long getMillisecondsUntilCollision()
    {
        return 0;
    }


    public Vector getKiloNormal()
    {
        return null;
    }
    
    public Brick getBrick()
    {
        return null;
    }

    /**
     * LEGIT
     * 
     * Returns the "earliest" collision, i.e., the one with the lowest milliseconds until collision.
     * Note that the parameters can be null.
     *
     * @post | implies(c1 == null, result == c2)
     * @post | implies(c2 == null, result == c1)
     * @post | implies(c1 != null && c2 != null && c1.getMillisecondsUntilCollision() <= c2.getMillisecondsUntilCollision(), result == c1)
     * @post | implies(c1 != null && c2 != null && c1.getMillisecondsUntilCollision() > c2.getMillisecondsUntilCollision(), result == c2)
     */
    @MPOOPLegitGenerated
    public static <T extends Collision> T getEarliestCollision(T c1, T c2)
    {
        if ( c1 == null )
        {
            return c2;
        }
        else if ( c2 == null )
        {
            return c1;
        }
        else if ( c1.getMillisecondsUntilCollision() <= c2.getMillisecondsUntilCollision() )
        {
            return c1;
        }
        else
        {
            return c2;
        }
    }

    /**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public String toString()
    {
    	if (this.brick == null) {
    		return String.format("Collision(t=%d, n=%s)", millisecondsUntilCollision, kiloNormal);    		
    	}
    	else {
    		return String.format("Collision(t=%d, n=%s, p=%s)", getMillisecondsUntilCollision(), getKiloNormal(), getBrick().getGridPosition());    		
    	}
        
    }
}
