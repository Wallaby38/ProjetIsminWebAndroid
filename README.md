Part of Android & Web Development - ISMIN 2021

Course followed by students of Mines St Etienne, ISMIN - M2 Computer Science.

[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# Project

This project provides information on `electric car plugs` in France.


# API

The endpoint of the API is the one in the project : Project-JLA-QJA

Send request
You could use your browser or Postman to send request to the API. According to your choice. The url will be :

List of methods for the API:

- `GET /station` : Retrieve a summary of all data (i.e. only the most important info for display on the list and the map).
- `GET /station/:id` : Retrieve the details of a station (for display in the details screen)
- `PUT /station/:id (body { favoris : true/false })` : To bookmark or not to bookmark a station. 

# Android

The android apps is in the android folder. You can run it within android studio with an emulator or you smartphone.

# Application

The list of available stations is displayed, you can click on a station to see its main characteristics and/or bookmark it. To return to the list, click on the `back button`.

You can also view the map or info by clicking on the map and info buttons in the menu. To refresh the page, click on the refresh button in the menu.
