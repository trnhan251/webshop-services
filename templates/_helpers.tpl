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
- name: WEBSHOP_CART_DB_SCHEMA
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: cartSchema
- name: WEBSHOP_CART_DB_USERNAME
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
- name: WEBSHOP_CATALOG_DB_SCHEMA
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: catalogSchema
- name: WEBSHOP_CATALOG_DB_USERNAME
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
- name: WEBSHOP_SHIPPING_DB_SCHEMA
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: shippingSchema
- name: WEBSHOP_SHIPPING_DB_USERNAME
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
- name: WEBSHOP_DB_HOST
  value: {{ template "postgres.fullname" . }}-headless.{{ .Release.Namespace }}.svc.cluster.local
- name: WEBSHOP_DB_PORT
  value: {{ .Values.postgres.service.port | quote }}
- name: WEBSHOP_DB_DB
  {{- if empty .Values.postgres.db }}
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-db-secrets
      key: postgresUsername
  {{- else }}
  value: {{ .Values.postgres.db }}
  {{- end -}}
{{- end -}}


{{- define "webshop.auth.env.cart" -}}
- name: WEBSHOP_CART_HOST
  value: {{ template "cart.fullname" . }}.{{ .Release.Namespace }}.svc.cluster.local
- name: WEBSHOP_CART_PORT
  value: {{ .Values.cart.service.port | quote }}
- name: WEBSHOP_CART_AUTH_USERNAME
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: cartUsername
- name: WEBSHOP_CART_AUTH_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: cartPassword
{{- end }}

{{- define "webshop.auth.env.catalog" -}}
- name: WEBSHOP_CATALOG_HOST
  value: {{ template "catalog.fullname" . }}.{{ .Release.Namespace }}.svc.cluster.local
- name: WEBSHOP_CATALOG_PORT
  value: {{ .Values.catalog.service.port | quote }}
- name: WEBSHOP_CATALOG_AUTH_USERNAME
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: catalogUsername
- name: WEBSHOP_CATALOG_AUTH_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: catalogPassword
{{- end }}

{{- define "webshop.auth.env.shipping" -}}
- name: WEBSHOP_SHIPPING_HOST
  value: {{ template "shipping.fullname" . }}.{{ .Release.Namespace }}.svc.cluster.local
- name: WEBSHOP_SHIPPING_PORT
  value: {{ .Values.shipping.service.port | quote }}
- name: WEBSHOP_SHIPPING_AUTH_USERNAME
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: shippingUsername
- name: WEBSHOP_SHIPPING_AUTH_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: shippingPassword
{{- end }}


{{- define "webshop.auth.env.checkout" -}}
- name: WEBSHOP_CHECKOUT_HOST
  value: {{ template "checkout.fullname" . }}.{{ .Release.Namespace }}.svc.cluster.local
- name: WEBSHOP_CHECKOUT_PORT
  value: {{ .Values.checkout.service.port | quote }}
- name: WEBSHOP_CHECKOUT_AUTH_USERNAME
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: checkoutUsername
- name: WEBSHOP_CHECKOUT_AUTH_PASSWORD
  valueFrom:
    secretKeyRef:
      name: {{ template "webshop.fullname" . }}-auth-secrets
      key: checkoutPassword
{{- end }}

{{- define "webshop.env.spring.profiles.active" -}}
- name: WEBSHOP_SPRING_PROFILES
  {{- $p := (default "production" .Values.spring.profiles.active) }}
  {{- if not (has $p (list "production" "dev")) }}
    {{- $p = "production" }}
  {{- end }}
  value: {{ $p }}
{{- end }}

{{- define "webshop.env.spring.show-sql" -}}
- name: WEBSHOP_SPRING_SHOW_SQL
  value: {{ (eq .Values.spring.profiles.active "dev") | quote }}
{{- end }}
