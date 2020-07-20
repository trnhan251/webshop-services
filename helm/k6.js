import http from 'k6/http';
import { sleep } from 'k6';

export default function () {
    const credentials = `webshop_catalog_auth_user:webshop_catalog_auth_password`;

    // Passing username and password as part of the URL will
    // allow us to authenticate using HTTP Basic Auth.
    const url = `http://${credentials}@http://10.0.2.15/api/catalog/`;
    http.get(url, { auth: "basic", })
    sleep(1);
}
