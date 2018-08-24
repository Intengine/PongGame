import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong implements ActionListener, KeyListener {
    public static Pong pong;
    public Renderer renderer;

    public Paddle player1;
    public Paddle player2;
    public Ball ball;

    public boolean bot = false;
    public boolean w, s, up, down;

    public int width = 700;
    public int height = 700;

    public int gameStatus = 0; // 0 - stopped, 1 - paused, 2 - playing

    public Pong() {
        Timer timer = new Timer(20, this);

        JFrame jframe = new JFrame("Pong");
        renderer = new Renderer();

        jframe.setSize(width + 15, height + 35);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.add(renderer);
        jframe.addKeyListener(this);

        start();

        timer.start();
    }

    public void start() {
        player1 = new Paddle(this, 1);
        player2 = new Paddle(this, 2);
        ball = new Ball(this);
    }

    public void update() {
        if(w) {
            player1.move(true);
        }

        if(s) {
            player1.move(false);
        }

        if(up) {
            player2.move(true);
        }

        if(down) {
            player2.move(false);
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(gameStatus == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 70));
            g.drawString("PONG", width / 2 - 100, 150);

            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Press SPACE to play", width / 2 - 150, height / 2 - 50);
            g.drawString("Press SHIFT to play with computer", width / 2 - 250, height / 2 - 10);
        }

        if(gameStatus == 2 || gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5f));
            g.drawLine(width / 2, 0, width / 2, height);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }

        if(gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 70));
            g.drawString("PAUSED", width / 2 - 15, height / 2 - 70);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameStatus == 2) {
            update();
        }

        renderer.repaint();
    }

    public static void main(String[] args) {
        pong = new Pong();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        if(id == KeyEvent.VK_W) {
            w = true;
        }

        if(id == KeyEvent.VK_S) {
            s = true;
        }

        if(id == KeyEvent.VK_UP) {
            up = true;
        }

        if(id == KeyEvent.VK_DOWN) {
            down = true;
        }

        if(id == KeyEvent.VK_SHIFT && gameStatus == 0) {
            bot = true;
        }

        if(id == KeyEvent.VK_SPACE) {
            if(gameStatus == 0) {
                gameStatus = 2;
                bot = false;
            } else if(gameStatus == 1) {
                gameStatus = 2;
            } else if(gameStatus == 2) {
                gameStatus = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();

        if(id == KeyEvent.VK_W) {
            w = false;
        }

        if(id == KeyEvent.VK_S) {
            s = false;
        }

        if(id == KeyEvent.VK_UP) {
            up = false;
        }

        if(id == KeyEvent.VK_DOWN) {
            down = false;
        }
    }
}