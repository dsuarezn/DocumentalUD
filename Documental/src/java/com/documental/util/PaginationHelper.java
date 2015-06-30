/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.util;

import javax.faces.model.DataModel;

/**
 *
 * @author DiegoM
 */
public abstract class PaginationHelper {

    private int pageSize;
    private int page;

    public PaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public abstract int getItemsCount();

    public abstract DataModel createPageDataModel();

    public int getPageFirstItem() {
        return pageSize * page;
    }

    public int getPageLastItem() {
        int i = getPageFirstItem() + pageSize - 1;
        int count = getItemsCount() - 1;
        if (i > count) {
            i = count;
        }
        if (i < 0) {
            i = 0;
        }
        return i;
    }

    public boolean isHasNextPage() {
        return (page + 1) * pageSize + 1 <= getItemsCount();
    }

    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
    }

    public boolean isHasPreviusPage() {
        return page > 0;
    }

    public void previusPage() {
        if (isHasPreviusPage()) {
            page--;
        }
    }

    public int getPageSize() {
        return pageSize;
    }
}
