import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Branch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Branch extends Platform
{
    /**
     * Act - do whatever the Branch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int breakCount = 100;
    private GreenfootImage branch = new GreenfootImage("BranchResize.png");
    private GreenfootImage branch2 = new GreenfootImage("Branch2.png");
    private GreenfootImage branch3 = new GreenfootImage("Branch3.png");
    //private GreenfootImage branch4 = new GreenfootImage("Branch4.png");
    public void act() 
    {
      breakCount--;
      if(isTouching(Witch.class)){
        crumble();
      }
    } 
    private void crumble(){
        if (breakCount >= 100){
            setImage(branch);
        }
        else if (breakCount >= 75){
            setImage(branch2);
        }
        else if (breakCount >= 50){
            setImage(branch3);
        }
        // else if (breakCount >= 1800){
            // setImage(branch4);
        // }
        else if (breakCount >= 10){
          getWorld().removeObject(this);
      }
    }
}