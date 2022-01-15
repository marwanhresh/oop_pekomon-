package gameClient;

import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

/**
 * In this class we design game itself.
 */
public class GamePanel extends JPanel{
    private Arena _ar;
    private gameClient.util.Range2Range _w2f;

    public GamePanel(Arena arena) {
        super();
        this._ar = arena;
        updateFrame();
    }

    private void updateFrame() {
        Range rx = new Range(20, this.getWidth() - 20);
        Range ry = new Range(this.getHeight() - 10, 150);
        Range2D frame = new Range2D(rx, ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g, frame);
    }

    public void paintComponent(Graphics g) {
//        this.setBackground(new Color(143, 232, 0)); //change color of background
        int w = this.getWidth();
        int h = this.getHeight();
        g.clearRect(0, 0, w, h);
        updateFrame();
        Image im = getToolkit().getImage("resources/back.png");
        g.drawImage(im, 0, 0, w, h, null);
        drawGraph(g);
        drawPokemons(g, w, h);
        drawAgants(g);
        drawInfo(g, w, h);
    }

    private void drawInfo(Graphics g, int w, int h) {

        g.setColor(Color.black);
        Font font = new Font("Ariel", Font.BOLD, 15);
        //     Font font = new Font("Ariel", Font.BOLD, 15);
        g.setFont(font);

        //show level
        int level = Ex2.getLevelNumber();
        g.drawString("Level number: " + level, w - w / 5, h / 15);

        //Show time to End
        long timeToEnd = Ex2.getTime();
        g.drawString("Time to End: " + timeToEnd / 1000, w - w / 5, h / 15 + 20);

        //show grade
        double grade = Ex2.getGrade();
        g.drawString("Grade: " + (int) grade, w - w / 5, h / 15 + 40);

        //show moves
        int moves = Ex2.getMoves();
        g.drawString("Moves: " + moves, w - w / 5, h / 15 + 60);
    }

    private void drawGraph(Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        Iterator<node_data> iter = gg.getV().iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            g.setColor(Color.black);
            drawNode(n, 5, g);
            Iterator<edge_data> itr = gg.getE(n.getKey()).iterator();
            while (itr.hasNext()) {
                edge_data e = itr.next();
                g.setColor(Color.BLACK);
                drawEdge(e, g);
            }
        }
    }

    private void drawPokemons(Graphics g, int w, int h) {
        List<CL_Pokemon> fs = _ar.getPokemons();
        if (fs != null) {
            Iterator<CL_Pokemon> itr = fs.iterator();

            while (itr.hasNext()) {
                CL_Pokemon f = itr.next();
                Point3D c = f.getLocation();
                int r = 10;
                g.setColor(Color.green);
                if (f.getType() < 0) {
                    g.setColor(Color.orange);
                }
                if (c != null) {
                    geo_location fp = this._w2f.world2frame(c);
                    Image im1 = getToolkit().getImage("resources/pokeBall.png");
                      g.drawImage(im1, (int) fp.x() - r, (int) fp.y() - r, 15, 15, null);
                    //  g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
                    //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
                    g.setColor(Color.red);
                    g.drawString("" + f.getValue(), (int) fp.x() - r, (int) fp.y() - r);
                }
            }
        }
    }

    private void drawAgants(Graphics g) {
        List<CL_Agent> rs = _ar.getAgents();
        //Iterator<OOP_Point3D> itr = rs.iterator();
        g.setColor(Color.red);
        int i = 0;
        while (rs != null && i < rs.size()) {
            geo_location c = rs.get(i).getLocation();
            int r = 8;
            i++;
            if (c != null) {
                geo_location fp = this._w2f.world2frame(c);
                Image im1 = getToolkit().getImage("resources/pikachu.png");
                g.drawImage(im1, (int) fp.x() - 15, (int) fp.y() - 15, 20, 20, null);
                g.setColor(Color.ORANGE);
                g.drawString("id: " + rs.get(i - 1).getID() + " value: " + rs.get(i - 1).getValue(), (int) fp.x() - 15, (int) fp.y() - 15);
                // g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
            }
        }
    }

    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
        g.drawString("" + n.getKey(), (int) fp.x(), (int) fp.y() - 4 * r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.drawLine((int) s0.x(), (int) s0.y(), (int) d0.x(), (int) d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }

}
