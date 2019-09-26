package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.bean.DikuFeatureChange;
import com.boot.data.bean.OutResult;
import com.boot.data.bean.P;
import com.boot.data.bean.Pson;
import com.boot.data.entity.BranchPadInputRecord;
import com.boot.data.entity.Teacher;
import com.sun.jna.Platform;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;
import com.vip.vjtools.vjkit.mapper.JsonMapper;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
@Slf4j
public class MainApp022 {

    @Test
    public void test1() {
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();
        int x = month.maxLength();
        System.out.println(x);


        int i0 = now.getDayOfWeek().getValue();

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
//            sheet.createRow()
        } catch (Exception e) {
            log.error("文件生成失败: {}", e);
        }


    }

    @Test
    public void test2() {
        LocalDate now = LocalDate.now();
        int pLines = countPLine(now);
        //pLines * 4 + 1 行

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {

            HSSFSheet sheet = workbook.createSheet();

            HSSFRow row001 = sheet.createRow(0);
            HSSFCell row001Cell001 = row001.createCell(0);
            row001Cell001.setCellValue("周日");
            HSSFCell row001Cell002 = row001.createCell(1);
            row001Cell002.setCellValue("周一");
            HSSFCell row001Cell003 = row001.createCell(2);
            row001Cell003.setCellValue("周二");
            HSSFCell row001Cell004 = row001.createCell(3);
            row001Cell004.setCellValue("周三");
            HSSFCell row001Cell005 = row001.createCell(4);
            row001Cell005.setCellValue("周四");
            HSSFCell row001Cell006 = row001.createCell(5);
            row001Cell006.setCellValue("周五");
            HSSFCell row001Cell007 = row001.createCell(6);
            row001Cell007.setCellValue("周六");
            now = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
            int days = days4Month(now);

            for (int i = 1; i <= days; i++) {
                HSSFRow row_01 = sheet.getRow((i / 7) * 5 + 1);
                if (row_01 == null) {
                    row_01 = sheet.createRow((i / 7) * 5 + 1);
                }
                HSSFCell row_01Cell_01 = row_01.createCell(day2Index(now));
                row_01Cell_01.setCellValue(now.getDayOfMonth());
                now = now.plusDays(1);
            }

            File file = new File("temp/hehe_" + System.currentTimeMillis() + ".xls");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            workbook.write(file);
            log.info(file.getName());
        } catch (IOException e) {
            log.error("文件生成失败: {}", e);
        }

    }

    private int countPLine(LocalDate date) {
        LocalDate month0 = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        int days = days4Month(date);
        int i = day2Index(month0);
        double d1 = (double) (days - (7 - i)) / 7;
        int plines = (int) Math.ceil(d1) + 1;

        return plines;
    }

    private int day2Index(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        int i = 0;
        switch (dayOfWeek) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
            case 5:
                i = 5;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 0;
                break;
        }
        return i;
    }

    private int days4Month(LocalDate date) {
        LocalDate month0 = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate month_end = month0.plusMonths(1).minusDays(1);
        return (int) (month_end.toEpochDay() - month0.toEpochDay() + 1);
    }

    @Test
    public void test3() throws EncoderException {
        File file = new File("C:\\Users\\98548\\Downloads\\张宇 - 给你们.mp3");
        MultimediaInfo media = new Encoder().getInfo(file);
        String format = media.getFormat();
        long duration = media.getDuration();
        System.out.println(format);
        System.out.println(duration);
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<>();
        System.out.println(list.stream()
                .limit(3)
                .collect(Collectors.joining("、")));
    }

    @Test
    public void test5() {

        System.out.println((double) (1 / 7));

        System.out.println(100 / 60);
    }

    @Test
    public void test6() {
        int i = 1;
        System.out.println(i++);
        System.out.println(i);
    }

    @Test
    public void test7() {
        String s = "{CHI[与|和] || CAT[J] LOGIC[G|D]}+(2){CAT[A] || OF_AMBI[A]}+(3){CHI[的]||CAT[N]}";
        jugment(s);
    }

    @Test
    public void test8() {
        String s = "(12)3";
        char[] c = s.toCharArray();
        Stack<Character> sc = new Stack<>();
        for (int i = 0; i < c.length; i++) {

            if (c[i] == '(' || c[i] == '[' || c[i] == '{') {
                sc.push(c[i]);
            } else if (c[i] == ')' && sc.peek() == '(') {
                sc.pop();
            } else if (c[i] == ']' && sc.peek() == '[') {
                sc.pop();
            } else if (c[i] == '}' && sc.peek() == '{') {
                sc.pop();
            }
        }

        if (sc.empty()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }

    @Test
    public void test9() {
        String[] arr = {"bat", "abt", "tab", "123", "12"};

        List<List<String>> lists = new ArrayList<>();
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            Arrays.sort(chars);
            String tempStr = String.valueOf(chars);
            if (hashMap.containsKey(tempStr)) {
                hashMap.get(tempStr).add(arr[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(arr[i]);
                hashMap.put(tempStr, list);
            }
        }
        hashMap.values().forEach(System.out::println);

    }

    private void jugment(String s) {
        Stack<Character> sc = new Stack<Character>();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(' || c[i] == '[' || c[i] == '{') {
                sc.push(c[i]);
            } else if (c[i] == ')') {
                if (sc.peek() == '(') {
                    sc.pop();
                }
            } else if (c[i] == ']') {
                if (sc.peek() == '[') {
                    sc.pop();
                }
            } else if (c[i] == '}') {
                if (sc.peek() == '{') {
                    sc.pop();
                }
            }
        }
        if (sc.empty()) {
            System.out.println("成对");
        } else {
            System.out.println("不成对");
        }
    }

    @Setter
    @Getter
    class User {
        private String name;
    }

    @Test
    public void test10() {
        Map<Object, Object> map = new HashMap<>();
        User user = new User();
        user.setName("张三");
        map.put(user, user);

        User user1 = new User();
        user1.setName("张三");
        map.put(user1, user1);

        user1.setName("李四");

        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test11() {
        User user = new User();
        User user1;
        user1 = user;
        System.out.println(user);
        user.setName("张三");
        System.out.println(user);
        System.out.println(user1);
        user1.setName("李四");
        System.out.println(user.getName());
        System.out.println(user1.getName());

        System.out.println(user.equals(user1));
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
    }

    class A {
        @Override
        public int hashCode() {
            System.out.println("hashcode哈哈哈");
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("equals呵呵呵");
            return false;
        }
    }

    @Test
    public void test12() {
        A a = new A();
        A b = new A();
        Map<A, Object> map = new HashMap<>();
        map.put(a, a);
        map.put(b, b);

        System.out.println(map.size());
    }

    @Test
    public void test13() {
        int i = 1 << 16;
        System.out.println(i);

    }

    @Test
    public void test14() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
            }
        });
        thread.start();
        System.out.println("B");

    }

    @Test
    public void test15() {

        final int[] i = {0};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                i[0]++;
            }
        });
        for (int i1 = 0; i1 < 10; i1++) {
            thread.run();
        }
        System.out.println(i[0]);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().toString() + "  " + i);
            }
        });
        thread.setDaemon(true);
        thread.start();
        thread.join();
        System.out.println(Thread.currentThread());
        System.exit(1);
        System.gc();
    }

    @Test
    public void test16() {
        Integer i = new Integer(123);
        Integer i1 = new Integer(123);
        int i3 = 123;
        System.out.println(i == i1);
        System.out.println(i == i3);

        String s1 = new String("xyz");
        String s2 = new String("xyz");
        String s3 = "xyz";
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);
    }

    @Test
    public void test17() {
        System.out.println(UUID.randomUUID());
        System.out.println(cn.hutool.core.lang.UUID.fastUUID());
        System.out.println(cn.hutool.core.lang.UUID.randomUUID());

    }

    @Test
    public void getRandom() {
        String val = "";
        Random random = new Random();
        int length = 10;
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        int i = Integer.parseInt(val);
        System.out.println(i);
    }

    @Test
    public void test18() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            System.out.println(random.nextInt(Integer.MAX_VALUE));
        }
    }

    @Test
    public void test19() {
        Long l1 = Long.valueOf(123);
        System.out.println(l1 == 123);
    }

    @Test
    public void test20() {
        Long l = 123L;
        Integer i = 123;
        System.out.println(l == Long.valueOf(i));
    }

    @Test
    public void test21() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        list.stream().filter(s -> s > 5);
        System.out.println(list);
    }

    @Test
    public void test22() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        List<String> list1 = new ArrayList<>();

        for (Integer integer : list) {
            list1.add(String.valueOf(integer));
        }

    }

    @Test
    public void test23() {
        String s = "0123456";
        System.out.println(s.substring(3, 2));
    }

    @Test
    public void test24() {
        Teacher teacher = new Teacher();

        System.out.println(teacher.getName());
        System.out.println(teacher.getAddress());

    }

    @Test
    public void test25() {
        Teacher teacher = new Teacher();

        List<Teacher> teachers = Collections.singletonList(teacher);
        System.out.println(teachers);
    }

    @Test
    public void test26() {
        final List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(6);
        System.out.println(integers);
    }


    @Test
    public void test27() {
        Pson son = new Pson();
        son.setAge("123");
        son.setName("zhansan");

        String x = JSON.toJSONString(son);
        System.out.println(x);
        Pson pson = JSON.parseObject(x, Pson.class);
        System.out.println("1111-----" + pson);
        System.out.println(pson.getAge());
        System.out.println(pson.getName());

        JsonMapper jsonMapper = JsonMapper.INSTANCE;
        String x1 = jsonMapper.toJson(son);
        System.out.println(x1);
        Pson pson1 = jsonMapper.fromJson(x1, Pson.class);
        System.out.println(pson1);
        System.out.println(pson1.getAge());
        System.out.println(pson1.getName());
    }

    @Test
    public void test28() {
        String s = "123";
        String s1 = DigestUtils.md5Hex(s);
        System.out.println(s1);

        String s2 = DigestUtils.md5Hex(s.getBytes());
        System.out.println(s2);
        System.out.println(s1.length());
    }

    @Test
    public void test29() {
        String s = "[{\"id\":2049,\"group\":\"sany\",\"employeeId\":\"10010450\",\"imageFeature\":\"0.023985 -0.045728 -0.024831 0.015863 0.091251 -0.055376 -0.146194 -0.011859 -0.016756 -0.055454 -0.013032 0.067025 -0.094178 0.01947 -0.057749 -0.071988 -0.061119 -0.001998 0.098212 -0.077924 0.0383 -0.037347 -0.031658 0.099923 0.042881 0.031261 0.024273 0.020748 0.095788 -0.040595 -0.037733 0.036001 0.00528 -0.006772 -0.03451 0.045272 -0.050131 -0.038171 -0.00956 0.08026 -0.043723 -0.021041 -0.058625 0.049806 0.038284 -0.022081 0.038415 -0.011098 -0.009206 0.041369 -0.020667 0.001966 0.036608 0.006437 -0.007588 -0.058155 -0.072048 -0.055037 -0.037825 -0.094402 -0.025894 0.041879 -0.013787 0.020026 -0.004752 0.030264 -0.035508 -0.012304 -0.008616 -0.078628 0.037757 0.024052 0.011719 -0.019998 0.127623 0.003544 -0.022201 0.042354 -0.033928 -0.016837 -0.018655 0.047493 -0.072932 -0.022521 0.036232 -0.013108 0.01618 0.032427 -0.041817 0.091122 -0.009352 -0.048775 -0.081823 -0.003193 0.037083 -0.01987 0.020003 -0.021174 0.001504 -0.021887 0.005205 -0.046284 0.039173 -0.076314 -0.002914 0.045609 0.01859 0.016394 0.027176 -0.040341 -0.044485 -0.056969 0.007488 -0.06118 -0.062633 -0.0641 0.006014 -0.022913 0.009999 0.010896 0.009654 -0.012657 -0.077793 0.062094 -0.057652 0.0222 -0.007187 0.006502 0.065595 -0.006931 -0.04027 0.084119 -0.121277 0.018354 -0.042842 0.085237 -0.071864 0.049463 -0.010923 0.050273 0.080345 0.00963 -0.011877 -0.059798 0.048696 0.068114 0.033969 -0.075441 -0.032028 0.069258 -0.003856 -2.04E-4 -0.089319 -0.017793 0.048305 0.00334 0.048565 -0.121828 0.03194 0.039502 -0.025019 -0.012358 -0.030295 -0.08423 -0.020484 -0.019505 -0.018219 0.056292 -0.010008 -0.014418 -0.019134 0.019698 -0.028499 -0.084104 0.030712 -0.030543 -0.042296 0.07931 -0.006188 -0.055789 -0.015951 0.051838 -0.04523 0.075411 0.002137 0.051247 -0.050501 0.021415 0.089683 0.031449 -0.038982 -0.03113 0.017164 -0.001859 0.048688 0.033705 -0.001985 -0.011547 0.025281 -0.01313 0.036971 0.012687 0.095872 -0.064157 -0.006209 -0.024496 -0.071193 0.005191 -0.052623 0.088557 -0.004192 0.052239 0.012737 0.04873 0.044912 -0.005484 -0.008577 -0.014007 0.01435 -0.062094 0.052729 -0.018201 0.007942 -0.002536 -0.069096 -0.040082 0.011361 -0.035156 -0.013465 0.066929 0.039274 -0.015202 -0.012314 0.029992 0.075847 0.00211 -0.009669 -0.013943 0.021911 0.091483 0.05304 0.039747 0.015211 -0.011672 0.044522 0.029113 0.015072 -0.010995 0.091888 -0.098468 -0.024726 -0.040455 -0.031973 -0.055943 0.023583 0.003388 -0.014075 0.001659 0.020345 0.030818 -0.036793 -0.011035 -0.02163 -0.039014 -0.080201 -0.017232 0.027079 -0.06545 0.019904 0.005183 -0.02133 0.020807 -0.064345 0.02185 0.041771 -0.076149 -0.00171 0.02964 0.012351 -0.077292 -0.031284 0.047512 0.005403 0.002324 0.003423 0.047058 0.003967 -0.008897 -0.060256 -0.03951 0.116903 0.020566 -0.033328 0.067735 0.049775 -0.059045 -0.016575 0.06419 -0.018009 -0.01632 0.054164 0.06199 -0.067698 -0.010672 0.045943 -0.021377 0.006553 -0.055269 -0.012071 -0.029759 0.041596 -0.048648 0.047853 0.037204 0.00596 -0.03166 -0.01369 -0.02415 0.014958 0.083186 -0.008049 0.023541 -0.060056 -0.066046 -0.068249 -0.034606 -0.0176 0.039364 0.018384 0.04081 -0.039679 -0.044276 0.052942 -0.006786 0.027043 -0.011223 -0.004434 -0.063549 -0.041528 0.096548 0.00246 0.084029 0.019765 0.066994 0.015644 -0.063245 -0.002892 -0.066028 -0.012983 0.023168 -0.017608 0.002628 -0.055163 -0.003811 0.050061 0.008145 0.017133 -0.007825 -0.014964 -0.002474 -0.021668 0.010541 0.045761 0.011789 0.049429 -0.01 -0.037598 0.015677 -0.061364 -0.028055 0.027263 -0.060158 0.007183 -0.053688 -0.05271 -0.05562 -0.049516 -0.034385 0.078685 0.063443 -0.026498 -0.016717 0.012324 0.002368 -0.061893 0.042413 0.009707 0.030362 -0.00298 0.043606 -0.07448 0.009974 0.066384 0.038161 -0.004141 -0.003506 -0.022432 0.023318 -0.001536 -0.003345 0.056782 -0.040046 0.013943 -0.051473 -0.015618 0.065899 0.019959 0.058746 0.052239 0.040905 -0.027278 0.044823 0.007094 -0.02085 -0.03352 0.034218 0.086296 0.031047 0.010385 -0.026751 0.055397 0.020266 0.014464 -0.06314 -0.020312 -0.00943 -0.036453 0.060124 -0.052247 0.006952 -0.013565 0.011066 0.043559 -0.0867 -0.053743 -0.008908 0.057157 -0.022095 0.004842 -0.091078 -0.021797 -0.06395 0.058771 0.033321 -0.066829 0.00262 -0.02114 0.008655 8.8E-4 -0.010967 0.018355 0.030252 0.015326 0.068341 0.004718 -0.038664 -0.027575 0.040448 0.059224 -0.034618 -0.042694 2.8E-5 0.036057 -0.061636 -0.021108 0.031617 -0.022 -0.012607 0.045117 0.034078 -0.039422 0.025134 -0.071109 0.094298 0.003149 0.045905 0.039819 -0.002687 -0.022842 0.04702 -0.028246 0.02864 0.005758 -0.062063 0.07508 -0.005254 -0.008462 0.032239 -0.036839 0.025332 -0.097291 -0.057502 0.023194 0.013358 0.005679 0.020401 0.04744 -0.026903 -0.066764 0.067755 0.027254 0.015736 0.007159 -0.021605 0.021306 0.022695 0.031019 -0.076365 -0.035609 -3.22E-4 -0.043309 -0.038014\",\"imageUrl\":\"http://10.88.0.44:8001/file/pangu/face/photos/18afeba0ac617be2050eda13e1df8aab-sany-10010450.jpg\",\"deleted\":false,\"updatedAt\":\"2019-08-16 10:00:48.447\"}]";

        List<DikuFeatureChange> changes = JSON.parseArray(s, DikuFeatureChange.class);
        DikuFeatureChange change = changes.get(0);
        System.out.println(change.getImageUrl());
    }

    @Test
    public void test30() {
        String s = "sany-48000032-bd12ce1b17ad4fca2cd5a91e86ab07c3.jpg";
        List<String> list = Arrays.stream(s.split("-")).flatMap(s1 -> Arrays.stream(s1.split("\\."))).map(String::toString).collect(Collectors.toList());
        list = list.stream().filter(s1 -> s1.length() == 32).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test31() {
        BranchPadInputRecord branchPadInputRecord = new BranchPadInputRecord();
        branchPadInputRecord.setId(0L);
        branchPadInputRecord.setUserNo("");
        branchPadInputRecord.setUserName("");
        branchPadInputRecord.setImgUrl("");
        branchPadInputRecord.setJpgId(0L);
        branchPadInputRecord.setType(0);
        branchPadInputRecord.setOperation(0);
        branchPadInputRecord.setResult(false);
        branchPadInputRecord.setIp("");
        branchPadInputRecord.setDate(new Date());
        branchPadInputRecord.setCreatedAt(new Date());
        System.out.println(JSON.toJSONString(branchPadInputRecord));
    }

    @Test
    public void test35() {
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while (true) {
            if (i > 100) {
                break;
            }
            list.add(i);
            i++;
            System.out.println(list);
            list.clear();
        }
    }

    @Test
    public void test36() {
        String s = "123";
        log.error("失败:{},{}", new RuntimeException(), s);
    }

    @Test
    public void test37() throws IOException {
        URL url = new URL("http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/0/18b5d10c579208c97d3dfac0fe14a956.jpg");
        System.out.println(url.openStream().available() / 1024);
    }

    @Test
    public void test38() {
        DikuFeatureChange change = new DikuFeatureChange();
        change.setId(0L);
        change.setGroup("");
        change.setName("");
        change.setEmployeeId("");
        change.setImageFeature("");
        change.setImageUrl("");
        change.setSource("");
        change.setDeleted(false);
        change.setUpdatedAt(new Date());

        System.out.println(JSON.toJSONString(Arrays.asList(change)));
    }

    @Test
    public void test39() {
        System.out.println(imgUrl2Md5Str("http://10.88.0.44:8001/file/pangu/face/photos/2ed634c4aa40f8b90d845d88d3d1eb98-sany-10011119.jpg"));
        System.out.println(imgUrl2Md5Str("2ed634c4aa40f8b90d845d88d3d1eb98-sany-10011119.jpg"));

    }

    private String imgUrl2Md5Str(String imgUrl) {
        imgUrl = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        String str = null;
        for (String s : imgUrl.split("\\.")) {
            for (String s1 : s.split("-")) {
                if (s1.length() == 32) {
                    return s1;
                }
            }
        }
        return str;
    }

    @Test
    public void test40() {
        String s = "2ed634c4aa40f8b90d845d88d3d1eb98-sany-10011119.jpg";
        System.out.println(s.lastIndexOf("/"));
    }


    @Test
    public void test41() throws InterruptedException {
        final int[] i = {1};
        Thread thread = new Thread(() -> {
            Thread innerThread = Thread.currentThread();
            while (!innerThread.isInterrupted()) {
                i[0]++;
                System.out.println(innerThread.getName() + i[0]);
                if (i[0] == 50) {
                    try {
                        throw new Exception("50已到,线程该完蛋喽!");
                    } catch (Exception e) {
                        log.error("捕获异常,线程状态:{}", innerThread.getState());
                    }
                }
            }
        });
        thread.start();
        thread.join();
//
//        String s = "测试Str";
//        try {
//            throw new Exception("没事闹着玩");
//        } catch (Exception e) {
//            log.error("解析Str-{}异常", s, e);
//        }
    }

    @Test
    public void test42() {
        try {
            System.out.println("1");
            throw new Exception("122121");
        } catch (Exception e) {
            System.out.println("2");
            return;
        } finally {
            System.out.println("3");
        }
    }

    @Test
    public void test43() throws InterruptedException {
        Thread thread = new Thread(() -> {
            ArrayList<Object> list = new ArrayList<>();
            while (true) {
                Thread thread1 = Thread.currentThread();
                System.out.println(thread1.getName() + "---" + thread1.getState());
                P s = null;
                try {
                    s = JSON.parseObject("{1:2}", P.class);
                    list.add(s);
                } catch (Exception e) {
                    log.error("json解析异常,{}", e);
                } finally {
                    list.clear();
                }
                System.out.println(thread1.getName() + "---" + thread1.getState());
                System.out.println(s);
            }
        });
        thread.start();
        thread.join();
        System.out.println("呵呵呵呵");
    }

    @Test
    public void test44() {
        Boolean b = null;
        System.out.println(b);
        Integer integer = new Integer(2);
        Integer integer1 = new Integer(2);
        System.out.println(integer.equals(2));
        System.out.println(2 == integer1);
        System.out.println(2 == integer);
//        System.out.println(2 == null);
    }

    @Test
    public void test45() {
        @Data
        class A {
            private Integer id;
            private String name;
        }

        A a1 = new A();
        a1.setId(1);
        a1.setName("李四");
        A a2 = new A();
        a2.setId(1);
        a2.setName("李四");

        Map<A, String> map = new HashMap<>();
        map.put(a1, a1.getName());
        map.put(a2, a2.getName());
        System.out.println(map);
        System.out.println(a1.equals(a2));
    }


    @Test
    public void test46() {

        Map map = new HashMap();
        for (int i = 1; i <= 31; i++) {
            HashMap<Object, Object> innerMap = new LinkedHashMap<>();
            HashMap<Object, Object> innerMap1 = new LinkedHashMap<>();
            innerMap1.put("name", "苏晓琴");
            innerMap1.put("color", "红色");
            innerMap.put("值班", innerMap1);

            HashMap<Object, Object> innerMap2 = new LinkedHashMap<>();
            innerMap2.put("name", "苏晓琴");
            innerMap2.put("color", "蓝色");
            innerMap.put("休班", innerMap2);

            HashMap<Object, Object> innerMap3 = new LinkedHashMap<>();
            innerMap3.put("name", "苏晓琴");
            innerMap3.put("color", "粉色");
            innerMap.put("备注", innerMap3);

            map.put(String.valueOf(i), innerMap);
        }
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test47() {
        try {
            String s;
            throw new NullPointerException();
        } catch (NullPointerException e) {
            log.error("11111111111111");
        } catch (Exception e) {
            log.error("222222222222");
        }
    }

    @Test
    public void test48() {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2, ThreadPoolUtil.buildThreadFactory("wxq"));

        poolExecutor.schedule(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis());
        }, 2, TimeUnit.SECONDS);
    }

    @Test
    public void test49() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time:");
            }
        }, 2000, 400);
    }

    @Test
    public void test50() {
        List<String> list = Arrays.asList("http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/8/b69223cefd71c7a00626b35d79c2de16.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/10/b1c79a4cf4d5a95502a52e99d77ed4c6.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/3/be0e720a0d4afccb67ac722225f38e84.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/12/0e2446c5eaf4eea601b3ba72e050a32a.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/14/4fa9d5f961b99903dfb3b30f59cdf69c.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/18/3f7e72f363d0f51eff45b2d753ee848e.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/3/96e337b19a3d03480120f04b62187eef.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/2/523c2c1569249eace6db31bc97c65fd8.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/1/c71b2c5af44a7ba640e63ffde6544fa5.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/11/2d6de2abc74427315d1140bc14650e3e.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/14/7b39a5b141118d05165805a509eff13b.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/18/d8284127aca864093bccb690bc68bd69.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/8/8bcd66ace6b894d477ac4883f638294b.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/10/be85a86764d4aaed660dfe36d962d7b7.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/3/d24b8b1b3df3d7f29a2708e01f8db0f6.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/1/70e57f73530fa26b3b85f96501685e9a.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/15/dc586fb3c189c97d41993e33bbb30f62.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/0/d8c1878504f58452040eb03853abe523.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/2/987baa0b2fdfc83dd6b99340f554e233.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/18/d10e37e8a997059b0512d6cd6b795432.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/2/84a38844167655d4a1eb7e6766e14cbe.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/18/f5c1bb16e06c097ec8aa517d79fcf5c1.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/7/28a09603bd505337b38bcc991c93be00.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/13/2a7277225538ca82ddbbc4b6d10a9c8a.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/10/53b3e8a2f89f5fa4132f56fe69184261.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/10/79d8b8a45a1ad60b97579c865c65846f.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/13/82f8b50dbc78f88eb01726a822514ae0.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/17/f4da1b0fac61c542f6b7b2c001528924.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/1/36af8d7e726de1d71b07de9d5177585a.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/7/74b5f517e063687fe2dff06406c4a228.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/11/486862dd414906c8eddbb386497d060a.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/11/16270779d552e57a026f7583edc684cc.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/4/c98bf251f717b88605563d2fed6f58c3.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/16/83e66bc7691c08aa7b57e9008bbe6baf.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/8/0c1857ebd236e5ad48be144f105f2688.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/6/a9d06d5f7c4dfb1e6c8b2121a91abd9f.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/1/38e13487cdc88d3ebbdd1a58ea2e6a4e.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/11/f169cc2e87a9e26d2084b39b19b6e8fd.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/16/9d3c85d2fe58032c05081df2b1418cd8.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/3/8736a2d987529d95feb8750a7dca3e65.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/17/cb68f09bb82c73da78bb4b65ead857a2.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/15/e3d3d37337b134e4f3bb738acd266e66.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/15/74caae788f11f4293e432a1c8f2d6448.jpg", "http://shr.sany.com.cn:8080/shr/personPhoto/shr_sany/14/ef86a03ba9fc336d7ff82a6cce748017.jpg");
        list.forEach(System.out::println);
    }

    @Test
    public void tst51() {
        File file = new File("C:\\Users\\98548\\Desktop\\测试文件夹", "a.md");
        System.out.println(file.length());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
    }

    @Test
    public void test52() {
        for (; ; ) {
            System.out.println("呵呵");
        }
    }

    @Test
    public void test53() {
        AtomicInteger cursor = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            System.out.println(cursor.getAndIncrement());
        }
    }

    @Test
    public void test54() {
        String format = "%2d";
        System.out.printf(format, 123);
        Platform.isLinux();
    }

    @Test
    public void test55() {

        String s = "{\"id\":\"car-analysis\",\"timestamp\":\"2019-09-03-16.17.53.00\",\"imagesize\":{\"w\":2560,\"h\":1440},\"detectedbox\":[]}";


        OutResult outResult = JSON.parseObject(s, OutResult.class);
        System.out.println(outResult);


    }

    @Test
    public void test56() {
        log.info("Device[%s] Port[%d] Disconnect!\n", 123, 123);
        log.info("Device{} Port{} Disconnect!\n", 123, 123);
    }


}
