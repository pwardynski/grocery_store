FROM mongo:4.0.1
MAINTAINER Przemyslaw Wardynski w.pwardynski@gmail.com
EXPOSE 27017
COPY sample_products.json /tmp
CMD mongod --fork --logpath /var/log/mongod.log &&  && /bin/bashmongoimport --db grocery --collection products --file /tmp/sample_products.json