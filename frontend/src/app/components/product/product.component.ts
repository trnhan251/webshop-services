import { Component, Input } from '@angular/core';
import { Product } from '../../models/product';
import { State } from 'src/app/models/state';
import { Store, select } from '@ngrx/store';
import { map } from 'rxjs/operators';
import { putCart, postCost } from 'src/app/pages/cart/cart.actions';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {

  @Input('product') public product: Product;

  private cartId: number
  private ids: number[]
  private products: Product[]

  constructor(private store: Store<State>) {
    store.pipe(select(s => s.catalog)).subscribe(p => this.products = p);
    store.pipe(select(s => s.cart.id)).subscribe(id => this.cartId = id);
    store.pipe(select(s => s.cart.productIds)).subscribe(ids => this.ids = ids);
  }

  public add() {
    const pr: number[] = [];
    const i = [this.product.id, ...this.ids];
    this.products.forEach(p => {
      if (i.includes(p.id)) pr.push(p.price);
    });
    this.store.dispatch(postCost({ prices: pr }));
    this.store.dispatch(putCart({ cartId: this.cartId, productIds: i }))
  }
}
