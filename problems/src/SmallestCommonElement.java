import org.jetbrains.annotations.NotNull;

/*
   Problem Description at GeeksForGeeks:
   https://www.geeksforgeeks.org/find-common-element-rows-row-wise-sorted-matrix/
*/

public class SmallestCommonElement {

    public static final int ELEMENT_NOT_FOUND = -1;

    /**
     * This method returns the common smallest element in the matrix, given the rows of the matrix are sorted.
     * It is assumed that the matrix's row does not contain duplicates
     *
     * @param mat: int: 2D sorted matrix per row
     * @return: return the common smallest element in the matrix
     */
    public int smallestCommonElement(@NotNull int[][] mat) {
        int numRows = mat.length;
        int numCols = mat[0].length;

        for(int currCol = 0; currCol < numCols; currCol++){
            int currElementInTopRow = mat[0][currCol];
            /* Check if other rows (below top row) have this element; if they do, we have the common element */
            boolean otherRowsHaveThisElement = true;
            for(int currRow = 1; currRow < numRows; currRow++){
                otherRowsHaveThisElement = binarySearch(mat[currRow], currElementInTopRow);
                if(!otherRowsHaveThisElement)
                    break;
            }

            /* If the value is still True, this means that all rows contain this element */
            if(otherRowsHaveThisElement)
                return currElementInTopRow;
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * Function to perform a binary search:
     * Given a sorted array, this method checks if an element is present in the array.
     *
     * @param currRow: int: currentRow (or an array) where element's presence is being checked.
     * @param elementToSearch: int: element to search for
     * @return: return True/False depending on the presence of `elementToSearch` in `currRow` array.
     */
    public boolean binarySearch(@NotNull int currRow[], int elementToSearch){
        int lPtr = 0;
        int rPtr = currRow.length - 1;
        while(rPtr >= lPtr){
            int midIdx = (lPtr + rPtr) / 2;
            int currMidElement = currRow[midIdx];
            if(currMidElement == elementToSearch)
                return true;
            else if(currMidElement > elementToSearch)
                rPtr = midIdx - 1;
            else
                lPtr = midIdx + 1;
        }

        return false;
    }
}
