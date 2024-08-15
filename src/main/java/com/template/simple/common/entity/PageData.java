package com.template.simple.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {

    private List<T> data;
    private long page;
    private long size;
    private long total;

    public static <T> PageData<T> of(List<T> data, long page, long size, long total) {
        return new PageData<>(data, page, size, total);
    }

    public static <T> PageData<T> of(Page<T> page) {
        return new PageData<>(page.getRecords(), page.getCurrent(), page.getSize(), page.getTotal());
    }
}
