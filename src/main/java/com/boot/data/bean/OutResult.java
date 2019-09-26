package com.boot.data.bean;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class OutResult {
    private String id;
    private String timestamp;
    private Imagesize imagesize;
    private List<Detectedbox> detectedbox;

    @Data
    @AllArgsConstructor
    public static class Imagesize {
        private int w;
        private int h;
    }

    @Data
    @EqualsAndHashCode(of = {"x1,y1,x2,y2"})
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Detectedbox {
        @NonNull
        private String id;
        @NonNull
        private double score;
        @NonNull
        private int x1;
        @NonNull
        private int y1;
        @NonNull
        private int x2;
        @NonNull
        private int y2;

        private int times;
    }
}