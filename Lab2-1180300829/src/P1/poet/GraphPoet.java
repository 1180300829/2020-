/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    // AF(graph)=�����������ַ������ɵ�ͼ
    // Representation invariant:
    // ���뱣������Ͽ��ļ����ɵ�ͼ
    // Safety from rep exposure:
    // ����graphΪprivate
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	BufferedReader toread=new BufferedReader(new FileReader(corpus));
    	String du;
    	String[] hang;
    	List<String> mylist=new ArrayList<>();
    	Map<String,Integer> mymap=new HashMap<>();
    	while((du=toread.readLine())!=null) {  //���ļ�һ���ַ�����list��
    		hang=du.split(" ");
    		mylist.addAll(Arrays.asList(hang));
    	}
    	toread.close();
    	for(int i=0;i<mylist.size()-1;i++) {
    		String from=mylist.get(i).toLowerCase();  //��ΪСдString
    		String to=mylist.get(i+1).toLowerCase();
    		int before=0;
    		String fromto=from+to;
    		if(mymap.containsKey(fromto)) {
    			before=mymap.get(fromto);
    		}
    		mymap.put(fromto, before+1);
    		graph.set(from, to, before+1);   //graph�м������е��
    	}
    	checkRep();
        //throw new RuntimeException("not implemented");
    }
    
    // TODO checkRep
    private void checkRep() {
		assert graph != null;
	}
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	StringBuilder build=new StringBuilder();
    	List<String> mylist=new ArrayList<>();
    	String[] newinput=input.split(" ");
    	mylist.addAll(Arrays.asList(newinput));  //���������ַ������ո�ָ�����list��
    	Map<String, Integer> sourcemap = new HashMap<>();
		Map<String, Integer> targetmap = new HashMap<>();
		for(int i=0;i<mylist.size()-1;i++) {
			build.append(mylist.get(i)).append(" ");    //ÿ����build�м���һ����
			String source=mylist.get(i).toLowerCase();
			String target=mylist.get(i+1).toLowerCase();  //һ�ν��������ڵ��ΪСд
			targetmap=graph.targets(source);  //�õ�ǰһ��Դ��������յ��
			sourcemap=graph.sources(target);  //�õ���һ���յ������Դ���
			int max=0;
			String word="";  //������ĵ�word
			for(String a:targetmap.keySet()) {   //���յ����ѡȡ��
				if(sourcemap.containsKey(a)&&sourcemap.get(a)+targetmap.get(a)>max) { 
					//����յ���еĵ���Դ����г��ֲ��ҳ��ȴ���max�������max����ȷ����ʱ��word
					max=sourcemap.get(a)+targetmap.get(a);
					word=a;
				}
			}
			if(max>0) {
				build.append(word+" ");  //����word��
			}
		}
		build.append(mylist.get(mylist.size()-1));  //���벢δ���ǵ����һ����
		String fanhui=build.toString();
		return fanhui;
        //throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    @Override
	public String toString() {
    	String a=graph.toString();;
		return a;
	}
}
