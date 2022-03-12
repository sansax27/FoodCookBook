# FoodCookBook
This project is an implementation of the app, which serves the following purposes:-
1. Shows Random Food on Main Screen
2. Let's user search food by name
3. Let's user save their favourite foods for later reference.

## This is a very old work of mine when I was a fresher in Android Development and therefore shows deprecated technologies. Now I use all the latest technologies and trends. 
This app is made using MVVM Architecture with programming language as Kotlin and the technologies used are retrofit(to connect to api), glide(to Download Images), Room(For Database).

The project contains three parts :- 


1. User Interface(UI) - This part deals with UI of the App and contains the MainActivity class and 3 Fragments class. The fragments are hosted on fragment container on MainActivity and Navigation Graph is built to connect all three of them. First fragment(MainFragment.kt) serves the purpose of showing random food on entry screen and also has search bar to search Meals by name. Second Fragment(Search.kt) serves the purpose of showing the search result. First fragment navigates to Second Fragment by click on SEARCHMOREFOODS Button. In case the api sends zero matching dishes, the BLANK Meal is shown to tell it. Each Meal in Second Fragment has ADDTOFAVOURITES Button(Random Food on Main Screen also has it) which adds the dish to favourites(Note:- When clicking first time, click this button twice to see the effect). On adding the dish, the button text changes to REMOVEFROMFAVOURITES, by clicking which the dish is removed from favourites. The Third Fragment(ViewFavourites.kt) serves the purpose of showing the favourite dishes. Third Fragment can be reached through overflow menu in MainActivity Action Bar and Clicking View Favourites. Meals List in Third Fragment has REMOVEFROMFAVOURITES Button to remove the Meal. In case there are no dishes in favourites, the screen is blank.


2. Newtwork :- The class in it-MealAPIService.kt serves the purpose of connecting to api, retrieving json and making it available to app.


3. Data :- This section of app is like cpu of the whole app. As can be easily inferred from the names of the corresponding classes, it contains view models for each fragment, view model factory where needed, DataBase, Data Class of MealDetail to store, Database Access Object for the Database, Custom Adapter for recycler view in Second Fragment, Custom Adapter1 for recycler view in Third Fragment. The names and the architecture used in the app itself makes clear the purpose of each of these classes.
At last, as in every project, these 3 parts are integrated. In data part, Repository layer could have been used, but in this case, for each usecase either api or database is the source of Truth, therefore, that layer has not been made.

See the code once and everything would be clear! In case of any doubt, contact me!
