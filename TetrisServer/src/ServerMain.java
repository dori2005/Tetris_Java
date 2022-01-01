import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
class userThread extends Thread {
 
    Socket SS;
    boolean start = false;
    int ID;
    static int count = 0;
    static int Dcount = 0;
 
    userThread(Socket SS) {
        this.SS = SS;
        if(count == 1) {
        	ID = 2;
        	count += ID;
        } else {
        	ID = 1;
        	count += ID;
        }
        System.out.println("--" + ID + "번째 유저 로그인");
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

    @Override
    public void run() {
        try {
            while (true) {
            	if(count < 3)
            		continue;
            	if(start == false) {
                    byte[] as = intToByte(1);
                    OutputStream OS = SS.getOutputStream();
                    OS.write(as);
                    OS.flush();
                    start = true;
                    System.out.println("시작!");
                    continue;
            	}
                InputStream IS = SS.getInputStream();
                byte[] bt = new byte[4];
                int trash = 0;
                int size = IS.read(bt);
                int damage = byteToInt(bt);
                
                if(size != 4)
                	System.out.println(size);
                if(ID == 1) {
                	Dcount -= damage;
                    System.out.println("1>damage>2 " + Dcount);
                    if(Dcount > 0) {
                    	trash = Dcount;
                    	Dcount = 0;
                    } else
                    	trash = 0;
                }
                else if(ID == 2) {
                	Dcount += damage;
                    System.out.println("2>damage>1 " + Dcount);
                    if(Dcount < 0) {
                    	trash = -Dcount;
                    	Dcount = 0;
                    } else
                    	trash = 0;
                }

                byte[] as = intToByte(trash);
                OutputStream OS = SS.getOutputStream();
                OS.write(as);
            }
        } catch (IOException e) {
            System.out.println("--" + ID + "번 유저 종료");
            count -= ID;
        }
    }
 
}//
 
class connectThread extends Thread {
 
    ServerSocket MSS;
 
    connectThread(ServerSocket MSS) {
        this.MSS = MSS;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                Socket SS = MSS.accept();
 
                userThread ust = new userThread(SS);
                ust.start();
            }
 
        } catch (IOException e) {
            System.out.println("--서버 종료");
        }
    }
}//
 
public class ServerMain {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ServerSocket MSS = null;
 
        try {
            MSS = new ServerSocket();
            MSS.bind(new InetSocketAddress("172.30.1.35", 8080));
 
            System.out.println("서버를 닫으려면 정수를 입력하십시오!");
            System.out.println("플레이어를 기다리는 중....");
            connectThread cnt1 = new connectThread(MSS);
            cnt1.start();
 
            int temp = input.nextInt();
 
        } catch (Exception e) {
            System.out.println(e);
        }
 
        try {
            MSS.close();
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }// MAIN
}
//import java.io.*;
//import java.net.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class ServerMain {
//	public static void main(String[] args) {
//		BufferedReader inA = null;
//		BufferedWriter outA = null;
//		BufferedReader inB = null;
//		BufferedWriter outB = null;
//		ServerSocket listener = null;
//		Socket socketA = null;
//		Socket socketB = null;
//			try {
//				listener = new ServerSocket(8080);
//				System.out.println(getTime()+"서버가 준비되었습니다.");
//				System.out.println(getTime()+"연결요청을 기다립니다.");
//				socketA = listener.accept();
//				System.out.println(getTime()+"첫 번째 사용자가 연결됐습니다.");
//				
//				socketB = listener.accept();
//				System.out.println(getTime()+"두 번째 사용자가 연결됐습니다.");
//	
//				inA = new BufferedReader(new InputStreamReader(socketA.getInputStream()));
//				outA = new BufferedWriter(new OutputStreamWriter(socketA.getOutputStream()));
//				
//				inB = new BufferedReader(new InputStreamReader(socketB.getInputStream()));
//				outB = new BufferedWriter(new OutputStreamWriter(socketB.getOutputStream()));
//	
//				outA.write('r');
//				outA.flush();
//				outB.write('r');
//				outB.flush();
//				
//				
//				System.out.println(getTime()+"첫 번째 사용자가 종료 대기 중");
//				String inputMessageA = inA.readLine();
//	
//				int Ascore = 0, d = 1;
//				for(int i = inputMessageA.length()-1; i >= 0 ; i--) {
//					Ascore += (int)(inputMessageA.charAt(i)-'0') * d;
//					d *= 10;
//				}
//				System.out.println("A : "+Ascore);
//				
//				System.out.println(getTime()+"두 번째 사용자가 종료 대기 중");
//				String inputMessageB = inB.readLine();
//				
//				int Bscore = 0;
//				d = 1;
//				for(int i = inputMessageB.length()-1; i >= 0 ; i--) {
//					Bscore += (int)(inputMessageB.charAt(i)-'0') * d;
//					d *= 10;
//				}
//				System.out.println("B : "+Bscore);
//				
//				if(Ascore > Bscore) {
//					System.out.println(getTime()+"첫 번째 사용자의 승리");
//					outA.write("Win");
//					outA.flush();
//					outB.write("Lose");
//					outB.flush();
//				}else if(Ascore < Bscore) {
//					System.out.println(getTime()+"두 번째 사용자의 승리");
//					outA.write("Lose");
//					outA.flush();
//					outB.write("Win");
//					outB.flush();
//				}else {
//					System.out.println(getTime()+"무승부");
//					outA.write("draw");
//					outA.flush();
//					outB.write("draw");
//					outB.flush();
//				}
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					socketA.close();
//					socketB.close();
//					listener.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//	}
//	static String getTime() {
//		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
//		return f.format(new Date());
//	}
//}
