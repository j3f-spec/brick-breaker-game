import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Ball - Represents the game ball with physics
 */
public class Ball {
    
    private double x;
    private double y;
    private double radius;
    private double velocityX = 0;
    private double velocityY = 0;
    
    public Ball(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    public void setVelocity(double vx, double vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }
    
    public void update() {
        x += velocityX;
        y += velocityY;
    }
    
    public void reverseX() {
        velocityX = -velocityX;
    }
    
    public void reverseY() {
        velocityY = -velocityY;
    }
    
    public boolean collidesWith(Paddle paddle) {
        return x + radius >= paddle.getX() &&
               x - radius <= paddle.getX() + paddle.getWidth() &&
               y + radius >= paddle.getY() &&
               y - radius <= paddle.getY() + paddle.getHeight();
    }
    
    public boolean collidesWith(Brick brick) {
        return x + radius >= brick.getX() &&
               x - radius <= brick.getX() + brick.getWidth() &&
               y + radius >= brick.getY() &&
               y - radius <= brick.getY() + brick.getHeight();
    }
    
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        gc.setStroke(Color.GOLD);
        gc.setLineWidth(1);
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }
    
    // Getters and Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    
    public double getRadius() { return radius; }
    
    public double getVelocityX() { return velocityX; }
    public double getVelocityY() { return velocityY; }
}