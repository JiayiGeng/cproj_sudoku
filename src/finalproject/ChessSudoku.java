package finalproject;

import java.util.*;
import java.io.*;


public class ChessSudoku
{
    /* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For
     * a standard Sudoku puzzle, SIZE is 3 and N is 9.
     */
    public int SIZE, N;

    /* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
     * not yet been revealed are stored as 0.
     */
    public int grid[][];

    /* Booleans indicating whether of not one or more of the chess rules should be
     * applied to this Sudoku.
     */
    public boolean knightRule;
    public boolean kingRule;
    public boolean queenRule;


    // Field that stores the same Sudoku puzzle solved in all possible ways
    public HashSet<ChessSudoku> solutions = new HashSet<ChessSudoku>();


    /* The solve() method should remove all the unknown characters ('x') in the grid
     * and replace them with the numbers in the correct range that satisfy the constraints
     * of the Sudoku puzzle. If true is provided as input, the method should find finds ALL
     * possible solutions and store them in the field named solutions. */
    public void solve(boolean allSolutions) {

            this.backTrace(0, 0);
            for(ChessSudoku solution: this.solutions){
                this.grid=solution.grid;

                //this.print();
        }
    }
    private void backTrace(int row, int col){
        if (row==this.N) {
            ChessSudoku nGrid =new ChessSudoku(this.SIZE);
            for(int i = 0;i < this.grid.length;i++){
                for (int j = 0; j < this.grid[0].length; j++) {
                    nGrid.grid[i][j] = this.grid[i][j];
                }
            }
            solutions.add(nGrid);
            return;
        }
        if(this.grid[row][col]!=0){

            backTrace(row+(col+1)/this.N,(col+1)%this.N);
            //System.out.println("row: "+ row);
            //System.out.println("col: "+col);
            //System.out.println("row"+row+(col+1)/this.N+"col:"+(col+1)%this.N);
        }
        else{
            for (int k=1;k<=this.N;k++){
                if(sudokuisValid(row,col,k)) {
                    this.grid[row][col] = k;
                    backTrace(row + (col + 1) / this.N, (col + 1) % this.N);
                }


            }
            this.grid[row][col]=0;
        }


    }
    // Helper method (Basic sudoku Rule)
    private boolean sudokuisValid(int row, int col,int val){
        for (int i=0;i<this.N;i++) {

            if (grid[row][i] == val) {
                return false;
            }
            if (grid[i][col]==val){
                return false;
            }
        }
        int rowX=(row/ this.SIZE)* this.SIZE;
        int colY=(col / this.SIZE) * this.SIZE;
        for (int m=rowX;m<rowX+this.SIZE;m++){
            for (int n=colY; n<colY+this.SIZE;n++){
                if (grid[m][n]== val){
                    return false;
                }
            }
        }
        // QueenRule is true
        if(queenRule && val==this.N) {
            for (int i = 0; i < N; i++) {

                if (row + i < N && col + i < N) {
                    if (this.grid[row + i][col + i] == val) {
                        return false;
                    }
                }
                if (row + i < N && col - i >= 0) {
                    if (this.grid[row + i][col - i] == val) {
                        return false;
                    }
                }
                if (row - i >= 0 && col + i < N) {
                    if (this.grid[row - i][col + i] == val) {
                        return false;
                    }
                }
                if (row - i >= 0 && col - i >= 0) {
                    if (this.grid[row - i][col - i] == val) {
                        return false;
                    }
                }
            }
        }
        // King rule
        if(kingRule){
            if(col-1>=0 && row-1>=0){
                if(this.grid[row-1][col-1]==val){
                    return false;
                }
            }
            if(col-1>=0 && row +1 < this.N){
                if(this.grid[row+1][col-1]==val){
                    return false;
                }
            }

            if(col+1<this.N && row-1>=0) {
                if (this.grid[row - 1][col + 1] == val) {
                    return false;
                }
            }

            if(col+1<this.N && row +1 < this.N){
                if(this.grid[row+1][col+1]==val){
                    return false;
                }
            }

        }
        // Knight Rule
        if(knightRule) {
            if (row - 2 >= 0 && col - 1 >= 0) {
                if (this.grid[row - 2][col - 1] == val) {
                    return false;
                }
            }
            if (row - 2 >= 0 && col + 1 < N) {
                if (this.grid[row - 2][col + 1] == val) {
                    return false;
                }
            }

            if (row - 1 >= 0 && col - 2 >= 0) {
                if (this.grid[row - 1][col - 2] == val) {
                    return false;

                }
            }
            if (row - 1 >= 0 && col + 2 < N) {
                if (this.grid[row - 1][col + 2] == val) {
                    return false;
                }
            }

            if (row + 1 < N && col - 2 >= 0) {
                if (this.grid[row + 1][col - 2] == val) {
                    return false;
                }
            }
            if (row + 1 < N && col + 2 < N) {
                if (this.grid[row + 1][col + 2] == val) {
                    return false;
                }
            }

            if (row + 2 < N && col - 1 >= 0) {
                if (this.grid[row + 2][col - 1] == val) {
                    return false;
                }
            }
            if (row + 2 < N && col + 1 < N) {
                if (this.grid[row + 2][col + 1] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean kingValid(int row, int col,int val){
        if(col-1>=0 && row-1>=0){
            if(this.grid[row-1][col-1]==val){
                return false;
            }
        }
        if(col-1>=0 && row +1 < this.N){
            if(this.grid[row+1][col-1]==val){
                return false;
            }
        }

        if(col+1<this.N && row-1>=0) {
            if (this.grid[row - 1][col + 1] == val) {
                return false;
            }
        }

        if(col+1<this.N && row +1 < this.N){
            if(this.grid[row+1][col+1]==val){
                return false;
            }
        }

        return true;
    }

    private boolean queenValid(int row, int col, int val) {
        for (int i=0; i < N; i++) {

            if (row + i < N && col + i < N) {
                if (this.grid[row + i][col + i] == val) {
                    return false;
                }
            }
            if(row + i < N && col - i >=0) {
                if (this.grid[row + i][col - i] == val) {
                    return false;
                }
            }
            if(row - i >=0 && col + i < N) {
                if (this.grid[row - i][col + i] == val) {
                    return false;
                }
            }
            if(row - i>=0 && col - i >=0) {
                if (this.grid[row - i][col - i] == val) {
                    return false;
                }
            }

        }
        return true;
    }
    private boolean knightRule(int row,int col,int val){
        if(row-2>=0 && col-1>=0) {
            if (this.grid[row - 2][col - 1] == val) {
                return false;
            }
        }
        if(row-2>=0 && col+1<N){
            if(this.grid[row-2][col+1]==val){
                return false;
            }
        }

        if(row-1>=0 && col-2>=0) {
            if (this.grid[row - 1][col - 2] == val) {
                return false;

            }
        }
        if(row-1>=0 && col+2<N) {
            if (this.grid[row - 1][col + 2] == val) {
                return false;
            }
        }

        if(row+1<N && col-2>=0){
            if(this.grid[row+1][col-2]==val){
                return false;
            }
        }
        if (row+1<N && col+2<N){
            if(this.grid[row+1][col+2]==val){
                return false;
            }
        }

        if(row+2<N && col-1>=0){
            if(this.grid[row+2][col-1]==val){
                return false;
            }
        }
        if(row+2<N && col+1<N){
            if(this.grid[row+2][col+1]==val){
                return false;
            }
        }
        return true;

    }


    /*****************************************************************************/
    /* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE METHODS BELOW THIS LINE. */
    /*****************************************************************************/

    /* Default constructor.  This will initialize all positions to the default 0
     * value.  Use the read() function to load the Sudoku puzzle from a file or
     * the standard input. */
    public ChessSudoku( int size ) {
        SIZE = size;
        N = size*size;

        grid = new int[N][N];
        for( int i = 0; i < N; i++ )
            for( int j = 0; j < N; j++ )
                grid[i][j] = 0;
    }


    /* readInteger is a helper function for the reading of the input file.  It reads
     * words until it finds one that represents an integer. For convenience, it will also
     * recognize the string "x" as equivalent to "0". */
    static int readInteger( InputStream in ) throws Exception {
        int result = 0;
        boolean success = false;

        while( !success ) {
            String word = readWord( in );

            try {
                result = Integer.parseInt( word );
                success = true;
            } catch( Exception e ) {
                // Convert 'x' words into 0's
                if( word.compareTo("x") == 0 ) {
                    result = 0;
                    success = true;
                }
                // Ignore all other words that are not integers
            }
        }

        return result;
    }


    /* readWord is a helper function that reads a word separated by white space. */
    static String readWord( InputStream in ) throws Exception {
        StringBuffer result = new StringBuffer();
        int currentChar = in.read();
        String whiteSpace = " \t\r\n";
        // Ignore any leading white space
        while( whiteSpace.indexOf(currentChar) > -1 ) {
            currentChar = in.read();
        }

        // Read all characters until you reach white space
        while( whiteSpace.indexOf(currentChar) == -1 ) {
            result.append( (char) currentChar );
            currentChar = in.read();
        }
        return result.toString();
    }


    /* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
     * grid is filled in one row at at time, from left to right.  All non-valid
     * characters are ignored by this function and may be used in the Sudoku file
     * to increase its legibility. */
    public void read( InputStream in ) throws Exception {
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                grid[i][j] = readInteger( in );
            }
        }
    }


    /* Helper function for the printing of Sudoku puzzle.  This function will print
     * out text, preceded by enough ' ' characters to make sure that the printint out
     * takes at least width characters.  */
    void printFixedWidth( String text, int width ) {
        for( int i = 0; i < width - text.length(); i++ )
            System.out.print( " " );
        System.out.print( text );
    }


    /* The print() function outputs the Sudoku grid to the standard output, using
     * a bit of extra formatting to make the result clearly readable. */
    public void print() {
        // Compute the number of digits necessary to print out each number in the Sudoku puzzle
        int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

        // Create a dashed line to separate the boxes
        int lineLength = (digits + 1) * N + 2 * SIZE - 3;
        StringBuffer line = new StringBuffer();
        for( int lineInit = 0; lineInit < lineLength; lineInit++ )
            line.append('-');

        // Go through the grid, printing out its values separated by spaces
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                printFixedWidth( String.valueOf( grid[i][j] ), digits );
                // Print the vertical lines between boxes
                if( (j < N-1) && ((j+1) % SIZE == 0) )
                    System.out.print( " |" );
                System.out.print( " " );
            }
            System.out.println();

            // Print the horizontal line between boxes
            if( (i < N-1) && ((i+1) % SIZE == 0) )
                System.out.println( line.toString() );
        }
    }


    /* The main function reads in a Sudoku puzzle from the standard input,
     * unless a file name is provided as a run-time argument, in which case the
     * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
     * outputs the completed puzzle to the standard output. */
    public static void main( String args[] ) throws Exception {
        InputStream in = new FileInputStream("KnightSudokuEasy3x3.txt");

        // The first number in all Sudoku files must represent the size of the puzzle.  See
        // the example files for the file format.
        int puzzleSize = readInteger( in );
        if( puzzleSize > 100 || puzzleSize < 1 ) {
            System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
            System.exit(-1);
        }

        ChessSudoku s = new ChessSudoku( puzzleSize );

        // You can modify these to add rules to your sudoku
        s.knightRule = true;
        s.kingRule = false;
        s.queenRule = false;

        // read the rest of the Sudoku puzzle
        s.read( in );

        System.out.println("Before the solve:");
        s.print();
        System.out.println();

        // Solve the puzzle by finding one solution.
        s.solve(false);

        // Print out the (hopefully completed!) puzzle
        System.out.println("After the solve:");
        s.print();
    }
}

