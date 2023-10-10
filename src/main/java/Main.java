import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

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
