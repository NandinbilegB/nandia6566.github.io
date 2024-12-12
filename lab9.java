import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Hangman extends JPanel {
    private String[] words = {"apple", "banana", "cherry", "date", "grape"};
    private String selectedWord;
    private StringBuilder currentWord;
    private int attemptsLeft = 5;
    private String guessedLetters = "";
    private boolean gameOver = false;

    private final StringBuilder wordBuilder = new StringBuilder();

    public Hangman() {
        Random rand = new Random();
        selectedWord = words[rand.nextInt(words.length)];
        currentWord = new StringBuilder("_".repeat(selectedWord.length()));
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.WHITE);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (gameOver) return;

                char guess = Character.toLowerCase(e.getKeyChar());
                if (Character.isLetter(guess) && !guessedLetters.contains(String.valueOf(guess))) {
                    guessedLetters += guess;
                    if (selectedWord.contains(String.valueOf(guess))) {
                        updateWordDisplay(guess);
                    } else {
                        attemptsLeft--;
                    }
                    if (attemptsLeft == 0) {
                        gameOver = true;
                    }
                    repaint();
                }
            }
        });
        setFocusable(true);
    }


    private void updateWordDisplay(char guess) {
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == guess) {
                currentWord.setCharAt(i, guess);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Word: " + currentWord, 20, 50);
        g.drawString("Guessed Letters: " + guessedLetters, 20, 80);
        g.drawString("Attempts Left: " + attemptsLeft, 20, 110);

      
        drawHangman(g);

        
        if (gameOver) {
            if (attemptsLeft == 0) {
                g.drawString("Game Over! The word was: " + selectedWord, 20, 150);
            } else {
                g.drawString("You Won!", 20, 150);
            }
        }
    }

    private void drawHangman(Graphics g) {
    
        int x = 200, y = 150;
        switch (attemptsLeft) {
            case 4: g.drawLine(x, y, x, y + 50); break; 
            case 3: g.drawLine(x, y + 50, x - 20, y + 80); break; 
            case 2: g.drawLine(x, y + 50, x + 20, y + 80); break; 
            case 1: g.drawLine(x, y + 20, x - 30, y + 40); break; 
            case 0: g.drawLine(x, y + 20, x + 30, y + 40); break; 
        }

        
        g.drawLine(x - 50, y, x + 50, y);  
        g.drawLine(x - 50, y, x - 50, y + 100);  
        g.drawLine(x + 50, y, x + 50, y + 30);  
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman Game");
        Hangman gamePanel = new Hangman();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
