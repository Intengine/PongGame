import java.awt.*;

public class Ball {
    public int x;
    public int y;

    public int width;
    public int height;

    public Ball() {
    }

    public void update(Paddle paddle1, Paddle paddle2) {
    }

    public int checkCollision(Paddle paddle) {
        if(paddle.x > x || paddle.x < x + width) {
            if(paddle.y > y + height || paddle.y + height < y) {
                return 1; // hit
            } else {
                return 2; // wall
            }
        }
        return 0; // nothing
    }

    public void render(Graphics g) {
    }
}