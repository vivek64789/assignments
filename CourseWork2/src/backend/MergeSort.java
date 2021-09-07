package backend;

import java.util.ArrayList;

public class MergeSort {

    public ArrayList<String> divide(ArrayList<String> inputArrayList, int startIndex, int endIndex) {

        // dividing till
        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int mid = (endIndex + startIndex) / 2;
            divide(inputArrayList, startIndex, mid);
            divide(inputArrayList, mid + 1, endIndex);

            merger(inputArrayList, startIndex, mid, endIndex);
        }
        return inputArrayList;
    }

    public void merger(ArrayList<String> inputArrayList, int startIndex, int midIndex, int endIndex) {

        ArrayList<String> mergedSortedArray = new ArrayList<>();

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while (leftIndex <= midIndex && rightIndex <= endIndex) {
            if (inputArrayList.get(leftIndex).compareTo(inputArrayList.get(rightIndex)) < 0) {
                mergedSortedArray.add(inputArrayList.get(leftIndex));
                leftIndex++;
            } else {
                mergedSortedArray.add(inputArrayList.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex <= midIndex) {
            mergedSortedArray.add(inputArrayList.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex <= endIndex) {
            mergedSortedArray.add(inputArrayList.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        while (i < mergedSortedArray.size()) {
            inputArrayList.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

    public ArrayList<String> divideDescending(ArrayList<String> inputArrayList, int startIndex, int endIndex) {

        // dividing till
        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int mid = (endIndex + startIndex) / 2;
            divideDescending(inputArrayList, startIndex, mid);
            divideDescending(inputArrayList, mid + 1, endIndex);

            mergerDescending(inputArrayList, startIndex, mid, endIndex);
        }
        return inputArrayList;
    }

    public void mergerDescending(ArrayList<String> inputArrayList, int startIndex, int midIndex, int endIndex) {

        ArrayList<String> mergedSortedArray = new ArrayList<>();

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while (leftIndex <= midIndex && rightIndex <= endIndex) {
            if (inputArrayList.get(leftIndex).compareTo(inputArrayList.get(rightIndex)) > 0) {
                mergedSortedArray.add(inputArrayList.get(leftIndex));
                leftIndex++;
            } else {
                mergedSortedArray.add(inputArrayList.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex <= midIndex) {
            mergedSortedArray.add(inputArrayList.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex <= endIndex) {
            mergedSortedArray.add(inputArrayList.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        while (i < mergedSortedArray.size()) {
            inputArrayList.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

}

