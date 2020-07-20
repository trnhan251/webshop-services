import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects"
import { HttpClient } from "@angular/common/http"
import { getCatalog, recivedCatalog } from './catalog.actions';
import { environment } from 'src/environments/environment';

import { switchMap, map, retry, catchError } from "rxjs/operators"
import { Observable, of } from 'rxjs';
import { Product } from 'src/app/models/product';

@Injectable()
export class CatalogEffects {
  constructor(private http: HttpClient, private actions$: Actions) { }

  getCatalog$ = createEffect(() => this.actions$.pipe(
    ofType(getCatalog),
    switchMap(() => {
      console.log(`Basic ${btoa(`${environment.catalog.username}:${environment.catalog.password}`)}`)
      return this.http.get<Product[]>(`${environment.catalog.url}/`, {
        headers: {
          "Authorization": `Basic ${btoa(`${environment.catalog.username}:${environment.catalog.password}`)}`
        }, withCredentials: true
      }).pipe(
        retry(3),
        // catchError(_ => of([])),
        map(products => recivedCatalog({ products })),
      )
    })
  ));

}
