package P3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board {
	private int sizeofboard;
	private final Set<Piece> myboard=new HashSet<>(); 
	public String playerone;
	public String playertwo;
	
	// Abstraction function:
    // AF(sizeofboard)=���̵Ŀ��
	// AF(myboard)=���̵���������
	// AF(playerone)=��һ����������
	// AF(playertwo)=�ڶ�����������
    // Representation invariant:
    // ���ӵ����ֱ������ַ���
    // Safety from rep exposure:
    // ��sizeofboard,myboard����Ϊprivate
    // ����myboard��mutable�������ڷ���ʱ��Ҫ����defensive copies
	

	/**
	 * ��ʼ�����췽�����������̴�С
	 *  @param type ��������
	 */
	public Board(String type) {
		if (type.equals("chess")) {
			sizeofboard = 7; 
		} 
		if (type.equals("go")) {
			sizeofboard = 18;
		}
	}
	
	/**
	 * ����myboard
	 * @return myboard
	 */
	public Set<Piece> getmyboard() {
		return new HashSet<Piece>(myboard);
	}
	
	/**
	 * �ж�ĳλ��P�Ƿ�Խ��
	 * @param P Ҫ�жϵ�λ��
	 * @return δԽ�緵��true ���� false
	 */
	public boolean whethervalidposition(Position P) {
		if (P.getx() < 0 || P.gety() < 0 || P.getx() > sizeofboard || P.gety() > sizeofboard) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * �õ�����ĳ��λ�õ�״̬���ַ��� 
	 * @param P ������λ��
	 * @return ����ĳ��λ��״̬���ַ���
	 */
	public String whethervalidplayer(Position P) {
		if (!whethervalidposition(P)) {
			return "�Ƿ�������ָ��λ�ó������̷�Χ\n";
		}
		Iterator<Piece> temp = myboard.iterator();
		while (temp.hasNext()) {
			Piece a = temp.next();
			if (a.getpieceposition().getx() == P.getx() && a.getpieceposition().gety() == P.gety()) {
				if (a.getwhohave() == 0) {
					return "��λ���ѱ�����" + playerone + "�����ӣ�" + a.getpiecename()+ "ռ��\n";
				} else {
					return "��λ���ѱ�����" + playertwo + "�����ӣ�" + a.getpiecename() + "ռ��\n";
				}
			}
		}
		return "��λ�ÿ���\n";
	}
	
	/**
	 * ͳ���������ֺ��е���������
	 * @return �������ֺ��е������������ַ���
	 */
	public String countplayerpiece() {
		Iterator<Piece> temp = myboard.iterator();
		int countone = 0;
		int counttwo = 0;
		while (temp.hasNext()) {
			Piece a = temp.next();
			if (a.getwhohave() == 0) {
				countone++;
			}
			if (a.getwhohave() == 1) {
				counttwo++;
			}
		}
		return playerone + "ӵ��" + countone + "������\n" + playertwo + "ӵ��" + counttwo + "������\n";
	}

	
	/**
	 * ���ĳ��λ���Ƿ��ǿ���
	 * @param P ������λ��
	 * @return ��λ�ÿ����򷵻�true�����ǿ��з���false
	 */
	public boolean whetherempty(Position P) {
		Iterator<Piece> temp = myboard.iterator();
		while (temp.hasNext()) {
			Piece a = temp.next();
			if (a.getpieceposition().getx() == P.getx() && a.getpieceposition().gety() == P.gety()) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * ����ĳ��������������
	 * @param mypiece �����õ�����
	 * @return ���óɹ�����true ��ָ��λ�ó������̷�Χ��ָ��λ���������ӷ���false
	 */
	public boolean putpiece(Piece mypiece) {
		Position temp=mypiece.getpieceposition();
		int x = temp.getx();
		int y = temp.gety();
		if(x<0||x>sizeofboard||y<0||y>sizeofboard) {
			System.out.println("�Ƿ�������ָ��λ�ó������̷�Χ\n");
			return false;
		}
		Iterator<Piece> tempone=myboard.iterator();
		while(tempone.hasNext()) {
			Piece a=tempone.next();
			if(a.getpieceposition().getx()==x&&a.getpieceposition().gety()==y) {
				System.out.println("�Ƿ�������ָ��λ����������\n");
				return false;
			}
		}
		return myboard.add(mypiece);
	}
	
	/**
	 * �Ƴ�ĳλ�õ�����(����)
	 * @param P ���Ƴ���λ��
	 * @param m �����Լ�
	 * @return �Ƴ��ɹ�����true ��ָ��λ�ó������̷�Χ���������Ӳ��ǶԷ����ӡ���ʼλ�����޿��ƶ������ӷ���false
	 */
	public boolean removepiece(Position P,Player m) {
		if(!whethervalidposition(P)) {
			System.out.println("�Ƿ�������ָ��λ�ó������̷�Χ\n");
			return false;
		}
		Iterator<Piece> tempone = myboard.iterator();
		while (tempone.hasNext()) {
			Piece a = tempone.next();
			if (a.getpieceposition().getx() == P.getx() && a.getpieceposition().gety() == P.gety()) {
				if(m.getmyturn()==a.getwhohave()) {
					System.out.println("�Ƿ��������������Ӳ��ǶԷ�����\n");
					return false;
				}
				tempone.remove();
				System.out.println("��λ�õ��ѱ��Ƴ��ɹ�\n");
				return true;
			}
		}
		System.out.println("�Ƿ���������λ�������ӿ���\n");
		return false;
	}
	
	/**
	 * �ƶ�����
	 * @param player ����
	 * @param p1     ��ʼ��
	 * @param p2     ��ֹ��
	 * @return �ƶ��ɹ�����true��p1λ�ó������̷�Χ��p2λ�ó������̷�Χ��Ŀ�ĵ������������ӡ�
	 * ��ʼλ�����޿��ƶ������ӡ�����λ����ͬ����ʼλ�õ����Ӳ��Ǹ��������з���false
	 */
	public boolean movepiece(Player player,Position p1,Position p2) {
		if(!whethervalidposition(p1)) {
			System.out.println("�Ƿ�������p1λ�ó������̷�Χ\n");
			return false;
		}
		if(!whethervalidposition(p2)) {
			System.out.println("�Ƿ�������p2λ�ó������̷�Χ\n");
			return false;
		}
		if(!whetherempty(p2)&&!(p1.equals(p2))) {
			System.out.println("�Ƿ�������Ŀ�ĵ�������������\n");
			return false;
		}
		if(p1.equals(p2)) {
			System.out.println("�Ƿ�����������λ����ͬ\n");
			return false;
		}
		Iterator<Piece> temp= myboard.iterator();
		while (temp.hasNext()) {
			Piece a = temp.next();
			if (a.getpieceposition().getx() == p1.getx() && a.getpieceposition().gety() == p1.gety()) {
				if (a.getwhohave() != player.getmyturn()) {
					System.out.println("�Ƿ���������ʼλ�õ����Ӳ��Ǹ���������\n");
					return false;
				} else {
					a.createnewposition(p2.getx(),p2.gety());
					System.out.println("�����ƶ��ɹ�\n");
					return true;
				}
			}
		}
		System.out.println("�Ƿ���������ʼλ�����޿��ƶ�������\n");
		return false;
	}
	
	
	/**
	 * ����
	 * @param player ����
	 * @param p1 ��һ��λ��
	 * @param p2 �ڶ���λ��
	 * @return ���ӳɹ�����true��p1λ�ó������̷�Χ��p2λ�ó������̷�Χ������λ����ͬ��
	 * �ڶ���λ���ϵ����Ӳ��ǶԷ����ӡ���һ��λ���ϵ����Ӳ����Լ����ӡ���һ��λ���������ӡ�
	 * �ڶ�λ���������ӷ���false
	 */
	public boolean eatpiece(Player player, Position p1, Position p2) {
		Piece from=null;
		Piece to=null;
		if(!whethervalidposition(p1)) {
			System.out.println("�Ƿ�������p1λ�ó������̷�Χ\n");
			return false;
		}
		if(!whethervalidposition(p2)) {
			System.out.println("�Ƿ�������p2λ�ó������̷�Χ\n");
			return false;
		}
		if(p1.equals(p2)) {
			System.out.println("�Ƿ�����������λ����ͬ\n");
			return false;
		}
		Iterator<Piece> temp= myboard.iterator();
		while (temp.hasNext()) {
			Piece a = temp.next();
			if (a.getpieceposition().getx() == p2.getx() && a.getpieceposition().gety() == p2.gety()) {
				if (a.getwhohave() == player.getmyturn()) {
					System.out.println("�Ƿ��������ڶ���λ���ϵ����Ӳ��ǶԷ�����\n");
					return false;
				} else {
					to=a;
				}
			}
			if (a.getpieceposition().getx() == p1.getx() && a.getpieceposition().gety() == p1.gety()) {
				if (a.getwhohave() != player.getmyturn()) {
					System.out.println("�Ƿ���������һ��λ���ϵ����Ӳ����Լ�����\n");
					return false;
				} else {
					from=a;
				}
			}
		}
		if (from == null) {
			System.out.println("�Ƿ���������һ��λ����������\n");
			return false;
		}
		if (to == null) {
			System.out.println("�Ƿ��������ڶ�λ����������\n");
			return false;
		}
		myboard.remove(to);
		from.createnewposition(p2.getx(),p2.gety());
		System.out.println("���ӳɹ�\n");
		return true;
	}
	
}
