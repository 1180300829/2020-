package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	/* Testing strategy
     * ��������˳��ֵ�ķ���ֵ����
     */
	@Test
	public void testGetmyturn() {
		Player m=new Player();
		Integer a=4;
		m.setmyturn(a);
		assertEquals(a,m.getmyturn());
	}
	
	/* Testing strategy
     * �����������ֵķ���ֵ����
     */
	@Test
	public void testGetname() {
		Player m=new Player();
		m.setname("yutao");
		assertEquals("yutao",m.getname());
	}

}
