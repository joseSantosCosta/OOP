package intList;

public class IntList {
	private int[] sequence;
	
	public int[] getSequence() {
		return sequence;
	}
	
	public int[] addToEnd(int number) {
		int newArray[] = new int[sequence.length + 1];
		for(int i = 0;i < sequence.length;++i) {
			newArray[i] = sequence[i];
		}
		newArray[sequence.length+1] = number;
		return newArray;
	}
	
}
