package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Coordinate;
import model.Player;
import model.World;

public class WorldUtils {

    private static List<String> names = new ArrayList<String>();
    private static List<String> usedNames = new ArrayList<String>();

    public static void setNames() {
        String player1 = "Fred";
        String player2 = "Greenlee";
        String player3 = "Bluebell";
        String player4 = "Greydon";
        String player5 = "Pinkney";
        String player6 = "Willem";
        names.add(player1);
        names.add(player2);
        names.add(player3);
        names.add(player4);
        names.add(player5);
        names.add(player6);
    }

    public static String getNames(int index) {
        return names.get(index);
    }

    public static List<Player> getRoundPlayers() {
        List<Player> l = new ArrayList<Player>();
        for (int i = 0; i < World.getInstance().getPlayers().size(); i++) {
            if (World.getInstance().getPlayers().get(i).isInRound()) {
                l.add(World.getInstance().getPlayers().get(i));
            }
        }
        return l;
    }

    public static Coordinate generateHead() {
        double x = Math.random() * World.getInstance().getWorldSize().width;
        double y = Math.random() * World.getInstance().getWorldSize().height;
        while (containHead(x, y) || !spaceFromWall(x, y)) {
            x = Math.random() * World.getInstance().getWorldSize().width;
            y = Math.random() * World.getInstance().getWorldSize().height;
        }
        return new Coordinate(x, y, true);
    }

    public static boolean spaceFromWall(double x, double y) {
        double minX = Math.min(World.getInstance().getWorldSize().width - x, x);
        double minY = Math.min(World.getInstance().getWorldSize().height - y, y);
        return minX >= 50 && minY >= 50;
    }

    public static boolean containHead(double x, double y) {
        for (int i = 0; i < World.getInstance().getPlayers().size(); i++) {
            Player p = World.getInstance().getPlayers().get(i);
            double dX = p.getHead().x - x;
            double dY = p.getHead().y - y;
            if (Math.hypot(dX, dY) < 50) {
                return true;
            }
        }
        return false;
    }

    public static String generateName() {
        String provisionalName = null;
        if(names.isEmpty()) {
            setNames();
        }
        if (World.getInstance().getPlayers().size() < World.getInstance().MAX_PLAYERS) {
            provisionalName = getNames(World.getInstance().getPlayers().size());
        }
            usedNames.add(getNames(World.getInstance().getPlayers().size()));
            names.remove(getNames(World.getInstance().getPlayers().size())); 
 
        return provisionalName;
    }

    public static boolean containName(String name) {
        for (int i = 0; i < World.getInstance().getPlayers().size(); i++) {
            if (World.getInstance().getPlayers().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static Color setColorByName(String name) {
        Color c = null;
        switch (name) {
        case "Fred": 
            c = new Color(255,0,0);
            break;
        case "Greenlee":
            c = new Color(0,255,0);
            break;
        case "Bluebell":
            c = new Color(0,0,255);
            break;
        case "Greydon":
            c = new Color(128,128,128);
            break;
        case "Pinkney":
            c = new Color(255,20,147);
            break;
        case "Willem":
            c = new Color(255,69,0);
            break;
        default:
            c = new Color(255,255,255);
            break;
        }
        return c;
    }

    public static String generateColor(String name) {
        Color c = setColorByName(name);
//        Color c = new Color((int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55);
//        while (containsSimilarColor(c))
//            c = new Color((int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55);
        String hex = Integer.toHexString(c.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            hex = "0" + hex;
        }
        hex = "#" + hex;
        return hex;
    }

//    public static boolean containsSimilarColor(Color c) {
//        for (int i = 0; i < World.getInstance().getPlayers().size(); i++) {
//            if (isSimilarColor(hex2Rgb(World.getInstance().getPlayers().get(i).getColor()), c)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public static boolean isSimilarColor(Color c1, Color c2) {
//        int dR = Math.abs(c1.getRed() - c2.getRed());
//        int dG = Math.abs(c1.getGreen() - c2.getGreen());
//        int dB = Math.abs(c1.getBlue() - c2.getBlue());
//        return (dR + dG + dB) < 100;
//    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

}
