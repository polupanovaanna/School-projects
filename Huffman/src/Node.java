
public class Node {
	int w;
	int left;
	int right;
	boolean used;
	int symb = -1;
	String code;
	 Node(int i, int freqi) {
		w = freqi;
		symb = i;
		left = -1;
		right = -1;
		used = false;
		code = "";
	}
	 Node() {
		left = -1;
		right = -1;
		used = false;
		code = ""; 
	 }
}
