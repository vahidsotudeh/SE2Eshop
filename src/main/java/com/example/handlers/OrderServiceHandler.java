package com.example.handlers;

import com.example.dao.*;
import com.example.dto.OrderDTO;
import com.example.dto.SingleOrderDTO;
import com.example.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Amir Shams on 1/17/2017.
 */
public class OrderServiceHandler {

    private OrderDTO dto;

    public OrderServiceHandler(OrderDTO dto) {
        this.dto = dto;
    }

    public Response handle()
    {
        ArrayList<SingleOrderDTO> orders = dto.getOrders();
        String discountCode = dto.getDiscountCode();
        String token = dto.getToken();

        ArrayList<String> bookIds = new ArrayList<>();
        for(SingleOrderDTO dto : orders)
            bookIds.add(dto.getId());

        User user = UserDAO.getInstance().getByAccessToken(token);

        if(user == null)
            return Response.status(Response.Status.FORBIDDEN).build();

        List<Book> books = BookDAO.getInstance().getBooks(bookIds);


        int totalPrice = 0;

        ArrayList<BookOrderAssignment> bookOrderAssignments = new ArrayList<>();
        for(SingleOrderDTO order : orders)
        {
            Book temp = getBook(books,order.getId());

            if(temp == null)
                return Response.status(Response.Status.BAD_REQUEST).build();

            if(temp.getCount() >= order.getCount()) {
                temp.setCount(temp.getCount() - order.getCount());
                temp.setSaleCount(temp.getSaleCount() + order.getCount());
                totalPrice += temp.getPrice() * order.getCount();

                BookOrderAssignment assignment = new BookOrderAssignment();
                assignment.setBook(temp);
                assignment.setOrderCount(order.getCount());
                bookOrderAssignments.add(assignment);
            }
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }

        Order customerOrder = new Order();

        int discountAmount = DiscountCodeDAO.getInstance().getCode(discountCode);
        customerOrder.setOrderDate(new Date(new java.util.Date().getTime()));
        customerOrder.setUser(user);
        customerOrder.setStatus("new");
        customerOrder.setPrice(totalPrice - discountAmount);

        for(BookOrderAssignment assignment : bookOrderAssignments)
            assignment.setOrder(customerOrder);

        Session session = HibernateUtils.getSession();

        Transaction tx = session.beginTransaction();

        for(Book book : books)
            session.update(book);

        session.save(customerOrder);

        for(BookOrderAssignment assignment : bookOrderAssignments)
            session.save(assignment);

        tx.commit();

        return Response.ok(totalPrice - discountAmount).build();

    }

    private Book getBook(List<Book> books,String id) {

        for(Book book : books)
            if(Objects.equals(book.getBookId(), id))
                return book;
        return null;
    }
}
