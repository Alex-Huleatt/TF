/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding5;

/**
 * Integer based coordinate.
 * @author alexhuleatt
 */
public class IntPoint {

    public int x;
    public int y;

    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IntPoint other = (IntPoint) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    
    public int convertToInt() {
        int a = 0;
        a |= x;
        a <<= 16;
        a |= y;
        return a;
    }
    
    public static int manhattan(IntPoint p1, IntPoint p2) {
        return Math.abs(p2.y - p1.y) + Math.abs(p2.x - p1.x);
    }
    
    public int serialize() {
        return (x << 16) | y;
    }
    
    public static IntPoint deserialize(int s) {
        return new IntPoint (s >> 16, ((1<<16)- 1) & s);
    }
}
