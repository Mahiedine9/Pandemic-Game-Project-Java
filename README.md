# l2s4-projet-2023


# Equipe

- FERROUDJ SID AHMED
- FERDJOUKH MAHIEDINE
- LAMRANI MASSINISSA
- CHARARA MOHAMAD

# Sujet

[Le sujet 2023](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2023.pdf)

#Instruction 

Un lancement réussi de ce jeu se fait en suivant les étapes suivantes :

- Définissez la variable CLASSPATH sur votre machine.

- Générer la documentation javadoc.

- Compiler l'ensembles des classes Java.

- Compiler et exécuter les tests Java.

- Éxécuter les programmes.

## Définition de la variable 'CLASSPATH' Java

- sous linux :

```bash
  someone@somewhere:~$ export CLASSPATH="src:classes"
```
- sous windows :

```bash
  someone@somewhere:~$ $env:CLASSPATH="src;classes"

```

## Générer la documentation javadoc.

- en vous mettant sur le dossier principale de projet:

```bash
  someone@somewhere:~$ cd l2s4-projet-2023
  someone@somewhere/../l2s4-projet-2023:~$ 

```
- lancez la commande :

```bash
  someone@somewhere/../tp5:~$ javadoc -d docs -subpackages pandemicgam
```
- N.B : La documentation générée est ensuite consutlable via un navigteur sur tp5/docs.

## Compiler l'ensembles des classes de Jeu

- toujours depuis le dossier parent. afin de compiler l'intégralité des fichers sources:

```bash
someone@somewhere/../l2s4-projet-2023:~$ javac src/pandemicgame/*.java -d classes

```
N.B : il est nécessaire de compiler touts les fichiers de sources.


## Compiler et exécuter les tests Java
- on commence par compliler les tests :

```bash
insctuction sur comment compiler les tests Junit hors eclipse à venir
```
- puis éxécuter un des tests :
```bash
insctuction sur comment éxécuter les tests Junit hors eclipse à venir
```


## Éxécuter les programmes

- toujours depuis le dossier parent, on éxécute un programme à la fois :

```bash
someone@somewhere/../l2s4-projet-2023:~$ java pandemicgame.PandemicGameMain

```


# Livrables

## Livrable 1 

### Atteinte des objectifs
- ✔️  UML fait
- ✔️  Classe City fait 
- ✔️  Classe World fait.
- ✔️  Classes des Tests fait
- ✔️ Constructeur fait et testé ( affihcée dans test/WorldTest :l'ensemble des villes avec pour chacune  la liste de leur voisines)


## Livrable 2

### Atteinte des objectifs
 - ✔ UML 
 - ✔️ Classe Player
 - ✔️ Classes des Cartes
 - Dans le main : 
  - ✔️  Créer 4 joueurs, un pour chaque rôle, et les placer sur une ville ;
  - ✔️  Tirer 2 cartes de la pile des cartes infection et effectuer les actions associées (donc être capable de dérouler une phase d'infection, au moins manuellement) ;
  - ✔️ Pour chaque joueur : tirer 2 cartes de la pile des cartes joueur et les ajouter à la main du joueur. Si une carte épidémie est tirée, il faut résoudre son utilisation correctement.


## Livrable 3

   - ⏳ crée un plateau de jeu à partir d’un fichier JSON passé en argument sur la ligne de commande :
   - ⏳ création des villes, création des paquets de cartes, et de tout ce qui est nécessaire pour le jeu ;
   - ⏳ crée 4 joueurs, chacun ayant un rôle différent, les place sur une ville (peu importe laquelle) ;
   - ⏳ lance le jeu qui réalise toutes les phases (pour chaque joueur : jouer 4 actions, piocher 2 cartes, propager les maladies, se référer au sujet et à la règle du jeu).

N.B : La gestion de la fin du jeu n'est pas obligatoire (le jeu pourra ne pas se terminer).


## Livrable 4



# Journal de bord

## Semaine 8

- dans le cadre de rattraper notre travail, Nous avons commencer à corriger les livrable 1 et 2.

## Semaine 9
- pour le livrable 2. il nous reste quelques modifications àfin de pouvoir lancer la mini-partie prévue pour ce dernier.

## Semaine 10

## Semaine 11

## Semaine 12
