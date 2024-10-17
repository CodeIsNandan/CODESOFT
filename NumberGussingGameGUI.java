
package com.CodSoftTasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private JTextField guessInputField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JButton guessButton;
    private JButton resetButton;
    private Random random;
    private int numberToGuess;
    private int attemptsLeft;

    public NumberGuessingGameGUI() {
      
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        random = new Random();

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        add(instructionLabel);

        guessInputField = new JTextField(10);
        add(guessInputField);

        guessButton = new JButton("Guess");
        add(guessButton);

        messageLabel = new JLabel("You have 10 attempts left.");
        add(messageLabel);

        attemptsLabel = new JLabel("");
        add(attemptsLabel);

        resetButton = new JButton("Reset Game");
        resetButton.setEnabled(false); 
        add(resetButton);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        resetGame();
    }

    private void handleGuess() {
        String input = guessInputField.getText();
        try {
            int userGuess = Integer.parseInt(input);
            attemptsLeft--;

            if (userGuess == numberToGuess) {
                messageLabel.setText("Correct! The number was " + numberToGuess);
                endGame(true);
            } else if (userGuess < numberToGuess) {
                messageLabel.setText("Too low! Attempts left: " + attemptsLeft);
            } else {
                messageLabel.setText("Too high! Attempts left: " + attemptsLeft);
            }

            if (attemptsLeft <= 0 && userGuess != numberToGuess) {
                messageLabel.setText("Game Over! The correct number was " + numberToGuess);
                endGame(false);
            }

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }


    private void resetGame() {
        numberToGuess = random.nextInt(100) + 1;
        System.out.println("The correct number (for testing): " + numberToGuess); 
        attemptsLeft = 10;
        messageLabel.setText("You have 10 attempts left.");
        guessInputField.setText("");
        guessButton.setEnabled(true);
        resetButton.setEnabled(false);
    }

  
    private void endGame(boolean won) {
        guessButton.setEnabled(false);
        resetButton.setEnabled(true);
        if (won) {
            attemptsLabel.setText("You won in " + (10 - attemptsLeft) + " attempts!");
        } else {
            attemptsLabel.setText("Better luck next time!");
        }
    }

    public static void main(String[] args) {
        
        NumberGuessingGameGUI frame = new NumberGuessingGameGUI();
        frame.setVisible(true);
    }
}

