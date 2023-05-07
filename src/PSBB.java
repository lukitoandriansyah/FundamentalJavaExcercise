import java.util.*;

public class Main {
    Scanner variable = new Scanner(System.in);

    //Count of Each Family
    public void countOfEachFamily(int paramFromNumberOfFamily) {
        String countOfEachFamInString = variable.nextLine().replaceAll("\\s+", ",");
        List<Integer> countOfEachFam = new ArrayList<>();
        try {
            for (String s : countOfEachFamInString.split(",")) {
                countOfEachFam.add(Integer.parseInt(s));
            }
        }catch (Exception exception){
            System.out.print("Input the number of members in family must be integer");
            System.exit(1);
        }
        LogicBus(countOfEachFam, paramFromNumberOfFamily);
    }

    //Number of Family
    public void numberOfFamily() {
        String numOfFam = variable.nextLine();
        try {
            Integer.parseInt(numOfFam);
            System.out.print("Input the number of members in family (separated by a space) : ");
            countOfEachFamily(Integer.parseInt(numOfFam));
        } catch (Exception exception) {
            System.out.print("Input the number families must be integer and/or not use any separator.");
        }
    }

    //General Logic Bus
    public void LogicBus(List<Integer> paramFromCountOfFam, int paramFromNumOfFam) {
        countOfFamNotEqualToNumOfFam(paramFromCountOfFam, paramFromNumOfFam);
        countOfFamEqualToNumOfFam(paramFromCountOfFam, paramFromNumOfFam);
    }

    //Logic If Count of Fam not Equal to Number of Fam
    public void countOfFamNotEqualToNumOfFam(List<Integer> firstParamFromLogicBus, int secondParamFromLogicBus) {
        if (firstParamFromLogicBus.size() != secondParamFromLogicBus) {
            System.out.print("Input must be equal with count of family");
        }
    }

    //Logic If Count of Fam Equal to Number of Fam
    public void countOfFamEqualToNumOfFam(List<Integer> firstParamFromLogicBus, int secondParamFromLogicBus) {
        if (firstParamFromLogicBus.size() == secondParamFromLogicBus) {
            int maxPassenger = 4;
            int countBus = 0;

            //CountOfShuffle
            int countOfShuffle = 1;
            for (int i = 1; i <= secondParamFromLogicBus; i++) {
                countOfShuffle *= i;
            }

            List<Integer> totalBusCountArr = new ArrayList<>();
            List<List<Integer>> collectionShuffleList = new ArrayList<>();
            List<Integer> copiedFirstParamFromLogicBus = new ArrayList<>(firstParamFromLogicBus);
            Collections.copy(copiedFirstParamFromLogicBus, firstParamFromLogicBus);
            collectionShuffleList.add(copiedFirstParamFromLogicBus);

            for (int init = 0; init < countOfShuffle; init++) {
                for (int i = 0; i < firstParamFromLogicBus.size(); i++) {

                    //logic alternative only for end of list
                    if (i == firstParamFromLogicBus.size() - 1) {
                        firstParamFromLogicBus.set(i, 0);
                        countBus++;
                    }

                    //Base logic to Count Of Bus
                    if (firstParamFromLogicBus.get(i) == maxPassenger) {
                        firstParamFromLogicBus.set(i, 0);
                        countBus++;
                    }

                    if (firstParamFromLogicBus.get(i) > maxPassenger) {
                        firstParamFromLogicBus.set(i, firstParamFromLogicBus.get(i) - maxPassenger);
                        countBus++;
                        while (firstParamFromLogicBus.get(i) >= maxPassenger) {
                            firstParamFromLogicBus.set(i, firstParamFromLogicBus.get(i) - maxPassenger);
                            countBus++;
                        }

                        if (firstParamFromLogicBus.get(i) > 0) {
                            if (i != firstParamFromLogicBus.size() - 1) {
                                if (firstParamFromLogicBus.get(i + 1) > 0 && firstParamFromLogicBus.get(i + 1) - (maxPassenger - firstParamFromLogicBus.get(i)) > 0) {
                                    firstParamFromLogicBus.set(i + 1, firstParamFromLogicBus.get(i + 1) - (maxPassenger - firstParamFromLogicBus.get(i)));
                                    firstParamFromLogicBus.set(i, 0);
                                    countBus++;
                                }
                                if (firstParamFromLogicBus.get(i + 1) > 0 && firstParamFromLogicBus.get(i + 1) - (maxPassenger - firstParamFromLogicBus.get(i)) == 0) {
                                    firstParamFromLogicBus.set(i, 0);
                                    firstParamFromLogicBus.set(i + 1, 0);
                                    countBus++;
                                }
                            } else {
                                firstParamFromLogicBus.set(i, 0);
                                countBus++;
                            }
                        }
                    }

                    if (firstParamFromLogicBus.get(i) > 0 && firstParamFromLogicBus.get(i) < maxPassenger) {
                        if (firstParamFromLogicBus.get(i + 1) > 0 && firstParamFromLogicBus.get(i + 1) - (maxPassenger - firstParamFromLogicBus.get(i)) > 0) {
                            firstParamFromLogicBus.set(i + 1, firstParamFromLogicBus.get(i + 1) - (maxPassenger - firstParamFromLogicBus.get(i)));
                            firstParamFromLogicBus.set(i, 0);

                        } else {
                            firstParamFromLogicBus.set(i, 0);
                            firstParamFromLogicBus.set(i + 1, 0);
                        }
                        countBus++;
                    }
                }

                totalBusCountArr.add(countBus);

                //Strategy to avoid list similarity
                Collections.copy(firstParamFromLogicBus, collectionShuffleList.get(collectionShuffleList.size() - 1));
                Collections.shuffle(firstParamFromLogicBus);
                for (List<Integer> integers : collectionShuffleList) {
                    if (firstParamFromLogicBus.equals(integers)) {
                        Collections.shuffle(firstParamFromLogicBus);
                        while (firstParamFromLogicBus.equals(integers)) {
                            Collections.shuffle(firstParamFromLogicBus);
                        }
                    }
                }

                Collections.copy(copiedFirstParamFromLogicBus, firstParamFromLogicBus);
                collectionShuffleList.add(copiedFirstParamFromLogicBus);
                countBus = 0;
            }

            //Result
            Collections.sort(totalBusCountArr);
            System.out.print("Minimum bus required is : " + totalBusCountArr.get(0));
        }
    }

    public static void main(String[] args) {
        Main psbb = new Main();
        System.out.print("Input the number of families : ");
        psbb.numberOfFamily();
    }
}
