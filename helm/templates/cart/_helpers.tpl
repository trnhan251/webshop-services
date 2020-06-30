{{- define "cart.fullname" -}}
{{- printf "%s-%s" .Release.Name "cart" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "cart.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: cart
{{- end -}}
