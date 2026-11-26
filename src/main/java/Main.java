import zero.GameApp;
import zero.ui.Button;
import zero.ui.Column;
import zero.ui.Label;
import zero.ui.Row;
import zero.Key;
import zero.Color;
import zero.ui.TextField;
import java.util.Random;



public class Main extends GameApp {
    private Player player;
    private Coin[] coins;
    private int points;
    private Label scoreLabel;
    private Label message;
    private Random r;


    @Override public void settings() {
        padding(20);
        size(640, 400);
    }

    @Override public void setup() {
        r = new Random();
        scoreLabel = new Label("");
        message = new Label("");
        var restartButton = new Button("Restart");
        restartButton.onClick(() -> restart());
        controls(new Row(12, scoreLabel, message, restartButton));
        restart();
    }

    
    public void restart() {
        player = new Player(40, 180);
        coins = new Coin[] {new Coin(r.nextDouble(12, 628), r.nextDouble(42, 388), r.nextInt(1, 11)), new Coin(r.nextDouble(12, 628), r.nextDouble(42, 388), r.nextInt(1, 11)), new Coin(r.nextDouble(12, 628), r.nextDouble(42, 388), r.nextInt(1, 11))};
        message.setText("Collect as many coins as you can. Red coins worth 5 points.");
        points = 0;
        scoreLabel.setText("Score: 0");
        showGame();
    }


    @Override public void update(double seconds) {
        int horizontal = 0;
        int vertical = 0;
        if (keyDown(Key.LEFT)) horizontal--;
        if (keyDown(Key.RIGHT)) horizontal++;
        if (keyDown(Key.UP)) vertical--;
        if (keyDown(Key.DOWN)) vertical++;
        player.update(horizontal, vertical, seconds, width(), height());
        for (int i = 0; i < 3; i++) {
            Coin each = coins[i];
            if (!each.isCollected() && each.touches(player)) {
                each.collect();
                if (each.getType() == 10) points += 5;
                else points++;
                scoreLabel.setText("Score: " + points);
                coins[i] = new Coin(r.nextDouble(12, 628), r.nextDouble(42, 388), r.nextInt(1, 11));
            }
        }
    }

    @Override public void draw() {
        background(Color.rgb(240,248,255));
        fill(Color.rgb(47, 79, 79));
        textSize(20);
        text("Click the canvas, then hold the arrow keys.", 20, 30);
        for (Coin each : coins) {
            each.draw(this);
        }
        player.draw(this);
    }

    public static void main(String[] args) { launch(args); }
}
