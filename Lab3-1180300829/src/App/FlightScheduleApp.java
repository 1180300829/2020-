package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import Board.FlightEntryBoard;
import EntryState.*;
import Factory.FlightEntryFactory;
import Location.*;
import Parser.Parser;
import PlanningEntry.*;
import PlanningEntryAPIs.PlanningEntryAPIs;
import Resource.Flight;
import Timeslot.*;

public class FlightScheduleApp {

	private static List<FlightEntry<Flight>> flightlist=new ArrayList<>(); //��������FlightEntry�ļ���
	
	
	 /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĺ���ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ��ǰʱ��һСʱǰ���ʱ��)");
		System.out.println("2.Ϊĳ������ƻ��������Դ");
		System.out.println("3.���ĳ������ƻ����λ��");
		System.out.println("4.���ĳ������ƻ������Դ");
		System.out.println("5.����ĳ������ƻ���");
		System.out.println("6.����ĳ������ƻ���");
		System.out.println("7.ȡ��ĳ������ƻ���");
		System.out.println("8.�鿴ĳ������ƻ����״̬");
		System.out.println("9.���ļ��ж��뺽��ƻ������ƻ������(��ȡ����ļ����ܺ�ʱ�ܳ�)");
		System.out.println("****(ע�⣺ִ��10,11,12�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("10.��⺽��ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("11.���ĳ���ɻ���Դ���г�����ʹ�ø���Դ�ĺ���ƻ���");
		System.out.println("12.���ĳ���ɻ���Դ��ѡ�к��и���Դ��ĳ������ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("13.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("14.��ʾ��ǰ���к���ƻ���ĸ���");
	}
	
	/**
	 * 
	 * @param args
	 * @throws ParseException ʱ���ʽ��Ϊyyyy-MM-dd HH:mm
	 * @throws IOException �ļ����������
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException, IOException {
		TwoLocationEntryImpl a;
		OneDistinguishResourceEntryImpl<Flight> b;
		NoBlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		FlightState state;
		Flight myflight;
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		String fileentry,fileoneline;
		List<String> allfileentry=new ArrayList<>();
		System.out.println("�ʼ��ִ�е�һ��");
		
		while(true) {
			String[] temp;
			String yixie;
			menu();
			System.out.println("��������Ĳ�����");
			choice=scanner.nextLine();
			switch(choice) {
			case "1":
				a=new TwoLocationEntryImpl();
				b=new OneDistinguishResourceEntryImpl<Flight>();
				c=new NoBlockableEntryImpl();
				PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a, b, c); //�ù���������������
				FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
				System.out.println("�˺���ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
				System.out.println("���������վ����(eg ����)��");
				String tempname;
				tempname=scanner.nextLine();
				FlightTrainLocation from=new FlightTrainLocation(weidu,jingdu,tempname);
				System.out.println("�������յ�վ����(eg �人)��");
				tempname=scanner.nextLine();
				FlightTrainLocation to=new FlightTrainLocation(weidu,jingdu,tempname);
				if(flight.setlocations(from, to)) {
					System.out.println("λ�����óɹ�");
				}
				System.out.println("��������ɺͽ���ʱ��(�ö��Ÿ���)(eg 2020-01-01 12:00,2020-01-01 14:00)��");
				try {
					temp=(scanner.nextLine()).split(",");
					Timeslot mytime=new Timeslot(temp[0],temp[1]);
					if(flight.settimeslot(mytime)) {
						System.out.println("һ��ʱ������óɹ�");
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
					 //e.printStackTrace();
					 break;
				}
				//temp=(scanner.nextLine()).split(",");
				//Timeslot mytime=new Timeslot(temp[0],temp[1]);
				//if(flight.settimeslot(mytime)) {
				//	System.out.println("һ��ʱ������óɹ�");
				//}
				System.out.println("�����뺽���(eg AH3567)��");
				String flightname=scanner.nextLine();
				flight.setplanningentryname(flightname);
			    System.out.println("�ƻ����������óɹ�");
				state = FlightWaitingState.instance;
				flight.setcurrentstate(state);
				System.out.println("�ƻ��ǰ״̬���óɹ�");
				System.out.println("��Ϣ�������\n");
				flightlist.add(flight);
				break;
			case "2":
				System.out.println("���������������Դ�ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				int i;
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println();
					break;
				}
				FlightEntry<Flight> tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("����δ����ɻ�(Waiting)")) {
					System.out.println("�����������ɻ���ţ����ͺţ���λ��������(eg N8981,C88,100,2.5)��");
					try {
						temp=(scanner.nextLine()).split(",");
						myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
						if(tempentry.setresource(myflight)) {
							 System.out.println("�������óɹ�");
						}
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
						 //e.printStackTrace();
						 break;
					}
					//temp=(scanner.nextLine()).split(",");
					//myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
					//if(tempentry.setresource(myflight)) {
					//	 System.out.println("�������óɹ�");
					//}
					state=((FlightState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("�ɻ���Դ�������\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�����÷ɻ���Դ����\n");
				}
				break;
			case "3":
				System.out.println("���������յ�λ�ò��ɱ��");
				System.out.println("\n");
				break;
			case "4":
				System.out.println("��������������Դ�ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {
					System.out.println("�������µķɻ���ţ����ͺţ���λ��������(eg B1104,A22,100,2.5)��");
					try {
						temp=(scanner.nextLine()).split(",");
						myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
						tempentry.changeresource(myflight);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��4����������ȷ��ʽ����\n");
						 //e.printStackTrace();
						 break;
					}
					//temp=(scanner.nextLine()).split(",");
					//myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
					//tempentry.changeresource(myflight);
					flightlist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�б���ɻ���Դ����\n");
				}
				break;
			case "5":
				System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {	
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.launch(yixie)) {
					state=((FlightState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ����ɲ���\n");
				}
				break;
			case "6":
				System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("���������(Running)")) {
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.finish(yixie)) {
					state=((FlightState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�н������\n");
				}
				break;
			case "7":
				System.out.println("����������Ҫȡ���ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("����δ����ɻ�(Waiting)")
						||((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {
					System.out.println("������ָ�ȡ��");
					yixie=scanner.nextLine();
					if(tempentry.cancel(yixie)) {
					canceltimeone=Calendar.getInstance();
					String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(canceltimeone.getTime()); 
					canceltimeone.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
					System.out.println("�ú���ƻ���ȡ����ʱ��Ϊ"+str);
					state=((FlightState)tempentry.getcurrentstate()).move('b');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.remove(i);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��������ɣ��޷�ȡ��\n");
				}
				break;
			case "8":
				System.out.println("����������Ҫ�鿴�ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ�������ѱ�ȡ��");
					System.out.println();
					break;
				}
				tempentry=flightlist.get(i);
				System.out.println("�ú���ƻ����״̬Ϊ��"+((FlightState)tempentry.getcurrentstate()).getflightstate());
				System.out.println("\n");
				break;
			case "9":
				System.out.println("�����ṩ����ļ��ɹ���ȡ���ļ����ֱ�Ϊ��FlightSchedule_1.txt,FlightSchedule_2.txt,FlightSchedule_3.txt"
						+ "FlightSchedule_4.txt,FlightSchedule_5.txt");
				System.out.println("����������Ҫ��ȡ����ƻ�����ļ���(eg FlightSchedule_1.txt)��");
				tempname=scanner.nextLine();
				BufferedReader thisfile=new BufferedReader(new FileReader("src/txt/"+tempname));
				i=0;
				int qq=0;
				fileentry="";
				allfileentry=new ArrayList<>();
				Parser tempparser=new Parser();
				List<FlightEntry<Flight>> tempflightlist=new ArrayList<>();
				boolean flag=true;
				while((fileoneline=thisfile.readLine())!=null) { //��������ÿʮ���кϳ�һ���ַ���
					if(fileoneline.equals("")) {  //��������
						continue;
					}
					else {
						fileentry=fileentry+fileoneline+"\n"; //�ǵü��ϻ��з�
						if(i==12) {
							allfileentry.add(fileentry);
							i=-1;
							fileentry="";
						}
						i++;
					}
				}
				for(i=0;i<allfileentry.size();i++) {  //���μ��ÿ���ַ����Ƿ���ϸ�ʽҪ��
					flag=tempparser.checkwhethercorrect(allfileentry.get(i));
					if(flag==false) {
						break;
					}
				}
				if(flag==false) {
					System.out.println("�ļ��еĺ�����Ϣ�ַ���������Ҫ��,����������ļ�\n");
					break;
				}
				else {          //���ڷ���Ҫ����ļ������ζ��뺽��ƻ�������뺽��ƻ������
					for(i=0;i<allfileentry.size();i++) {
						fileentry=allfileentry.get(i);
						a=new TwoLocationEntryImpl();
						b=new OneDistinguishResourceEntryImpl<Flight>();
						c=new NoBlockableEntryImpl();
						originflight=new FlightEntryFactory().getFlightEntry(a, b, c); //�ù���������������
						flight=(FlightEntry<Flight>)originflight;
						
						temp=tempparser.getAllinformation("Flight:", fileentry).split(",");  
						
						Calendar readytime= Calendar.getInstance(); 
						readytime.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(temp[0])); //�õ��ƻ��������
						
						tempname=temp[1];  //�õ������
						flight.setplanningentryname(tempname);
						
						from=new FlightTrainLocation(weidu,jingdu,tempparser.getAllinformation("DepartureAirport:",fileentry)); //�õ����
						to=new FlightTrainLocation(weidu,jingdu,tempparser.getAllinformation("ArrivalAirport:",fileentry));  //�õ��յ�
						flight.setlocations(from, to);
						
						Calendar begintime= Calendar.getInstance(); 
						begintime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempparser.getAllinformation("DepatureTime:",fileentry))); //���ʱ��
						Calendar endtime= Calendar.getInstance(); 
						endtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempparser.getAllinformation("ArrivalTime:",fileentry)));  //�ִ�ʱ��
						
						Timeslot mytime=new Timeslot(tempparser.getAllinformation("DepatureTime:",fileentry),tempparser.getAllinformation("ArrivalTime:",fileentry));  //�õ�ʱ���
						flight.settimeslot(mytime);
						
						String number=tempparser.getAllinformation("Plane:",fileentry);  //�õ��ɻ��ĸ�����Ϣ
						String type=tempparser.getAllinformation("Type:",fileentry);
						int seats= Integer.parseInt(tempparser.getAllinformation("Seats:",fileentry));
						Double age=Double.parseDouble(tempparser.getAllinformation("Age:",fileentry));
						
						myflight=new Flight(number,type,seats,age);
						flight.setresource(myflight);
						
						state = FlightWaitingState.instance;
						state=state.move('a');
						flight.setcurrentstate(state);
						
						//��ȥ���зǷ����
						if(!((begintime.get(Calendar.YEAR)==(readytime.get(Calendar.YEAR))) 
								&&(begintime.get(Calendar.MONTH)==readytime.get(Calendar.MONTH))&&
								(begintime.get(Calendar.DAY_OF_MONTH)==readytime.get(Calendar.DAY_OF_MONTH)))) {
							System.out.println("����ƻ���"+tempname+"����������һ�����ڲ�һ��");
							flag=false;
						}
				     	if((((endtime.getTime().getTime()-begintime.getTime().getTime())/1000/60)>1440)) {
							System.out.println("����ƻ���"+tempname+"���������������������һ��");
							flag=false;
						}
						for(int m=0;m<tempflightlist.size();m++) {
							FlightEntry<Flight> tempflight=tempflightlist.get(m);
							if(((changeformat(tempflight.getplanningentryname()).equals(changeformat(flight.getplanningentryname()))))){
									if((begintime.get(Calendar.YEAR)==(tempflight.gettimeslot().getbegintime().get(Calendar.YEAR)))
											&&(begintime.get(Calendar.MONTH)==tempflight.gettimeslot().getbegintime().get(Calendar.MONTH))
											&&(begintime.get(Calendar.DAY_OF_MONTH)==tempflight.gettimeslot().getbegintime().get(Calendar.DAY_OF_MONTH))) {
										System.out.println("��"+i+"������ƻ���"+tempname+"���Ѵ����б���ĳ�ƻ����ź�������ͬ");
										flag=false;
										break;
									}
									else if(!(tempflight.getfromlocation().getlocationname().equals(from.getlocationname())&&
											tempflight.gettolocation().getlocationname().equals(to.getlocationname())&&
											tempflight.gettimeslot().getbegintime().get(Calendar.HOUR_OF_DAY)==begintime.get(Calendar.HOUR_OF_DAY)&&
											tempflight.gettimeslot().getendtime().get(Calendar.HOUR_OF_DAY)==endtime.get(Calendar.HOUR_OF_DAY)&&
											tempflight.gettimeslot().getbegintime().get(Calendar.MINUTE)==begintime.get(Calendar.MINUTE)&&
											tempflight.gettimeslot().getendtime().get(Calendar.MINUTE)==endtime.get(Calendar.MINUTE))) {
										System.out.println("��"+i+"������ƻ���"+tempname+"���Ѵ����б���ĳ�ƻ�������ͬ������������"
												+ "�ͽ�������������͵���ʱ���г����˲�ͬ");
										flag=false;
										break;
									}
							}
						}
						if(flag==true) {
							tempflightlist.add(flight);
							qq++;
						}
						if(flag==false) {
							System.out.println("����������ļ�\n");
							break;
						}
					}
					if(flag==false) {
						tempflightlist=new ArrayList<FlightEntry<Flight>>();
					}
					if(flag==true) {
						flightlist.addAll(tempflightlist);
						System.out.println("������"+qq+"���ƻ�������ļ��мƻ�����¼��ɻ��ƻ������\n");
					}
					thisfile.close();
				}
				break;
			case "10":
				System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
				tempname=scanner.nextLine();
				System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
				myapis.checkLocationConflict(flightlist,tempname);
				System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
				myapis.checkResourceExclusiveConflict(flightlist);
				System.out.println("\n");
				break;
			case "11":
				System.out.println("����������Ҫ�鿴�ķɻ���Դ�ķɻ����(eg N8981)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getresource().getflightnumber().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���ӵ�и���Դ");
					System.out.println();
					break;
				}
				tempentry=flightlist.get(i);
				myapis.findEntryPerResource(tempentry.getresource(), flightlist);
				System.out.println("\n");
				break;
			case "12":
				System.out.println("����������Ҫ�鿴��ǰ��ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println();
					break;
				}
				tempentry=flightlist.get(i);
				myapis.findPreEntryPerResource(tempentry.getresource(),tempentry,flightlist);
				System.out.println("\n");
				break;
			case "13":
				System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����)");
				tempname=scanner.nextLine();
				FlightEntryBoard flightboard=new FlightEntryBoard();	
				flightboard.setairportlocation(tempname);
				flightboard.getsortcomeentry(flightlist);
				flightboard.getsorttoentry(flightlist);
				flightboard.createFlightEntryBoard();
				flightboard.visualize();	
				System.out.println("\n");
				break;
			case "14":
				System.out.println("��ǰ���к���ƻ���ĸ���Ϊ��"+flightlist.size()+"\n");
				break;
			default:
				System.out.println("��������ȷָ��\n");
				break;	
				
			}
		}
		
		
	}
	
	/**
	 * ������Ž���ת�����ڱȽ�
	 * @param a ��ת�������
	 * @return ת����ĺ����
	 */
	public static String changeformat(String a) {  //ת������Ž����ж�
		if(a.length()==6) {
			return a;
		}
		else if(a.length()==4) {
			return a.substring(0, 2)+"00"+a.substring(2);	
		}
		else{
			return a.substring(0, 2)+"0"+a.substring(2);
		}
	}
}
