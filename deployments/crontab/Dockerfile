FROM openjdk:11

ENV APP_HOME /app

WORKDIR $APP_HOME

# Copy files into the container image.
COPY . .
COPY /deployments/crontab/tomorrow.sh /tomorrow.sh

RUN chmod 777 /tomorrow.sh

RUN apt-get update && apt-get -y install cron vim

COPY /deployments/crontab/crontab /etc/cron.d/crontab

RUN chmod 0644 /etc/cron.d/crontab

RUN /usr/bin/crontab /etc/cron.d/crontab

# run crond as main process of container
CMD ["cron", "-f"]