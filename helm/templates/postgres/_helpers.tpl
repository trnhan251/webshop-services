{{- define "postgres.fullname" -}}
{{- printf "%s-%s" .Release.Name "postgres" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "postgres.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: postgres
{{- end -}}
