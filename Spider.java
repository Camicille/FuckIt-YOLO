import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Spider: That will drop and wiggle 
 * To do fall from branch quickly then ascend slowly creating a string
 * @author Philip Jepkes
 * @version (V0)
 */
public class Spider extends Enemy
{   private int frameCount=0;
    private int imageUpTick=0;
    protected int webCounter=0;
    protected int maxDistance=0;
    protected  int spawnPosition=0;
    private int health=1;
    private int damageSlot=0;
    private double upSpeed=.5;
    protected static int downSpeed=2;
    private int randomDeathImage=Greenfoot.getRandomNumber(3);
    private boolean inRange;
    protected boolean goDown;
    private static GreenfootImage[] images;
   
    private counter weblength;

    public Spider(int spawnLocationY,int travelDistance)
    {
        //intializeImages();
        spawnPosition=Math.abs(spawnLocationY);//-half image height
        maxDistance=Math.abs(travelDistance);
        
        //weblength = new counter(0,maxDistance);

    }

    public Spider()
    {
        this(48,128);
    }

    /**
     * Act - do whatever the Spider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // 
        movement();
        if(isTouching(Witch.class) && this !=null)
        {
            dealDmg(damageSlot);
        }
        damage();
        
    }  

    private void movement()
    {
        if (getY() <= (spawnPosition)){
            goDown = true;
            // GameWorld world=(GameWorld) getWorld();
            // List<Web> webs= world.getObjectsAt(getX(),spawnPosition+maxDistance,Web.class);
            // if (webs.size()>0){
                // world.removeObjects(webs);
            // }
            //add || facingLeft
        }

        if (getY() >=(spawnPosition+maxDistance)){
            goDown = false;

            //add || facingRight
        } 
        if (goDown)
        {
            setLocation(getX(),getY()+downSpeed);
            createWeb();
        }
        if(!goDown)
        {        
            GameWorld world=(GameWorld) getWorld();
            List<Web> webs= world.getObjectsAt(getX(),spawnPosition+maxDistance,Web.class);
           //Actor webs = getOneIntersectingObject(Web.class);
           setLocation(getX(),getExactY()-upSpeed);
           if(getY()<=spawnPosition+downSpeed/2)
           {
               world.removeObjects(webs);
               
           }
            
            //getOneIntersectingObject(Web.class);
           
        }

        
        //GameWorld world =(GameWorld) getWorld();
        //int sallyPosX =
        //if(getY()<=spawnPostion &&){}
        //drop pattern if player is in range and or timer conditional
    }
       private void damage()
    {
        if (isTouching(Fireball.class)&& this !=null)
        {
            removeTouching(Fireball.class);
            health--;
        } 
        if(health<=0)
        {
            GameWorld world =(GameWorld) getWorld(); 
            sound =new GreenfootSound("clap.mp3");
            sound.play();
            world.changeScore(10);
            world.addObject(new Death(randomDeathImage),getX(),getY());
            List<Web> webs= world.getObjectsAt(getX(),spawnPosition+maxDistance,Web.class);
            world.removeObjects(webs);
            world.removeObject(this);
        }
        return;
        //{GameWorld world =(GameWorld) getWorld(); 
        // addtoscore greenfootsound play
        //world.addObject(new Death(randomDeathImage),getX(),getY())
        //world.removeObject(this); return;}
    }
    

    private void checkForSally(){
    }

    private void createWeb()
    {
        GameWorld world=(GameWorld) getWorld();
        List<Web> webs= world.getObjects(Web.class);
        List<Spider> spiders= world.getObjects(Spider.class);
        

        world.addObject(new Web(),getX(),getY());
        
    }
    //System.out.println(spiders.size());

        
    // if (webs.size()<1){
    // return;}
    // else if (webs.size()>0){
    // world.removeObjects(webs);
    // world.addObject(new Web(),getX(),spawnPosition);}


}


