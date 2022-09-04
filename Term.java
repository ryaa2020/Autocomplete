package autocomplete;


import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Term implements Comparable<Term> {
	public long weight;
	public String query;

	/**
	 * Initializes a term with the given query string and weight.
	 * 
	 * @param query
	 *            word to be stored
	 * @param weight
	 *            associated frequency
	 */
	public Term(String query, long weight) {
		this.query = query;
		this.weight = weight;
	}

	/**
	 * @return comparator ordering elts by descending weight
	 */
	public static Comparator<Term> byReverseWeightOrder() {
		// TODO: implement and return appt comparator
		return (Term a, Term b) -> {
			if (a.weight > b.weight) {return -1;}
			if (a.weight < b.weight) {return 1;}
			else {return 0;}
		};  
	}

	/**
	 * @param r
	 *            Number of initial characters to use in comparing words
	 * @return comparator using lexicographic order, but using only the first r
	 *         letters of each word
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		// TODO: implement and return appt comparator
		return (Term a, Term b) -> {
			int minA = Integer.min(r, a.query.length());
			int minB = Integer.min(r, b.query.length());
			return a.query.substring(0, minA).compareTo(b.query.substring(0, minB));	
		};
	}

	/**
	 * @param that
	 *            Term to be compared
	 * @return -1, 0, or 1 depending on whether the word for THIS is
	 *		   lexicographically smaller, same or larger than THAT
	 */
	public int compareTo(Term that) {
		// TODO: implement standard comparator
		
		// three conditions 
		if (this.query.compareTo(that.query) < 0) {return -1;}
		else if (this.query.compareTo(that.query) > 0) {return 1;}
		else {return 0;}
		
	}

	/**
	 * @return a string representation of this term in the following format: the
	 *         weight, followed by 2 tabs, followed by the word.
	 **/
	public String toString() {
		// TODO: return appropriate string
		return weight + "\t\t" + query;
		
	}
	
	public static void main(String[] args) {
		// create an array list of terms to  add test terms
		ArrayList<Term> tester = new ArrayList<Term>();
		// my test erms
		Term hello = new Term("hello", 10);
        tester.add((hello));
        Term helper = new Term("helper", 2);
        tester.add(helper);
        Term house = new Term("house", 7);
        tester.add(house);
        Term honey = new Term("honey", 6);
        tester.add(honey);
        
        // seeing if it sorts by reverse weight order
        Collections.sort(tester, byReverseWeightOrder());
        System.out.println(tester);
        
        // seeing if it sorts by prefix order
        Collections.sort(tester, byPrefixOrder(3));
        System.out.println(tester);
        
        
	}

}
