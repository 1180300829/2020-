package EntryState;

public class FlightBlockedState implements FlightState{

	 static FlightBlockedState instance=new FlightBlockedState();
	 private String nowstate="������;����(Blocked)";
	 
	 private FlightBlockedState() {
		};
		
		/**
		 * �ı䵱ǰ״̬
		 * @param c �ı�ָ��
		 * @return �µ�״̬
		 */
		@Override
		public FlightState move(char c) {
			switch(c) {
			case 'a':
				return FlightRunningState.instance;
			case 'b':
				return FlightCancelledState.instance;
			default:
				throw new IllegalArgumentException();
			}
		}

		/**
		 * �õ���ǰ״̬
		 * @return ������ǰ״̬���ַ���
		 */
		@Override
		public String getflightstate() {
			// TODO �Զ����ɵķ������
			return this.nowstate;
		}
}
