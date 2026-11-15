Question 1 : 
On utilise forward lorsque le login echoue pour conserver toute la requete( ne pas perdre le nom de l'utilisateur par exemple) sans changer l'url. 
Alors qu'on utilise senRedirect quand il reussit pour changer l'url et rediriger vers une autre page.

Question 2 :
si doPost() fait un forward vers la liste, lorque l'utilisateur appuie sur F5, le navigateur peut envoyer la derniere requete, d'ou le risque d'avoir le meme produit plusieurs fois
si doPost() fait un redirect vers la liste, lorque l'utilisateur appuie sur F5, le navigateur repete juste la requete get, donc le produit est enregistré une fois.
le mecanisme s'appele le PRG(post,redirect,get)

Question 3 :
avec ${produit.nom} le contenu s'affiche directement sans echappement automatique et on peut l'executer
avec <c:out value="${produit.nom}"/> le script s'affiche comme un text et ne sera pas execute
c'est mieux d'utiliser c:out pour eviter les attaques XSS (cross site Scripting)

Question 4 : 
filtre permet de securise la page en verifiant l'authentification des utilisateurs avant l'accées aux pages protegées, et centralisé les verifications.
deux autres utilisation : 
le filtre d'encodage: gere correctement les caractére speciaux et les accents avec UTF-8
le filtre de journalisation : enregistrer les requetes http et suivre les activités de l'application
