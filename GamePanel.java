import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 * GamePanel - Main game logic and rendering
 * Handles game loop, collision detection, and drawing
 */
public class GamePanel extends Pane {
    
    private Canvas canvas;
    private GraphicsContext gc;
    
    private GameState gameState;
    private Paddle paddle;
    private Ball ball;
    private Brick[][] bricks;
    
    private int score;
    private int lives;
    private int level;
    
    private boolean[] keysPressed = new boolean[256];
    private double mouseX = 0;
    private AnimationTimer gameLoop;
    
    // Game constants
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BRICK_ROWS = 4;
    private static final int BRICK_COLS = 8;
    
    public GamePanel(int width, int height) {
        this.setStyle("-fx-background-color: black;");
        
        // Create canvas
        canvas = new Canvas(width, height);
        this.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        
        // Initialize game state
        gameState = GameState.MENU;
        score = 0;
        lives = 3;
        level = 1;
        
        // Initialize game objects
        initializeGame();
        
        // Setup input handlers
        setupInputHandlers();
    }
    
    private void initializeGame() {
        paddle = new Paddle(WINDOW_WIDTH / 2 - 50, WINDOW_HEIGHT - 40, 100, 15);
        ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 80, 8);
        initializeBricks();
    }
    
    private void initializeBricks() {
        bricks = new Brick[BRICK_ROWS][BRICK_COLS];
        int brickWidth = WINDOW_WIDTH / BRICK_COLS;
        int brickHeight = 20;
        int startY = 30;
        
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLS; j++) {
                int x = j * brickWidth;
                int y = startY + (i * (brickHeight + 5));
                bricks[i][j] = new Brick(x, y, brickWidth - 2, brickHeight);
            }
        }
    }
    
    private void setupInputHandlers() {
        this.setFocusTraversable(true);
        
        this.setOnKeyPressed((KeyEvent event) -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.LEFT || code == KeyCode.A) {
                paddle.moveLeft();
            } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
                paddle.moveRight();
            } else if (code == KeyCode.SPACE) {
                if (gameState == GameState.MENU) {
                    gameState = GameState.PLAYING;
                    ball.setVelocity(3, -5);
                } else if (gameState == GameState.PLAYING) {
                    gameState = GameState.PAUSED;
                }
            } else if (code == KeyCode.ESCAPE || code == KeyCode.Q) {
                System.exit(0);
            }
        });
        
        this.setOnMouseMoved((MouseEvent event) -> {
            mouseX = event.getX();
            paddle.setX((int)(mouseX - paddle.getWidth() / 2));
        });
    }
    
    public void startGame() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }
    
    private void update() {
        if (gameState != GameState.PLAYING) {
            return;
        }
        
        // Update paddle position
        paddle.update(WINDOW_WIDTH);
        
        // Update ball position
        ball.update();
        
        // Ball collision with walls
        if (ball.getX() <= 0 || ball.getX() + ball.getRadius() * 2 >= WINDOW_WIDTH) {
            ball.reverseX();
        }
        if (ball.getY() <= 0) {
            ball.reverseY();
        }
        
        // Ball collision with paddle
        if (ball.collidesWith(paddle)) {
            ball.reverseY();
            ball.setY(paddle.getY() - ball.getRadius() * 2);
        }
        
        // Ball collision with bricks
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLS; j++) {
                if (bricks[i][j] != null && bricks[i][j].isActive()) {
                    if (ball.collidesWith(bricks[i][j])) {
                        ball.reverseY();
                        bricks[i][j].setActive(false);
                        score += 10;
                        checkLevelComplete();
                    }
                }
            }
        }
        
        // Ball lost
        if (ball.getY() > WINDOW_HEIGHT) {
            lives--;
            if (lives <= 0) {
                gameState = GameState.GAME_OVER;
            } else {
                resetBall();
            }
        }
    }
    
    private void resetBall() {
        ball.setX(WINDOW_WIDTH / 2);
        ball.setY(WINDOW_HEIGHT - 80);
        ball.setVelocity(0, 0);
        gameState = GameState.MENU;
    }
    
    private void checkLevelComplete() {
        boolean allBroken = true;
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLS; j++) {
                if (bricks[i][j].isActive()) {
                    allBroken = false;
                    break;
                }
            }
            if (!allBroken) break;
        }
        
        if (allBroken) {
            level++;
            score += 100;
            initializeBricks();
            resetBall();
        }
    }
    
    private void render() {
        // Clear canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // Draw bricks
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLS; j++) {
                if (bricks[i][j].isActive()) {
                    bricks[i][j].draw(gc);
                }
            }
        }
        
        // Draw paddle
        paddle.draw(gc);
        
        // Draw ball
        ball.draw(gc);
        
        // Draw UI
        drawUI();
    }
    
    private void drawUI() {
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font(16));
        gc.fillText("Score: " + score, 20, 20);
        gc.fillText("Lives: " + lives, 20, 40);
        gc.fillText("Level: " + level, WINDOW_WIDTH - 150, 20);
        
        if (gameState == GameState.MENU) {
            gc.setFont(javafx.scene.text.Font.font(24));
            gc.fillText("Press SPACE to Start", WINDOW_WIDTH / 2 - 120, WINDOW_HEIGHT / 2);
        } else if (gameState == GameState.PAUSED) {
            gc.setFont(javafx.scene.text.Font.font(24));
            gc.fillText("PAUSED - Press SPACE to Resume", WINDOW_WIDTH / 2 - 180, WINDOW_HEIGHT / 2);
        } else if (gameState == GameState.GAME_OVER) {
            gc.setFont(javafx.scene.text.Font.font(24));
            gc.fillText("GAME OVER! Final Score: " + score, WINDOW_WIDTH / 2 - 150, WINDOW_HEIGHT / 2);
        }
    }
    
    // Enums
    private enum GameState {
        MENU, PLAYING, PAUSED, GAME_OVER
    }
}