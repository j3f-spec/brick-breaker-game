import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * BrickBreakerGame - Main entry point for the Brick Breaker game
 * Built with JavaFX
 */
public class BrickBreakerGame extends Application {
    
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create root layout
            BorderPane root = new BorderPane();
            
            // Create and add game panel
            GamePanel gamePanel = new GamePanel(WINDOW_WIDTH, WINDOW_HEIGHT);
            root.setCenter(gamePanel);
            
            // Create scene
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            
            // Setup stage
            primaryStage.setTitle("Brick Breaker Game");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            // Start game loop
            gamePanel.startGame();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}