package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {

	/* Testing strategy
     * ����������������˳���ֵ�ķ���ֵ����
     */
	@Test
	public void testGetwhohave() {
		Piece m=new Piece("yutao",0);
		assertEquals(0,m.getwhohave());
	}
	
	/* Testing strategy
     * �����������ֵķ���ֵ����
     */
	@Test
	public void testGetpiecename() {
		Piece m=new Piece("yutao",0);
		assertEquals("yutao",m.getpiecename());
	}

	/* Testing strategy
     * �������ֵ�λ�õķ���ֵ����
     */
	@Test
	public void testGetpieceposition() {
		Piece m=new Piece("yutao",0);
		m.createnewposition(3, 4);
		assertEquals(3,m.getpieceposition().getx());
		assertEquals(4,m.getpieceposition().gety());
	}
	
}
