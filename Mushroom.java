import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Grows to help the witch Jump
 * 
 * @author Camille Otillio (with some code from Ed Parrish)
 * @version (a version number or a date)
 */
public class Mushroom extends Platforms
{
    /**
     * Act - do whatever the Mushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static final int NUMBER_IMAGES= 20;
    private static GreenfootImage[] images;
    private int currentImage = 10;

    private boolean hasJumped = false;
    private boolean hasGrown = false;
    private boolean hasShrunk = false;
    public void act() 
    {
        initializeImages();
        witchJump();
        if(hasJumped == true)
        {
            grow();
        }
        if(hasGrown == true)
        {
            shrink();
        }
        if (hasShrunk == true){
            setImage(images[10]);
        }
    }

    private static void initializeImages() 
    {
        if (images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("mushResize.png");
            images = new GreenfootImage[NUMBER_IMAGES];
            for (int i = 0; i < NUMBER_IMAGES; i++) 
            {
                int width = baseImage.getWidth();
                int height = (i+1) * 3 * ( baseImage.getWidth() / NUMBER_IMAGES );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(width, height);
            }
        }
    }
    private void grow()
    {
       if  (currentImage <= NUMBER_IMAGES) {
           setImage(images[currentImage]);
           currentImage++;
        }
       if(currentImage == NUMBER_IMAGES){
            hasGrown = true;
        }
   
    }
    private void shrink()
    {
        if (currentImage > 0){
            currentImage--;
            setImage(images[currentImage]);
        }
        if (currentImage == 0){
           hasShrunk = true;     
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
