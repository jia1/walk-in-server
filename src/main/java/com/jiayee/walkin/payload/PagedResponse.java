package com.jiayee.walkin.payload;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {

  private static final String DEFAULT_PAGE_NUMBER = "0";

  private static final String DEFAULT_PAGE_SIZE = "30";

  private static final int MAX_PAGE_SIZE = 50;

  private List<T> content;

  private int pageIndex;

  private int pageSize;

  private long numElement;

  private int numPage;

  private boolean isLastPage;

}
