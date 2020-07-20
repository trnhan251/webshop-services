import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects"
import { HttpClient } from "@angular/common/http"
import { environment } from 'src/environments/environment';

import { switchMap, map, retry, catchError, tap, mergeMap } from "rxjs/operators"
import { postCheckout, onReciveTrackingOrder } from './checkout.actions';
import { TrackingOrder } from 'src/app/models/checkout';
import { Router } from '@angular/router';
import { from, of } from 'rxjs';
import { emptyCart, putCart } from '../cart/cart.actions';

@Injectable()
export class CheckoutgEffects {
  constructor(private http: HttpClient, private actions$: Actions, private router: Router) { }

  getCatalog$ = createEffect(() => this.actions$.pipe(
    ofType(postCheckout),
    switchMap(t => {
      console.log(t.checkout)
      return this.http.post<TrackingOrder>(`${environment.checkout.url}/`, t.checkout, {
        headers: {
          "Authorization": `Basic ${btoa(`${environment.checkout.username}:${environment.checkout.password}`)}`
        }
      }).pipe(
        retry(3),
        tap(() => this.router.navigateByUrl("/checkout")),
        mergeMap(trackingOrder => of(onReciveTrackingOrder({ trackingOrder }), putCart({ cartId: t.checkout.cartId, productIds: [] }))),
      )
    })
  ));

}
