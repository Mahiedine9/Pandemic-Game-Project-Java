# l2s4-projet-2023


# Equipe

- FERROUDJ SID AHMED
- FERDJOUKH MAHIEDINE
- LAMRANI MASSINISSA
- CHARARA MOHAMAD

# Sujet

[Le sujet 2023](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2023.pdf)

# Livrables

## Livrable 1 

L'expoiltation de ce projet se fait en suivant les étapes suivantes :

- Définissez la variable CLASSPATH sur votre machine.

- Générer la documentation javadoc.

- Compiler l'ensembles des classes Java.

- Compiler et exécuter les tests Java.

- Éxécuter les programmes.

Définition de la variable 'CLASSPATH' Java

- sous linux :

```bash
  someone@somewhere:~$ export CLASSPATH="src:classes"
```
- sous windows :

```bash
  someone@somewhere:~$ $env:CLASSPATH="src;classes"

```

--Générer la documentation javadoc--

- en vous mettant sur le dossier principale de projet :

```bash
  someone@somewhere:~$ cd PandemicGame
  someone@somewhere/../PandemicGame:~$ 

```
- lancez la commande :

```bash
  someone@somewhere/../TP8:~$ javadoc -d docs -subpackages pandemicGame
```

- N.B : La documentation générée est ensuite consutlable via un navigteur sur pandemicGame/docs.

--Compiler l'ensembles des classes Java--

- toujours depuis le dossier parent. Pour compiler l'intégralité des fichers sources (recommandé):

```bash
someone@somewhere/../TP8:~$ javac src/pandemicGame/src/*.java -d classes 
```

- ou un seul ficher à la fois :

```bash
someone@somewhere/../TP8:~$ javac src/sous_dossier/fichier.java -d classes
```

N.B : il est nécessaire de compiler touts les fichiers de sources.


--Compiler et exécuter les tests Java--
- on commence par compliler les tests :

```bash
someone@somewhere/../TP8:~$ javac -classpath test4poo.jar test/pandemicGame/*.java -d classes
```
- puis éxécuter un des tests :
```bash
someone@somewhere/../TP8:~$ java -jar test4poo.jar pacakge.TestName
```

### Atteinte des objectifs

- UML, Classes City/WorldMap et leur Classes de tests respectives.

### Difficultés restant à résoudre


## Livrable 2

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

## Semaine 1

## Semaine 2

## Semaine 3

## Semaine 4

## Semaine 5

## Semaine 6

## Semaine 7

## Semaine 8

## Semaine 9

## Semaine 10

## Semaine 11

## Semaine 12
