package thesis.Common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CommonArray {
	
	public static Double[] removeDuplicates(Double[] list) {
		ArrayList<Double> newList = new ArrayList<Double>();
		for (int i = 0; i < list.length; i++) {
			if (!newList.contains(list[i])) {
				newList.add(list[i]);
			}
		}
		return newList.toArray(new Double[0]);

	}
	
	public static String[] removeDuplicates(String[] list) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
			if (!newList.contains(list[i])) {
				newList.add(list[i]);
			}
		}
		return newList.toArray(new String[0]);

	}
	
	public static Double minValue(Double[] arr) {
		Double min = Double.MAX_VALUE;
		
		for (Double value : arr) {
			if (value < min) {
				min = value;
			}
		}
		return min;
	}
	
	public static Double maxValue(Double[] arr) {
		Double max = Double.MIN_VALUE;
		
		for (Double value : arr) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}
	
	public static int indexOf(String[] arr, String element) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
	
	public static String[] orderArrayByOtherArray(String[] arrToOrder, String[] order) {
		int nextIndex = 0;
		for (int i = 0; i < order.length; i++) {
			String catagoryToBeOrdered = order[i];
			int indexOfNextToOrder = CommonArray.indexOf(arrToOrder, catagoryToBeOrdered);
			if (indexOfNextToOrder != -1) {				
				for (int reorderIndex = indexOfNextToOrder; reorderIndex > nextIndex; reorderIndex--) {
					arrToOrder[reorderIndex] = arrToOrder[reorderIndex-1];
				}
				arrToOrder[nextIndex] = catagoryToBeOrdered;
				nextIndex++;
			}
		}
		return arrToOrder;
	}
	
	public static Double[] arrayListToArray(ArrayList<Double> origList) {
		Double[] doubleList = new Double[origList.size()];
		for (int i = 0; i < doubleList.length; i++) {
			doubleList[i] = origList.get(i);
		}
		return doubleList;
	}
	
	public static boolean valuesUnique(ArrayList<String> values) {
		Set<String> set = new HashSet<String>(values);
		
		if(set.size() < values.size()){
			return false;
		}
		return true;
	}
	
	public static boolean anyNullValues(ArrayList<String> rowNamesToAdd) {
		for (int i = 0; i < rowNamesToAdd.size(); i++) {
			if (rowNamesToAdd.get(i) == null) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<String> generateIncreasingSequence(int size) {
		ArrayList<String> rowNames = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			rowNames.add(String.valueOf(i));
		}
		return rowNames;
	}
	
	public static Object[] convertStringArrayToObjectArray(String[] arr) {
		Object[] objArr = new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			objArr[i] = (Object) arr[i];
		}
		return objArr;
	}
}
