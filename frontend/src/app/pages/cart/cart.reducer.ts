import { createReducer, on } from '@ngrx/store';
import { Cart } from 'src/app/models/cart';
import { onNewCart, addProduct, reciveCost } from './cart.actions';
import { Cost } from 'src/app/models/checkout';

export const cartReducer = createReducer<Cart>({ id: -1, productIds: [] },
  on(onNewCart, (state, action) => {
    return { id: action.cart.id, productIds: action.cart.productIds }
  })
);



export const costReducer = createReducer<Cost>({ total: 0, shipping: 0 },
  on(reciveCost, (state, action) => action.cost)
);
