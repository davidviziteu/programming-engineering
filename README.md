# programming-engineering

Simulator de trafic.

-sunt generate niste masini pe harta in mod aleatoriu, destinatiile lor vor fi marginile ecranului, corespunzand capatului unei strazi;

-utilizatorul va adauga o masina pe harta si-i va da destinatia, dupa care se alege cel mai favorabil drum,pentru a ajunge la destinatie: drumul cel mai scurt sau cel optim, in functie de diferenta de timp pe care o petrece in trafic, raportata la distanta parcursa;

-utilizatorul poate alege densitatea traficului si viteza cu care merg masinile;

# Ce am facut noi (echipa de algoritmica1 - David Viziteu + Andreea Ciocan):
- miscam masinile pe harta, la fiecare moment, stiindu-se pozitia fiecare masini pe strada; 
- calculam drumul cel mai scurt pt fiecare masina si estimam cat va dura ca o masina
    sa ajunga la destinatie;
- functionarea semafoarelor (logica);
- teste pentru codul nostru;
- integrare;
- modulul de grafica (stim, suna ironic)


# Ce folosim din laboratoare:
- Objects and Classes 
- Interfaces. Generics. Collections 
- Concurrency: Threads and Locks (syncronised(), wait-notify, readWrite locks)
- stream api
- Working with Files
- Graphical User Interfaces (JavaFX)
