package PlanningEntry;


public class OneDistinguishResourceEntryImpl<R> implements OneDistinguishResourceEntry<R>{

	private R mycource; //��Դ
	
	// mutability��
	// Abstraction function:
	// AF(mycource)=����Դ
	// Representation invariant:
	// �������Դ����Ϊ��
	// Safety from rep exposure:
	// ��mycource����Ϊprivate
	
	/**
	 * ���ø���Դ
	 * @param a ����Դ
	 * @return �Ƿ�ɹ����ø���Դ
	 */
	@Override
	public boolean setresource(R a) {
		if(mycource==null) {
		    this.mycource=a;	
		    System.out.println("��Դ���óɹ�");
		    return true;
		}
		System.out.println("��Դֻ������һ��");
		return false;	
	}

	/**
	 * �õ��ý�ʦ��Դ
	 * @return �ý�ʦ��Դ
	 */
	@Override
	public R getresource() {
		return mycource;
	}

	/**
	 * ���ĸ���Դ
	 * @param a ���ĺ����Դ
	 * @return �Ƕ��ɹ����ĸ���Դ
	 */
	@Override
	public boolean changeresource(R a) {
		if(a.equals(mycource)) {
			System.out.println("��ԭ��Դ�ظ�");
			return false;
		}
		this.mycource=a;
		System.out.println("��Դ���ĳɹ�");
		return true;
	}

}
