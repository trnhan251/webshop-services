{{- define "catalog.fullname" -}}
{{- printf "%s-%s" .Release.Name "catalog" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "catalog.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: catalog
{{- end -}}
