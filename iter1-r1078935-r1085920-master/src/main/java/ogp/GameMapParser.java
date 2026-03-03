package ogp;


import java.util.Arrays;
import java.util.stream.IntStream;

import ogp.balls.BallBehavior;
import ogp.math.Circle;
import ogp.math.Point;
import ogp.math.Vector;
import ogp.util.MPOOPLegitGenerated;


/**
 * LEGIT
 */
@MPOOPLegitGenerated
public class GameMapParser
{
    private GameMapParser()
    {
        // NOP
    }

    /**
     * Create a fresh BreakoutState using a game field described by strings. 
     *
     */
    public static BreakoutState parse(String[] lines, int brickWidth, int brickHeight)
    {
        var brickGrid = parseBrickGrid(lines, brickWidth, brickHeight);
        var paddleHalfWidth = brickWidth;
        var paddleSpeed = brickWidth / 100;
        var state = new BreakoutState(brickGrid, paddleHalfWidth, paddleSpeed, 70);
        addBalls(state);

        return state;
    }
    
    /**
     * post: no balls in result
     * post: 70 hps
     */
    public static BreakoutState parseNoBalls(String[] lines, int brickWidth, int brickHeight) {
        var brickGrid = parseBrickGrid(lines, brickWidth, brickHeight);
        var paddleHalfWidth = brickWidth;
        var paddleSpeed = brickWidth / 100;
        var state = new BreakoutState(brickGrid, paddleHalfWidth, paddleSpeed, 70);
        return state;
    }

    /*
     * LEGIT
     * 
     * #:standard
	 * S:spikey
	 * I: invert paddle
	 * -: shrink paddle
	 * o: spawn-ball brick
     */
    @MPOOPLegitGenerated
    private static BrickGrid parseBrickGrid(String[] lines, int brickWidth, int brickHeight)
    {
        var width = lines[0].length();
        var height = lines.length;
        var brickGrid = new BrickGrid(width, height, brickWidth, brickHeight);

        for ( int y = 0; y != height; ++y )
        {
            for ( int x = 0; x != width; ++x )
            {
                var gridPosition = new Point(x, y);

                switch ( lines[y].charAt(x) )
                {
                case ' ':
                    // NOP
                    break;

                case '#':
                    brickGrid.addStandardBrick(gridPosition);
                    break;

                case 'S':
                    brickGrid.addSpikeyBrick(gridPosition);
                    break;

                case 'I':
                    brickGrid.addInvertPaddleBrick(gridPosition);
                    break;

                case '-':
                    brickGrid.addShrinkPaddleBrick(gridPosition);
                    break;


                case 'o':
                    brickGrid.addSpawnBallBrick(gridPosition);
                    break;

                default:
                    throw new IllegalArgumentException();
                }
            }
        }


        return brickGrid;
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    private static void addBalls(BreakoutState state)
    {
        var brickGrid = state.getBrickGrid();
        var ballRadius = 500;
        var ballPosition = brickGrid.getBoundingRectangle().getBottomCenter().add(new Vector(0, -2 * ballRadius));
        var geometry = new Circle(ballPosition, ballRadius);
        var velocity = new Vector(25, -25);
        var behavior = new BallBehavior();
        
        state.addBall(geometry, velocity, behavior);
    }
    

    public final static String[] descr1 = new String[] {
		"SSSSSSSSS",
		"         ",
		"         ",
		"#       #",
		"         ",
		"         ",
		"         ",
		"         ",    		
    };
    public final static String[] descr2 = new String[] {
		"#I##I##",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
    };



    public final static String[] descr6 = new String[] {
		"#######",
		"#######",
		"#######",
		"#######",
		"ooooooo",
		"#######",
		"#######",
		"ooooooo",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
		"       ",
    };


    public final static String[] small = new String[] {
    		"    #  ",
    		"       ",
    		"       ",
    };

    public final static String[] crazy2 = new String[] {
    		"ooooooo",
    		"ooooooo",
    		"ooooooo",
    		"ooooooo",
    		"ooooooo",
    		"ooooooo",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    };

//   * #:standard
//	 * S:spikey
//	 * I: invert paddle
//	 * -: shrink paddle
//	 * o: spawn-ball brick
    

    public final static String[] SOME_MAP = new String[] {
    		"o#S##S#",
    		"###-#I#",
    		"#######",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
    		"       ",
        };

    

   public final static String[] OOP_MAP = SOME_MAP;
    

    public final static String[] OGP_MAP = SOME_MAP;


}
