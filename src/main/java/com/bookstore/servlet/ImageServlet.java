package com.bookstore.servlet;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Book;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ImageServlet", urlPatterns = {"/image/*"})
public class ImageServlet extends HttpServlet {

    @EJB(beanName = "bookDao")
    private Dao bookDao;

    public Dao getBookDao() {
        return bookDao;
    }

    public void setBookDao(Dao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.valueOf(request.getPathInfo().substring(1));
        Book b = (Book) bookDao.read(id);
        byte[] img = b.getCover();
        response.setContentType(getServletContext().getMimeType("image/jpeg"));
        response.setContentLength(img.length);
        response.getOutputStream().write(img);

    }

}
