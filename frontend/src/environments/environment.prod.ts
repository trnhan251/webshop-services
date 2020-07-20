export const environment = {
  production: true,

  cart: {
    url: "http://control-plane.minikube.internal/api/cart",
    username: "webshop_cart_auth_user",
    password: "webshop_cart_auth_password"
  },
  catalog: {
    url: "http://control-plane.minikube.internal/api/catalog",
    username: "webshop_catalog_auth_user",
    password: "webshop_catalog_auth_password"
  },
  checkout: {
    url: "http://control-plane.minikube.internal/api/checkout",
    username: "webshop_checkout_auth_user",
    password: "webshop_checkout_auth_password"
  },
  shipping: {
    url: "http://control-plane.minikube.internal/api/shipping",
    username: "webshop_shipping_auth_user",
    password: "webshop_shipping_auth_password"
  },

};
