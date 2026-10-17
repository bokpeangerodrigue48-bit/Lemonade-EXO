# Lemonade

Petite appli Android que j'ai faite pour le chapitre 7 du cours de développement mobile. Le principe est : on tape sur l'image, on cueille un citron, on le presse, on boit la citronnade, et on recommence.

## Comment ça marche

Il y a 4 écrans :

1. **Le citronnier** : on tape dessus pour cueillir un citron.
2. **Le citron** : il faut taper plusieurs fois pour le presser. Le nombre de clics est tiré au hasard entre 2 et 4, donc ça change à chaque fois.
3. **Le verre de citronnade** : on tape pour la boire.
4. **Le verre vide** : on tape pour recommencer depuis le début.

## Ce que j'ai utilisé

- Kotlin
- Jetpack Compose (Material 3)
- Android Studio

Le projet est un *Empty Activity*, package `ci.pigier.lemonade`, SDK minimum 24.

## Lancer le projet

1. Ouvre le dossier dans Android Studio.
2. Attends que Gradle finisse de se synchroniser .
3. Lance sur un émulateur ou sur ton téléphone.

## Le code

Tout est dans `MainActivity.kt`. J'ai deux variables d'état :

- `etape` retient à quelle étape on est (de 1 à 4) ;
- `squeezeCount` compte combien de fois il reste à presser le citron.

Au clic, un `when` regarde l'étape en cours et décide quoi faire. Pour le nombre aléatoire j'utilise simplement `(2..4).random()`.

J'ai mis `rememberSaveable` plutôt que `remember`, sinon l'appli revenait au citronnier à chaque rotation de l'écran. Les textes sont dans `strings.xml` (avec aussi des descriptions pour les images, pour l'accessibilité).


