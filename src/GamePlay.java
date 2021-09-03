import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class GamePlay extends JFrame{
	boolean gameOver, minoOnField;
	int bag[][], bagnum, rt;
	JPanel contentPane;
	Label label[][];
	JLabel scoreL;
	JLabel win_lose;
	Tetrimino mino;
	Field f;
	Timer timer;
	Color[] blockC = {Color.WHITE, Color.CYAN, Color.BLUE, Color.ORANGE,
			Color.YELLOW, Color.GREEN, Color.PINK, Color.RED, Color.GRAY};
	Socket CS = null;
	
	public GamePlay() {
		gameOver = false; minoOnField = false;
		bag = new int [2][7];
		f = new Field();
		getNewBag();
		getNewBag();
		bagnum = 0;
		mino = new Tetrimino(bag[0][bagnum++]);
 
        CS = null;
        try {
            CS = new Socket();
            CS.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
            System.out.println("연결성공! 대기중...");
            InputStream IS = CS.getInputStream();
            byte[] bt = new byte[4];
            
            IS.read(bt);
            System.out.print("준비완료"+byteToInt(bt));
            
        } catch (Exception e) {
            System.out.println(e);
        }
		
		setTitle("Tetris");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds (10, 10, 350, 800);
		 
		GridLayout grid = new GridLayout(20, 10);
		grid.setVgap(2);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLocation(20, 120);
		panel.setSize(300, 600);
		panel.setLayout(grid);
		
	     //창 닫기 방식
	      
		label = new Label[20][10];
		for ( int i = 0; i < label.length; i++ )
		{
			for ( int j = 0; j < label[i].length; j++ )
			{
				label[i][j] = new Label();
				label[i][j].setBackground (Color.WHITE);
				panel.add (label[i][j]);
			}
		}
		c.add(panel);

		scoreL = new JLabel("score : 0");
		scoreL.setBounds(50, 50, 350, 40);
		scoreL.setFont(new Font("맑은 고딕", Font.BOLD, 32));
		c.add(scoreL);
		
		timer = new Timer(600, new MyTimer());
		timer.start();
		
		this.addKeyListener(new MyKeyAdapter());
		
	}
	
	public  byte[] intToByte(int value) {
		byte[] byteArray = new byte[4];
		byteArray[0] = (byte)(value >> 24);
		byteArray[1] = (byte)(value >> 16);
		byteArray[2] = (byte)(value >> 8);
		byteArray[3] = (byte)(value);
		return byteArray;
	}
    public int byteToInt(byte[] arr){ 
    	return (arr[0] & 0xff)<<24 | (arr[1] & 0xff)<<16 | (arr[2] & 0xff)<<8 | (arr[3] & 0xff); 
    }
	
	public void updateGUI() {		
		scoreL.setText("score : "+f.score);
		for ( int i = 0; i < label.length; i++ ) {
			for ( int j = 0; j < label[i].length; j++ ) {
				label[i][j].setBackground (blockC[f.field[i+1][j+2]]);
			}
		}
	}
	
	public void getNewBag() {
		bag[0] = bag[1];
		bag[1] = new int [7];
		for(int i = 0; i < 7; i++) {
			bag[1][i] = (int)(Math.random()*6.99999);
			for(int j = 0; j < i; j++) {
				if(bag[1][i] == bag[1][j]) {
					i--;
					break;
				}
			}
		}
	}
	
	void downBlock() {
		f.putOffBlock(mino);
		if(mino.iscanMoveMino(0, 1, f.field)) {
			mino.y += 1;
			f.putBlock(mino);
			updateGUI();
			return;
		}	
		f.putBlock(mino);
		mino = new Tetrimino(bag[0][bagnum]);
		bagnum += 1;
		if(bagnum == 7) {
			getNewBag();
			bagnum = 0;
		}
		int OT = f.checkClear();
		try {
			byte[] as = intToByte(OT);
			OutputStream OS = CS.getOutputStream();
            OS.write(as);
            
            InputStream IS = CS.getInputStream();
            byte[] bt = new byte[4];
            
            IS.read(bt);
            rt += byteToInt(bt);
            
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
        System.out.print("> "+rt);
        f.putTrash(rt);
        rt = 0;
		
		
		if(f.checkGameOver())
			gameOver = true;
		updateGUI();
	}
	
	class MyTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!gameOver) {
				downBlock();
			}else {
				try {
		            CS.close();
		        } catch (Exception e1) {
		            System.out.println(e);
		        }
				timer.stop();
			}
		}
	}
	class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(!gameOver) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					f.putOffBlock(mino);
					if (mino.iscanMoveMino(-1,0,f.field))
						mino.x -= 1;
					f.putBlock(mino);
					break;
				case KeyEvent.VK_RIGHT:
					f.putOffBlock(mino);
					if (mino.iscanMoveMino(1,0,f.field))
						mino.x += 1;
					f.putBlock(mino);
					break;
				case KeyEvent.VK_E:
					f.putOffBlock(mino);
					mino.turnMino(1,f.field);
					f.putBlock(mino);
					break;
				case KeyEvent.VK_Q:
					f.putOffBlock(mino);
					mino.turnMino(-1,f.field);
					f.putBlock(mino);
					break;
				case KeyEvent.VK_W:
					rt += 1;
					System.out.println(rt);
					break;
				case KeyEvent.VK_DOWN:
					downBlock();
					break;
				case KeyEvent.VK_SPACE:	
					while(true) {
						f.putOffBlock(mino);
						if(mino.iscanMoveMino(0, 1, f.field)) {
							mino.y += 1;
							f.putBlock(mino);
						}else {
							f.putBlock(mino);
							mino = new Tetrimino(bag[0][bagnum]);
							bagnum += 1;
							if(bagnum == 7) {
								getNewBag();
								bagnum = 0;
							}
							int OT = f.checkClear();
							try {
								byte[] as = intToByte(OT);
								OutputStream OS = CS.getOutputStream();
				                OS.write(as);
				                
				                InputStream IS = CS.getInputStream();
				                byte[] bt = new byte[4];
				                
				                IS.read(bt);
				                rt += byteToInt(bt);
				                
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							};
			                System.out.print("> "+rt);
			                f.putTrash(rt);
			                rt = 0;
							
							if(f.checkGameOver())
								gameOver = true;
							updateGUI();
							break;
						}
					}
					break;
				}
				updateGUI(); //화면 업데이트
			}
		}
	}
}
   