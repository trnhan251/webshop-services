import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { TrackingOrder } from 'src/app/models/checkout';
import { State } from 'src/app/models/state';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  to: TrackingOrder = null
  constructor(store: Store<State>) {
    store.select(s => s.trackingOrders).subscribe(tos => {
      if (tos.length == 0) return;
      this.to = tos[0]
    })
  }

  ngOnInit(): void {
  }

}
