import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { State } from 'src/app/models/state';
import { Store, select } from '@ngrx/store';
import { map, filter, tap } from 'rxjs/operators';
import { Product } from 'src/app/models/product';
import { interval } from 'rxjs';
import { Cart } from 'src/app/models/cart';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.scss']
})
export class CatalogComponent implements OnInit {

  public catalog: Product[] = [];
  public pending: boolean = true;


  constructor(private store: Store<State>, c: ChangeDetectorRef) {
    store.pipe(
      select(state => { return { cart: state.cart, catalog: state.catalog } }),
      filter(s => !!s.cart),
    ).subscribe(s => {
      const ids = s.cart.productIds;
      this.catalog = [];
      s.catalog.forEach(p => {
        if (!ids?.includes(p.id)) this.catalog.push(p)
      })
    })
  }

  ngOnInit(): void {

  }

}
