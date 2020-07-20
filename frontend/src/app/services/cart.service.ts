import { Injectable } from '@angular/core';
import { Cart } from '../models/cart';
import { State } from '../models/state';
import { Store } from '@ngrx/store';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cart: Cart

  constructor(private store: Store<State>) {
    store.select(s => s.cart).subscribe(c => {

    })
  }
}
