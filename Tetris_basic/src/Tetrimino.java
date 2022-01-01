
class Tetrimino {
	public int x, y;	//��Ʈ���̳��� ��ġ ��ǥ
	public int num, turn;	//��� ��ȣ, ȸ��
	public int[][][] tetrimino;	//��Ʈ���̳��� ���
	int[][][] make = {
			{{1, 0}, {1, 1}, {1, 2}, {1, 3}},	//I
			{{0, 0}, {1, 0}, {1, 1}, {1, 2}},	//J
			{{0, 2}, {1, 0}, {1, 1}, {1, 2}},	//L
			{{1, 1}, {1, 2}, {2, 1}, {2, 2}},	//O
			{{0, 1}, {0, 2}, {1, 0}, {1, 1}},	//S
			{{0, 1}, {1, 0}, {1, 1}, {1, 2}},	//T
			{{0, 0}, {0, 1}, {1, 1}, {1, 2}}	//Z
	};
	
	public int getPositionRow(int i) {
		if(num != 0 && num != 3) {
			switch(turn) {
			case 0:
				return y + make[num][i][0];
			case 1:
				return y + make[num][i][1];
			case 2:
				return y + 2 - make[num][i][0];
			case 3:
				return y + 2 - make[num][i][1];
			}
		}else {
			switch(turn) {
			case 0:
				return y + make[num][i][0];
			case 1:
				return y + make[num][i][1];
			case 2:
				return y + 3 - make[num][i][0];
			case 3:
				return y + 3 - make[num][i][1];
			}
		}
		return 0;
	}
	public int getPositionCol(int i) {
		if(num != 0 && num != 3) {
			switch(turn) {
			case 0:
				return x + make[num][i][1];
			case 1:
				return x + 2 - make[num][i][0];
			case 2:
				return x + 2 - make[num][i][1];
			case 3:
				return x + make[num][i][0];
			}
		}else {
			switch(turn) {
			case 0:
				return x + make[num][i][1];
			case 1:
				return x + 3 - make[num][i][0];
			case 2:
				return x + 3 - make[num][i][1];
			case 3:
				return x + make[num][i][0];
			}
		}
		return 0;
	}
	
	//���� ������ �� �ִ����� �Ǵ����ִ� �Լ�
	public boolean iscanMoveMino(int dx, int dy, int[][] field) {
		if(num != 0 && num != 3) {
			switch(turn) {
			case 0:
				return (field[y+dy+make[num][0][0]][x+dx+make[num][0][1]]==0)&&
						(field[y+dy+make[num][1][0]][x+dx+make[num][1][1]]==0)&&
						(field[y+dy+make[num][2][0]][x+dx+make[num][2][1]]==0)&&
						(field[y+dy+make[num][3][0]][x+dx+make[num][3][1]]==0);
			case 1:
				return (field[y+dy+make[num][0][1]][x+dx+2-make[num][0][0]]==0)&&
						(field[y+dy+make[num][1][1]][x+dx+2-make[num][1][0]]==0)&&
						(field[y+dy+make[num][2][1]][x+dx+2-make[num][2][0]]==0)&&
						(field[y+dy+make[num][3][1]][x+dx+2-make[num][3][0]]==0);
			case 2:
				return (field[y+dy+2-make[num][0][0]][x+dx+2-make[num][0][1]]==0)&&
						(field[y+dy+2-make[num][1][0]][x+dx+2-make[num][1][1]]==0)&&
						(field[y+dy+2-make[num][2][0]][x+dx+2-make[num][1][1]]==0)&&
						(field[y+dy+2-make[num][3][0]][x+dx+2-make[num][3][1]]==0);
			case 3:
				return (field[y+dy+2-make[num][0][1]][x+dx+make[num][0][0]]==0)&&
						(field[y+dy+2-make[num][1][1]][x+dx+make[num][1][0]]==0)&&
						(field[y+dy+2-make[num][2][1]][x+dx+make[num][2][0]]==0)&&
						(field[y+dy+2-make[num][3][1]][x+dx+make[num][3][0]]==0);
			}
		}
		else {
			switch(turn) {
			case 0:
				return (field[y+dy+make[num][0][0]][x+dx+make[num][0][1]]==0)&&
						(field[y+dy+make[num][1][0]][x+dx+make[num][1][1]]==0)&&
						(field[y+dy+make[num][2][0]][x+dx+make[num][2][1]]==0)&&
						(field[y+dy+make[num][3][0]][x+dx+make[num][3][1]]==0);
			case 1:
				return (field[y+dy+make[num][0][1]][x+dx+3-make[num][0][0]]==0)&&
						(field[y+dy+make[num][1][1]][x+dx+3-make[num][1][0]]==0)&&
						(field[y+dy+make[num][2][1]][x+dx+3-make[num][2][0]]==0)&&
						(field[y+dy+make[num][3][1]][x+dx+3-make[num][3][0]]==0);
			case 2:
				return (field[y+dy+3-make[num][0][0]][x+dx+3-make[num][0][1]]==0)&&
						(field[y+dy+3-make[num][1][0]][x+dx+3-make[num][1][1]]==0)&&
						(field[y+dy+3-make[num][2][0]][x+dx+3-make[num][1][1]]==0)&&
						(field[y+dy+3-make[num][3][0]][x+dx+3-make[num][3][1]]==0);
			case 3:
				return (field[y+dy+3-make[num][0][1]][x+dx+make[num][0][0]]==0)&&
						(field[y+dy+3-make[num][1][1]][x+dx+make[num][1][0]]==0)&&
						(field[y+dy+3-make[num][2][1]][x+dx+make[num][2][0]]==0)&&
						(field[y+dy+3-make[num][3][1]][x+dx+make[num][3][0]]==0);
			}
		}
		return false;
	}
	//����� ȸ����Ű�� �Լ�, ȸ����ų �� �ִ��� ���� �Ǵ��Ͽ� ȸ�� �Ұ����� ��� ȸ������ �ʴ´�.
	public boolean turnMino(int tm, int[][] field) {
		int before = turn;
		turn += tm;
		if(turn == -1) turn = 3;
		else if(turn == 4) turn = 0;
		if(num != 0 && num != 3) {
			switch(turn) {
			case 0:
				if ((field[y+make[num][0][0]][x+make[num][0][1]]==0)&&
						(field[y+make[num][1][0]][x+make[num][1][1]]==0)&&
						(field[y+make[num][2][0]][x+make[num][2][1]]==0)&&
						(field[y+make[num][3][0]][x+make[num][3][1]]==0))
					return true;
				turn = before;
				return false;
			case 1:
				if ((field[y+make[num][0][1]][x+make[num][0][0]]==0)&&
						(field[y+make[num][1][1]][x+make[num][1][0]]==0)&&
						(field[y+make[num][2][1]][x+make[num][2][0]]==0)&&
						(field[y+make[num][3][1]][x+make[num][3][0]]==0))
					return true;
				turn = before;
				return false;
			case 2:
				if ((field[y+2-make[num][0][0]][x+2-make[num][0][1]]==0)&&
						(field[y+2-make[num][1][0]][x+2-make[num][1][1]]==0)&&
						(field[y+2-make[num][2][0]][x+2-make[num][1][1]]==0)&&
						(field[y+2-make[num][3][0]][x+2-make[num][3][1]]==0))
					return true;
				turn = before;
				return false;
			case 3:
				if ((field[y+2-make[num][0][1]][x+2-make[num][0][0]]==0)&&
						(field[y+2-make[num][1][1]][x+2-make[num][1][0]]==0)&&
						(field[y+2-make[num][2][1]][x+2-make[num][2][0]]==0)&&
						(field[y+2-make[num][3][1]][x+2-make[num][3][0]]==0))
					return true;
				turn = before;
				return false;
			}
		}else {
			switch(turn) {
			case 0:
				if ((field[y+make[num][0][0]][x+make[num][0][1]]==0)&&
						(field[y+make[num][1][0]][x+make[num][1][1]]==0)&&
						(field[y+make[num][2][0]][x+make[num][2][1]]==0)&&
						(field[y+make[num][3][0]][x+make[num][3][1]]==0))
					return true;
				turn = before;
				return false;
			case 1:
				if ((field[y+make[num][0][1]][x+make[num][0][0]]==0)&&
						(field[y+make[num][1][1]][x+make[num][1][0]]==0)&&
						(field[y+make[num][2][1]][x+make[num][2][0]]==0)&&
						(field[y+make[num][3][1]][x+make[num][3][0]]==0))
					return true;
				turn = before;
				return false;
			case 2:
				if ((field[y+3-make[num][0][0]][x+3-make[num][0][1]]==0)&&
						(field[y+3-make[num][1][0]][x+3-make[num][1][1]]==0)&&
						(field[y+3-make[num][2][0]][x+3-make[num][1][1]]==0)&&
						(field[y+3-make[num][3][0]][x+3-make[num][3][1]]==0))
					return true;
				turn = before;
				return false;
			case 3:
				if ((field[y+3-make[num][0][1]][x+3-make[num][0][0]]==0)&&
						(field[y+3-make[num][1][1]][x+3-make[num][1][0]]==0)&&
						(field[y+3-make[num][2][1]][x+3-make[num][2][0]]==0)&&
						(field[y+3-make[num][3][1]][x+3-make[num][3][0]]==0))
					return true;
				turn = before;
				return false;
			}
		}
		return false;
	}
	
	public Tetrimino(int n) {	//������(��� ���� �ʱ�ȭ) 0~6
		tetrimino = new int [4][4][4];
		x = 5; y = 0; turn = 0;
		num = n;
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				for(int l = 0; l < 4; l++)
					tetrimino[i][j][l] = 0;

		if(num != 0 && num != 3) {
			//0�� ȸ��
			tetrimino[0][make[n][0][0]][make[n][0][1]] = n+1;
			tetrimino[0][make[n][1][0]][make[n][1][1]] = n+1;
			tetrimino[0][make[n][2][0]][make[n][2][1]] = n+1;
			tetrimino[0][make[n][3][0]][make[n][3][1]] = n+1;
		
			//90�� ȸ��
			tetrimino[1][make[n][0][1]][2-make[n][0][0]] = n+1;
			tetrimino[1][make[n][1][1]][2-make[n][1][0]] = n+1;
			tetrimino[1][make[n][2][1]][2-make[n][2][0]] = n+1;
			tetrimino[1][make[n][3][1]][2-make[n][3][0]] = n+1;

			//180�� ȸ��
			tetrimino[2][2-make[n][0][0]][2-make[n][0][1]] = n+1;
			tetrimino[2][2-make[n][1][0]][2-make[n][1][1]] = n+1;
			tetrimino[2][2-make[n][2][0]][2-make[n][2][1]] = n+1;
			tetrimino[2][2-make[n][3][0]][2-make[n][3][1]] = n+1;

			//270�� ȸ��
			tetrimino[3][2-make[n][0][1]][make[n][0][0]] = n+1;
			tetrimino[3][2-make[n][1][1]][make[n][1][0]] = n+1;
			tetrimino[3][2-make[n][2][1]][make[n][2][0]] = n+1;
			tetrimino[3][2-make[n][3][1]][make[n][3][0]] = n+1;
		}
		else {
			//0�� ȸ��
			tetrimino[0][make[n][0][0]][make[n][0][1]] = n+1;
			tetrimino[0][make[n][1][0]][make[n][1][1]] = n+1;
			tetrimino[0][make[n][2][0]][make[n][2][1]] = n+1;
			tetrimino[0][make[n][3][0]][make[n][3][1]] = n+1;
		
			//90�� ȸ��
			tetrimino[1][make[n][0][1]][3-make[n][0][0]] = n+1;
			tetrimino[1][make[n][1][1]][3-make[n][1][0]] = n+1;
			tetrimino[1][make[n][2][1]][3-make[n][2][0]] = n+1;
			tetrimino[1][make[n][3][1]][3-make[n][3][0]] = n+1;

			//180�� ȸ��
			tetrimino[2][3-make[n][0][0]][3-make[n][0][1]] = n+1;
			tetrimino[2][3-make[n][1][0]][3-make[n][1][1]] = n+1;
			tetrimino[2][3-make[n][2][0]][3-make[n][2][1]] = n+1;
			tetrimino[2][3-make[n][3][0]][3-make[n][3][1]] = n+1;

			//270�� ȸ��
			tetrimino[3][3-make[n][0][1]][make[n][0][0]] = n+1;
			tetrimino[3][3-make[n][1][1]][make[n][1][0]] = n+1;
			tetrimino[3][3-make[n][2][1]][make[n][2][0]] = n+1;
			tetrimino[3][3-make[n][3][1]][make[n][3][0]] = n+1;
		}
	}
}
