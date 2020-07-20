import { createReducer, on } from '@ngrx/store';
import { TrackingOrder } from 'src/app/models/checkout';
import { onReciveTrackingOrder } from './checkout.actions';

export const checkoutReducer = createReducer<TrackingOrder[]>([], on(onReciveTrackingOrder, (state, action) => [action.trackingOrder, ...state]))
