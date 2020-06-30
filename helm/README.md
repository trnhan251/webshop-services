# Helm
These charts are meant to be deployed on minikube with the ingress-dns addon enabled.

## Volumes
A default volume with the path `/data` will be created. Make sure to delete this directory if you want to hard-reset the deployment.

## Install
```sh
helm install <name> .
```

## Uninstall
```sh
helm uninstall <name>
```
