/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tf;

import java.util.ArrayList;

/**
 *
 * @author alexhuleatt
 */
public interface Tower extends Entity {
    
    public double attackRange();
    public double getDamage();
    
    
    public void doStuff(ArrayList<Entity> ent);
    
}
