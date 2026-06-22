import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Paddle - Represents the player-controlled paddle
 */
public class Paddle {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed = 7;
    
    private boolean movingLeft = false;
    private boolean movingRight = false;
    
    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void moveLeft() {
        movingLeft = true;
    }
    
    public void moveRight() {
        movingRight = true;
    }
    
    public void stopMovingLeft() {
        movingLeft = false;
    }
    
    public void stopMovingRight() {
        movingRight = false;
    }
    
    public void update(int windowWidth) {
        if (movingLeft && x > 0) {
            x -= speed;
        }
        if (movingRight && x + width < windowWidth) {
            x += speed;
        }
    }
    
    public void setX(int x) {
        this.x = Math.max(0, Math.min(x, 800 - width)); // Clamp to screen bounds
    }
    
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, width, height);
        gc.setStroke(Color.LIGHTYELLOW);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, width, height);
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}