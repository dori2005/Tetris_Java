
class Field {
	int score, attack;
	public int[][] field;	//�ʵ� ����
	
	int checkClear() {		//����� ���� �� ���� �ֳ� Ȯ�� �� ����
		int[] isfullC = {0, 0, 0, 0, 0};
		int i, j, l, n = 0;
		for(i = 20; i >= 0; i--) {
			j=2;
			while(field[i][j] != 0 && j < 12)
				j++;
			if(j == 12) isfullC[n++] = i;
		}	//���� �� �� üũ
		
		if(n==0) return 0;	//���� �� ���� ����
		
		//�� �ٵ��� �Ʒ��� ��������
		int tum = 1;	//�Ʒ��� ������ ĭ ��
		for(i = 0; i < n; i++) {
			System.out.println(isfullC[i+1]);
			while(isfullC[i]-1==isfullC[i+1]) {	//���ӵǴ� ���� �ǳʶ�
				i++; tum++;
				continue;
			}
			System.out.println(tum);
			//������ �ٺ��� ������ �Ʒ��� ����
			for(j = isfullC[i-tum+1]; j >= tum; j--) {
				for(l = 2; l < 12; l++)
					field[j][l] = field[j-tum][l];
			}
			if(tum == 1) {
				score += 1000; attack = 0; return 0;
			} else if(tum == 2) {
				score += 3000; attack = 1; return 1;
			} else if(tum == 3) {
				score += 6000; attack = 2; return 2;
			}else if(tum == 4) {
				score += 10000; attack = 4; return 4;
			}
			tum = 1;
		}
		return 0;
	}
	void putTrash(int a) {
		int i, j;
		int rand = 2+(int)(Math.random()*9.99999);
		for(i = a; i < 21; i++) {
			for(j = 2; j < 12; j++)
				field[i-a][j] = field[i][j];
		}
		for(i = 20; i > 20-a; i--) {
			for(j = 2; j < 12; j++)
				if(rand != j)
					field[i][j] = 8;
				else
					field[i][j] = 0;
		}
	}
	public boolean checkGameOver() {
		int sum = 0;
		for(int i = 2; i < 12; i++)
			sum += field[1][i];
		return sum != 0;
	}
	void putBlock(Tetrimino tetrimino) {
		for(int i = 0; i < 4; i++) 
			field[tetrimino.getPositionRow(i)][tetrimino.getPositionCol(i)] = tetrimino.num+1;
	}
	void putOffBlock(Tetrimino tetrimino) {
		for(int i = 0; i < 4; i++) 
			for(int j = 0; j < 4; j++) 
				if (tetrimino.tetrimino[tetrimino.turn][i][j] != 0)
					field[tetrimino.y+i][tetrimino.x+j] = 0;
	}
	
	public Field() {	//�ʵ� �ʱ�ȭ
		field = new int [23][14];
		for(int i = 0; i < 21; i++) {
			for(int j = 2; j < 12; j++) {
				field[i][j] = 0;	
			}
		}
		for(int i = 0; i < 14; i++) {
			field[21][i] = -1;
			field[22][i] = -1;
		}
		for(int i = 0; i < 21; i++) {
			field[i][0] = -1;
			field[i][1] = -1;
			field[i][12] = -1;
			field[i][13] = -1;
		}
	}
}
