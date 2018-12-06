import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SuperClass of Badguys
 * To-do inheritance certralized methods utilized by all subclasses
 * @author Philip Jepkes
 * @version (V1.0)
 */
public abstract class Enemy extends SmoothMover
{  //private int health;
    private int damageAmount;
    private int health;
    private int randomDeathImage;
    private static int[] healthValues={1};
    private static int[] dmgValues={1,2};
    protected GreenfootSound sound;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  
     protected void removeFromDmg(int arrayHealthIndex){
        health = healthValues[arrayHealthIndex];
        if (isTouching(Fireball.class)&& this !=null)
        {
            //removeTouching(Fireball.class);
            health--;
        } 
        if(health<=0)
        {
            GameWorld world =(GameWorld) getWorld(); 
            sound =new GreenfootSound("clap.mp3");
            sound.play();
            world.addObject(new Death(randomDeathImage),getX(),getY());
            world.removeObject(this);
        }
        return;
        //{GameWorld world =(GameWorld) getWorld(); 
        // addtoscore greenfootsound play
        //world.addObject(new Death(randomDeathImage),getX(),getY())
        //world.removeObject(this); return;}
    }

    protected void dealDmg(int arrayDmgIndex)
    {
        damageAmount=dmgValues[arrayDmgIndex];
        GameWorld world =(GameWorld) getWorld(); 
        if(isTouching(Witch.class))
        {
            world.changeHealth(-damageAmount);
        }
    }
    
    /** Tyler added this for transparency
      * trims white from images
      */
    public final void trimWhite(){
        int range = 25; //Should be in the range 0-255.
        GreenfootImage img = getImage();
        Color transparent = new Color(0, 0, 0, 0);
        for(int x = 0; x < img.getWidth(); x++)
        {
            for(int y = 0; y < img.getHeight(); y++)
            {
                Color color = img.getColorAt(x, y);
                if(color.getRed()   > 255 - range
                && color.getGreen() > 255 - range
                && color.getBlue()  > 255 - range)
                    img.setColorAt(x, y, transparent);
            }
        }
    }
}
