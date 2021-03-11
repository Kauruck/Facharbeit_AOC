input metauml;
beginfig(uml);
    Class.A("Knoten")("+ gebäude : Gebäude", "+ kanten : Kante[]", "+ x : Dezimalzahl", "+ y : Dezimalzahl")();
Class.B("Kante")("+ A : Knoten", "+ B : Knoten", "+ attraktivität : Dezimalzahl")();
Class.C("Lagerhaus :: Gebäude")()("+ balance(ressource : Ressource) : Dezimalzahl", "+ blanceEnthält(ressource : Ressource) : Wahrheitswert");
Class.D("Gebäude")("+ verbraucht : Ressourcen[]", "+ produziert : Ressourcen[]", "+ produktionsZeit : Zahl")();
Class.E("Ameise")("+ momentanePosition : Knoten", "+ transportierteRessource : Ressource")("- berrechneP(kante : Kante) : Dezimalzahl","- berrechnep(kante : Kante) : Dezimalzahl","- effektivität(kante : Kante) : Dezimalzahl","- summeWahrscheinlichkeiten() : Dezimahlzahl", "+ selektiereKante() : Kante");
Class.F("Colonie")("+ alpha : Dezimalzahl","+ beta : Dezimalzahl","+ theta : Dezimalzahl","+ rho : Dezimalzahl","+ Q : Dezimalzahl","+ ameisen : Ameise[]", "+ gegangeneWege : Kante[]", "+ alleKnoten : Knoten[]")("+ verdunste () : nichts", "+ belohne() : nichts", "+ bewegeAlle() : nichts");
Class.G("Ressource")("+ volumen : Dezimalzahl")();
topToBottom(10)(A, B, D, C, E, F,G);

drawObjects(A,B,D, C, E, F,G);
endfig;
end
