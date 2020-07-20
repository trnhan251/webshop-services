import { Component, Input } from '@angular/core';
import { Product } from 'src/app/models/product';
import { Store, select } from '@ngrx/store';
import { State } from 'src/app/models/state';
import { putCart, postCost } from 'src/app/pages/cart/cart.actions';
import { dispatch } from 'rxjs/internal/observable/pairs';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent {

  @Input('order') public order: Product;

  private cartId: number
  private ids: number[]
  private products: Product[]

  constructor(private store: Store<State>) {
    store.pipe(select(s => s.cart.id)).subscribe(id => this.cartId = id);
    store.pipe(select(s => s.catalog)).subscribe(p => this.products = p);
    store.pipe(select(s => s.cart.productIds)).subscribe(ids => this.ids = ids);
  }

  public rm() {
    const pr: number[] = [];
    const i = [...this.ids.filter(id => id != this.order.id)];
    this.products.forEach(p => {
      if (i.includes(p.id)) pr.push(p.price);
    });

    this.store.dispatch(postCost({ prices: pr }));
    this.store.dispatch(putCart({ cartId: this.cartId, productIds: i }))
  }
}
