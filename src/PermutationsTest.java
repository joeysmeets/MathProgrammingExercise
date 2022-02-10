import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationsTest extends Permutations{

	@Test
	void permToLehmerExampleProf() {
		int[] perm   = {1,3,2,6,5,4};
		int[] lehmer = {0, 1, 0, 2, 1, 0};
		assertArrayEquals(lehmer, getLehmerCode(perm));	
	} 
	
	@Test
	void permToLehmerRandomNumbersWithDuplicates() {
		int[] perm   = {5,1,3,3,4,3,2,1,4,4,5,6,3,2,2,2,2};
//		int[] lehmer = {9, 0, 0, 0, 3, 2, 1, 0, 0, 0, 0, 5, 4, 0, 0, 0, 0}; OLD VERSION LEHMER CODE
		int[] lehmer = {14, 0, 6, 6, 8, 6, 1, 0, 5, 5, 5, 5, 4, 0, 0, 0, 0};
		assertArrayEquals(lehmer, getLehmerCode(perm));	
	}
	
	@Test
	void permToLehmerBigValues() {
		int[] perm 	 = {57,23,46322,63,27,34,6,7,3532,6563,8,52345};
//		int[] lehmer = {6, 3, 8, 5, 3, 2, 0, 0, 0, 1, 0, 0}; OLD VERSION LEHMER CODE
		int[] lehmer = {6, 3, 8, 5, 3, 3, 0, 0, 1, 1, 0, 0};
		assertArrayEquals(lehmer, getLehmerCode(perm));	 
	} 
	
	@Test
	void lehmerCodeToPermExampleProf() {
		int[] ogPerm = {1,2,3,4,5,6};
		int[] lehmer = {0,1,0,2,1,0};
		int[] perm   = {1,3,2,6,5,4};
		assertArrayEquals(perm, getPerm(lehmer, ogPerm));	
	} 
	
	@Test
	void lehmerCodeToPermRandomNumbersWithDuplicates() {
		int[] ogPerm = {1,2,3,3,4,5,5,6};
		int[] lehmer = {7, 0, 3, 1, 0, 0, 0, 0};
		int[] perm   = {6,1,4,3,2,3,5,5};
		assertArrayEquals(perm, getPerm(lehmer, ogPerm));	
	}
	
	@Test
	void lehmerCodeToPermBigValues() {
		int[] ogPerm = {1000,2000,3000,4000,5000}; 
		int[] lehmer = {4, 1, 0, 1, 0};
		int[] perm 	 = {5000,2000,1000,4000,3000};
		assertArrayEquals(perm, getPerm(lehmer, ogPerm));	
	}   
	
	@Test
	void lehmerCodeToPermManyValues() {
		int[] ogPerm = {2, 2, 3, 4, 7, 7, 8, 8, 23, 26, 43, 45, 75, 84, 732, 865, 2437, 5235, 5432, 654326, 1324421, 45238463, 67452374};
		int[] lehmer = {20, 17, 17, 19, 9, 2, 12, 3, 12, 13, 0, 2, 6, 7, 2, 1, 2, 1, 4, 2, 1, 1, 0};
		int[] perm 	 = {1324421, 5235, 5432, 67452374, 26, 3, 732, 7, 2437, 45238463, 2, 7, 45, 84, 8, 4, 23, 8, 654326, 75, 43, 865, 2};
		assertArrayEquals(perm, getPerm(lehmer, ogPerm));	
	}  
}
