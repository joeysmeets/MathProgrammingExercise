import java.util.ArrayList;
public class SLE {
    private static ArrayList<Integer> ausgezeichneteSpalten;
    public static boolean DEBUG = false;
    // Fehlerschranke anpassen, um Rundungsfehler beim Test zu vermeiden
    public static double EPS = 1e-6;

    //Schritt I in L.3.4.6: Formt (A|b) so um, dass Â 2.Normalform (NF2) hat. 
    // Zur Berechnung können Sie ein maximales Element in den ausgezeichneten Spalten verwenden.
    /**
     * @param double[][] Ab
     */
    public static double[][] calcNF2(double[][] Ab){
        int lead = 0;
        ausgezeichneteSpalten = new ArrayList<>();
        int m = Ab.length;
        int n = Ab[0].length-1;
        double[][] NF2 = new double[m][n+1];
        for(int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                NF2[i][j] = Ab[i][j];
            }
        }
        int i;
        boolean quit = false;
        for (int row = 0; row < m && !quit; row++) {
            if(DEBUG) {
                printMatrix(NF2);
                System.out.println();
            }
            // if lead is greater than or equal to colCount break
            if (lead >= n) {
                quit = true;
                break;
            }
            i = row;
            // find next done column (lead)
            while (!quit && NF2[i][lead] == 0) {
                i++;
                // if a value not equal to 0 in the column, then increase lead
                if (i == m) {
                    i = row;
                    lead++;
                    // if lead equals colCount, break
                    if (lead == n) {
                        quit = true;
                        break;
                    }
                }
            }
            if (!quit) {
                ausgezeichneteSpalten.add(lead);
                pivotisiere(NF2, row, lead);
                if(DEBUG){
                    System.out.println("Nach Pivot: ");
                    printMatrix(NF2);
                    System.out.println("\n");
                }
                if (NF2[row][lead] != 0){
                    for (i = 0; i < m; i++) {
                        if (i != row)
                            subtractRows(NF2, row, i, lead);
                    }
                    multiplyRow(NF2, row, 1.0 / NF2[row][lead]);
                }
            }
        }
        if (DEBUG){
            printMatrix(NF2);
        }
        return NF2; 
    }

    private static void pivotisiere(double[][] matrix, int row, int col) {
        int maxRow = row;
        double maxValue = matrix[maxRow][col];

        for(int r = row+1; r < matrix.length; r++){
            if( Math.abs(matrix[r][col]) > maxValue){
                maxRow = r;
                maxValue = Math.abs(matrix[r][col]);
            }
        }
        if(row != maxRow){
            swapRows(matrix, maxRow, row);
        }
    }

    /**
     * swaps the values of two rows
     * @param double[][] matrix
     * @param int row1
     * @param int row2
     */
    static void swapRows(double [][] matrix, int row1, int row2) {
        double [] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    /**
     * formats the matrix to be printed nicely formatted
     * @param double [][] matrix
     */
    public static void printMatrix(double[][] matrix) {
        if (matrix == null) {
            return;
        }
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("|");
            for (int column = 0; column < matrix[row].length; column++) {
                if (column != 0) {
                    System.out.print(" ");
                }
                System.out.printf("%8.4f", matrix[row][column]);
            }
            System.out.println("|");
        }
    }

    /**
     * subtracts the values from one row 
     * @param double[][] matrix
     * @param int row
     * @param int from_this_row
     * @param int column
     */
    static void subtractRows(double [][] matrix, int row, int from_this_row, int column) {
        double scalarRow = matrix[from_this_row][column];
        double scalarFromThisRow = matrix[row][column];
        for(int c = 0; c < matrix[0].length; c++) {
            matrix[from_this_row][c] = 
            scalarFromThisRow * matrix[from_this_row][c] - scalarRow * matrix[row][c];
        }
        // erneut normieren
        if(row > from_this_row){
            multiplyRow(matrix, from_this_row, 1.0 / scalarFromThisRow);
        }
    }

    /**
     * multiplying a certain row with a scaler
     * @param double[][] matrix
     * @param int row
     * @param double scalar
     */
    static void multiplyRow(double [][] matrix, int row, double scalar) {
        for(int c = 0; c < matrix[0].length; c++){
            matrix[row][c] *= scalar;
        }
    }

    /*
    Hat Ax = b Lösungen, so berechnet solve die Lösungen xh des homogenen Systems und eine spezielle Lösung xs des
    inhomogenen Systems und gibt diese in der Matrix xhs = (xh|xs) zurück. 
    xhs ist also eine n x(d+1)-Matrix, wobei n die Anzahl der Unbestimmten und d = n – rg(A) die Dimension des Lösungsraums des homogenen Systems ist.
    Hat Ax = b keine Lösungen, so wird null zurückgegeben.
     */
    /**
     * @param double[][] Ab
     */
    public static double[][] solve(double[][] Ab){
        int rowCount = Ab.length;
        int colCount = Ab[0].length;
        int numberUnknown = colCount - 1; // Durch Ihren Code ersetzen!
        double[][] NF2 = calcNF2(Ab);
        int rangA = getRang(NF2, false);
        int rangAb = getRang(NF2, true);
        if (rangA == rangAb) {
            // Dimension of the solution matrix and +1 because of specific solution
            int dim = numberUnknown - rangA + 1;
            double[][] xhs = new double[numberUnknown][dim];
            if (rangA == numberUnknown) {
                // Re-substitution
                for (int i = rowCount - 1; i >= 0; i--) {
                    xhs[i][dim-1] = NF2[i][numberUnknown];
                }
            } else {  // endless amount of solutions
                // homogene solution
                int hom = 0;
                for(int var = 0; var < numberUnknown; var++){
                    // für die nicht ausgezeichneten Spalten
                    if(!ausgezeichneteSpalten.contains(var)){
                        for(int r = 0; r < rowCount; r++){
                            if( NF2[r][var] != 0){
                                xhs[ausgezeichneteSpalten.get(r)][hom] = - NF2[r][var];
                            }
                        }
                        xhs[var][hom] = 1;
                        hom++;
                    }
                }
                // specific solution
                for(int i = 0; i < ausgezeichneteSpalten.size(); i++){
                    xhs[ausgezeichneteSpalten.get(i)][dim-1] = NF2[i][colCount-1];
                }
            }
            // output the matrix with solution of the homogeneous system
            // and specific solution of the inhomogeneous system in last column
            return xhs;
        } else { // no solution
            return null;
        }
    }

    /**
     * returns the rank of a matrix
     * @param double[][] matrix
     * @param boolean lastColumn
     * @return
     */
    private static int getRang(double[][] matrix, boolean lastColumn) {
        int rang = matrix.length - 1;
        while (zeroRow(matrix, rang, lastColumn) && rang >= 0) {
            rang--;
        }
        return rang + 1;
    }

    /**
     * Checks if a row in the matrix is a zero row. The third parameter is 'true' 
     * if the last column should be counted
     * @param double[][] matrix
     * @param int row
     * @param boolean lastColumn
     * @return
     */
    private static boolean zeroRow(double[][] matrix, int row, boolean lastColumn){
        int maxSpalte = matrix[row].length + ((lastColumn)? 0 : -1);
        for (int i = 0; i < maxSpalte; i++) {
            if( Math.abs(matrix[row][i]) > EPS){
                return false;
            }
        }
        return true;
    }
}