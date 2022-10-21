FROM node:14.19-alpine

WORKDIR /app

COPY ["./package.json", "/app"]

RUN npm install

CMD [ "npm", "run", "start" ]
