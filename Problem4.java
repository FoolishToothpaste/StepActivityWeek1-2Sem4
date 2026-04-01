import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double r, double v) {
        this.name = name;
        this.returnRate = r;
        this.volatility = v;
    }
}

public class Problem4 {
    static void mergeSort(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(Asset[] arr, int l, int m, int r) {
        Asset[] temp = new Asset[r - l + 1];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i].returnRate <= arr[j].returnRate)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (i = l, k = 0; i <= r; i++, k++)
            arr[i] = temp[k];
    }

    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        double pivot = arr[high].returnRate;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot ||
                    (arr[j].returnRate == pivot &&
                            arr[j].volatility < arr[high].volatility)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] arr = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        mergeSort(arr, 0, arr.length - 1);
        for (Asset a : arr)
            System.out.println(a.name + ":" + a.returnRate);

        quickSort(arr, 0, arr.length - 1);
        for (Asset a : arr)
            System.out.println(a.name + ":" + a.returnRate);
    }
}