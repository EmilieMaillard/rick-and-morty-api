Ce projet est une application utilisant l'API Rick et Morty. Il est écrit en Kotlin Multiplateforme et utilise la bibliothèque Jetpack Compose pour créer une interface utilisateur réactive. 
L'application est conçue pour fonctionner sur plusieurs plateformes (ici : Android et Desktop).

L'architecture logicielle est en MVI (Model-View-Intent) et utilise la Clean Architecture.
Le modèle est séparé en trois couches bien distinctes :
- La couche UI : Partie qui gère l'affichage de l'application (affichage de données, gestion des événements utilisateur).
- La couche Domain : Partie qui gère la logique de l'application. Elle gère les données et de la logique métier (models, interfaces de repository). Cette couche n'est pas dépendante de la couche UI et de la couche Data, elle est donc indépendante de la technologie utilisée (API, base de données, etc...).
- La couche Data : Partie qui gère les données de l'application. Son rôle est de géré la liaison entre la couche Domain et les implémentations de repository. 

Concernant l'injection de dépendances, on utilise Koin.


* `/composeApp` : C'est le module principal de l'application qui regroupe tout le code
  - `androidMain` : Code bien spécifique à Android. Certains éléments ne peuvent être faisable que sur Android et peut être pas d'autres plateformes, dans ce cas, il faut développer la fonctionnalité dans cette partie. 
    - `ui` : C'est la partie UI de l'application côté android.
    - `data` : C'est la partie qui gère les données de l'application côté android. 
  - `desktopMain` : Code bien spécifique à Desktop. Certains éléments ne peuvent être faisable que sur Desktop et peut être pas d'autres plateformes, dans ce cas, il faut développer la fonctionnalité dans cette partie.
  - `commonMain` is for code that’s common for all targets.
    - `composeResources` : Ressources de l'application (images, icônes, etc...)
    - `kotlin` : C'est là qu'on retrouve l'ensemble du code source de l'application pour toutes les plateformes réunies. 
      - `common` : Tous les éléments qui sont communs pour toutes les plateformes. 
      - `data` : C'est la partie qui gère les données de l'application.
        - `local` : On y retrouve les objets qui gèrent la base de données ainsi que les DAO (Data Access Object) qui permettent d'effectuer les CRUD. 
        - `remote` : On y retrouve les fichiers qui gèrent les appels de l'API. 
        - `repositories` : Ce sont les repositories qui permettent de gérer les données de l'application, de la base de données à l'API (Service).
      - `domain` : C'est la partie qui gère la logique de l'application.
        - `models` : Création des modèles. 
        - `Repository` : On y retrouve les interfaces des repositories.
      - `ui` : C'est la partie Interface de l'application. 
        - `core` : Les éléments de base concernant l'UI de l'application.
          - `composables` : Les composants qui sont réutilisables dans l'application.
          - `theme` : Gestion du thème de l'application (couleurs, typographie, etc...)
        - `screens` : Les écrans (pages) de l'application. On y retrouve pour chaque écran un fichier pour le ViewModel et un fichier pour l'UI.
      
  - `build.gradle.kts` : Ce sont toutes les dépendances de l'application. Il faut faire attention à bien mettre les bonnes dépendances pour chaque plateforme.
  
