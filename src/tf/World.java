/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf;

import DisplayStuff.GameFrame;
import DisplayStuff.GamePanel;
import java.awt.Graphics;
import java.util.ArrayList;
import pathfinding5.AStar;
import pathfinding5.IntPoint;

/**
 *
 * @author alexhuleatt
 */
public final class World extends Thread {

    public static int ms_to_wait = 17;
    private ArrayList<Entity> ent;
    private GameFrame gf;
    private static AStar panther;
    public static int WIDTH = 300;
    public static int HEIGHT = 200;

    public World() {
        panther = new AStar(new boolean[WIDTH][HEIGHT]);
        this.ent = new ArrayList<>();
        initComponents();
    }

    public void initComponents() {
        gf = new GameFrame();
        gf.setWorld(this);
        gf.getPanel().setWorld(this);
    }

    @Override
    public void run() {
        long old_time = System.currentTimeMillis();
        long next_time = old_time + ms_to_wait;
        while (true) {
            long old_old_time = old_time;
            while (old_time < next_time) {
                old_time = System.currentTimeMillis();
            }
            next_time = old_time + ms_to_wait;
            update_world(old_time - old_old_time);
            gf.getPanel().repaint();
        }
    }

    private void update_world(long time_diff) {
        ArrayList<Entity> ISURVIVED = new ArrayList<>();
        for (Entity e : ent) {
            if (e.getHP() != 0) {
                e.update((int) time_diff); //update position
                ISURVIVED.add(e);
            }
        }
        ent = ISURVIVED;
    }

    public static double euclidean(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public ArrayList<Entity> stuffInRange(Point p, double distance) {
        ArrayList<Entity> stuff = new ArrayList<>();
        for (Entity e : ent) {
            if (euclidean(e.getPosn(), p) <= distance + e.getCollisionSize()) {
                stuff.add(e);
            }
        }
        return stuff;
    }
    
    public boolean wouldICollide(Entity e, Point wannaBeAt) {
        ArrayList<Entity> stuff = stuffInRange(wannaBeAt, e.getCollisionSize());
        for (Entity er : stuff) {
            if (er.collideable()) return true;
        }
        return false;
    }

    public void renderWorld(Graphics g) {
        for (Entity e : ent) {
            e.drawSelf(g);
        }
    }

    public static Point normalize(Point p) {
        double mag = magnitude(p);
        return new Point(p.x / mag, p.y / mag);
    }

    public static double magnitude(Point p) {
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }
    
    public void addThing(Entity e) {
        ent.add(e);
        if (e.collideable()) {
            //need to add to AStar map.
        }
    }
    
    public IntPoint[] pathPlease(Point start, Point goal) {
        IntPoint s = new IntPoint((int) start.x, (int) start.y);
        IntPoint g = new IntPoint((int) goal.x, (int) goal.y);
        return panther.pathfind(s, s);
    }
}
