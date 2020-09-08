package P3;

import java.util.ArrayList;


public class Action {
	private final ArrayList<String> allhistory=new ArrayList<>();
	int counthistory=0;

	// Abstraction function:
    // AF(allhistory)=������ʷ
	// AF(counthistory)=����Ĵ���
    // Safety from rep exposure:
    // �� allhistory����Ϊprivate
	
	/**
	 * �õ�����ĳ��λ�õ�״̬���ַ��� 
	 * @param board ����
	 * @param position ������λ��
	 * @return ����ĳ��λ��״̬���ַ���
	 */
	public String whethervalidplayer(Board board, Position position) {
		return board.whethervalidplayer(position);
	}
	
	/**
	 *  ͳ���������ֺ��е���������
	 * @param board ����������
	 * @return �������ֺ��е������������ַ���
	 */
	public String countplayerpiece(Board board) {
		return board.countplayerpiece();
	}
	
	/**
	 * �����¼�������̵�����
	 */
	public void printhistory() {
		for (String m : allhistory) {
			System.out.println(m);
		}
	}
	
	/**
	 * ��������ĳ��������������
	 * @param piece �����õ�����
	 * @param board ����
	 * @param player ����
	 * @return ���óɹ�����true ��ָ��λ�ó������̷�Χ��ָ��λ���������ӡ�
	 * �����Ӳ������ڸ����֣���ָ���������Ѿ��������Ϸ���false
	 */
	public boolean putpiece(Player player, Board board, Piece piece) {
		if(piece.getwhohave()!=player.getmyturn()) {
			System.out.println("�Ƿ������������Ӳ������ڸ�����\n");
			return false;
		}
		if(board.getmyboard().contains(piece)) {
			System.out.println("�Ƿ���������ָ���������Ѿ���������\n");
			return false;
		}
		boolean flag=board.putpiece(piece);
		if(flag) {
			allhistory.add(counthistory + "." + player.getname() + "������һ������" + piece.getpiecename() + "������Ϊ ("
					+ piece.getpieceposition().getx() + "," + piece.getpieceposition().gety() + ")\n");
			counthistory++;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * �Ƴ�ĳλ�õ�����(����)
	 * @param position ���Ƴ���λ��
	 * @param board ����
	 * @param player ����
	 * @return �Ƴ��ɹ�����true ��ָ��λ�ó������̷�Χ���������Ӳ��ǶԷ����ӡ���ʼλ�����޿��ƶ������ӷ���false
	 */
	public boolean removepiece(Player player, Board board, Position position) {
		boolean flag=board.removepiece(position,player);
		if(flag) {
			allhistory.add(counthistory + "." + player.getname() + "�Ƴ���һ������(����)"  + "������Ϊ ("
					+ position.getx() + "," + position.gety() + ")\n");
			counthistory++;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * �ƶ�����
	 * @param player ����
	 * @param board ����
	 * @param p1 ��ʼλ��
	 * @param p2 ��ֹλ��
	 * @return �ƶ��ɹ�����true��p1λ�ó������̷�Χ��p2λ�ó������̷�Χ��Ŀ�ĵ������������ӡ�
	 * ��ʼλ�����޿��ƶ������ӡ�����λ����ͬ����ʼλ�õ����Ӳ��Ǹ��������з���false
	 */
	public boolean movepiece(Player player, Board board, Position p1, Position p2) {
		boolean flag = board.movepiece(player, p1, p2);
		if (flag) {
			allhistory.add(counthistory + "." + player.getname() + " �ƶ���һ������λ�ô�(" + p1.getx() + ","
					+ p1.gety() + ")��(" + p2.getx() + "," + p2.gety() + ")\n");
			counthistory++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ����
	 * @param player ����
	 * @param board ����
	 * @param p1 ��һ��λ��
	 * @param p2 �ڶ���λ��
	 * @return ���ӳɹ�����true��p1λ�ó������̷�Χ��p2λ�ó������̷�Χ������λ����ͬ��
	 * �ڶ���λ���ϵ����Ӳ��ǶԷ����ӡ���һ��λ���ϵ����Ӳ����Լ����ӡ���һ��λ���������ӡ�
	 * �ڶ�λ���������ӷ���false
	 */
	public boolean eatpiece(Player player, Board board, Position p1, Position p2) {
		boolean flag = board.eatpiece(player, p1, p2);
		if (flag) {
			allhistory.add(counthistory + "." + player.getname() + " ��һ��λ��(" + p1.getx() + ","
					+ p1.gety() + ")�Ե���(" + p2.getx() + "," + p2.gety() + ")λ�õ�����\n");
			counthistory++;
			return true;
		} else {
			return false;
		}
	}
	
	
}
