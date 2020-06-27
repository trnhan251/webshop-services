{{- define "checkout.fullname" -}}
{{- printf "%s-%s" .Release.Name "checkout" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "checkout.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: checkout
{{- end -}}
