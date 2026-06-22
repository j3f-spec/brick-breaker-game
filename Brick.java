import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Brick - Represents a breakable brick in the game
 */
public class Brick {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean active = true;
    private Color color;
    
    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = generateRandomColor();
    }
    
    private Color generateRandomColor() {
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        return new Color(red, green, blue, 1.0);
    }
    
    public void draw(GraphicsContext gc) {
        if (active) {
            gc.setFill(color);
            gc.fillRect(x, y, width, height);
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(1);
            gc.strokeRect(x, y, width, height);
        }
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isActive() { return active; }
}
