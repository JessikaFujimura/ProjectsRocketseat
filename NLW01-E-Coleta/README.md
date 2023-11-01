# ![ecolta](./web/src/assets/logo.svg)

Aplicação desenvolvida durante o evento Next Level Week 01 da Rocketseat.

## Funcionalidades 

* Cadastro de ponto de coleta (web)
* Filtragem de pontos de coletas cadastrados (mobile)
* Envio de mensagem por whatsapp ou email para o ponto de coleta cadastrado (mobile)

## Tecnologias utilizadas

* [TypeScript](https://www.typescriptlang.org/index.html)

### Back-end
* [Node.JS](https://nodejs.org/en/)
* [Express](https://expressjs.com/pt-br/)
* [Sqlite](https://www.sqlite.org/index.html)
* [Knex](http://knexjs.org/)
* [Multer](https://github.com/expressjs/multer)
* [Celebrate](https://github.com/arb/celebrate)

### Front-end Web
* [React.JS](https://pt-br.reactjs.org/)
* [Axios](https://github.com/axios/axios)
* [leaflet](https://leafletjs.com/)
* [react-leaflet](https://react-leaflet.js.org/)
* [react-dropzone](https://github.com/react-dropzone/react-dropzone)
* [react-icons](https://github.com/react-icons/react-icons)
* [react-router-dom](https://www.npmjs.com/package/react-router-dom)

### Front-end Mobile
* [ReactNative.js](https://reactnative.dev/)
* [Expo](https://expo.io/)
* [React-native-maps](https://github.com/react-native-community/react-native-maps)
* [React-native-picker-selec](https://github.com/lawnstarter/react-native-picker-select)


## Telas

#### Web
Na interface web é possível fazer o cadastro de um ponto de coleta.

![web](./web/src/assets/video.gif)


#### Mobile
Na interface mobile é possível ver os pontos de coletas próximos e filtar por tipo de resíduo coletado.

![mobo](./mobile/src/assets/video.gif)


# Contribuição

Para contribuir com este projeto de um fork neste projeto

## - /server

Rode o comando no terminal   
`npm install`

Para criar o bando de dados rode os comandos     
`npm run knex:migrate `

Para criar os items no banco de dados rode os comandos     
`npm run knex:seed `

Para executar o server e fazer as requisições rode os comando
`npm run dev `

** Altere o endereço http nos arquivos ItemsController.ts e PointsController.ts para o mesmo do mobile.


## - /web

Rode o comando no terminal   
`npm install`

Para acessar a webpage rode o comando
`npm start `

** Altere o endereço http no arquivo api para o mesmo do mobile.

## - /mobile

Rode o comando no terminal   
`npm install`

Para visualizar a interface da aplicação rode o comando
`npm start `

Instale o aplicativo [Expo](https://expo.io/) em seu celular.

Scaneie o QR que aparecer no terminal.

