public class Solution {
    public int reverse(int x) {
        boolean neg = false;
        String num = Integer.toString(x);
        if (x < 0) {
            neg = true;
            num = num.substring(1);
        }
        long retVal = Long.parseLong(new StringBuilder(num).reverse().toString());
        if (neg) retVal *= -1;
        if (retVal <= Integer.MAX_VALUE && retVal >= Integer.MIN_VALUE) return (int) retVal;
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(-2147483648));
    }
}