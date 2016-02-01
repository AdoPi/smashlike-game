# smashlike-game

##Présentation
Ce projet a été réalisé dans le cadre d'un projet à sujet libre pour notre première année dans l'école d'ingénieurs ENSEEIHT.
Nous avons réalisé un jeu s'inspirant de Smash bros afin d'étudier le développement des jeux vidéo et de ces différents aspects.

![Screenshot du jeu Smash](https://raw.github.com/AdoPi/smashlike-game/master/presentation.jpg)

Nous avons donc codé un mini moteur de jeu ainsi qu'un petit moteur physique tout en respectant une architecture MVC.

## Compilation
Vous pouvez compiler l'intégralité du projet à l'aide de la commande 'ant'
grâce au fichier build.xml.

    $ ant

Le résultat de la compilation sera dans build/classes/
Un fichier .jar executable sera aussi créé.

## Lancer le jeu
Le projet se lance de deux manieres :
  un menu graphique dans le package menu (classe Main)
  le jeu sans menu qui se lance depuis le Main dans le default package

Pour lancer le .jar :
    $ java -jar dist/projet.jar

Sinon, il faudra simplement lancer Main ou menu.Main pour démarrer le projet.

    $ cd build/classes ; java Main

    $ cd build/classes ; java menu.Main

## Comment jouer ?

### Le menu
Le menu n'étant pas très intuitif, voici quelques explications.

Commencez par choisir le mode de jeu (de haut en bas : Vie, entrainement, Temps).
Puis, selectionnez un personnage ainsi qu'un stage en cliquant sur les icones respectives.


### Les commandes en jeu
Les touches définies par défaut sont présentées dans l'image ci-dessous :
![Screenshot des touches du jeu](https://raw.github.com/AdoPi/smashlike-game/master/touches.png)


Un joueur possède 4 touches de direction (celle du haut permettant de sauter).
De plus, chaque joueur possède 2 boutons d'attaques.
Il est possible de combiner un bouton d'attaque avec une touche de direction.
Ce qui donne un total de 8 attaques différentes par personnage !


## Auteurs

Remerciements à tous les membres du groupe 44 :

* Louis QIU
* Joseph MECHALI
* Luca ROCHEREAU
* Taha ELLEUCH
* Adonis NAJIMI
