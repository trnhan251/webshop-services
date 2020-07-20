import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { State } from 'src/app/models/state';
import { FormGroup, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { Checkout, Cost } from 'src/app/models/checkout';
import { isNumber } from 'util';
import { Product } from 'src/app/models/product';
import { postCost, putCart } from './cart.actions';
import { catalogReducer } from '../catalog/catalog.reducer';
import { filter } from 'rxjs/operators';
import { postCheckout } from '../checkout/checkout.actions';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  cost: Cost = { shipping: 0, total: 0 };
  orders: Product[] = [];
  cartId: number = -1
  constructor(private store: Store<State>) {
    store.select(s => s)
      .subscribe(s => {
        this.cost = s.cost;
        this.orders = [];
        s.catalog.forEach(p => {
          if (s.cart.productIds.includes(p.id)) this.orders.push(p);
        })
        this.cartId = s.cart.id
      });
    this.checkoutFg.statusChanges.subscribe(console.log);
  }

  onSubmit() {
    const check: Checkout = {
      cartId: this.cartId,
      email: this.checkoutFg.get("email").value,
      address: {
        city: this.checkoutFg.get("address.city").value,
        state: this.checkoutFg.get("address.state").value,
        country: this.checkoutFg.get("address.country").value,
        zipCode: this.checkoutFg.get("address.zipCode").value,
        street: this.checkoutFg.get("address.street").value,
      },
      creditCard: {
        number: this.checkoutFg.get("creditCard.number").value,
        expire: this.checkoutFg.get("creditCard.expire").value,
        ccv: this.checkoutFg.get("creditCard.ccv").value,
      }
    }
    console.log(check)
    this.store.dispatch(postCheckout({ checkout: check }))
  }

  empty() {
    this.store.dispatch(putCart({ cartId: this.cartId, productIds: [] }));
    this.store.dispatch(postCost({ prices: [] }));
  }

  checkoutFg = new FormGroup({
    email: new FormControl("", [Validators.email, Validators.required]),
    address: new FormGroup({
      street: new FormControl("", [Validators.required]),
      zipCode: new FormControl("", [Validators.required, Validators.pattern("\\d{5}")]),
      city: new FormControl("", [Validators.required]),
      state: new FormControl("", [Validators.required]),
      country: new FormControl("", [Validators.required])
    }),
    creditCard: new FormGroup({
      number: new FormControl("", [Validators.required, this.correctNumber(), Validators.pattern("\\d+")]),
      expire: new FormControl("", [Validators.required, Validators.pattern("\\d{2}/\\d{4}")]),
      ccv: new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern("\\d{3}")]),
    })
  })


  public get email() {
    return this.checkoutFg.get("email")
  }


  ngOnInit(): void {

  }

  correctNumber(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {

      let sum = 0;
      const str = control.value as string;
      const chars = str.split("").reverse();
      let multiply = false;
      console.log(chars)
      for (const c of chars) {

        const i = Number(c);
        if (isNaN(i)) {
          return { "nan": { value: control.value } }
        }
        if (multiply) {
          const m = i * 2;
          if (m > 9) {
            sum += (m / 10) + (m % 10)
          } else {
            sum += m;
          }
        } else {
          sum += i;
        }
        sum = Math.round(sum);
        multiply = !multiply;
        console.log(sum)
      }
      if (sum % 10 == 0) {
        console.log("good")
        return null
      }

      return { "bad": { value: control.value } };
    };
  }

}
