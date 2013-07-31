import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BenchmarkHttp {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("BenchmarkHttp url content[thread-count]");
            System.out.println("e.g. BenchmarkHttp http://www.baidu.com {\"name\":\"xuhuiqing\"}");
            return;
        }

        System.out.println("ID    CURRENT AVERAGE MIN   MAX   TOTAL-TIME/ms TOTAL-COUNT LAT/ms");
        ExecutorService pool = Executors.newCachedThreadPool();
        int threadCount = Runtime.getRuntime().availableProcessors() * 2 + 1;

        if (args.length > 2) {
            threadCount = Integer.parseInt(args[2]);
        }

        Thread statisticsThread = new Thread(Statistics.getInstance());
        statisticsThread.setDaemon(true);
        statisticsThread.start();

        for (int i = 0; i < threadCount; i++) {
            pool.execute(new Post(args[0], args[1]));
        }

        pool.shutdown();
    }

    private static class Statistics extends Thread {
        private static Statistics instance = new Statistics();
        private AtomicLong sum = new AtomicLong(0);
        private AtomicLong count = new AtomicLong(0);

        private Statistics() {
        }

        public static Statistics getInstance() {
            return instance;
        }

        public void update(long time) {
            sum.addAndGet(time);
            count.incrementAndGet();
        }

        public void run() {
            Thread.currentThread().setName("statistics thread");

            long max_num = -1, precount = 0, current = 0, index = 1, sum, count, avg, min_num = Long.MAX_VALUE;
            double time;

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                sum = this.sum.get();
                count = this.count.get();
                current = count - precount;
                max_num = current > max_num ? current : max_num;
                min_num = current < min_num ? current : min_num;
                avg = count / index;
                time = count != 0 ? sum / count : -1L;

                System.out.println(String.format(
                        "%-5d %-7d %-7d %-5d %-5d %-13d %-11d %.3f", (index++),
                        current, avg, min_num,
                        max_num, sum, count, time));
                precount = count;
            }
        }
    }

    private static class Post extends Thread {
        private final String url;
        private final String content;

        public Post(String url, String content) {
            this.url = url;
            this.content = content;
        }

        public void run() {
            Thread.currentThread().setName("POST-" + url);
            byte[] buff = new byte[10240];
            long time1, time2;

            while (true) {
                try {
                    time1 = System.currentTimeMillis();
                    URL httpUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();
                    final OutputStream os = connection.getOutputStream();
                    final InputStream is = connection.getInputStream();

                    os.write(content.getBytes());
                    os.flush();

                    while (true) {
                        int cnt = is.read(buff);
                        if (cnt == -1) {
                            break;
                        }
//                        System.out.println(new String(buff, 0, cnt));
                    }
                    time2 = System.currentTimeMillis();
                    Statistics.getInstance().update(time2 - time1);
                } catch (Exception e) {
                    System.out.println("error, " + e);
                    break;
                }
            }

        }

        public static String getRandomString() {
            return "" + (char) (Math.random() * 26 + 'A')
                    + (char) (Math.random() * 26 + 'A')
                    + (char) (Math.random() * 26 + 'A')
                    + (char) (Math.random() * 26 + 'A')
                    + (char) (Math.random() * 26 + 'A')
                    + (char) (Math.random() * 26 + 'A');
        }
    }

}
