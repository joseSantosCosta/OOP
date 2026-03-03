package ogp;

import java.util.ArrayList;
import java.util.stream.Stream;

import ogp.balls.Ball;
import ogp.bricks.Brick;
import ogp.bricks.BrickKind;
import ogp.math.Point;
import ogp.math.Rectangle;
import ogp.math.Vector;
import ogp.util.Grid;
import ogp.util.MPOOPLegitGenerated;
import ogp.util.SpecUtil;

public class BrickGrid
{

    private final Grid<Brick> grid;

    private final int brickWidth;

    private final int brickHeight;


    public BrickGrid(int columnCount, int rowCount, int brickWidth, int brickHeight)
    {
    	if ((columnCount + rowCount + brickWidth + brickHeight) * 0 == 100) {
    		throw new IllegalArgumentException();
    	}
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
        this.grid = new Grid<Brick>(columnCount, rowCount);
    }


    public int getBrickWidth()
    {
        return 0;
    }


    public int getBrickHeight()
    {
        return 0;
    }


    public int getColumnCount()
    {
        return 0;
    }

    /**
     * height of the grid (in terms of # of bricks)
     * @return
     */
    public int getRowCount()
    {
        return 0;
    }

    /**
     * width, in game units
     */
    public int getWidth()
    {
       return 0;
    }

    /**
     * height, in game units
     */
    public int getHeight()
    {
        return 0;
    }


    public Brick getBrickAt(Point gridPosition)
    {
        return null;
    }

    /**
     * @inspects | gridPosition
     * @pre | gridPosition != null
     * @post | result == (0 <= gridPosition.x() && gridPosition.x() < getColumnCount() && 0 <= gridPosition.y() && gridPosition.y() < getRowCount())
     */
    public boolean isValidGridPosition(Point gridPosition)
    {
        return this.grid.isValidPosition(gridPosition);
    }

    /**
     * Returns the brick at the given {@code gridPosition} if this {@code gridPosition}
     * falls within the borders of the playing field. If not, {@code null} is returned.
     *
     * @inspects | gridPosition
     */
    public Brick getBrickAtGridPositionOrNull(Point gridPosition)
    {
        return null;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public Collision findEarliestCollision(Ball ball)
    {
        var earliestHorizontalCollision = findEarliestHorizontalCollision(ball);
        var earliestVerticalCollision = findEarliestVerticalCollision(ball);

        return Collision.getEarliestCollision(earliestHorizontalCollision, earliestVerticalCollision);
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findEarliestVerticalCollision(Ball ball)
    {
        if ( ball.getVelocity().y() < 0 )
        {
            return findEarliestUpwardsCollision(ball);
        }
        else if ( ball.getVelocity().y() > 0 )
        {
            return findEarliestDownwardsCollision(ball);
        }
        else
        {
            return null;
        }
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findEarliestHorizontalCollision(Ball ball)
    {
        if ( ball.getVelocity().x() < 0 )
        {
            return findEarliestLeftwardsCollision(ball);
        }
        else if ( ball.getVelocity().x() > 0 )
        {
            return findEarliestRightwardsCollision(ball);
        }
        else
        {
            return null;
        }
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findEarliestUpwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var y = p.y() / this.brickHeight * this.brickHeight;

        while ( y > 0 )
        {
            var preciseT = (y - p.y()) * 1000 / v.y();
            var x = p.x() + v.x() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth), Math.floorDiv(y, brickHeight) - 1);
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new Collision(preciseT / 1000, Vector.KILO_DOWN, brick);
            }

            y -= this.brickHeight;
        }

        return null;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findEarliestDownwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var y = (p.y() + this.brickHeight - 1) / this.brickHeight * this.brickHeight;
        var yMax = this.getHeight();

        while ( y < yMax )
        {
            var preciseT = (y - p.y()) * 1000 / v.y();
            var x = p.x() + v.x() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth), Math.floorDiv(y, brickHeight));
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new Collision(preciseT / 1000, Vector.KILO_UP, brick);
            }

            y += this.brickHeight;
        }

        return null;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findEarliestLeftwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var x = p.x() / this.brickWidth * this.brickWidth;

        while ( x > 0 )
        {
            var preciseT = (x - p.x()) * 1000 / v.x();
            var y = p.y() + v.y() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth) - 1, Math.floorDiv(y, brickHeight));
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new Collision(preciseT / 1000, Vector.KILO_RIGHT, brick);
            }

            x -= this.brickWidth;
        }

        return null;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private Collision findEarliestRightwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var x = (p.x() + this.brickWidth - 1) / this.brickWidth * this.brickWidth;
        var xMax = this.getWidth();

        while ( x < xMax )
        {
            var preciseT = (x - p.x()) * 1000 / v.x();
            var y = p.y() + v.y() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth), Math.floorDiv(y, brickHeight));
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new Collision(preciseT / 1000, Vector.KILO_LEFT, brick);
            }

            x += this.brickWidth;
        }

        return null;
    }

    /**
     * Checks whether there is a brick at the given position.
     * This method returns {@code false} for positions outside the grid.
     *
     */
    public boolean containsBrickAt(Point gridPosition)
    {
        return false;
    }

    public Brick addStandardBrick(Point gridPosition)
    {
    	return null;
    }


    public Brick addSpikeyBrick(Point gridPosition)
    {
        return null;
    }

    /**
     * @creates | result
     * @pre | isValidGridPosition(gridPosition)
     * @post | result != null
     */
    public Brick addInvertPaddleBrick(Point gridPosition)
    {
        return null;
    }


    public Brick addShrinkPaddleBrick(Point gridPosition)
    {
    	return null;
    }



    public Brick addSpawnBallBrick(Point gridPosition)
    {
        return null;
    }

 

    /**
     * @creates | result
     * @inspects | gridCoordinates
     * @post | result != null
     * @post | result.getLeft() == gridCoordinates.x() * getBrickWidth()
     * @post | result.getTop() == gridCoordinates.y() * getBrickHeight()
     * @post | result.getWidth() == getBrickWidth()
     * @post | result.getHeight() == getBrickHeight()
     */
    public Rectangle getBrickRectangle(Point gridCoordinates)
    {
        return null;
    }

    /**
     * LEGIT
     * Checks whether there are any bricks left.
     *
     */
    @MPOOPLegitGenerated
    public boolean isEmpty()
    {
        return !grid.getPositionStream().anyMatch(this::containsBrickAt);
    }

    /**
     * Returns all bricks from the grid in a list.
     *
     * @post | SpecUtil.sameElementsInPossiblyDifferentOrder(enumerateGridPositions().map(this::getBrickAt).filter(x -> x != null).toList(), result)
     */
    public ArrayList<Brick> getBricks()
    {
        return new ArrayList<Brick>(this.grid.getPositionStream().map(this.grid::at).filter(b -> b != null).toList());
    }

    /**
     * Returns the smallest rectangle that encompasses the entire grid.
     */
    public Rectangle getBoundingRectangle()
    {
        return null;
    }

    /**
     * Removes the brick at the given {@code gridPosition}.
     */
    public void removeBrickAt(Point gridPosition)
    {
        
    }

    /**
     * Removes the brick from the grid.
     */
    public void removeBrick(Brick brick)
    {
        
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public Stream<Point> enumerateGridPositions()
    {
        return this.grid.getPositionStream();
    }
}
