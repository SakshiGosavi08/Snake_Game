package snakegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    private Image apple;
    private Image head;
    private Image dot;
    
    private final int Alldots=900;
    private final int dotsize=10;
    
    private int apple_x;
     private int apple_y;
    
    
    private final int Randomposition=29;
    private final int x[]=new int[Alldots];
    private final int y[]=new int[Alldots];
    
    private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downDirection=false;
    
    private boolean ingame=true;


    
    
    
    private int dots;
    private Timer timer;
    Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
        loadimages();
        initGame();
    
}
    public void loadimages()
    {
       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
       apple=i1.getImage();
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot=i2.getImage();
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head=i3.getImage();
       

        
    }
    public void initGame()
    {
        dots=3;
        for(int i=0;i<dots;i++)
        {
           y[i]=50;
           x[i]=50-i*dotsize;
                  }
         locateapple();
           
           timer=new Timer(200,this);
           timer.start();

        
    }
    public void locateapple()
    {
        int r=(int)(Math.random()*Randomposition);
        apple_x=r*dotsize;
         r=(int)(Math.random()*Randomposition);
         apple_y=r*dotsize;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        if(ingame)
        {
             g.drawImage(apple,apple_x,apple_y,this);
        for(int i=0;i<dots;i++)
        {
            if(i==0)
            {
                g.drawImage(head,x[i],y[i], this);
            }
            else
            {
                g.drawImage(dot,x[i],y[i],this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
        }
        else
        {
            gameOver(g);
        }
       
    }
    public void gameOver(Graphics g)
    {
        String msg="GAME OVER!!";
        Font font =new Font("SAN_SERIF",Font.BOLD,14);
        FontMetrics metrices =getFontMetrics(font);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg,(300-metrices.stringWidth(msg))/2,300/2);
    }
    
    public void move()
    {
        for(int i=dots;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        
         if(leftDirection)
        {
            x[0]=x[0]-dotsize;
        }
         if(rightDirection)
        {
            x[0]=x[0]+dotsize;
        }
          if(upDirection)
        {
            y[0]=y[0]-dotsize;
        }
           if(downDirection)
        {
            y[0]=y[0]+dotsize;
        }
        
      
    }
    public void checkApple()
    {
        if((x[0]==apple_x)&&(y[0]==apple_y))
        {
            dots++;
            locateapple();
        }
    }
    public void checkCollision()
    {
        for(int i=dots;i>0;i--)
        {
            if((i>4)&&(x[0]==x[i])&&(y[0]==y[i]))
            {
                ingame=false;
            }
        }
        
        if(y[0] >= 300)
        {
            ingame=false;
        }
        if(x[0] >= 300)
        {
            ingame=false;
        }
        if(y[0] < 0)
        {
            ingame=false;
        }if(x[0] < 0)
        {
            ingame=false;
        }
        if(!ingame)
        {
            timer.stop();
        }
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(ingame)
        {
        checkApple();
        move();
        repaint();
        checkCollision();
        }
    }
    
    public class TAdapter extends KeyAdapter
    {
        @Override
       public void keyPressed (KeyEvent e) 
       {
           int key=e.getKeyCode();
           if(key==KeyEvent.VK_LEFT && (!rightDirection))
           {
               leftDirection=true;
               upDirection=false;
               downDirection=false;
           }
           if(key==KeyEvent.VK_RIGHT && (!leftDirection))
           {
               rightDirection=true;
               upDirection=false;
               downDirection=false;
           }
           if(key==KeyEvent.VK_UP && (!downDirection))
           {
              
               upDirection=true;
                leftDirection=false;
               rightDirection=false;
           }
           if(key==KeyEvent.VK_DOWN && (!upDirection))
           {
               downDirection=true;
               leftDirection=false;
               rightDirection=false;
           }
       }
    }
}