import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main character
 * 
 * @author Marlene Inoue & Ed Parrish
 * @version 1.2 28 November 2018
 */
public class Witch extends Sprite {
    private static final double MOVE_SPEED = 1.6;

    //gravity and jumping
    private static final double GRAVITY = 0.8;
    private static final double MAX_VEL = 15;
    private static final int JUMP = 10;
    private boolean canJump = true;

    //running animation array
    private static GreenfootImage faceRight;
    private static GreenfootImage faceLeft;
    private static GreenfootImage[] runRightImages;
    private static GreenfootImage[] runLeftImages;
    private static final int RUN_COUNT = 6; //FIXME: NEED TO CHANGE AMOUNT OF IMAGEAS

    //time of fireball fire
    private counter coolDown = new counter(20);
    private counter potionCool = new counter(50);

    //checking whether facing left or right
    private int facing = 1;

    //animation
    private IncQuo animationSpeed = new IncQuo(1, 5);
    private static polar animationCount;

    //invinsibility
    public counter invinsibility = new counter(50,0);
    /**
     * Default constructor of Witch
     */
    public Witch() {
        initializeImages();
    }

    /**
     * @Ed
     * 
     * Put the images into the image arrays
     */
    public static void initializeImages() {
        if (runRightImages == null) {
            //make standing pimage
            faceRight = new GreenfootImage("standing.png");
            faceLeft = new GreenfootImage(faceRight);
            faceLeft.mirrorHorizontally();

            //load running images
            runRightImages = new GreenfootImage[RUN_COUNT];
            for (int i = 0; i < RUN_COUNT; ++i) {
                String file = "run" + (i + 1) + ".png";
                runRightImages[i] = new GreenfootImage(file);
            }
            runLeftImages = flipImages(runRightImages);

            animationCount = new polar(RUN_COUNT - 1);
        }
    }

    /**
     * 
     * @Ed
     * Flip the images from facing right to left
     * 
     * @param imgs  the images to be added to the left facing image array
     */
    private static GreenfootImage[] flipImages(GreenfootImage[] imgs) {
        GreenfootImage[] flip = new GreenfootImage[imgs.length];

        int i = 0;

        while (i < imgs.length) {
            flip[i] = new GreenfootImage(imgs[i]);
            flip[i].mirrorHorizontally();
            ++i;
        }

        return flip;
    }

    /**
     * @Ed
     * 
     * Check for vertical collisions and adjust jumping or falling.
     */
    public void checkVertical() {
        double yVel = getYVelocity();
        int lookY = 0;

        if (yVel > 0) {
            lookY = (int) (yVel + GRAVITY + getImage().getHeight() / 2);
        } else {
            lookY = (int) (yVel + GRAVITY - getImage().getHeight() / 2);
        }

        //check for vertical collision in this cycle
        Actor actor = getOneObjectAtOffset(0, lookY, Platform.class);
        //SmoothMover actor = (SmoothMover)getOneObjectAtOffset(0, lookY, SmoothMover.class);
        if (actor == null) {
            //no collision in this cycle
            applyGravity();
            canJump = false;
        } else {
            //collision detected
            moveToContactVertical(actor);
            if (yVel > 0) {

                canJump = true;

                setYVelocity(0.0);
            }
        }
    }

    /**
     * @Ed
     * 
     * Move the Witch into contact with the specified actor vertically
     * 
     * @param target  the target this sprite is approaching
     */
    public void moveToContactVertical(Actor target) {
        int height2 = (target.getImage().getHeight() + getImage().getHeight()) / 2;
        int newY = 0;

        if (target.getY() > getY()) {
            newY = target.getY() - height2;
        } else {
            newY = target.getY() + height2;
        }

        setLocation(getX(), newY);
    }

    /**
     * @Ed
     * 
     * Check for a horizontal collision with a platform.
     */
    public void checkHorizontal() {
        double velocityX = getXVelocity();
        int lookX = 0;

        if (velocityX < 0) {
            lookX = (int) velocityX - getImage().getWidth() / 2;
        } else {
            lookX = (int) velocityX + getImage().getWidth() / 2;
        }

        Actor a = getOneObjectAtOffset(lookX, 0, Platform.class);

        if (a != null) {
            moveToContactHorizontal(a);

            stopMoving();
        }
    }

    /**
     * @Ed
     * 
     * Move this Actor into contact with the specified Actor in the
     * horizontal (x) direction.
     *
     * @param target The target this sprite is approaching.
     */
    public void moveToContactHorizontal(Actor target)
    {
        int w2 = (getImage().getWidth() + target.getImage().getWidth()) / 2;
        int newX = 0;

        if (target.getX() > getX()) {
            newX = target.getX() - w2;
        }  else {
            newX = target.getX() + w2;
        }

        setLocation(newX, getY());
    }

    /**
     * @Ed-ish
     * 
     * Checks and responds to certain key presses
     */
    private void keyPress() {
        //move left right and stationary
        if (Greenfoot.isKeyDown("d")) {
            moveRight();
        } else if (Greenfoot.isKeyDown("a")) {
            moveLeft();
        } else {
            stopMoving();
        }

        //jump
        if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")) {
            jump();
        }

        //attack with fireball
        if(Greenfoot.isKeyDown("f")){
            if(coolDown.full()){
                getWorld().addObject(new Fireball(5 * facing),
                    getX()+facing*getImage().getWidth(),
                    getY());
                coolDown.reset();
            }
        } 

        if(Greenfoot.isKeyDown("f")){
            if(coolDown.full()){
                getWorld().addObject(new Fireball(5 * facing),
                    getX()+facing*getImage().getWidth(),
                    getY());
                coolDown.reset();
            }
        }

        if(Greenfoot.isKeyDown("p")){
            GameWorld world = (GameWorld)getWorld();
            if(potionCool.full() && world.numPotions > 0){
                world.changeHealth(10);
                world.numPotions--;
                world.updatePotions();
                potionCool.reset();
            }
        }
    }

    /**
     * @Ed
     * 
     * Move to the right.
     */
    public void moveRight() {
        setXVelocity(MOVE_SPEED);

        if (animationSpeed.poll() > 0) {
            setImage(runRightImages[animationCount.poll()]);
        }

        facing = 1;
    }

    /**
     * @Ed
     * 
     * Move to the left.
     */
    public void moveLeft() {
        setXVelocity(-MOVE_SPEED);

        if (animationSpeed.poll() > 0) {
            setImage(runLeftImages[animationCount.poll()]);
        }

        facing = -1;
    }

    /**
     * @Ed
     * 
     * The Witch doesn't move in any direction
     */
    public void stopMoving() {
        setXVelocity(0.0);

        if (facing > 0) {
            setImage(faceRight);
        } else if (facing < 0) {
            setImage(faceLeft);
        }
    }

    /**
     * @Ed
     * 
     * Makes character jump
     */
    private void jump() {
        if (canJump) {
            setYVelocity(-JUMP);
            canJump = false;
        }
    }

    /**
     * @Ed
     * 
     * Apply gravity when the Witch is jumping or falling
     */
    public void applyGravity() {
        double yVelocity = getYVelocity() + GRAVITY;

        if (yVelocity > MAX_VEL) {
            yVelocity = MAX_VEL;
        }

        setYVelocity(yVelocity);
    }

    /**
     * @Ed
     * 
     * Moves based on velocity, overriding the move() method in sprite to
     * enable scrolling
     */
    @Override public void move() {
        super.move();
        double dx = getXVelocity();
        GameWorld w = (GameWorld )getWorld();

        if (w == null || dx == 0) return;

        w.scrollHorizontal(dx);
        setLocation(w.getWidth() / 2, getY()); // stay in horizontal center
    }

    /**
     * Reducing Witch's hp when touching an enemy
     * FIXME: move audio play to a spot when takes damage
     */
    private void touchEnemies() {
        GameWorld world = (GameWorld) getWorld();

        if (world.getHealth() <= 0) {
            world.removeObject(this);

            Greenfoot.playSound("Ouch.wav");
        }
    }

    /**
     * At end game, when the cat walks to the witch
     */
    private void touchCat() {
        if (isTouching(Cat.class)) {
            setLocation(getX(), getY() - 5);
        }
    }

    private void mushJump(){
        if(isTouching(Mushroom.class)){
            setYVelocity(-15.0);
        }
    }
    //Actor a = getOneObjectAtOffset(lookX, 0, Branch.class);
    private void branchLand(){
        if(isTouching(Branch.class)){
            //setYVelocity(0.0);
        }
    }

    /**
     * Act - do whatever the Witch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        invinsibility.poll();

        keyPress();

        checkHorizontal();
        checkVertical();

        mushJump();
        //branchLand();
        touchCat();

        move();

        coolDown.poll();
        potionCool.poll();
        touchEnemies();            
    }
}