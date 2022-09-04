package autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Autocomplete implements AutocompleteInterface { // should implement the AutocompleteInterface 
	List<Term> toSort;
	List<Term> prefixList;
	List<Term> subPrefixList;
	
	//constructor
	public Autocomplete(List<Term> termList) {
		this.toSort = termList;
		//sorting the list
		//Collections.sort(toSort, Term.byReverseWeightOrder());
	}
	
	/**
	* @param prefix
	* string to be matched
	* @return List of all matching terms in descending order by weight
	*/
	public List<Term> allMatches(String prefix) {
		// create new comparator 
		Comparator<Term> prefixComp = Term.byPrefixOrder(prefix.length());
		
		//sort the list of Terms in prefix order
		Collections.sort(toSort, prefixComp);
		
		//make prefix into a term 
		Term prefixTerm = new Term(prefix, 0); 
		
		//variable for first index where the prefix occurs in the sorted list
		int firstIndex = BinarySearchForAll.firstIndexOf(toSort, prefixTerm, prefixComp);
		
		//variable for last index where the prefix occurs in the sorted list
		int lastIndex = BinarySearchForAll.lastIndexOf(toSort, prefixTerm, prefixComp);
		
		//if the prefix doesn't exist in the list of terms, don't make a new list
		if (firstIndex == -1 || lastIndex == -1) {
			prefixList = new ArrayList<Term>(); 
			return prefixList;
		}
		else {
			//adding terms with prefix to the list of terms
			//assign the sublist to be between the indexes in toSort
			prefixList = toSort.subList(firstIndex, lastIndex+1);
			}
			return prefixList;
		}
		
		
}