import { createReducer, on } from '@ngrx/store';

import { recivedCatalog } from './catalog.actions';
import { Product } from 'src/app/models/product';

export const catalogReducer = createReducer<Product[]>([], on(recivedCatalog, (_, action) => action.products));
