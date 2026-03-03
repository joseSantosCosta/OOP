package ogp.balls;

import java.awt.Color;

import ogp.BreakoutState;
import ogp.Collision;
import ogp.ui.Canvas;
import ogp.util.MPOOPLegitGenerated;


public class BallBehavior
{
	
    public final static Color COLOR = Color.WHITE;
    
	
    /**
     * NOSPEC
     * 
     * This method computes what happens to the ball in the next elapsedMilliseconds.
     *
     * @pre | state != null
     * @pre | ball != null
     * @pre | state.getBalls().contains(ball)
     * @pre | elapsedMilliseconds >= 0
     * @mutates | this
     * @mutates | state
     * @mutates | ball
     */
    public void update(BreakoutState state, Ball ball, long elapsedMilliseconds)
    {


        while ( elapsedMilliseconds > 0 )
        {
            var closestWallCollision = findClosestWallCollision(state, ball);
            var paddleCollision = findPaddleCollision(state, ball);
            var closestBrickCollision = findClosestBrickCollision(state, ball);

            if ( isClosestCollision(closestWallCollision, paddleCollision, closestBrickCollision) && closestWallCollision.getMillisecondsUntilCollision() <= elapsedMilliseconds )
            {

            }
            else if ( isClosestCollision(paddleCollision, closestWallCollision, closestBrickCollision) && paddleCollision.getMillisecondsUntilCollision() <= elapsedMilliseconds )
            {

            }
            else if ( isClosestCollision(closestBrickCollision, closestWallCollision, paddleCollision) && closestBrickCollision.getMillisecondsUntilCollision() <= elapsedMilliseconds )
            {

            }
            else
            {

            }
        }

        if ( state.isBallLost(ball) )
        {
            ballLost(state, ball);
        }
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private boolean isClosestCollision(Collision c1, Collision c2, Collision c3)
    {
        var t1 = c1 == null ? Long.MAX_VALUE : c1.getMillisecondsUntilCollision();
        var t2 = c2 == null ? Long.MAX_VALUE : c2.getMillisecondsUntilCollision();
        var t3 = c3 == null ? Long.MAX_VALUE : c3.getMillisecondsUntilCollision();

        return t1 < t2 && t1 < t3;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findClosestWallCollision(BreakoutState state, Ball ball)
    {
        Collision closestCollision = null;

        for ( var wall : state.getWalls() )
        {
            var collision = wall.findCollision(ball);
            closestCollision = Collision.getEarliestCollision(closestCollision, collision);
        }

        return closestCollision;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findPaddleCollision(BreakoutState state, Ball ball)
    {
        var paddle = state.getPaddle();

        return paddle.findCollision(ball);
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findClosestBrickCollision(BreakoutState state, Ball ball)
    {
        var brickGrid = state.getBrickGrid();

        return brickGrid.findEarliestCollision(ball);
    }

    /**
     *
     * This implementation does the following:
     * - It moves the ball to the point of impact.
     * - If reflects the velocity to make the ball bounce off the wall.
     */
    @MPOOPLegitGenerated
    public void bounceOffWall(BreakoutState state, Ball ball, Collision collision)
    {
        // This implementation is LEGIT
        ball.move(collision.getMillisecondsUntilCollision());
        var newVelocity = ball.getVelocity().kiloBounce(collision.getKiloNormal());
        ball.setVelocity(newVelocity);
    }

    @MPOOPLegitGenerated
    public void bounceOffPaddle(BreakoutState state, Ball ball, Collision collision)
    {
        // This implementation is LEGIT
        ball.move(collision.getMillisecondsUntilCollision());
        var newVelocity = ball.getVelocity().kiloBounce(collision.getKiloNormal());
        ball.setVelocity(newVelocity);
    }

    /**
     *
     * This implementation does the following:
     * - It moves the ball to the point of impact.
     * - It tells the brick it has been hit.
     * - It reflects the velocity so as to make the ball bounce off the brick.
     */
    @MPOOPLegitGenerated
    public void bounceOffBrick(BreakoutState state, Ball ball, Collision collision)
    {
        // This implementation is LEGIT
        ball.move(collision.getMillisecondsUntilCollision());
        collision.getBrick().hit(state, ball);
        var newVelocity = ball.getVelocity().kiloBounce(collision.getKiloNormal());
        ball.setVelocity(newVelocity);
    }

    /**
     * Called when the ball is lost, i.e., ventures outside the playing field.
     *
     * This implementation removes the ball from the game state.
     *
     */
    @MPOOPLegitGenerated
    public void ballLost(BreakoutState state, Ball ball)
    {
        // This implementation is LEGIT
        state.removeBall(ball);
    }

    /**
     * Paints the given ball on the given canvas.
     *
     */
    public void paint(Canvas canvas, Ball ball)
    {
        
    }

    /**
     *
     * @post | result != null
     */
    public Color getColor()
    {
        return COLOR;
    }
}
