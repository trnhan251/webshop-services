export const environment = {
  production: true,

  cart: {
    url: "http://${WEBSHOP_CART_HOST}:${WEBSHOP_CART_PORT}",
    username: "${WEBSHOP_CART_AUTH_USERNAME}",
    password: "${WEBSHOP_CART_AUTH_PASSWORD}"
  },
  catalog: {
    url: "http://${WEBSHOP_CATALOG_HOST}:${WEBSHOP_CATALOG_PORT}",
    username: "${WEBSHOP_CATALOG_AUTH_USERNAME}",
    password: "${WEBSHOP_CATALOG_AUTH_PASSWORD}"
  },
  checkout: {
    url: "http://${WEBSHOP_CHECKOUT_HOST}:${WEBSHOP_CHECKOUT_PORT}",
    username: "${WEBSHOP_CHECKOUT_AUTH_USERNAME}",
    password: "${WEBSHOP_CHECKOUT_AUTH_PASSWORD}"
  },
  shipping: {
    url: "http://${WEBSHOP_SHIPPING_HOST}:${WEBSHOP_SHIPPING_PORT}",
    username: "${WEBSHOP_SHIPPING_AUTH_USERNAME}",
    password: "${WEBSHOP_SHIPPING_AUTH_PASSWORD}"
  },

};
