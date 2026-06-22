# Brick Breaker Game

A classic Brick Breaker game built with Java and JavaFX, featuring paddle control, ball physics, collision detection, and score tracking.

## Features

- **Paddle Control**: Move the paddle using keyboard (arrow keys or A/D) or mouse
- **Ball Physics**: Realistic ball movement with collision detection
- **Brick Breaking**: Break bricks and earn points
- **Score Tracking**: Keep track of your score and game progress
- **Game Over**: Lose a life when the ball goes below the paddle
- **Multiple Difficulty Levels**: Adjust game speed and brick layouts
- **Modern UI**: Built with JavaFX for a polished graphical interface

## Requirements

- Java 11 or higher
- JavaFX SDK 11 or higher

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd brick-breaker-game
```

2. Download JavaFX SDK from [openjfx.io](https://openjfx.io) and extract it

## Running the Game

### Using command line with JavaFX module path:

```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls *.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls BrickBreakerGame
```

### Using IDE:

Configure your IDE (IntelliJ IDEA, Eclipse, NetBeans) to include JavaFX SDK in the project libraries.

## Game Controls

- **Arrow Keys** or **A/D**: Move paddle left/right
- **Mouse**: Move paddle to mouse position
- **SPACE**: Start/pause game
- **ESC** or **Q**: Quit game

## Game Mechanics

1. **Scoring**: Each brick destroyed = 10 points
2. **Lives**: Start with 3 lives
3. **Game Over**: Lose when all lives are gone
4. **Win**: Break all bricks to advance to next level
5. **Speed**: Ball speed increases with each level

## Project Structure

```
brick-breaker-game/
├── BrickBreakerGame.java    # Game entry point (Main application class)
├── GamePanel.java           # Main game logic and animation loop
├── Paddle.java              # Paddle class with collision detection
├── Ball.java                # Ball class with physics
├── Brick.java               # Brick class with properties
└── README.md                # This file
```

## Author

Created as a learning project for GUI development and game mechanics using JavaFX.