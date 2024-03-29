import { useState } from "react";

export function useCart() {
    const [cart, setCart] = useState([]);

    function addToCart(classItem) {
        setCart(state => [...state, classItem]);
    }

    function removeFromCart(classItem) {
        setCart(state => state.filter(s => s !== classItem));
    }

    function clearCart() {
        setCart([]);
    }

    return { cart, addToCart, removeFromCart, clearCart };
}
