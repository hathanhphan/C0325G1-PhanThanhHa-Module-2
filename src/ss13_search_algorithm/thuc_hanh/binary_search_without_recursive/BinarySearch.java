package ss13_search_algorithm.thuc_hanh.binary_search_without_recursive;

public class BinarySearch {
    public static void main(String[] args) {
        int[] list = {2, 4, 7, 10, 11, 45, 50, 59, 60, 66, 69, 70, 79};
        System.out.println(binarySearch(list, 2));  /* 0 */
        System.out.println(binarySearch(list, 11)); /* 4 */
        System.out.println(binarySearch(list, 79)); /* 12 */
        System.out.println(binarySearch(list, 1));  /* -1 */
        System.out.println(binarySearch(list, 5));  /* -1 */
        System.out.println(binarySearch(list, 80)); /* -1 */
    }
    static int binarySearch(int[] list, int target) {
        int left = 0;
        int right = list.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (list[mid] == target) {
                return mid;
            } else if (list[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
