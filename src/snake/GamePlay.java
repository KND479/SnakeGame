package snake;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * GamePlay.java - The main Code
 *
 * @teacher Mr.Wachs
 * @author Raden Pablo
 * @since Jan. 9, 2020,10:36:42 a.m.
 */
public class GamePlay extends JFrame {

    // Global Variable
    private final int STOP = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    private final int UP = 3;
    private final int DOWN = 4;
    private final int HEAD = 0;
    private final int BLANK = -1;
    private final int FORM_WIDTH = 740;
    private final int FORM_HEIGHT = 740;
    private final int SQUARE_SIZE = 30;
    private final int TIMER_DELAY = 100;
    public int points = 0;
    private final Border SQUARE_BORDER = BorderFactory.createLineBorder(Color.black);
    private final Color SNAKE_HEAD_COLOR = Color.black;
    private final Color SNAKE_BODY_COLOR = Color.pink;
    private final Color TILE_BACKGROUND1 = new Color(0, 153, 51);
    private final Color TILE_BACKGROUND2 = new Color(51, 204, 51);
    private final String APPLE = "C:"
            + "\\Users\\r.pablo\\"
            + "Desktop\\Raden Pablo Final Project\\apple.png";
    private final String BACKGROUND = "C:"
            + "\\Users\\r.pablo\\"
            + "Desktop\\Raden Pablo Final Project\\"
            + "Background Game Play.jpg";
    private int countPowerUps = 0;
    private Movements[] snake;
    private int maxRows = 24;
    private int maxColumns = 23;
    private Movements[] powerUps = new Movements[maxRows * maxColumns];
    private int length;
    private int counter = 0;
    private JLabel[][] grid;
    private JLabel background;
    private Timer timer;

    /**
     * Default constructor, set class properties
     */
    public GamePlay() {
        setForm();
        setGrid();
        setKeyListener();
        setTimer();
        setSnake();
        newPowerUp();
        timer.start();
        setVisible(true);
    }

    /**
     * set up grids
     */
    private void setGrid() {
        grid = new JLabel[maxRows][maxColumns];
        int y = (FORM_HEIGHT - (maxRows * SQUARE_SIZE)) / 2;
        for (int row = 0; row < maxRows; row++) {
            int x = (FORM_WIDTH - (maxColumns * SQUARE_SIZE)) / 2;
            for (int column = 0; column < maxColumns; column++) {
                createSquare(x, y, row, column);
                x += SQUARE_SIZE;
            }
            y += SQUARE_SIZE;
        }
    }

    /**
     * Creates each individual square on the grid
     *
     * @param x the x coordinate of the square
     * @param y the y coordinate of the square
     * @param row this squares row
     * @param column this squares column
     */
    private void createSquare(int x, int y, int row, int column) {
        grid[row][column] = new JLabel();
        grid[row][column].setOpaque(true);

        counter++;
        if (counter % 2 == 0) {
            grid[row][column].setForeground(TILE_BACKGROUND2);
            grid[row][column].setBackground(TILE_BACKGROUND2);
            grid[row][column].setText("1");
        } else {
            grid[row][column].setForeground(TILE_BACKGROUND2);
            grid[row][column].setBackground(TILE_BACKGROUND2);
            grid[row][column].setText("2");
        }

        grid[row][column].setBorder(SQUARE_BORDER);
        this.getContentPane().add(grid[row][column]);
        grid[row][column].setBounds(x, y, SQUARE_SIZE, SQUARE_SIZE);
    }

    /**
     * Sets the form
     */
    private void setForm() {
        this.getContentPane().setLayout(null);
        this.setSize(FORM_WIDTH, FORM_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        background = new JLabel(new ImageIcon(BACKGROUND));
        this.getContentPane().add(background);
        background.setBounds(0, 0, FORM_WIDTH, FORM_HEIGHT);
    }

    /**
     * Sets the keyboard to listen to events
     */
    private void setKeyListener() {
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent key) {
            }

            public void keyPressed(KeyEvent key) {
                keyPress(key);
            }

            public void keyReleased(KeyEvent key) {
            }
        });
    }

    /**
     * When a key is pressed, move the snake head's direction
     *
     * @param event this keys event
     */
    private void keyPress(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            snake[HEAD].direction = UP;
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            snake[HEAD].direction = DOWN;
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            snake[HEAD].direction = LEFT;
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake[HEAD].direction = RIGHT;
        }
    }

    /**
     * Sets up the snake at the start of the game
     */
    private void setSnake() {
        length = 1;
        snake = new Movements[maxRows * maxColumns];
        for (int i = 0; i < snake.length; i++) {
            snake[i] = new Movements(BLANK, BLANK, STOP);
        }
        int startRow = maxRows / 2;
        int startColumn = maxColumns / 2;
        snake[HEAD] = new Movements(startRow, startColumn, STOP);
    }

    /**
     * Moves all the snake positions for this time cycle
     */
    private void moveSnake() {
        for (int i = length; i > 0; i--) {
            snake[i].row = snake[i - 1].row;
            snake[i].column = snake[i - 1].column;
            snake[i].direction = snake[i - 1].direction;
        }
        if (snake[HEAD].direction == UP) {
            snake[HEAD].row--;
        } else if (snake[HEAD].direction == DOWN) {
            snake[HEAD].row++;
        } else if (snake[HEAD].direction == LEFT) {
            snake[HEAD].column--;
        } else if (snake[HEAD].direction == RIGHT) {
            snake[HEAD].column++;
        }
    }

    /**
     * Redraw the game grid for this time cycle
     */
    private void redraw() {
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxColumns; column++) {
                grid[row][column].setBackground(TILE_BACKGROUND1);

                if (grid[row][column].getText().equals("1")) {
                    grid[row][column].setForeground(TILE_BACKGROUND1);
                    grid[row][column].setBackground(TILE_BACKGROUND1);
                } else {
                    grid[row][column].setForeground(TILE_BACKGROUND2);
                    grid[row][column].setBackground(TILE_BACKGROUND2);
                }

            }
        }

        grid[snake[HEAD].row][snake[HEAD].column].setBackground(
                SNAKE_HEAD_COLOR);
        grid[snake[HEAD].row][snake[HEAD].column].setForeground(
                SNAKE_HEAD_COLOR);

        for (int i = 1; i < length; i++) {
            grid[snake[i].row][snake[i].column].setBackground(SNAKE_BODY_COLOR);
            grid[snake[i].row][snake[i].column].setForeground(SNAKE_BODY_COLOR);
        }

        for (int i = 0; i < powerUps.length; i++) {
            if (powerUps[i] != null) {
                int row = powerUps[i].row;
                int column = powerUps[i].column;
                grid[row][column].setIcon(new ImageIcon(APPLE));
            }
        }

    }

    /**
     * Sets the game timer
     */
    private void setTimer() {
        timer = new Timer(TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                action();
            }
        });
    }

    /**
     * The timers action for each cycle
     */
    private void action() {
        moveSnake();
        if (hasEatenPowerUp() == false) {
            checkForCollisionWithSelf();
        }
        if (hasHitBoundry() == false) {
            redraw();
        }

    }

    /**
     * Checks if the head of the snake has gone outside the boundry
     *
     * @return has hit the boundry (true) or not (false)
     */
    private boolean hasHitBoundry() {
        if (snake[HEAD].row < 0
                || snake[HEAD].row >= maxRows
                || snake[HEAD].column < 0
                || snake[HEAD].column >= maxColumns) {
            timer.stop();
            new GameOver(this);
            this.dispose();

        }
        return false;
    }

    /**
     * Generates a new power up in a random location
     */
    private void newPowerUp() {
        int row = 0;
        int column = 0;
        do {
            row = Random.random(0, maxRows - 1);
            column = Random.random(0, maxColumns - 1);
        } while (isInSnake(row, column, false));
        powerUps[countPowerUps] = new Movements(row, column, STOP);
        row = powerUps[countPowerUps].row;
        column = powerUps[countPowerUps].column;
        grid[row][column].setIcon(new ImageIcon(APPLE));
        countPowerUps++;

        if (countPowerUps % 6 == 0) {
            points *= 2;
            newPowerUp();
            grid[row][column].setIcon(new ImageIcon(APPLE));
        }

    }

    /**
     * Determines is this location is touching any part of the snake
     *
     * @param row the row to check
     * @param column the column to check
     * @param isHead do I check the head location or not
     * @return is hitting a part of the snake (true) or not (false)
     */
    private boolean isInSnake(int row, int column, boolean isHead) {
        int i = 0;
        if (isHead) {
            i = 1;
        }
        while (i < length) {
            if (snake[i].row == row
                    && snake[i].column == column) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Checks if the head of the snake has eaten a power up
     *
     * @return has eaten a power up (true) or not (false)
     */
    private boolean hasEatenPowerUp() {
        for (int i = 0; i < powerUps.length; i++) {
            if (powerUps[i] != null) {
                if (snake[HEAD].row == powerUps[i].row
                        && snake[HEAD].column == powerUps[i].column) {
                    growSnake();
                    grid[powerUps[i].row][powerUps[i].column].setIcon(null);
                    powerUps[i] = null;
                    newPowerUp();
                    points++;
                    System.out.println("Points " + points);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the snake head s hitting the snake body
     */
    private void checkForCollisionWithSelf() {
        if (isInSnake(snake[0].row, snake[0].column, true)) {
            this.dispose();
            System.out.println("Points: " + points);
            timer.stop();
            new GameOver(this);
        }
    }

    /**
     * Adds a new segment to the snake body
     */
    private void growSnake() {
        length++;
        snake[length - 1].row = snake[length - 2].row;
        snake[length - 1].column = snake[length - 2].column;
        if (snake[length - 2].direction == UP) {
            snake[length - 1].row++;
        } else if (snake[length - 2].direction == DOWN) {
            snake[length - 1].row--;
        } else if (snake[length - 2].direction == LEFT) {
            snake[length - 1].column++;
        } else if (snake[length - 2].direction == RIGHT) {
            snake[length - 1].column--;
        }
    }

}
