package tp4;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

public class Board extends JPanel {

	Random random;
	Place BoardPosition[][];
	int size;
	
	
	public Board(int size) {
		this.size = size;
		setSize(400, 400);
		setLocation(100, 20);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(size, size));
		setVisible(true);
		BoardPosition = new Place[size][size];
		random = new Random();

	}
	
	
	/**
	 *
	 *Metodo encarregue por verificar se o jogador atingiu o objetivo do jogo
	 *Simplesmente corremos um ciclo for as posicoes do tabuleiro para saber se algum
	 *NumericPlace tem o valor de 2048
	 *
	 */
	public boolean victory(){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(BoardPosition[i][j] instanceof NumericPlace){
					if (((NumericPlace) BoardPosition[i][j]).getNum() == 2048) {
						return false;
					}
				}
			}
		}
		return false;
	}
	
	
	

	/**
	 *Metodo encarregue de verificar se o jogador perdeu o jogo ao amontoar
	 *demasiados places. Isto acontece quando o jogador ja nao tiver posicoes vazias onde
	 *possa colocar outras pecas e/ou aparecer pecas random
	 */
	
	public boolean gameOver(){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(BoardPosition[i][j] instanceof EmptyPlace){
					return false;
				}
			}
		}
		return true;
	}
	
	

	/**
	 *
	 *Metodo encarregue de verificar se o jogador esta preso.
	 *Ou seja o jogador pode ficar preso para a "direita" ou seja ja nao tem pecas
	 *para colocar à direita. Nao perde o jogo mas nao podem aparecer mais pecas random
	 *porque o jogador tem de de escolher outro movimento. Aparecer novas pecas facilitava.
	 *
	 *Basta verificar se para cada movimento o jogador tem nessa mesma direccao um EmptyPlace
	 *ou um NumericPlace a que se possa fundir
	 *
	 */
	
	public boolean stuck(String move) {

		if (move.equals("down")) {

			for (int i = 0; i < size - 1; i++) {
					for (int j = 0; j < size; j++) {
					
					if (BoardPosition[i][j] instanceof NumericPlace
							&& BoardPosition[i + 1][j] instanceof NumericPlace) {

						if (((NumericPlace) BoardPosition[i][j]).getNum() == ((NumericPlace) BoardPosition[i + 1][j])
								.getNum()) {
							return false;

						}

					}
					
					if(BoardPosition[i][j] instanceof NumericPlace && BoardPosition[i+1][j] instanceof EmptyPlace){
						return false;
					}
				}

			}
			return true;
		}
		
		
		
		
			if (move.equals("right")) {
				
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size-1; j++) {
						if (BoardPosition[i][j] instanceof NumericPlace
								&& BoardPosition[i][j + 1] instanceof NumericPlace) {

							if (((NumericPlace) BoardPosition[i][j]).getNum() == ((NumericPlace) BoardPosition[i][j + 1])
									.getNum()) {
								return false;

							}

						}
						
						if(BoardPosition[i][j] instanceof NumericPlace && BoardPosition[i][j + 1] instanceof EmptyPlace){
							return false;
						}
					}

				}
				
				return true;

			}

			if (move.equals("left")) {
				
				for (int i = 0; i < size; i++) {
					for (int j = 1; j < size; j++) {
						if (BoardPosition[i][j] instanceof NumericPlace
								&& BoardPosition[i][j - 1] instanceof NumericPlace) {

							if (((NumericPlace) BoardPosition[i][j]).getNum() == ((NumericPlace) BoardPosition[i][j - 1])
									.getNum()) {
								return false;

							}

						}
						
						if(BoardPosition[i][j] instanceof NumericPlace && BoardPosition[i][j - 1] instanceof EmptyPlace){
							return false;
						}
					}

				}
				
				return true;

			}
			
			return false;
	}
	
	

	/**
	 *
	 *Metodo usado quando se inicia um novo jogo. O metodo encarrega-se de escolher coordenadas diferentes
	 *para duas NumericPlace com valor de 2. Só quando existem coordenadas diferentes é que estas sao atribuidas
	 *às NumericPlace e estas mesmo adicionadas ao Panel em conjunto com as Empty no resto das posicoes
	 *
	 */
	public void generateNewGame() {
		removeAll();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				BoardPosition[i][j] = null;
			}
		}
		int x0 = random.nextInt(5);
		int x1 = random.nextInt(5);
		int y0 = random.nextInt(5);
		int y1 = random.nextInt(5);

		System.out.println("x0:" + x0 + "  y0:" + y0 + "  x1:" + x1 + "  y1:" + y1);

		if (x0 != x1 || y0 != y1) {
			BoardPosition[x0][y0] = new NumericPlace(x0, y0, 2);
			BoardPosition[x1][y1] = new NumericPlace(x1, y1, 2);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (BoardPosition[i][j] == null) {
						BoardPosition[i][j] = new EmptyPlace(i, j);
					}
				}
			}

			paintArray();
			revalidate();
			repaint();

		} else {
			generateNewGame();
		}

	}

	
	

	/**
	 *Metodo que adiciona ao Panel as Places que estao guardadas no array bidimensional de Places BoardPosition
	 */
	
	public void paintArray() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				add(BoardPosition[i][j]);
			}
		}
	}

	
	
	
	public boolean moveDown() {

		removeAll();

		// correr posicoes de baixo para cima
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++) {

				// se o place a ser analisado for numerico e numa posicao valida
				if (BoardPosition[i][j] instanceof NumericPlace && i + 1 < size) {

					// se o Place a baixo do analisado é um Empty...
					if (BoardPosition[i + 1][j] instanceof EmptyPlace) {

						// lastX fica com a ultima coordenada X onde ha um Empty
						int lastX = i + 1;
						for (int coordX = i + 1; coordX < size; coordX++) {
							if (BoardPosition[coordX][j] instanceof EmptyPlace) {
								lastX = coordX;
							}
						}

						// depois de correr as posicoes todas Empty na Vertical
						// temos de saber
						// se existe uma numeric por baixo
						if (lastX < size - 1) {

							if (BoardPosition[lastX + 1][j] instanceof NumericPlace) {

								if (((NumericPlace) BoardPosition[i][j])
										.getNum() == ((NumericPlace) BoardPosition[lastX + 1][j]).getNum()) {
									BoardPosition[lastX + 1][j] = new NumericPlace(lastX + 1, j,
											(((NumericPlace) BoardPosition[i][j]).getNum()) * 2);

									// eliminar a peca movida do local antigo
									BoardPosition[i][j] = new EmptyPlace(i, j);

								}

								else {
									// criar peca
									BoardPosition[lastX][j] = new NumericPlace(lastX, j,
											((NumericPlace) BoardPosition[i][j]).getNum());
									// eliminar antiga
									BoardPosition[i][j] = new EmptyPlace(i, j);
								}

							}
							
							// NOVO
							else {

								// criar peca
								BoardPosition[lastX][j] = new NumericPlace(lastX, j,
										((NumericPlace) BoardPosition[i][j]).getNum());
								// eliminar antiga
								BoardPosition[i][j] = new EmptyPlace(i, j);

							}
							
						}
						
						
						else {

							// criar peca
							BoardPosition[lastX][j] = new NumericPlace(lastX, j,
									((NumericPlace) BoardPosition[i][j]).getNum());
							// eliminar antiga
							BoardPosition[i][j] = new EmptyPlace(i, j);

						}
						

					}

					// se for numerica basta verificar se tem o mesmo valor caso
					// tenham duplica se o valor da peca
					else if (BoardPosition[i + 1][j] instanceof NumericPlace) {
						if (((NumericPlace) BoardPosition[i][j]).getNum() == ((NumericPlace) BoardPosition[i + 1][j])
								.getNum()) {
							BoardPosition[i + 1][j] = new NumericPlace(i + 1, j,
									(((NumericPlace) BoardPosition[i][j]).getNum()) * 2);

							// eliminar a peca movida do local antigo
							BoardPosition[i][j] = new EmptyPlace(i, j);

						}
					}

				}
			}
		}

		createPlaces();
		updateRocks();
		paintArray();
		revalidate();
		repaint();

		return true;
	}
	
	
	public boolean moveRight() {

		removeAll();

		for (int j = size - 1; j >= 0; j--) {
			for (int i = 0; i < size; i++) {

				if (BoardPosition[i][j] instanceof NumericPlace && j + 1 < size) {

					if (BoardPosition[i][j + 1] instanceof EmptyPlace) {

						int lastY = j + 1;
						for (int coordY = j + 1; coordY < size; coordY++) {
							if (BoardPosition[i][coordY] instanceof EmptyPlace) {
								lastY = coordY;
							}
						}

						
						if (lastY < size - 1) {

							if (BoardPosition[i][lastY + 1] instanceof NumericPlace) {

								if (((NumericPlace) BoardPosition[i][j])
										.getNum() == ((NumericPlace) BoardPosition[i][lastY + 1]).getNum()) {
									BoardPosition[i][lastY + 1] = new NumericPlace(i, lastY + 1,
											(((NumericPlace) BoardPosition[i][j]).getNum()) * 2);

									BoardPosition[i][j] = new EmptyPlace(i, j);

								}

								else {
							
									BoardPosition[i][lastY] = new NumericPlace(i, lastY,
											((NumericPlace) BoardPosition[i][j]).getNum());
							
									BoardPosition[i][j] = new EmptyPlace(i, j);
								}

							}
							
					
							else{
						
								BoardPosition[i][lastY] = new NumericPlace(i, lastY,
										((NumericPlace) BoardPosition[i][j]).getNum());
						
								BoardPosition[i][j] = new EmptyPlace(i, j);
							}
							
							
						} else {

						
							BoardPosition[i][lastY] = new NumericPlace(i, lastY,
									((NumericPlace) BoardPosition[i][j]).getNum());
					
							BoardPosition[i][j] = new EmptyPlace(i, j);

						}

					}

				
					else if (BoardPosition[i][j + 1] instanceof NumericPlace) {
						if (((NumericPlace) BoardPosition[i][j]).getNum() == ((NumericPlace) BoardPosition[i][j + 1])
								.getNum()) {
							BoardPosition[i][j + 1] = new NumericPlace(i, j + 1,
									(((NumericPlace) BoardPosition[i][j]).getNum()) * 2);

							
							BoardPosition[i][j] = new EmptyPlace(i, j);

						}
					}

				}
			}
		}

		createPlaces();
		updateRocks();
		paintArray();
		revalidate();
		repaint();

		return true;

	}

	
	
	
	
	
	
	public boolean moveLeft() {

		removeAll();

		for (int j = 1; j < size; j++) {
			for (int i = 0; i < size; i++) {


				if (BoardPosition[i][j] instanceof NumericPlace && j - 1 >= 0) {

					if (BoardPosition[i][j - 1] instanceof EmptyPlace) {
						
						int lastY = j - 1;
						for (int coordY = j - 1; coordY >= 0; coordY--) {
							if (BoardPosition[i][coordY] instanceof EmptyPlace) {
								lastY = coordY;
							}
						}

						
						if (lastY > 0) {

							if (BoardPosition[i][lastY - 1] instanceof NumericPlace) {

								if (((NumericPlace) BoardPosition[i][j])
										.getNum() == ((NumericPlace) BoardPosition[i][lastY - 1]).getNum()) {
									BoardPosition[i][lastY - 1] = new NumericPlace(i, lastY - 1,
											(((NumericPlace) BoardPosition[i][j]).getNum()) * 2);

									BoardPosition[i][j] = new EmptyPlace(i, j);

								}

								else {

									BoardPosition[i][lastY] = new NumericPlace(i, lastY,
											((NumericPlace) BoardPosition[i][j]).getNum());

									BoardPosition[i][j] = new EmptyPlace(i, j);
								}

							}

						
							else {
	
								BoardPosition[i][lastY] = new NumericPlace(i, lastY,
										((NumericPlace) BoardPosition[i][j]).getNum());

								BoardPosition[i][j] = new EmptyPlace(i, j);
							}

						} else {

				
							BoardPosition[i][lastY] = new NumericPlace(i, lastY,
									((NumericPlace) BoardPosition[i][j]).getNum());

							BoardPosition[i][j] = new EmptyPlace(i, j);

						}

					}

			
					else if (BoardPosition[i][j - 1] instanceof NumericPlace) {
						if (((NumericPlace) BoardPosition[i][j]).getNum() == ((NumericPlace) BoardPosition[i][j - 1])
								.getNum()) {
							BoardPosition[i][j - 1] = new NumericPlace(i, j - 1,
									(((NumericPlace) BoardPosition[i][j]).getNum()) * 2);
							
							BoardPosition[i][j] = new EmptyPlace(i, j);

						}
					}

				}
			}
		}

		createPlaces();
		updateRocks();
		paintArray();
		revalidate();
		repaint();

		return true;

	}


	/**
	 *
	 *Metodo encarregue de decrementar a "vida" das RockPlaces e de eliminar as que
	 *nao devem durar mais
	 *
	 */

	public void updateRocks() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (BoardPosition[i][j] instanceof RockPlace) {
					if (((RockPlace) BoardPosition[i][j]).getSpan() > 1) {
						((RockPlace) BoardPosition[i][j]).decrementaSpan();
						int aux = ((RockPlace) BoardPosition[i][j]).getSpan();
						BoardPosition[i][j] = new RockPlace(i, j, aux);
					} else {
						BoardPosition[i][j] = new EmptyPlace(i, j);
					}
				}
			}
		}
	}
	
	

	/**
	 *
	 *Metodo usado a seguir a cada jogada para aparecer com uma certa probabilidade uma NumericPlace valor 2
	 * ou valor 2 ou ate mesmo uma RockPlace que desaparecera depois dos proximos 4 movimentos
	 *
	 */

	public void createPlaces() {
		int x0 = random.nextInt(5);
		int y0 = random.nextInt(5);
		int aux = random.nextInt(100);
		if (BoardPosition[x0][y0] instanceof EmptyPlace) {
			if (aux < 88) {
				BoardPosition[x0][y0] = new NumericPlace(x0, y0, 2);
			}
			if (aux < 98 && aux > 87) {
				BoardPosition[x0][y0] = new NumericPlace(x0, y0, 4);
				
			}
			if (aux < 100 && aux > 97) {
				BoardPosition[x0][y0] = new RockPlace(x0, y0, 5);
			}
		} else {
			createPlaces();
		}
	}

}
