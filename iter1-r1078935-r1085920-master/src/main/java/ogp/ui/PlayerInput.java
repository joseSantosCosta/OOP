package ogp.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ogp.paddles.PaddleMotionDirection;
import ogp.util.MPOOPLegitGenerated;

/**
 * LEGIT
 */
@MPOOPLegitGenerated
public class PlayerInput
{
    private boolean leftKeyDown = false;

    private boolean rightKeyDown = false;

    public PlayerInput(java.awt.Component component)
    {
        component.addKeyListener(new KeyListener());
    }

    public PaddleMotionDirection getPaddleMovement()
    {
        if ( leftKeyDown && !rightKeyDown )
        {
            return PaddleMotionDirection.LEFT;
        }
        else if ( !leftKeyDown && rightKeyDown )
        {
            return PaddleMotionDirection.RIGHT;
        }
        else
        {
            return PaddleMotionDirection.STATIONARY;
        }
    }

    private class KeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch ( e.getKeyCode() )
            {
            case KeyEvent.VK_RIGHT:
                rightKeyDown = true;
                break;

            case KeyEvent.VK_LEFT:
                leftKeyDown = true;
                break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            switch ( e.getKeyCode() )
            {
            case KeyEvent.VK_RIGHT:
                rightKeyDown = false;
                break;

            case KeyEvent.VK_LEFT:
                leftKeyDown = false;
                break;
            }
        }
    }
}
