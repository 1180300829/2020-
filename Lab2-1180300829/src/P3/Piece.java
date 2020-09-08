package P3;

public class Piece {
	private final String piecename; //���ӵ�����
	private Position pieceposition; //���ӵ�λ��
	private final int whohave;  //�������ڵ�����

	// Abstraction function:
    // AF(piecename)=���ӵ�����
	// AF(piecename)=���������̵�λ��
	// AF(whohave)=�������ڵ�����
    // Representation invariant:
    // ���ӵ����ֱ������ַ������������ڵ����ֱ�����0����1
    // Safety from rep exposure:
    // ��piecename,pieceposition,whohave����Ϊprivate
    // ����piecename,pieceposition,whohave��inmutable�������ڷ���ʱ����Ҫ����defensive copies
	
	/**
	 * ��ʼ�����췽��
	 * @param pieceName  ���ӵ�����
	 * @param owner      ���ӵ�������
	 */
	public Piece(String pieceName,int whoHave) {
		this.piecename = pieceName;
		this.whohave=whoHave;
	}
	
	/**
	 * �������ӵ�������
	 * @return ���ӵ�������
	 */
	public int getwhohave() {
		return whohave;
	}

	/**
	 * �������ӵ�����
	 * @return ���ӵ�����
	 */
	public String getpiecename() {
		return this.piecename;
	}

	/**
	 * �������ӵ�λ��
	 * @return ���ӵ�λ��
	 */
	public Position getpieceposition() {
		return this.pieceposition;
	}
	
	/**
	 * ��������µ�����λ��
	 * @param x �����µĺ�����
	 * @param y �����µ�������
	 */
	public void createnewposition(Integer x,Integer y) {
		this.pieceposition=new Position(x,y);
	}
}
