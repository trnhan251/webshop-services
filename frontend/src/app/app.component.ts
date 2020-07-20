import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { State } from './models/state';
import { getCart } from './pages/cart/cart.actions';
import { getCatalog } from './pages/catalog/catalog.actions';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  constructor(private cart: CartService, private store: Store<State>) {

  }
  ngOnInit(): void {
    this.store.dispatch(getCart({}));
    this.store.dispatch(getCatalog());
  }
}
