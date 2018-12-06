import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * @author Camille Otillio 
 * @version 1
 */

public class MushTest extends Platforms   
{
    private static final int NUMBER_IMAGES= 20;
    private static GreenfootImage[] images;
    private int currentImage = 20;
    
    private boolean hasJumped = false;
    /**
     * Act - do whatever the Projectile_1End wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        initializeImages();
        grow();
    }
    private static void initializeImages() 
    {
        //if (images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("Cloud.png");
            images = new GreenfootImage[NUMBER_IMAGES];
            for (int i = 0; i < NUMBER_IMAGES; i++) 
            {
                int size = (i+1) * 3 * ( baseImage.getWidth() / NUMBER_IMAGES );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    private void grow()
    {
        
        // if  (currentImage < NUMBER_IMAGES) 
        // {
            // setImage(images[currentImage]);
            // currentImage++;
        // }
        
        {
            if (currentImage > 0){
            currentImage--;
            setImage(images[currentImage]);
        }
        }
    }
    private void witchJump(){
        if(isTouching(Witch.class))
        {
            hasJumped = true;
            //grow();
        }
    }
}

