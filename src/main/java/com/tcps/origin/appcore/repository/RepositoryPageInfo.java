package com.tcps.origin.appcore.repository;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class RepositoryPageInfo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T entity;
    private Integer pageNo = 1;
    private Integer pageSize = 12;
    private Integer totalCount = 0;
    private List<?> queryResultList;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageStartIndex() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public Integer getPageEndIndex() {
        int end = this.getPageStartIndex() + this.getPageSize();
        if (end > this.getTotalCount()) {
            end = this.getPageStartIndex() + (this.getTotalCount() % this.getPageSize());
        }
        return end;
    }

    public Integer getTotalPage() {
        if (this.totalCount % this.pageSize == 0) {
            return this.totalCount / this.pageSize;
        }
        return this.totalCount / this.pageSize + 1;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getQueryResultList() {
        return queryResultList;
    }

    public void setQueryResultList(List<?> resultList) {
        this.queryResultList = resultList;
    }
}