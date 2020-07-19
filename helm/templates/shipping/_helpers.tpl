{{- define "shipping.fullname" -}}
{{- printf "%s-%s" .Release.Name "shipping" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "shipping.labels" -}}
{{- include "webshop.labels" . }}
app.kubernetes.io/component: shipping
{{- end -}}

{{- define "shipping.r4j.retry.shipping" -}}
{{- $yml := .Values.shipping.resilience4j.retry.shipping -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.timelimiter.shipping" -}}
{{- $yml := .Values.shipping.resilience4j.timelimiter.shipping -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.ratelimiter.shipping" -}}
{{- $yml := .Values.shipping.resilience4j.ratelimiter.shipping -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.circuitbreaker.shipping" -}}
{{- $yml := .Values.shipping.resilience4j.circuitbreaker.shipping -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.thread_pool_bulkhead.shipping" -}}
{{- $yml := .Values.shipping.resilience4j.thread_pool_bulkhead.shipping -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.retry.cost" -}}
{{- $yml := .Values.shipping.resilience4j.retry.cost -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.timelimiter.cost" -}}
{{- $yml := .Values.shipping.resilience4j.timelimiter.cost -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.ratelimiter.cost" -}}
{{- $yml := .Values.shipping.resilience4j.ratelimiter.cost -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.circuitbreaker.cost" -}}
{{- $yml := .Values.shipping.resilience4j.circuitbreaker.cost -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}

{{- define "shipping.r4j.thread_pool_bulkhead.cost" -}}
{{- $yml := .Values.shipping.resilience4j.thread_pool_bulkhead.cost -}}
{{- if not (empty $yml) -}}
{{- $yml | toYaml -}}
{{- end -}}
{{- end -}}



