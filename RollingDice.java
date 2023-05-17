import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RollingDice extends JFrame {
    public RollingDice() {
        super("Rolling Double Dice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JLabel diceOneImg = ImgService.loadImage("resources/dice1.png");
        diceOneImg.setBounds(100, 200, 200, 200);
        jPanel.add(diceOneImg);

        JLabel diceTwoImg = ImgService.loadImage("resources/dice1.png");
        diceTwoImg.setBounds(390, 200, 200, 200);
        jPanel.add(diceTwoImg);

        Random random = new Random();
        JButton rollButton = new JButton("Roll");
        rollButton.setBounds(250, 550, 200, 50);
        // rollButton.setBackground(Color.RED);
        rollButton.setForeground(Color.RED);

        rollButton.setFont(new Font("Serif", Font.PLAIN, 36));
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(false);

                long startTime = System.currentTimeMillis();
                Thread rollThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long endTime = System.currentTimeMillis();
                        try {
                            while ((endTime - startTime) / 1000F < 3) {

                                int diceOne = random.nextInt(1, 7);
                                int diceTwo = random.nextInt(1, 7);

                                ImgService.updateImage(diceOneImg, "resources/dice" + diceOne + ".png");
                                ImgService.updateImage(diceTwoImg, "resources/dice" + diceTwo + ".png");

                                repaint();
                                revalidate();

                                Thread.sleep(60);

                                endTime = System.currentTimeMillis();

                            }
                            rollButton.setEnabled(true);
                        } catch (InterruptedException e) {
                            System.out.println("Threading Error: " + e);
                        }
                    }
                });
                rollThread.start();
            }
        });
        jPanel.add(rollButton);

        this.getContentPane().add(jPanel);
    }
}
