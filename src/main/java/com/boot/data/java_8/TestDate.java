package com.boot.data.java_8;

import org.junit.Test;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static java.lang.annotation.ElementType.*;

public class TestDate {
    @Test
    public void test1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> callable = () -> dateFormat.parse("20190713");

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(callable));
        }

        results.stream()
                .map(e -> {
                    try {
                        return e.get();
                    } catch (Exception e1) {
                        return null;
                    }
                })
                .forEach(System.out::println);
    }

    static class DateFormatThreadLocal {
        private static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMdd");
            }
        };

        public static Date convert(String str) throws ParseException {
            return THREAD_LOCAL.get().parse(str);
        }
    }

    //加锁保证线程安全
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(() -> DateFormatThreadLocal.convert("20190713")));
        }

        for (Future future : futures) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }

    //java_8
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(() -> LocalDate.parse("20190713", DateTimeFormatter.ofPattern("yyyyMMdd"))));
        }

        for (Future future : futures) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }

    @Test
    public void test4() {
        Instant now = Instant.now();
        System.out.println(now);
        OffsetDateTime dateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(dateTime);

        System.out.println(now.toEpochMilli());
        System.out.println(dateTime.toEpochSecond());

        LocalDate localDate = LocalDate.of(2019, 06, 11);
        LocalDate now1 = LocalDate.now();

        Period period = Period.between(localDate, now1);
        System.out.println(period);
        System.out.println(period.getDays());
    }

    //时间矫正器 TemporalAdjuster
    @Test
    public void test5() {


        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.withDayOfMonth(2));
        System.out.println(now);
        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE;

        System.out.println(timeFormatter.format(now));
    }

    @Test
    public void test6() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);
    }

    @Test
    public void test7() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);
        System.out.println(now.atZone(ZoneId.of("Asia/Shanghai")));

        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
        System.out.println(now1.atZone(ZoneId.of("Asia/Shanghai")));
    }

    // 重复注解/类型注解
    @Test
    public void test8() {

    }


    @Repeatable(MyAnnotations.class)
    @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {

        String hehe() ;


    }

    @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotations {
        MyAnnotation[] value();

    }

}
