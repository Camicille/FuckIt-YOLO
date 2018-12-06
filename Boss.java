import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A boss that moves in a set pattern
 * To-do more intricate pattern  
 * @author (Philip Jepkes) 
 * @version (V1.0)
 */
public class Boss extends Enemy
{

    public Boss()
    {
        setImage("tux.png");
        //MyWorld w =(MyWorld)getWorld();
        //w.Boss = true
    }

    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        clickMe();

        

    }  

    private void clickMe()
    {
        clickMe("What time is it? HammerTime or HammerTime?",
        "HammerTime","Stop ,HammerTime","It's not that time");

    }

    private void clickMe(String ask,String answer,String Response, String Rejection)
    {
        if(Greenfoot.mousePressed(this))
        {
            String question=Greenfoot.ask(ask);
            if(question.contains(answer))
            {

                getWorld().showText(Response,400,300);
            }
            else
            {
                getWorld().showText(Rejection,400,300);
            }
        } 
    }

}
