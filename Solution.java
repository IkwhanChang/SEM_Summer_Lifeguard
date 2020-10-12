import java.io.*;
import java.util.*;

class Solution {

    public static void findMinLifeguard(String input, String output) throws IOException {
        Scanner sc = new Scanner(new File(input));
        int n;
        List<int[]> list = new ArrayList<>();


        n = sc.nextInt();
        while (sc.hasNext()) {
            int start, end;
            start = sc.nextInt();
            end = sc.nextInt();
            list.add(new int[]{start, end});
        }

        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(int[] left, int[] right) {
                return left[0] < right[0] ? -1 : (left[0] == right[0]) ? 0 : 1;
            }

        });


        //l.assign(n,0),r.assign(n,0);
        int[] left = new int[n];
        int[] right = new int[n];
        int rmax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            right[i] = rmax;
            rmax = Math.max(rmax, list.get(i)[1]);
        }

        int lmin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            left[i] = lmin;
            lmin = Math.min(lmin, list.get(i)[0] - 1);
        }

        int indcover = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int ll = Math.max(list.get(i)[0], right[i]);
            int rr = Math.min(list.get(i)[1] - 1, left[i]);

            if (ll > rr) indcover = 0;
            else indcover = Math.min(indcover, rr - ll + 1);
        }

        int cover = 0;
        int currl = 0, currr = -1;

        for (int i = 0; i < n; i++) {
            if (currr >= list.get(i)[0])
                currr = Math.max(currr, list.get(i)[1] - 1);
            else {
                cover += (currr - currl + 1);
                currl = list.get(i)[0];
                currr = list.get(i)[1] - 1;
            }
        }
        cover += (currr - currl + 1);
        System.out.println(cover - indcover);

        Writer wr = new FileWriter(output);
        wr.write(String.valueOf(cover - indcover));

        wr.flush();
        wr.close();
    }

    public static void main(String args[]) throws IOException {
        for (int i = 1; i <= 10; i++) {
            findMinLifeguard(i + ".in", i + ".out");
        }
    }

}