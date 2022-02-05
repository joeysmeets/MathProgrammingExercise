import java.util.ArrayList;
import java.util.Arrays;
/**
 * Permutations in lexicographic order, converting permutations to Lehmer code, and 
 * getting the permutation belonging to the Lehmer code of a permutation
 * @author Joey Smeets and Ekaterina Kuritsyna
 * @date   05.02.2021
 */
public class Permutations {
	ArrayList<int[]> permutations = new ArrayList<>();
	
	public static void main(String[] args) {	 
		int[] perm1   = {1,3,2,6,5,4};
		int[] perm2   = {5,1,3,3,4,3,2,1,4,4,5,6,3,2,2,2,2};
		int[] perm3   = {57,23,46322,63,27,34,6,7,3532,6563,8,52345};
		
		System.out.println(Arrays.toString(getLehmerCode(perm1)));
		System.out.println(Arrays.toString(getLehmerCode(perm2)));
		System.out.println(Arrays.toString(getLehmerCode(perm3)));
		
		System.out.println();
		
		int[] perm4   = {6,1,4,3,2,3,5,5}; 
		int[] perm5   = {5000,2000,1000,4000,3000};
		int[] perm6   = {1324421,5235,5432,67452374,26,3,732,7,2437,45238463,2,7,45,84,8,4,23,8,654326,75,43,865,2};
		System.out.println(Arrays.toString(perm6));
		Arrays.sort(perm6);
		
		System.out.println(Arrays.toString(getLehmerCode(perm4)));
		System.out.println(Arrays.toString(getLehmerCode(perm5)));
		System.out.println(Arrays.toString(getLehmerCode(perm6)));
		System.out.println(Arrays.toString(perm6));
	} 
	
	/**
	 * 1. Schreiben Sie eine Methode getLehmerCode, die den Lehmer-Code von p zurückgibt.
	 * Der Lehmer-Code der Permutation p ist der Vektor L(p) = (l1, l2, ... ln), 
	 * wobei die li definiert sind als die Anzahl der Zahlen, die in der Tupeldarstellung von p rechts von pi stehen und kleiner als pi sind: 
	 * li |{j{1,2,...n}|(i, j)Inversion}|.
	 * 
	 * Method to get the Lehmer Code from a permutation
	 * @param  int[] permutation   
	 * @return int[] Lehmer code associated with the given permutation
	 */
	public static int[] getLehmerCode( int[] permutation) {
		int[] lehmerCode = new int[permutation.length]; // instantiating the output array
		int leftValue;  // value to keep track of the value on the left
		int rightValue; // value to keep track of the value on the right
		int counter;    // value to keep track of the number of right lower elements of the left value
		for (int i = 0; i < permutation.length; i++) { // iterate over the array to set the leftValue
			leftValue = permutation[i];
			counter = 0; // reset the counter each iteration
			for (int j = i + 1; j < permutation.length + 1; j++) { // iterate over the right neighbors
				if (j == permutation.length) { // if j is outside of the array, make sure there is no error or incremented count
					rightValue = Integer.MAX_VALUE;
				}
				else { // otherwise set rightValue to the value of the neighbor of the iteration index
					rightValue = permutation[j];
				}
				if (leftValue > rightValue) { // if the left value is bigger than the right value, the counter it incremented. 
					counter++;
				}
				lehmerCode[i] = counter;  
			}
		}
		return lehmerCode;
	}
	
	/**
	 * 2. Schreiben Sie eine Methode getPerm, welche die zu L(p) gehörende Permutation p zurückgibt.
	 * Nun kann man aus dem Lehmer-Code L(p) die Permutation p eindeutig rekonstruieren: 
	 * Dazu entfernt man aus dem n-Tupel 1,2...n im i-ten Schritt die (li +1)-te Zahl und notiert diese als pi .
	 * 
	 * Method to get the permutation belonging to the Lehmer code of a permutation
	 * @param  int[] Lehmer Code
	 * @param  int[] permutation you want to permute
	 * @return int[] permutation belonging to the Lehmer code
	 */
	public static int[] getPerm(int[] lehmerCode, int[] ogPermutation) {
		int[] permutation = new int[lehmerCode.length]; // instantiating the output array

		ArrayList<Integer> ogPermutationCopy = new ArrayList<Integer>();		
		for (int integer : ogPermutation) { // copying the array to an arraylist to have a dynamic size
			ogPermutationCopy.add(integer);
		}

		for (int i = 0; i < lehmerCode.length; i++) {
			int position = lehmerCode[i] + 1; // the (li +1)th number
			int index = position - 1; // index for this number
			permutation[i] = ogPermutationCopy.get(index); // adding the value of this index to the output array
			ogPermutationCopy.remove(index); // removing the index 
		}
		return permutation; // returning the associated permutation to the lehmer code
	}
	
	/**
	 * 3. Schreiben Sie nun eine Methode, welche die Permutationen von {1, 2, ... n} lexikographisch auflistet.
	 * Tipp: Inkrementieren Sie eine Laufvariable in der oben beschriebenen Darstellung und verwenden Sie diese Zahl als Lehmer-Code für die auszugebende Permutation.
	 * Rufen Sie in Ihrer Methode eine Methode showPerm auf, welche die Permutation p , ihren Lehmer-Code, ihre Zyklendarstellung und ihr Signum ausgibt.
	 * 
	 * 
	 * @param int[] elements
	 * @return
	 */
	public ArrayList<int[]> permute(int[] elements) {
		for (int i = 0; i < elements.length; i++ ) {
			//swap();
		}
		return permutations;
	}
}
	
//	
//	public void swap(int[] array, int i, int j) {
//		int temp = array[i];
//		array[i] = array[j];
//		array[j] = temp;
//	}

// OLD VERSION -- STOPS ONCE SOMETHING ON THE RIGHT OF THE CHECKED INTERATION IS HIGHER.
///**
// * 1. Schreiben Sie eine Methode getLehmerCode, die den Lehmer-Code von p zurückgibt.
// * Method to get the Lehmer Code from a permutation
// * @param  int[] permutation   
// * @return int[] Lehmer code associated with the given permutation
// */
//public static int[] getLehmerCode( int[] permutation) {
//	int[] lehmerCode = new int[permutation.length]; // result Array
//	int leftValue;  // value to keep track of the value on the left
//	int rightValue; // value to keep track of the value on the right
//	boolean stopIteration;
//	int counter; // value to keep track of the amount of right adjacent lower elements of the left
//	for (int i = 0; i < permutation.length; i++) { // iterate over the array to set the leftValue
//		leftValue = permutation[i];
//		counter = 0; // reset the counter each iteration
//		stopIteration = false; //reset the second iterations
//		for (int j = i + 1; j < permutation.length + 1; j++) { // iterate over the right neighbors
//			if (stopIteration == false) {
//				if (j == permutation.length) { // if j is outside of the array
//					rightValue = Integer.MAX_VALUE;
//				}
//				else {
//					rightValue = permutation[j];
//				}
//				if (leftValue <= rightValue) {
//					lehmerCode[i] = counter;
//					stopIteration = true;
//				}
//				else {
//					counter++;
//				}
//			}
//		}
//	}
//	return lehmerCode;
//}
