package com.czx.school.common;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    List<T> list;
    long total;
    long current;
    long size;
    long pages;
}
