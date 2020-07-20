import { Component, OnInit } from '@angular/core';
import { State } from 'src/app/models/state';
import { Store } from '@ngrx/store';
import { count, tap } from 'rxjs/operators';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public productCount: number = 0;

  constructor(private store: Store<State>) {
    store.select(s => s.cart.productIds.length).subscribe(c => this.productCount = c);
  }

  ngOnInit(): void {
  }

}
