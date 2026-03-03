package ogp;

import java.util.ArrayList;
import java.util.Arrays;

import ogp.balls.Ball;
import ogp.balls.BallBehavior;
import ogp.bricks.Brick;
import ogp.math.Circle;
import ogp.math.Interval;
import ogp.math.Rectangle;
import ogp.math.Vector;
import ogp.paddles.Paddle;
import ogp.util.MPOOPLegitGenerated;
import ogp.util.SpecUtil;
import ogp.walls.Wall;
import ogp.walls.WallKind;

/**
 * Represents the current state of a breakout game.
 */
public class BreakoutState
{
    public static int MAXIMUM_TIME_DELTA = 20;

    /**
     * List of all balls.
     * 
     * @invar | balls.stream().allMatch(b -> b != null && getBoundingRectanglePrivate().thickening().contains(b.getGeometry().getCenter()))
     * @ invar | no duplicates
     * @representationObject
     */
    private ArrayList<Ball> balls;

    /**
     * @invar | bricks != null
     */
    private final BrickGrid bricks;

    /**
     * @invar | paddle != null
     * @ invar | inside getBoundingRectanglePrivate
     */
    private Paddle paddle;

    /**
     * @representationObject
     */
    private final ArrayList<Wall> walls;
    
    /**
     * Health points.
     * @invar | hps >= 0
     */
    private int hps;
    
    
    public int getHps() {
    	return 0;
    }

    /**
	 * Returns the list of balls.
	 *
	 */
	public ArrayList<Ball> getBalls()
	{
	    return balls;
	}

	/**
	 * Return the paddle of this BreakoutState.
	 */
	public Paddle getPaddle()
	{
	    return null;
	}

	/**
	 * Returns a list of bricks.
	 * 
	 * LEGIT
	 * 
	 * @post | result != null
	 * @post | result.stream().allMatch(brick -> brick != null)
	 * @post | !SpecUtil.containsDuplicateObjects(result)
	 */
    @MPOOPLegitGenerated
	public ArrayList<Brick> getBricks()
	{
	    return this.bricks.getBricks();
	}


	public BrickGrid getBrickGrid()
	{
	    return null;
	}

	/**
	 * Return a rectangle representing the game field.
	 *
	 * LEGIT
	 *
	 * @post | result != null
	 */
    @MPOOPLegitGenerated
	public Rectangle getBoundingRectangle()
	{
	    return getBoundingRectanglePrivate();
	}

	/**
	 * Returns a list of walls.
	 * 
	 */
	public ArrayList<Wall> getWalls()
	{
	    return walls;
	}

	/**
     * Construct a new BreakoutState.
     */
    public BreakoutState(BrickGrid brickGrid, long initialPaddleHalfWidth, long paddleSpeed, int initHP)
    {
        this.hps = 0;
        this.balls = null;
        this.bricks = null;
        this.paddle = null;
        this.walls = null;
    }

    /**
     * Move all moving objects one step forward.
     * Cuts large elapsedMilliseconds in multiple smaller values.
     * 
     * LEGIT
     *
     * @pre | elapsedMilliseconds >= 0
     *
     * @mutates | this
     * @mutates | ...getBalls()
     */
    @MPOOPLegitGenerated
    public void tick(long elapsedMilliseconds)
    {
        while ( elapsedMilliseconds > 0 )
        {
            var dt = Math.min(MAXIMUM_TIME_DELTA, elapsedMilliseconds);
            atomicTick(dt);
            elapsedMilliseconds -= dt;
        }
    }

    /**
     * Checks if the game has ended.
     *  
     */
    public boolean isGameEnded()
    {
        return isGameWon() || isGameLost();
    }

    /**
     * Checks whether the game has been won.
     * The game is won when the bricks that remain are indestructible.
     *
     */
    public boolean isGameWon()
    {
    	return bricks.getBricks().isEmpty();
    }

    /**
     * Checks whether the game has been lost.
     * The game is lost when there are no more balls left or we have <= 0 hps
     *
     */
    public boolean isGameLost()
    {
        return false;
    }

    /**
     * Removes the given ball from the game.
     */
    public void removeBall(Ball ball)
    {
        
    }

    /**
     * Checks if the ball is lost.
     * 
     * LEGIT
     * 
     * @pre | ball != null
     */
    @MPOOPLegitGenerated
    public boolean isBallLost(Ball ball)
    {
        return !getBoundingRectangle().contains(ball.getCenter());
    }

    /**
     * Adds new ball to the game and returns the reference.
     * 
     */
    public Ball addBall(Circle geometry, Vector velocity, BallBehavior behavior)
    {
    	return null;
    }
    
    public void lose1Life() {

    }
    
    

	/**
	 * LEGIT
	 */
    @MPOOPLegitGenerated
	private void atomicTick(long elapsedTime)
	{
	    paddle.tick(this, elapsedTime);
	
	    for ( var ball : new ArrayList<>(this.balls) )
	    {
	        ball.tick(this, elapsedTime);
	    }
	}
	


	/**
	 * Private twin of getBoundingRectangle() so as to be usable internally.
	 * 
	 * LEGIT
	 */
    @MPOOPLegitGenerated
	private Rectangle getBoundingRectanglePrivate()
	{
	    var left = 0;
	    var top = 0;
	    var width = this.bricks.getWidth();
	    var height = this.bricks.getHeight() + this.paddle.getHeight();
	
	    return new Rectangle(left, top, width, height);
	}


	/**
	 * Returns a list containing the top right and left walls.
	 */
	private static ArrayList<Wall> createWalls(BrickGrid brickGrid)
	{
	    return null;
	}

	/**
	 * LEGIT
	 */
    @MPOOPLegitGenerated
	private static Paddle createPaddle(BrickGrid brickGrid, long initialPaddleHalfWidth, long speed)
	{
	    var allowedInterval = new Interval(0, brickGrid.getWidth());
	    var topCenter = brickGrid.getBoundingRectangle().getBottomCenter();
	
	    return new Paddle(allowedInterval, topCenter, initialPaddleHalfWidth, speed);
	}
}
