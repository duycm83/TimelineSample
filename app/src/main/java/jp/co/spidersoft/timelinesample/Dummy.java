package jp.co.spidersoft.timelinesample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lamphucduy on 16/06/26.
 */
public class Dummy {
    private static int count;
    private static List<PostDetail> list = new ArrayList<>();
    public static List<PostDetail> getDataSet() {
        int size = 20;
        for (int i = 0; i < size; i++) {
            list.add(new PostDetail("Edogawa " + count++, System.currentTimeMillis()));
        }
        return list;
    }

    public static List<PostDetail> loadMore() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int curentSize = list.size();
        int size = curentSize+20;
        for (int i = curentSize; i < size; i++) {
            list.add(new PostDetail("Edogawa " + count++, System.currentTimeMillis()));
        }
        return list;
    }

    public static int addOneMore() {
        list.add(new PostDetail("Edogawa " + count++, System.currentTimeMillis()));
        return list.size();
    }
}
