{{- define "shipping.fullname" -}}
{{- printf "%s-%s" .Release.Name "shipping" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "shipping.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: shipping
{{- end -}}
