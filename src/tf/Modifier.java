/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tf;

/**
 *
 * @author alexhuleatt
 */
public class Modifier {
    public double speed_mul;
    public double speed_add;
    
    public double attack_speed_mul;
    public double attack_speed_add;
    
    //Add more or whatever
    
    
    
    private double lifetime;
    
    public Modifier(double lifetime) {
        this.lifetime = lifetime;
    }
    
    public void update_life(int time) {
        lifetime -= time;
    }
    public boolean amAlive() {
        return lifetime > 0;
    }
    
    public void add(Modifier m) {
        speed_mul += m.speed_mul;
        speed_add += m.speed_add;
        attack_speed_mul += m.attack_speed_mul;
        attack_speed_add += m.attack_speed_add;
    }
}
