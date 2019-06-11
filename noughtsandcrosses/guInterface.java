
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class guInterface extends JFrame 
{
	JButton[] gridButtons = new JButton[9];
	JButton restartButton = new JButton("Start a new game");
	JFrame window = new JFrame("Noughts and Crosses");
	Grid gameBoard = new Grid('X', 'O');
	computerPlayer opponent = new computerPlayer();

	public guInterface() 
	{
		window.setSize(550, 565);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
	}
	public void start() 
	{
		JPanel mainWindow = new JPanel(new BorderLayout());
		JPanel mainBoard = new JPanel(new GridLayout(3,3));		
		window.add(mainWindow);
		mainBoard.setPreferredSize(new Dimension(400,500));
		mainWindow.add(mainBoard, BorderLayout.SOUTH);
		mainWindow.add(restartButton);
		restartButton.addActionListener(new myActionListener());

		for(int n=0; n<9; n++) 
		{
			gridButtons[n] = new JButton();
			gridButtons[n].setText("");
			gridButtons[n].setVisible(true);
			mainBoard.add(gridButtons[n]); 
			gridButtons[n].addActionListener(new myActionListener());
			gridButtons[n].setFont(new Font("Arial", Font.BOLD, 150));
		}
	}
	private class myActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent action) 
		{
			int computerMove;
			for (int i=0; i<9; i++) {
				if (action.getSource() == gridButtons[i] && 
						gameBoard.locAvailable(i)) 
				{
					gameBoard.newCell(gameBoard.giveHuman(), i);
					gridButtons[i].setText(Character.toString(gameBoard.giveHuman()));
					gridButtons[i].setForeground(Color.BLUE);
					if (gameBoard.hasWon(gameBoard.giveHuman())) 
					{
						gameOver();
					}
					computerMove = opponent.getMove(gameBoard);
					if (computerMove != -1) 
					{
						gameBoard.newCell(gameBoard.giveComputer(), computerMove);
						gridButtons[computerMove].setText(Character.toString(gameBoard.giveComputer()));
						gridButtons[computerMove].setForeground(Color.RED);

						if (gameBoard.hasWon(gameBoard.giveComputer())) 
						{
							gameOver();
						}
					} else {
						gameOver();
					}
				}
			}
			if(action.getSource() == restartButton) 
			{
				for (int n=0; n<9; n++) 
				{ 
					gridButtons[n].setText(""); 
					gridButtons[n].setEnabled(true);
					
				}
				gameBoard.reset();
			}
		}
	}
	public void gameOver() 
	{
		for (int n=0; n<9; n++) 
		{
			gridButtons[n].setEnabled(false);
		}
		if (gameBoard.hasWon(gameBoard.giveHuman())) 
		{
			JOptionPane.showMessageDialog(window, "You win!");						
			
		} else if (gameBoard.hasWon(gameBoard.giveComputer())) 
		{
			
			JOptionPane.showMessageDialog(window, "You Lose.");						

		} else {
			JOptionPane.showMessageDialog(window, "Draw.");						
			
		}
	}
}

