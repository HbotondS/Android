# MovieDB
The goal is to create a sample app,
with login/registration (FireBase), connect to https://www.themoviedb.org/ and get the top
movies from there, manage searching in the Movie DB, have a profile screen, sync current playing
movies in the cinemas to a local DB from Movie DB and have a favourite section where you can
view the movies what you liked.
1. **Login/Register:** On this screen you have to have a login/register section for the user, this can
be locally, with SQLite Database. After login users will be navigated to the main screen, where
they will have a bottom navigation, with the help of it they can navigate in the app easily.
2. **Home screen:** Will be a bottom navigation item for it and on this screen the user can view the
top movies from the Movie DB. Also will have on option to search for movies in the DB. One
list item should have an Image, Short description and a Title. Pagination is also needed at
listing.
3. **Profile screen:** Will be a bottom navigation item for it and on this screen the user can view
his/her profile, can change profile picture and can change password. All locally, with with
SQLite Database.
4. **Detail screen:** For this create a separate screen, without bottom navigation. You need on “X”
in the top right corner for closing it, if needed. Also you have to display a video about it,
images (with a scrolling listing horizontally), description, title, a like button (to favourites) and a
related movies list (also with a scrolling listing horizontally - each item will have an image and
a title, in case you click on it will show the detail page of it).
5. **Favourites screen:** In case you like a movie on the Detail screen, then it will be saved in a
local DB, and will be listed here. Will be a bottom navigation item for this too. It will work like
the Home screen, just the data will come from the favourites local DB.
6. **Now in cinemas screen:** Will be a bottom navigation item for it and on this screen the users
can check the currently playing movies in the cinemas. For this you will have a local DB, which
will be updated in every 12 hour by a service which will download and save the movies with
the release date for the past 3 and the next 3 days. In every 12 hour the services deletes the
old data and populates with the new ones. The functionality of the screen has to be like the
Home screen.
