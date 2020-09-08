package Resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightTest {

	/* Testing strategy
	 * ����getflightnumber����
     * ���Էɻ���ŵķ���ֵ����
     */
	@Test
	public void getflightnumbertest() {
		Flight temp=new Flight("A898","C88",100,2.5);
		assertEquals("A898",temp.getflightnumber());
	}
	
	/* Testing strategy
	 * ����getflighttype����
     * ���Էɻ����ͺŵķ���ֵ����
     */
	@Test
	public void getflighttypetest() {
		Flight temp=new Flight("A898","C88",100,2.5);
		assertEquals("C88",temp.getflighttype());
	}
	
	/* Testing strategy
	 * ����getflightallseat����
     * ���Էɻ���λ���ķ���ֵ����
     */
	@Test
	public void getflightallseattest() {
		Flight temp=new Flight("A898","C88",100,2.5);
		assertEquals(100,temp.getflightallseat());
	}
	
	/* Testing strategy
	 * ����getflightage����
     * ���Էɻ�����ķ���ֵ����
     */
	@Test
	public void getflightagetest() {
		Flight temp=new Flight("A898","C88",100,2.5);
		assertEquals(Double.doubleToLongBits(2.5),Double.doubleToLongBits(temp.getflightage()));
	}
	
	/* Testing strategy
	 * ����hashcode����
     * ����������ͬ�ķɻ���hashcode�Ƿ���ͬ����
     */
	@Test
	public void hashcodetest() {
		Flight temp=new Flight("A898","C88",100,2.5);
		Flight temp1=new Flight("A898","C88",100,2.5);
		assertEquals(temp.hashCode(),temp1.hashCode());
	}
	
	/* Testing strategy
	 * ����equals����
     * ���������ɻ����Ƿ���ͬ���ֵȼ��ࣺ��ͬ����ͬ
     */
	@Test
	public void equalstest() {
		Flight temp=new Flight("A898","C88",100,2.5);
		Flight temp1=new Flight("A898","C88",100,2.5);
		Flight temp2=new Flight("A222","C88",100,2.5);
		assertEquals(true,temp.equals(temp1));
		assertEquals(false,temp.equals(temp2));
	}

}
