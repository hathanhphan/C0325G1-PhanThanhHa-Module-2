package ss13_search_algorithm.bai_tap.binary_search_recursive;

public class BinarySearchRecursive {
    public static void main(String[] args) {
        int[] list = {2, 4, 7, 10, 11, 45, 50, 59, 60, 66, 69, 70, 79};
        int length = list.length - 1;
        System.out.println(binarySearchRecursive(list, 2, 0, length));  /* 0 */
        System.out.println(binarySearchRecursive(list, 11, 0, length)); /* 4 */
        System.out.println(binarySearchRecursive(list, 79, 0, length)); /* 12 */
        System.out.println(binarySearchRecursive(list, 1, 0, length));  /* -1 */
        System.out.println(binarySearchRecursive(list, 5, 0, length));  /* -1 */
        System.out.println(binarySearchRecursive(list, 80, 0, length)); /* -1 */
    }
    static int binarySearchRecursive(int[] list, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (list[mid] == target) {
            return mid;
        } else if (list[mid] < target) {
            return binarySearchRecursive(list, target, mid + 1, right);
        } else {
            return binarySearchRecursive(list, target, left, mid - 1);
        }
    }
}
