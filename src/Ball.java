import java.awt.*;
import java.util.Random;

public class Ball {
    public int x;
    public int y;

    public int width = 25;
    public int height = 25;

    public int motionX;
    public int motionY;

    public Random random;

    public Ball(Pong pong) {
        this.random = new Random();
        this.x = pong.width / 2 - this.width / 2;
        this.y = pong.height / 2 - this.height / 2;
    }

    public void update(Paddle paddle1, Paddle paddle2) {
        if(checkCollision(paddle1) == 1) {
            this.motionX = 1;
            this.motionY = -2 + random.nextInt(4);
        } else if(checkCollision(paddle2) == 1) {
            this.motionX = -1;
            this.motionY = -2 + random.nextInt(4);
        }

        if(checkCollision(paddle1) == 2) {
            paddle2.score++;
        } else if(checkCollision(paddle2) == 2) {
            paddle1.score++;
        }
    }

    public int checkCollision(Paddle paddle) {
        if(paddle.x > x || paddle.x < x + width) {
            if(paddle.y > y + height || paddle.y + height < y) {
                return 1; // hit
            } else {
                return 2; // score
            }
        }
        return 0; // nothing
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}