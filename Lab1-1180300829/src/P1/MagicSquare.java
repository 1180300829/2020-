package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class MagicSquare {
	public static void main(String[] args) {
		System.out.println("���������ṩ���ļ������Ƿ���MagicSquare�ļ�⣬���Ƿ���true�����Ƿ���false��˵��ԭ��");
		System.out.println("�ļ�һ�Ľ��Ϊ"+MagicSquare.isLegalMagicSquare("src/P1/txt/1.txt")+"\n");
		System.out.println("�ļ����Ľ��Ϊ"+MagicSquare.isLegalMagicSquare("src/P1/txt/2.txt")+"\n");
		System.out.println("�ļ����Ľ��Ϊ"+MagicSquare.isLegalMagicSquare("src/P1/txt/3.txt")+"\n");
		System.out.println("�ļ��ĵĽ��Ϊ"+MagicSquare.isLegalMagicSquare("src/P1/txt/4.txt")+"\n");
		System.out.println("�ļ���Ľ��Ϊ"+MagicSquare.isLegalMagicSquare("src/P1/txt/5.txt")+"\n");
		int a;
		while(true)
		{
			Scanner in=new Scanner(System.in);
			System.out.println("�������������Լ���ʵ�ֵ�MagicSquare�Ŀ�ȣ�");
			a=in.nextInt();
			if(a%2==0) {
				System.out.println("��ż��"+a+"Ϊ����������MagicSquare������ֵΪ"+MagicSquare.generateMagicSquare(a)+"\n");
			}
			else if(a<0) {
				System.out.println("�Ը���"+a+"Ϊ����������MagicSquare������ֵΪ"+MagicSquare.generateMagicSquare(a)+"\n");
			}
			else {
				System.out.println("��"+a+"ʵ�ֵ�MagicSquare���£����Ѵ���6.txt");
				MagicSquare.generateMagicSquare(a);
				System.out.println("Ȼ����ļ��������Ƿ���MagicSquare�ļ�⣬������£�");
				System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/6.txt")+"\n");
			}
			
		}
	}//���������Լ����ı��ļ������ж�
	
	public static boolean isLegalMagicSquare(String fileName) {
		int hang=-1; //��������
		File myfile=new File(fileName);
		try {
	    	//�õ�����
	    	BufferedReader yes=new BufferedReader(new FileReader(myfile));
			String mytemp1=null;
			do {
				hang++; //�õ�����
			}while((mytemp1=yes.readLine())!=null);
			yes.close();
			
			//�����һ�ִ�������������ȡ����Ǿ���
			@SuppressWarnings("resource")
			BufferedReader faultone=new BufferedReader(new FileReader(myfile));
			String mytemp2=null;
			String[] everyhang;
			int lie;
			while((mytemp2=faultone.readLine())!=null) {
				everyhang=mytemp2.split("\t");   //�����Ʊ����һ�е��ַ��ֿ�װ��������
				lie=everyhang.length;
				if(lie!=hang) {
					System.out.println(fileName+"����������ȣ����ܹ��ɾ���");
					return false;
				}
			}
			everyhang=null;
			faultone.close();
			
			//����ڶ��ִ��󣺾�����ĳЩ���ֲ���������
		    @SuppressWarnings("resource")
			BufferedReader faulttwo=new BufferedReader(new FileReader(myfile));
		   	String mytemp3=null;
		    int i=0,j;
	    	while((mytemp3=faulttwo.readLine())!=null) {
			    everyhang=mytemp3.split("\t");
		    	lie=everyhang.length;
		   		for(j=0;j<lie;j++) {
		    		if(everyhang[j].contains(".")||everyhang[j].contains("-")) {
			    		System.out.println(fileName+"���ڲ�������������");
			    		return false;
				   	}
			   	 }
		    	i++;
		   	}
		   	everyhang=null;
		   	faulttwo.close();
		   	
		    //��������ִ��������ֲ���\t��Ϊ�ָ����׳��쳣	
			int[][] juzhen=new int[hang][hang];
			i=0;
		   	try {
		   	    BufferedReader faultthree=new BufferedReader(new FileReader(myfile));
		   		mytemp3=null;
		    	while((mytemp3=faultthree.readLine())!=null) {
				    everyhang=mytemp3.split("\t");
			    	lie=everyhang.length;
			   		for(j=0;j<lie;j++) {
			   			juzhen[i][j]=Integer.valueOf(everyhang[j]);
			   		}
			   		i++;
		    	}
		    	faultthree.close();
		     }catch(Exception e) {//Integer.valueOf()���������ո���ᴥ���쳣���ܹ�ɸѡ��"\t"�����
		   	      System.out.println(fileName+"����֮�䲢��ʹ��\\t�ָ�");
		   	      e.printStackTrace();
                  return false;
		   	    }
			
		   	lie=hang;
		   	int[] sumhang=new int[hang];
		    int[] sumlie=new int[lie];
		    int[] sumdia=new int[2];
		    int temp1,temp2,temp3;
		    for(i=0;i<hang;i++) {
		    	sumhang[i]=0;
		    }
		    for(j=0;j<lie;j++) {
		    	sumlie[j]=0;
		    }
		    sumdia[0]=0;sumdia[1]=0;
		    
		    //�����У��У��Խ��ߵĺͲ��ж��Ƿ�ΪMagicSquare
		    //����ÿһ�еĺͱ���������sumhang
		    for(i=0;i<hang;i++) {
		    	for(j=0;j<lie;j++) {
		    		sumhang[i]=sumhang[i]+juzhen[i][j];
		    	}
		    }
		    //����ĳһ�еĺͱ�����sumlie
		    for(i=0;i<lie;i++) {
		    	for(j=0;j<hang;j++) {
		    		sumlie[i]=sumlie[i]+juzhen[j][i];
		    	}
		    }
		    //����Խ��ߵĺͱ�����sumdia
		    for(j=0;j<hang;j++) {
		    	sumdia[0]=sumdia[0]+juzhen[j][j];
		    	sumdia[1]=sumdia[1]+juzhen[j][hang-j-1];
		    	}
		    //�ж��Ƿ�����MagicSquare������
		    temp1=sumhang[0];temp2=sumlie[0];temp3=sumdia[0];
		    if(temp1!=temp2||temp1!=temp3||temp2!=temp3) {
		    	return false;
		    }
		    else {
		    	for(i=1;i<hang;i++) {
			    	if(sumhang[i]!=temp1) {
			    		return false;
			    	}
		    	}
		    	for(i=1;i<lie;i++) {
		    		if(sumlie[i]!=temp2) {
		    			return false;
		    		}
		    	}
		    	if(sumdia[1]!=sumdia[0]) {
		    		return false;
		    	}	    	
		    }    	
		    //��������ִ��������ֲ���\t��Ϊ�ָ����׳��쳣	
	    }catch(Exception e) { 
               System.out.println(fileName+"�ļ�������");
               e.printStackTrace();
               return false;
	    }
		return true;  
	}
	
	@SuppressWarnings("resource")
	public static boolean generateMagicSquare(int n) {
		 if (n % 2 == 0 || n <= 0) {   //�ж������n�Ƿ�Ϊ�����������򷵻�false
	            return false;
	        }
		 int magic[][] = new int[n][n];
		 int row = 0, col = n / 2, i, j, square = n * n;
		 for (i = 1; i <= square; i++) {  //����������MagicSquare
		 magic[row][col] = i;
		 if (i % n == 0)    //б����
		 row++;
		 else {
		 if (row == 0)    //�ϱ��������¹���
		 row = n - 1;
		 else
		 row--;
		 if (col == (n - 1))   //�ұ�����������
		 col = 0;
		 else
		 col++;
		 }
		 }
		 for (i = 0; i < n; i++) {              
		 for (j = 0; j < n; j++)
		    System.out.print(magic[i][j] + "\t");   //���MagicSquare
	     System.out.println();
		 }	
		 FileWriter writefile = null;
	     BufferedWriter mywriter = null;
	     try {
	    	    writefile = new FileWriter("src/P1/txt/6.txt");
	            mywriter = new BufferedWriter(writefile);
	        } catch (Exception e) {
	        	 System.out.println("д�����1");
	        	 e.printStackTrace();
	             return false;
	        }
	        for (i = 0; i < n; i++) {
	            for (j = 0; j < n; j++) {
	                try {
	                    mywriter.write(magic[i][j] + "\t");
	                } catch (Exception e) {
	                	System.out.println("д�����2");
	                	e.printStackTrace();
	                	 return false;
	                }
	            }
	            try {
	                mywriter.write("\n");
	            } catch (Exception e) {
	            	System.out.println("д�����3");
	            	e.printStackTrace();
	                return false;
	            }
	        }
	        try {
	            if(mywriter!=null)
	                mywriter.close();
	        } catch (Exception e) {
	        	System.out.println("д�����4");
	        	e.printStackTrace();
	        	return false;
	        } 
	        return true;
	}
}
	     

			
			
			
