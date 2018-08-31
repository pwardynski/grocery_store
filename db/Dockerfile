FROM mongo:latest
MAINTAINER Przemyslaw Wardynski w.pwardynski@gmail.com
COPY sample_products.json /tmp
CMD mongod --fork --logpath /var/log/mongod.log && mongoimport --db grocery --collection products --file /tmp/sample_products.json && /bin/bash