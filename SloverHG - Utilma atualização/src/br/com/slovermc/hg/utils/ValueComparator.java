package br.com.slovermc.hg.utils;

import java.util.Comparator;
import java.util.List;

public class ValueComparator implements Comparator<Integer> {

	List<Integer> integers = null;
	
	public ValueComparator(List<Integer> integers) {
		this.integers = integers;
	}
	public int compare(Integer a, Integer b) {
		 if (a > b) {
		     return -1;
		 }
		 return 1;
	}
}
