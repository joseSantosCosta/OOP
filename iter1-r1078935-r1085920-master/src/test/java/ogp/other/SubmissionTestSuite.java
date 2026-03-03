package ogp.other;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import ogp.BreakoutState;
import ogp.BrickGrid;
import ogp.Collision;
import ogp.balls.Ball;
import ogp.balls.BallBehavior;
import ogp.bricks.Brick;
import ogp.math.Circle;
import ogp.math.CoordinateMapper;
import ogp.math.Interval;
import ogp.math.IntervalMapper;
import ogp.math.Point;
import ogp.math.Rectangle;
import ogp.math.Vector;
import ogp.paddles.Paddle;
import ogp.paddles.PaddleMotionDirection;
import ogp.ui.Canvas;
import ogp.walls.Wall;

/**
 * Submission tests check that all expected methods exist
 * and conform to a certain signature.
 */
@Tag("submission")
@Timeout(5)
public class SubmissionTestSuite
{


    private static void assertHasConstructor(Class<?> c, Class<?>... parameterTypes)
    {
        try
        {
            c.getConstructor(parameterTypes);
        } catch (NoSuchMethodException e)
        {
            fail(String.format("Class %s should have constructor with correct parameter types", c.getName()));
        }
    }

    private static void assertHasMethod(Class<?> c, Class<?> returnType, String methodName, Class<?>... parameterTypes)
    {
        try
        {
            var method = c.getMethod(methodName, parameterTypes);

            assertSame(returnType, method.getReturnType());
        } catch (NoSuchMethodException e)
        {
            fail(String.format("Class %s should have method %s with the correct parameter types", c.getName(), methodName));
        }
    }

    /**
     * Passes if methods in i exist in c as well (says nothing about the other direction).
     */
    private static void assertClassFitsInInterface(Class<?> c, Class<?> i)
    {
        for ( var method : i.getMethods() )
        {
            if ( method.getName().startsWith("$constructor") )
            {
                assertHasConstructor(c, method.getParameterTypes());
            }
            else
            {
                assertHasMethod(c, method.getReturnType(), method.getName(), method.getParameterTypes());
            }
        }
        
    }



    @Nested
    class PaddleTests
    {
        @Test
        void constant()
        {
            assertEquals(1000, Paddle.HEIGHT);
            assertEquals(1100, Paddle.GROW_FACTOR);
            assertEquals(900, Paddle.SHRINK_FACTOR);
        }

        @Test
        void constructor()
        {
            var allowedInterval = Interval.createMaximalInterval();
            var topCenter = new Point(0, 0);
            var halfWidth = 100;
            var speed = 100;

            new Paddle(allowedInterval, topCenter, halfWidth, speed);
        }

        interface PaddleTarget
        {
            PaddleMotionDirection getMotionDirection();

            void setMotionDirection(PaddleMotionDirection direction);

            Point getTopCenter();

            Interval getAllowedInterval();

            long getHalfWidth();

            long getWidth();

            long getHeight();

            long getSpeed();

            Rectangle getGeometry();

            void setTopCenterX(long x);

            void move(long distance);

            void tick(BreakoutState state, long elapsedMilliseconds);

            long computeMovementDistance(long elapsedMilliseconds);

            long clamp(long x);

            Point clamp(Point position);

            Collision findCollision(Ball ball);

            void paint(Canvas canvas);

            void scale(long kilofactor);

            void grow();

            void shrink();

            Vector getKiloNormal(long x);
            
            public void applyInverted();

        }

        @Test
        void members()
        {
            assertClassFitsInInterface(Paddle.class, PaddleTarget.class);
        }
    }

    @Nested
    class PaddleMotionDirectionTests
    {
        @Test
        void constants()
        {
            assertNotSame(PaddleMotionDirection.LEFT, PaddleMotionDirection.RIGHT);
            assertNotSame(PaddleMotionDirection.LEFT, PaddleMotionDirection.STATIONARY);
            assertNotSame(PaddleMotionDirection.STATIONARY, PaddleMotionDirection.RIGHT);
        }

        interface PaddleMotionDirectionTarget
        {
            int getFactor();
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(PaddleMotionDirection.class, PaddleMotionDirectionTarget.class);
        }
    }

    @Nested
    class CircleTests
    {
        private interface CircleTarget
        {
            void $constructor(Point center, long radius);

            Point getCenter();

            long getRadius();

            Point getRightmostPoint();

            Point getLeftmostPoint();

            Point getTopmostPoint();

            Point getBottommostPoint();

            Point getPointInDirection(Vector direction);

            long getLeft();

            long getTop();

            long getRight();

            long getBottom();

            Rectangle getBoundingRectangle();

            Circle move(Vector vector);

            Circle moveTo(Point newCenter);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(Circle.class, CircleTarget.class);
        }
    }

    @Nested
    class CoordinateMapperTests
    {
        private interface CoordinateMapperTarget
        {
            void $constructor(IntervalMapper xCoordinateMapper, IntervalMapper yCoordinateMapper);

            Point map(Point p);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(CoordinateMapper.class, CoordinateMapperTarget.class);
        }
    }

    @Nested
    class IntervalTests
    {
        private interface IntervalTarget
        {
            void $constructor(long lowerBound, long upperBound);

            long getLowerBound();

            long getUpperBound();

            long toRelative(long x);

            long fromRelative(long x);

            long getWidth();

            boolean isInside(long x);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(Interval.class, IntervalTarget.class);
        }

        @Test
        void constants()
        {
            assertEquals(1000, Interval.PRECISION_FACTOR);
        }

        @Test
        void createMaximalInterval()
        {
            Interval i = Interval.createMaximalInterval();
            assertNotNull(i);
        }
    }

    @Nested
    class IntervalMapperTests
    {
        private interface IntervalMapperTarget
        {
            void $constructor(Interval sourceInterval, Interval targetInterval);

            Interval getSourceInterval();

            Interval getTargetInterval();

            long map(long x);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(IntervalMapper.class, IntervalMapperTarget.class);
        }
    }

    @Nested
    class PointTests
    {
        private interface PointTarget
        {
            void $constructor(long x, long y);
            long x();
            long y();
            Point add(Vector v);
            Point subtract(Vector v);
            Point moveDown(int dy);
            Point moveUp(int dy);
            Point moveLeft(int dx);
            Point moveRight(int dx);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(Point.class, PointTarget.class);
        }
    }

    @Nested
    class RectangleTests
    {
        private interface RectangleTarget
        {
            void $constructor1(Point topLeft, Point bottomRight);

            void $constructor2(long left, long top, long width, long height);

            long getLeft();

            long getTop();

            long getWidth();

            long getHeight();

            long getRight();

            long getBottom();

            void setLeft(long left);

            void setTop(long top);

            void setWidth(long width);

            void setHeight(long height);

            Point getTopLeft();

            Point getBottomRight();

            boolean contains(Point point);

            boolean contains(Circle circle);

            boolean contains(Rectangle other);

            Point getBottomCenter();

            Point getCenter();

            Rectangle copy();

            Rectangle growHeight(long extraHeight);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(Rectangle.class, RectangleTarget.class);
        }
    }

    @Nested
    class VectorTests
    {
        @Test
        void constants()
        {
            assertEquals(new Vector(0, 1), Vector.DOWN);
            assertEquals(new Vector(0, -1), Vector.UP);
            assertEquals(new Vector(1, 0), Vector.RIGHT);
            assertEquals(new Vector(-1, 0), Vector.LEFT);
            assertEquals(new Vector(0, 1000), Vector.KILO_DOWN);
            assertEquals(new Vector(0, -1000), Vector.KILO_UP);
            assertEquals(new Vector(1000, 0), Vector.KILO_RIGHT);
            assertEquals(new Vector(-1000, 0), Vector.KILO_LEFT);
            assertEquals(new Vector(-707, -707), Vector.KILO_UP_LEFT);
            assertEquals(new Vector(707, -707), Vector.KILO_UP_RIGHT);
            assertEquals(new Vector(-707, 707), Vector.KILO_DOWN_LEFT);
            assertEquals(new Vector(707, 707), Vector.KILO_DOWN_RIGHT);
        }
        
        private interface VectorTarget
        {
            void $constructor(long x, long y);
            long x();
            long y();
            Vector multiply(long factor);
            Vector add(Vector other);
            Vector subtract(Vector other);
            Vector divide(long d);
            long dotProduct(Vector v);
            long getSquaredLength();
            long getLength();
            Vector rescale(long newLength);
            Vector kiloBounce(Vector kiloNormal);
            boolean isUnitVector();
            boolean isKiloUnitVector();
        }
        
        @Test
        void members()
        {
            assertClassFitsInInterface(Vector.class, VectorTarget.class);
        }
    }
    
    @Nested
    class BallTests
    {

        
        private interface BallTarget
        {
            void $constructor(Rectangle allowedArea, Circle geometry, Vector velocity, BallBehavior behavior);
            Circle getGeometry();
            Vector getVelocity();
            Rectangle getAllowedArea();
            BallBehavior getBehavior();
            Color getColor();
            Point getCenter();
            void tick(BreakoutState state, long elapsedMilliseconds);
            void move(long elapsedMilliseconds);
            Circle computeDestination(long elapsedMilliseconds);
            void setGeometry(Circle geometry);
            void setVelocity(Vector velocity);
            void paint(Canvas canvas);
            void setBehavior(BallBehavior behavior);
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(Ball.class, BallTarget.class);
        }
    }
    
    @Nested
    class BallBehaviorTests
    {

        
        private interface BallBehaviorTarget
        {
            void update(BreakoutState state, Ball ball, long elapsedMilliseconds);
            void bounceOffWall(BreakoutState state, Ball ball, Collision collision);
            void bounceOffPaddle(BreakoutState state, Ball ball, Collision collision);
            void bounceOffBrick(BreakoutState state, Ball ball, Collision collision);
            void ballLost(BreakoutState state, Ball ball);
            void paint(Canvas canvas, Ball ball);
            Color getColor();
        }
        
        @Test
        void members()
        {
            assertClassFitsInInterface(BallBehavior.class, BallBehaviorTarget.class);
        }
        

        
    
    @Nested
    class BrickGridTests
    {
        private interface BrickGridTarget
        {
            void $constructor(int columnCount, int rowCount, int brickWidth, int brickHeight);
            int getBrickWidth();
            int getBrickHeight();
            int getColumnCount();
            int getRowCount();
            int getWidth();
            int getHeight();
            Brick getBrickAt(Point gridPosition);
            boolean isValidGridPosition(Point gridPosition);
            Brick getBrickAtGridPositionOrNull(Point gridPosition);
            Collision findEarliestCollision(Ball ball);
            boolean containsBrickAt(Point gridPosition);
            Brick addStandardBrick(Point gridPosition);
            Brick addSpikeyBrick(Point gridPosition);
            Brick addInvertPaddleBrick(Point gridPosition);
            Brick addShrinkPaddleBrick(Point gridPosition);
            Brick addSpawnBallBrick(Point gridPosition);
            Rectangle getBrickRectangle(Point gridCoordinates);
            boolean isEmpty();
            ArrayList<Brick> getBricks();
            Rectangle getBoundingRectangle();
            void removeBrickAt(Point gridPosition);
            void removeBrick(Brick brick);
            Stream<Point> enumerateGridPositions();
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(BrickGrid.class, BrickGridTarget.class);
        }
    }
    
    @Nested
    class CollisionTests
    {
        private interface CollisionTarget
        {
            void $constructor(long millisecondsUntilCollision, Vector kiloNormal);
            long getMillisecondsUntilCollision();
            Vector getKiloNormal();
        }
        
        @Test
        void members()
        {
            assertClassFitsInInterface(Collision.class, CollisionTarget.class);
        }
        
        @Test
        void earliestCollision()
        {
            assertNull(Collision.getEarliestCollision(null, null));
        }
        
        @Nested
        class BrickCollisionTests
        {
            private interface BrickCollisionTarget
            {
                void $constructor(long millisecondsUntilCollision, Vector kiloNormal, Brick brick);
                long getMillisecondsUntilCollision();
                Vector getKiloNormal();
                Brick getBrick();
            }
            
            @Test
            void members()
            {
                assertClassFitsInInterface(Collision.class, BrickCollisionTarget.class);
            }
        }
        
    }
    

    
    @Nested
    class BreakoutStateTests
    {
        private interface BreakoutStateTarget
        {
            void $constructor(BrickGrid brickGrid, long initialPaddleHalfWidth, long paddleSpeed, int hps);
            ArrayList<Ball> getBalls();
            Paddle getPaddle();
            Rectangle getBoundingRectangle();
            void tick(long elapsedMilliseconds);
            boolean isGameEnded();
            boolean isGameWon();
            boolean isGameLost();
            ArrayList<Wall> getWalls();
            ArrayList<Brick> getBricks();
            void removeBall(Ball ball);
            boolean isBallLost(Ball ball);
            Ball addBall(Circle geometry, Vector velocity, BallBehavior behavior);
            BrickGrid getBrickGrid();
            int getHps();
        }

        @Test
        void members()
        {
            assertClassFitsInInterface(BreakoutState.class, BreakoutStateTarget.class);
        }
    }
    
    @Nested
    class SelectionOfCorrectionTests
    {
        @Test
        void movingBall()
        {
            var brickGrid = new BrickGrid(10, 10, 100, 20);
            var paddleHalfWidth = 50;
            var paddleSpeed = 10;
            var state = new BreakoutState(brickGrid, paddleHalfWidth, paddleSpeed, 5);
            
            var ballPosition = new Point(50, 25);
            var ballGeometry = new Circle(ballPosition, 5);
            var ballVelocity = new Vector(0, -5);
            var ballBehavior = new BallBehavior();
            var ball = state.addBall(ballGeometry, ballVelocity, ballBehavior);
            
            state.tick(1);
            
            assertEquals(new Point(50, 20), ball.getCenter());
        }
        
        @Test
        void movingPaddleLeft()
        {
            var brickGrid = new BrickGrid(10, 10, 100, 20);
            var paddleHalfWidth = 50;
            var paddleSpeed = 10;
            var state = new BreakoutState(brickGrid, paddleHalfWidth, paddleSpeed, 5);
            var paddle = state.getPaddle();
            var originalPaddlePosition = paddle.getTopCenter().x();
            
            paddle.setMotionDirection(PaddleMotionDirection.LEFT);
            state.tick(2);
            
            assertEquals(originalPaddlePosition - 20, paddle.getTopCenter().x());
        }
        
        @Test
        void ballBouncingOffEastWall()
        {
            var brickGrid = new BrickGrid(10, 10, 100, 20);
            var paddleHalfWidth = 50;
            var paddleSpeed = 10;
            var state = new BreakoutState(brickGrid, paddleHalfWidth, paddleSpeed, 5);
            
            var ballPosition = new Point(900, 30);
            var ballGeometry = new Circle(ballPosition, 25);
            var ballVelocity = new Vector(25, 10);
            var ballBehavior = new BallBehavior();
            var ball = state.addBall(ballGeometry, ballVelocity, ballBehavior);
            
            state.tick(4);
            
            assertEquals(new Point(950, 70), ball.getCenter());
            assertEquals(new Vector(-25, 10), ball.getVelocity());
        }
    }
    
	    @Test
	    void checkSetup() {
	    	var state = TestSetup.mkHitState("#", " ", new BallBehavior());
	    	
	    	assertEquals(1, state.getBricks().size());
	    	state.tick(120);
	    	assertEquals(0, state.getBricks().size());
	    	assertEquals(1, state.getBalls().size());
	    }
    
    }
}
