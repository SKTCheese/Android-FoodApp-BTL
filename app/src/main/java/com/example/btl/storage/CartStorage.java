package com.example.btl.storage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.btl.models.CartModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class CartStorage {
    private static final String PREFS_NAME = "cart_prefs";
    private static final String KEY_CART_JSON = "cart_items_json";

    private static final Gson gson = new Gson();
    private static final Type cartListType = new TypeToken<List<CartModel>>() {}.getType();

    private CartStorage() {}

    @NonNull
    public static List<CartModel> getCart(@NonNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CART_JSON, null);
        if (json == null || json.trim().isEmpty()) return new ArrayList<>();

        try {
            List<CartModel> list = gson.fromJson(json, cartListType);
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            // Nếu dữ liệu hỏng/khác format thì reset để tránh crash
            clearCart(context);
            return new ArrayList<>();
        }
    }

    public static void addToCart(@NonNull Context context, @NonNull CartModel item) {
        List<CartModel> list = getCart(context);
        list.add(item);
        saveCart(context, list);
    }

    public static void clearCart(@NonNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_CART_JSON).apply();
    }

    private static void saveCart(@NonNull Context context, @NonNull List<CartModel> list) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_CART_JSON, gson.toJson(list, cartListType)).apply();
    }
}


