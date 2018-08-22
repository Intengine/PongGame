import java.awt.*;

public class Paddle {
    public int paddleNumber;

    public int x;
    public int y;

    public int width = 100;
    public int height = 500;

    public int score;

    public Paddle(Pong pong, int paddleNumber) {
        this.paddleNumber = paddleNumber;

        if(paddleNumber == 1) {
            this.x = width;
        }

        if(paddleNumber == 2) {
            this.x = pong.width - width;
        }

        this.y = pong.height / 2 - this.height / 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}