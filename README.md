# smashlike-game

##Présentation
Ce projet a été réalisé dans le cadre d'un projet à sujet libre pour notre première année dans l'école d'ingénieurs ENSEEIHT.
Nous avons réalisé un jeu s'inspirant de Smash bros afin d'étudier le développement des jeux vidéo et de ces différents aspects.

![Screenshot du jeu Smash](https://raw.github.com/AdoPi/smashlike-game/master/presentation.jpg)

Nous avons donc codé un mini moteur de jeu ainsi qu'un petit moteur physique tout en respectant une architecture MVC.

## Compilation
Vous pouvez compiler l'intégralité du projet à l'aide de la commande 'ant'
grâce au fichier build.xml.


Le résultat de la compilation sera dans build/classes/
Un fichier .jar executable sera aussi créé.

## Lancer le jeu
Le projet se lance de deux manieres :
  un menu graphique dans le package menu (classe Main)
  le jeu sans menu qui se lance depuis le Main dans le default package

Il suffira donc de lancer Main ou menu.Main pour démarrer le projet.

    $ cd build/classes ; java Main

    $ cd build/classes ; java menu.Main

## Auteurs

Remerciements à tous les membres du groupe 44 :

* Louis QIU
* Joseph MECHALI
* Luca ROCHEREAU
* Taha ELLEUCH
* Adonis NAJIMI
