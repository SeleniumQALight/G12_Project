package org.apiDemoQa;

public interface EndPointsDemoQa {
    String LOGIN = "Account/v1/Login";
    String DELETE = "BookStore/v1/Books?UserId={0}";
    String GET_ALL_BOOKS = "BookStore/v1/Books";
    String ADD_BOOK_TO_USER = "BookStore/v1/Books";
    String GET_BOOKS_BY_USER = "Account/v1/User/{0}";
}
