
import { Cart } from './cart';
import { TrackingOrder, Cost } from './checkout';
import { Product } from './product';

export interface State {
  cart: Cart,
  catalog: Product[],
  trackingOrders: TrackingOrder[],
  cost: Cost
}
