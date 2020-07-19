{{- define "cart.fullname" -}}
{{- printf "%s-%s" .Release.Name "cart" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "cart.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: cart
{{- end -}}

{{- define "cart.r4j.retry.cart" -}}
{{- $yml := .Values.cart.resilience4j.retry.cart -}}
{{- if not (empty $yml) -}}%
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "cart.r4j.timelimiter.cart" -}}
{{- $yml := .Values.cart.resilience4j.timelimiter.cart -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "cart.r4j.ratelimiter.cart" -}}
{{- $yml := .Values.cart.resilience4j.ratelimiter.cart -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "cart.r4j.circuitbreaker.cart" -}}
{{- $yml := .Values.cart.resilience4j.circuitbreaker.cart -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "cart.r4j.thread_pool_bulkhead.cart" -}}
{{- $yml := .Values.cart.resilience4j.thread_pool_bulkhead.cart -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

