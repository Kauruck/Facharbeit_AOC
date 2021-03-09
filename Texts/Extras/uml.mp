input metauml;
beginfig(uml);
    Class.A("Knoten")("+ gebäude : Gebäude", "+ kanten : Katen[]")();
Class.B("Kante")("+ A : Knoten", "+ B : Knoten")();
Class.C("Lagerhaus")("+ verbraucht : Ressourcen[]", "+ produziert : Ressourcen[]", "+ produktionsZeit : Zahl")("+ balance(ressource : Ressource) : Dezimalzahl", "+ blanceEnthält(ressource : Ressource) : Wahrheitswert");
Class.D("Gebäude")("+ verbraucht : Ressourcen[]", "+ produziert : Ressourcen[]", "+ produktionsZeit : Zahl")();
Class.E("Ameise")("+ momentanePosition : Knoten", "+ transportierteRessource : Ressource")("- berrechneP(kante : Kante) : Dezimalzahl","- berrechnep(kante : Kante) : Dezimalzahl","-effizienz(kante : Kante) : Dezimalzahl","- summeWahrscheinlichkeit() : Dezimahlzahl", "+ selektiereKante() : Kante");
Class.F("Colonie")("+ alpha : Dezimalzahl","+ beta : Dezimalzahl","+ theta : Dezimalzahl","+ rho : Dezimalzahl","+ Q : Dezimalzahl","+ ameisen : Ameise[]", "+ gegangeneWege : Kante[]", "+ alleKnoten : Knote[]")("+ verdunste () : nichts", "+ belohne() : nichts", "+ bewegeAlle() : nichts");
Class.G("Ressource")("+ volumen : Dezimalzahl")();
topToBottom(10)(A, B, D, C, E, F,G);

drawObjects(A,B,D, C, E, F,G);
endfig;
end
