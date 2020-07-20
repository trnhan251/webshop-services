import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects"
import { HttpClient } from "@angular/common/http"
import { environment } from 'src/environments/environment';
import { switchMap, map, retry, catchError, tap } from "rxjs/operators"
import { TrackingOrder, Cost } from 'src/app/models/checkout';
import { emptyCart, onNewCart, putCart, getCart, postCost, reciveCost } from './cart.actions';
import { Cart } from 'src/app/models/cart';

@Injectable()
export class CartEffects {
  constructor(private http: HttpClient, private actions$: Actions) { }

  emptyCart$ = createEffect(() => this.actions$.pipe(
    ofType(emptyCart),
    switchMap(c => {
      return this.http.delete<Cart>(`${environment.cart.url}/${c.id}`, {
        headers: {
          "Authorization": `Basic ${btoa(`${environment.cart.username}:${environment.cart.password}`)}`
        }
      }).pipe(
        retry(3),
        map(cart => onNewCart({ cart })),
      )
    })
  ));

  putCart$ = createEffect(() => this.actions$.pipe(
    ofType(putCart),
    switchMap(c => {
      return this.http.put<Cart>(`${environment.cart.url}/${c.cartId}`, c.productIds, {
        headers: {
          "Authorization": `Basic ${btoa(`${environment.cart.username}:${environment.cart.password}`)}`
        }
      }).pipe(
        retry(3),
        map(cart => {
          return onNewCart({ cart });
        })
      )
    })
  ));

  getCart$ = createEffect(() => this.actions$.pipe(
    ofType(getCart),
    switchMap(c => {
      return this.http.get<Cart>(`${environment.cart.url}/${c.id ?? ""}`, {
        headers: {
          "Authorization": `Basic ${btoa(`${environment.cart.username}:${environment.cart.password}`)}`
        }
      }).pipe(
        retry(3),
        map(cart => onNewCart({ cart })),
      )
    })
  ));


  postCost = createEffect(() => this.actions$.pipe(
    ofType(postCost),
    switchMap(c => {
      return this.http.post<Cost>(`${environment.shipping.url}/cost`, c.prices, {
        headers: {
          "Authorization": `Basic ${btoa(`${environment.shipping.username}:${environment.shipping.password}`)}`
        }
      }).pipe(
        retry(3),
        map(cost => reciveCost({ cost })),
      )
    })
  ));
}
