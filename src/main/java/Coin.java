import zero.Color;
import zero.GameApp;

public class Coin {
    private final double x;
    private final double y;
    private final double size = 24;
    private boolean collected;
    private int type;

    public Coin(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean isCollected() { 
        return collected; 
    }
    public boolean touches(Player player) { 
        return player.touches(x, y, size); 
    }
    public void collect() { 
        collected = true; 
    }

    public void draw(GameApp app) {
        if (collected) return;
        if (type == 10) app.fill(Color.rgb(255, 0, 0));
        else app.fill(Color.GOLD);
        app.circle(x, y, size);
    }

    public int getType() {
        return type;
    }
}

