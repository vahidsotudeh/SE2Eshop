package com.example.controllers;

import com.example.dao.DiscountCodeDAO;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


/**
 * Created by Amir Shams on 1/17/2017.
 */
@Path("api")
public class DiscountController {

    @GET
    @Path("discount")
    public Response getCode(@DefaultValue("0")@QueryParam("code") String code)
    {
        String result = String.valueOf(DiscountCodeDAO.getInstance().getCode(code));

        return Response.ok(result).build();
    }
}
