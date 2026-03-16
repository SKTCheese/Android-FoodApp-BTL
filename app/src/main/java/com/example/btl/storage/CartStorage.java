package com.example.btl.storage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.btl.models.OrderModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class CartStorage {
    private static final String PREFS_NAME = "cart_prefs";
    private static final String KEY_CART_JSON = "cart_items_json";

    private static final Gson gson = new Gson();
    private static final Type cartListType = new TypeToken<List<OrderModel>>() {}.getType();

    private CartStorage() {}

    @NonNull
    public static List<OrderModel> getCart(@NonNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CART_JSON, null);
        if (json == null || json.trim().isEmpty()) return new ArrayList<>();

        try {
            List<OrderModel> list = gson.fromJson(json, cartListType);
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            // Nếu dữ liệu hỏng/khác format thì reset để tránh crash
            clearCart(context);
            return new ArrayList<>();
        }
    }

    /**
     * Thêm sản phẩm vào giỏ. Nếu đã có cùng tên + giá thì cộng thêm số lượng, không tạo item mới.
     */
    public static void addToCart(@NonNull Context context, @NonNull OrderModel item) {
        List<OrderModel> list = getCart(context);
        int qtyToAdd = item.getQuantity() <= 0 ? 1 : item.getQuantity();

        for (OrderModel existing : list) {
            if (isSameProduct(existing, item)) {
                existing.setQuantity(existing.getQuantity() + qtyToAdd);
                saveCart(context, list);
                return;
            }
        }
        item.setQuantity(qtyToAdd);
        list.add(item);
        saveCart(context, list);
    }

    /** Coi hai item là cùng một sản phẩm nếu trùng tên và giá. */
    private static boolean isSameProduct(@NonNull OrderModel a, @NonNull OrderModel b) {
        if (a.getName() == null && b.getName() != null) return false;
        if (a.getName() != null && !a.getName().equals(b.getName())) return false;
        if (a.getPrice() == null && b.getPrice() != null) return false;
        if (a.getPrice() != null && !a.getPrice().equals(b.getPrice())) return false;
        return true;
    }

    /** Cập nhật số lượng tại vị trí index. Nếu newQuantity <= 0 thì xóa item đó. */
    public static void updateQuantity(@NonNull Context context, int index, int newQuantity) {
        List<OrderModel> list = getCart(context);
        if (index < 0 || index >= list.size()) return;
        if (newQuantity <= 0) {
            list.remove(index);
        } else {
            list.get(index).setQuantity(newQuantity);
        }
        saveCart(context, list);
    }

    /** Xóa item tại vị trí index khỏi giỏ. */
    public static void removeItem(@NonNull Context context, int index) {
        List<OrderModel> list = getCart(context);
        if (index < 0 || index >= list.size()) return;
        list.remove(index);
        saveCart(context, list);
    }

    public static void clearCart(@NonNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_CART_JSON).apply();
    }

    private static void saveCart(@NonNull Context context, @NonNull List<OrderModel> list) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_CART_JSON, gson.toJson(list, cartListType)).apply();
    }
}




