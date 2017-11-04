package com.bookstore.controllers.alter;

import com.bookstore.dao.Dao;
import com.bookstore.utils.Paginator;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.bookstore.entitys.AbstractEntity;

public abstract class AbstractController<T extends AbstractEntity> implements Serializable {

    private List<T> list;

    private List<T> subList;

    private T currentT;

    private Paginator<T> paginator;

    protected void init() {
        list = getDao().getList();
        paginator = new Paginator(list);
        subList = paginator.firstSheet();
    }

    public abstract Dao<T> getDao();

    public abstract void create();

    public void delete(T t) {
        getDao().delete(t);
        init();
    }

    public void update(T t) {
        getDao().update(t);
    }

    public void edit(T t) {
        t.setEditable(true);
    }

    public List<T> getList() {
        return list = getDao().getList();
    }

    public List<T> getSubList() {
        return subList;
    }

    protected T getCurrentT(String key) {

        Long id = getId(key);

        if (id == null) {
            return currentT;
        }

        if (currentT == null || !currentT.getId().equals(id)) {
            currentT = getDao().read(id);
        }
        return currentT;
    }

    public void nextSheet() {
        subList = paginator.nextSheet();
    }

    public void prevSheet() {
        subList = paginator.prevSheet();
    }

    private Long getId(String key) {

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ex = fc.getExternalContext();

        String id = ex.getRequestParameterMap().get(key);
        if (id == null) {
            return null;
        }

        return Long.parseLong(id);

    }

}
