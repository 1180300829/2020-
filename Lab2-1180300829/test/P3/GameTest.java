package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	/* Testing strategy
	 * ����putpiece�������ڽ�Boardʱ���ֵȼ��������Χ�����ֻ����һ�ּ���
     * ���ռ������ӵ������Ƿ�Խ�绮�֣�����Խ�磬���겻Խ��
     * ���ռ������ӵ������Ƿ��Ѿ��������ӻ��֣�ָ��λ���������ӣ�ָ��λ��û������
     * �������ӻ��֣������Ӳ������ڸ����֣���ָ���������Ѿ���������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testPutpiece() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Position temp=new Position(99,99);
		Position temp1=new Position(1,1);
		assertEquals(false,pp.putpiece(pp.getplayer(0),b,temp1));
		assertEquals(false,pp.putpiece(pp.getplayer(0),a,temp));
		assertEquals(true,pp.putpiece(pp.getplayer(0),a,temp1));
		assertEquals(false,pp.putpiece(pp.getplayer(1),b,temp1));
		assertEquals(false,pp.putpiece(pp.getplayer(1),b,temp1));
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
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Position temp=new Position(99,99);
		Position temp1=new Position(1,1);
		Position temp2=new Position(2,2);
		pp.putpiece(pp.getplayer(0),a,temp1);
		pp.putpiece(pp.getplayer(1),b,temp2);
		assertEquals(false,pp.removepiece(pp.getplayer(0),a.getpieceposition()));
		assertEquals(false,pp.removepiece(pp.getplayer(0),temp));
		assertEquals(false,pp.removepiece(pp.getplayer(0),temp1));
		assertEquals(true,pp.removepiece(pp.getplayer(0),b.getpieceposition()));
		assertEquals(true,pp.removepiece(pp.getplayer(1),a.getpieceposition()));
	}

	/* Testing strategy
	 * ����movepiece�������ڽ�Boardʱ���ֵȼ��������Χ�����ֻ����һ�ּ���
     * �����ƶ�ǰ�����ӵ����껮�֣�p1λ�ó������̷�Χ,p2λ�ó������̷�Χ,Ŀ�ĵ�������������,
	 * ��ʼλ�����޿��ƶ�������,����λ����ͬ,��ʼλ�õ����Ӳ��Ǹ�����,�ƶ��ɹ�
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testMovepiece() {
		Game pp=new Game("chess");
		pp.setplayernames("haha", "lala");
		Position temp=new Position(99,99);
		Position temp1=new Position(1,1);
		Position temp3=new Position(3,3);
		Position temp4=new Position(4,4);
		Position temp5=new Position(7,7);
		assertEquals(false,pp.movepiece(pp.getplayer(0),temp,temp3));
		assertEquals(false,pp.movepiece(pp.getplayer(0),temp1,temp));
		assertEquals(false,pp.movepiece(pp.getplayer(0),temp1,temp5));
		assertEquals(false,pp.movepiece(pp.getplayer(0),temp3,temp4));
		assertEquals(false,pp.movepiece(pp.getplayer(0),temp1,temp1));
		assertEquals(false,pp.movepiece(pp.getplayer(0),temp5,temp3));
		assertEquals(true,pp.movepiece(pp.getplayer(0),temp1,temp3));	
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
		Game pp=new Game("chess");
		pp.setplayernames("haha", "lala");
		Position temp=new Position(99,99);
		Position temp1=new Position(1,1);
		Position temp2=new Position(2,2);
		Position temp3=new Position(3,3);
		Position temp5=new Position(7,7);
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp,temp5));
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp1,temp));
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp1,temp1));
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp1,temp2));
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp5,temp2));
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp3,temp5));
		assertEquals(false,pp.eatpiece(pp.getplayer(0),temp1,temp3));
		assertEquals(true,pp.eatpiece(pp.getplayer(0),temp1,temp5));
	}
	
	/* Testing strategy
	 * ����whethervalidplayer����
     * �������ӵ����껮�֣�ָ��λ�ó������̷�Χ,��λ���Ѿ���һ�����ֵ����ӣ�whiteռ��,��λ���Ѿ����ڶ������ֵ����ӣ�blackռ�ã���λ�ÿ���
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void testWhethervalidplayer() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Position temp=new Position(1,1);
		Position temp1=new Position(2,2);
		Position temp2=new Position(3,3);
		Position temp3=new Position(99,99);
		pp.putpiece(pp.getplayer(0),a,temp1);
		pp.putpiece(pp.getplayer(1),b,temp2);
		assertEquals("�Ƿ�������ָ��λ�ó������̷�Χ\n",pp.whethervalidplayer(temp3));
		assertEquals("��λ���ѱ�����haha�����ӣ�whiteռ��\n",pp.whethervalidplayer(temp1));
		assertEquals("��λ���ѱ�����lala�����ӣ�blackռ��\n",pp.whethervalidplayer(temp2));
		assertEquals("��λ�ÿ���\n",pp.whethervalidplayer(temp));
	}
	
	/* Testing strategy
	 * ����countplayerpiece����
     * �����������ķ����ַ�������
     */
	@Test
	public void testCountplayerpiece() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Position temp1=new Position(2,2);
		Position temp2=new Position(3,3);
		pp.putpiece(pp.getplayer(0),a,temp1);
		pp.putpiece(pp.getplayer(1),b,temp2);
		assertEquals("hahaӵ��1������\n" + "lalaӵ��1������\n",pp.countplayerpiece());
	}
	
	/* Testing strategy
	 * ����printhistory����
     * ִ�д˷�������
     */
	@Test
	public void testPrinthistory() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Position temp1=new Position(1,1);
		Position temp2=new Position(2,2);
		pp.putpiece(pp.getplayer(0),a,temp1);
		pp.putpiece(pp.getplayer(1),b,temp2);
	    pp.printhistory();
	}
	
	/* Testing strategy
	 * ����getmyboard����
     * ���Է���ֵ����
     */
	@Test
	public void testGetmyoard() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Piece a=new Piece("white",0);
		Piece b=new Piece("black",1);
		Position temp1=new Position(1,1);
		Position temp2=new Position(2,2);
		pp.putpiece(pp.getplayer(0),a,temp1);
		pp.putpiece(pp.getplayer(1),b,temp2);
		assertEquals("haha",pp.getmyboard().playerone);
		assertEquals("lala",pp.getmyboard().playertwo);
		assertEquals(true,pp.getmyboard().getmyboard().contains(a));
		assertEquals(true,pp.getmyboard().getmyboard().contains(b));
	}


	/* Testing strategy
	 * ����getplayerone����
     * ���Է���ֵ����
     */
	@Test
	public void testGetplayerone() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Integer a=new Integer(0);
		assertEquals("haha",pp.getplayerone().getname());
		assertEquals(a,pp.getplayerone().getmyturn());
	}

	/* Testing strategy
	 * ����getplayertwo����
     * ���Է���ֵ����
     */
	@Test
	public void testGetplayertwo() {
		Game pp=new Game("go");
		pp.setplayernames("haha", "lala");
		Integer a=new Integer(1);
		assertEquals("lala",pp.getplayertwo().getname());
		assertEquals(a,pp.getplayertwo().getmyturn());
	}

}
