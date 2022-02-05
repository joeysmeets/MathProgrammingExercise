import java.util.ArrayList;
/**
 * Permutations in lexicographic order, converting permutations to Lehmer code, and 
 * getting the permutation belonging to the Lehmer code of a permutation
 * @author Joey Smeets and Ekaterina Kuritsyna
 * @date   05.02.2021
 */
public class Permutations {
	ArrayList<int[]> permutations = new ArrayList<>();
	
	public static void main(String[] args) {	
	} 
	
	/**
	 * 1. Schreiben Sie eine Methode getLehmerCode, die den Lehmer-Code von p zurückgibt.
	 * Method to get the Lehmer Code from a permutation
	 * @param  int[] permutation   
	 * @return int[] Lehmer code associated with the given permutation
	 */
	public static int[] getLehmerCode( int[] permutation) {
		int[] lehmerCode = new int[permutation.length]; // result Array
		int leftValue;  // value to keep track of the value on the left
		int rightValue; // value to keep track of the value on the right
		boolean stopIteration;
		int counter; // value to keep track of the amount of right adjacent lower elements of the left
		for (int i = 0; i < permutation.length; i++) { // iterate over the array to set the leftValue
			leftValue = permutation[i];
			counter = 0; // reset the counter each iteration
			stopIteration = false; //reset the second iterations
			for (int j = i + 1; j < permutation.length + 1; j++) { // iterate over the right neighbors
				if (stopIteration == false) {
					if (j == permutation.length) { // if j is outside of the array
						rightValue = Integer.MAX_VALUE;
					}
					else {
						rightValue = permutation[j];
					}
					if (leftValue <= rightValue) {
						lehmerCode[i] = counter;
						stopIteration = true;
					}
					else {
						counter++;
					}
				}
			}
		}
		return lehmerCode;
	}
	
	/**
	 * 2. Schreiben Sie eine Methode getPerm, welche die zu L(p) gehörende Permutation p zurückgibt.
	 * Method to get the permutation belonging to the Lehmer code of a permutation
	 * @param  int[] Lehmer Code
	 * @param  int[] permutation you want to permute
	 * @return int[] permutation belonging to the Lehmer code
	 */
	public static int[] getPerm(int[] lehmerCode, int[] ogPermutation) {
		int[] permutation = new int[lehmerCode.length];

		ArrayList<Integer> ogPermutationCopy = new ArrayList<Integer>();		
		for (int integer : ogPermutation) {
			ogPermutationCopy.add(integer);
		}

		for (int i = 0; i < lehmerCode.length; i++) {
			int position = lehmerCode[i] + 1;
			int index = position - 1;
			permutation[i] = ogPermutationCopy.get(index);
			ogPermutationCopy.remove(index);
		}
		return permutation;
	}
	
	/**
	 * 3. Schreiben Sie nun eine Methode, welche die Permutationen von {1, 2, ... n} lexikographisch auflistet.
	 * Tipp: Inkrementieren Sie eine Laufvariable in der oben beschriebenen Darstellung und verwenden Sie diese Zahl als Lehmer-Code für die auszugebende Permutation.
	 * Rufen Sie in Ihrer Methode eine Methode showPerm auf, welche die Permutation p , ihren Lehmer-Code, ihre Zyklendarstellung und ihr Signum ausgibt.
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

