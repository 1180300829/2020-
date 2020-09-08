package P3;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;



public class MyChessAndGoGame {
	public static Game mygame;
	private static String tempchess[][]=new String[8][8];
	private static String tempgo[][]=new String[19][19];
	
	// Abstraction function:
    // AF(mygame)=���������Χ����Ϸ
	// AF(tempchess)=������������
	// AF(tempgo)=Χ������
    // Representation invariant:
    // �û�����ı�����"chess"����"go"
    // Safety from rep exposure:
    // ��mygame,tempchess,tempgo����Ϊprivate

	/**
	 * ��ʼ�����̵�����ȫΪo
	 */
	public static void empty() {
		int i, j;
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				tempchess[i][j]="o";
			}
		}
		for(i=0;i<19;i++) {
			for(j=0;j<19;j++) {
				tempgo[i][j]="o";
			}
		}
	}
	
	/**
	 * ��������˵�
	 */
	public static void chessmenu(){
		System.out.println("��ѡ����Ĳ���(�������ֱ�ż���,����end������Ϸ)��");
		System.out.println("1.�ƶ�������ĳ��λ�õ���������λ��");
		System.out.println("2.���ӣ�ʹ�ü������ӳԵ��������ӣ�");
		System.out.println("3.��ѯĳ��λ�õ�ռ����������У����߱���һ����ʲô������ռ�ã�");
		System.out.println("4.����������ҷֱ��������ϵ���������");
		System.out.println("5.����");
		System.out.println("end.�����˴ζԾ�");
		
	}

	/**
	 * Χ��˵�
	 */
	public static void gomenu() {
		System.out.println("��ѡ����Ĳ���(�������ֱ�ż���,����end������Ϸ)��");
		System.out.println("1.����δ�������ϵ�һ�����ӷ��������ϵ�ָ��λ��");
		System.out.println("2.���ӣ��Ƴ����ֵ����ӣ�");
		System.out.println("3.��ѯĳ��λ�õ�ռ����������У����߱���һ����ʲô������ռ�ã�");
		System.out.println("4.������������������ϵ���������");
		System.out.println("5.����");
		System.out.println("end.�����˴ζԾ�");
	}
	
	/**
	 * ��ӡ�������������
	 */
	public static void printchess() {
		Set<Piece> mm=mygame.getmyboard().getmyboard();
		int i,j;
		empty();
		Iterator<Piece> temp=mm.iterator();
		while(temp.hasNext()) {
			Piece a=temp.next();
			tempchess[a.getpieceposition().gety()][a.getpieceposition().getx()]=a.getpiecename();
		}
		System.out.println("�������£�");
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				System.out.print(tempchess[i][j]+"\t");
			}
			System.out.print("\n");
		}	
	}
	
	/**
	 * ��ӡΧ�������
	 */
	public static void printgo() {
		Set<Piece> mm=mygame.getmyboard().getmyboard();
		int i,j;
		empty();
		Iterator<Piece> temp=mm.iterator();
		while(temp.hasNext()) {
			Piece a=temp.next();
			tempgo[a.getpieceposition().gety()][a.getpieceposition().getx()]=a.getpiecename();
		}
		System.out.println("�������£�");
		for(i=0;i<19;i++) {
			for(j=0;j<19;j++) {
				System.out.print(tempgo[i][j]+"\t");
			}
			System.out.print("\n");
		}	
	}
	
	public static void main(String[] args) {
		String type;
		Scanner scanner=new Scanner(System.in);
		while(true) {  //�ж���Ϸ���ͣ�����chess��go֮�����Ч
			System.out.println("�����������Ϸ����:����chessΪ�������壬����goΪΧ��");
			type=scanner.nextLine();
			if(type.equals("chess")||type.equals("go")) {
				mygame=new Game(type);
				break;
			}
		}
		System.out.println("�������һλ���ֵ�����");
		String playeronename=scanner.nextLine();
		System.out.println("������ڶ�λ���ֵ�����");
		String playertwoname=scanner.nextLine();
		mygame.setplayernames(playeronename, playertwoname);
		if(type.equals("chess")) {
			System.out.println("�������������ʽ��ʼ!\n");
			chessgame();
		}
		if(type.equals("go")) {
			System.out.println("Χ�������ʽ��ʼ!\n");
			gogame();
		}
	}
	
	/**
	 * ������������Ľ���Ͳ���
	 */
	public static void chessgame() {
		String choice,x1,y1,x2,y2,watch;
		Scanner scanner=new Scanner(System.in);
		String[] temp,tempone,temptwo;
		int turn=0;
		boolean flag=true;
		empty();
		printchess();
		while(flag) {
			System.out.println("�����ֵ�" + mygame.getplayer(turn).getname() + "�Ļغ�");
			chessmenu();
			choice=scanner.nextLine();
			switch(choice) {
			case "1": //�ƶ�������ĳ��λ�õ���������λ��
				System.out.println("ѡ��" + mygame.getplayer(turn).getname()+"���" + "����������Ҫ�ƶ�ǰ����������ӵ�����x1,y1��x2,y2����һ�����ӵĺ��������ö��Ÿ�������������֮���ÿո������");
				temp=(scanner.nextLine()).split(" ");
				tempone=temp[0].split(",");
				temptwo=temp[1].split(","); //���β�ֵõ���������
				x1=tempone[0];
				y1=tempone[1];
				x2=temptwo[0];
				y2=temptwo[1];
				if(temp.length==2&&tempone.length==2&&temptwo.length==2) {
					Position p1=new Position(Integer.valueOf(x1),Integer.valueOf(y1));
					Position p2=new Position(Integer.valueOf(x2),Integer.valueOf(y2));
					if(mygame.movepiece(mygame.getplayer(turn),p1,p2)) {
						turn=(turn+1)%2;
						//System.out.println("�ƶ����ӳɹ�\n");
						printchess();
					}
				}
				else {
					System.out.println("���������ʽ����\n");
				}
				break;
			case "2": //���ӣ�ʹ�ü������ӳԵ��������ӣ�
				System.out.println("ѡ��" + mygame.getplayer(turn).getname()+"���" + "��������ļ������ӵ�����x1,y1��Ҫ�Ե��ĶԷ���������x2,y2����һ�����ӵĺ��������ö��Ÿ�������������֮���ÿո������");
				temp=(scanner.nextLine()).split(" ");
				tempone=temp[0].split(",");
				temptwo=temp[1].split(","); //���β�ֵõ���������
				x1=tempone[0];
				y1=tempone[1];
				x2=temptwo[0];
				y2=temptwo[1];
				if(temp.length==2&&tempone.length==2&&temptwo.length==2) {
					Position p1=new Position(Integer.valueOf(x1),Integer.valueOf(y1));
					Position p2=new Position(Integer.valueOf(x2),Integer.valueOf(y2));
					if(mygame.eatpiece(mygame.getplayer(turn),p1,p2)) {
						turn=(turn+1)%2;
						//System.out.println("���ӳɹ�\n");
						printchess();
					}
				}
				else {
					System.out.println("���������ʽ����\n");
				}
				break;
            case "3": //��ѯĳ��λ�õ�ռ����������У����߱���һ����ʲô������ռ�ã�
            	System.out.println("ѡ��" + mygame.getplayer(turn).getname()+"���" + "����������Ҫ��ѯ������λ������x,y�������������Զ��Ÿ�����");
            	temp=(scanner.nextLine()).split(",");
            	x1=temp[0];
				y1=temp[1];
            	if(temp.length==2) {
            		Position p=new Position(Integer.valueOf(x1),Integer.valueOf(y1));
            		System.out.println(mygame.whethervalidplayer(p));
            	}
            	else {
            		System.out.println("���������ʽ����\n");
            	}
				break;
            case "4": //������������������ϵ���������
            	System.out.println(mygame.countplayerpiece());
            	break;
            case "5": //����
            	System.out.println("ѡ��"+ mygame.getplayer(turn).getname()+"����\n");
            	turn=(turn+1)%2;
            	break;
            case "end": //�����˴ζԾ�
            	System.out.println("���������������\n");
            	flag=false;
            	break;
            default:
            	System.out.println("��ִ����ȷ����\n");
				break;  		
			}	
		}
		System.out.println("�Ƿ�鿴���α�����������ʷ��(����yes��no)");
		watch=scanner.nextLine();
		switch(watch){
		case "yes":
			mygame.printhistory();
			break;
		case "no":
			break;
		}
		System.out.println("���˳�����\n");
		
	}
	
	/**
	 * Χ������Ľ���Ͳ���
	 */
    public static void gogame() {
    	String choice,x1,y1,watch;
		Scanner scanner=new Scanner(System.in);
		String[] temp;
		String[] color=new String[2];
		color[0] = "white";
		color[1] = "black";
		int turn=0;
		boolean flag=true;
		empty();
		printgo();
		while(flag) {
			System.out.println("�����ֵ�" + mygame.getplayer(turn).getname() + "�Ļغ�");
			gomenu();
			choice=scanner.nextLine();
			switch(choice) {
			case "1": //����δ�������ϵ�һ�����ӷ��������ϵ�ָ��λ��
				System.out.println("ѡ��" + mygame.getplayer(turn).getname()+"���" + "����������Ҫ���������ϵ����ӵ�����x,y�����ӵĺ��������ö��Ÿ�����");
				temp=(scanner.nextLine()).split(",");
            	x1=temp[0];
				y1=temp[1];
				if(temp.length==2) {
					Position p=new Position(Integer.valueOf(x1),Integer.valueOf(y1));
					Piece createpiece=new Piece(color[turn],turn);
					if(mygame.putpiece(mygame.getplayer(turn),createpiece,p)) {
						turn=(turn+1)%2;
						System.out.println("�������ӳɹ�\n");
						printgo();
					}
				}
				else {
					System.out.println("���������ʽ����\n");
				}
				break;
			case "2": //���ӣ��Ƴ����ֵ����ӣ�
				System.out.println("ѡ��" + mygame.getplayer(turn).getname()+"���" + "����������Ҫ�Ƴ��Է����ӵ�����x,y�����ӵĺ��������ö��Ÿ�����");
				temp=(scanner.nextLine()).split(",");
            	x1=temp[0];
				y1=temp[1];
				if(temp.length==2) {
					Position p=new Position(Integer.valueOf(x1),Integer.valueOf(y1));
					if(mygame.removepiece(mygame.getplayer(turn), p)) {
						turn=(turn+1)%2;
						//System.out.println("�Ƴ��Է����ӳɹ�\n");
						printgo();
					}
				}
				else {
					System.out.println("���������ʽ����\n");
				}
				break;
            case "3": //��ѯĳ��λ�õ�ռ����������У����߱���һ����ʲô������ռ�ã�
            	System.out.println("ѡ��" + mygame.getplayer(turn).getname()+"���" + "����������Ҫ��ѯ������λ������x,y�������������Զ��Ÿ�����");
            	temp=(scanner.nextLine()).split(",");
            	x1=temp[0];
				y1=temp[1];
            	if(temp.length==2) {
            		Position p=new Position(Integer.valueOf(x1),Integer.valueOf(y1));
            		System.out.println(mygame.whethervalidplayer(p));
            	}
            	else {
            		System.out.println("���������ʽ����\n");
            	}
				break;
            case "4": //������������������ϵ���������
            	System.out.println(mygame.countplayerpiece());
            	break;
            case "5": //����
            	System.out.println("ѡ��"+ mygame.getplayer(turn).getname()+"����\n");
            	turn=(turn+1)%2;
            	break;
            case "end": //�����˴ζԾ�
            	System.out.println("���������������\n");
            	flag=false;
            	break;
            default:
            	System.out.println("��ִ����ȷ����\n");
				break;  		
			}	
		}
		System.out.println("�Ƿ�鿴���α�����������ʷ��(����yes��no)\n");
		watch=scanner.nextLine();
		switch(watch){
		case "yes":
			mygame.printhistory();
			break;
		case "no":
			break;
		}
		System.out.println("���˳�����\n");
	}
}
