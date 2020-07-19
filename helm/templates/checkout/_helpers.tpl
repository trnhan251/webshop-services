{{- define "checkout.fullname" -}}
{{- printf "%s-%s" .Release.Name "checkout" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "checkout.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: checkout
{{- end -}}

{{- define "checkout.r4j.retry.checkout" -}}
{{- $yml := .Values.checkout.resilience4j.retry.checkout -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "checkout.r4j.timelimiter.checkout" -}}
{{- $yml := .Values.checkout.resilience4j.timelimiter.checkout -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "checkout.r4j.ratelimiter.checkout" -}}
{{- $yml := .Values.checkout.resilience4j.ratelimiter.checkout -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "checkout.r4j.circuitbreaker.checkout" -}}
{{- $yml := .Values.checkout.resilience4j.circuitbreaker.checkout -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "checkout.r4j.thread_pool_bulkhead.checkout" -}}
{{- $yml := .Values.checkout.resilience4j.thread_pool_bulkhead.checkout -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

