package cn.com.dyj.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageInfo<T> {

  private Long count;
  private List<T> rows;

  public PageInfo(final Page<T> page) {
    this.count = page.getTotalElements();
    this.rows = page.getContent();
  }

  public PageInfo(final List<T> list, final Long count) {
    this.count = count;
    this.rows = list;
  }
}
