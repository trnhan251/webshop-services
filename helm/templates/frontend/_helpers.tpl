{{- define "frontend.fullname" -}}
{{- printf "%s-%s" .Release.Name "frontend" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "frontend.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: frontend
{{- end -}}
