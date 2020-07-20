import { createAction, props } from "@ngrx/store";
import { Product } from "../../models/product";

export const getCatalog = createAction("[CATALOG] Get all Products");
export const recivedCatalog = createAction("[CATALOG] Got all Products", props<{ products: Product[] }>());

