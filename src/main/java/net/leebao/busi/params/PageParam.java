package net.leebao.busi.params;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by LONG on 2017/5/29.
 */
public class PageParam {
    private int pageNum = 1;

    private int pageSize = 5;

    private List<SortParam> sorts = Lists.newArrayList();

    private Map filters = Maps.newHashMap();

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map getFilters() {
        return filters;
    }

    public void setFilters(Map filters) {
        this.filters = filters;
    }

    public List<SortParam> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortParam> sorts) {
        this.sorts = sorts;
    }
}
