package debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventManagerTest {

	/* Testing strategy
	 * ����book����
	 * ���ռ����ʱ���ԭ�ȴ��ڵķ�ʽ���֣�ֻ�п�ʼʱ�䣬ֻ�н���ʱ�䣬����ʱ�䶼û�У�����ʱ�䶼��
	 * �����Ƿ�����ص�ʱ��λ��֣������ص�ʱ��Σ�û���ص�ʱ���
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void booktest() {
		assertEquals(1,EventManager.book(1, 1, 2));
		assertEquals(2,EventManager.book(1, 1, 2));
		assertEquals(2,EventManager.book(1, 3, 4));
		assertEquals(3,EventManager.book(1, 1, 6));
		assertEquals(3,EventManager.book(1, 5, 6));
	}

}
