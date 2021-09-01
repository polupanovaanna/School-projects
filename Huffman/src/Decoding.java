import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decoding {
	static int M;
	static Node tree[];
	public static int freq[];
	static String symbCodes[];
	static int symb;
	static int length;
	static int bitcount;
	static int curbyte;
	static FileInputStream fl;
	static BufferedInputStream bin;
	static FileOutputStream fl1;
	static BufferedOutputStream bout;
	public static int searchmin(int Mi) {
		int min = Integer.MAX_VALUE;
		int minp = 0;
		for (int j = 0; j < Mi; j ++) {
    		if (tree[j].w < min && tree[j].used == false) {
    			min = tree[j].w;
    			minp = j;
    		}
    	}
    	tree[minp].used = true;
    	return minp;
		
		
	}
	
	public static int getInt(int arr[]) {
		int result = 0;
		for (int i = 0; i < 4; i ++) {
			result = (result<<8) + arr[i];
		}
		return result;
	}
	public static int getNextBit() throws IOException {
		if (bitcount%8 == 0) {
			curbyte = bin.read();
			bitcount = 0;
		}
		int result = (curbyte>>7)%2;
		curbyte = curbyte<<1;
		bitcount = bitcount + 1;
		return result;
	}

	public static void main(String[] args) throws IOException {
		fl = new FileInputStream("output.txt");
		bin = new BufferedInputStream(fl);
		
		fl1 = new FileOutputStream("decoded.png");
		bout = new BufferedOutputStream(fl1);
		
		M = bin.read() + 1;
		freq = new int[256];
		for (int i = 0; i < M; i++) {
			int symb = bin.read();
			int arrbyte[] = new int[4];
			for (int j = 0; j < 4; j ++) {
				arrbyte[j] = bin.read();
			}
			int symbnum = getInt(arrbyte);
			freq[symb] = symbnum;
		}
		tree = Main.buildtree(freq);
		length = 0;
		for (int i = 0; i < 256; i ++) {
			length = length + freq[i];
		}
		int current = 2*M - 2;
		int count = 0;
		bitcount = 0;
		curbyte = 0;
		while (count < length) {
			int bit = getNextBit();
			if (bit == 0) {
				current = tree[current].left;	
			} else {
				current = tree[current].right;
			}
			if (tree[current].symb != -1) {
				bout.write(tree[current].symb);
				current = 2*M - 2;
				count = count + 1;
			}
		}
		
		bin.close();
		bout.close();
	}

}
