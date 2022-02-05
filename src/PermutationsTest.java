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
		int[] lehmer = {9, 0, 0, 0, 3, 2, 1, 0, 0, 0, 0, 5, 4, 0, 0, 0, 0};
		assertArrayEquals(lehmer, getLehmerCode(perm));	
	}
	
	@Test
	void permToLehmerBigValues() {
		int[] perm 	 = {57,23,46322,63,27,34,6,7,3532,6563,8,52345};
		int[] lehmer = {1, 0, 8, 4, 0, 2, 0, 0, 0, 1, 0, 0};
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
		int[] lehmer = {7,0,3,1,0,0,1,0};
		int[] perm   = {6,1,4,3,2,3,5,5};
		assertArrayEquals(perm, getPerm(lehmer, ogPerm));	
	}
	
	@Test
	void lehmerCodeToPermBigValues() {
		int[] ogPerm = {1000,2000,3000,4000,5000};
		int[] lehmer = {4,1,0,1,0};
		int[] perm 	 = {5000,2000,1000,4000,3000};
		assertArrayEquals(perm, getPerm(lehmer, ogPerm));	
	}  
}
