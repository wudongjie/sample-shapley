package sampleShapley;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Given an ArrayList, return a permutation set.
 * Providing two methods:
 * getPermutationSet return a set of all permutations of the original ArrayList
 * getSamplePermutation return a set of sampling permutations of the original ArrayList
 */
public class Permutation {
    private ArrayList<Object> permuteList;
    private ArrayList<ArrayList> permutationSet;
    public Permutation(Object[] objects) {
        permuteList = new ArrayList<Object>();
        for (Object i:objects) {
            this.permuteList.add(i);
        }
        permutationSet = new ArrayList<ArrayList>();
    }

    private void swap(int i, int j) {
        Object temp = permuteList.get(i);
        permuteList.set(i, permuteList.get(j));
        permuteList.set(j, temp);
    }

    private void computePermutationSet(int start) {
        if (start < 0) {
            throw new RuntimeException("Start Index mush not be smaller than 0");
        }
        if (start < permuteList.size()) {
            for (int i = start; i < permuteList.size(); i++) {
                swap(start, i);
                computePermutationSet(start + 1);
                swap(start, i);
            }
        }
        else if (start == permuteList.size()) {
            //System.out.println(permuteList);
            permutationSet.add(new ArrayList(permuteList));
        }
    }

    private void samplePermutation(int step) {
        for (int i=0; i<step; i++) {
            Collections.shuffle(permuteList);
            permutationSet.add(new ArrayList(permuteList));
        }
    }

    /**
     * get a nested ArrayList for all permutations of the given ArrayList
     * @return a nested ArrayList in which each ArrayList represents a permutation
     * from the original ArrayList
     */
    public ArrayList<ArrayList> getPermutationSet() {
        if (permuteList.size() > 10) {
            System.out.println("Warning: You have more than ten members!");
            System.out.println("The permutation set could be too large!");
        }
        computePermutationSet(0);
        return permutationSet;
    }

    /**
     * get a sampling permutation set
     * @param step identify the number of samples in the permutation set
     * @return a nested ArrayList in which each ArrayList represents a permutation from
     * the original ArrayList
     */
    public ArrayList<ArrayList> getSamplePermutationSet(int step) {
        samplePermutation(step);
        return permutationSet;
    }
}
