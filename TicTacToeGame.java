import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] gridButtons;
    private JLabel messageLabel;
    private char currentPlayer;
    private boolean gameEnded;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        gridButtons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
                button.addActionListener(this);
                gridButtons[row][col] = button;
                gridPanel.add(button);
            }
        }

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        messageLabel = new JLabel("X's turn");
        bottomPanel.add(messageLabel);
        bottomPanel.add(newGameButton);

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        currentPlayer = 'X';
        gameEnded = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) {
            resetGame();
            return;
        }

        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(Character.toString(currentPlayer));
            if (checkWinner()) {
                messageLabel.setText(currentPlayer + " wins!");
                gameEnded = true;
            } else if (isGridFull()) {
                messageLabel.setText("It's a draw!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                messageLabel.setText(currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (gridButtons[i][0].getText().equals(currentPlayer + "") &&
                    gridButtons[i][1].getText().equals(currentPlayer + "") &&
                    gridButtons[i][2].getText().equals(currentPlayer + "") ||
                    gridButtons[0][i].getText().equals(currentPlayer + "") &&
                            gridButtons[1][i].getText().equals(currentPlayer + "") &&
                            gridButtons[2][i].getText().equals(currentPlayer + "")) {
                return true;
            }
        }
        if (gridButtons[0][0].getText().equals(currentPlayer + "") &&
                gridButtons[1][1].getText().equals(currentPlayer + "") &&
                gridButtons[2][2].getText().equals(currentPlayer + "") ||
                gridButtons[0][2].getText().equals(currentPlayer + "") &&
                        gridButtons[1][1].getText().equals(currentPlayer + "") &&
                        gridButtons[2][0].getText().equals(currentPlayer + "")) {
            return true;
        }
        return false;
    }

    private boolean isGridFull() {
        // Check if the grid is full
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gridButtons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        // Reset the game
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridButtons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
        messageLabel.setText("X's turn");
        gameEnded = false;
    }

    public static void main(String[] args) {
        // Tic Tac Toe is fun!
        TicTacToe game = new TicTacToe();
        game.setVisible(true);
    }
}