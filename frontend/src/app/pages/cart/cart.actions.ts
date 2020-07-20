import { createAction, props } from '@ngrx/store';
import { Cart } from 'src/app/models/cart';
import { Cost } from 'src/app/models/checkout';

export const getCart = createAction("[CART] Get cart", props<{ id?: number }>());

export const addProduct = createAction("[CART] Add product to cart", props<{ id: number }>());
export const removeProduct = createAction("[CART] Remove product from cart", props<{ id: number }>());

export const emptyCart = createAction("[CART] Empty cart", props<{ id: number }>());
export const putCart = createAction("[CART] Put cart", props<{ cartId: number, productIds: number[] }>());
export const onNewCart = createAction("[CART] Got new cart", props<{ cart: Cart }>());

export const postCost = createAction("[CART] Post Cost", props<{ prices: number[] }>());
export const reciveCost = createAction("[CART] Recive Cost", props<{ cost: Cost }>());
