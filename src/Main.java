import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\vadim\\Downloads\\hyperskill-dataset-62194310.txt";
        File file = new File(path);

        try {
            Scanner sc = new Scanner(file);
            String[] searchArrayString = sc.nextLine().split(" ");
            int[] integerArray = convertStringArrayToIntegerArray(searchArrayString);
            String[] referenceArrayString = sc.nextLine().split(" ");
            for (String number: referenceArrayString) {
                int numberAsInteger = Integer.parseInt(number);
                System.out.print(findApproximateNumber(integerArray, numberAsInteger) + " ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    static int[] convertStringArrayToIntegerArray(String[] stringArray) {
        int[] integerArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            integerArray[i] = Integer.parseInt(stringArray[i]);
        }
        return integerArray;
    }

    static int findApproximateNumber(int[] sourceArray, int referenceNumber) {
        /* This method finds closest number to given reference number from specified array */
        int middle = sourceArray.length / 2 - 1;
        int middleItem = sourceArray[middle];
        int nextToMiddleItem = sourceArray[middle + 1];

        if (middleItem < referenceNumber && nextToMiddleItem > referenceNumber) {
            sourceArray = new int[]{middleItem, nextToMiddleItem};
        }

        if (sourceArray.length == 2) {
            int firstElementDistance = Math.abs(sourceArray[0] - referenceNumber);
            int secondElementDistance = Math.abs(sourceArray[1] - referenceNumber);
            return firstElementDistance <= secondElementDistance ? sourceArray[0] : sourceArray[1];
        }

        if (middleItem == referenceNumber) {
            return referenceNumber;
        } else if (middleItem > referenceNumber) {
            int newEndItemIndex = middle + 1;
            if (newEndItemIndex == 1) {
                newEndItemIndex = 2;
            }
            return findApproximateNumber(Arrays.copyOfRange(sourceArray, 0, newEndItemIndex), referenceNumber);
        } else {
            int newStartItemIndex = middle + 1;
            return findApproximateNumber(Arrays.copyOfRange(sourceArray, newStartItemIndex, sourceArray.length), referenceNumber);
        }
    }
}