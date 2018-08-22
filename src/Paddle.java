import java.awt.*;

public class Paddle {
    public int paddleNumber;

    public int x;
    public int y;

    public int width = 50;
    public int height = 300;

    public int score;

    public Paddle(Pong pong, int paddleNumber) {
        this.paddleNumber = paddleNumber;

        if(paddleNumber == 1) {
            this.x = 0;
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

    public void move(boolean up) {
        int speed = 5;

        if(up) {
            if(y + height - speed > Pong.pong.height) {
                y--;
            } else {
                y = 0;
            }
        } else {
            if(y + speed < Pong.pong.height) {
                y++;
            }
        }
    }
}