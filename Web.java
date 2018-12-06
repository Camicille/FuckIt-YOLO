import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * object Spider creates
 * 
 * @author Philip Jepkes 
 * @version (v1)
 */
public class Web extends Spider
{  
    protected GreenfootImage web= new GreenfootImage(32,maxDistance);
    public Web()
    {
        web.drawLine(16,spawnPosition,16,spawnPosition+downSpeed);

        setImage(web);

    }

    /**
     * Act - do whatever the Web wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        //web.scale(web.getWidth(),maxDistance/downSpeed+(webCounter));  
    }

}
