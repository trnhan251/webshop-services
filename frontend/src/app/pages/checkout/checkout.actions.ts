import { createAction, props } from '@ngrx/store';
import { Checkout, TrackingOrder } from 'src/app/models/checkout';

export const postCheckout = createAction("[CHECKOUT] Post Checkout", props<{ checkout: Checkout }>());
export const onReciveTrackingOrder = createAction("[CHECKOUT] Recived tacking order", props<{ trackingOrder: TrackingOrder }>());
