# **Hotel Management Project**

#### Hi there 👋 it's arixstoo again !
## Description du Projet
Le projet est un système de gestion d'hôtel, développé en Java avec une interface utilisateur utilisant Swing et une base dennées utilisant MongoDB. L'application permet aux utilisateurs de faire des réservations de chambres, d'afficher les détails des chambres disponibles et de gérer les réservations existantes. L'application propose aussi une interface et un contrôle complet pour les admins et les réceptionnistes de l'hôtel. 

## Technologies Utilisées
**- Langage de Programmation** : **Java**

**- Bibliothèque d'Interface Graphique** : **Swing**

**- Gestion de la Base de Données** : **MongoDB** (via le driver Java MongoDB)

**- Collections et Maps** : Avant d'intégrer MongoDB, les données étaient stockées en utilisant des **Map** en Java pour organiser et manipuler les informations.

**- MVC (Model-View-Controller)** : Architecture utilisée pour **organiser** le code de l'application de manière **modulaire** et maintenable.


## Structure du Projet : Architecture MVC (Model-View-Controller)
L'architecture **MVC** divise l'application en trois composants principaux :

### **Model (Modèle)** :
Représente les **données** de l'application. Dans ce projet, il inclut des classes comme Chambre, **Client**, et Réservation qui interagissent avec **MongoDB** pour gérer les données. Ces classes centralisent la manipulation et la validation des données.

### **View (Vue)** :
Gère **l'interface utilisateur** de l'application. Elle est constituée des fenêtres et panneaux **Swing**, comme dans la classe reservation, permettant aux utilisateurs de **sélectionner** des chambres et de **saisir** des dates.

### **Controller (Contrôleur)** :
Coordonne **les interactions** entre le modèle et la vue. Il capte les actions des utilisateurs **(clics de boutons)** et met à jour le modèle en conséquence. Par exemple, dans la classe reservation, le bouton **"continuer"** déclenche des mises à jour du modèle et affiche des messages d'erreur si nécessaire.


## Fonctionnalités Clés
**Effectuer des Réservations** : Permet de faire des réservations en ligne **en sélectionnant** des chambres disponibles et en fournissant les dates de début et de fin de **séjour**.

**Modification des Chambres** : Les chambres disponibles sont affichées avec **leurs caractéristiques**, telles que le type et le prix. Les utilisateurs peuvent sélectionner les chambres souhaitées pour **leur réservation**.

**Validation des Dates** : Le système vérifie que les dates entrées sont au format **correct** et qu'elles sont **valides** pour éviter les erreurs de saisie.

## Fonctionnalités Administratives
Le côté administrateur de l'application offre des fonctionnalités supplémentaires pour la gestion des réservations et des chambres :

**Visualisation des Réservations** : Les administrateurs peuvent accéder à une interface qui affiche toutes **les réservations** effectuées, avec des options pour **accepter ou refuser** des réservations. Les réservations sont listées avec **les détails** pertinents tels que le nom du client, les dates de séjour, et les chambres réservées.

**Gestion des Chambres** : Les administrateurs peuvent voir toutes les chambres **disponibles** et **leur état** (libre ou occupée). Ils ont la possibilité de mettre à jour les informations sur les chambres, telles que **le prix** et le type.

**Gestion de l'Hôtel** : Les administrateurs peuvent **modifier** les détails des réservations existantes, y compris l'ajout ou **la suppression** de chambres, ainsi que la modification **des dates** de séjour.


## Instructions pour l'Exécution
### Cloner le Repository :
ecrivez sur le bash : git clone https://github.com/arixstoo/Hotel-Management-Project.git

### Configurer le Projet :
Assurez-vous que Java est installé sur votre machine.
Importez le projet dans votre IDE Java préféré.

### Installer les Dépendances :
Assurez-vous d'avoir les dépendances nécessaires pour MongoDB (ajoutez-les au fichier pom.xml si vous utilisez Maven ou build.gradle pour Gradle).

### Exécuter l'Application :
Exécutez la classe '/Controller/main' pour démarrer l'application.

## 🧰 Languages and Tools
        

<img align="left" alt="GitHub" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/github/github-original.svg" />
<img align="left" alt="Git" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" />
<img align="left" alt="VsCode" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/vscode/vscode-original.svg" />
<img align="left" alt="IntelliJ" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" />
<img align="left" alt="Java" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"/>
<br />


## 📊 Reach me here:

[LinkedIn]: <a href="https://www.linkedin.com/in/saidi-mohamed-rostom-21b3b525a/" target="_blank">arixstoo</a>
<br />
[E-mail]: saidi.rostom26@gmail.com
<br />
[Discord]: arixstoo
