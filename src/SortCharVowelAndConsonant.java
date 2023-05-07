import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortCharacter {
    Scanner variable = new Scanner(System.in);

    //Create Input and Print
    public void inputString(){
        String str = variable.nextLine().toLowerCase().replaceAll("\\s+","");
        printVowelList(str);
        printConsonantList(str);
    }

    //Handling Array that contains vowel only
    public List<String> vowelList(String paramPrintVowelList){
        List<String> vowel = new ArrayList<>();
        String[] strArr = paramPrintVowelList.split("");
        for (int i = 0; i<strArr.length;i++){
            char strToChar = paramPrintVowelList.charAt(i);
            if(strToChar=='a'||strToChar=='i'||strToChar=='u'||strToChar=='e'||strToChar=='o'){
                vowel.add(strArr[i]);
            }
        }

        System.out.println("Vowel Characters :");
        return vowel;
    }

    //Handling Array that contains consonant only
    public List<String> consonantList(String paramPrintConsonantList){
        List<String> consonant = new ArrayList<>();
        String[] strArr = paramPrintConsonantList.split("");
        for (int i = 0; i<strArr.length;i++){
            char strToChar = paramPrintConsonantList.charAt(i);
            if(!(strToChar=='a'||strToChar=='i'||strToChar=='u'||strToChar=='e'||strToChar=='o')){
                consonant.add(strArr[i]);
            }
        }
        System.out.println("Consonant Characters :");
        return consonant;
    }

    //Handling Logic Array that contains vowel only
    public List<String> logicHandlingVowelList(String paramFromInputString){
        List<String> originVowelList = vowelList(paramFromInputString);
        List<String> collectionVowelList = new ArrayList<>();
        List<String> copiedOriginVowelList = new ArrayList<>(originVowelList);
        Collections.copy(copiedOriginVowelList, originVowelList);
        for (int i = 0; i<copiedOriginVowelList.size(); i++){
            while (copiedOriginVowelList.get(i)!=null){
                collectionVowelList.add(copiedOriginVowelList.get(i));
                copiedOriginVowelList.set(i,null);
                for(String s:copiedOriginVowelList){
                    if(s!=null && s.equals(collectionVowelList.get(collectionVowelList.size()-1))){
                        collectionVowelList.add(s);
                        copiedOriginVowelList.set(copiedOriginVowelList.indexOf(s),null);
                    }
                }
            }
        }

        return collectionVowelList;
    }

    //Handling Logic Array that contains consonant only
    public List<String> logicHandlingConsonantList(String paramFromInputString){
        List<String> originConsonantList = consonantList(paramFromInputString);
        List<String> collectionConsonantList = new ArrayList<>();
        List<String> copiedOriginConsonantList = new ArrayList<>(originConsonantList);
        Collections.copy(copiedOriginConsonantList, originConsonantList);
        for (int i = 0; i<copiedOriginConsonantList.size(); i++){
            while (copiedOriginConsonantList.get(i)!=null){
                collectionConsonantList.add(copiedOriginConsonantList.get(i));
                copiedOriginConsonantList.set(i,null);
                for(String s:copiedOriginConsonantList){
                    if(s!=null && s.equals(collectionConsonantList.get(collectionConsonantList.size()-1))){
                        collectionConsonantList.add(s);
                        copiedOriginConsonantList.set(copiedOriginConsonantList.indexOf(s),null);
                    }
                }
            }
        }
        return collectionConsonantList;
    }

    public void printVowelList(String paramFromInputString){
        List<String> arrVowelList = logicHandlingVowelList(paramFromInputString);
        for (String s : arrVowelList) {
            System.out.print(s);
        }
        System.out.println();
    }

    public void printConsonantList(String paramFromInputString){
        List<String> arrConsonantList = logicHandlingConsonantList(paramFromInputString);
        for (String s : arrConsonantList) {
            System.out.print(s);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortCharacter sortCharacter = new SortCharacter();
        System.out.print("Input one line of words (S) : ");
        sortCharacter.inputString();
    }
}
