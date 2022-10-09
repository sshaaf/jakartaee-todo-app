FROM payara/server-full:5.2021.10
COPY target/jakartaee-todo-app.war $DEPLOY_DIR
