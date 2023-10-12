import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main extends Exception {
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    public static void main(String[] args) {
        System.out.println(getHumanReadableSize(24060));
        System.exit(0);
        System.out.println(getSizeFromHumanReadable("1KB"));
        System.exit(0);

        String folderPath = "/Users/munirismoilov/Desktop/танг";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
//        System.out.println(getFolderSize(file));
//        Set keys = System.getProperties().keySet();
//        for (Object key : keys){
//            System.out.println(key);
//        }

    }

    //Todo: B, Kb, Mb, Gb,Tb
    public static String getHumanReadableSize(long size) {

        for (int i = 0; i < sizeMultipliers.length; i++) {
            double value = size / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value) +""+ sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Very Big!";
    }

    //Todo: B, K, M, G,T
    // 235K = 240640
    public static long getSizeFromHumanReadable(String size) {
        HashMap<Character, Integer> char2multiplier = getMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(
                size.replaceAll("[^0-9]", "")
        );

        return length;
    }

    private static HashMap<Character, Integer> getMultipliers() {

        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++) {
            char2multiplier.put(sizeMultipliers[i], (int) Math.pow(1024, i));

        }
        return char2multiplier;
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}
