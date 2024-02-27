package GameAssets;

public class Body {
    protected Vector position;
    protected Vector velocity;
    protected Ball[] shape;

    /**
     * Creates a blank Game Body
     */
    public Body(){
        position = new Vector();
        velocity = new Vector();
        shape = new Ball[0];
    }

    /**
     * Creates a Game Body with specified position
     * @param x     X position in pixels
     * @param y     Y position in pixels
     */
    public Body(int x, int y){
        position = new Vector(x, y);
        velocity = new Vector();
        shape = new Ball[0];
    }

    /**
     * Re-Initialises and shows the Body
     * @param x     X position in pixels
     * @param y     Y position in pixels
     */
    public void init(double x, double y) {
        this.moveTo(x, y);
        this.position.x = x;
        this.position.y = y;

        this.velocity.x = 0;
        this.velocity.y = 0;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    public void setVelocity(double x, double y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }

    public void addTo(GameArena arena) {
        for (Ball ball : shape) {
            arena.addBall(ball);
        }
    }

    public void removeFrom(GameArena arena) {
        for (Ball ball : shape) {
            arena.removeBall(ball);
        }
    }

    /**
     * moves the body to position with, x, and y
     * @param x         pixel to move to in x dimension
     * @param y         pixel to move to in y dimension
     */
    public void moveTo(double x, double y) {
        for (Ball ball : shape) {
            ball.move(x - position.x, y - position.y);
        }

        position.x = x;
        position.y = y;
    }

    /**
     * moves the body with constant displacement, x, and y
     * @param x         pixels to move in x direction
     * @param y         pixels to move in y direction
     */
    public void move(double x, double y) {
        position.x += x;
        position.y += y;

        for (Ball ball : shape) {
            ball.move(x, y);
        }
    }

    /**
     * moves the body with constant displacement, x, and y, collides on edge with game border
     * @param arena             the game arena to determine edge collisions
     * @param dx                pixels to move in x direction
     * @param dy                pixels to move in y direction
     * @param detectionRadius   how far from the edge to collide with the wall
     */
    public void moveAndCollide(GameArena arena, double dx, double dy, double detectionRadius) {
        // if x is out of bounds, set it to the edge
        if (position.x + dx + detectionRadius > arena.getArenaWidth())
            dx = arena.getArenaWidth() - position.x - detectionRadius;
        else if (position.x + dx - detectionRadius < 0)
            dx = position.x + detectionRadius;

        // if y is out of bounds, set it to the edge
        if (position.y + dy + detectionRadius > arena.getArenaHeight())
            dy = arena.getArenaHeight() - position.y - detectionRadius;
        else if (position.y + dy - detectionRadius < 0)
            dy = 0;

        position.x += dx;
        position.y += dy;

        for (Ball ball : shape) {
            ball.move(dx, dy);
        }
    }

    /**
     * gives the body a boost of speed, adding to its velocity.
     * Does not move the body at all
     * @param dx    adds dx velocity to the player
     * @param dy    adds dy velocity to the player
     */
    public void accelerate(double dx, double dy) {
        velocity.x += dx;
        velocity.y += dy;
    }

    /**
     * Uses the velocity of the body instance to automatically move the body in that direction
     */
    public void slide() {
        position.x += velocity.x;
        position.y += velocity.y;

        for (Ball ball : shape) {
            ball.move(velocity.x, velocity.y);
        }
    }

    /**
     * Uses the velocity of the body instance to automatically move the body in that direction,
     * and collide with any edges of the game arena
     * @param arena             the game arena to determine edge collisions
     * @param detectionRadius   how far from the edge to collide with the wall
     */
    public void slideAndCollide(GameArena arena, double detectionRadius) {
        moveAndCollide(arena, velocity.x, velocity.y, detectionRadius);
    }

    /**
     * Takes the first ball in the array and returns true if it is colliding with the other body's first ball in that array
     * @param body  the other body to check if it is colliding with
     * @return      true if the bodies are colliding
     */
    public boolean isCollidedWith(Body body) {
        return this.shape[0].collides(body.shape[0]);
    }

    /**
     * Takes all Balls in the body, and returns true if it is colliding with any of the other body's Balls
     * @param body  the other body to check if it is colliding with
     * @return      true if the bodies are truly colliding
     */
    public boolean isCollidedWithRigorous(Body body) {
        for (Ball ball : this.shape) {
            for (Ball otherBall : body.shape) {
                if (ball.collides(otherBall))
                    return true;
            }
        }
        return false;
    }

}
