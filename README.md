# Movie Mobile App

Movie Mobile est une application mobile Android développée en Kotlin et utilise l'architecture `MVVM (Model-View-ViewModel)` avec `Retrofit` pour interagir avec l'API publique de The Movie Database (TMDb).

## Fonctionnalités


### 1. `Recherche de Films par Genres :`
   - Affiche une liste de films populaires provenant de l'API TMDb.
   - Permet à l'utilisateur de rechercher des films en fonction de genres spécifiques.
   - Prise en charge de la pagination pour charger davantage de films.
     
### 2.  `Recherche par Nom de Film :`
   - Offre une fonction de recherche permettant à l'utilisateur de filtrerla liste des films en fonction de leur nom.

### 3. `Page de Détails du Film :`
   - Affiche des informations détaillées sur un film sélectionné, y compris le titre, la date de sortie, le résumé, etc.

### 4. `Gestion des Erreurs de Connexion:`
   - Utilisation d'un `OkHTTP intercepteur` pour détecter les erreurs de connexion lors des appels à l'API TMDb.
   - La classe `Connectivity Manager` est employée pour détecter l'absence de connexion Internet.
   - Des messages d'erreur clairs et informatifs sont affichés à l'utilisateur en cas de défaillance de la connexion.

## Démo

https://github.com/wiem2000/Movie-Mobile-APP/assets/96753615/1b041cc4-b2d3-4d23-a09d-4575c7a21434

