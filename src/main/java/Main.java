import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main extends Exception {


    public static void main(String[] args) {

        String folderPath = "/Users/munirismoilov/Desktop/25svadb";
        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
       pool.invoke(calculator);
        System.out.println(root.getSize());
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
        System.out.println(root.toString());
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
