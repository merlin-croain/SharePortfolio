/*
 * Copyright 2023 Croain
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.utc.ida.forge.metier;

import java.util.Objects;

/**
 * Action class.
 */
public abstract class AbstractAction implements Comparable<AbstractAction> {
    /**
     * Const to set the hash key.
     */
    private static final int HASHKEY = 53;

    /**
     * Const to set the hash coefficient.
     */
    private static final int HASHCOEF = 3;

    /**
     * Libelle of an action.
     */
    private final String libelle;

    /**
     * Constructor for the Action class.
     * @param s libelle
     */
    protected AbstractAction(final String s) {
        this.libelle = s;
    }

    /**
     * Get the value of libelle.
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Abstract function to get the value of an action.
     * @param j Jour
     * @return double
     */
    public abstract double valeur(Jour j);

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = HASHCOEF;
        hash = HASHKEY * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    /**
     * @param obj Objet
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractAction other = (AbstractAction) obj;
        return Objects.equals(this.libelle, other.libelle);
    }

    /**
     * Fonction pour afficher l'action de manière compréhensible.
     * @return String
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }

    /**
     *  CompareTo for class Action.
     *
     * @param o the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(final AbstractAction o) {
        return this.libelle.compareTo(o.getLibelle());
    }
}
