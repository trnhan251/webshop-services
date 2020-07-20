import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import localeDe from '@angular/common/locales/de'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CatalogComponent } from './pages/catalog/catalog.component';
import { CartComponent } from './pages/cart/cart.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { OrderComponent } from './components/order/order.component';
import { ProductComponent } from './components/product/product.component';
import { registerLocaleData, CommonModule } from '@angular/common';
import { StoreModule, State } from '@ngrx/store';
import { HeaderComponent } from './components/header/header.component';

import { EffectsModule } from '@ngrx/effects';

import { StoreDevtoolsModule } from "@ngrx/store-devtools";
import { HttpClientModule } from '@angular/common/http';
import { cartReducer, costReducer } from './pages/cart/cart.reducer';
import { catalogReducer } from './pages/catalog/catalog.reducer';
import { checkoutReducer } from './pages/checkout/checkout.reducer';
import { CartEffects } from './pages/cart/cart.effects';
import { CheckoutgEffects } from './pages/checkout/checkout.effects';
import { CatalogEffects } from './pages/catalog/catalog.effects';
import { ReactiveFormsModule } from '@angular/forms';
registerLocaleData(localeDe, 'de-DE');

@NgModule({
  declarations: [
    AppComponent,
    CatalogComponent,
    CartComponent,
    CheckoutComponent,
    OrderComponent,
    ProductComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    StoreModule.forRoot({
      cart: cartReducer,
      catalog: catalogReducer,
      trackingOrders: checkoutReducer,
      cost: costReducer
    }),
    StoreDevtoolsModule.instrument(),
    EffectsModule.forRoot([
      CartEffects,
      CheckoutgEffects,
      CatalogEffects

    ])
  ],
  providers: [{
    provide: LOCALE_ID,
    useValue: 'de-DE'
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
