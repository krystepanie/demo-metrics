# demo-metrics
Demo application to try out the integration between Wildfly, Prometheus and Grafana using Microprofile

### How to run
just run deploy.sh script that will build the application using maven and then run docker-compose up command

### URLs
http://localhost:8080/openapi - openapi documentation

http://localhost:8080/demo-metrics-hello/greetings - GET simple hello endpoint with random delay

http://localhost:9990/metrics - microprofile metrics

http://localhost:9090/graph - prometheus

http://localhost:3000 - grafana

http://localhost:16686 - jaeger tracing (currently seems not to receive and request from the application though)