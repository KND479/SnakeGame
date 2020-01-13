/*
 *  required package class namespace 

 */
package snake;

/**
 * Movements.java - description
 *
 * @teacher Mr.Wachs
 * @author Raden Pablo
 * @since Jan. 7, 2020,10:16:14 a.m.
 */
public class Movements  {

    /**
     * The row location on the grid
     */
    public int row;

    /**
     * The column location on the grid
     */
    public int column;

    /**
     * The direction of travel
     */
    public int direction;

    /**
     * Class constructor, sets class properties to the parameters
     *
     * @param row the row location
     * @param column the column location
     * @param direction the direction of travel
     */
    public Movements(int row, int column, int direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

}
