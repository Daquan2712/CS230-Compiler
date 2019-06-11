import java.util.*;
//This class is for the symbol table
//This symbol table's structure is very simple (simply using a HashSet). 
//Its purpose is to store declared variables within the funtion. 
public class SymbolTable {
	HashSet<String> st;
	public SymbolTable(HashSet<String> table) {
		st = table;
	}
	public void Add(String k) {
		st.add(k);
	}
	public void Print() {
		System.out.print("Variables saved in symbol table: ");
		Iterator<String> itr = st.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}
	public boolean contains(String a) {
		return st.contains(a);
	}
}

