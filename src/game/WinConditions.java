package game;

public class WinConditions {
	
	Board board;
	int[] scores;
	
	public WinConditions(Board board) {
		this.board = board;
		scores = new int[4];	//0 = red, 1 = blue, 2 = green, 3 = yellow
		for (int i = 0; i < 4; i++) {
			scores[i] = 0;
		}
	}
	
	public void calculate() {
		int size = board.getSize();
		int rings = 4;
		int[] counter;
		int ringCounter;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				counter = new int[4];	//0 = red, 1 = blue, 2 = green, 3 = yellow
				ringCounter = 0;
				for (int k = 0; k < rings; k++) {	//reset counter before next tile counting attempt
					counter[k] = 0;
				}
				for (int k = 0; k < rings; k++) {	//count colors in current tile
					switch (board.getTile(i, j).getColor(k)) {
					case NONEE:
						break;
					case REDDD:
						counter[0]++;
						ringCounter++;
						break;
					case BLUEE:
						counter[1]++;
						ringCounter++;
						break;
					case GREEN:
						counter[2]++;
						ringCounter++;
						break;
					case YELLO:
						counter[3]++;
						ringCounter++;
						break;
					case RBASE:
					case BBASE:
					case GBASE:
					case YBASE:
					case SBASE:
						k = rings;
						break;
					default:
						break;
					}
				}
				if (!(ringCounter == 0)) {
					if (ringCounter == 1) {
						for (int k = 0; k < rings; k++) {
							if (counter[k] == 1) {
								scores[k]++;
								break;
							}
						}
					} else if (ringCounter == 2) {
						for (int k = 0; k < rings; k++) {
							if (counter[k] == 2) {
								scores[k]++;
								break;
							}
						}
					} else if (ringCounter == 3) {
						for (int k = 0; k < rings; k++) {
							if (counter[k] == 2 || counter[k] == 3) {
								scores[k]++;
								break;
							}
						}
					} else {
						int store = 5;
						for (int k = 0; k < rings; k++) {
							if (counter[k] == 3 || counter [k] == 4) {
								scores[k]++;
								break;
							}
							if (counter[k] == 2) {
								if (store == 5) {
									store = k;
								} else {
									store = 5;
								}
							}
						}
						if (!(store == 5)) {
							scores[store]++;
						}
					}
				}
			}
		}
		boolean draw = false;
		int winner = 0;
		int highScore = scores[0];
		for (int i = 1; i < 4; i++) {
			if (scores[i] == highScore) {
				draw = true;
			} else if (scores[i] > highScore) {
				draw = false;
				highScore = scores[i];
				winner = i;
			}
		}
		if (draw) {
			System.out.println("It is a tie!");
		} else {
			System.out.println("The winner is " + winner + "! (0 = red, 1 = blue, 2 = green, 3 = yellow)");
		}
	}
	
}
