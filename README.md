# Pour démarrer le projet

- Se placer sur une connexion hors VPN !
- Si ce n'est déjà fait (démarrage automatique ?), démarrer la BDD MySQL avec **MySQL workbench**
- En haut de la fenêtre IntelliJ → flèche verte
- Ou fichier **SpringBootLibraryApplication.java** → ligne ``public class SpringBootLibraryApplication`` → flèche verte
- Cf. **resources/application.properties** pour :
  - le **client ID** du projet dans Okta : 0oa9veuaf4qsT2nkb5d7 (_Dans un vrai projet faire en sorte de le rendre inaccessible_),
  - l'**URL de l'issuer** côté Okta : https://dev-35751166.okta.com/oauth2/default

# Dans le projet

- Comment gérer le JWT (passage au backend, traitement du jeton...), voir :
  - ``controller/BookController.java``,
  - ``utils/ExtractJWT.java``
  - ``config/SecurityConfiguration.java`` permet entre autres choses :
    - de vérifier, côté backend, la validité du token qui est transmis du front au back, avant que ce token ne soit utilisé par le back,
    - cette vérification passe par une communication en sous-main, avec le backend d'Okta (serveur Idp d'Okta)
# Tips

- Ctrl + Alt + L : formater le code
- Ctrl + Alt + Maj + L : accéder à la fenêtre de formatage de code