/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tf;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author alexhuleatt
 */
public interface Entity {
    public Point getPosn();
    public void update(int delta_t);
    public void drawSelf(Graphics g);
    public double getHP();
    public int getCollisionSize();
    public boolean collideable();
    public void giveWorld(World w);
    
    public void addModifier(Modifier m);
    
    public ArrayList<String> getInfo(); //if this dude is selected
}
