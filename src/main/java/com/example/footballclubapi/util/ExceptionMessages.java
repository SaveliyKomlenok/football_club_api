package com.example.footballclubapi.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessages {
    public static final String CLOTHING_NOT_EXISTS = "Clothing with id %d is not exists";
    public static final String CLOTHING_WAREHOUSE_NOT_EXISTS = "Clothing warehouse with id %d is not exists";
    public static final String CLOTHING_WITH_SELECTED_SIZE_NOT_EXISTS = "Clothing id %d with size with id %d is not exists";
    public static final String CLOTHING_TYPE_NOT_EXISTS = "Clothing type with id %d is not exists";
    public static final String CLOTH_SIZE_NOT_EXISTS = "Clothing size with id %d is not exists";
    public static final String MANUFACTURER_NOT_EXISTS = "Manufacturer with id %d is not exists";
    public static final String SHOES_NOT_EXISTS = "Shoes with id %d is not exists";
    public static final String SHOES_TYPE_NOT_EXISTS = "Shoes type with id %d is not exists";
    public static final String SHOE_SIZE_NOT_EXISTS = "Shoes size with id %d is not exists";
    public static final String SHOES_WAREHOUSE_NOT_EXISTS = "Shoes warehouse review with id %d is not exists";
    public static final String SHOES_WITH_SELECTED_SIZE_NOT_EXISTS = "Shoes id %d with size with id %d is not exists";
    public static final String USER_IS_ALREADY_EXISTS = "User with this username %s is already exists";
    public static final String USER_NOT_EXISTS = "User with id %d is not exists";
    public static final String USER_WITH_USERNAME_NOT_EXISTS = "User with username %s is not exists";
    public static final String CART_CLOTHING_NOT_EXISTS = "Cart clothing with id %d is not exists";
    public static final String CART_SHOES_NOT_EXISTS = "Cart shoes with id %d is not exists";
    public static final String AMOUNT_CLOTHING_EXCEEDED = "Amount of clothing to purchase has been exceeded";
    public static final String AMOUNT_SHOES_EXCEEDED = "Amount of shoes to purchase has been exceeded";
    public static final String ORDER_NOT_EXISTS = "Order with id %d is not exists";
}
