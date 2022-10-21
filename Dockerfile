FROM node:14.19-alpine

WORKDIR /app

COPY ["./package.json", "/app"]

RUN npm install

COPY ["./src", "/app/src"]

CMD [ "npm", "run", "start" ]
