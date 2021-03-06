package week2;


class PopAndSum {

    public static int popAndSumTwoStack(int k, int[] a, int[] b) {

        int aIndex = 0;
        int bIndex = 0;
        int count = 0;
        int sum = 0;

        // moving bIndex to the position of last elements till where elements can be taken from B

        while (bIndex < b.length && sum + b[bIndex] <= k) {
            sum += b[bIndex];
            bIndex++;
        }
        // whenever b index reaches end or sum>k, more elements can not be taken from it, so decrease
        // bIndex and increase count;
        bIndex--;
        count = bIndex + 1;

        while (aIndex < a.length && bIndex < b.length) {
            sum += a[aIndex];
            if (sum > k) {
                while (bIndex >= 0) {
                    sum -= b[bIndex];
                    bIndex--;
                    if (sum <= k) break;
                }
                // even if no elements is taken from B stack, but still sum is greater than k,
                // then a[Index] should not be chosen and loop terminates

                if (sum > k && bIndex < 0) {
                    aIndex--;
                    break;
                }
            }
            count = Math.max(aIndex + bIndex + 2, count);
            aIndex++;
        }

        return count;
    }


    public static void main(String[] args) {
        
        int aSize = 5;
        int bSize = 4;
        int k = 11;

        int[] a = new int[aSize];
        a[0] = 4;
        a[1] = 3;
        a[2] = 6;
        a[3] = 7;
        a[4] = 9;

        int[] b = new int[bSize];
        b[0] = 1;
        b[1] = 2;
        b[2] = 9;
        b[3] = 5;

        int result = popAndSumTwoStack(k, a, b);
        System.out.println(result);
    }

}
