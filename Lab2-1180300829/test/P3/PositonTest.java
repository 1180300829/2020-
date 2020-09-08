package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositonTest {

	/* Testing strategy
     * ���Ժ�����ķ���ֵ����
     */
	@Test
	public void testGetx() {
		Position m=new Position(3,3);
		assertEquals(3, m.getx());
	}
	
	/* Testing strategy
     * ����������ķ���ֵ����
     */
	@Test
	public void testGety() {
		Position m=new Position(3,3);
		assertEquals(3, m.gety());
	}
	
	/* Testing strategy
	 * �������Position�Ƿ���ͬ
     * �����������Ƿ���ͬ���֣�������ͬ�����겻ͬ
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testEquals() {
		Position m=new Position(3,3);
		Position n=new Position(3,3);
		Position p=new Position(4,4);
		assertEquals(true, m.equals(n));
		assertEquals(false, m.equals(p));
	}

}
