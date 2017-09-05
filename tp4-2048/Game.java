package tp4;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Game extends JFrame{

	Board board;

	public static void main(String[] args) {
		new Game();

	}

	public Game() {
		board = new Board(5);
		setTitle("TP4 - 2048");
		setSize(600, 600);
		getContentPane().setLayout(null);
		setVisible(true);		
		MenuInicial();
	}

	public void MenuInicial() {

		JButton buttonGame = new JButton("Game");
		buttonGame.setSize(80, 20);
		buttonGame.setLocation(40, 500);
		add(buttonGame);

		JButton buttonAbout = new JButton("ABOUT");
		buttonAbout.setSize(80, 20);
		buttonAbout.setLocation(140, 500);
		add(buttonAbout);

		JButton buttonHelp = new JButton("HELP");
		buttonHelp.setSize(80, 20);
		buttonHelp.setLocation(240, 500);
		add(buttonHelp);

		// ACTION LISTENER BUTTON GAME
		buttonGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(buttonGame);
				remove(buttonAbout);
				remove(buttonHelp);
				FrameGame();
				repaint();

			}
		});

		// ACTION LISTENER BUTTON ABOUT
		buttonAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(buttonGame);
				remove(buttonAbout);
				remove(buttonHelp);
				FrameAbout();
				repaint();
			}
		});

		// ACTION LISTENER BUTTON HELP
		buttonHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(buttonGame);
				remove(buttonAbout);
				remove(buttonHelp);
				FrameHelp();
				repaint();
			}
		});
	}

	public void FrameGame() {

		// ADD BUTTONS
		JButton buttonInit = new JButton("INIT");
		buttonInit.setSize(80, 20);
		buttonInit.setLocation(40, 500);

		// ACTION LISTENER BUTTON INIT
		buttonInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// QUANDO CHAMAR MOS INIT

				board.generateNewGame();
				
				
				

			}
		});
		
		
		

		JButton buttonLeft = new JButton("LEFT");
		buttonLeft.setSize(80, 20);
		buttonLeft.setLocation(200, 500);
		// ACTION LISTENER BUTTON LEFT
		buttonLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// QUANDO CHAMAR MOS LEFT
				
				if(!board.stuck("left")){
					board.moveLeft();
				}
				
				if(board.victory()){
					board.removeAll();
					JLabel labelVictory = new JLabel("Victory", SwingConstants.CENTER);
					labelVictory.setFont(new Font(labelVictory.getName(), Font.PLAIN, 50));
					labelVictory.setSize(300, 300);
					board.add(labelVictory);
					board.revalidate();
				}
				
				if(board.gameOver()){
					board.removeAll();
					JLabel gameOver = new JLabel("Perdeu", SwingConstants.CENTER);
					gameOver.setFont(new Font(gameOver.getName(), Font.PLAIN, 50));
					gameOver.setSize(300, 300);
					board.add(gameOver);
					board.revalidate();
				}
			}
		});

		JButton buttonDown = new JButton("Down");
		buttonDown.setSize(80, 20);
		buttonDown.setLocation(300, 500);
		// ACTION LISTENER BUTTON DOWN
		buttonDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// QUANDO CHAMAR MOS Down
				if(!board.stuck("down")){
					board.moveDown();
				}
				
				if(board.victory()){
					board.removeAll();
					JLabel labelVictory = new JLabel("Victory", SwingConstants.CENTER);
					labelVictory.setFont(new Font(labelVictory.getName(), Font.PLAIN, 50));
					labelVictory.setSize(300, 300);
					board.add(labelVictory);
					board.revalidate();
				}
				
				
				if(board.gameOver()){
					board.removeAll();
					JLabel gameOver = new JLabel("Perdeu", SwingConstants.CENTER);
					gameOver.setFont(new Font(gameOver.getName(), Font.PLAIN, 50));
					gameOver.setSize(300, 300);
					board.add(gameOver);
					board.revalidate();
				}
			}
		});

		JButton buttonRight = new JButton("Right");
		buttonRight.setSize(80, 20);
		buttonRight.setLocation(400, 500);
		// ACTION LISTENER BUTTON RIGHT
		buttonRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// QUANDO CHAMAR MOS Right
				
				if(!board.stuck("right")){
					board.moveRight();
				}
				
				if(board.victory()){
					board.removeAll();
					JLabel labelVictory = new JLabel("Victory", SwingConstants.CENTER);
					labelVictory.setFont(new Font(labelVictory.getName(), Font.PLAIN, 50));
					labelVictory.setSize(300, 300);
					board.add(labelVictory);
					board.revalidate();
				}
				
				if(board.gameOver()){
					board.removeAll();
					JLabel gameOver = new JLabel("Perdeu", SwingConstants.CENTER);
					gameOver.setFont(new Font(gameOver.getName(), Font.PLAIN, 50));
					gameOver.setSize(300, 300);
					board.add(gameOver);
					board.revalidate();
				}
			}
			
			
		});

		add(board);
		add(buttonInit);
		add(buttonLeft);
		add(buttonDown);
		add(buttonRight);

	}

	public void FrameAbout() {

		JLabel labelLeim = new JLabel();
		labelLeim.setText("Licenciatura em Engenharia Informatica e Multimedia");
		labelLeim.setSize(400, 50);
		labelLeim.setLocation(100, 100);

		JLabel labelMop = new JLabel();
		labelMop.setText("Modelacao e Programacao");
		labelMop.setSize(400, 50);
		labelMop.setLocation(100, 150);

		JLabel labelSemester = new JLabel();
		labelSemester.setText("Semestre de Verao 2015/2016");
		labelSemester.setSize(400, 50);
		labelSemester.setLocation(100, 200);

		String text = "Turma: LEIM21D -" + "Diogo Fernandes n 39205, Ricardo Sousa n 40274";
		JLabel labelClass = new JLabel("<html>" + text + "</html>");
		labelClass.setSize(400, 100);
		labelClass.setLocation(100, 250);

		JButton buttonBack = new JButton("Back");
		buttonBack.setSize(80, 20);
		buttonBack.setLocation(400, 500);
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(labelLeim);
				remove(labelMop);
				remove(labelSemester);
				remove(labelClass);
				remove(buttonBack);
				MenuInicial();
				repaint();
			}
		});

		add(labelLeim);
		add(labelMop);
		add(labelSemester);
		add(labelClass);
		add(buttonBack);

	}

	public void FrameHelp() {

		JLabel labelHelp = new JLabel();
		labelHelp.setText("How to Play 2048");
		labelHelp.setSize(400, 50);
		labelHelp.setLocation(100, 100);

		JLabel labelText = new JLabel();
		labelText.setText("Sempre que se juntar duas pecas de igual valor,");
		labelText.setSize(400, 20);
		labelText.setLocation(100, 150);

		JLabel labelText1 = new JLabel();
		labelText1.setText("estas fundem-se numa peca com a soma dos seus valores");
		labelText1.setSize(400, 20);
		labelText1.setLocation(100, 170);

		JLabel labelText2 = new JLabel();
		labelText2.setText("o objectivo e conseguir chegar com uma peca ao valor 2048.");
		labelText2.setSize(400, 20);
		labelText2.setLocation(100, 190);

		JButton buttonBack = new JButton("Back");
		buttonBack.setSize(80, 20);
		buttonBack.setLocation(400, 500);
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(labelHelp);
				remove(labelText);
				remove(labelText1);
				remove(labelText2);
				remove(buttonBack);
				MenuInicial();
				repaint();
			}
		});

		add(labelHelp);
		add(labelText);
		add(labelText1);
		add(labelText2);
		add(buttonBack);
	}


	
	
	

	
	
}
