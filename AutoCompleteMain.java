package autocomplete;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AutoCompleteMain {
	
	public static void main(String[] args) {
		// create a list
		List<Term> list_word = new ArrayList<Term>();
		
		// arguments that will be passed 
		int item = Integer.parseInt(args[0]); 
		String filename = args[1];

		try {
			BufferedReader fileRead = new BufferedReader(new FileReader(filename));
			// read first line to see how many terms are in the file 
			String lines = fileRead.readLine();
			int num_lines = Integer.parseInt(lines); // covert to integer
			
			// read in all the words and its corresponding weight 
			for (int i = 0; i<num_lines; i++) {
				String next_line = fileRead.readLine().trim(); // trim 
				String[] arrOfLine = next_line.split("	", 2); 
				Term new_term = new Term(arrOfLine[1], Long.valueOf(arrOfLine[0])); // make it a term
				list_word.add(new_term); // add to the list of terms
				
			}
			Autocomplete completer = new Autocomplete(list_word);
			Scanner in = new Scanner(System.in);
			System.out.println("Enter a new prefix: "); 
			while (in.hasNext()) {
				String prefix = in.nextLine();
				
				// find all the matches
				List<Term> matches = completer.allMatches(prefix);
				int match_len = matches.size();
				
				// sort based on the weight 
				Comparator<Term> sortWeight = Term.byReverseWeightOrder();
				Collections.sort(matches, sortWeight);
				
				// print the respective statements based on the ammount of matches
				if (match_len > item) {
					System.out.println("There are " + match_len + " matches.");
					System.out.println("The " + item + " largest are:");
					
				} else if (match_len == 0){ // 
					System.out.println("There are no matches");	
				} else { // 
					System.out.println("There are " + match_len + " matches.");
					System.out.println("The matching items are:");
		
				// 
				} for (int i = 0; i < Math.min(item, match_len); i ++) {
					System.out.println(matches.get(i));
				}
				System.out.println("Enter a new prefix: ");
				
				}
			// close 
			in.close();
			fileRead.close();
			} catch (Exception FileNotFoundException) {
		}
	}

}
