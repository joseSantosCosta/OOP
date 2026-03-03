package ogp.math;

import java.util.Objects;

import ogp.util.MPOOPLegitGenerated;

/**
 * Represents a rectangle in a 2-dimensional integer coordinate system.
 * The rectangle's sides are parallel to X- and Y-axis.
 * Mathematically, the rectangle can be written as
 * [left, left + width] x [top, top + bottom].
 */
public class Rectangle
{
    private long left;

    private long top;

    /**
     * @invar | width >= 0
     */
    private long width;

    /**
     * @invar | height >= 0
     */
    private long height;

    /**
     * Construct a new rectangle with given the coordinates of its top left
     * and bottom right corners.
     * 
     * @post | getLeft() == topLeft.x()
     * @post | getTop() == topLeft.y()
     * @post | getWidth() == bottomRight.x() - topLeft.x()
     * @post | getHeight() == bottomRight.y() - topLeft.y()
     * @throws IllegalArgumentException
     *   | topLeft == null
     * @throws IllegalArgumentException
     *   | bottomRight == null
     * @throws IllegalArgumentException
     *   | topLeft.x() > bottomRight.x()
     * @throws IllegalArgumentException
     *   | topLeft.y() > bottomRight.y()
     */
    public Rectangle(Point topLeft, Point bottomRight)
    {
        this(topLeft.x(), topLeft.y(), bottomRight.x() - topLeft.x(), bottomRight.y() - topLeft.y());
    }

    /**
     * Create rectangle given the coordinates of its top left corner
     * and its dimensions.
     * 
     * @post | getLeft() == left
     * @post | getTop() == top
     * @post | getWidth() == width
     * @post | getHeight() == height
     * @throws IllegalArgumentException
     *   | width < 0
     * @throws IllegalArgumentException
     *   | height < 0
     */
    public Rectangle(long left, long top, long width, long height)
    {        
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Returns x-coordinate of the rectangle's left side.
     */
    public long getLeft()
    {
        return left;
    }
    
    /**
     * Returns the y-coordinate of the rectangle's top side.
     * Note that since the Y-axis is pointing down,
     * the top side has a lower Y-coordinate than the bottom side.
     */
    public long getTop()
    {
        return top;
    }

    /**
     * Returns the width of this rectangle.
     * 
     * @post | result >= 0
     */
    public long getWidth()
    {
        return width;
    }

    /**
     * Returns the height of this rectangle.
     * 
     * @post | result >= 0
     */
    public long getHeight()
    {
        return height;
    }

    /**
     * Returns the x-coordinate of the right side of this rectangle.
     * 
     * @post | result == getLeft() + getWidth()
     */
    public long getRight()
    {
        return left + width;
    }    

    /**
     * Returns the y-coordinate of the bottom side of this rectangle.
     * 
     * @post | result == getTop() + getHeight()
     */
    public long getBottom()
    {
        return top + height;
    }

    /**
     * Updates the x-coordinate of the left side of this rectangle.
     * The width of this rectangle remains unaffected, i.e., using this
     * method moves this rectangle horizontally.
     * 
     * @post | getLeft() == left
     * @mutates_properties | getLeft()
     */
    public void setLeft(long left)
    {
        this.left = left;
    }

    /**
     * Updates the y-coordinate of the top side of this rectangle.
     * The height remains unaffected, i.e., using this method
     * moves the rectangle vertically.
     *   
     * @post | getTop() == top
     * @mutates_properties | getTop()
     */
    public void setTop(long top)
    {
        this.top = top;
    }

    /**
     * Updates the width of this rectangle.
     * The width cannot be negative, but is allowed to be zero.
     *
     * @pre | width >= 0
     * @post | getWidth() == width
     * @mutates_properties | getWidth()
     */
    public void setWidth(long width)
    {
        this.width = width;
    }

    /**
     * Updates the height of this rectangle.
     * The height cannot be negative, but is allowed to be zero.
     *
     * @pre | height >= 0
     * @post | getHeight() == height
     * @mutates_properties | getHeight()
     */
    public void setHeight(long height)
    {
        this.height = height;
    }
    
    /** 
     * Return the top-left point of this rectangle
     * 
     * @creates | result
     * @post | result != null
     * @post | result.equals(new Point(getLeft(), getTop()))
     */
    public Point getTopLeft()
    {
        return new Point(left, top);
    }

    /**
     * Return the bottom-right point of this rectangle
     * 
     * @creates | result
     * @post | result != null
     * @post | result.equals(new Point(getLeft() + getWidth(), getTop() + getHeight()))
     */
    public Point getBottomRight()
    {
        return new Point(left + width, top + height);
    }

    /**
     * Returns whether given point is inside this rectangle.
     * 
     * @pre | point != null
     * @post | result == (getLeft() <= point.x() && getTop() <= point.y() && point.x() <= getRight() && point.y() <= getBottom())
     */
    public boolean contains(Point point)
    {
        return getLeft() <= point.x() && getTop() <= point.y() && point.x() <= getRight() && point.y() <= getBottom();
    }

    /**
     * Return whether this rectangle contains a given circle.
     * 
     * LEGIT
     */
    @MPOOPLegitGenerated
    public boolean contains(Circle circle)
    {
        return contains(circle.getBoundingRectangle());
    }

    /**
     * Return whether this rectangle contains a given other rectangle.
     * 
     * @post | result == (contains(other.getTopLeft()) && contains(other.getBottomRight()))
     */
    public boolean contains(Rectangle other)
    {
        return contains(other.getTopLeft()) && contains(other.getBottomRight());
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public Point getBottomCenter()
    {
        var x = left + width / 2;
        var y = top + height;

        return new Point(x, y);
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public Point getCenter()
    {
        var x = left + width / 2;
        var y = top + height / 2;

        return new Point(x, y);
    }

    /**
     * Returns a copy of this rectangle.
     * 
     * @creates result
     * @post | result != null
     * @post | result.equals(this)
     */
    public Rectangle copy()
    {
        return new Rectangle(left, top, width, height);
    }
    
    /**
     * Returns a new rectangle with same left, top and width properties.
     * The height equals this rectangle's height plus the given extra height.
     * 
     * @creates | result
     * @pre | getHeight() + extraHeight >= 0
     * @post | result != null
     * @post | result.getLeft() == getLeft()
     * @post | result.getTop() == getTop()
     * @post | result.getWidth() == getWidth()
     * @post | result.getHeight() == getHeight() + extraHeight
     */
    public Rectangle growHeight(long extraHeight)
    {
        return new Rectangle(left, top, width, height + extraHeight);
    }

    public Rectangle growWidth(int extra) {
    	return new Rectangle(left, top, width + extra, height);
	}

	/**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public int hashCode()
    {
        return Objects.hash(left, top, width, height);
    }

    /**
     * LEGIT
     */
    @Override
    @MPOOPLegitGenerated
    public boolean equals(Object obj)
    {
        if ( obj instanceof Rectangle that )
        {
            return this.left == that.left && this.top == that.top && this.width == that.width && this.height == that.height;
        }
        else
        {
            return false;
        }
    }

    /**
     * LEGIT
     */
    @MPOOPLegitGenerated
    public String toString()
    {
        return String.format("Rectangle[left=%s, top=%s, width=%s, height=%s]", left, top, width, height);
    }
    
    public Rectangle thickening() {
    	Rectangle res = this.copy();
    	res.width = res.width * 2;
    	res.height = res.height * 2;
    	res.top = this.top - this.height / 2;
    	res.left = this.left - this.width/ 2;
    	
    	return res;
    }
}
