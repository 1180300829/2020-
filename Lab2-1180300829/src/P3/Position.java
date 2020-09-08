package P3;

public class Position {
	private int x;  //���ӵĺ�����
	private int y;  //���ӵ�������
	
	// Abstraction function:
    // AF(x)=���Ӻ�����
	// AF(y)=����������
    // Representation invariant:
    // �������������
    // Safety from rep exposure:
    // ��x,y����Ϊprivate
    // ����x,y��inmutable�������ڷ���ʱ����Ҫ����defensive copies
	
	/**
	 * ��ʼ�����췽��
	 * @param ix x����
	 * @param iy y����
	 */
	public Position(int ix,int iy) {
		this.x=ix;
		this.y=iy;
	}
	
	/**
	 * ���ص�ǰ��ĺ�����
	 * @return ������x
	 */
	public int getx() {
		return x;
	}
	
	/**
	 * ���ص�ǰ���������
	 * @return ������y
	 */
	public int gety() {
		return y;
	}
	
	/**
	 * ��дeuqals���ж�����Position�Ƿ����
	 * @param m ���ж�λ��
	 * @return �Ƿ���ȵ��ж�
	 */
	public boolean equals(Position m) {
		if(m.x==this.x&&m.y==this.y) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
