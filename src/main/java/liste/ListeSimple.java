package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Returns the current size of the list.
     *
     * @return the number of elements in the list
     */
    public long getSize() {
        return size;
    }

    /**
     * Adds a new element to the front of the list.
     *
     * @param element the element to be added
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifies the value of the element that has the specified value
     * as an argument.
     * If multiple elements in the list have this value, only the first
     * occurrence is modified.
     *
     * @param element the value to modify
     * @param nouvelleValeur the new value
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifies the value of every element in the list that matches the specified value.
     * If multiple elements in the list have this value, all occurrences are modified.
     *
     * @param element the value to be modified
     * @param nouvelleValeur the new value to replace the old value
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Returns a string representation of the list.
     * The string is a comma-separated list of string representations of each
     * element in the list, enclosed in parentheses.
     *
     * @return a string representation of the list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Removes the first occurrence of the specified element in the list.
     * If the list is empty or the specified element is not in the list,
     * the list is left unchanged.
     *
     * @param element the element to be removed
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Removes all occurrences of the specified element in the list.
     * If the list is empty or the specified element is not in the list,
     * the list is left unchanged.
     *
     * @param element the element to be removed
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Removes all occurrences of the specified element in the list, starting
     * from the specified head of the list.
     * If the list is empty or the specified element is not in the list,
     * the list is left unchanged.
     * @param element the element to be removed
     * @param tete the head of the list
     * @return the new head of the list
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Returns the node that is just before the last node of the list.
     * Returns null if the list is empty or has only one element.
     * @return the node that is just before the last node of the list
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse the order of the elements of the list.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Returns the node that is just before the given node in the list.
     * Returns null if the given node is the first node of the list.
     * @param r the node to find the predecessor of
     * @return the node that is just before the given node in the list
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }
    
    /**
     * Exchanges the two nodes r1 and r2 in the list.
     * If r1 is the first, then r2 becomes the first.
     * If r2 is the first, then r1 becomes the first.
     * @param r1 the first node to exchange
     * @param r2 the second node to exchange
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else{
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}