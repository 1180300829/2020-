package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class AcitionTest {

	/* Testing strategy
	 * ����putpiece�������ڽ�Boardʱ���ֵȼ��������Χ�����ֻ����һ�ּ���
     * ���ռ������ӵ������Ƿ�Խ�绮�֣�����Խ�磬���겻Խ��
     * ���ռ������ӵ������Ƿ��Ѿ��������ӻ��֣�ָ��λ���������ӣ�ָ��λ��û������
     * �������ӻ��֣������Ӳ������ڸ����֣���ָ���������Ѿ���������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testPutpiece() {
		Action pp=new Action();
		Board m=new Board("chess");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Piece c=new Piece("white",0);
		Piece d=new Piece("black",1);
		a.createnewposition(99, 99);
		b.createnewposition(2, 2);
		c.createnewposition(3, 3);
		d.createnewposition(3, 3);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		assertEquals(false,pp.putpiece(num1,m,a));
		assertEquals(false,pp.putpiece(num1,m,b));
		assertEquals(true,pp.putpiece(num1,m,c));
		assertEquals(false,pp.putpiece(num2,m,d));
		assertEquals(false,pp.putpiece(num1,m,c));
	}
	
	/* Testing strategy
	 * ����removepiece�������ڽ�Boardʱ���ֵȼ���ֻ�����Χ�弴��
     * ���մ��Ƴ��������Ƿ�Խ�绮�֣�����Խ�磬���겻Խ��
     * ���մ��Ƴ����ӵ����껮�֣�ָ��λ�ó������̷�Χ���������Ӳ��ǶԷ����ӣ���ʼλ�����޿��ƶ������ӣ��Ƴ��ɹ�
     * �������ֻ��֣���һ�������Ƴ��ڶ����˵����ӣ��ڶ������Ƴ���һ���˵�����
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testRemovepiece() {
		Action pp=new Action();
		Board m=new Board("go");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		a.createnewposition(1, 1);
		b.createnewposition(2, 2);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		pp.putpiece(num1,m,a);
		pp.putpiece(num2,m,b);
		Position temp=new Position(99,99);
		Position temp1=new Position(4,4);
		assertEquals(false,pp.removepiece(num1,m,a.getpieceposition()));
		assertEquals(false,pp.removepiece(num1,m,temp));
		assertEquals(false,pp.removepiece(num1,m,temp1));
		assertEquals(true,pp.removepiece(num1,m,b.getpieceposition()));
		assertEquals(true,pp.removepiece(num2,m,a.getpieceposition()));
	}

	/* Testing strategy
	 * ����movepiece�������ڽ�Boardʱ���ֵȼ��������Χ�����ֻ����һ�ּ���
     * �����ƶ�ǰ�����ӵ����껮�֣�p1λ�ó������̷�Χ,p2λ�ó������̷�Χ,Ŀ�ĵ�������������,
	 * ��ʼλ�����޿��ƶ�������,����λ����ͬ,��ʼλ�õ����Ӳ��Ǹ�����,�ƶ��ɹ�
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testMovepiece() {
		Action pp=new Action();
		Board m=new Board("chess");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		a.createnewposition(1, 1);
		b.createnewposition(2, 2);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		m.putpiece(a);
		m.putpiece(b);
		Position temp=new Position(99,99);
		Position temp1=new Position(1,1);
		Position temp2=new Position(2,2);
		Position temp3=new Position(3,3);
		Position temp4=new Position(4,4);
		assertEquals(false,pp.movepiece(num1,m,temp,temp3));
		assertEquals(false,pp.movepiece(num1,m,temp1,temp));
		assertEquals(false,pp.movepiece(num1,m,temp1,temp2));
		assertEquals(false,pp.movepiece(num1,m,temp3,temp4));
		assertEquals(false,pp.movepiece(num1,m,temp1,temp1));
		assertEquals(false,pp.movepiece(num1,m,temp2,temp3));
		assertEquals(true,pp.movepiece(num1,m,temp1,temp3));	
	}
	
	/* Testing strategy
	 * ����eatpiece�������ڽ�Boardʱ���ֵȼ���ֻ����Թ������弴��
     * ���ճ���ǰ�����ӵ����껮�֣�p1λ�ó������̷�Χ��p2λ�ó������̷�Χ������λ����ͬ��
	 * �ڶ���λ���ϵ����Ӳ��ǶԷ����ӣ���һ��λ���ϵ����Ӳ����Լ����ӣ���һ��λ���������ӣ�
	 * �ڶ�λ���������ӣ����ӳɹ�
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testEatpiece() {
		Action pp=new Action();
		Board m=new Board("chess");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Piece c=new Piece("white",0);
		Piece d=new Piece("black",1);
		a.createnewposition(1, 1);
		b.createnewposition(2, 2);
		c.createnewposition(3, 3);
		d.createnewposition(4, 4);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		m.putpiece(a);
		m.putpiece(b);
		m.putpiece(c);
		m.putpiece(d);
		Position temp=new Position(99,99);
		Position temp1=new Position(1,1);
		Position temp2=new Position(2,2);
		Position temp3=new Position(3,3);
		Position temp4=new Position(4,4);
		Position temp5=new Position(5,5);
		assertEquals(false,pp.eatpiece(num1,m,temp,temp2));
		assertEquals(false,pp.eatpiece(num1,m,temp1,temp));
		assertEquals(false,pp.eatpiece(num1,m,temp1,temp1));
		assertEquals(false,pp.eatpiece(num1,m,temp1,temp3));
		assertEquals(false,pp.eatpiece(num1,m,temp2,temp4));
		assertEquals(false,pp.eatpiece(num1,m,temp5,temp4));
		assertEquals(false,pp.eatpiece(num1,m,temp1,temp5));
		assertEquals(true,pp.eatpiece(num1,m,temp1,temp2));
	}
	
	/* Testing strategy
	 * ����whethervalidplayer����
     * �������ӵ����껮�֣�ָ��λ�ó������̷�Χ,��λ���Ѿ���һ�����ֵ����ӣ�whiteռ��,��λ���Ѿ����ڶ������ֵ����ӣ�blackռ�ã���λ�ÿ���
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testWhethervalidplayer() {
		Action pp=new Action();
		Board m=new Board("chess");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		a.createnewposition(1, 1);
		b.createnewposition(2, 2);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		m.putpiece(a);
		m.putpiece(b);
		Position temp=new Position(1,1);
		Position temp1=new Position(2,2);
		Position temp2=new Position(3,3);
		Position temp3=new Position(99,99);
		assertEquals("�Ƿ�������ָ��λ�ó������̷�Χ\n",pp.whethervalidplayer(m,temp3));
		assertEquals("��λ���ѱ�����null�����ӣ�whiteռ��\n",pp.whethervalidplayer(m,temp));
		assertEquals("��λ���ѱ�����null�����ӣ�blackռ��\n",pp.whethervalidplayer(m,temp1));
		assertEquals("��λ�ÿ���\n",pp.whethervalidplayer(m,temp2));
	}
	
	/* Testing strategy
	 * ����countplayerpiece����
     * �����������ķ����ַ�������
     */
	@Test
	public void testCountplayerpiece() {
		Action pp=new Action();
		Board m=new Board("chess");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		a.createnewposition(1, 1);
		b.createnewposition(2, 2);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		m.putpiece(a);
		m.putpiece(b);
		assertEquals("nullӵ��1������\n" + "nullӵ��1������\n",pp.countplayerpiece(m));
	}
	
	/* Testing strategy
	 * ����printhistory����
     * ִ�д˷�������
     */
	@Test
	public void testPrinthistory() {
		Action pp=new Action();
		Board m=new Board("chess");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		a.createnewposition(1, 1);
		b.createnewposition(2, 2);
		Player num1=new Player();
		Player num2=new Player();
		num1.setname("haha");
		num2.setname("lala");
		num1.setmyturn(0);
		num2.setmyturn(1);
		m.putpiece(a);
		m.putpiece(b);
	    pp.printhistory();
	}

}
