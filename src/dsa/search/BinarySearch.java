package dsa.search;

/**
 * 
 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 * Important Points:
 * 
 * Binary Search Templates:
 *  1. returning l pointer as the index where target lives (when l == r and input[m] == target, move r off of target)
 *  2. returning r pointer as the index where target lives (when l == r and input[m] == target, move l off of target)
 * 
 * NOTE: do not return r if your template maps the l pointer to the index where target lives, 
 *  and vice versa for returning l if r is mapped to land at the target index
 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 * l and r will both converge to the target value after log(n) iterations
 * 
 * Base Cases for returning l pointer (when l == r and input[m] == target, move r off of target):
 * 
 * if target is in array:
 *  l and r will both converge, such that when l and r and equal, 
 *  they will both point to the index of the target value (but the loop will only 
 *  exit when l > r). l and r converging is the natural state of binary search
 *  when the target is found. i.e. that's what happens when you compute a mid between l and r,
 *  and move l and r based on the comparison of target and input[m]. The algorithm
 *  magically works
 * 
 * if target is not in array:
 *  if target > last element in array
 *      l == array.length
 *      r == array.length - 1
 *  if target < first element in array
 *      l == 0
 *      r == -1  
 *  if first element < target < last element
 *      l == first element > target (i.e. next largest)
 *      r == first element < target (i.e. next smallest)
 *      ** essentialy l and r point to the closest indexes where target would live between
 * 
 * 
 * Base Cases for returning r pointer (when l == r and input[m] == target, move l off of target):
 * 
 * if target is in array:
 *  l and r will both converge, such that when l and r and equal, 
 *  they will both point to the index of the target value (but the loop will only 
 *  exit when l > r). l and r converging is the natural state of binary search
 *  when the target is found. i.e. that's what happens when you move l and r
 *  based on the comparison of target and input[m]
 * 
 * if target is not in array:
 *  if target > last element in array
 *      l == array.length
 *      r == array.length - 1
 *  if target < first element in array
 *      l == 0
 *      r == -1  
 *  if first element < target < last element
 *      l == first element > target (i.e. next largest)
 *      r == first element < target (i.e. next smallest)
 *      ** essentialy l and r point to the closest indexes where target would live between
 */
public class BinarySearch {

    /**
     * does not return early if target is found, instead does all log(n) iterations
     * @param input
     * @param target
     * @return
     */ 
    int search(int[] input, int target) {
        int l = 0;
        int r = input.length - 1;


        while(l <= r) {
            int m = l + (r-l)/2;
            if(target <= input[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l;
    }

    /**
     * returns early if target is found, and may do less than log(n) iterations
     * 
     * @param input
     * @param target
     * @return
     */
    int searchOptimized(int[] input, int target) {
        int l = 0;
        int r = input.length - 1;

        while(l <= r) {
            int m = l + (r-l)/2;
            if(target < input[m]) {
                r = m - 1;
            } else if(target > input[m]) {
                l = m + 1;
            } else {
                return m;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] input;
        BinarySearch binarySearch = new BinarySearch();
       
        //Test 1.0: target not in array. target > input[nums.length-1]
        System.out.println("Test 1.0: target not in array. target > input[nums.length-1]");
        input = new int[] {1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch.search(input, 10));
        //Test 1.1: target not in array. target < input[0]
        System.out.println("Test 1.1: target not in array. target < input[0]");
        input = new int[] {1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch.search(input, 0));
        //Test 1.1: target not in array. input[0] < target < input[nums.length-1]
        System.out.println("Test 1.1: target not in array. input[0] < target < input[nums.length-1]");
        input = new int[] {1,2,3,4,5,6,7,8,9,11};
        System.out.println(binarySearch.search(input, 10));
        
        //Test 2.0: target is in array
        System.out.println("Test 2.0: target is in array");
        input = new int[] {1,2,3,4,5,6,7,8,9,10};
        for(int i = 0; i < 10; i++) {
            System.out.println(binarySearch.search(input, i+1));
        }
    }
}

/**
 * this is also the default binary search implementation
 * 
 * when l == r and input[m] == target,
 * move r off of target.
 * 
 */
class BinarySearchLeftMostTarget {
    
    int search(int[] input, int target) {
        int l = 0;
        int r = input.length - 1;

        while(l <= r) {
            int m = l + (r-l)/2;
            if(target <= input[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        int[] input = new int[] {1,2,2,2,2,3,3,3,4,4,5,6,6,7,7};
        BinarySearchLeftMostTarget search = new BinarySearchLeftMostTarget();
        System.out.println(search.search(input, 7));
    }
}

/**
 * when l == r and input[m] == target,
 * move l off of the target
 */
class BinarySearchRightMostTarget {
    
    int search(int[] input, int target) {
        int l = 0;
        int r = input.length - 1;

        while(l <= r) {
            int m = l + (r-l)/2;
            if(target >= input[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int[] input = new int[] {1,2,2,2,2,3,3,3,4,4,5,6,6,7,7};
        BinarySearchRightMostTarget searchRightMostTarget = new BinarySearchRightMostTarget();
        BinarySearchLeftMostTarget searchLeftMostTarget = new BinarySearchLeftMostTarget();
        
        input = new int[] {0,1,2,3,4,5,6,7,8,9,11,11,11};
        //test 1.0: target not in input: target < nums[0]
        System.out.println("array range: 0 to 12\n");
        
        System.out.println();

        System.out.println("test 1.0: target not in input: target < nums[0]\n");
        System.out.println("searchLeftMostTarget: index "+searchLeftMostTarget.search(input, -1));
        System.out.println("searchRightMostTarget: index "+searchRightMostTarget.search(input, -1));
        
        System.out.println();
        
        System.out.println("test 1.1: target not in input: target > nums[nums.length-1]\n");
        System.out.println("searchLeftMostTarget: index "+searchLeftMostTarget.search(input, 12));
        System.out.println("searchRightMostTarget: index "+searchRightMostTarget.search(input, 12));

        System.out.println();
        
        System.out.println("test 1.2: target not in input: nums[0] < target < nums[nums.length-1]\n");
        System.out.println("searchLeftMostTarget: index "+searchLeftMostTarget.search(input, 10));
        System.out.println("searchRightMostTarget: index "+searchRightMostTarget.search(input, 10));
        
        System.out.println();
        
        System.out.println("test 1.3: target is in input:\n");
        System.out.println("searchLeftMostTarget: index "+searchLeftMostTarget.search(input, 9));
        System.out.println("searchRightMostTarget: index "+searchRightMostTarget.search(input, 9));
        
        System.out.println();
        
        System.out.println("test 1.4: target is in input: duplicate values\n");
        System.out.println("searchLeftMostTarget: index "+searchLeftMostTarget.search(input, 11));
        System.out.println("searchRightMostTarget: index "+searchRightMostTarget.search(input, 11));
        input = new int[] {0,1,2,3,4,5,7,7,8,8,8,9,11};
        System.out.println("test 1.4: target is in input: duplicate values\n");
        System.out.println("searchLeftMostTarget: index "+searchLeftMostTarget.search(input, 7));
        System.out.println("searchRightMostTarget: index "+searchRightMostTarget.search(input, 7));


    }
    /**
     * 
     * what's in the difference between moving l off m when l==r and target==m
     * and moving r off m when l==r and target==m?
     */
}