{{- define "catalog.fullname" -}}
{{- printf "%s-%s" .Release.Name "catalog" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "catalog.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: catalog
{{- end -}}

{{- define "catalog.r4j.retry.catalog" -}}
{{- $yml := .Values.catalog.resilience4j.retry.catalog -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "catalog.r4j.timelimiter.catalog" -}}
{{- $yml := .Values.catalog.resilience4j.timelimiter.catalog -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "catalog.r4j.ratelimiter.catalog" -}}
{{- $yml := .Values.catalog.resilience4j.ratelimiter.catalog -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "catalog.r4j.circuitbreaker.catalog" -}}
{{- $yml := .Values.catalog.resilience4j.circuitbreaker.catalog -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "catalog.r4j.thread_pool_bulkhead.catalog" -}}
{{- $yml := .Values.catalog.resilience4j.thread_pool_bulkhead.catalog -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

