package P3;

public class Game {
	private Board myboard;
	private final Action myaction=new Action();
	private final Player playerone=new Player();
	private final Player playertwo=new Player();
	
	// Abstraction function:
    // AF(myboard)=����
	// AF(myaction)=���̵����в���
	// AF(playerone)=��һ������
	// AF(playertwo)=�ڶ�������
    // Representation invariant:
    // �������������ֻ����"chess"����"go"
    // Safety from rep exposure:
    // �����г�Ա����Ϊprivate
    // ����myboard,playerone,playertwo��inmutable�������ڷ���ʱ����Ҫ����defensive copies
	
	/**
	 * ���췽������ʼ������
	 * @param type ����ʼ������������
	 */
	public Game(String type) {
		if(type.equals("chess")) { //��������
			myboard=new Board("chess");
			//����
			Piece kinga=new Piece("King0",0);
			Piece kingb=new Piece("King1",1);
			kinga.createnewposition(4, 0);
			kingb.createnewposition(4, 7);
			//�ʺ�
			Piece Queena=new Piece("Queen0",0);
			Piece Queenb=new Piece("Queen1",1);
			Queena.createnewposition(3, 0);
			Queenb.createnewposition(3, 7);
			//ս��
			Piece Rooka1=new Piece("Rook0",0);
			Piece Rooka2=new Piece("Rook0",0);
			Piece Rookb1=new Piece("Rook1",1);
			Piece Rookb2=new Piece("Rook1",1);
			Rooka1.createnewposition(0, 0);
			Rooka2.createnewposition(7, 0);
			Rookb1.createnewposition(0, 7);
			Rookb2.createnewposition(7, 7);
			//��ʿ
			Piece Knighta1=new Piece("Knight0",0);
			Piece Knighta2=new Piece("Knight0",0);
			Piece Knightb1=new Piece("Knight1",1);
			Piece Knightb2=new Piece("Knight1",1);
			Knighta1.createnewposition(1, 0);
			Knighta2.createnewposition(6, 0);
			Knightb1.createnewposition(1, 7);
			Knightb2.createnewposition(6, 7);
			//����
			Piece Bishopa1=new Piece("Bishop0",0);
			Piece Bishopa2=new Piece("Bishop0",0);
			Piece Bishopb1=new Piece("Bishop1",1);
			Piece Bishopb2=new Piece("Bishop1",1);
			Bishopa1.createnewposition(2, 0);
			Bishopa2.createnewposition(5, 0);
			Bishopb1.createnewposition(2, 7);
			Bishopb2.createnewposition(5, 7);
			//������
			Piece Pawna1=new Piece("Pawn0",0);
			Piece Pawna2=new Piece("Pawn0",0);
			Piece Pawna3=new Piece("Pawn0",0);
			Piece Pawna4=new Piece("Pawn0",0);
			Piece Pawna5=new Piece("Pawn0",0);
			Piece Pawna6=new Piece("Pawn0",0);
			Piece Pawna7=new Piece("Pawn0",0);
			Piece Pawna8=new Piece("Pawn0",0);
			Piece Pawnb1=new Piece("Pawn1",1);
			Piece Pawnb2=new Piece("Pawn1",1);
			Piece Pawnb3=new Piece("Pawn1",1);
			Piece Pawnb4=new Piece("Pawn1",1);
			Piece Pawnb5=new Piece("Pawn1",1);
			Piece Pawnb6=new Piece("Pawn1",1);
			Piece Pawnb7=new Piece("Pawn1",1);
			Piece Pawnb8=new Piece("Pawn1",1);
			Pawna1.createnewposition(0, 1);
			Pawna2.createnewposition(1, 1);
			Pawna3.createnewposition(2, 1);
			Pawna4.createnewposition(3, 1);
			Pawna5.createnewposition(4, 1);
			Pawna6.createnewposition(5, 1);
			Pawna7.createnewposition(6, 1);
			Pawna8.createnewposition(7, 1);
			Pawnb1.createnewposition(0, 6);
			Pawnb2.createnewposition(1, 6);
			Pawnb3.createnewposition(2, 6);
			Pawnb4.createnewposition(3, 6);
			Pawnb5.createnewposition(4, 6);
			Pawnb6.createnewposition(5, 6);
			Pawnb7.createnewposition(6, 6);
			Pawnb8.createnewposition(7, 6);
			//���������ӷ�����������
			myboard.putpiece(kinga);
			myboard.putpiece(kingb);
			
			myboard.putpiece(Queena);
			myboard.putpiece(Queenb);
			
			myboard.putpiece(Rooka1);
			myboard.putpiece(Rooka2);
			myboard.putpiece(Rookb1);
			myboard.putpiece(Rookb2);
			
			myboard.putpiece(Knighta1);
			myboard.putpiece(Knighta2);
			myboard.putpiece(Knightb1);
			myboard.putpiece(Knightb2);
			
			myboard.putpiece(Bishopa1);
			myboard.putpiece(Bishopa2);
			myboard.putpiece(Bishopb1);
			myboard.putpiece(Bishopb2);
			
			myboard.putpiece(Pawna1);
			myboard.putpiece(Pawna2);
			myboard.putpiece(Pawna3);
			myboard.putpiece(Pawna4);
			myboard.putpiece(Pawna5);
			myboard.putpiece(Pawna6);
			myboard.putpiece(Pawna7);
			myboard.putpiece(Pawna8);
			myboard.putpiece(Pawnb1);
			myboard.putpiece(Pawnb2);
			myboard.putpiece(Pawnb3);
			myboard.putpiece(Pawnb4);
			myboard.putpiece(Pawnb5);
			myboard.putpiece(Pawnb6);
			myboard.putpiece(Pawnb7);
			myboard.putpiece(Pawnb8);		
		}
		if(type.equals("go")) {  //Χ��
			myboard=new Board("go");
		}
	}
	
	/**
	 * ��ʼ������
	 * @param a ����a������
	 * @param b ����b������
	 */
	public void setplayernames(String a,String b) {
		this.playerone.setname(a);
		this.playertwo.setname(b);
		this.playerone.setmyturn(0);
		this.playertwo.setmyturn(1);
		myboard.playerone=a;
		myboard.playertwo=b;
	}
	
	/**
	 * ��������
	 * @return ����
	 */
	public Board getmyboard() {
		return myboard;
	}

	/**
	 * ��������A
	 * @return ����A
	 */
	public Player getplayerone() {
		return playerone;
	}

	/**
	 * ��������B
	 * @return ����B
	 */
	public Player getplayertwo() {
		return playertwo;
	}

	/**
	 * �õ���ǰ�غϵ�����
	 * @param turn �غϵı�ʶ��
	 * @return ��ǰ�غ�Ӧ���ж�������
	 */
	public Player getplayer(int myturn) {
		if (myturn == 0) {
			return playerone;
		} else {
			return playertwo;
		}
	}
	
	/**
	 * �õ�����ĳ��λ�õ�״̬���ַ��� 
	 * @param P ������λ��
	 * @return ����ĳ��λ��״̬���ַ���
	 */
	public String whethervalidplayer(Position P) {
		return myaction.whethervalidplayer(myboard, P);

	}

	/**
	 * ͳ���������ֺ��е���������
	 * @return �������ֺ��е������������ַ���
	 */
	public String countplayerpiece() {
		return myaction.countplayerpiece(myboard);
	}

	/**
	 * �����¼�������̵�����
	 */
	public void printhistory() {
		myaction.printhistory();
	}
	
	/**
	 * ��������ĳ��������������
	 * @param player ����
	 * @param piece ����
	 * @param P �����õ�λ��
	 * @return ���óɹ�����true ��ָ��λ�ó������̷�Χ��ָ��λ���������ӡ�
	 * �����Ӳ������ڸ����ַ���false
	 */
	public boolean putpiece(Player player, Piece piece, Position P) {
		piece.createnewposition(P.getx(), P.gety());
		return myaction.putpiece(player, myboard, piece);
	}
	
	/**
	 * �Ƴ�ĳλ�õ�����(����)
	 * @param player �����Լ�
	 * @param P ���Ƴ���λ��
	 * @return �Ƴ��ɹ�����true ��ָ��λ�ó������̷�Χ���������Ӳ��ǶԷ����ӡ���ʼλ�����޿��ƶ������ӷ���false
	 */
	public boolean removepiece(Player player, Position P) {
		return myaction.removepiece(player, myboard, P);
	}
	
	/**
	 * �ƶ�����
	 * @param player ����
	 * @param p1 ��ʼλ��
	 * @param p2 ��ֹλ��
	 * @return �ƶ��ɹ�����true��p1λ�ó������̷�Χ��p2λ�ó������̷�Χ��Ŀ�ĵ������������ӡ�
	 * ��ʼλ�����޿��ƶ������ӡ�����λ����ͬ����ʼλ�õ����Ӳ��Ǹ��������з���false
	 */
	public boolean movepiece(Player player, Position p1, Position p2) {
		return myaction.movepiece(player, myboard, p1, p2);
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
		return myaction.eatpiece(player, myboard, p1, p2);
	}

	
	
}
