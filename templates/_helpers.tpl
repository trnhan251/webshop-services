{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "webshop.name" -}}
{{- .Chart.Name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "webshop.fullname" -}}
{{- printf "%s-%s" .Release.Name .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "webshop.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "webshop.labels" -}}
helm.sh/chart: {{ include "webshop.chart" . }}
{{ include "webshop.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "webshop.selectorLabels" -}}
app.kubernetes.io/name: {{ include "webshop.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}


{{- define "webshop.db.env.secrets.all" -}}
{{- include "webshop.db.env.secrets.cart" . }}
{{ include "webshop.db.env.secrets.catalog" . }}
{{ include "webshop.db.env.secrets.shipping" . }}
{{- end -}}

{{- define "webshop.db.env.secrets.cart" -}}
- name: WEBSHOP_CART_SCHEMA
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: cartSchema
- name: WEBSHOP_CART_DB_USER
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: cartUsername
- name: WEBSHOP_CART_DB_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: cartPassword
{{- end -}}

{{- define "webshop.db.env.secrets.catalog" -}}
- name: WEBSHOP_CATALOG_SCHEMA
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: catalogSchema
- name: WEBSHOP_CATALOG_DB_USER
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: catalogUsername
- name: WEBSHOP_CATALOG_DB_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: catalogPassword
{{- end -}}

{{- define "webshop.db.env.secrets.shipping" -}}
- name: WEBSHOP_SHIPPING_SCHEMA
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: shippingSchema
- name: WEBSHOP_SHIPPING_DB_USER
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: shippingUsername
- name: WEBSHOP_SHIPPING_DB_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: shippingPassword
{{- end -}}

{{- define "webshop.db.env.connection" -}}
- name: DB_HOST
  value: {{ template "postgres.fullname" . }}-headless.{{ .Release.Namespace }}.svc.cluster.local
- name: DB_PORT
  value: {{ .Values.postgres.service.port | quote }}
- name: DB_DB
  {{- if empty .Values.postgres.db }}
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: postgresUsername
  {{- else }}
  value: {{ .Values.postgres.db }}
  {{- end -}}
{{- end -}}
