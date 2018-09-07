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

        timer.start();
    }

    public void start() {
        gameStatus = 2;
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

        if(!bot) {
            if(up) {
                player2.move(true);
            }

            if(down) {
                player2.move(false);
            }
        } else {
            int speed = 15;

            if(player2.y < ball.y) {
                player2.y += speed;
            }

            if(player2.y > ball.y) {
                player2.y -= speed;
            }
        }

        ball.update(player1, player2);
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
            g.drawOval(width / 2 - 50, height / 2 - 50, 100, 100);

            g.setFont(new Font("Arial", 1, 70));
            g.drawString(String.valueOf(player1.score), width / 2 - 150, 150);
            g.drawString(String.valueOf(player2.score), width / 2 + 100, 150);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }

        if(gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 70));
            g.drawString("PAUSE", width / 2 - 120, height / 2 - 70);
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

        // tour the ifology
        if(id == KeyEvent.VK_W) {
            w = true;
        } else if(id == KeyEvent.VK_S) {
            s = true;
        } else if(id == KeyEvent.VK_UP) {
            up = true;
        } else if(id == KeyEvent.VK_DOWN) {
            down = true;
        } else if(id == KeyEvent.VK_ESCAPE && gameStatus == 2) {
            gameStatus = 0;
        } else if(id == KeyEvent.VK_SHIFT && gameStatus == 0) {
            bot = true;
            start();
        } else if(id == KeyEvent.VK_SPACE) {
            if(gameStatus == 0) {
                start();
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
        } else if(id == KeyEvent.VK_S) {
            s = false;
        } else if(id == KeyEvent.VK_UP) {
            up = false;
        } else if(id == KeyEvent.VK_DOWN) {
            down = false;
        }
    }
}