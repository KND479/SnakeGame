/*
 *  required package class namespace 

 */
package snake;

/**
 * Random.java - Random number
 *
 * @teacher Mr.Wachs
 * @author Raden Pablo
 * @since Jan. 13, 2020,10:37:45 a.m.
 */
public class Random {

    /**
     * Generates a Random integer in the range
     *
     * @param low the lowest Random integer to generate
     * @param high the highest Random integer to generate
     * @return a Random integer inside the range
     */
    public static int random(int low, int high) {
        double L = (double) low;
        double H = (double) high;
        double seed = Math.random();
        double random = (H - L + 1d) * seed + L;
        return (int) random;
    }
}
