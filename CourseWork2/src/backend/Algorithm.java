package backend;


import java.util.ArrayList;

public class Algorithm {

    public int binarySearch(ArrayList<Integer> arrayListData, int x) {
        int left = 0, right = arrayListData.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arrayListData.get(mid) == x)
                return mid;

            if (arrayListData.get(mid) < x)
                left = mid + 1;

            else
                right = mid - 1;
        }

        return -1;
    }

    public int binarySearchString(ArrayList<String> arrayListData, String searchData) {

        int min = 0;
        int max = arrayListData.size();

        while (min <= max) {
            System.out.println("min: " + min);
            System.out.println("max: " + max);
            int mid = (min + max) / 2;
            String[] temp_id = arrayListData.get(mid).split("~");
            System.out.println("Temp ID: " + temp_id[1]);
            if (arrayListData.get(mid).equals(searchData + "~" + temp_id[1])) {
                return Integer.parseInt(temp_id[1]) - 1;
            } else if (arrayListData.get(mid).compareTo(searchData + "~" + temp_id[1]) < 0) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }
}
