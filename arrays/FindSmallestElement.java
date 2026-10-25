// 1. Find Smallest Element
// Problem
// Given an unsorted array, find the smallest element.
// Example:
// Input:  [5, 2, 8, 1, 6]
// Output: 1
// Thinking
// Keep the first element as the smallest seen so far.
// Scan the remaining elements. Whenever we find something smaller, update min.

public class FindSmallestElement{
    public static int findSmallestElementInArray(int[] arr){
        int smallestNum = arr[0];
        for(int i=1; i<arr.length; i++){
            if(arr[i] < smallestNum){
                smallestNum = arr[i];
            }
        }
        return smallestNum;
    }
}