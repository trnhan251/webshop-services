import { Product } from './product';


export interface Checkout {
  cartId: number,
  email: string,
  address: Address,
  creditCard: CreditCard
}

export interface TrackingOrder {
  trackingNumber: string,
  cost: Cost,
  address: Address,
  email: string,
  creditCard: CreditCard,
  products: Product[]
}

export interface Cost {
  shipping: number,
  total: number
}

export interface Address {
  street: string,
  zipCode: string,
  city: string,
  state: string,
  country: string
}

export interface CreditCard {
  number: string,
  expire: string,
  ccv: string
}

