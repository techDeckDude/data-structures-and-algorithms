package dsa.sort.comparison;

public class QuickSelect {

    int getKthElement(int[] input, int k) {
        return quickSelect(input, input.length-k, 0, input.length-1);
    }

    int quickSelect(int[] input, int target, int l, int r) {
        if(l > r) {
            return input[r];
        }
        int partition = partition(input, l, r);
        if(partition < target) {
            return quickSelect(input, target, partition+1, r);
        } else if(partition > target) {
            return quickSelect(input, target, l, partition - 1);
        } else {
            return input[partition];
        }
    }

    int partition(int[] input, int l, int r) {
        int pivot = input[r];
        int i = l;
        for(int j = l; j < r; j++) {
            if(input[j] < pivot) {
                swap(input, i, j);
                i++;
            }
        }
        swap(input, i, r);
        return i;
    }

    void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    public static void main(String[] args) {
        QuickSelect qs = new QuickSelect();
        int[] input = new int[] {22,4,56,4,1,3};//1,3,4,4,22,56
        for(int i = 1; i <= input.length; i++) {
            int ans = qs.getKthElement(input, i);
            System.out.println("ans = "+ans);
        }
    }
}
