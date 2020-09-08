package debug;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LowestPriceTest {

	/* Testing strategy
	 * ����shoppingOffers����
	 * ������Ʒ�������֣�����Ϊ1������Ϊ6������Ϊ1��6֮��
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void shoppingOfferstest() {
		LowestPrice test=new LowestPrice();
		List<Integer> price=Arrays.asList(2);
		List<Integer> needs=Arrays.asList(2);
		List<Integer> temp1=Arrays.asList(2,3);
		List<List<Integer>> special=Arrays.asList(temp1);
		assertEquals(3,test.shoppingOffers(price,special,needs));
		
		price=Arrays.asList(2,3,4);
		needs=Arrays.asList(1,2,1);
		temp1=Arrays.asList(1,1,0,4);
		List<Integer> temp2=Arrays.asList(2,2,1,9);
		special=Arrays.asList(temp1,temp2);
		assertEquals(11,test.shoppingOffers(price,special,needs));
		
		price=Arrays.asList(1,2,3,4,5,6);
		needs=Arrays.asList(3,3,3,3,3,3);
		temp1=Arrays.asList(1,1,1,1,1,0,19);
		special=Arrays.asList(temp1,temp2);
		assertEquals(63,test.shoppingOffers(price,special,needs));
	}
}
