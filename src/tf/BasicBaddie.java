/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author alexhuleatt
 */
public abstract class BasicBaddie implements Baddie {

    ArrayList<Modifier> modifiers;
    Point posn;
    Point direction;
    double hp;
    public static double MOVE_SPEED = .002;
    public static int BASIC_BADDIE_SIZE = 4; //is "radius" or 1/2 of length side if rectangular
    public static Color BASIC_BADDIE_COLOR = Color.RED;
    public static double INITIAL_HP = 400;
    public static double MY_AOE = 100;
    World w;

    public BasicBaddie(Point posn) {
        modifiers = new ArrayList<>();
        this.posn = posn;
        hp = INITIAL_HP;
        this.direction = new Point(-posn.x, -posn.y); //head to origin.
    }

    @Override
    public void giveWorld(World w) {
        this.w = w;
    }

    @Override
    public boolean collideable() {
        return false; //enemies can't collide with eachother
    }

    @Override
    public Point getGoal() {
        return new Point(0, 0);
    }

    @Override
    public Point getPosn() {
        return posn;
    }

    @Override
    public void update(int delta_t) {
        moveOrPath(delta_t);
        //NOW DO OTHER THINGS
        ArrayList<Entity> stuff = w.stuffInRange(posn, MY_AOE);
        //BASIC BADDIE DOES NOTHING!
    }

    void moveOrPath(int delta_t) {
        Point des = desiredLocation(delta_t);
        if (w.wouldICollide(this, des)) {
            //THIS ONLY HAPPENS WHEN I ENCOUNTER AN OBSTACLE CREATED SINCE I SPAWNED
            //REPLOT PATH
            w.pathPlease(posn, getGoal());
            //I THINK THEY SHOULD SPEND A ROUND THINKING. I THINK IT WOULD LOOK NICE.
        } else {
            posn = des;
        }
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(BASIC_BADDIE_COLOR);
        g.fillOval((int) posn.x, (int) posn.y, BASIC_BADDIE_SIZE, BASIC_BADDIE_SIZE);
    }

    @Override
    public double getHP() {
        return hp;
    }

    @Override
    public int getCollisionSize() {
        return BASIC_BADDIE_SIZE;
    }

    @Override
    public void addModifier(Modifier m) {
        modifiers.add(m);
    }

    @Override
    public ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Position: " + posn.toString());

        Modifier temp = new Modifier(0);
        for (Modifier m : modifiers) {
            temp.add(m);
        }
        info.add(temp.toString());

        return info;
    }

    private Point desiredLocation(int delta_t) {
        Point goal = getGoal();
        Point dir = new Point(goal.x - posn.x, goal.y - posn.y);
        dir = World.normalize(dir);
        dir.x = dir.x * MOVE_SPEED * delta_t;
        dir.y = dir.y * MOVE_SPEED * delta_t;
        return new Point(posn.x + dir.x, posn.y + dir.y);
    }

}
