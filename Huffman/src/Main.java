import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
	static int M;
	static Node tree[];
	static int freq[];
	static String symbCodes[];
	static int symb;
	
	public static void codes(int num) {
		if (tree[num].symb >=0) {
			symbCodes[tree[num].symb] = tree[num].code;
			return;
		}
		tree[tree[num].left].code = tree[num].code + "0";
		tree[tree[num].right].code = tree[num].code + "1";
		codes(tree[num].left);
		codes(tree[num].right);
	}
	
	public static void readfile(String filename) throws IOException {
		FileInputStream fl = new FileInputStream(filename);
		BufferedInputStream bin = new BufferedInputStream(fl);
		freq = new int[256]; 
		while ((symb = bin.read()) != -1) {
			freq[symb] = freq[symb] + 1;
		}
		bin.close();
	}
	
	public static Node[] buildtree(int freq[]) {
		M = 0;
	    for (int i = 0; i < 256; i ++) {
	    	if(freq[i] != 0) {
	    		M = M + 1;
	    	}
	    }
	    Node tree[] = new Node[2*M - 1];
	    int len = 0;
	    for (int i = 0; i < 256; i ++) {
	    	if (freq[i] != 0) {
	    		tree[len] = new Node(i, freq[i]);
	    		len = len + 1;
	    	}
	    }
	    for (int i = 0; i < M - 1; i ++) {
	    	int min1 = Integer.MAX_VALUE;
	    	int min2 = Integer.MAX_VALUE;
	    	int min1p = 0;
	    	int min2p = 0;
	  
	    	for (int j = 0; j < M + i; j ++) {
	    		if (tree[j].w < min1 && tree[j].used == false) {
	    			min1 = tree[j].w;
	    			min1p = j;
	    		}
	    	}
	    	tree[min1p].used = true;
	    	for (int j = 0; j < M + i; j ++) {
	    		if (tree[j].w < min2 && tree[j].used == false) {
	    			min2 = tree[j].w;
	    			min2p = j;
	    		}
	    	}
	    	tree[min2p].used = true;
	    	tree[len] = new Node();
	    	tree[len].left = min1p;
	    	tree[len].right = min2p;
	    	tree[len].w = tree[min1p].w + tree[min2p].w;
	    	len = len + 1;
	 
	    }
	    return tree;
	}
	public static int[] writeint(int x) {
		int ans[] = new int[4];
		ans[0] = x>>24;
	    ans[1] = (x<<8)>>24;
	    ans[2] = (x<<16)>>24;
	    ans[3] = (x << 24) >> 24;
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		readfile("input.png");
		tree = buildtree(freq);
		symbCodes = new String[256];
	    codes(2*M-2);
	    
	    FileInputStream fl1 = new FileInputStream("input.png");
		BufferedInputStream bin1 = new BufferedInputStream(fl1);
		
		FileOutputStream fl2 = new FileOutputStream("output.txt");
		BufferedOutputStream bout = new BufferedOutputStream(fl2);
		
		bout.write(M-1);
		for (int i = 0; i < 256; i ++) {
			if (freq[i] != 0) {
				bout.write(i);
				int arr[] = writeint(freq[i]);
				for (int j = 0; j < 4; j ++) {
					bout.write(arr[j]);
				}
			}
		}
		String buffer = "";
		while ((symb = bin1.read()) != -1) {
			buffer = buffer + symbCodes[symb];
			while (buffer.length() >=8) {
				String num = buffer.substring(0,8);
				bout.write(Integer.parseInt(num,2));
				buffer = buffer.substring(8, buffer.length());
			}
		}
		if (buffer.equals("") == false) {
			int k = buffer.length();
			for (int i = 0; i < 8 - k; i ++) {
				buffer = buffer + "0";
			}
			bout.write(Integer.parseInt(buffer,2));
		}
		bout.close();
		
		

	}

}
