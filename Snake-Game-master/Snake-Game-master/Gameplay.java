package snippet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener
{
	
	private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private int lengthofsnake = 3;
    
    private Timer timer;
    private int delay = 100;
    
    private boolean gameOver = false;
    
    private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyypos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private Random random=new Random();
    private int xpos =random.nextInt(34);
    private int ypos=random.nextInt(23);
    
    private int score=0;
    private int moves = 0;
    

    
	public Gameplay()
	{
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
	}
	public void paint (Graphics g)
	{
		if(moves == 0)
        {
            snakexlength[2] = 50; 
            snakexlength[1] = 75;
            snakexlength[0] = 100;
            
            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;
        }
		
		// Draw title border
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 51);
		
		// Draw title
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("SNAKE GAME", 350, 40);
		
		// Draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// Draw background for gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		// Draw scores with better visibility
		g.setColor(Color.BLACK);
		g.fillRect(750, 15, 120, 40);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Score: " + score, 755, 32);
		g.drawString("Length: " + lengthofsnake, 755, 50);
		
		// Draw snake
        for(int a = 0; a < lengthofsnake; a++)
        {
            if(a == 0) // Snake head
            {
                g.setColor(Color.YELLOW);
                g.fillOval(snakexlength[a], snakeylength[a], 25, 25);
                
                // Draw eyes based on direction
                g.setColor(Color.BLACK);
                if(right)
                {
                    g.fillOval(snakexlength[a] + 15, snakeylength[a] + 5, 3, 3);
                    g.fillOval(snakexlength[a] + 15, snakeylength[a] + 15, 3, 3);
                }
                else if(left)
                {
                    g.fillOval(snakexlength[a] + 5, snakeylength[a] + 5, 3, 3);
                    g.fillOval(snakexlength[a] + 5, snakeylength[a] + 15, 3, 3);
                }
                else if(up)
                {
                    g.fillOval(snakexlength[a] + 5, snakeylength[a] + 5, 3, 3);
                    g.fillOval(snakexlength[a] + 15, snakeylength[a] + 5, 3, 3);
                }
                else if(down)
                {
                    g.fillOval(snakexlength[a] + 5, snakeylength[a] + 15, 3, 3);
                    g.fillOval(snakexlength[a] + 15, snakeylength[a] + 15, 3, 3);
                }
            }
            else // Snake body
            {
                g.setColor(Color.GREEN);
                g.fillRect(snakexlength[a], snakeylength[a], 25, 25);
            }
        }
        
        // Check if food is eaten
        if((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]))
        {
        	lengthofsnake++;
        	score++;
        	xpos = random.nextInt(34);
        	ypos = random.nextInt(23);
        }
        
        // Draw food
        g.setColor(Color.RED);
        g.fillOval(enemyxpos[xpos], enemyypos[ypos], 25, 25);
        
        // Check collision with self
        for(int b = 1; b < lengthofsnake; b++)
        {
        	if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0])
        	{
        		gameOver = true;
        		timer.stop();
        	}
        }
        
        // Draw game over screen
        if(gameOver)
        {
        	g.setColor(Color.WHITE);
        	g.setFont(new Font("Arial", Font.BOLD, 50));
        	g.drawString("GAME OVER", 300, 300);
        	
        	g.setFont(new Font("Arial", Font.BOLD, 20));
        	g.drawString("Press Space to Restart", 350, 340);
        }
        
        g.dispose();
	}
	@Override
    public void keyTyped(KeyEvent ke) {
      
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    	if(e.getKeyCode()==KeyEvent.VK_SPACE)
    	{
    		moves = 0;
    		score = 0;
    		lengthofsnake = 3;
    		gameOver = false;
    		right = false;
    		left = false;
    		up = false;
    		down = false;
    		timer.start();
    		repaint();
    	}
    	if(e.getKeyCode()==KeyEvent.VK_RIGHT)
    	{
    		moves++;
    		right=true;
    		if(!left)
    		{
    			right=true;
    		}
    		else
    		{
    			right=false;
    			left=true;
    		}
    		up=false;
    		down=false;
    	}
    	if(e.getKeyCode()==KeyEvent.VK_LEFT)
    	{
    		moves++;
    		left=true;
    		if(!right)
    		{
    			left=true;
    		}
    		else
    		{
    			left=false;
    			right=true;
    		}
    		up=false;
    		down=false;
    	}
    	if(e.getKeyCode()== KeyEvent.VK_UP)
    	{
    		moves++;
    		up=true;
    		if(!down)
    		{
    			up=true;
    		}
    		else
    		{
    			up=false;
    			down=true;
    		}
    		left=false;
    		right=false;
    	}
    	if(e.getKeyCode()== KeyEvent.VK_DOWN)
    	{
    		moves++;
    		down=true;
    		if(!up)
    		{
    			down=true;
    		}
    		else
    		{
    			down=false;
    			up=true;
    		}
    		left=false;
    		right=false;
    	}
    }


    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    	if(gameOver) return;
    	
    	// Move snake body
    	for(int r = lengthofsnake - 1; r >= 0; r--)
    	{
    		if(r == 0)
    		{
    			// Move head based on direction
    			if(right) snakexlength[r] += 25;
    			if(left) snakexlength[r] -= 25;
    			if(up) snakeylength[r] -= 25;
    			if(down) snakeylength[r] += 25;
    		}
    		else
    		{
    			// Move body segments
    			snakexlength[r] = snakexlength[r-1];
    			snakeylength[r] = snakeylength[r-1];
    		}
    	}
    	
    	// Handle wall wrapping
    	if(snakexlength[0] > 850) snakexlength[0] = 25;
    	if(snakexlength[0] < 25) snakexlength[0] = 850;
    	if(snakeylength[0] > 625) snakeylength[0] = 75;
    	if(snakeylength[0] < 75) snakeylength[0] = 625;
    	
    	repaint();
    }
}



















