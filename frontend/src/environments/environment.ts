// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,

  cart: {
    url: "http://localhost:9082",
    username: "dev_u",
    password: "dev_p"
  },
  catalog: {
    url: "http://localhost:9081",
    username: "dev_u",
    password: "dev_p"
  },
  checkout: {
    url: "http://localhost:9083",
    username: "dev_u",
    password: "dev_p"
  },
  shipping: {
    url: "http://localhost:9084",
    username: "dev_u",
    password: "dev_p"
  },

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
